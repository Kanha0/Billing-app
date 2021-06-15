package com.example.BillingApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class customer extends AppCompatActivity {

    private EditText name;
//    private String n;
    private EditText address;
//    private String a;
    private EditText GSTIN;
//    private String g;
    private EditText number;
//    private String no;

    private static final String Data = "Customerobject";
//    private static int customer_id = 0 ;
//    Drawable error = AppCompatResources.getDrawable(customer.this,R.drawable.ic_baseline_error_24);
//Drawable error = getDrawable(R.drawable.ic_baseline_error_24);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        // initilizating customer id

        callfindViewbyId();
    }

    private void callfindViewbyId() {
        // name of all variable
//        productlist = new productList();

        name = (EditText) findViewById(R.id.Dname);
        address = (EditText) findViewById(R.id.Daddress);
        GSTIN = (EditText) findViewById(R.id.GSTIN);
        number = (EditText) findViewById(R.id.Dnumber);


    }


    public void AddCustomer(View view) {
       int  flag = 0;
       // incrementing Id
//        customer_id++;
        // copying data from layout
        String n = name.getText().toString();
        String a = address.getText().toString();
        String g = GSTIN.getText().toString();
        String no = number.getText().toString();

        if(n.isEmpty()){
            name.setError("Name is Empty");
            flag = 1;
        }
        if (a.isEmpty()){
            address.setError("Address is Empty");
            flag = 1;

        }
        if (g.isEmpty()){
            GSTIN.setError("GSTIN No. is Empty");
            flag = 1;
        }
        if (no.isEmpty()){
            number.setError("Number is Empty");
            flag = 1;
        }

        //checking flag
        if (flag == 0) {
            // incrementing customer id
//            customer_id++;

            //intent Reply
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(name.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
//                CustomerDetails data = new CustomerDetails(customer_id, n, a, no, g);
                CustomerDetails data = new CustomerDetails( n, a, no, g);

                replyIntent.putExtra(Data, (Serializable) data);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        } else {
            Toast.makeText(this,"Please Enter Data", Toast.LENGTH_SHORT);
        }
        Toast.makeText(this,"Please Enter Data", Toast.LENGTH_SHORT);

    }
}