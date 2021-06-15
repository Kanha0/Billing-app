package com.example.BillingApp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModelData extends AndroidViewModel {

    private RepositoryData repository;

    private final LiveData<List<DataTable>> alldata;
    private final LiveData<List<CustomerDetails>> allCustomer;

    public ViewModelData(Application application) {
        super(application);
        this.repository = new RepositoryData(application);
        this.alldata = repository.getData();
        this.allCustomer = repository.getrCustomer();
    }

    public LiveData<List<DataTable>> getAlldata(){
        return alldata;
    }
    public LiveData<List<CustomerDetails>> getAllCustomer(){
        return allCustomer;
    }


    //insert data
    public void insert(DataTable data){
        repository.insert(data);
    }

    public void insertCustomer(CustomerDetails data){
        repository.insertCustomer(data);
    }

    // delete data
    public void delete(DataTable data){
        repository.delete(data);
    }

    public void deleteCustomer(CustomerDetails data){
        repository.deleteCustomer(data);
    }

}
