
package com.backbase.persistence.messaging.rest.spec.v1.transactions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/transactions")
@RestController
public interface OTPTransactionsApi {


    @RequestMapping(method = RequestMethod.POST, value = "", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    public OTPTransactionsPostResponseBody postOTPTransactions(
        @RequestBody
        @Valid
        OTPTransactionsPostRequestBody oTPTransactionsPostRequestBody, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    ;

    @RequestMapping(method = RequestMethod.GET, value = "/{transactionId}", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    public TransactionGetResponseBody getTransaction(
        @PathVariable("transactionId")
        String transactionId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    ;

    @RequestMapping(method = RequestMethod.POST, value = "/verify", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    public VerifyTransactionPostResponseBody postVerifyTransaction(
        @RequestBody
        @Valid
        VerifyTransactionPostRequestBody verifyTransactionPostRequestBody, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    ;

}
