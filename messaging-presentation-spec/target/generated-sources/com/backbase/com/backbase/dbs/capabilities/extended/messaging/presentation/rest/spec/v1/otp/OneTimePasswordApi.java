
package com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp;

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

@RequestMapping("/v1/otp")
@RestController
public interface OneTimePasswordApi {


    @RequestMapping(method = RequestMethod.POST, value = "", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    public OneTimePasswordPostResponseBody postOneTimePassword(
        @RequestBody
        @Valid
        OneTimePasswordPostRequestBody oneTimePasswordPostRequestBody, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    ;

    @RequestMapping(method = RequestMethod.POST, value = "/verify", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    public OneTimePasswordVerifyPostResponseBody postOneTimePasswordVerify(
        @RequestBody
        @Valid
        OneTimePasswordVerifyPostRequestBody oneTimePasswordVerifyPostRequestBody, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    ;

}
