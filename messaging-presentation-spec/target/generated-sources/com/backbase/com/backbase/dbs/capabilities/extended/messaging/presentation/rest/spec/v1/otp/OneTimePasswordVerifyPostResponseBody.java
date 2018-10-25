
package com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp;

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
 * One Time Password VerificationResponse
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "transactionID",
    "verified"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class OneTimePasswordVerifyPostResponseBody implements Serializable, AdditionalPropertiesAware
{

    @JsonProperty("transactionID")
    private String transactionID;
    @JsonProperty("verified")
    private Boolean verified;
    private final static long serialVersionUID = -7162156087463895749L;
    /**
     * Additional Properties
     * 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> additions = new HashMap<String, String>();

    /**
     * 
     * @return
     *     The transactionID
     */
    @JsonProperty("transactionID")
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * 
     * @param transactionID
     *     The transactionID
     */
    @JsonProperty("transactionID")
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public OneTimePasswordVerifyPostResponseBody withTransactionID(String transactionID) {
        this.transactionID = transactionID;
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

    public OneTimePasswordVerifyPostResponseBody withVerified(Boolean verified) {
        this.verified = verified;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(transactionID).append(verified).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OneTimePasswordVerifyPostResponseBody) == false) {
            return false;
        }
        OneTimePasswordVerifyPostResponseBody rhs = ((OneTimePasswordVerifyPostResponseBody) other);
        return new EqualsBuilder().append(transactionID, rhs.transactionID).append(verified, rhs.verified).isEquals();
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
