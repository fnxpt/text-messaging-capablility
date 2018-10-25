
package com.backbase.persistence.messaging.rest.spec.v1.transactions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.backbase.buildingblocks.persistence.model.AdditionalPropertiesAware;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Get Transaction Response
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "transactionId",
    "userId",
    "phoneNumber",
    "otp",
    "verified"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionGetResponseBody implements Serializable, AdditionalPropertiesAware
{

    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("otp")
    private Integer otp;
    @JsonProperty("verified")
    private Boolean verified;
    private final static long serialVersionUID = 4912000860934290869L;
    /**
     * Additional Properties
     * 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> additions = new HashMap<String, String>();

    /**
     * 
     * @return
     *     The transactionId
     */
    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * 
     * @param transactionId
     *     The transactionId
     */
    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionGetResponseBody withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    /**
     * 
     * @return
     *     The userId
     */
    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The userId
     */
    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TransactionGetResponseBody withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 
     * @return
     *     The phoneNumber
     */
    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 
     * @param phoneNumber
     *     The phoneNumber
     */
    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public TransactionGetResponseBody withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * 
     * @return
     *     The otp
     */
    @JsonProperty("otp")
    public Integer getOtp() {
        return otp;
    }

    /**
     * 
     * @param otp
     *     The otp
     */
    @JsonProperty("otp")
    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public TransactionGetResponseBody withOtp(Integer otp) {
        this.otp = otp;
        return this;
    }

    /**
     * 
     * @return
     *     The verified
     */
    @JsonProperty("verified")
    public Boolean getVerified() {
        return verified;
    }

    /**
     * 
     * @param verified
     *     The verified
     */
    @JsonProperty("verified")
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public TransactionGetResponseBody withVerified(Boolean verified) {
        this.verified = verified;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(transactionId).append(userId).append(phoneNumber).append(otp).append(verified).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TransactionGetResponseBody) == false) {
            return false;
        }
        TransactionGetResponseBody rhs = ((TransactionGetResponseBody) other);
        return new EqualsBuilder().append(transactionId, rhs.transactionId).append(userId, rhs.userId).append(phoneNumber, rhs.phoneNumber).append(otp, rhs.otp).append(verified, rhs.verified).isEquals();
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    @JsonProperty("additions")
    public Map<String, String> getAdditions() {
        return this.additions;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    @JsonProperty("additions")
    public void setAdditions(Map<String, String> additions) {
        this.additions = additions;
    }

}
