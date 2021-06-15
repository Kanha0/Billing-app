package com.example.BillingApp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RepositoryData {
    private DaoData rdaoData;
    private LiveData<List<DataTable>> rdata;
    private  LiveData<List<CustomerDetails>> rCustomer;


    public RepositoryData(Application application) {
        RoomDatabaseData db = RoomDatabaseData.getDatabase(application);

        this.rdaoData = db.daoData();
        this.rdata = rdaoData.getAllData();
        this.rCustomer = rdaoData.getAllCustomer();
    }

    public LiveData<List<DataTable>> getData(){
        return rdata;
    }

    public LiveData<List<CustomerDetails>> getrCustomer(){return rCustomer; }

    // to insert data
    public void insert(DataTable dataTable){
        RoomDatabaseData.databaseWriterExecutor.execute(() ->{
            rdaoData.insertData(dataTable);
        });
    }
    public void insertCustomer(CustomerDetails customer){
        RoomDatabaseData.databaseCustomerWriterExecutor.execute(() ->{
            rdaoData.insertCustomer(customer);
        });
    }

// to delete data
    public void delete(DataTable dataTable){
        RoomDatabaseData.databaseWriterExecutor.execute(() -> {
            rdaoData.deleteData(dataTable);
        });
    }

    public void deleteCustomer(CustomerDetails customer){
        RoomDatabaseData.databaseCustomerWriterExecutor.execute(() -> {
            rdaoData.deleteCustomer(customer);
        });
    }

}
