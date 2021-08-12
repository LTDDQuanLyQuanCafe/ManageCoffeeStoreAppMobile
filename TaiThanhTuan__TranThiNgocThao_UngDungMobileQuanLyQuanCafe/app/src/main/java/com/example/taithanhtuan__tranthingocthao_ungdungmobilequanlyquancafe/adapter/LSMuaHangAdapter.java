package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.DonGiaoHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.R;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListener;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListenerLS;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class LSMuaHangAdapter  extends RecyclerView.Adapter<LSMuaHangAdapter.PopularViewHolder>  {


    private final Context context;
    private final ArrayList<DonGiaoHang> popularList;
    private OnClickListenerLS listener;

    public LSMuaHangAdapter(Context context, ArrayList<DonGiaoHang> popularList) {
        this.context = context;
        this.popularList = popularList;
    }

    public LSMuaHangAdapter(Context context, ArrayList<DonGiaoHang> popularList, OnClickListenerLS listener) {
        this.context = context;
        this.popularList = popularList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public LSMuaHangAdapter.PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_ls_muahang, null);
        return new LSMuaHangAdapter.PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LSMuaHangAdapter.PopularViewHolder holder, int position) {
        DonGiaoHang dalThucDon = popularList.get(position);

        holder.lbNgay.setText(dalThucDon.getNGAYGIAO());
        holder.lbTongTien.setText(dalThucDon.getTRANGTHAI());
        holder.lbDiaChi.setText(dalThucDon.getDIACHIGIAO());
        if(dalThucDon.getTRANGTHAI() == "true"){
            holder.lbTrangThai.setText("Đã giao hàng");
        }else {
            holder.lbTrangThai.setText("Chưa hoàn thành");
        }


        // for image we add Glide library dependency for image fetching from server
        holder.dalThucDon = popularList.get(position);
    }


    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder{

        DonGiaoHang dalThucDon ;
        public TextView lbNgay, lbTrangThai, lbTongTien, lbDiaChi;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            lbNgay=itemView.findViewById(R.id.txt_NgayDH);
            lbTrangThai=itemView.findViewById(R.id.txt_TrangThai);
            lbTongTien=itemView.findViewById(R.id.txt_price);
            lbDiaChi=itemView.findViewById(R.id.txt_DiaChi);
            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.ItemClick(dalThucDon);
                }
            });
        }
    }
}

