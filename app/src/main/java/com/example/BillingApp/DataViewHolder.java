package com.example.BillingApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DataViewHolder extends RecyclerView.ViewHolder {

    private TextView name ;
    private TextView id;
    private ImageView deleteButton;


    public DataViewHolder( View itemView) {
        super(itemView);
        this.name = (TextView) itemView.findViewById(R.id.Dname);
        this.id = (TextView) itemView.findViewById(R.id.customerId);
        this.deleteButton = (ImageView)itemView.findViewById(R.id.remove);
    }

    public TextView getName() {
        return name;
    }
    public TextView getDate(){ return name; }

    public TextView getId() {
        return id;
    }

    public void setId(TextView id) {
        this.id = id;
    }


    public ImageView getDeleteButton() {
        return deleteButton;
    }




    public void bindData(String name, int Id){
        this.name.setText(name);
        this.id.setText(Integer.toString(Id));
    }

    static DataViewHolder create (ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemrecyclerview,parent, false);
        return new DataViewHolder(view);
    }
}

