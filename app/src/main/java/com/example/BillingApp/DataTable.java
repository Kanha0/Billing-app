package com.example.BillingApp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "Data_Table")
public class DataTable implements Serializable {
    // list of data columns we will make to store our data.
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date")
    private String  date;

    @ColumnInfo(name = "cgst")
    private float cgst;

    @ColumnInfo(name = "sgst")
    private float sgst;

    @ColumnInfo(name = "igst")
    private float igst;

    //    @ColumnInfo(name = "productList")
//    private productList productlist;
    //sn 10 variables
    @ColumnInfo(name = "sn0")
    private int sn0;
    @ColumnInfo(name = "sn1")
    private int sn1;
    @ColumnInfo(name = "sn2")
    private int sn2;
    @ColumnInfo(name = "sn3")
    private int sn3;
    @ColumnInfo(name = "sn4")
    private int sn4;
    @ColumnInfo(name = "sn5")
    private int sn5;
    @ColumnInfo(name = "sn6")
    private int sn6;
    @ColumnInfo(name = "sn7")
    private int sn7;
    @ColumnInfo(name = "sn8")
    private int sn8;
    @ColumnInfo(name = "sn9")
    private int sn9;

    //particular 10 variable
    @ColumnInfo(name = "particular0")
    private String particular0;
    @ColumnInfo(name = "particular1")
    private String particular1;
    @ColumnInfo(name = "particular2")
    private String particular2;
    @ColumnInfo(name = "particular3")
    private String particular3;
    @ColumnInfo(name = "particular4")
    private String particular4;
    @ColumnInfo(name = "particular5")
    private String particular5;
    @ColumnInfo(name = "particular6")
    private String particular6;
    @ColumnInfo(name = "particular7")
    private String particular7;
    @ColumnInfo(name = "particular8")
    private String particular8;
    @ColumnInfo(name = "particular9")
    private String particular9;

    //hsncode 10 variables
    @ColumnInfo(name = "hsncode0")
    private String hsnCode0;
    @ColumnInfo(name = "hsncode1")
    private String hsnCode1;
    @ColumnInfo(name = "hsncode2")
    private String hsnCode2;
    @ColumnInfo(name = "hsncode3")
    private String hsnCode3;
    @ColumnInfo(name = "hsncode4")
    private String hsnCode4;
    @ColumnInfo(name = "hsncode5")
    private String hsnCode5;
    @ColumnInfo(name = "hsncode6")
    private String hsnCode6;
    @ColumnInfo(name = "hsncode7")
    private String hsnCode7;
    @ColumnInfo(name = "hsncode8")
    private String hsnCode8;
    @ColumnInfo(name = "hsncode9")
    private String hsnCode9;

    //quantity 10 variables
    @ColumnInfo(name = "quantity0")
    private int quatity0;
    @ColumnInfo(name = "quantity1")
    private int quatity1;
    @ColumnInfo(name = "quantity2")
    private int quatity2;
    @ColumnInfo(name = "quantity3")
    private int quatity3;
    @ColumnInfo(name = "quantity4")
    private int quatity4;
    @ColumnInfo(name = "quantity5")
    private int quatity5;
    @ColumnInfo(name = "quantity6")
    private int quatity6;
    @ColumnInfo(name = "quantity7")
    private int quatity7;
    @ColumnInfo(name = "quantity8")
    private int quatity8;
    @ColumnInfo(name = "quantity9")
    private int quatity9;

    //rate 10 variable
    @ColumnInfo(name = "rate0")
    private int rate0;
    @ColumnInfo(name = "rate1")
    private int rate1;
    @ColumnInfo(name = "rate2")
    private int rate2;
    @ColumnInfo(name = "rate3")
    private int rate3;
    @ColumnInfo(name = "rate4")
    private int rate4;
    @ColumnInfo(name = "rate5")
    private int rate5;
    @ColumnInfo(name = "rate6")
    private int rate6;
    @ColumnInfo(name = "rate7")
    private int rate7;
    @ColumnInfo(name = "rate8")
    private int rate8;
    @ColumnInfo(name = "rate9")
    private int rate9;

    //amount 10 variables
    @ColumnInfo(name = "amount0")
    private int amount0;
    @ColumnInfo(name = "amount1")
    private int amount1;
    @ColumnInfo(name = "amount2")
    private int amount2;
    @ColumnInfo(name = "amount3")
    private int amount3;
    @ColumnInfo(name = "amount4")
    private int amount4;
    @ColumnInfo(name = "amount5")
    private int amount5;
    @ColumnInfo(name = "amount6")
    private int amount6;
    @ColumnInfo(name = "amount7")
    private int amount7;
    @ColumnInfo(name = "amount8")
    private int amount8;
    @ColumnInfo(name = "amount9")
    private int amount9;



//empty constructer
    public DataTable() {
        // empty constructor
    }


