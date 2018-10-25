
package com.backbase.persistence.messaging.listener.spec.v1.transactions;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsPostRequestBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsPostResponseBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.TransactionGetResponseBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.VerifyTransactionPostRequestBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.VerifyTransactionPostResponseBody;
import org.apache.camel.Consume;
import org.apache.camel.Exchange;
import org.apache.camel.builder.script.Groovy;
import org.springframework.stereotype.Service;

@Service
@RequestListener
public interface OTPTransactionsListener {


    @Consume(uri = "direct:com.backbase.persistence.messaging.listener.v1.transactions.post")
    public RequestProxyWrapper<OTPTransactionsPostResponseBody> postOTPTransactions(RequestProxyWrapper<OTPTransactionsPostRequestBody> oTPTransactionsPostRequestBody, Exchange exchange)
        throws BadRequestException, InternalServerErrorException
    ;

    @Consume(uri = "direct:com.backbase.persistence.messaging.listener.v1.transactions.transactionId.get")
    public RequestProxyWrapper<TransactionGetResponseBody> getTransaction(RequestProxyWrapper<Void> request, Exchange exchange,
        @Groovy("assert request.body.pathParams[\"transactionId\"]||request.body.pathParams[\"transactionId\"]==0||request.body.pathParams[\"transactionId\"]==false : \"transactionId is required\"; request.body.pathParams[\"transactionId\"]")
        String transactionId)
        throws BadRequestException, InternalServerErrorException
    ;

    @Consume(uri = "direct:com.backbase.persistence.messaging.listener.v1.transactions.verify.post")
    public RequestProxyWrapper<VerifyTransactionPostResponseBody> postVerifyTransaction(RequestProxyWrapper<VerifyTransactionPostRequestBody> verifyTransactionPostRequestBody, Exchange exchange)
        throws BadRequestException, InternalServerErrorException
    ;

}
