
package com.backbase.messaging.integration.rest.spec.v1.sms.compat;

import java.util.HashMap;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.messaging.integration.listener.spec.v1.sms.SendSMSListener;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSApi;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSPostRequestBody;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSPostResponseBody;
import org.apache.camel.Produce;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Default implementation of {@link SendSMSApi } which delegates to an
 * implementation of {@link SendSMSListener }, if one is present in the
 * application context.
 * <p>Conditionally added to the application context by the {@link DelegatingSendSMSApiImplConfig }
 * configuration class (see the documentation for that class for details of the conditions).</p>
 * 
 */
public class DelegatingSendSMSApiImpl implements SendSMSApi
{

    private final static Logger LOG = LoggerFactory.getLogger(DelegatingSendSMSApiImpl.class);
    @Produce(uri = DelegatingSendSMSApiImpl.RestToJmsListenerRouteProxy.POST_SEND_S_M_S_FROM_URI)
    private DelegatingSendSMSApiImpl.RestToJmsListenerRouteProxy postSendSMSRoute;

    @RequestMapping(method = RequestMethod.POST, value = "", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    @Override
    public SendSMSPostResponseBody postSendSMS(
        @RequestBody
        @Valid
        SendSMSPostRequestBody sendSMSPostRequestBody, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    {
        LOG.trace("Forwarding HTTP request for postSendSMS to JMS RequestListener.");
        InternalRequest<SendSMSPostRequestBody> request = new InternalRequest<SendSMSPostRequestBody>(sendSMSPostRequestBody, httpServletRequest, UUID.randomUUID().toString());
        RequestProxyWrapper<SendSMSPostRequestBody> requestProxyWrapper = new RequestProxyWrapper<SendSMSPostRequestBody>();
        HashMap<String, Object> pathParams = new HashMap<String, Object>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        requestProxyWrapper.setPathParams(pathParams);
        requestProxyWrapper.setQueryParams(queryParams);
        requestProxyWrapper.setRequest(request);
        requestProxyWrapper.setHttpMethod("post");
        requestProxyWrapper.setUrl("com.backbase.messaging.integration.listener.v1.sms");
        RequestProxyWrapper<SendSMSPostResponseBody> responseWrapper = postSendSMSRoute.postSendSMS((requestProxyWrapper));
        return responseWrapper.getRequest().getData();
    }


    /**
     * Interface for the Camel route proxies used in {@link DelegatingSendSMSApiImpl }.
     * <p>One {@code *_FROM_URI}, one {@code *_ROUTE_ID}, and one method per {@link SendSMSApi } method.</p>
     * 
     */
    public interface RestToJmsListenerRouteProxy {

        public final static String POST_SEND_S_M_S_FROM_URI = "direct:rest2jms.postSendSMS.DelegatingSendSMSApiImpl.compat.sms.v1.spec.rest.integration.messaging.backbase.com";
        public final static String POST_SEND_S_M_S_ROUTE_ID = "rest2jms.postSendSMS.DelegatingSendSMSApiImpl.compat.sms.v1.spec.rest.integration.messaging.backbase.com";

        public RequestProxyWrapper<SendSMSPostResponseBody> postSendSMS(RequestProxyWrapper<SendSMSPostRequestBody> request);

    }

}
