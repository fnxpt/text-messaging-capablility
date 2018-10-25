
package com.backbase.messaging.integration.listener.spec.v1.sms;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSPostRequestBody;
import com.backbase.messaging.integration.rest.spec.v1.sms.SendSMSPostResponseBody;
import org.apache.camel.Consume;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Service;

@Service
@RequestListener
public interface SendSMSListener {


    @Consume(uri = "direct:com.backbase.messaging.integration.listener.v1.sms.post")
    public RequestProxyWrapper<SendSMSPostResponseBody> postSendSMS(RequestProxyWrapper<SendSMSPostRequestBody> sendSMSPostRequestBody, Exchange exchange)
        throws BadRequestException, InternalServerErrorException
    ;

}
