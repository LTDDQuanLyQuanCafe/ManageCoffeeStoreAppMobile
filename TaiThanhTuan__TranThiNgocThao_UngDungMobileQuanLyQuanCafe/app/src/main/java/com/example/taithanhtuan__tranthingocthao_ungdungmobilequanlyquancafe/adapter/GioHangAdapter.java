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

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.GioHangActivity;
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
    public View getView(int position, View convertview, ViewGroup parent) {
        if(convertview == null)
        {
            convertview = LayoutInflater.from(context).inflate(R.layout.layout_1dong_giohang, null);
            // gen
            ImageView cart_img;
            TextView cart_name, cart_cost, cart_qlty;
            ImageView btn_add, btn_min;

            // mapping

            cart_img = convertview.findViewById(R.id.imageView);
            cart_name = convertview.findViewById(R.id.lst_item_name);
            cart_cost = convertview.findViewById(R.id.lst_item_price);
            cart_qlty = convertview.findViewById(R.id.tv_SL);
            btn_add = convertview.findViewById(R.id.btnCong);
            btn_min = convertview.findViewById(R.id.btnTru);

            //hooking
            GioHang cart = (GioHang)getItem(position);

            cart_name.setText(cart.getTensp());
            cart_cost.setText(cart.getGiasp());

            //load anh
            cart_img.setImageBitmap(this.converStringToBitmapFromAccess(cart.getHinhsp()));
            cart_img.setScaleType(ImageView.ScaleType.FIT_CENTER);
            cart_qlty.setText(cart.getSoluong() + "");

            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Common.carts.get(position).soluong++;
                    cart_qlty.setText(Common.carts.get(position).soluong + "");
                    ((GioHangActivity)(context)).xuLyThanhTien();


                }
            });

            btn_min.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int soluong;
                    soluong = Integer.parseInt(cart_qlty.getText().toString());
                    if(soluong < 1)
                    {
                        btn_min.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Common.carts.get(position).soluong--;
                        cart_qlty.setText(Common.carts.get(position).soluong + "");
                        ((GioHangActivity)(context)).xuLyThanhTien();
                    }

                }
            });

        }
        return convertview;
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
