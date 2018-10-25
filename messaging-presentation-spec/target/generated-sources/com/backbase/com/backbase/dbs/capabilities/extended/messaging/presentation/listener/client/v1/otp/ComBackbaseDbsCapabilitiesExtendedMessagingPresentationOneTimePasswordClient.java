
package com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.client.v1.otp;

import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostRequestBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostResponseBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostRequestBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostResponseBody;


/**
 * JMS client common interface.
 * 
 */
public interface ComBackbaseDbsCapabilitiesExtendedMessagingPresentationOneTimePasswordClient {


    public RequestProxyWrapper<OneTimePasswordPostResponseBody> postOneTimePassword(InternalRequest<OneTimePasswordPostRequestBody> oneTimePasswordPostRequestBody);

    public RequestProxyWrapper<OneTimePasswordVerifyPostResponseBody> postOneTimePasswordVerify(InternalRequest<OneTimePasswordVerifyPostRequestBody> oneTimePasswordVerifyPostRequestBody);

}
