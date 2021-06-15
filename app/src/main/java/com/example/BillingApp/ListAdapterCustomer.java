package com.example.BillingApp;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ListAdapterCustomer  extends ListAdapter<CustomerDetails,CustomerViewHolder> {

    CustomerCLicked listiner;

    protected ListAdapterCustomer(@NonNull DiffUtil.ItemCallback<CustomerDetails> diffCallback, CustomerCLicked listiner) {
        super(diffCallback);
        this.listiner = listiner;
    }

    @NonNull 
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final CustomerViewHolder holder = CustomerViewHolder.create(parent);

//        holder.getDeleteButton().setOnClickListener((view) -> listiner.onDeleteClicked(getItem(holder.getAdapterPosition())));
//        holder.getName().setOnClickListener((view) -> listiner.onNameClicked(getItem(holder.getAdapterPosition())));
//
        holder.getDeleteButton().setOnClickListener((view) -> listiner.onDeleteClicked(getItem((holder.getAdapterPosition()))));
        holder.getName().setOnClickListener((view) -> listiner.onNameClicked(getItem(holder.getAdapterPosition())));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        CustomerDetails current = getItem(position);

        holder.bindCustomer(current.getName(), current.getId());
    }

    /*
  in bellow class we are checking wheather the two same inputs are same with respect to name of not*/
    static class DataDiff extends DiffUtil.ItemCallback<CustomerDetails>{

        @Override
        public boolean areItemsTheSame(@NonNull CustomerDetails oldItem, @NonNull CustomerDetails newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CustomerDetails oldItem, @NonNull CustomerDetails newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

}

interface CustomerCLicked {
    void onDeleteClicked(CustomerDetails data);
    void onNameClicked(CustomerDetails data);
}