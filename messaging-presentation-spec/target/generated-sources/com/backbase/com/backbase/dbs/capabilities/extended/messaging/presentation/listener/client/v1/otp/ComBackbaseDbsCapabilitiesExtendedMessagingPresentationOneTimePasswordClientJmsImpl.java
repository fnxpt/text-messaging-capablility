
package com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.client.v1.otp;

import java.util.HashMap;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxy;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostRequestBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostResponseBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostRequestBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostResponseBody;
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
public class ComBackbaseDbsCapabilitiesExtendedMessagingPresentationOneTimePasswordClientJmsImpl implements ComBackbaseDbsCapabilitiesExtendedMessagingPresentationOneTimePasswordClient
{

    @Produce(uri = RequestProxy.DIRECT_PRODUCE_NAME)
    private RequestProxy requestProxy;

    @HystrixCommand(ignoreExceptions = {
        BadRequestException.class,
        InternalServerErrorException.class
    })
    public RequestProxyWrapper<OneTimePasswordPostResponseBody> postOneTimePassword(InternalRequest<OneTimePasswordPostRequestBody> oneTimePasswordPostRequestBody)
        throws BadRequestException, InternalServerErrorException
    {
        RequestProxyWrapper<OneTimePasswordPostRequestBody> requestProxyWrapper = new RequestProxyWrapper<OneTimePasswordPostRequestBody>();
        HashMap<String, Object> pathParams = new HashMap<String, Object>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        requestProxyWrapper.setPathParams(pathParams);
        requestProxyWrapper.setQueryParams(queryParams);
        requestProxyWrapper.setRequest(oneTimePasswordPostRequestBody);
        requestProxyWrapper.setHttpMethod("post");
        requestProxyWrapper.setUrl("com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.v1.otp");
        return ((RequestProxyWrapper<OneTimePasswordPostResponseBody> ) requestProxy.sendRequest(requestProxyWrapper));
    }

    @HystrixCommand(ignoreExceptions = {
        BadRequestException.class,
        InternalServerErrorException.class
    })
    public RequestProxyWrapper<OneTimePasswordVerifyPostResponseBody> postOneTimePasswordVerify(InternalRequest<OneTimePasswordVerifyPostRequestBody> oneTimePasswordVerifyPostRequestBody)
        throws BadRequestException, InternalServerErrorException
    {
        RequestProxyWrapper<OneTimePasswordVerifyPostRequestBody> requestProxyWrapper = new RequestProxyWrapper<OneTimePasswordVerifyPostRequestBody>();
        HashMap<String, Object> pathParams = new HashMap<String, Object>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        requestProxyWrapper.setPathParams(pathParams);
        requestProxyWrapper.setQueryParams(queryParams);
        requestProxyWrapper.setRequest(oneTimePasswordVerifyPostRequestBody);
        requestProxyWrapper.setHttpMethod("post");
        requestProxyWrapper.setUrl("com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.v1.otp.verify");
        return ((RequestProxyWrapper<OneTimePasswordVerifyPostResponseBody> ) requestProxy.sendRequest(requestProxyWrapper));
    }

}
