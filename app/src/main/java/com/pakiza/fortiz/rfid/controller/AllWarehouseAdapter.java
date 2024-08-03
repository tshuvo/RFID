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
import com.pakiza.fortiz.rfid.model.CompanyDataModel;
import com.pakiza.fortiz.rfid.model.WarehouseDataModel;

import java.util.List;

public class AllWarehouseAdapter extends ArrayAdapter<WarehouseDataModel> {
    private final Context context;
    private final List<WarehouseDataModel> warehouses;
    public AllWarehouseAdapter(@NonNull Context context, @NonNull List<WarehouseDataModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.warehouses = objects;
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
        WarehouseDataModel warehouseDataModel = warehouses.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.txt_sp);
        textView.setText(warehouseDataModel.getShortName());

        return convertView;
    }
}