    // Constructor with all variable

    public DataTable(@NonNull int id,String date, ArrayList<Integer> snList, ArrayList<String> particularList, ArrayList<String> hsnCodelist,
                     ArrayList<Integer> quatityList,ArrayList<Integer> rateList, ArrayList<Integer> amountList,  float cgst, float sgst , float igst) {
        this.id = id;
        this.date = date;
        this.cgst = cgst;
        this.sgst = sgst;
        this.igst = igst;

        // product list data
        listToarray(snList, particularList, hsnCodelist, rateList, quatityList, amountList);


//        this.sn = sn;
//        this.particulars = particulars;
//        this.hsnCode = hsnCode;
//        this.quantity = quantity;
//        this.rate = rate;
////        this.amount = rate * quantity;
//        setAmount((rate*quantity));

    }

// Getters.

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSn0() {
        return sn0;
    }

    public void setSn0(int sn0) {
        this.sn0 = sn0;
    }

    public int getSn1() {
        return sn1;
    }

    public void setSn1(int sn1) {
        this.sn1 = sn1;
    }

    public int getSn2() {
        return sn2;
    }

    public void setSn2(int sn2) {
        this.sn2 = sn2;
    }

    public int getSn3() {
        return sn3;
    }

    public void setSn3(int sn3) {
        this.sn3 = sn3;
    }

    public int getSn4() {
        return sn4;
    }

    public void setSn4(int sn4) {
        this.sn4 = sn4;
    }

    public int getSn5() {
        return sn5;
    }

    public void setSn5(int sn5) {
        this.sn5 = sn5;
    }

    public int getSn6() {
        return sn6;
    }

    public void setSn6(int sn6) {
        this.sn6 = sn6;
    }

    public int getSn7() {
        return sn7;
    }

    public void setSn7(int sn7) {
        this.sn7 = sn7;
    }

    public int getSn8() {
        return sn8;
    }

    public void setSn8(int sn8) {
        this.sn8 = sn8;
    }

    public int getSn9() {
        return sn9;
    }

    public void setSn9(int sn9) {
        this.sn9 = sn9;
    }

    public String getParticular0() {
        return particular0;
    }

    public void setParticular0(String particular0) {
        this.particular0 = particular0;
    }

    public String getParticular1() {
        return particular1;
    }

    public void setParticular1(String particular1) {
        this.particular1 = particular1;
    }

    public String getParticular2() {
        return particular2;
    }

    public void setParticular2(String particular2) {
        this.particular2 = particular2;
    }

    public String getParticular3() {
        return particular3;
    }

    public void setParticular3(String particular3) {
        this.particular3 = particular3;
    }

    public String getParticular4() {
        return particular4;
    }

    public void setParticular4(String particular4) {
        this.particular4 = particular4;
    }

    public String getParticular5() {
        return particular5;
    }

    public void setParticular5(String particular5) {
        this.particular5 = particular5;
    }

    public String getParticular6() {
        return particular6;
    }

    public void setParticular6(String particular6) {
        this.particular6 = particular6;
    }

    public String getParticular7() {
        return particular7;
    }

    public void setParticular7(String particular7) {
        this.particular7 = particular7;
    }

    public String getParticular8() {
        return particular8;
    }

    public void setParticular8(String particular8) {
        this.particular8 = particular8;
    }

    public String getParticular9() {
        return particular9;
    }

    public void setParticular9(String particular9) {
        this.particular9 = particular9;
    }

    public String getHsnCode0() {
        return hsnCode0;
    }

    public void setHsnCode0(String hsnCode0) {
        this.hsnCode0 = hsnCode0;
    }

    public String getHsnCode1() {
        return hsnCode1;
    }

    public void setHsnCode1(String hsnCode1) {
        this.hsnCode1 = hsnCode1;
    }

    public String getHsnCode2() {
        return hsnCode2;
    }

    public void setHsnCode2(String hsnCode2) {
        this.hsnCode2 = hsnCode2;
    }

    public String getHsnCode3() {
        return hsnCode3;
    }

    public void setHsnCode3(String hsnCode3) {
        this.hsnCode3 = hsnCode3;
    }

    public String getHsnCode4() {
        return hsnCode4;
    }

    public void setHsnCode4(String hsnCode4) {
        this.hsnCode4 = hsnCode4;
    }

    public String getHsnCode5() {
        return hsnCode5;
    }

