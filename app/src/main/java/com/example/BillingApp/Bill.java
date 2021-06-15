package com.example.BillingApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Bill extends AppCompatActivity {


    private EditText date;
    private String d;
    private int id;
    private EditText Bcgst;
    private float cgst;
    private EditText Bsgst;
    private float sgst;
    private EditText Bigst;
    private float igst;
    private ImageView dateImageView;
    private DatePickerDialog.OnDateSetListener setListener;


//    Drawable error = getApplicationContext().getDrawable(R.drawable.ic_baseline_error_24);
//Drawable error = getDrawable(R.drawable.ic_baseline_error_24);

    private ViewModelData  viewmodel;
    private LinearLayout layoutlist;
    private Button add;
    private ImageView remove;

    private productAdapter adapter;
//    private productList productlist;

    // array list variables for product list
//    private ArrayList<Integer> snList;
//    private ArrayList<String> particularList;
//    private ArrayList<String> hsnCodeList;
//    private ArrayList<Integer> quatityList;
//    private ArrayList<Integer> rateList;
//    private ArrayList<Integer> amountList;


    //object of class DataTable
//    DataTable data ;

    public static final String Data = "Dataobject";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        // method to Initilization of variables;
         callfindViewbyId();

         Intent intent = getIntent();
        this.id = intent.getIntExtra("currentCustomer", 0);


    }

    private void callfindViewbyId() {
        // name of all variable
//        productlist = new productList();
        viewmodel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ViewModelData.class);

        layoutlist = (LinearLayout) findViewById(R.id.layoutlist);
        add = (Button) findViewById(R.id.add);
        remove = (ImageView) findViewById(R.id.remove);
        date = (EditText) findViewById(R.id.date);
        dateImageView = (ImageView) findViewById(R.id.calendar);

        Bcgst = (EditText) findViewById(R.id.Bcgst);
        cgst = 0.0f;
        Bsgst = (EditText) findViewById(R.id.Bsgst);
        sgst = 0.0f;
        Bigst = (EditText) findViewById(R.id.Bigst);
        igst = 0.0f;

        // add button function
        add.setOnClickListener((v) -> {
            addList();
        });

        //setting Todays date on edit text
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String today = dateFormat.format(calendar.getTime());

        date.setText(today);

        // creating a datepicker listiner
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateImageView.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(Bill.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener, year,month,day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        //setting datepicker Dialog LIstiner
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                if(dayOfMonth >=10 || month >=  10) {
                    if(dayOfMonth >= 10 && month <=9) {
                        String d = dayOfMonth + "/0" + month + "/" + year;
                        date.setText(d);
                    } else if(dayOfMonth <= 9 && month >=10){
                        String d = "0"+dayOfMonth + "/" + month + "/" + year;
                        date.setText(d);
                    } else {
                        String d = dayOfMonth + "/" + month + "/" + year;
                        date.setText(d);
                    }
                } else {
                    String d = "0"+dayOfMonth + "/0" + month + "/" + year;
                    date.setText(d);
                }
            }
        };

    }

    private void addList() {

        View itemView = getLayoutInflater().inflate(R.layout.productrecyclerview, null, false);


        Spinner particular = (Spinner) itemView.findViewById(R.id.Lparticulars);
        EditText hsncode = (EditText) itemView.findViewById(R.id.Lhsncode);
        EditText rate = (EditText) itemView.findViewById(R.id.Lrate);
        EditText quantity = (EditText) itemView.findViewById(R.id.Lquantity);
        ImageView remove = (ImageView) itemView.findViewById(R.id.remove);

        //spinner addapter
//        ArrayAdapter<CharSequence> padapter = ArrayAdapter.createFromResource(Bill.this,R.array.praticular_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> padapter = ArrayAdapter.createFromResource(Bill.this,R.array.praticular_array, R.layout.custom_spinner_item);
//        padapter.setDropDownViewResource(R.layout.dropdown);
        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        particular.setAdapter(padapter);
////Setting Errors if the hsncode or rate or Quantity is empty
//        if (hsncode.getText().toString().isEmpty()){
//            hsncode.setError("HSN Code is Empty");
//        }
//        if( rate.getText().toString().isEmpty()){
//            rate.setError("Rate is Empty");
//        }
//        if (quantity.getText().toString().isEmpty()){
//            quantity.setError("Quantity is Empty");
//        }

        // remove button function
        remove.setOnClickListener((v) -> {
            removelist(itemView);
        });

        layoutlist.addView(itemView);
    }


    private void removelist(View view){
        layoutlist.removeView(view);

    }

    public void submitData(View view) {
        //is flag is 1 then it will give the tost i.e. some thing is empty
        int flag = 0;
        System.out.println("-----------------------------------------");
        ArrayList<Integer> snList = new ArrayList<>();
        ArrayList<String> particularList = new ArrayList<>();
        ArrayList<String> hsnCodeList = new ArrayList<>();
        ArrayList<Integer> quatityList = new ArrayList<>();
        ArrayList<Integer> rateList = new ArrayList<>();
        ArrayList<Integer> amountList = new ArrayList<>();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        // copying data from layout
        this.d = date.getText().toString();
        this.cgst = Float.parseFloat(Bcgst.getText().toString());
        this.sgst = Float.parseFloat(Bsgst.getText().toString());
        this.igst = Float.parseFloat(Bigst.getText().toString());
        System.out.println("**********************************************");
        if(d.isEmpty()){
            flag = 1;
            date.setError("Date is empty");
        }

        if(this.cgst == 0){
            Bcgst.setError("CGST is 0.0%");
            this.cgst = 0.0f;
        }
        if (this.sgst == 0 ) {
          this.sgst = 0.0f;
            Bsgst.setError("SGST is 0.0%");
        }
        if (this.igst == 0) {
            this.igst = 0.0f;
            Bigst.setError("BIGST is 0.0%");
        }


         // process to get Productlist
        if(layoutlist.getChildCount() <= 0){
            flag = 2;
        } else {

            for (int i = 0; i < layoutlist.getChildCount(); i++) {
                View productView = layoutlist.getChildAt(i);

                Spinner particular = (Spinner) productView.findViewById(R.id.Lparticulars);
                EditText hsncode = (EditText) productView.findViewById(R.id.Lhsncode);
                EditText rate = (EditText) productView.findViewById(R.id.Lrate);
                EditText quantity = (EditText) productView.findViewById(R.id.Lquantity);


                //extract data from input
                String p = particular.getSelectedItem().toString();
                String h = hsncode.getText().toString();
                int r = Integer.parseInt(rate.getText().toString());
                int q = Integer.parseInt(quantity.getText().toString());

                //Setting Errors if the hsncode or rate or Quantity is empty
                if(particular.getSelectedItem().toString().isEmpty()){
                    Toast.makeText(this,"Please Enter Products Name", Toast.LENGTH_SHORT);
                    flag = 3;
                }
                if (hsncode.getText().toString().isEmpty()){
                    hsncode.setError("HSN Code is Empty");
                    flag = 3;
                }
                if( r ==0){
                    rate.setError("Rate is Empty");
                    flag = 3;
                }
                if (q ==0 ){
                    quantity.setError("Quantity is Empty");
                    flag = 3;
                }


//                //extract data from input
//                String p = particular.getSelectedItem().toString();
//                String h = hsncode.getText().toString();
//                int r = Integer.parseInt(rate.getText().toString());
//                int q = Integer.parseInt(quantity.getText().toString());

                if (p != null) {

                    snList.add(i + 1);
                    particularList.add(p);
                    hsnCodeList.add(h);
                    rateList.add(r);
                    quatityList.add(q);
                    amountList.add(r * q);

                } else {
                    snList.add(i + 1);
                    particularList.add("Particular");
                    hsnCodeList.add("HSNCode");
                    rateList.add(0);
                    quatityList.add(0);
                    amountList.add(0);
                }
            }
        }
        System.out.println("/////////////////////////////////////////////////////////////////////////");
//        productlist = new productList(snList,particularList,hsnCodeList,quatityList,rateList,amountList);

        // defining object of DataTable by above data
//         data = new DataTable(n,d,a, no, g,productlist.getSnList(), productlist.getParticularList(),productlist.getHsnCodeList(),productlist.getQuatityList(),productlist.getRateList(),productlist.getAmountList());

        if(flag == 0) {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(date.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                productList productlist = new productList(snList, particularList, hsnCodeList, quatityList, rateList, amountList);
                DataTable data = new DataTable(this.id, this.d, productlist.getSnList(), productlist.getParticularList(), productlist.getHsnCodeList(),
                        productlist.getQuatityList(), productlist.getRateList(), productlist.getAmountList(), this.cgst, this.sgst, this.igst);
                System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooo");

                            viewmodel.insert(data);
                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                replyIntent.putExtra(Data, (Serializable) data);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        } else if (flag ==1){
            Toast.makeText(this,"Date is empty", Toast.LENGTH_SHORT);
        } else if (flag == 2 ){
            Toast.makeText(this,"Please Enter Products", Toast.LENGTH_LONG);
        } else if(flag == 3){
            Toast.makeText(this,"Please enter product Details", Toast.LENGTH_SHORT);

        }

        Toast.makeText(this,"Please Enter Data", Toast.LENGTH_SHORT);


    }

}
