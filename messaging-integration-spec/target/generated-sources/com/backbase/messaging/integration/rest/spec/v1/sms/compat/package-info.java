/**
 * Compatibility layer to allow inbound HTTP service-to-service communication using an
 * existing JMS RequestListener implementation to handle API calls.
 * <p>Provides default implementations for the RestController interfaces defined in this
 * specification project.  These implementations delegate to their equivalent JMS
 * RequestListeners (directly via Camel, bypassing JMS), if such implementations exist in
 * the application context.</p>
 * 
 */
package com.backbase.messaging.integration.rest.spec.v1.sms.compat;
