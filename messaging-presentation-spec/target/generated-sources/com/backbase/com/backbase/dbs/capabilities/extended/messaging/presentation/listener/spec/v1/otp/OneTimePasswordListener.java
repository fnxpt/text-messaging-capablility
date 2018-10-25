
package com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.spec.v1.otp;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostRequestBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordPostResponseBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostRequestBody;
import com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp.OneTimePasswordVerifyPostResponseBody;
import org.apache.camel.Consume;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Service;

@Service
@RequestListener
public interface OneTimePasswordListener {


    @Consume(uri = "direct:com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.v1.otp.post")
    public RequestProxyWrapper<OneTimePasswordPostResponseBody> postOneTimePassword(RequestProxyWrapper<OneTimePasswordPostRequestBody> oneTimePasswordPostRequestBody, Exchange exchange)
        throws BadRequestException, InternalServerErrorException
    ;

    @Consume(uri = "direct:com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.listener.v1.otp.verify.post")
    public RequestProxyWrapper<OneTimePasswordVerifyPostResponseBody> postOneTimePasswordVerify(RequestProxyWrapper<OneTimePasswordVerifyPostRequestBody> oneTimePasswordVerifyPostRequestBody, Exchange exchange)
        throws BadRequestException, InternalServerErrorException
    ;

}
