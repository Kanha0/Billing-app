package com.example.BillingApp;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PdfGenerator {

    private String[] informationArray = new String[]{"Name              : ", "Address         : " , "Contact No. :", "GSTIN             :"};
    private String[] indexArray = new String[]{" S.N.", "PARTICULARS", "HSN Code", "QUANTITY", "RATE", "AMOUNT"};
    private String[] bankDetails = new String[]{"Bank :        ","A/C. : 000000000000000","IFSC Code : 000000","Branch : ."};
    private String[] amountDetails;
    private String[] terms = new String[]{"1. Goods once sold will not be taken back.","2. Interest @ 24% p.a. will be charged if the payment ","is not made with in the stipulated time",
            "3. Subject to 'Nagpur' Jurisdiction only.","4. Cheque Bounce Charge 1000/-"};

    private String fpath;
    private float textsize;
    private Bitmap logo , scaledLogo;

    private DataTable listdata;
    private CustomerDetails customerdata;
    private Resources r;
    private productList productlist;
    private Activity a;
    private PdfDocument mypdf ;

    private static int invoice = 1;


    private float Total;
    private float cgst;
    private float sgst;
    private float igst;
    private float Amount;
    private int GrandTotal;
    private String type;


    public PdfGenerator(DataTable listdata, CustomerDetails customerdata, Resources r, Activity a, productList p, String type) {
        this.listdata = listdata;
        this.customerdata = customerdata;
        this.r = r;
        this.a = a;
        this.productlist = new productList(p.getSnList(),p.getParticularList(),p.getHsnCodeList(),p.getQuatityList(),p.getRateList(),p.getAmountList());
        this.Total = 0.0f;
        this.cgst = 0.0f;
        this.sgst = 0.0f;
        this.igst = 0.0f;
        this.Amount = 0.0f;
        this.GrandTotal = 0;
        this.type = type;

        calculation();

        // add your logo
//        logo = BitmapFactory.decodeResource(r,R.drawable.logo);
        scaledLogo = Bitmap.createScaledBitmap(logo,90,90,false);

    }
    

    public void createPdf( FileOutputStream fileOutputStream ){
        FileOutputStream fileOutput = fileOutputStream;
            mypdf = new PdfDocument();
            Paint mypaint = new Paint();
            String[] Customerdetail = new String[]{customerdata.getName(), customerdata.getAddress(), customerdata.getNumber(), customerdata.getGSTIN()};
            String[] totalDetail = new String[]{Float.toString(Total), Float.toString(cgst), Float.toString(sgst), Float.toString(igst), Float.toString(Amount), Integer.toString(GrandTotal)};
            String[] amountDetails = new String[]{" Total", "CGST @ " + listdata.getCgst() + "% ","SGST @ " + listdata.getSgst() +"% ","IGST @ " + listdata.getIgst() + "% ","AMOUNT","G.TOTAL"};
            PdfDocument.PageInfo mypageInfo1 = new PdfDocument.PageInfo.Builder(400 ,600, 1).create();
            PdfDocument.Page mypage1 = mypdf.startPage(mypageInfo1);
            Canvas canvas = mypage1.getCanvas();
//Logo

            canvas.drawBitmap(scaledLogo,10,10,mypaint);

//Quotation
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(1.0f);
            mypaint.setTextScaleX(1.5f);
            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setTextSize(9.0f);
            if(type == "Q"){
                canvas.drawText("Quotation N0 : " + invoice , mypageInfo1.getPageWidth()/2  , 15,mypaint);
            } else if (type == "B"){
                canvas.drawText("Bill Invoice : "+ invoice , mypageInfo1.getPageWidth()/2  , 15,mypaint);

            }
            mypaint.setTextScaleX(1.0f);

//Neo AgroTech
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTextSize(28.0f);
            canvas.drawText("Company Name",110,40,mypaint);
            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setStrokeWidth(0f);

// Address Nilima...
            mypaint.setTextScaleX(1.5f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTextSize(8.5f);
            canvas.drawText("Address Line 1" ,110 , 55, mypaint);
            canvas.drawText("address Line 2",110 ,65,mypaint);
            mypaint.setTextScaleX(1.0f);

// Modile Number
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTextSize(10.0f);
            canvas.drawText("Mob : 99261xxxxx",110 ,80,mypaint);

// Gstin 27AASFN1760G1Z0
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(1.0f);
            mypaint.setTextScaleX(1.2f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTextSize(10f);
            canvas.drawText("GSTIN : 0000000000000000",11,115,mypaint);
            mypaint.setTextScaleX(1.0f);
            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setStrokeWidth(0f);

//Date
            mypaint.setTextScaleX(1.5f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTextSize(9f);
            canvas.drawText("Date : ",mypageInfo1.getPageWidth()/2 + 15,115,mypaint);
            // date from data class
        canvas.drawText(listdata.getDate(),mypageInfo1.getPageWidth()/2 + 60,115,mypaint);
        //Date dash Line

            canvas.drawLine(mypageInfo1.getPageWidth()/2 + 50,116,mypageInfo1.getPageWidth() - 10 ,116,mypaint);
            mypaint.setTextScaleX(1.0f);


            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(1.0f);

            canvas.drawLine(10 ,120,mypageInfo1.getPageWidth() - 10 ,120,mypaint);

            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);
//Name, Address, Gstin
            mypaint.setTextScaleX(1.5f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTextSize(9f);
            float scalex = 11;
            float scaley = 135;
            for(int i=0; i<informationArray.length; i++){
                canvas.drawText(informationArray[i], scalex, scaley,mypaint);
                scaley += 18;
            }

            //Customer Details form data class
        scalex = scalex + 90;
        scaley = 135;
        for(int i=0; i<informationArray.length; i++){
            canvas.drawText(Customerdetail[i], scalex, scaley,mypaint);
            scaley += 18;
        }

            mypaint.setTextScaleX(1.0f);

            scaley = scaley - 10;
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(1.0f);
            canvas.drawLine(10,scaley  , mypageInfo1.getPageWidth() - 10, scaley ,mypaint);
            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setStrokeWidth(1.5f);

// rectangles
            scaley = scaley + 5;
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(1);
            canvas.drawRect(10, scaley ,mypageInfo1.getPageWidth() - 10,scaley + 20,mypaint);

            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

// S.N> , Particulars , Hsn COde , quantity, RAte ,Amount
            mypaint.setTextScaleX(1.3f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            mypaint.setTextSize(7.0f);
            scalex = 13;
            float top = scaley ;
            float bottom = scaley + 20;

            //sn
            canvas.drawText(indexArray[0],scalex - 1f,scaley + 10f ,mypaint);
            canvas.drawLine(scalex + 25f, top, scalex + 25f, bottom, mypaint);

            //particular
            canvas.drawText(indexArray[1],scalex + 35f,scaley + 10f ,mypaint);
            canvas.drawLine(scalex + 175f, top, scalex + 175f, bottom, mypaint);

            //hsn code
            canvas.drawText(indexArray[2],scalex + 177f,scaley + 10f ,mypaint);
            canvas.drawLine(scalex + 220f, top, scalex + 220f, bottom, mypaint);

            // quantity
            canvas.drawText(indexArray[3],scalex + 223f,scaley + 10f ,mypaint);
            canvas.drawLine(scalex + 275f, top, scalex + 275f, bottom, mypaint);

            // Rate
            canvas.drawText(indexArray[4],scalex + 278f,scaley + 10f ,mypaint);
            canvas.drawLine(scalex + 315f, top, scalex + 315f, bottom, mypaint);

            //amount
            canvas.drawText(indexArray[5],scalex + 328f,scaley + 10f ,mypaint);
//            canvas.drawLine(scalex + 30f, top, scalex + 30f, bottom, mypaint);
            mypaint.setTextScaleX(1.0f);

            scaley = bottom;
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.ITALIC));

//product details

        bottom = bottom + 15;
        for(int i = 0 ; i < productlist.getParticularList().size(); i++){

            if(productlist.getAmountList().get(i) != 0) {
                //sn
                canvas.drawText(Integer.toString(productlist.getSnList().get(i)), scalex - 1f, scaley + 10f, mypaint);
                canvas.drawLine(scalex + 25f, top, scalex + 25f, bottom, mypaint);

                //particular
                canvas.drawText(productlist.getParticularList().get(i), scalex + 35f, scaley + 10f, mypaint);
                canvas.drawLine(scalex + 175f, top, scalex + 175f, bottom, mypaint);

                //hsn code
                canvas.drawText(productlist.getHsnCodeList().get(i), scalex + 177f, scaley + 10f, mypaint);
                canvas.drawLine(scalex + 220f, top, scalex + 220f, bottom, mypaint);

                // quantity
                canvas.drawText(Integer.toString(productlist.getQuatityList().get(i)), scalex + 223f, scaley + 10f, mypaint);
                canvas.drawLine(scalex + 275f, top, scalex + 275f, bottom, mypaint);

                // Rate
                canvas.drawText(Integer.toString(productlist.getRateList().get(i)), scalex + 278f, scaley + 10f, mypaint);
                canvas.drawLine(scalex + 315f, top, scalex + 315f, bottom, mypaint);

                //amount
                canvas.drawText(Integer.toString(productlist.getAmountList().get(i)), scalex + 328f, scaley + 10f, mypaint);
//            canvas.drawLine(scalex + 30f, top, scalex + 30f, bottom, mypaint);

                scaley = scaley + 15;
                bottom = bottom + 15;
            }

        }



// product details ends only scaley increases

            // line bellow products
            scaley = scaley + 20;
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(1);
            canvas.drawLine(10,scaley , mypageInfo1.getPageWidth() -10, scaley, mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);


// total and gst detils

            float Rtop = scaley +10;
            float Rbottom = scaley + 30;
            mypaint.setTextSize(8);
            for(int i = 0; i < amountDetails.length ; i++ ) {
                mypaint.setStyle(Paint.Style.STROKE);
                mypaint.setStrokeWidth(1.0f);
                canvas.drawRect(233, Rtop, mypageInfo1.getPageWidth() - 10, Rbottom, mypaint);
                mypaint.setStrokeWidth(0);
                mypaint.setStyle(Paint.Style.FILL);
                canvas.drawText(amountDetails[i], 238, Rtop + 10f,  mypaint);
                //details from calculation method
                canvas.drawText(totalDetail[i], 238 + 100, Rtop + 10f,  mypaint);

                // enter results
                Rtop = Rbottom;
                Rbottom += 20;
            }

            canvas.drawLine(328,scaley + 10, 328,Rbottom - 20,mypaint);




// Bank Details
            scaley = scaley + 10;
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(0.7f);
            mypaint.setTextScaleX(1.2f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTextSize(9f);
            canvas.drawText("Bank Details :",11,scaley ,mypaint);
            mypaint.setTextScaleX(1.0f);
            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setStrokeWidth(0f);


            mypaint.setTextScaleX(1.2f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTextSize(8.0f);
            for(int i =0; i < bankDetails.length; i++) {
                scaley += 9;
                canvas.drawText(bankDetails[i], 11, scaley, mypaint);
            }
            mypaint.setTextScaleX(1.0f);

//Terms & conditions
            scaley = scaley + 20;
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(0.5f);
            mypaint.setTextScaleX(1.5f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTextSize(7.0f);
            canvas.drawText("Terms & Conditions :",11,scaley ,mypaint);
            mypaint.setTextScaleX(1.0f);
            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setStrokeWidth(0f);

            mypaint.setTextScaleX(1.2f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTextSize(6.0f);
            for(int i =0; i < terms.length; i++) {
                scaley += 8;
                canvas.drawText(terms[i], 11, scaley, mypaint);
            }
            mypaint.setTextScaleX(1.0f);


// Last Line

            canvas.drawText("For Neo Agrotech", 350, mypageInfo1.getPageHeight()-20f,mypaint);




//*********************************************************************************

            mypdf.finishPage(mypage1);


            try{
                mypdf.writeTo(fileOutput);
                Toast.makeText(a, "Pdf generated", Toast.LENGTH_SHORT).show();
            } catch(IOException e){
                e.printStackTrace();
                Toast.makeText(a, "Pdf generated failed", Toast.LENGTH_SHORT).show();
            }
//
            mypdf.close();
            this.invoice++;

    }

    private void calculation(){

        ArrayList<Integer> temp = new ArrayList<>();
        temp = productlist.getAmountList();
        int a;

        for(int i = 0 ; i < temp.size() ; i++ ){
            this.Total = this.Total + temp.get(i);
        }

        this.cgst = (float) this.Total * (listdata.getCgst()/100);
        this.sgst = (float) this.Total * (listdata.getSgst()/100);
        this.igst = (float) this.Total * (listdata.getIgst()/100);

        this.Amount = (float) this.cgst + this.igst + this.sgst + this.Total;

        a = (int) this.Amount ;


        if(a != this.Amount){
            this.GrandTotal = (int) this.Amount + 1;
        } else {
            this.GrandTotal = (int) this.Amount;
        }

    }
}


