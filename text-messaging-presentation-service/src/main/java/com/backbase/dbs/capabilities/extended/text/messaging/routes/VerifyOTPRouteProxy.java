package com.backbase.dbs.capabilities.extended.text.messaging.routes;

import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostRequestBody;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostResponseBody;

public interface VerifyOTPRouteProxy {

    OneTimePasswordVerifyPostResponseBody verifyOneTimePassword(InternalRequest<OneTimePasswordVerifyPostRequestBody> internalRequest);

}
