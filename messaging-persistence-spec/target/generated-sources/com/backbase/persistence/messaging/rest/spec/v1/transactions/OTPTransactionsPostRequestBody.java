
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
 * Post Transaction Request
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "userID",
    "phoneNumber",
    "otp"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class OTPTransactionsPostRequestBody implements Serializable, AdditionalPropertiesAware
{

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("userID")
    @NotNull
    private String userID;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("phoneNumber")
    @NotNull
    private String phoneNumber;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("otp")
    @NotNull
    private Integer otp;
    private final static long serialVersionUID = -4736431742134476802L;
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
     *     The userID
     */
    @JsonProperty("userID")
    public String getUserID() {
        return userID;
    }

    /**
     * 
     * (Required)
     * 
     * @param userID
     *     The userID
     */
    @JsonProperty("userID")
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public OTPTransactionsPostRequestBody withUserID(String userID) {
        this.userID = userID;
        return this;
    }

    /**
     * 
     * (Required)
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
     * (Required)
     * 
     * @param phoneNumber
     *     The phoneNumber
     */
    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public OTPTransactionsPostRequestBody withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public OTPTransactionsPostRequestBody withOtp(Integer otp) {
        this.otp = otp;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(userID).append(phoneNumber).append(otp).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OTPTransactionsPostRequestBody) == false) {
            return false;
        }
        OTPTransactionsPostRequestBody rhs = ((OTPTransactionsPostRequestBody) other);
        return new EqualsBuilder().append(userID, rhs.userID).append(phoneNumber, rhs.phoneNumber).append(otp, rhs.otp).isEquals();
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
