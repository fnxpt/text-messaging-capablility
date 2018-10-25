package com.backbase.dbs.capabilities.extended.text.messaging.routes;

import com.backbase.buildingblocks.backend.communication.extension.SimpleExtensibleRouteBuilder;
import org.springframework.stereotype.Component;


/**
 * Send OTP Route responsible for provide channels for sending OTP
 *
 * @author Paulo Cardoso
 * @see com.backbase.buildingblocks.backend.communication.extension.SimpleExtensibleRouteBuilder
 * @since 1.0.0
 */
@Component
public class SendOTPRoute extends SimpleExtensibleRouteBuilder {

    public static final String ROUTE_ID = "SendOTPRoute";

    public SendOTPRoute() {
        super(ROUTE_ID, TextMessagingConstants.DIRECT_REQUEST_OTP, TextMessagingConstants.DIRECT_BUSINESS_REQUEST_OTP);
    }

}
