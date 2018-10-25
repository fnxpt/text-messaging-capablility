
package com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.compat;

import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.spec.v1.otp.OneTimePasswordListener;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordApi;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration class providing a default implementation of the {@link OneTimePasswordApi }
 * RestController interface ({@link DelegatingOneTimePasswordApiImpl }) and a {@link RoutesBuilder }
 * which configures some direct Camel routes from {@link DelegatingOneTimePasswordApiImpl } to an implementation
 * of the {@link OneTimePasswordListener } JMS RequestListener interface,
 * if one exists in the application context.
 * <p>Conditional on:</p>
 * <ul>
 * <li><strong>There being no other implementation of the {@link OneTimePasswordApi }
 *     RestController interface in the application context</strong>.
 *     (Therefore, if the service provides its own implementation to use, the compatibility
 *     one will be ignored.)
 * <li><strong>An implementation of the {@link OneTimePasswordListener } JMS
 *     RequestListener interface being present in the application context</strong>, so that
 * {@link DelegatingOneTimePasswordApiImpl }
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
@Configuration("com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.compat.DelegatingOneTimePasswordApiImplConfig")
@ConditionalOnBean(OneTimePasswordListener.class)
@ConditionalOnMissingBean(OneTimePasswordApi.class)
@ConditionalOnProperty(name = "backbase.communication.inbound", havingValue = "HTTP")
public class DelegatingOneTimePasswordApiImplConfig {


    @Bean("com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.compat.DelegatingOneTimePasswordApiImplConfig.OneTimePasswordApi")
    public OneTimePasswordApi delegatingOneTimePasswordApiImpl() {
        return new DelegatingOneTimePasswordApiImpl();
    }

    @Bean("com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.compat.DelegatingOneTimePasswordApiImplConfig.RouteBuilder")
    public RoutesBuilder delegatingOneTimePasswordApiImplRoutes(
        @Autowired
        final OneTimePasswordListener listener) {
        return new RouteBuilder() {


            /**
             * Configures routes from {@link DelegatingOneTimePasswordApiImpl } to a {@link OneTimePasswordListener }.
             * 
             */
            @Override
            public void configure() {
                from(DelegatingOneTimePasswordApiImpl.RestToJmsListenerRouteProxy.POST_ONE_TIME_PASSWORD_FROM_URI).bean(listener, "postOneTimePassword").routeId(DelegatingOneTimePasswordApiImpl.RestToJmsListenerRouteProxy.POST_ONE_TIME_PASSWORD_ROUTE_ID);
                from(DelegatingOneTimePasswordApiImpl.RestToJmsListenerRouteProxy.POST_ONE_TIME_PASSWORD_VERIFY_FROM_URI).bean(listener, "postOneTimePasswordVerify").routeId(DelegatingOneTimePasswordApiImpl.RestToJmsListenerRouteProxy.POST_ONE_TIME_PASSWORD_VERIFY_ROUTE_ID);
            }

        }
        ;
    }

}
