package com.backbase.dbs.capabilities.extended.text.messaging.service;

import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostRequestBody;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostResponseBody;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostRequestBody;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostResponseBody;

public interface TextMessagingService {

    OneTimePasswordPostResponseBody requestOneTimePassword(InternalRequest<OneTimePasswordPostRequestBody> internalRequest);

    OneTimePasswordVerifyPostResponseBody verifyOneTimePassword(InternalRequest<OneTimePasswordVerifyPostRequestBody> internalRequest);


}
