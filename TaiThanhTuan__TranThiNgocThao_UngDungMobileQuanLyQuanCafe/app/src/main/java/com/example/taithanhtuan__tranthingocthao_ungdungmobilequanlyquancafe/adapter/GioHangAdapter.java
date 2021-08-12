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
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnDeleteCart;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> gioHangs;
    OnDeleteCart onDeleteCart;

    public GioHangAdapter(Context context, ArrayList<GioHang> carts, OnDeleteCart onDeleteCart)
    {
        this.context = context;
        this.gioHangs = carts;
        this.onDeleteCart = onDeleteCart;
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
            Button btn_delete;

            // mapping

            cart_img = convertview.findViewById(R.id.imageView);
            cart_name = convertview.findViewById(R.id.lst_item_name);
            cart_cost = convertview.findViewById(R.id.lst_item_price);
            cart_qlty = convertview.findViewById(R.id.tv_SL);
            btn_add = convertview.findViewById(R.id.btnCong);
            btn_min = convertview.findViewById(R.id.btnTru);
            btn_delete = convertview.findViewById(R.id.btn_Cart_Xoa1dong);

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
                    GioHangActivity.xuLyThanhTien();


                }
            });

            btn_min.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int soluong;
                    soluong = Integer.parseInt(cart_qlty.getText().toString());
                    if(soluong == 1)
                    {
                        //btn_min.setVisibility(View.VISIBLE);

                        if(Common.carts.size()<=0)
                        {
                            ((GioHangActivity)(context)).tvNull.setVisibility(View.VISIBLE);
                        }
                        if ((Common.carts.size()>=0))
                        {

                            onDeleteCart.onDelete(gioHangs.get(position));
                            GioHangActivity.xuLyThanhTien();
                        }
                        else if(Common.carts.size()<=0)
                        {
                            ((GioHangActivity)(context)).tvNull.setVisibility(View.VISIBLE);
//                        ((CartActivity)(context)).cartAdapter.notify();
                            GioHangActivity.xuLyThanhTien();
                        }
                    }
                    else
                    {
                        Common.carts.get(position).soluong--;
                        cart_qlty.setText(Common.carts.get(position).soluong + "");
                        GioHangActivity.xuLyThanhTien();
                    }

                }
            });

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Common.carts.size()<=0)
                    {
                        ((GioHangActivity)(context)).tvNull.setVisibility(View.VISIBLE);
                    }
                    if ((Common.carts.size()>0))
                    {
//                        DEPRESS.carts.remove(carts.get(position));
//                        synchronized(carts){
//                        carts.notify();
//                    }
                        onDeleteCart.onDelete(gioHangs.get(position));
                        GioHangActivity.xuLyThanhTien();
                    }
                    else if(Common.carts.size()<=0)
                    {
                        ((GioHangActivity)(context)).tvNull.setVisibility(View.VISIBLE);
//                        ((CartActivity)(context)).cartAdapter.notify();
                        GioHangActivity.xuLyThanhTien();
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
