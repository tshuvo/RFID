package com.pakiza.fortiz.rfid.controller;

import static android.content.Context.AUDIO_SERVICE;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pakiza.fortiz.rfid.R;
import com.pakiza.fortiz.rfid.model.WareHouseWiseDataResponseModel;
import com.pakiza.fortiz.rfid.model.WarehouseErrorModel;
import com.rscja.deviceapi.RFIDWithUHFUART;
import com.rscja.deviceapi.entity.UHFTAGInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomWarehouseErrorDataAdapter extends RecyclerView.Adapter<CustomWarehouseErrorDataAdapter.CustomWarehouseErrorDataViewHolder>{

    public RFIDWithUHFUART mReader;
    public UHFTAGInfo uhftagInfo = new UHFTAGInfo();
    HashMap<Integer, Integer> soundMap = new HashMap<Integer, Integer>();
    private AudioManager am;
    private SoundPool soundPool;
    private float volumnRatio;
    private ArrayList<WarehouseErrorModel> dataList;
    Context mContext;

    public CustomWarehouseErrorDataAdapter(ArrayList<WarehouseErrorModel> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
        initSound();
        initUHF();
    }

    @NonNull
    @Override
    public CustomWarehouseErrorDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_error_warehouse, parent, false);
        return new CustomWarehouseErrorDataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomWarehouseErrorDataViewHolder holder, int position) {
        WarehouseErrorModel warehouseErrorModel = dataList.get(position);
        holder.txtSn.setText(String.valueOf(position));
        holder.txtRfid.setText(warehouseErrorModel.getRevDetailId());
        holder.txtStatus.setText(warehouseErrorModel.getRfidStatus());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CustomWarehouseErrorDataViewHolder extends RecyclerView.ViewHolder{

        TextView txtSn, txtRfid, txtStatus;
        public CustomWarehouseErrorDataViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSn = itemView.findViewById(R.id.txt_error_sn);
            txtRfid = itemView.findViewById(R.id.txt_error_rfid);
            txtStatus = itemView.findViewById(R.id.txt_error_status);

            setIsRecyclable(false);
        }
    }

    public void playSound(int id) {
        float audioMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // 返回当前AudioManager对象的最大音量值
        float audioCurrentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);// 返回当前AudioManager对象的音量值
        volumnRatio = audioCurrentVolume / audioMaxVolume;
        try {
            soundPool.play(soundMap.get(id), volumnRatio,
                    volumnRatio,
                    1,
                    0,
                    1
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initUHF() {

//        mActionBar = mContext.getSupportActionBar();
//        mActionBar.setDisplayShowTitleEnabled(true);
//        mActionBar.setDisplayShowHomeEnabled(true);
//        mActionBar.setDisplayHomeAsUpEnabled(true);
//        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        try {
            mReader = RFIDWithUHFUART.getInstance();
        } catch (Exception ex) {

//            toastMessage(ex.getMessage());
            return;
        }

        if (mReader != null) {
            new InitTask().execute();
        }
    }

    public class InitTask extends AsyncTask<String, Integer, Boolean> {
        ProgressDialog mypDialog;

        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub

            return mReader.init();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            mypDialog.cancel();
//            if (!result) {
//                Toast.makeText(mContext, "init fail", Toast.LENGTH_SHORT).show();
//            }
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            mypDialog = new ProgressDialog(mContext);
            mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mypDialog.setMessage("wait for configuring device.");
            mypDialog.setCanceledOnTouchOutside(false);
            mypDialog.show();
        }
    }


    private void initSound() {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
        soundMap.put(1, soundPool.load(mContext, R.raw.barcodebeep, 1));
        soundMap.put(2, soundPool.load(mContext, R.raw.serror, 1));
        am = (AudioManager) mContext.getSystemService(AUDIO_SERVICE);// 实例化AudioManager对象
    }

    private void releaseSoundPool() {
        if(soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }

}
