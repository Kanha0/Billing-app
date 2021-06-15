package com.example.BillingApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerViewHolder extends RecyclerView.ViewHolder {

    private TextView name ;
    private TextView id;
    private ImageView deleteButton;


    public CustomerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name = (TextView) itemView.findViewById(R.id.Dname);
        this.id = (TextView) itemView.findViewById(R.id.customerId);
        this.deleteButton = (ImageView)itemView.findViewById(R.id.remove);
    }

    public TextView getName() {
        return name;
    }

    public TextView getId() {
        return id;
    }
//
    public void setId(TextView id) {
        this.id = id;
    }
//
//
    public ImageView getDeleteButton() {
        return deleteButton;
    }



    static CustomerViewHolder create (ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemrecyclerview,parent, false);
        return new CustomerViewHolder(view);
    }

    public void bindCustomer(String name, int Id){
        this.name.setText(name);
        this.id.setText(Integer.toString(Id));
    }
}
