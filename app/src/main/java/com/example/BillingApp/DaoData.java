package com.example.BillingApp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoData {

    // function to insert data
    @Insert
    void insertData(DataTable data);

    @Insert
    void insertCustomer(CustomerDetails customer);

    // function to delete data
    @Delete
    void deleteData(DataTable data);

    @Delete
    void deleteCustomer(CustomerDetails customer);

    // function to get all data ORDERED  by date
    @Query("SELECT * FROM Data_Table")
    LiveData<List<DataTable>> getAllData();

    @Query("SELECT * FROM Customer_Details ORDER BY name ")
    LiveData<List<CustomerDetails>> getAllCustomer();

    @Query("DELETE FROM Data_Table")
    void deleteAll();

    @Query("DELETE  FROM Customer_Details")
    void deleteAllCustomer();

}
