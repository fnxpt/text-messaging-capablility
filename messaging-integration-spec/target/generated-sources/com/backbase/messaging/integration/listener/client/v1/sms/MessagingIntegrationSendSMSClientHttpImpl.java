
package com.backbase.messaging.integration.listener.client.v1.sms;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.communication.http.HttpCommunicationConstants;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequestContext;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSPostRequestBody;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSPostResponseBody;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * {@link RestTemplate } based HTTP client.
 * 
 * <p>Reduces the effort required from capabilities to migrate from JMS communication between services to HTTP.
 * Allows services using JMS clients to switch over to use HTTP based clients and back with no changes in implementation.</p>
 * 
 * <p>Can be enabled by setting the following property: {@code backbase.communication.outbound=HTTP}</p>
 * 
 */
public class MessagingIntegrationSendSMSClientHttpImpl implements MessagingIntegrationSendSMSClient
{

    protected RestTemplate restTemplate;
    protected String serviceId;
    protected ObjectMapper objectMapper;
    private final static Logger LOG = LoggerFactory.getLogger(MessagingIntegrationSendSMSClientHttpImpl.class);

    /**
     * All constructor parameters are required.
     * 
     * @param objectMapper
     *     Jackson ObjectMapper.
     * @param restTemplate
     *     Ribbon configured RestTemplate.
     * @param serviceId
     *     The application name in the registry.
     *     Typically the spring.application.name from the bootstrap properties of the service implementing this spec.
     *     A default can be configured with the plugin serviceId property.
     */
    public MessagingIntegrationSendSMSClientHttpImpl(RestTemplate restTemplate, String serviceId, ObjectMapper objectMapper) {
        Objects.requireNonNull(restTemplate, "RestTemplate is required.");
        Objects.requireNonNull(serviceId, "ServiceId is required.");
        Objects.requireNonNull(objectMapper, "ObjectMapper is required.");
        this.restTemplate = restTemplate;
        this.serviceId = serviceId;
        this.objectMapper = objectMapper;
    }

    @HystrixCommand(ignoreExceptions = {
        BadRequestException.class,
        InternalServerErrorException.class
    })
    public RequestProxyWrapper<SendSMSPostResponseBody> postSendSMS(InternalRequest<SendSMSPostRequestBody> sendSMSPostRequestBody)
        throws BadRequestException, InternalServerErrorException
    {
        RequestProxyWrapper<SendSMSPostResponseBody> responseRequestProxyWrapper = new RequestProxyWrapper<SendSMSPostResponseBody>();
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(("http://"+ serviceId)).path("/v1/sms");
            // If uriString is passed to restTemplate.exchange below, it ends up double-encoding the query string
            // part.  So first, it is converted to a URI, which will get handled properly in RestTemplate:
            String uriString = uriBuilder.buildAndExpand().toUriString();
            URI uri = new URI(uriString);
            InternalRequestContext internalRequestContext = sendSMSPostRequestBody.getInternalRequestContext();
            HttpHeaders httpHeaders = new HttpHeaders();
            String authToken = internalRequestContext.getSecurityContext();
            httpHeaders.add(HttpHeaders.AUTHORIZATION, authToken);
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
            // Add information sent over JMS in the internalRequestContext as request headers.
            httpHeaders.add(HttpCommunicationConstants.X_CXT_SOURCEADDRESS, internalRequestContext.getSourceAddress());
            httpHeaders.add(HttpCommunicationConstants.X_CXT_REQUESTTIME, String.valueOf(internalRequestContext.getRequestTime()));
            httpHeaders.add(HttpCommunicationConstants.X_CXT_USERAGENT, internalRequestContext.getUserAgent());
            httpHeaders.add(HttpCommunicationConstants.X_CXT_CHANNELID, internalRequestContext.getChannelID());
            httpHeaders.add(HttpCommunicationConstants.X_CXT_REQUESTUUID, internalRequestContext.getRequestUUID());
            httpHeaders.add(HttpCommunicationConstants.X_CXT_AUTHSTATUS, String.valueOf(internalRequestContext.getAuthStatus()));
            HttpEntity httpEntity = new HttpEntity(sendSMSPostRequestBody.getData(), httpHeaders);
            ResponseEntity<SendSMSPostResponseBody> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, SendSMSPostResponseBody.class);
            InternalRequest<SendSMSPostResponseBody> internalRequest = new InternalRequest<SendSMSPostResponseBody>(response.getBody(), sendSMSPostRequestBody.getInternalRequestContext());
            responseRequestProxyWrapper.setRequest(internalRequest);
        } catch (RestClientResponseException exception) {
            try {
                switch (exception.getRawStatusCode()) {
                    case  400 :
                        throw objectMapper.readValue(exception.getResponseBodyAsString(), BadRequestException.class);
                    case  500 :
                        throw objectMapper.readValue(exception.getResponseBodyAsString(), InternalServerErrorException.class);
                    default:
                        throw exception;
                }
            } catch (JsonMappingException jsonMappingException) {
                throw new InternalServerErrorException(jsonMappingException);
            } catch (JsonParseException jsonParseException) {
                throw new InternalServerErrorException(jsonParseException);
            } catch (IOException ioException) {
                throw new InternalServerErrorException(ioException);
            }
        } catch (Exception exception) {
            LOG.debug("Unexpected error sending request.", exception);
            throw new InternalServerErrorException(exception.getMessage(), exception);
        }
        return responseRequestProxyWrapper;
    }

}
