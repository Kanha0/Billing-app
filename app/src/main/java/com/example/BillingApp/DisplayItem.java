package com.example.BillingApp;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayItem# newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayItem extends Fragment implements itemCLicked, IonFragmentBackpressed {

   private static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private FloatingActionButton addItem;

//    private Button addItem;
   private RecyclerView rv;
   private ViewModelData  viewmodel;
   private CustomerDetails customerDetails;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_item, container, false);

//        addItem = (Button) view.findViewById(R.id.addItem);
          addItem = (FloatingActionButton) view.findViewById(R.id.addItem);

        rv = (RecyclerView) view.findViewById(R.id.fragmentRecyclerView);


        addItem.setOnClickListener(v -> {
            Intent intent = new Intent(getContext() , Bill.class);
//            startActivity(intent);
            intent.putExtra("currentCustomer" , customerDetails.getId());
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);

        });

        // recyclerView setting;
//        final ListAdapter adapter = new ListAdapterData(new ListAdapterData.DataDiff(), MainActivity.this);
        final ListAdapter adapter = new ListAdapterData(new ListAdapterData.DataDiff(), DisplayItem.this);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        Application application = this.getActivity().getApplication();
        //view model setting
        viewmodel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelData.class);

//        viewmodel = new ViewModelProvider(this).get(ViewModelData.class);
        viewmodel.getAlldata().observe(DisplayItem.this,data -> {
            List<DataTable> finaldata = new ArrayList<>();
            for(int i = 0 ; i < data.size() ; i++){
                if (data.get(i).getId() == this.customerDetails.getId()){
                    finaldata.add(data.get(i));
                }
            }

            adapter.submitList(finaldata);
        } );


        return view;
    }

    public DisplayItem(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
//        this.viewmodel = viewmodeldata;

        // Required empty public constructor
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
//            System.out.println("saved");
//            System.out.println(requestCode);
//            System.out.println(resultCode);

            DataTable finaldata = (DataTable) data.getSerializableExtra("Dataobject");


//            viewmodel.insert(finaldata);
            Toast.makeText(getContext(), "Added Successfully", Toast.LENGTH_LONG).show();

        } else {
//            System.out.println("not saved");
//            System.out.println(requestCode);
//            System.out.println(resultCode);
            Toast.makeText(getContext(), "Empty not saved", Toast.LENGTH_LONG).show();
        }
    }


//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public DisplayItem() {
        // Required empty public constructor
    }

    @Override
    public void onDeleteClicked(DataTable data) {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(getContext());
        myAlert.setTitle("Delete");
        myAlert.setMessage("Are you Sure ?");
        myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewmodel.delete(data);
            }
        });

        myAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        myAlert.setCancelable(false);
        myAlert.show();    }

    @Override
    public void onNameClicked(DataTable data) {
        Intent replyIntent = new Intent(getContext() , quotation.class);
        replyIntent.putExtra("currentItem", (Serializable) data);
        replyIntent.putExtra("currentCustomer", (Serializable) this.customerDetails);
        startActivity(replyIntent);

    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment DisplayItem.
//    */
//    // TODO: Rename and change types and number of parameters
//    public static DisplayItem newInstance(String param1, String param2) {
//        DisplayItem fragment = new DisplayItem();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        // recyclerView setting;
////        final ListAdapter adapter = new ListAdapterData(new ListAdapterData.DataDiff(), MainActivity.this);
//        final ListAdapter adapter = new ListAdapterData(new ListAdapterData.DataDiff(), DisplayItem.this);
//
//        rv.setAdapter(adapter);
//        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        Application application = this.getActivity().getApplication();
//        //view model setting
//        viewmodel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelData.class);
//
////        viewmodel = new ViewModelProvider(this).get(ViewModelData.class);
//        viewmodel.getAlldata().observe(DisplayItem.this,data -> {
//            adapter.submitList(data);
//        } );
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public boolean onBackpressed() {
        return true;
    }
}