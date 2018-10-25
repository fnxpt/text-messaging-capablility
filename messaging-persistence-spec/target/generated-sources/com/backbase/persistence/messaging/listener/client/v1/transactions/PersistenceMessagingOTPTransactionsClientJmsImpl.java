
package com.backbase.persistence.messaging.listener.client.v1.transactions;

import java.util.HashMap;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxy;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsPostRequestBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsPostResponseBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.TransactionGetResponseBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.VerifyTransactionPostRequestBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.VerifyTransactionPostResponseBody;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.camel.Produce;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


/**
 * Camel JMS client.
 * 
 */
@ConditionalOnProperty(name = "backbase.communication.outbound", havingValue = "JMS", matchIfMissing = true)
@Service
public class PersistenceMessagingOTPTransactionsClientJmsImpl implements PersistenceMessagingOTPTransactionsClient
{

    @Produce(uri = RequestProxy.DIRECT_PRODUCE_NAME)
    private RequestProxy requestProxy;

    @HystrixCommand(ignoreExceptions = {
        BadRequestException.class,
        InternalServerErrorException.class
    })
    public RequestProxyWrapper<OTPTransactionsPostResponseBody> postOTPTransactions(InternalRequest<OTPTransactionsPostRequestBody> oTPTransactionsPostRequestBody)
        throws BadRequestException, InternalServerErrorException
    {
        RequestProxyWrapper<OTPTransactionsPostRequestBody> requestProxyWrapper = new RequestProxyWrapper<OTPTransactionsPostRequestBody>();
        HashMap<String, Object> pathParams = new HashMap<String, Object>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        requestProxyWrapper.setPathParams(pathParams);
        requestProxyWrapper.setQueryParams(queryParams);
        requestProxyWrapper.setRequest(oTPTransactionsPostRequestBody);
        requestProxyWrapper.setHttpMethod("post");
        requestProxyWrapper.setUrl("com.backbase.persistence.messaging.listener.v1.transactions");
        return ((RequestProxyWrapper<OTPTransactionsPostResponseBody> ) requestProxy.sendRequest(requestProxyWrapper));
    }

    @HystrixCommand(ignoreExceptions = {
        BadRequestException.class,
        InternalServerErrorException.class
    })
    public RequestProxyWrapper<TransactionGetResponseBody> getTransaction(InternalRequest<Void> request, String transactionId)
        throws BadRequestException, InternalServerErrorException
    {
        RequestProxyWrapper requestProxyWrapper = new RequestProxyWrapper();
        HashMap<String, Object> pathParams = new HashMap<String, Object>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        if (transactionId!= null) {
            pathParams.put("transactionId", transactionId);
        } else {
            throw new IllegalArgumentException("Parameter 'transactionId' must not be null");
        }
        requestProxyWrapper.setPathParams(pathParams);
        requestProxyWrapper.setQueryParams(queryParams);
        requestProxyWrapper.setRequest(request);
        requestProxyWrapper.setHttpMethod("get");
        requestProxyWrapper.setUrl("com.backbase.persistence.messaging.listener.v1.transactions.transactionId");
        return ((RequestProxyWrapper<TransactionGetResponseBody> ) requestProxy.sendRequest(requestProxyWrapper));
    }

    @HystrixCommand(ignoreExceptions = {
        BadRequestException.class,
        InternalServerErrorException.class
    })
    public RequestProxyWrapper<VerifyTransactionPostResponseBody> postVerifyTransaction(InternalRequest<VerifyTransactionPostRequestBody> verifyTransactionPostRequestBody)
        throws BadRequestException, InternalServerErrorException
    {
        RequestProxyWrapper<VerifyTransactionPostRequestBody> requestProxyWrapper = new RequestProxyWrapper<VerifyTransactionPostRequestBody>();
        HashMap<String, Object> pathParams = new HashMap<String, Object>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        requestProxyWrapper.setPathParams(pathParams);
        requestProxyWrapper.setQueryParams(queryParams);
        requestProxyWrapper.setRequest(verifyTransactionPostRequestBody);
        requestProxyWrapper.setHttpMethod("post");
        requestProxyWrapper.setUrl("com.backbase.persistence.messaging.listener.v1.transactions.verify");
        return ((RequestProxyWrapper<VerifyTransactionPostResponseBody> ) requestProxy.sendRequest(requestProxyWrapper));
    }

}
