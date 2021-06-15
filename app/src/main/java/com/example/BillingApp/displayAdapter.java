package com.example.BillingApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.recyclerview.widget.RecyclerView.*;

public class  displayAdapter extends RecyclerView.Adapter<displayViewHolder>{
    private ArrayList<products> productsArray ;

    //constructer


    public displayAdapter(ArrayList<products> productsArray) {
        this.productsArray = productsArray;
    }

    @NonNull
    @Override
    public displayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        productsArray = new ArrayList<>();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.displayproduct, parent,false);
        final displayViewHolder viewHolder = new displayViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull displayViewHolder holder, int position) {
        products currentproduct = productsArray.get(position);
// by product class
        holder.sn.setText(Integer.toString(currentproduct.getSn()));
        holder.particular.setText(currentproduct.getParticular());
        holder.hsncode.setText(currentproduct.getHsnCode());
        holder.rate.setText(Integer.toString(currentproduct.getRate()));
        holder.quantity.setText(Integer.toString(currentproduct.getQuatity()));
        holder.amount.setText(Integer.toString(currentproduct.getAmount()));

//        holder.sn.setText(productlist.getSnList().get(i));
//        holder.particular.setText(productlist.getParticularList().get(i));
//        holder.hsncode.setText(productlist.getHsnCodeList().get(i));
//        holder.rate.setText(productlist.getRateList().get(i));
//        holder.quantity.setText(productlist.getQuatityList().get(i));
//        holder.amount.setText(productlist.getAmountList().get(i));


    }

    @Override
    public int getItemCount() {
        return productsArray.size();
    }
}

class displayViewHolder extends ViewHolder{

    public  displayViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    TextView sn = (TextView) itemView.findViewById(R.id.psn);
    TextView particular = (TextView) itemView.findViewById(R.id.pparticulars);
    TextView hsncode = (TextView) itemView.findViewById(R.id.phsncode);
    TextView rate = (TextView) itemView.findViewById(R.id.prate);
    TextView quantity = (TextView) itemView.findViewById(R.id.pquantity);
    TextView amount = (TextView) itemView.findViewById(R.id.pamount);

}
