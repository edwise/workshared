package com.edwise.utils.jsoncrosser.model;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 01/06/16.
 */
public class BidderOwner {

    private String _id;
    private Wallet wallet;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "BidderOwner{" +
               "_id='" + _id + '\'' +
               ", wallet=" + wallet +
               '}';
    }
}
