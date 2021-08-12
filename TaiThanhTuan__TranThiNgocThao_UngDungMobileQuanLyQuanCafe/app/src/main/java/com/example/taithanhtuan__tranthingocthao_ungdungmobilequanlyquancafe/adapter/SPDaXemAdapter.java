package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.R;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListener;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SPDaXemAdapter extends RecyclerView.Adapter<SPDaXemAdapter.PopularViewHolder> {
    private static Context context;
    private ArrayList<DALThucDon> popularList;
    private OnClickListener listener;


    public SPDaXemAdapter(Context context, ArrayList<DALThucDon> popularList) {
        this.context = context;
        this.popularList = popularList;
    }
    public SPDaXemAdapter(Context context) {
        this.context = context;
    }
    public SPDaXemAdapter(Context context, ArrayList<DALThucDon> popularList, OnClickListener listener) {
        this.context = context;
        this.popularList = popularList;
        this.thucDonArrayList_History = popularList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_dssp, null);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        DALThucDon dalThucDon = popularList.get(position);
        holder.LbTitle.setText(dalThucDon.getTenMon());
        holder.LbContent.setText(dalThucDon.getMoTa());
        // for image we add Glide library dependency for image fetching from server
        holder.Img.setImageBitmap(this.converStringToBitmapFromAccess(dalThucDon.getHinhAnh()));
        holder.Img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        holder.dalThucDon = popularList.get(position);
    }


    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public Bitmap converStringToBitmapFromAccess(String filename) {
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

    public class PopularViewHolder extends RecyclerView.ViewHolder {

        DALThucDon dalThucDon;
        public ImageView Img;
        public TextView LbTitle;
        public TextView LbContent;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            Img = itemView.findViewById(R.id.imageView);
            LbTitle = itemView.findViewById(R.id.lst_item_name);
            LbContent = itemView.findViewById(R.id.lst_item_price);

            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(dalThucDon);
                }
            });

        }
    }
    static String filename="database.txt";
    public static ArrayList<DALThucDon> thucDonArrayList_History = new ArrayList<>();

    public void addFurintureHistorry(DALThucDon furniture) {
        if (thucDonArrayList_History.indexOf(furniture) > 0) {
            this.thucDonArrayList_History.add(0, furniture);
        }
    }

    public ArrayList<DALThucDon> getFurnitureHistory() {
        return this.thucDonArrayList_History;
    }

    public static void WriteToFileInternal(ArrayList<DALThucDon> arrayList) {
        try {
            File file = new File(context.getFilesDir(), filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new
                    ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<DALThucDon> LoadFileInternal(){
        ArrayList<DALThucDon> arrayList = new ArrayList<>();
        try {
            File file = new File(context.getFilesDir(), filename);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new
                    ObjectInputStream(fileInputStream);
            arrayList = (ArrayList<DALThucDon>) objectInputStream.readObject();
            Log.d("FurnitureApp", arrayList.size()+"");
            return arrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}



