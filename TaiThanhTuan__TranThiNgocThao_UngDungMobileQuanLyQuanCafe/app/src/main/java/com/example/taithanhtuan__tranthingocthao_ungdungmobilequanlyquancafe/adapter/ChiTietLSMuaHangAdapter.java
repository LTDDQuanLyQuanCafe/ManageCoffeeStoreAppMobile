package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.CTGiaoHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.DonGiaoHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.R;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListenerLS;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ChiTietLSMuaHangAdapter extends RecyclerView.Adapter<ChiTietLSMuaHangAdapter.PopularViewHolder>  {
    private Context context;
    private ArrayList<CTGiaoHang> popularList;
    private OnClickListenerLS listener;


    public ChiTietLSMuaHangAdapter(Context context, ArrayList<CTGiaoHang> popularList, OnClickListenerLS listener) {
        this.context = context;
        this.popularList = popularList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ChiTietLSMuaHangAdapter.PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_chitiet_ls_muahang, null);
        return new ChiTietLSMuaHangAdapter.PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietLSMuaHangAdapter.PopularViewHolder holder, int position) {
        CTGiaoHang dalThucDon = popularList.get(position);

   
        holder.lbTen.setText(dalThucDon.getTENMON());
        holder.lbGia.setText( dalThucDon.getDONGIA());
        holder.lbSL.setText(dalThucDon.getSOLUONGGIAO());
        holder.imgHinh.setImageBitmap(this.converStringToBitmapFromAccess(dalThucDon.getHINHANH()));
        holder.imgHinh.setScaleType(ImageView.ScaleType.FIT_CENTER);

        // for image we add Glide library dependency for image fetching from server
        holder.dalThucDon = popularList.get(position);
    }


    public Bitmap converStringToBitmapFromAccess(String filename){
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder{

        CTGiaoHang dalThucDon ;
        public ImageView imgHinh;
        public TextView lbTen, lbGia, lbSL;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHinh=itemView.findViewById(R.id.img_Hinh);
            lbTen=itemView.findViewById(R.id.txt_name);
            lbGia=itemView.findViewById(R.id.txt_price);
            lbSL=itemView.findViewById(R.id.txt_SL);
        }
    }

}


