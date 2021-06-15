package com.example.BillingApp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomerCLicked,itemCLicked  {

    private RecyclerView customerRecyclerView;
    private RecyclerView dataRecyclerView;

    private ViewModelData  viewmodel;
    private FloatingActionButton fab;
    private ToggleButton toggleSwitch;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
//    public static int Storageflag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MANAGE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        toggleSwitch = findViewById(R.id.toggleSwitch);

        //floating button onClickLisiner
        fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, customer.class);
//            startActivity(intent);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });

        // recyclerView setting;
        customerRecyclerView = findViewById(R.id.Customerrecyclerview);
        callCustomerRecyclerview();

//        dataRecyclerView.invalidate();
//        customerRecyclerView = findViewById(R.id.Customerrecyclerview);
////        final ListAdapter adapter = new ListAdapterData(new ListAdapterData.DataDiff(), MainActivity.this);
//        final ListAdapter adapter = new ListAdapterCustomer(new ListAdapterCustomer.DataDiff(), MainActivity.this);
//
//        customerRecyclerView.setAdapter(adapter);
//        customerRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//
//        //view model setting
////        viewmodel = new ViewModelProvider(this).get(ViewModelData.class);
//        viewmodel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ViewModelData.class);
//        viewmodel.getAllCustomer().observe(MainActivity.this,data -> {
//            adapter.submitList(data);
//        } );


        // setting toggle switch
        toggleSwitch.setOnClickListener(v -> {

            if( toggleSwitch.isChecked()){
                customerRecyclerView.setAlpha(0);
                dataRecyclerView = findViewById(R.id.Datarecyclerview);
                dataRecyclerView.setAlpha(1);

                dataRecyclerView.bringToFront();

                final ListAdapter listadapter = new ListAdapterData(new ListAdapterData.DataDiff(), MainActivity.this);

                dataRecyclerView.setAdapter(listadapter);
                dataRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                Toast.makeText(this,"List of All Bill's & Quotations", Toast.LENGTH_SHORT).show();

//                listadapter.submitList(viewmodel.getAlldata().getValue());

                viewmodel.getAlldata().observe(MainActivity.this,data -> {
                    listadapter.submitList(data);
                } );
            } else {
                dataRecyclerView.setAlpha(0);
                customerRecyclerView.setAlpha(1);
                customerRecyclerView.bringToFront();

                Toast.makeText(this,"List of All Customer", Toast.LENGTH_SHORT).show();
//                Intent intent = getIntent();
//                finish();
//                startActivity(intent);

                callCustomerRecyclerview();
//                final ListAdapter adapter = new ListAdapterCustomer(new ListAdapterCustomer.DataDiff(), MainActivity.this);
//
//                recyclerView.setAdapter(adapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//
//                viewmodel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ViewModelData.class);

//                viewmodel.getAllCustomer().observe(MainActivity.this,data -> {
//                    adapter.submitList(viewmodel.getAllCustomer().getValue());
//                } );
            }

        });

//        viewmodel.getAlldata().observe(MainActivity.this, new Observer<List<DataTable>>() {
//            @Override
//            public void onChanged(List<DataTable> dataTables) {
//                if(dataTables != null) {
//                adapter.submitList(dataTables);
//                }
//            }
//        } );

    }

    public void callCustomerRecyclerview(){
//        final ListAdapter adapter = new ListAdapterData(new ListAdapterData.DataDiff(), MainActivity.this);
        customerRecyclerView = findViewById(R.id.Customerrecyclerview);
        final ListAdapter adapter = new ListAdapterCustomer(new ListAdapterCustomer.DataDiff(), MainActivity.this);

        customerRecyclerView.setAdapter(adapter);
        customerRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //view model setting
//        viewmodel = new ViewModelProvider(this).get(ViewModelData.class);
        viewmodel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ViewModelData.class);
        viewmodel.getAllCustomer().observe(MainActivity.this,data -> {
            adapter.submitList(data);
        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("**************************************************************************************************************************");
//        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            System.out.println("saved");
            System.out.println(requestCode);
            System.out.println(resultCode);

            CustomerDetails finaldata = (CustomerDetails) data.getSerializableExtra("Customerobject");

            System.out.println(finaldata.getName());

            viewmodel.insertCustomer(finaldata);
            Toast.makeText(getApplicationContext(), "Customer Added Successfully", Toast.LENGTH_LONG).show();

        } else {
            System.out.println("not saved");
            System.out.println(requestCode);
            System.out.println(resultCode);
            Toast.makeText(getApplicationContext(), "Empty not saved", Toast.LENGTH_LONG).show();
        }
    }

//    public void alert(View view) {
//        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
//        myAlert.setTitle("Exit");
//        myAlert.setMessage("Are you Sure ?");
//        myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(Activity2.this,"Yes Pressed", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });
//
//        myAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(Activity2.this,"No Pressed", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });
//        myAlert.setCancelable(false);
//        myAlert.show();
//    }

    @Override
    public void onDeleteClicked(CustomerDetails data) {
//        viewmodel.deleteCustomer(data);
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setTitle("Delete");
        myAlert.setMessage("Are you Sure ?");
        myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
        viewmodel.deleteCustomer(data);
            }
        });

        myAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        myAlert.setCancelable(false);
        myAlert.show();

    }

    @Override
    public void onNameClicked(CustomerDetails data) {
        Intent replyIntent = new Intent(MainActivity.this, displayData.class);
            replyIntent.putExtra("currentCustomer", (Serializable) data);
            startActivity(replyIntent);

        finish();
    }

    @Override
    public void onDeleteClicked(DataTable data) {
        Toast.makeText(this,"Unable to Delete Item", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNameClicked(DataTable data) {

        List<CustomerDetails> finaldata =  viewmodel.getAllCustomer().getValue();
        for(CustomerDetails currentCustomer : finaldata ){
            if (data.getId() == currentCustomer.getId()){
                Intent replyIntent = new Intent(MainActivity.this, quotation.class);
        replyIntent.putExtra("currentItem", (Serializable) data);
        replyIntent.putExtra("currentCustomer", (Serializable) currentCustomer);
        startActivity(replyIntent);
            }
        }
    }
}