
package com.backbase.persistence.messaging.rest.spec.v1.transactions.compat;

import com.backbase.persistence.messaging.listener.spec.v1.transactions.OTPTransactionsListener;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsApi;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration class providing a default implementation of the {@link OTPTransactionsApi }
 * RestController interface ({@link DelegatingOTPTransactionsApiImpl }) and a {@link RoutesBuilder }
 * which configures some direct Camel routes from {@link DelegatingOTPTransactionsApiImpl } to an implementation
 * of the {@link OTPTransactionsListener } JMS RequestListener interface,
 * if one exists in the application context.
 * <p>Conditional on:</p>
 * <ul>
 * <li><strong>There being no other implementation of the {@link OTPTransactionsApi }
 *     RestController interface in the application context</strong>.
 *     (Therefore, if the service provides its own implementation to use, the compatibility
 *     one will be ignored.)
 * <li><strong>An implementation of the {@link OTPTransactionsListener } JMS
 *     RequestListener interface being present in the application context</strong>, so that
 * {@link DelegatingOTPTransactionsApiImpl }
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
@Configuration("com.backbase.persistence.messaging.rest.spec.v1.transactions.compat.DelegatingOTPTransactionsApiImplConfig")
@ConditionalOnBean(OTPTransactionsListener.class)
@ConditionalOnMissingBean(OTPTransactionsApi.class)
@ConditionalOnProperty(name = "backbase.communication.inbound", havingValue = "HTTP")
public class DelegatingOTPTransactionsApiImplConfig {


    @Bean("com.backbase.persistence.messaging.rest.spec.v1.transactions.compat.DelegatingOTPTransactionsApiImplConfig.OTPTransactionsApi")
    public OTPTransactionsApi delegatingOTPTransactionsApiImpl() {
        return new DelegatingOTPTransactionsApiImpl();
    }

    @Bean("com.backbase.persistence.messaging.rest.spec.v1.transactions.compat.DelegatingOTPTransactionsApiImplConfig.RouteBuilder")
    public RoutesBuilder delegatingOTPTransactionsApiImplRoutes(
        @Autowired
        final OTPTransactionsListener listener) {
        return new RouteBuilder() {


            /**
             * Configures routes from {@link DelegatingOTPTransactionsApiImpl } to a {@link OTPTransactionsListener }.
             * 
             */
            @Override
            public void configure() {
                from(DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy.POST_O_T_P_TRANSACTIONS_FROM_URI).bean(listener, "postOTPTransactions").routeId(DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy.POST_O_T_P_TRANSACTIONS_ROUTE_ID);
                from(DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy.GET_TRANSACTION_FROM_URI).bean(listener, "getTransaction").routeId(DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy.GET_TRANSACTION_ROUTE_ID);
                from(DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy.POST_VERIFY_TRANSACTION_FROM_URI).bean(listener, "postVerifyTransaction").routeId(DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy.POST_VERIFY_TRANSACTION_ROUTE_ID);
            }

        }
        ;
    }

}
