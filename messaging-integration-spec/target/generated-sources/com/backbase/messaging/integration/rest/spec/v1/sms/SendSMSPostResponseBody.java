
package com.backbase.messaging.integration.rest.spec.v1.sms;

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
 * SMS Request
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "smsSent"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendSMSPostResponseBody implements Serializable, AdditionalPropertiesAware
{

    @JsonProperty("smsSent")
    private Boolean smsSent;
    private final static long serialVersionUID = -3008043578721541741L;
    /**
     * Additional Properties
     * 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> additions = new HashMap<String, String>();

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

    public SendSMSPostResponseBody withSmsSent(Boolean smsSent) {
        this.smsSent = smsSent;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(smsSent).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SendSMSPostResponseBody) == false) {
            return false;
        }
        SendSMSPostResponseBody rhs = ((SendSMSPostResponseBody) other);
        return new EqualsBuilder().append(smsSent, rhs.smsSent).isEquals();
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
