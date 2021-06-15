package com.example.BillingApp;

import java.util.ArrayList;

// class to accept the list of product.
public class productList {

    private ArrayList<Integer> snList;
    private ArrayList<String> particularList;
    private ArrayList<String> hsnCodeList;
    private ArrayList<Integer> quatityList;
    private ArrayList<Integer> rateList;
    private ArrayList<Integer> amountList;

    private int sn;

    // empty constructor


    public productList() {
        //constructor called;
    }

    public productList(ArrayList<Integer> snList, ArrayList<String> particularList,
                       ArrayList<String> hsnCodeList, ArrayList<Integer> quatityList, ArrayList<Integer> rateList, ArrayList<Integer> amountList) {
        this.snList = snList;
        this.particularList = particularList;
        this.hsnCodeList = hsnCodeList;
        this.quatityList = quatityList;
        this.rateList = rateList;
        this.amountList = amountList;
    }

    public  productList(String particular, String hsnCode, int quatity, int rate) {
// updating list values;

        this.particularList.add(particular);
        this.hsnCodeList.add(hsnCode);
        this.quatityList.add(quatity);
        this.rateList.add(rate);
        this.amountList.add(rate * quatity);

    }

    public ArrayList<Integer> getSnList() {
        return snList;
    }

    public void setSnList(ArrayList<Integer> snList) {
        this.snList = snList;
    }

    public ArrayList<String> getParticularList() {
        return particularList;
    }

    public void setParticularList(ArrayList<String> particularList) {
        this.particularList = particularList;
    }

    public ArrayList<String> getHsnCodeList() {
        return hsnCodeList;
    }

    public void setHsnCodeList(ArrayList<String> hsnCodeList) {
        this.hsnCodeList = hsnCodeList;
    }

    public ArrayList<Integer> getQuatityList() {
        return quatityList;
    }

    public void setQuatityList(ArrayList<Integer> quatityList) {
        this.quatityList = quatityList;
    }

    public ArrayList<Integer> getRateList() {
        return rateList;
    }

    public void setRateList(ArrayList<Integer> rateList) {
        this.rateList = rateList;
    }

    public ArrayList<Integer> getAmountList() {
        return amountList;
    }

    public void setAmountList(ArrayList<Integer> amountList) {
        this.amountList = amountList;
    }
}
