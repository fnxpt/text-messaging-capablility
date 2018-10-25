
package com.backbase.messaging.integration.listener.client.v1.sms;

import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSPostRequestBody;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSPostResponseBody;


/**
 * JMS client common interface.
 * 
 */
public interface MessagingIntegrationSendSMSClient {


    public RequestProxyWrapper<SendSMSPostResponseBody> postSendSMS(InternalRequest<SendSMSPostRequestBody> sendSMSPostRequestBody);

}
