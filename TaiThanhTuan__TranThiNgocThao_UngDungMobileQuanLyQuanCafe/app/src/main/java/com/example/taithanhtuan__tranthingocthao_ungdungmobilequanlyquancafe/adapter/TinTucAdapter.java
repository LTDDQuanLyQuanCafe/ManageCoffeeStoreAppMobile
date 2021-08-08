package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.TinTuc;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TinTucAdapter extends RecyclerView.Adapter<TinTucAdapter.UserItemViewHolder> {
    List<TinTuc> tinTucList;
    Context context;
    String url = "https://imgur.com/";

    public TinTucAdapter(List<TinTuc> users, Context context) {
        this.tinTucList = users;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return tinTucList.size();
    }

    @Override
    public com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.TinTucAdapter.UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_1dong_tintuc,null);
        return new com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.TinTucAdapter.UserItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.TinTuc tinTuc = tinTucList.get(position);
        holder.tvTieuDe.setText(tinTuc.getTIEUDE());
        holder.tvNoiDung.setText(tinTuc.getNOIDUNG());
        Picasso.with(context)
                .load(url + tinTuc.getHINHANH())
                .placeholder(R.drawable.login)
                .into(holder.ivAvatar);
        holder.ivAvatar.setScaleType(ImageView.ScaleType.FIT_XY);
    }


    public static class UserItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTieuDe;
        public TextView tvNoiDung;
        public ImageView ivAvatar;

        public UserItemViewHolder(View itemView) {
            super(itemView);
            tvTieuDe = itemView.findViewById(R.id.txt_TieuDe);
            tvNoiDung = itemView.findViewById(R.id.txt_NoiDung);
            ivAvatar = itemView.findViewById(R.id.img_TinTuc);


        }
    }
}
