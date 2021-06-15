package com.example.BillingApp;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Customer_Details")
public class CustomerDetails implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "number")
    private String number;

    @ColumnInfo(name = "GSTIN")
    private String GSTIN;

    //empty constructor
    @Ignore
    public CustomerDetails() {
    }

//constructor
//    public CustomerDetails( int id ,@NonNull String name, String address, String number, String GSTIN) {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.number = number;
//        this.GSTIN = GSTIN;
//    }

    public CustomerDetails(@NonNull String name, String address, String number, String GSTIN) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.number = number;
        this.GSTIN = GSTIN;
    }

    // getter and setter


    public int getId() {
        return id;
    }

    public void setId( int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }
}
