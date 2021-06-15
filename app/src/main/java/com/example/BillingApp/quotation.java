package com.example.BillingApp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class quotation extends AppCompatActivity {

    private RecyclerView displayrecycler;
    private TextView name;

    private TextView date;
    private TextView total;
    private TextView CGST;
    private TextView SGST;
    private TextView IGST;
    private TextView amount;
    private TextView Grandtotal;
    private Button printQuotation;
    private Button printBill;

    private TextView outputtotal;
    private TextView outputCGST;
    private TextView outputSGST;
    private TextView outputIGST;
    private TextView outputamount;
    private TextView outputGrandTotal;

    private float Total;
    private float cgst;
    private float sgst;
    private float igst;
    private float Amount;
    private int GrandTotal;

    private static final int CREATE_FILE = 1;

    private displayAdapter adapter;
    private DataTable data;
    private CustomerDetails customerdata;
    private productList productlist ;
    private ArrayList<products> productsArray;
    private PdfGenerator pdfGenerator;
//    private Activity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);
        data = new DataTable();
        productsArray = new ArrayList<>();

        this.Total = 0.0f;
        this.cgst = 0.0f;
        this.sgst = 0.0f;
        this.igst = 0.0f;
        this.Amount = 0.0f;
        this.GrandTotal = 0;

        data = (DataTable) getIntent().getSerializableExtra("currentItem");
        customerdata = (CustomerDetails) getIntent().getSerializableExtra("currentCustomer");
        callviewbyid();
        arraytoList(data);
        for (int i =0 ; i < productlist.getParticularList().size(); i++ ) {
            products currentProduct = new products(productlist.getSnList().get(i), productlist.getParticularList().get(i), productlist.getHsnCodeList().get(i),
                    productlist.getQuatityList().get(i), productlist.getRateList().get(i));

            this.productsArray.add(currentProduct);
        }

        // recyclerView setting;
        displayrecycler.setLayoutManager(new LinearLayoutManager(quotation.this));
        adapter = new displayAdapter(productsArray);
        displayrecycler.setAdapter(adapter);

        calculation();
        setValues();

    }

    // creating product list array
