package com.backbase.dbs.capabilities.extended.text.messaging.service.impl;

import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.dbs.capabilities.extended.text.messaging.routes.TextMessagingConstants;
import com.backbase.dbs.capabilities.extended.text.messaging.service.TextMessagingService;
import com.backbase.dbs.capabilities.extended.text.messaging.service.OneTimePasswordStrategyService;
import com.backbase.dbs.capabilities.extended.text.messaging.util.ContextUtil;
import com.backbase.persistence.text.messaging.listener.client.v1.otp.PersistenceTextMessagingOTPClient;
import com.backbase.persistence.text.messaging.rest.spec.v1.otp.*;
import com.backbase.text.messaging.integration.listener.client.v1.sms.TextMessagingIntegrationSendSMSClient;
import com.backbase.text.messaging.integration.rest.spec.v1.sms.SendSMSPostRequestBody;
import com.backbase.text.messaging.integration.rest.spec.v1.sms.SendSMSPostResponseBody;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostRequestBody;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostResponseBody;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostRequestBody;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostResponseBody;
import org.apache.camel.Consume;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


/**
 * Messaging Controller provider the main resources for Messaging Integrations
 *
 * @author Paulo Cardoso
 * @since 1.0.0
 */
@Service
public class TextMessagingServiceImpl implements TextMessagingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextMessagingServiceImpl.class);

    private PersistenceTextMessagingOTPClient persistenceTextMessagingOTPClient;
    private TextMessagingIntegrationSendSMSClient integrationOtpOTPRequestClient;
    private OneTimePasswordStrategyService oneTimePasswordStrategyService;

    public TextMessagingServiceImpl(PersistenceTextMessagingOTPClient persistenceTextMessagingOTPClient, TextMessagingIntegrationSendSMSClient integrationOtpOTPRequestClient, OneTimePasswordStrategyService oneTimePasswordStrategyService) {
        this.persistenceTextMessagingOTPClient = persistenceTextMessagingOTPClient;
        this.integrationOtpOTPRequestClient = integrationOtpOTPRequestClient;
        this.oneTimePasswordStrategyService = oneTimePasswordStrategyService;
    }

    /**
     * @param internalRequest
     * @return OneTimePasswordPostResponseBody
     * @consume direct:request.messaging.otp
     */
    @Override
    @Consume(uri = TextMessagingConstants.DIRECT_BUSINESS_REQUEST_OTP)
    public OneTimePasswordPostResponseBody requestOneTimePassword(InternalRequest<OneTimePasswordPostRequestBody> internalRequest) {
        LOGGER.info("Requesting OTP for Internal Request: {}", internalRequest);
        int otp = oneTimePasswordStrategyService.generateOpt();
        String transactionID = persistNewRequest(internalRequest, otp);

        RequestProxyWrapper<SendSMSPostResponseBody> otpRequestPostResponseBody = callIntegrationServiceToSendSms(internalRequest, String.valueOf(otp));
        Boolean smsSent = otpRequestPostResponseBody.getRequest().getData().getSmsSent();
        LOGGER.info("Successfully requested new OTP {} with Transaction ID: {}", otp, transactionID);
        return new OneTimePasswordPostResponseBody().withTransactionID(transactionID).withOtp(otp)
                .withSmsSent(smsSent);
    }

    @Override
    @Consume(uri = TextMessagingConstants.DIRECT_BUSINESS_VERIFY_OTP)
    public OneTimePasswordVerifyPostResponseBody verifyOneTimePassword(InternalRequest<OneTimePasswordVerifyPostRequestBody> internalRequest) {
        LOGGER.info("Verifying OTP : {}", internalRequest);

        final OneTimePasswordVerifyPostRequestBody otpRequest = internalRequest.getData();

        final RequestProxyWrapper<TransactionGetResponseBody> response = persistenceTextMessagingOTPClient
                .getTransaction(new InternalRequest<>(null, internalRequest.getInternalRequestContext()), otpRequest.getTransactionID());

        final TransactionGetResponseBody otpDataResponse = response.getRequest().getData();

        if (otpDataResponse.getVerified()) {
            LOGGER.info("OTP for this transaction was already verified");
            throw new InternalServerErrorException().withMessage("OTP is not valid");
        }

        if (!otpDataResponse.getOtp().equals(otpRequest.getOtp())) {
            LOGGER.info("OTP is not matching");
            throw new InternalServerErrorException().withMessage("OTP does not Match");
        }

        VerifyOTPPostRequestBody verifyRequest = new VerifyOTPPostRequestBody();
        verifyRequest.setTransactionID(otpRequest.getTransactionID());
        verifyRequest.setVerified(true);

        final RequestProxyWrapper<VerifyOTPPostResponseBody> UpdateResponse = persistenceTextMessagingOTPClient.postVerifyOTP(new InternalRequest<>(verifyRequest, internalRequest.getInternalRequestContext()));


        return new OneTimePasswordVerifyPostResponseBody()
                .withTransactionID(UpdateResponse.getRequest().getData().getTransactionID())
                .withVerified(true);
    }

    private RequestProxyWrapper<SendSMSPostResponseBody> callIntegrationServiceToSendSms(
            InternalRequest<OneTimePasswordPostRequestBody> internalRequest, String message) {

        SendSMSPostRequestBody requestPostRequestBody = new SendSMSPostRequestBody()
                .withPhoneNumber(internalRequest.getData().getPhoneNumber())
                .withMessage(message);

        return integrationOtpOTPRequestClient.postSendSMS(ContextUtil.copyInternalRequest(internalRequest, requestPostRequestBody));
    }

    private String persistNewRequest(InternalRequest<OneTimePasswordPostRequestBody> internalRequest, int otp) {
        OTPPostRequestBody transactionPostRequestBody
                = new ModelMapper().map(internalRequest.getData(), OTPPostRequestBody.class).withOtp(otp);

        RequestProxyWrapper<OTPPostResponseBody> transactionPostResponseBody = persistenceTextMessagingOTPClient
                .postOTP(ContextUtil.copyInternalRequest(internalRequest, transactionPostRequestBody));
        return transactionPostResponseBody.getRequest().getData().getTransactionID();
    }


}
