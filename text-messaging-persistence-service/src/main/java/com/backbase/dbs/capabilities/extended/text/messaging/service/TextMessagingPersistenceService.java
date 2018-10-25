package com.backbase.dbs.capabilities.extended.text.messaging.service;

import com.backbase.dbs.capabilities.extended.text.messaging.domain.OTP;
import com.backbase.persistence.text.messaging.rest.spec.v1.otp.OTPPostRequestBody;
import com.backbase.persistence.text.messaging.rest.spec.v1.otp.VerifyOTPPostRequestBody;

public interface TextMessagingPersistenceService {

    String saveNewOneTimePasswordRequest(OTPPostRequestBody transactionPostRequestBody);

    OTP fetchOneTimePasswordRequest(String transactionId);

    String updateOneTimePasswordRequestVerificationStatus(VerifyOTPPostRequestBody verifyTransactionPostRequestBody);
}
