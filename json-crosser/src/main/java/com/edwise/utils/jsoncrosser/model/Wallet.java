package com.edwise.utils.jsoncrosser.model;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 01/06/16.
 */
public class Wallet {
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Wallet{" +
               "currency='" + currency + '\'' +
               '}';
    }
}
