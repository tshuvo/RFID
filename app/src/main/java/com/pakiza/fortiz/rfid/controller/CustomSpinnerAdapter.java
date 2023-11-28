package com.pakiza.fortiz.rfid.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.model.WarehouseModel;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<WarehouseModel> {
    public CustomSpinnerAdapter(@NonNull Context context, List<WarehouseModel> warehouseList) {
        super(context,0, warehouseList);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        WarehouseModel warehouse = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(warehouse.getWareHouseName());

        return convertView;
    }
}
