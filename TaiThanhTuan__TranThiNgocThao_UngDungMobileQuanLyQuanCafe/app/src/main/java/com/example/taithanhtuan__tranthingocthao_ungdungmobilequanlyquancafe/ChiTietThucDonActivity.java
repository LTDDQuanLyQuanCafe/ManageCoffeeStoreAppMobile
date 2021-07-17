package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.GioHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.ProductAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

public class ChiTietThucDonActivity extends AppCompatActivity {

    Button btnAddToCart;
    TextView tvName, tvPrice, tvDesc, tvPrNumb;
    ImageView imgHinhAnh, imgAddNumb, imgMinusNumb, imgFavorite, imgBack, imgCart;
    int numb;
    private static final String SELECTED_ITEM_ID = "selected"; //nguoi dung da select item
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thuc_don);
        anhXa();

        //Gui du lieu tu home qua
        Intent intent = getIntent();
        if(Common.thucDon != null)
        {
//            products = (Products) intent.getSerializableExtra("key1");
            imgHinhAnh.setImageBitmap(this.converStringToBitmapFromAccess(Common.thucDon.getHinhAnh()));
            imgHinhAnh.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //Set lại id để load dữ liệu từ HomeActivity qua
            tvName.setText(Common.thucDon.getTenMon());
            tvPrice.setText(Common.thucDon.getDonGia());
            tvDesc.setText(Common.thucDon.getMoTa());
        }

        imgAddNumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numb++;
                tvPrNumb.setText(numb);
            }
        });


        imgMinusNumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numb--;
                tvPrNumb.setText(numb);
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cart ton tai phan tu
                if(Common.carts.size() > 0)//Chưa khai báo r
                {
                    boolean exist = false;
                    for(int i =0; i < Common.carts.size(); i++)
                    {
                        if(Common.carts.get(i).getIdsp().equals(Common.thucDon.getMaLoaiTD()))//OK so sánh String phải dùng equal
                        {
                            Common.carts.get(i).setSoluong(Common.carts.get(i).getSoluong()+numb);
                            exist =true;
                        }
                    }
                    if(exist == false)
                    {
                        Common.carts.add(new GioHang(Common.thucDon.getMaLoaiTD(),Common.thucDon.getHinhAnh(), Common.thucDon.getTenMon(), Common.thucDon.getDonGia(), numb));
                    }
                }
                //Cart null
                else
                {
                    Common.carts.add(new GioHang(Common.thucDon.getMaLoaiTD(),Common.thucDon.getHinhAnh(), Common.thucDon.getTenMon(), Common.thucDon.getDonGia(), numb));

                }
                Intent intent1 = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent1);
            }
        });

    }

    public Bitmap converStringToBitmapFromAccess(String filename){
        AssetManager assetManager = getApplicationContext().getAssets();
        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void anhXa(){
        btnAddToCart = findViewById(R.id.btn_AddCart);

        tvName = findViewById(R.id.tv_name);
        tvPrice = findViewById(R.id.tv_price);
        tvDesc = findViewById(R.id.tv_Content);
        tvPrNumb = findViewById(R.id.tv_SL);

        imgBack = findViewById(R.id.back2);
        imgCart = findViewById(R.id.cart);
        imgHinhAnh = findViewById(R.id.img_Hinh);
        imgAddNumb = findViewById(R.id.img_Cong);
        imgMinusNumb = findViewById(R.id.img_Tru);
        imgFavorite = findViewById(R.id.img_Favorite);

    }
}