
package com.backbase.messaging.integration.rest.spec.v1.sms.compat;

import com.backbase.messaging.integration.listener.spec.v1.sms.SendSMSListener;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSApi;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration class providing a default implementation of the {@link SendSMSApi }
 * RestController interface ({@link DelegatingSendSMSApiImpl }) and a {@link RoutesBuilder }
 * which configures some direct Camel routes from {@link DelegatingSendSMSApiImpl } to an implementation
 * of the {@link SendSMSListener } JMS RequestListener interface,
 * if one exists in the application context.
 * <p>Conditional on:</p>
 * <ul>
 * <li><strong>There being no other implementation of the {@link SendSMSApi }
 *     RestController interface in the application context</strong>.
 *     (Therefore, if the service provides its own implementation to use, the compatibility
 *     one will be ignored.)
 * <li><strong>An implementation of the {@link SendSMSListener } JMS
 *     RequestListener interface being present in the application context</strong>, so that
 * {@link DelegatingSendSMSApiImpl }
 *     has something to delegate requests to.  (This also means that projects depending on
 *     this project for the specification's client classes will not have their own
 *     application contexts polluted by the RestController implementation.)
 * <li><strong>The configuration property {@code backbase.communication.inbound} being set to
 *     {@code HTTP}</strong> (default value is {@code JMS} so that a conscious action needs
 *     to be made to enable the compatibility RestController implementation on a
 *     per-deployment basis.
 * </ul>
 * 
 */
@Configuration("com.backbase.messaging.integration.rest.spec.v1.sms.compat.DelegatingSendSMSApiImplConfig")
@ConditionalOnBean(SendSMSListener.class)
@ConditionalOnMissingBean(SendSMSApi.class)
@ConditionalOnProperty(name = "backbase.communication.inbound", havingValue = "HTTP")
public class DelegatingSendSMSApiImplConfig {


    @Bean("com.backbase.messaging.integration.rest.spec.v1.sms.compat.DelegatingSendSMSApiImplConfig.SendSMSApi")
    public SendSMSApi delegatingSendSMSApiImpl() {
        return new DelegatingSendSMSApiImpl();
    }

    @Bean("com.backbase.messaging.integration.rest.spec.v1.sms.compat.DelegatingSendSMSApiImplConfig.RouteBuilder")
    public RoutesBuilder delegatingSendSMSApiImplRoutes(
        @Autowired
        final SendSMSListener listener) {
        return new RouteBuilder() {


            /**
             * Configures routes from {@link DelegatingSendSMSApiImpl } to a {@link SendSMSListener }.
             * 
             */
            @Override
            public void configure() {
                from(DelegatingSendSMSApiImpl.RestToJmsListenerRouteProxy.POST_SEND_S_M_S_FROM_URI).bean(listener, "postSendSMS").routeId(DelegatingSendSMSApiImpl.RestToJmsListenerRouteProxy.POST_SEND_S_M_S_ROUTE_ID);
            }

        }
        ;
    }

}
