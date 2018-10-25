
package com.backbase.persistence.messaging.listener.client.v1.transactions;

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
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsPostRequestBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsPostResponseBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.TransactionGetResponseBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.VerifyTransactionPostRequestBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.VerifyTransactionPostResponseBody;
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
public class PersistenceMessagingOTPTransactionsClientHttpImpl implements PersistenceMessagingOTPTransactionsClient
{

    protected RestTemplate restTemplate;
    protected String serviceId;
    protected ObjectMapper objectMapper;
    private final static Logger LOG = LoggerFactory.getLogger(PersistenceMessagingOTPTransactionsClientHttpImpl.class);

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
    public PersistenceMessagingOTPTransactionsClientHttpImpl(RestTemplate restTemplate, String serviceId, ObjectMapper objectMapper) {
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
    public RequestProxyWrapper<OTPTransactionsPostResponseBody> postOTPTransactions(InternalRequest<OTPTransactionsPostRequestBody> oTPTransactionsPostRequestBody)
        throws BadRequestException, InternalServerErrorException
    {
        RequestProxyWrapper<OTPTransactionsPostResponseBody> responseRequestProxyWrapper = new RequestProxyWrapper<OTPTransactionsPostResponseBody>();
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(("http://"+ serviceId)).path("/v1/transactions");
            // If uriString is passed to restTemplate.exchange below, it ends up double-encoding the query string
            // part.  So first, it is converted to a URI, which will get handled properly in RestTemplate:
            String uriString = uriBuilder.buildAndExpand().toUriString();
            URI uri = new URI(uriString);
            InternalRequestContext internalRequestContext = oTPTransactionsPostRequestBody.getInternalRequestContext();
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
            HttpEntity httpEntity = new HttpEntity(oTPTransactionsPostRequestBody.getData(), httpHeaders);
            ResponseEntity<OTPTransactionsPostResponseBody> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, OTPTransactionsPostResponseBody.class);
            InternalRequest<OTPTransactionsPostResponseBody> internalRequest = new InternalRequest<OTPTransactionsPostResponseBody>(response.getBody(), oTPTransactionsPostRequestBody.getInternalRequestContext());
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

    @HystrixCommand(ignoreExceptions = {
        BadRequestException.class,
        InternalServerErrorException.class
    })
    public RequestProxyWrapper<TransactionGetResponseBody> getTransaction(InternalRequest<Void> request, String transactionId)
        throws BadRequestException, InternalServerErrorException
    {
        RequestProxyWrapper<TransactionGetResponseBody> responseRequestProxyWrapper = new RequestProxyWrapper<TransactionGetResponseBody>();
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(("http://"+ serviceId)).path("/v1/transactions/{transactionId}");
            // If uriString is passed to restTemplate.exchange below, it ends up double-encoding the query string
            // part.  So first, it is converted to a URI, which will get handled properly in RestTemplate:
            String uriString = uriBuilder.buildAndExpand(transactionId).toUriString();
            URI uri = new URI(uriString);
            InternalRequestContext internalRequestContext = request.getInternalRequestContext();
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
            HttpEntity httpEntity = new HttpEntity(httpHeaders);
            ResponseEntity<TransactionGetResponseBody> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, TransactionGetResponseBody.class);
            InternalRequest<TransactionGetResponseBody> internalRequest = new InternalRequest<TransactionGetResponseBody>(response.getBody(), request.getInternalRequestContext());
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

    @HystrixCommand(ignoreExceptions = {
        BadRequestException.class,
        InternalServerErrorException.class
    })
    public RequestProxyWrapper<VerifyTransactionPostResponseBody> postVerifyTransaction(InternalRequest<VerifyTransactionPostRequestBody> verifyTransactionPostRequestBody)
        throws BadRequestException, InternalServerErrorException
    {
        RequestProxyWrapper<VerifyTransactionPostResponseBody> responseRequestProxyWrapper = new RequestProxyWrapper<VerifyTransactionPostResponseBody>();
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(("http://"+ serviceId)).path("/v1/transactions/verify");
            // If uriString is passed to restTemplate.exchange below, it ends up double-encoding the query string
            // part.  So first, it is converted to a URI, which will get handled properly in RestTemplate:
            String uriString = uriBuilder.buildAndExpand().toUriString();
            URI uri = new URI(uriString);
            InternalRequestContext internalRequestContext = verifyTransactionPostRequestBody.getInternalRequestContext();
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
            HttpEntity httpEntity = new HttpEntity(verifyTransactionPostRequestBody.getData(), httpHeaders);
            ResponseEntity<VerifyTransactionPostResponseBody> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, VerifyTransactionPostResponseBody.class);
            InternalRequest<VerifyTransactionPostResponseBody> internalRequest = new InternalRequest<VerifyTransactionPostResponseBody>(response.getBody(), verifyTransactionPostRequestBody.getInternalRequestContext());
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