    public void setHsnCode5(String hsnCode5) {
        this.hsnCode5 = hsnCode5;
    }

    public String getHsnCode6() {
        return hsnCode6;
    }

    public void setHsnCode6(String hsnCode6) {
        this.hsnCode6 = hsnCode6;
    }

    public String getHsnCode7() {
        return hsnCode7;
    }

    public void setHsnCode7(String hsnCode7) {
        this.hsnCode7 = hsnCode7;
    }

    public String getHsnCode8() {
        return hsnCode8;
    }

    public void setHsnCode8(String hsnCode8) {
        this.hsnCode8 = hsnCode8;
    }

    public String getHsnCode9() {
        return hsnCode9;
    }

    public void setHsnCode9(String hsnCode9) {
        this.hsnCode9 = hsnCode9;
    }

    public int getQuatity0() {
        return quatity0;
    }

    public void setQuatity0(int quatity0) {
        this.quatity0 = quatity0;
    }

    public int getQuatity1() {
        return quatity1;
    }

    public void setQuatity1(int quatity1) {
        this.quatity1 = quatity1;
    }

    public int getQuatity2() {
        return quatity2;
    }

    public void setQuatity2(int quatity2) {
        this.quatity2 = quatity2;
    }

    public int getQuatity3() {
        return quatity3;
    }

    public void setQuatity3(int quatity3) {
        this.quatity3 = quatity3;
    }

    public int getQuatity4() {
        return quatity4;
    }

    public void setQuatity4(int quatity4) {
        this.quatity4 = quatity4;
    }

    public int getQuatity5() {
        return quatity5;
    }

    public void setQuatity5(int quatity5) {
        this.quatity5 = quatity5;
    }

    public int getQuatity6() {
        return quatity6;
    }

    public void setQuatity6(int quatity6) {
        this.quatity6 = quatity6;
    }

    public int getQuatity7() {
        return quatity7;
    }

    public void setQuatity7(int quatity7) {
        this.quatity7 = quatity7;
    }

    public int getQuatity8() {
        return quatity8;
    }

    public void setQuatity8(int quatity8) {
        this.quatity8 = quatity8;
    }

    public int getQuatity9() {
        return quatity9;
    }

    public void setQuatity9(int quatity9) {
        this.quatity9 = quatity9;
    }

    public int getRate0() {
        return rate0;
    }

    public void setRate0(int rate0) {
        this.rate0 = rate0;
    }

    public int getRate1() {
        return rate1;
    }

    public void setRate1(int rate1) {
        this.rate1 = rate1;
    }

    public int getRate2() {
        return rate2;
    }

    public void setRate2(int rate2) {
        this.rate2 = rate2;
    }

    public int getRate3() {
        return rate3;
    }

    public void setRate3(int rate3) {
        this.rate3 = rate3;
    }

    public int getRate4() {
        return rate4;
    }

    public void setRate4(int rate4) {
        this.rate4 = rate4;
    }

    public int getRate5() {
        return rate5;
    }

    public void setRate5(int rate5) {
        this.rate5 = rate5;
    }

    public int getRate6() {
        return rate6;
    }

    public void setRate6(int rate6) {
        this.rate6 = rate6;
    }

    public int getRate7() {
        return rate7;
    }

    public void setRate7(int rate7) {
        this.rate7 = rate7;
    }

    public int getRate8() {
        return rate8;
    }

    public void setRate8(int rate8) {
        this.rate8 = rate8;
    }

    public int getRate9() {
        return rate9;
    }

    public void setRate9(int rate9) {
        this.rate9 = rate9;
    }

    public int getAmount0() {
        return amount0;
    }

    public void setAmount0(int amount0) {
        this.amount0 = amount0;
    }

    public int getAmount1() {
        return amount1;
    }

    public void setAmount1(int amount1) {
        this.amount1 = amount1;
    }

    public int getAmount2() {
        return amount2;
    }

    public void setAmount2(int amount2) {
        this.amount2 = amount2;
    }

    public int getAmount3() {
        return amount3;
    }

    public void setAmount3(int amount3) {
        this.amount3 = amount3;
    }

    public int getAmount4() {
        return amount4;
    }

    public void setAmount4(int amount4) {
        this.amount4 = amount4;
    }

    public int getAmount5() {
        return amount5;
    }

    public void setAmount5(int amount5) {
        this.amount5 = amount5;
    }

    public int getAmount6() {
        return amount6;
    }

    public void setAmount6(int amount6) {
        this.amount6 = amount6;
    }

    public int getAmount7() {
        return amount7;
    }

    public void setAmount7(int amount7) {
        this.amount7 = amount7;
    }

    public int getAmount8() {
        return amount8;
    }

