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
import com.pakiza.fortiz.rfid.model.WarehouseModel;

import java.util.List;


public class AllCompanyAdapter extends ArrayAdapter<CompanyDataModel> {

    private Context context;
    private List<CompanyDataModel> companies;
    public AllCompanyAdapter(@NonNull Context context,  @NonNull List<CompanyDataModel> companies) {
        super(context, 0, companies);
        this.context = context;
        this.companies = companies;
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
        CompanyDataModel company = companies.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.txt_sp);
        textView.setText(company.getCompanyName());

        return convertView;
    }
}
