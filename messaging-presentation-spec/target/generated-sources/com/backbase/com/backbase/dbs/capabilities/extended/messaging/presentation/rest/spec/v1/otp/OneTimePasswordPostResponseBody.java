
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
 * One Time Password Response
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "transactionID",
    "otp",
    "smsSent"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class OneTimePasswordPostResponseBody implements Serializable, AdditionalPropertiesAware
{

    @JsonProperty("transactionID")
    private String transactionID;
    @JsonProperty("otp")
    private Integer otp;
    @JsonProperty("smsSent")
    private Boolean smsSent;
    private final static long serialVersionUID = 4526369342732860481L;
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

    public OneTimePasswordPostResponseBody withTransactionID(String transactionID) {
        this.transactionID = transactionID;
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

    public OneTimePasswordPostResponseBody withOtp(Integer otp) {
        this.otp = otp;
        return this;
    }

    /**
     * 
     * @return
     *     The smsSent
     */
    @JsonProperty("smsSent")
    public Boolean getSmsSent() {
        return smsSent;
    }

    /**
     * 
     * @param smsSent
     *     The smsSent
     */
    @JsonProperty("smsSent")
    public void setSmsSent(Boolean smsSent) {
        this.smsSent = smsSent;
    }

    public OneTimePasswordPostResponseBody withSmsSent(Boolean smsSent) {
        this.smsSent = smsSent;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(transactionID).append(otp).append(smsSent).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OneTimePasswordPostResponseBody) == false) {
            return false;
        }
        OneTimePasswordPostResponseBody rhs = ((OneTimePasswordPostResponseBody) other);
        return new EqualsBuilder().append(transactionID, rhs.transactionID).append(otp, rhs.otp).append(smsSent, rhs.smsSent).isEquals();
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
