package com.edwise.utils.jsoncrosser.model;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 01/06/16.
 */
public class MergedResult {

    private final String _id;
    private final String currency;
    private final String country;

    public MergedResult(String _id, String currency, String country) {
        this._id = _id;
        this.currency = currency;
        this.country = country;
    }

    public String get_id() {
        return _id;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "{" +
               "_id='" + _id + '\'' +
               ", currency_BIDDER='" + currency + '\'' +
               ", country_SONATA='" + country + '\'' +
               '}';
    }
}
