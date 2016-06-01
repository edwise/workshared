package com.edwise.utils.jsoncrosser.model;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 01/06/16.
 */
public class SonataUser {

    private String _id;
    private String country;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "SonataUser{" +
               "_id='" + _id + '\'' +
               ", country='" + country + '\'' +
               '}';
    }
}
