
package com.backbase.messaging.integration.listener.client.v1.sms;

import java.util.HashMap;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxy;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSPostRequestBody;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSPostResponseBody;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.camel.Produce;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


/**
 * Camel JMS client.
 * 
 */
@ConditionalOnProperty(name = "backbase.communication.outbound", havingValue = "JMS", matchIfMissing = true)
@Service
public class MessagingIntegrationSendSMSClientJmsImpl implements MessagingIntegrationSendSMSClient
{

    @Produce(uri = RequestProxy.DIRECT_PRODUCE_NAME)
    private RequestProxy requestProxy;

    @HystrixCommand(ignoreExceptions = {
        BadRequestException.class,
        InternalServerErrorException.class
    })
    public RequestProxyWrapper<SendSMSPostResponseBody> postSendSMS(InternalRequest<SendSMSPostRequestBody> sendSMSPostRequestBody)
        throws BadRequestException, InternalServerErrorException
    {
        RequestProxyWrapper<SendSMSPostRequestBody> requestProxyWrapper = new RequestProxyWrapper<SendSMSPostRequestBody>();
        HashMap<String, Object> pathParams = new HashMap<String, Object>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        requestProxyWrapper.setPathParams(pathParams);
        requestProxyWrapper.setQueryParams(queryParams);
        requestProxyWrapper.setRequest(sendSMSPostRequestBody);
        requestProxyWrapper.setHttpMethod("post");
        requestProxyWrapper.setUrl("com.backbase.messaging.integration.listener.v1.sms");
        return ((RequestProxyWrapper<SendSMSPostResponseBody> ) requestProxy.sendRequest(requestProxyWrapper));
    }

}
