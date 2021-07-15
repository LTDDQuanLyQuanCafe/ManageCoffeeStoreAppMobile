package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter;

import android.content.Context;
import android.content.Intent;
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

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.ChiTietThucDonActivity;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.R;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.PopularViewHolder> {
    private Context context;

    public ProductAdapter(Context context, List<DALThucDon> popularList) {
        this.context = context;
        this.popularList = popularList;
    }

    private List<DALThucDon> popularList;

    @NonNull
    @Override
    public ProductAdapter.PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_dssp, parent, false);
        // here we need to create a layout for recyclerview cell items.


        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.PopularViewHolder holder, int position) {
        holder.LbTitle.setText(popularList.get(position).getTenMon());
        holder.LbContent.setText(popularList.get(position).getMoTa());
        // for image we add Glide library dependency for image fetching from server
        holder.Img.setImageBitmap(this.converStringToBitmapFromAccess(popularList.get(position).getHinhAnh()+".jpg"));
        holder.Img.setScaleType(ImageView.ScaleType.FIT_CENTER);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ChiTietThucDonActivity.class);
                i.putExtra("name", popularList.get(position).getTenMon());
                i.putExtra("price", popularList.get(position).getDonGia());
                i.putExtra("decs", popularList.get(position).getMoTa());
                i.putExtra("image", popularList.get(position).getHinhAnh());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public  static class PopularViewHolder extends RecyclerView.ViewHolder{

        ImageView Img;
        TextView LbTitle;
        TextView LbContent;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            Img=itemView.findViewById(R.id.imageView);
            LbTitle=itemView.findViewById(R.id.lst_item_title);
            LbContent=itemView.findViewById(R.id.lst_item_text);

        }
    }
//    private List<DALThucDon> listData;
//    private LayoutInflater layoutInflater;
//    private Context context;
//
//    public ProductAdapter(@NonNull Context context, @NonNull List<DALThucDon> objects) {
//        super(context, 0, objects);
//        this.context=context;
//        this.listData=objects;
//        this.layoutInflater =LayoutInflater.from(context);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        ViewItem view;
//        if(convertView==null){
//            convertView=layoutInflater.inflate(R.layout.layout_1dong_dssp, null);
//            view = new ViewItem();
//            view.Img=(ImageView) convertView.findViewById(R.id.imageView);
//            view.LbTitle=(TextView) convertView.findViewById(R.id.lst_item_title);
//            view.LbContent=(TextView) convertView.findViewById(R.id.lst_item_text);
//            convertView.setTag(view);
//        }
//        else{
//            view=(ViewItem) convertView.getTag();
//        }
//        DALThucDon thucdon=this.listData.get(position);
//        view.LbTitle.setText(thucdon.getTenMon());
//        view.LbContent.setText(thucdon.getMoTa().substring(0,140)+"...");
//        view.Img.setImageBitmap(this.converStringToBitmapFromAccess(thucdon.getHinhAnh()+".jpg"));
//        view.Img.setScaleType(ImageView.ScaleType.FIT_CENTER);
////        convertView= LayoutInflater.from(getContext()).inflate(R.layout.activity_main,parent,false);
//        return convertView;
//    }
//    static class ViewItem{
//        ImageView Img;
//        TextView LbTitle;
//        TextView LbContent;
//    }
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
}
