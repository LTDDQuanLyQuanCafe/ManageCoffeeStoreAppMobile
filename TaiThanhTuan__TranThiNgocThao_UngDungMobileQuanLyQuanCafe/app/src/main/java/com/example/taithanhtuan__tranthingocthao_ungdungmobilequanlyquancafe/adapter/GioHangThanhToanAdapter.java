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
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnDeleteCart;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GioHangThanhToanAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> gioHangs;
    OnDeleteCart onDeleteCart;

    public GioHangThanhToanAdapter(Context context, ArrayList<GioHang> gioHangs)
    {
        this.context = context;
        this.gioHangs = gioHangs;
    }

    public GioHangThanhToanAdapter(Context context, ArrayList<GioHang> carts, OnDeleteCart onDeleteCart)
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

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        if(convertview == null)
        {
            convertview = LayoutInflater.from(context).inflate(R.layout.layout_1dong_giohang, null);
            // gen
            ImageView cart_img;
            TextView cart_name, cart_cost, cart_qlty, free;
            ImageView btn_add, btn_min;
            Button btn_delete;

            // mapping

            cart_img = convertview.findViewById(R.id.imageView);
            cart_name = convertview.findViewById(R.id.lst_item_name);
            cart_cost = convertview.findViewById(R.id.lst_item_price);
            free = convertview.findViewById(R.id.all_menu_delivery_charge);
            cart_qlty = convertview.findViewById(R.id.tv_SL);
            btn_add = convertview.findViewById(R.id.btnCong);
            btn_min = convertview.findViewById(R.id.btnTru);
            btn_delete = convertview.findViewById(R.id.btn_Cart_Xoa1dong);
            //hooking
            GioHang cart = (GioHang)getItem(position);

            cart_name.setText(cart.getTensp());
            cart_cost.setText("Giá bán: "+cart.getGiasp()+" VNĐ");
            cart_cost.setLines(2);
            String tsl = "Số lượng : "+cart.getSoluong()+" món";
            cart_qlty.setText(tsl);

            btn_add.setVisibility(View.INVISIBLE);
            btn_min.setVisibility(View.INVISIBLE);
            btn_delete.setVisibility(View.INVISIBLE);
            free.setVisibility(View.INVISIBLE);

            //load anh
            cart_img.setImageBitmap(this.converStringToBitmapFromAccess(cart.getHinhsp()));
            cart_img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        return convertview;
    }

    public class ViewHolderListGH
    {
        public TextView tvTenspCart, tvGiaspCart, tvSLspCart;
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
