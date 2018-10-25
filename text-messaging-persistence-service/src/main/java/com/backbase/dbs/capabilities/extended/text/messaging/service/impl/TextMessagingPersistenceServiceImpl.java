package com.backbase.dbs.capabilities.extended.text.messaging.service.impl;

import com.backbase.dbs.capabilities.extended.text.messaging.domain.OTP;
import com.backbase.dbs.capabilities.extended.text.messaging.repository.OTPRepository;
import com.backbase.dbs.capabilities.extended.text.messaging.service.TextMessagingPersistenceService;
import com.backbase.persistence.text.messaging.rest.spec.v1.otp.OTPPostRequestBody;
import com.backbase.persistence.text.messaging.rest.spec.v1.otp.VerifyOTPPostRequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TextMessagingPersistenceServiceImpl implements TextMessagingPersistenceService {

    private final OTPRepository otpRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TextMessagingPersistenceServiceImpl(OTPRepository otpRepository, ModelMapper modelMapper) {
        this.otpRepository = otpRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveNewOneTimePasswordRequest(OTPPostRequestBody transactionPostRequestBody) {
        OTP oneTimePasswordRequest = mapRequestToModel(transactionPostRequestBody);
        otpRepository.save(oneTimePasswordRequest);
        return oneTimePasswordRequest.getId();
    }

    @Override
    public OTP fetchOneTimePasswordRequest(String transactionId) {
        return otpRepository.findOne(transactionId);
    }

    @Override
    @Transactional
    public String updateOneTimePasswordRequestVerificationStatus(VerifyOTPPostRequestBody verifyTransactionPostRequestBody) {
        String transactionID = verifyTransactionPostRequestBody.getTransactionID();
        OTP oneTimePasswordRequest = otpRepository.findOne(transactionID);
        oneTimePasswordRequest.setVerified(verifyTransactionPostRequestBody.getVerified());
        return transactionID;
    }

    private OTP mapRequestToModel(OTPPostRequestBody transactionPostRequestBody) {
        OTP oneTimePasswordRequest = new OTP();
        oneTimePasswordRequest.setOtp(transactionPostRequestBody.getOtp());
        oneTimePasswordRequest.setPhoneNumber(transactionPostRequestBody.getPhoneNumber());
        oneTimePasswordRequest.setUserId(transactionPostRequestBody.getUserID());
        oneTimePasswordRequest.setVerified(false);
        return oneTimePasswordRequest;
    }
}