    public void setAmount8(int amount8) {
        this.amount8 = amount8;
    }

    public int getAmount9() {
        return amount9;
    }

    public void setAmount9(int amount9) {
        this.amount9 = amount9;
    }


    public float getCgst() {
        return cgst;
    }

    public void setCgst(float cgst) {
        this.cgst = cgst;
    }

    public float getSgst() {
        return sgst;
    }

    public void setSgst(float sgst) {
        this.sgst = sgst;
    }

    public float getIgst() {
        return igst;
    }

    public void setIgst(float igst) {
        this.igst = igst;
    }

    public void listToarray(ArrayList<Integer> snList, ArrayList<String> particularList, ArrayList<String> hsnCodelist,
                            ArrayList<Integer> quatityList, ArrayList<Integer> rateList, ArrayList<Integer> amountList) {


        for(int x = 0;  x <10; x++) {

            if (x < particularList.size() && x == 0) {
                this.sn0 = snList.get(x);
                this.particular0 = particularList.get(x);
                this.hsnCode0 = hsnCodelist.get(x);
                this.quatity0 = quatityList.get(x);
                this.rate0 = rateList.get(x);
                this.amount0 = amountList.get(x);
//                x =1 ;

            } else if (x < particularList.size() && x == 1) {
                this.sn1 = snList.get(x);
                this.particular1 = particularList.get(x);
                this.hsnCode1 = hsnCodelist.get(x);
                this.quatity1 = quatityList.get(x);
                this.rate1 = rateList.get(x);
                this.amount1 = amountList.get(x);
            } else if (x < particularList.size() && x == 2) {
//            x = 2;
                this.sn2 = snList.get(x);
                this.particular2 = particularList.get(x);
                this.hsnCode2 = hsnCodelist.get(x);
                this.quatity2 = quatityList.get(x);
                this.rate2 = rateList.get(x);
                this.amount2 = amountList.get(x);
            } else if (x < particularList.size() && x == 3) {
//            x = 3;
                this.sn3 = snList.get(x);
                this.particular3 = particularList.get(x);
                this.hsnCode3 = hsnCodelist.get(x);
                this.quatity3 = quatityList.get(x);
                this.rate3 = rateList.get(x);
                this.amount3 = amountList.get(x);
            } else if (x < particularList.size() && x == 4) {
//            x = 4;
                this.sn4 = snList.get(x);
                this.particular4 = particularList.get(x);
                this.hsnCode4 = hsnCodelist.get(x);
                this.quatity4 = quatityList.get(x);
                this.rate4 = rateList.get(x);
                this.amount4 = amountList.get(x);
            } else if (x < particularList.size() && x == 5) {
//            x = 5;
                this.sn5 = snList.get(x);
                this.particular5 = particularList.get(x);
                this.hsnCode5 = hsnCodelist.get(x);
                this.quatity5 = quatityList.get(x);
                this.rate5 = rateList.get(x);
                this.amount5 = amountList.get(x);
            } else if (x < particularList.size() && x == 6) {
//            x = 6;
                this.sn6 = snList.get(x);
                this.particular6 = particularList.get(x);
                this.hsnCode6 = hsnCodelist.get(x);
                this.quatity6 = quatityList.get(x);
                this.rate6 = rateList.get(x);
                this.amount6 = amountList.get(x);
            } else if (x < particularList.size() && x == 7) {
//            x = 7;
                this.sn7 = snList.get(x);
                this.particular7 = particularList.get(x);
                this.hsnCode7 = hsnCodelist.get(x);
                this.quatity7 = quatityList.get(x);
                this.rate7 = rateList.get(x);
                this.amount7 = amountList.get(x);
            } else if (x < particularList.size() && x == 8) {
//            x = 8;
                this.sn8 = snList.get(x);
                this.particular8 = particularList.get(x);
                this.hsnCode8 = hsnCodelist.get(x);
                this.quatity8 = quatityList.get(x);
                this.rate8 = rateList.get(x);
                this.amount8 = amountList.get(x);
            } else if (x < particularList.size() && x == 9) {
//            x = 9;
                this.sn9 = snList.get(x);
                this.particular9 = particularList.get(x);
                this.hsnCode9 = hsnCodelist.get(x);
                this.quatity9 = quatityList.get(x);
                this.rate9 = rateList.get(x);
                this.amount9 = amountList.get(x);
            }
        }
    }

    //    public int getSn() {
//        return sn;
//    }

//    public String getParticulars() {
//        return particulars;
//    }
//
//    public String getHsnCode() {
//        return hsnCode;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public int getRate() {
//        return rate;
//    }
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }

}