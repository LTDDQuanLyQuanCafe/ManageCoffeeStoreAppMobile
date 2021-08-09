package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.LoaiTD;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.R;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.TrangChuActivity;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListenerLoaiTD;

import java.util.List;

public class LoaiTDAdapter extends RecyclerView.Adapter<LoaiTDAdapter.UserItemViewHolder> {
    List<LoaiTD> loaiTDList;
    Context context;
    OnClickListenerLoaiTD listenerLoaiTD;


    public LoaiTDAdapter(List<LoaiTD> users, Context c, OnClickListenerLoaiTD listenerLoaiTD) {
        this.loaiTDList = users;
        this.context = c;
        this.listenerLoaiTD = listenerLoaiTD;
    }

    @Override
    public int getItemCount() {
        return loaiTDList.size();
    }

    @Override
    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_1dong_options,null);
        return new UserItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        LoaiTD options = loaiTDList.get(position);

        com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.LoaiTD loaiTD = loaiTDList.get(position);
        holder.tvTenLoaiTD.setText(loaiTD.getTENLOAITD());
//        Locale locale = new Locale("vn", "VN");
//        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
//        holder.txtGiaSanPham.setText(currencyFormatter.format(sanPham.getGiaSanPham()));
        holder.ivAvatar.setImageResource(loaiTD.getHINHANH());

        holder.loaiTD = loaiTDList.get(position);
    }


    public class UserItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTenLoaiTD;
        public ImageView ivAvatar;
        LoaiTD loaiTD;

//        private OnClickListenerLoaiTD listenerLoaiTD;

        public UserItemViewHolder(View itemView) {
            super(itemView);
            tvTenLoaiTD = itemView.findViewById(R.id.tv_option);
            ivAvatar = itemView.findViewById(R.id.img_option);

            //xuly su kien click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerLoaiTD.ItemClick(loaiTD);
                }
            });

        }


    }
}