//    private ArrayList<products> getproductArray(productList p){
//        ArrayList<products> temp = new ArrayList<>();
//
//        for (int i =0 ; i < p.getParticularList().size(); i++ ){
//            products currentProduct = new products(p.getSnList().get(i),p.getParticularList().get(i), p.getHsnCodeList().get(i),
//                    p.getQuatityList().get(i), p.getRateList().get(i));
//
//            temp.add(currentProduct);
//
//        }
//
//        return temp;
//    }
    private void setValues() {


        name.setText("Name:  "+customerdata.getName());

        date.setText("Date:  "+data.getDate());

        CGST.setText("CGST@ " + data.getCgst() + "% :");
        SGST.setText("SGCT@ " + data.getSgst() + "% :");
        IGST.setText("IGST@ " + data.getIgst() + "% :");

        outputtotal.setText(Float.toString(this.Total));
        outputCGST.setText(Float.toString(this.cgst));
        outputIGST.setText(Float.toString(this.igst));
        outputSGST.setText(Float.toString(this.sgst));
        outputamount.setText(Float.toString(this.Amount));
        outputGrandTotal.setText(Float.toString(this.GrandTotal));

        printQuotation.setOnClickListener((v) -> {
            boolean test = createFolder();

            if(test) {
                // creating Path for Storage access framework
                File fpath = new File(Environment.getExternalStorageDirectory(), "NeoAgrotech");
                Uri filePath = Uri.fromFile(fpath);
// calling storage access framework method
                createFile(filePath, "Q");

                pdfGenerator = new PdfGenerator(data, customerdata, getResources(), this, this.productlist, "Q");
            } else {
                Toast.makeText(this, "Folder generating failed", Toast.LENGTH_SHORT).show();


            }

        });

        printBill.setOnClickListener((v) -> {
            boolean test = createFolder();
            if(test) {
            // creating Path for Storage access framework
            File fpath =  new File(Environment.getExternalStorageDirectory() , "NeoAgrotech");
            Uri filePath = Uri.fromFile(fpath);
// calling storage access framework method
            createFile(filePath,"B");

            pdfGenerator = new PdfGenerator(data,customerdata,getResources(),this, this.productlist, "B");
            } else {
                Toast.makeText(this, "Folder generating failed", Toast.LENGTH_SHORT).show();


            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_FILE && resultCode == RESULT_OK){
            try{
                Uri path = data.getData();

                ParcelFileDescriptor pfd = this.getContentResolver().
                        openFileDescriptor(path, "w");
                FileOutputStream fileOutputStream =
                        new FileOutputStream(pfd.getFileDescriptor());
                 pdfGenerator.createPdf(fileOutputStream);
//                mypdf.writeTo(fileOutputStream);
                // Let the document provider know you're done by closing the stream.
                fileOutputStream.close();
                pfd.close();


                Toast.makeText(this, "Pdf generated", Toast.LENGTH_SHORT).show();
            }catch(FileNotFoundException e){
                e.printStackTrace();
                Toast.makeText(this, "Pdf generated failed", Toast.LENGTH_SHORT).show();
            } catch(IOException e ){
                e.printStackTrace();
                Toast.makeText(this, "Pdf generated failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //*********************************************************************************************
    // Request code for creating a PDF document.
    private void createFile(Uri pickerInitialUri, String type) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_TITLE,  customerdata.getId() + type+ data.getDate()  + ".pdf");

        // Optionally, specify a URI for the directory that should be opened in
        // the system file picker when your app creates the document.
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);

        startActivityForResult(intent, CREATE_FILE);
    }

    private boolean createFolder() {
        // creating folder kto store pdf

        if(ActivityCompat.checkSelfPermission(this  , Manifest.permission.WRITE_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED ) {
            File pdfStorage = new File(Environment.getExternalStorageDirectory(), "NeoAgrotech");
            if (!pdfStorage.exists()) {
                pdfStorage.mkdir();
                Toast.makeText(this, "Pdf folder Generated ", Toast.LENGTH_SHORT).show();
                return true;
            }else{
                return true;
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE }, PackageManager.PERMISSION_GRANTED);
            createFolder();
        }
return false;
    }

    private void callviewbyid(){
        displayrecycler = (RecyclerView) findViewById(R.id.displayRecyclerView);
        name = (TextView) findViewById(R.id.Dname);
        date = (TextView) findViewById(R.id.date);

        outputtotal = (TextView) findViewById(R.id.Outputtotal);
        outputCGST = (TextView) findViewById(R.id.OutputCGST);
        outputSGST = (TextView) findViewById(R.id.OutputSGST);
        outputIGST = (TextView) findViewById(R.id.OutputIGST);
        outputamount = (TextView) findViewById(R.id.OutputAMOUNT);
        outputGrandTotal = (TextView) findViewById(R.id.OutputGTOTAL);

        total = (TextView) findViewById(R.id.total);
        CGST = (TextView) findViewById(R.id.CGST);
        SGST = (TextView) findViewById(R.id.SGST);
        IGST = (TextView) findViewById(R.id.IGST);
        amount = (TextView) findViewById(R.id.AMOUNT);
        Grandtotal = (TextView) findViewById(R.id.GTOTAL);

        printQuotation = (Button) findViewById(R.id.PrintQuotation);
        printBill = (Button) findViewById(R.id.PrintBill);


    }

    private void calculation(){

        ArrayList<Integer> temp = new ArrayList<>();
        temp = productlist.getAmountList();
        int a;

        for(int i = 0 ; i < temp.size() ; i++ ){
            this.Total = this.Total + temp.get(i);
        }

        this.cgst = (float) this.Total * (data.getCgst()/100);
        this.sgst = (float) this.Total * (data.getSgst()/100);
        this.igst = (float) this.Total * (data.getIgst()/100);

        this.Amount = (float) this.cgst + this.igst + this.sgst + this.Total;

        a = (int) this.Amount ;


        if(a != this.Amount){
            this.GrandTotal = (int) this.Amount + 1;
        } else {
            this.GrandTotal = (int) this.Amount;
        }

    }


    private void arraytoList(DataTable data){
        ArrayList<Integer> snList = new ArrayList<>();
        ArrayList<String> particularList = new ArrayList<>();
        ArrayList<String> hsnCodeList = new ArrayList<>();
        ArrayList<Integer> quatityList = new ArrayList<>();
        ArrayList<Integer> rateList = new ArrayList<>();
        ArrayList<Integer> amountList = new ArrayList<>();

        for(int x = 0;  x <10; x++) {

            if ( x == 0) {
                // 0 adding data to list
                snList.add(data.getSn0());
                particularList.add(data.getParticular0());
                hsnCodeList.add(data.getHsnCode0());
                quatityList.add(data.getQuatity0());
                rateList.add(data.getRate0());
                amountList.add(data.getRate0() * data.getQuatity0());
            } else if ( x == 1) {
                //1
                snList.add(data.getSn1());
                particularList.add(data.getParticular1());
                hsnCodeList.add(data.getHsnCode1());
                quatityList.add(data.getQuatity1());
                rateList.add(data.getRate1());
                amountList.add(data.getRate1() * data.getQuatity1());
            } else if ( x == 2) {
                //2
                snList.add(data.getSn2());
                particularList.add(data.getParticular2());
                hsnCodeList.add(data.getHsnCode2());
                quatityList.add(data.getQuatity2());
                rateList.add(data.getRate2());
                amountList.add(data.getRate2() * data.getQuatity2());
            } else if ( x == 3) {
                //3
                snList.add(data.getSn3());
                particularList.add(data.getParticular3());
                hsnCodeList.add(data.getHsnCode3());
                quatityList.add(data.getQuatity3());
                rateList.add(data.getRate3());
                amountList.add(data.getRate3() * data.getQuatity3());
            } else if ( x == 4) {
                //4
                snList.add(data.getSn4());
                particularList.add(data.getParticular4());
                hsnCodeList.add(data.getHsnCode4());
                quatityList.add(data.getQuatity4());
                rateList.add(data.getRate4());
                amountList.add(data.getRate4() * data.getQuatity4());
            } else if ( x == 5) {
                //5
                snList.add(data.getSn5());
                particularList.add(data.getParticular5());
                hsnCodeList.add(data.getHsnCode5());
                quatityList.add(data.getQuatity5());
                rateList.add(data.getRate5());
                amountList.add(data.getRate5() * data.getQuatity5());
            } else if ( x == 6) {
                //6
                snList.add(data.getSn6());
                particularList.add(data.getParticular6());
                hsnCodeList.add(data.getHsnCode6());
                quatityList.add(data.getQuatity6());
                rateList.add(data.getRate6());
                amountList.add(data.getRate6() * data.getQuatity6());
            } else if ( x == 7) {
                //7
                snList.add(data.getSn7());
                particularList.add(data.getParticular7());
                hsnCodeList.add(data.getHsnCode7());
                quatityList.add(data.getQuatity7());
                rateList.add(data.getRate7());
                amountList.add(data.getRate7() * data.getQuatity7());
            } else if ( x == 8) {
                //8
                snList.add(data.getSn8());
                particularList.add(data.getParticular8());
                hsnCodeList.add(data.getHsnCode8());
                quatityList.add(data.getQuatity8());
                rateList.add(data.getRate8());
                amountList.add(data.getRate8() * data.getQuatity8());
            } else if ( x == 9) {
                //9
                snList.add(data.getSn9());
                particularList.add(data.getParticular9());
                hsnCodeList.add(data.getHsnCode9());
                quatityList.add(data.getQuatity9());
                rateList.add(data.getRate9());
                amountList.add(data.getRate9() * data.getQuatity9());
            }
        }

        productlist = new productList(snList,particularList,hsnCodeList,quatityList,rateList,amountList);

    }

}