
package com.backbase.persistence.messaging.listener.client.v1.transactions;

import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsPostRequestBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsPostResponseBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.TransactionGetResponseBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.VerifyTransactionPostRequestBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.VerifyTransactionPostResponseBody;


/**
 * JMS client common interface.
 * 
 */
public interface PersistenceMessagingOTPTransactionsClient {


    public RequestProxyWrapper<OTPTransactionsPostResponseBody> postOTPTransactions(InternalRequest<OTPTransactionsPostRequestBody> oTPTransactionsPostRequestBody);

    public RequestProxyWrapper<TransactionGetResponseBody> getTransaction(InternalRequest<Void> request, String transactionId);

    public RequestProxyWrapper<VerifyTransactionPostResponseBody> postVerifyTransaction(InternalRequest<VerifyTransactionPostRequestBody> verifyTransactionPostRequestBody);

}
