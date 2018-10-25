
package com.backbase.persistence.messaging.rest.spec.v1.transactions.compat;

import java.util.HashMap;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.persistence.messaging.listener.spec.v1.transactions.OTPTransactionsListener;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsApi;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsPostRequestBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.OTPTransactionsPostResponseBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.TransactionGetResponseBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.VerifyTransactionPostRequestBody;
import com.backbase.persistence.messaging.rest.spec.v1.transactions.VerifyTransactionPostResponseBody;
import org.apache.camel.Produce;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Default implementation of {@link OTPTransactionsApi } which delegates to an
 * implementation of {@link OTPTransactionsListener }, if one is present in the
 * application context.
 * <p>Conditionally added to the application context by the {@link DelegatingOTPTransactionsApiImplConfig }
 * configuration class (see the documentation for that class for details of the conditions).</p>
 * 
 */
public class DelegatingOTPTransactionsApiImpl implements OTPTransactionsApi
{

    private final static Logger LOG = LoggerFactory.getLogger(DelegatingOTPTransactionsApiImpl.class);
    @Produce(uri = DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy.POST_O_T_P_TRANSACTIONS_FROM_URI)
    private DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy postOTPTransactionsRoute;
    @Produce(uri = DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy.GET_TRANSACTION_FROM_URI)
    private DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy getTransactionRoute;
    @Produce(uri = DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy.POST_VERIFY_TRANSACTION_FROM_URI)
    private DelegatingOTPTransactionsApiImpl.RestToJmsListenerRouteProxy postVerifyTransactionRoute;

    @RequestMapping(method = RequestMethod.POST, value = "", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    @Override
    public OTPTransactionsPostResponseBody postOTPTransactions(
        @RequestBody
        @Valid
        OTPTransactionsPostRequestBody oTPTransactionsPostRequestBody, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    {
        LOG.trace("Forwarding HTTP request for postOTPTransactions to JMS RequestListener.");
        InternalRequest<OTPTransactionsPostRequestBody> request = new InternalRequest<OTPTransactionsPostRequestBody>(oTPTransactionsPostRequestBody, httpServletRequest, UUID.randomUUID().toString());
        RequestProxyWrapper<OTPTransactionsPostRequestBody> requestProxyWrapper = new RequestProxyWrapper<OTPTransactionsPostRequestBody>();
        HashMap<String, Object> pathParams = new HashMap<String, Object>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        requestProxyWrapper.setPathParams(pathParams);
        requestProxyWrapper.setQueryParams(queryParams);
        requestProxyWrapper.setRequest(request);
        requestProxyWrapper.setHttpMethod("post");
        requestProxyWrapper.setUrl("com.backbase.persistence.messaging.listener.v1.transactions");
        RequestProxyWrapper<OTPTransactionsPostResponseBody> responseWrapper = postOTPTransactionsRoute.postOTPTransactions((requestProxyWrapper));
        return responseWrapper.getRequest().getData();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{transactionId}", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    @Override
    public TransactionGetResponseBody getTransaction(
        @PathVariable("transactionId")
        String transactionId, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    {
        LOG.trace("Forwarding HTTP request for getTransaction to JMS RequestListener.");
        InternalRequest<Void> request = new InternalRequest<Void>(null, httpServletRequest, UUID.randomUUID().toString());
        RequestProxyWrapper<Void> requestProxyWrapper = new RequestProxyWrapper<Void>();
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
        RequestProxyWrapper<TransactionGetResponseBody> responseWrapper = getTransactionRoute.getTransaction((requestProxyWrapper));
        return responseWrapper.getRequest().getData();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verify", produces = {
        "application/json"
    })
    @ResponseStatus(HttpStatus.OK)
    @Override
    public VerifyTransactionPostResponseBody postVerifyTransaction(
        @RequestBody
        @Valid
        VerifyTransactionPostRequestBody verifyTransactionPostRequestBody, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
        throws BadRequestException, InternalServerErrorException
    {
        LOG.trace("Forwarding HTTP request for postVerifyTransaction to JMS RequestListener.");
        InternalRequest<VerifyTransactionPostRequestBody> request = new InternalRequest<VerifyTransactionPostRequestBody>(verifyTransactionPostRequestBody, httpServletRequest, UUID.randomUUID().toString());
        RequestProxyWrapper<VerifyTransactionPostRequestBody> requestProxyWrapper = new RequestProxyWrapper<VerifyTransactionPostRequestBody>();
        HashMap<String, Object> pathParams = new HashMap<String, Object>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        requestProxyWrapper.setPathParams(pathParams);
        requestProxyWrapper.setQueryParams(queryParams);
        requestProxyWrapper.setRequest(request);
        requestProxyWrapper.setHttpMethod("post");
        requestProxyWrapper.setUrl("com.backbase.persistence.messaging.listener.v1.transactions.verify");
        RequestProxyWrapper<VerifyTransactionPostResponseBody> responseWrapper = postVerifyTransactionRoute.postVerifyTransaction((requestProxyWrapper));
        return responseWrapper.getRequest().getData();
    }


    /**
     * Interface for the Camel route proxies used in {@link DelegatingOTPTransactionsApiImpl }.
     * <p>One {@code *_FROM_URI}, one {@code *_ROUTE_ID}, and one method per {@link OTPTransactionsApi } method.</p>
     * 
     */
    public interface RestToJmsListenerRouteProxy {

        public final static String POST_O_T_P_TRANSACTIONS_FROM_URI = "direct:rest2jms.postOTPTransactions.DelegatingOTPTransactionsApiImpl.compat.transactions.v1.spec.rest.messaging.persistence.backbase.com";
        public final static String POST_O_T_P_TRANSACTIONS_ROUTE_ID = "rest2jms.postOTPTransactions.DelegatingOTPTransactionsApiImpl.compat.transactions.v1.spec.rest.messaging.persistence.backbase.com";
        public final static String GET_TRANSACTION_FROM_URI = "direct:rest2jms.getTransaction.DelegatingOTPTransactionsApiImpl.compat.transactions.v1.spec.rest.messaging.persistence.backbase.com";
        public final static String GET_TRANSACTION_ROUTE_ID = "rest2jms.getTransaction.DelegatingOTPTransactionsApiImpl.compat.transactions.v1.spec.rest.messaging.persistence.backbase.com";
        public final static String POST_VERIFY_TRANSACTION_FROM_URI = "direct:rest2jms.postVerifyTransaction.DelegatingOTPTransactionsApiImpl.compat.transactions.v1.spec.rest.messaging.persistence.backbase.com";
        public final static String POST_VERIFY_TRANSACTION_ROUTE_ID = "rest2jms.postVerifyTransaction.DelegatingOTPTransactionsApiImpl.compat.transactions.v1.spec.rest.messaging.persistence.backbase.com";

        public RequestProxyWrapper<OTPTransactionsPostResponseBody> postOTPTransactions(RequestProxyWrapper<OTPTransactionsPostRequestBody> request);

        public RequestProxyWrapper<TransactionGetResponseBody> getTransaction(RequestProxyWrapper<Void> request);

        public RequestProxyWrapper<VerifyTransactionPostResponseBody> postVerifyTransaction(RequestProxyWrapper<VerifyTransactionPostRequestBody> request);

    }

}
