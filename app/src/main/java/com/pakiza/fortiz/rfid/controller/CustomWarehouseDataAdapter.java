package com.pakiza.fortiz.rfid.controller;

import static android.content.Context.AUDIO_SERVICE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.model.WareHouseWiseDataResponseModel;
import com.pakiza.fortiz.rfid.view.ProductEntryActivity;
import com.rscja.deviceapi.RFIDWithUHFUART;
import com.rscja.deviceapi.entity.UHFTAGInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomWarehouseDataAdapter extends RecyclerView.Adapter<CustomWarehouseDataAdapter.CustomWarehouseDataViewHolder>{

    private final ArrayList<WareHouseWiseDataResponseModel> dataList;
    Context mContext;

    public CustomWarehouseDataAdapter(ArrayList<WareHouseWiseDataResponseModel> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public CustomWarehouseDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_warehouse_data, parent, false);
        return new CustomWarehouseDataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomWarehouseDataViewHolder holder, int position) {

        WareHouseWiseDataResponseModel wareHouseWiseDataResponseModel = dataList.get(position);
        holder.txtSn.setText(String.valueOf(position+1));
        holder.txtProductName.setText(wareHouseWiseDataResponseModel.getProductName());
        holder.txtQnty.setText(wareHouseWiseDataResponseModel.getQuantity());

        boolean isClickable = wareHouseWiseDataResponseModel.getRfIdStatus().equals("error");
        if(isClickable){
//            holder.txtRfid.setBackground(ResourcesCompat.getDrawable(mContext,R.drawable.error_rfid));
//            holder.txtRfid.setBackground(mContext.getDrawable(R.drawable.error_rfid));
            holder.txtRfid.setText("SCAN");
        }else{
//            holder.txtRfid.setBackground(mContext.getDrawable(R.drawable.success_rfid));
            holder.txtRfid.setText("DONE");
        }
////        holder.txtRfid.setBackground();
        holder.txtRfid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClickable){
                    Intent i = new Intent(mContext, ProductEntryActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("item",wareHouseWiseDataResponseModel);
                    i.putExtras(b);
                    mContext.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CustomWarehouseDataViewHolder extends RecyclerView.ViewHolder{

        TextView txtSn, txtProductName, txtQnty, txtRfid;
        public CustomWarehouseDataViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSn = itemView.findViewById(R.id.txt_sn);
            txtProductName = itemView.findViewById(R.id.txt_product_name);
            txtQnty = itemView.findViewById(R.id.txt_qnty);
            txtRfid = itemView.findViewById(R.id.txt_rfid);

            setIsRecyclable(false);
        }
    }


}
