package com.example.BillingApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class productAdapter extends RecyclerView.Adapter<productViewHolder>   {
    ArrayList<products> items = new ArrayList<>();
    private productList productlist = new productList();
    private productItemclicked listiner;

    public productAdapter(productItemclicked listiner) {
        this.listiner = listiner;
    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productrecyclerview, parent,false);
        final productViewHolder viewHolder = new productViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listiner.onItemClicked(items.get(viewHolder.getAdapterPosition()));
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull productViewHolder holder, int position) {
//        ArrayAdapter<CharSequence> padapter = ArrayAdapter.createFromResource(productAdapter.class,R.array.praticular_array, android.R.layout.simple_spinner_item);
//        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        holder.particular.setAdapter(padapter);

        products currentitem = items.get(position);
        String p = holder.particular.getSelectedItem().toString();
        String h = holder.hsncode.getText().toString();
        int q = Integer.parseInt(holder.rate.getText().toString());
        int r = Integer.parseInt(holder.quantity.getText().toString());



//        holder.sn.setText(position + 1);// depend up on spinner  adapter;
        currentitem.setParticular(p);
        currentitem.setHsnCode(h);
        currentitem.setRate(q);
        currentitem.setQuatity(r);

//        productlist.updatelist(p,h,q,r);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public productList update(){
// to recall recycler View adapters methods;
        notifyDataSetChanged();

        return productlist;
    }
}

class productViewHolder extends RecyclerView.ViewHolder{

    public productViewHolder(@NonNull View itemView) {
        super(itemView);
    }

//    TextView sn = (TextView) itemView.findViewById(R.id.Lparticulars);
    Spinner particular = (Spinner) itemView.findViewById(R.id.Lparticulars);
    EditText hsncode = (EditText) itemView.findViewById(R.id.Lhsncode);
    EditText rate = (EditText) itemView.findViewById(R.id.Lrate);
    EditText quantity = (EditText) itemView.findViewById(R.id.Lquantity);
}

// caller is made by the interface
interface productItemclicked{

    void onItemClicked(products item);
}


