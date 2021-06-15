package com.example.BillingApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class displayData extends AppCompatActivity {

   private ViewPager viewpager;
   private displayCustomer displayCustomer;
   private DisplayItem displayitem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        Intent intent = getIntent();
        CustomerDetails currentCustomer = (CustomerDetails) intent.getSerializableExtra("currentCustomer");

        displayCustomer = new displayCustomer(currentCustomer);
        displayitem = new DisplayItem(currentCustomer);

        Toast.makeText(this,"Swipe ---> for Bill's & Quotations", Toast.LENGTH_SHORT).show();


        viewpager.setAdapter(new pagerAdapter(getSupportFragmentManager()));

    }


    // inner adapter class
    class pagerAdapter extends FragmentPagerAdapter{

        String d[] = {"Customer Details", "Item Details"};

        public pagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public pagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            if (position == 0){

                return displayCustomer;

            } else if (position == 1){
                return displayitem;
            }
            return null;
        }

        @Override
        public int getCount() {
            return d.length;
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this,MainActivity.class);
        finish();
        startActivity(intent);
    }

}

