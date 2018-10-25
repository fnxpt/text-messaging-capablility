
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
 * Post Transaction Response
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "transactionID"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class OTPTransactionsPostResponseBody implements Serializable, AdditionalPropertiesAware
{

    @JsonProperty("transactionID")
    private String transactionID;
    private final static long serialVersionUID = -1250599096401472728L;
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

    public OTPTransactionsPostResponseBody withTransactionID(String transactionID) {
        this.transactionID = transactionID;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(transactionID).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OTPTransactionsPostResponseBody) == false) {
            return false;
        }
        OTPTransactionsPostResponseBody rhs = ((OTPTransactionsPostResponseBody) other);
        return new EqualsBuilder().append(transactionID, rhs.transactionID).isEquals();
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
