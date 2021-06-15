package com.example.BillingApp;

public class products {

    private int sn;
    private String particular;
    private String hsnCode;
    private int quatity;
    private int rate;
    private int amount;

    public products(int sn, String particular, String hsnCode, int quatity, int rate) {
        this.sn = sn;
        this.particular = particular;
        this.hsnCode = hsnCode;
        this.quatity = quatity;
        this.rate = rate;
        this.amount = rate * quatity;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
