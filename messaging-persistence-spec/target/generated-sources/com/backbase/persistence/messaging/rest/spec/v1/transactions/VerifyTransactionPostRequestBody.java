
package com.backbase.persistence.messaging.rest.spec.v1.transactions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import com.backbase.buildingblocks.persistence.model.AdditionalPropertiesAware;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Verify Transaction Request
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
public class VerifyTransactionPostRequestBody implements Serializable, AdditionalPropertiesAware
{

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("transactionID")
    @NotNull
    private String transactionID;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("verified")
    @NotNull
    private Boolean verified;
    private final static long serialVersionUID = 8747263920537249964L;
    /**
     * Additional Properties
     * 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> additions = new HashMap<String, String>();

    /**
     * 
     * (Required)
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
     * (Required)
     * 
     * @param transactionID
     *     The transactionID
     */
    @JsonProperty("transactionID")
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public VerifyTransactionPostRequestBody withTransactionID(String transactionID) {
        this.transactionID = transactionID;
        return this;
    }

    /**
     * 
     * (Required)
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
     * (Required)
     * 
     * @param verified
     *     The verified
     */
    @JsonProperty("verified")
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public VerifyTransactionPostRequestBody withVerified(Boolean verified) {
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
        if ((other instanceof VerifyTransactionPostRequestBody) == false) {
            return false;
        }
        VerifyTransactionPostRequestBody rhs = ((VerifyTransactionPostRequestBody) other);
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
