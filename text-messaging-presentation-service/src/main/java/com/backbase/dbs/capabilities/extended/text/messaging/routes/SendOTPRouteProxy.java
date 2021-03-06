package com.backbase.dbs.capabilities.extended.text.messaging.routes;


import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostRequestBody;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostResponseBody;

public interface SendOTPRouteProxy {

    OneTimePasswordPostResponseBody requestOneTimePassword(InternalRequest<OneTimePasswordPostRequestBody> requestBodyInternalRequest);

}
