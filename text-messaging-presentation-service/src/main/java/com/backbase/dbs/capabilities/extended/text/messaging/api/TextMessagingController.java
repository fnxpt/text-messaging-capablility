package com.backbase.dbs.capabilities.extended.text.messaging.api;

import com.backbase.buildingblocks.backend.api.IDUtils;
import com.backbase.buildingblocks.backend.communication.event.util.LoggingUtils;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.dbs.capabilities.extended.text.messaging.routes.TextMessagingConstants;
import com.backbase.dbs.capabilities.extended.text.messaging.routes.SendOTPRouteProxy;
import com.backbase.dbs.capabilities.extended.text.messaging.routes.VerifyOTPRouteProxy;
import com.backbase.dbs.capabilities.extended.text.messaging.util.ContextUtil;
import com.backbase.text.messaging.presentation.rest.spec.v1.otp.*;
import org.apache.camel.Produce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Text Messaging Controller provider the main resources for Messaging Integrations
 *
 * @author Paulo Cardoso
 * @since 1.0.0
 */
@RestController
public class TextMessagingController implements OneTimePasswordApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextMessagingController.class);

    @Autowired
    private IDUtils idUtils;

    @Produce(uri = TextMessagingConstants.DIRECT_REQUEST_OTP)
    private SendOTPRouteProxy sendOTPRouteProxy;

    @Produce(uri = TextMessagingConstants.DIRECT_VERIFY_OTP)
    private VerifyOTPRouteProxy verifyOneTimePassword;


    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param oneTimePasswordPostRequestBody
     * @return OneTimePasswordPostResponseBody
     */
    @Override
    public OneTimePasswordPostResponseBody postOneTimePassword(@RequestBody @Valid OneTimePasswordPostRequestBody oneTimePasswordPostRequestBody,
                                                               HttpServletRequest httpServletRequest,
                                                               HttpServletResponse httpServletResponse) throws InternalServerErrorException {
        try {
            LOGGER.info("Creating a new One Time Password");
            LOGGER.debug("payload: {}", oneTimePasswordPostRequestBody);
            InternalRequest<OneTimePasswordPostRequestBody> internalRequest = ContextUtil.createInternalRequest(oneTimePasswordPostRequestBody,
                    httpServletRequest, idUtils.generateRandomID());
            return sendOTPRouteProxy.requestOneTimePassword(internalRequest);
        } catch (Exception e) {
            LOGGER.error(LoggingUtils.ERROR_CAUGHT_REQUEST, e.getMessage(), e);
            throw new InternalServerErrorException("An unexpected error occurred", e);
        }
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param oneTimePasswordVerifyPostRequestBody
     * @return OneTimePasswordVerifyPostResponseBody
     */
    @Override
    public OneTimePasswordVerifyPostResponseBody postOneTimePasswordVerify(@RequestBody @Valid OneTimePasswordVerifyPostRequestBody oneTimePasswordVerifyPostRequestBody,
                                                                           HttpServletRequest httpServletRequest,
                                                                           HttpServletResponse httpServletResponse) throws InternalServerErrorException {
        try {
            LOGGER.info("Verify a new One Time Password");
            LOGGER.debug("payload: {}", oneTimePasswordVerifyPostRequestBody);
            InternalRequest<OneTimePasswordVerifyPostRequestBody> internalRequest = ContextUtil.createInternalRequest(oneTimePasswordVerifyPostRequestBody,
                    httpServletRequest, idUtils.generateRandomID());
            return verifyOneTimePassword.verifyOneTimePassword(internalRequest);
        } catch (Exception e) {
            LOGGER.error(LoggingUtils.ERROR_CAUGHT_REQUEST, e.getMessage(), e);
            throw new InternalServerErrorException("An unexpected error occurred", e);
        }
    }

}
