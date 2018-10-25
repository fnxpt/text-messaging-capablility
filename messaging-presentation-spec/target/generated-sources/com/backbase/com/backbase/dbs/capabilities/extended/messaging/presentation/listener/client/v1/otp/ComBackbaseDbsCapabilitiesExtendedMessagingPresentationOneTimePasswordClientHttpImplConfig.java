
package com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.client.v1.otp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;


/**
 * Configuration class providing an HTTP implementation of the {@link ComBackbaseDbsCapabilitiesExtendedMessagingPresentationOneTimePasswordClient }.
 * 
 * <p>Conditional on:</p>
 * <ul>
 * <li><strong>There being no other implementation of the {@link ComBackbaseDbsCapabilitiesExtendedMessagingPresentationOneTimePasswordClient }
 *     interface in the application context</strong>.
 *     (Therefore, if the service configures its own {@link ComBackbaseDbsCapabilitiesExtendedMessagingPresentationOneTimePasswordClientHttpImpl } to use, the instance configured here
 *     will be ignored.)
 * <li><strong>The configuration property {@code backbase.communication.outbound} being set to
 *     {@code HTTP}</strong> (default value is {@code JMS}) so that a conscious action needs
 *     to be made to enable the HTTP Client implementation on a
 *     per-deployment basis.
 * </ul>
 * 
 */
@Lazy
@Configuration("com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.client.v1.otp.ComBackbaseDbsCapabilitiesExtendedMessagingPresentationOneTimePasswordClientHttpImplConfig")
@ConfigurationProperties("backbase.communication.services.com.backbase.dbs.capabilities.extended.messaging.presentation")
@ConditionalOnProperty(name = "backbase.communication.outbound", havingValue = "HTTP")
public class ComBackbaseDbsCapabilitiesExtendedMessagingPresentationOneTimePasswordClientHttpImplConfig {

    /**
     * The host part of the URLs given to the RestTemplate.
     * 
     * <p>This is used by Ribbon to look up the service instances in the registry.</p>
     * 
     */
    private String serviceId;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @ConditionalOnMissingBean
    @Bean("com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.client.v1.otp.ComBackbaseDbsCapabilitiesExtendedMessagingPresentationOneTimePasswordClient")
    public ComBackbaseDbsCapabilitiesExtendedMessagingPresentationOneTimePasswordClient createClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        return new ComBackbaseDbsCapabilitiesExtendedMessagingPresentationOneTimePasswordClientHttpImpl(restTemplate, serviceId, objectMapper);
    }

}
