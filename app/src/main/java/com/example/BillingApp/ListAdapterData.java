package com.example.BillingApp;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
public class  ListAdapterData extends ListAdapter<DataTable,DataViewHolder> {

    itemCLicked listiner;

    protected ListAdapterData(@NonNull DiffUtil.ItemCallback<DataTable> diffCallback, itemCLicked listiner) {
        super(diffCallback);
        this.listiner = listiner;
    }


    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final DataViewHolder holder = DataViewHolder.create(parent);

        holder.getDeleteButton().setOnClickListener((view) -> listiner.onDeleteClicked(getItem(holder.getAdapterPosition())));
        holder.getName().setOnClickListener((view) -> listiner.onNameClicked(getItem(holder.getAdapterPosition())));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        DataTable current = getItem(position);

        holder.bindData(current.getDate(), current.getId());
    }
    /*
    in bellow class we are checking wheather the two same inputs are same with respect to DATE of not*/
    static class DataDiff extends DiffUtil.ItemCallback<DataTable>{

        @Override
        public boolean areItemsTheSame(@NonNull DataTable oldItem, @NonNull DataTable newItem) {
//            return oldItem == newItem;
            return false;

        }

        @Override
        public boolean areContentsTheSame(@NonNull DataTable oldItem, @NonNull DataTable newItem) {
            return false;
//            return oldItem.getDate().equals(newItem.getDate());

        }
    }

}

interface itemCLicked {
    void onDeleteClicked(DataTable data);
    void onNameClicked(DataTable data);
}