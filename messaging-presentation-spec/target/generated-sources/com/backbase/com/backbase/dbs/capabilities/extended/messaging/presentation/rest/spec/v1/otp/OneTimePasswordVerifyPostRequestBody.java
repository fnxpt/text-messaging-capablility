
package com.backbase.com.backbase.dbs.capabilities.extended.messaging.presentation.rest.spec.v1.otp;

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
 * One Time Password Response
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "transactionID",
    "otp"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class OneTimePasswordVerifyPostRequestBody implements Serializable, AdditionalPropertiesAware
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
    @JsonProperty("otp")
    @NotNull
    private Integer otp;
    private final static long serialVersionUID = 8342714356865582083L;
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

    public OneTimePasswordVerifyPostRequestBody withTransactionID(String transactionID) {
        this.transactionID = transactionID;
        return this;
    }

    /**
     * 
     * (Required)
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
     * (Required)
     * 
     * @param otp
     *     The otp
     */
    @JsonProperty("otp")
    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public OneTimePasswordVerifyPostRequestBody withOtp(Integer otp) {
        this.otp = otp;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(transactionID).append(otp).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OneTimePasswordVerifyPostRequestBody) == false) {
            return false;
        }
        OneTimePasswordVerifyPostRequestBody rhs = ((OneTimePasswordVerifyPostRequestBody) other);
        return new EqualsBuilder().append(transactionID, rhs.transactionID).append(otp, rhs.otp).isEquals();
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
