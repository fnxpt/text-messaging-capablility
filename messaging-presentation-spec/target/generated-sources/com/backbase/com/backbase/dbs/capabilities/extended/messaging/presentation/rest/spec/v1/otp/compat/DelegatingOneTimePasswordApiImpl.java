
package com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.compat;

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
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.spec.v1.otp.OneTimePasswordListener;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordApi;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostRequestBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostResponseBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostRequestBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostResponseBody;
import org.apache.camel.Produce;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Default implementation of {@link OneTimePasswordApi } which delegates to an
 * implementation of {@link OneTimePasswordListener }, if one is present in the
 * application context.
 * <p>Conditionally added to the application context by the {@link DelegatingOneTimePasswordApiImplConfig }
 * configuration class (see the documentation for that class for details of the conditions).</p>
 * 
 */
public class DelegatingOneTimePasswordApiImpl implements OneTimePasswordApi
{

    private final static Logger LOG = LoggerFactory.getLogger(DelegatingOneTimePasswordApiImpl.class);
    @Produce(uri = DelegatingOneTimePasswordApiImpl.RestToJmsListenerRouteProxy.POST_ONE_TIME_PASSWORD_FROM_URI)
    private DelegatingOneTimePasswordApiImpl.RestToJmsListenerRouteProxy postOneTimePasswordRoute;
    @Produce(uri = DelegatingOneTimePasswordApiImpl.RestToJmsListenerRouteProxy.POST_ONE_TIME_PASSWORD_VERIFY_FROM_URI)
    private DelegatingOneTimePasswordApiImpl.RestToJmsListenerRouteProxy postOneTimePasswordVerifyRoute;

    @RequestMapping(method = RequestMethod.POST, value = "", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    @Override
    public OneTimePasswordPostResponseBody postOneTimePassword(
        @RequestBody
        @Valid
        OneTimePasswordPostRequestBody oneTimePasswordPostRequestBody, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    {
        LOG.trace("Forwarding HTTP request for postOneTimePassword to JMS RequestListener.");
        InternalRequest<OneTimePasswordPostRequestBody> request = new InternalRequest<OneTimePasswordPostRequestBody>(oneTimePasswordPostRequestBody, httpServletRequest, UUID.randomUUID().toString());
        RequestProxyWrapper<OneTimePasswordPostRequestBody> requestProxyWrapper = new RequestProxyWrapper<OneTimePasswordPostRequestBody>();
        HashMap<String, Object> pathParams = new HashMap<String, Object>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        requestProxyWrapper.setPathParams(pathParams);
        requestProxyWrapper.setQueryParams(queryParams);
        requestProxyWrapper.setRequest(request);
        requestProxyWrapper.setHttpMethod("post");
        requestProxyWrapper.setUrl("com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.v1.otp");
        RequestProxyWrapper<OneTimePasswordPostResponseBody> responseWrapper = postOneTimePasswordRoute.postOneTimePassword((requestProxyWrapper));
        return responseWrapper.getRequest().getData();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verify", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    @Override
    public OneTimePasswordVerifyPostResponseBody postOneTimePasswordVerify(
        @RequestBody
        @Valid
        OneTimePasswordVerifyPostRequestBody oneTimePasswordVerifyPostRequestBody, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    {
        LOG.trace("Forwarding HTTP request for postOneTimePasswordVerify to JMS RequestListener.");
        InternalRequest<OneTimePasswordVerifyPostRequestBody> request = new InternalRequest<OneTimePasswordVerifyPostRequestBody>(oneTimePasswordVerifyPostRequestBody, httpServletRequest, UUID.randomUUID().toString());
        RequestProxyWrapper<OneTimePasswordVerifyPostRequestBody> requestProxyWrapper = new RequestProxyWrapper<OneTimePasswordVerifyPostRequestBody>();
        HashMap<String, Object> pathParams = new HashMap<String, Object>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        requestProxyWrapper.setPathParams(pathParams);
        requestProxyWrapper.setQueryParams(queryParams);
        requestProxyWrapper.setRequest(request);
        requestProxyWrapper.setHttpMethod("post");
        requestProxyWrapper.setUrl("com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.v1.otp.verify");
        RequestProxyWrapper<OneTimePasswordVerifyPostResponseBody> responseWrapper = postOneTimePasswordVerifyRoute.postOneTimePasswordVerify((requestProxyWrapper));
        return responseWrapper.getRequest().getData();
    }


    /**
     * Interface for the Camel route proxies used in {@link DelegatingOneTimePasswordApiImpl }.
     * <p>One {@code *_FROM_URI}, one {@code *_ROUTE_ID}, and one method per {@link OneTimePasswordApi } method.</p>
     * 
     */
    public interface RestToJmsListenerRouteProxy {

        public final static String POST_ONE_TIME_PASSWORD_FROM_URI = "direct:rest2jms.postOneTimePassword.DelegatingOneTimePasswordApiImpl.compat.otp.v1.spec.rest.presentation.messaging.extended.capabilities.dbs.backbase.com.backbase.com";
        public final static String POST_ONE_TIME_PASSWORD_ROUTE_ID = "rest2jms.postOneTimePassword.DelegatingOneTimePasswordApiImpl.compat.otp.v1.spec.rest.presentation.messaging.extended.capabilities.dbs.backbase.com.backbase.com";
        public final static String POST_ONE_TIME_PASSWORD_VERIFY_FROM_URI = "direct:rest2jms.postOneTimePasswordVerify.DelegatingOneTimePasswordApiImpl.compat.otp.v1.spec.rest.presentation.messaging.extended.capabilities.dbs.backbase.com.backbase.com";
        public final static String POST_ONE_TIME_PASSWORD_VERIFY_ROUTE_ID = "rest2jms.postOneTimePasswordVerify.DelegatingOneTimePasswordApiImpl.compat.otp.v1.spec.rest.presentation.messaging.extended.capabilities.dbs.backbase.com.backbase.com";

        public RequestProxyWrapper<OneTimePasswordPostResponseBody> postOneTimePassword(RequestProxyWrapper<OneTimePasswordPostRequestBody> request);

        public RequestProxyWrapper<OneTimePasswordVerifyPostResponseBody> postOneTimePasswordVerify(RequestProxyWrapper<OneTimePasswordVerifyPostRequestBody> request);

    }

}
