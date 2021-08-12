package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.R;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListener;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.PopularViewHolder> implements Filterable {
    private final Context context;
    private ArrayList<DALThucDon> popularList;
    ArrayList<DALThucDon> popularListSearch;
    private OnClickListener listener;


    public ProductAdapter(Context context, ArrayList<DALThucDon> popularList) {
        this.context = context;
        this.popularList = popularList;
    }
    public ProductAdapter(Context context, ArrayList<DALThucDon> popularList, OnClickListener listener) {
        this.context = context;
        this.popularList = popularList;
        this.popularListSearch = popularList;
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
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, ChiTietThucDonActivity.class);
//                i.putExtra("tv_name", popularList.get(position).getTenMon());
//                i.putExtra("price", popularList.get(position).getDonGia());
//                i.putExtra("decs", popularList.get(position).getMoTa());
//                i.putExtra("image", popularList.get(position).getHinhAnh());
//
//                context.startActivity(i);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return popularList.size();
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
//            view.LbTitle=(TextView) convertView.findViewById(R.id.lst_item_name);
//            view.LbContent=(TextView) convertView.findViewById(R.id.lst_item_price);
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

    public class PopularViewHolder extends RecyclerView.ViewHolder{

        DALThucDon dalThucDon ;
        public ImageView Img;
        public TextView LbTitle;
        public TextView LbContent;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            Img=itemView.findViewById(R.id.imageView);
            LbTitle=itemView.findViewById(R.id.lst_item_name);
            LbContent=itemView.findViewById(R.id.lst_item_price);

            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(dalThucDon);
                }
            });

        }
    }


    //Search view cho san pham
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty())
                {
                    popularList = popularListSearch;
                }
                else
                {
                    ArrayList<DALThucDon> list = new ArrayList<>();
                    for(DALThucDon products : popularListSearch)
                    {
                        if(products.getTenMon().toLowerCase().contains(strSearch.toLowerCase()))
                        {
                            list.add(products);
                        }
                    }
                    popularList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = popularList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                popularList = (ArrayList<DALThucDon>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}


