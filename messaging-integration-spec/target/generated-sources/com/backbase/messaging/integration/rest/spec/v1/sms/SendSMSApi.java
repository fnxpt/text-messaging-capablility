
package com.backbase.messaging.integration.rest.spec.v1.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/sms")
@RestController
public interface SendSMSApi {


    @RequestMapping(method = RequestMethod.POST, value = "", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    public SendSMSPostResponseBody postSendSMS(
        @RequestBody
        @Valid
        SendSMSPostRequestBody sendSMSPostRequestBody, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    ;

}
