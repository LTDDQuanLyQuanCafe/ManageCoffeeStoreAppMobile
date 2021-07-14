package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.R;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<DALThucDon> {
    private List<DALThucDon> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ProductAdapter(@NonNull Context context, @NonNull List<DALThucDon> objects) {
        super(context, 0, objects);
        this.context=context;
        this.listData=objects;
        this.layoutInflater =LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewItem view;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.fragment_product_item, null);
            view = new ViewItem();
            view.Img=(ImageView) convertView.findViewById(R.id.imgProduct);
            view.LbTitle=(TextView) convertView.findViewById(R.id.lbProductName);
            view.LbContent=(TextView) convertView.findViewById(R.id.lbDescription);
            convertView.setTag(view);
        }
        else{
            view=(ViewItem) convertView.getTag();
        }
        DALThucDon thucdon=this.listData.get(position);
        view.LbTitle.setText(thucdon.getTenMon());
        view.LbContent.setText(thucdon.getMoTa().substring(0,140)+"...");
        view.Img.setImageBitmap(this.converStringToBitmapFromAccess(thucdon.getHinhAnh()+".jpg"));
        view.Img.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        convertView= LayoutInflater.from(getContext()).inflate(R.layout.activity_main,parent,false);
        return convertView;
    }
    static class ViewItem{
        ImageView Img;
        TextView LbTitle;
        TextView LbContent;
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
}
