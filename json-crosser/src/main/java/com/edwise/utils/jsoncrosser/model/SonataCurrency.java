package com.edwise.utils.jsoncrosser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 01/06/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SonataCurrency {
    private String name;
    private String countryCode;
    private IntCurrency currency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public IntCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(IntCurrency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "SonataCurrency{" +
               "name='" + name + '\'' +
               ", countryCode='" + countryCode + '\'' +
               ", currency=" + currency +
               '}';
    }
}
