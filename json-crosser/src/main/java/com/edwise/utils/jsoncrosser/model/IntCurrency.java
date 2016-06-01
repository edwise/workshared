package com.edwise.utils.jsoncrosser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 01/06/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntCurrency {
    private String baseCurrency;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    @Override
    public String toString() {
        return "IntCurrency{" +
               "baseCurrency='" + baseCurrency + '\'' +
               '}';
    }
}
