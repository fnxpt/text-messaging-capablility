
package com.backbase.messaging.integration.rest.spec.v1.sms;

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
 * SMS Request Response
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "phoneNumber",
    "message"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendSMSPostRequestBody implements Serializable, AdditionalPropertiesAware
{

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
    @JsonProperty("message")
    @NotNull
    private String message;
    private final static long serialVersionUID = 536766424411366070L;
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

    public SendSMSPostRequestBody withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The message
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * 
     * (Required)
     * 
     * @param message
     *     The message
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public SendSMSPostRequestBody withMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(phoneNumber).append(message).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SendSMSPostRequestBody) == false) {
            return false;
        }
        SendSMSPostRequestBody rhs = ((SendSMSPostRequestBody) other);
        return new EqualsBuilder().append(phoneNumber, rhs.phoneNumber).append(message, rhs.message).isEquals();
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
