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
import com.pakiza.fortiz.rfid.model.StoreModel;
import com.pakiza.fortiz.rfid.model.WarehouseModel;

import java.util.List;

public class CustomStoreSpinnerAdapter extends ArrayAdapter<StoreModel> {
    public CustomStoreSpinnerAdapter(@NonNull Context context, List<StoreModel> storeModelList) {
        super(context,0, storeModelList);
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
        StoreModel storeModel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(storeModel.getStoreName());

        return convertView;
    }
}

