package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.GioHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.R;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> gioHangs;


    public GioHangAdapter(Context context, ArrayList<GioHang> gioHangs)
    {
        this.context = context;
        this.gioHangs = gioHangs;
    }

    @Override
    public int getCount() {
        return gioHangs.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolderListGH
    {
        public TextView tvTenspCart, tvGiaspCart, tvSLspCart;
        ImageView btnAdd, btnMin;
        ImageView imgspCart;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolderListGH viewHolderListGH = null;
        if(view == null)
        {
            viewHolderListGH = new ViewHolderListGH();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_1dong_giohang, null);
            // anh xa
            viewHolderListGH.tvTenspCart = (TextView)view.findViewById(R.id.lst_item_name);
            viewHolderListGH.tvGiaspCart = (TextView)view.findViewById(R.id.lst_item_price);
            viewHolderListGH.tvSLspCart = (TextView)view.findViewById(R.id.tv_SL);
            viewHolderListGH.imgspCart = (ImageView)view.findViewById(R.id.imageView);
            viewHolderListGH.btnAdd = (ImageView)view.findViewById(R.id.btnCong);
            viewHolderListGH.btnMin = (ImageView)view.findViewById(R.id.btnTru);
            view.setTag(viewHolderListGH);
        }
        else
        {
            viewHolderListGH = (ViewHolderListGH) view.getTag();
        }
        GioHang gioHang = (GioHang)getItem(position);
        viewHolderListGH.tvTenspCart.setText(gioHang.getTensp());
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        viewHolderListCart.tvGiaspCart.setText(decimalFormat.format(cart.getGiasp()));
        viewHolderListGH.tvGiaspCart.setText(gioHang.getGiasp());

        //load anh
        viewHolderListGH.imgspCart.setImageBitmap(this.converStringToBitmapFromAccess(gioHang.getHinhsp()));
        viewHolderListGH.imgspCart.setScaleType(ImageView.ScaleType.FIT_CENTER);

        //Them "" de chuyen ve kieu String
        viewHolderListGH.tvSLspCart.setText(gioHang.getSoluong() + "");


        return view;
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
