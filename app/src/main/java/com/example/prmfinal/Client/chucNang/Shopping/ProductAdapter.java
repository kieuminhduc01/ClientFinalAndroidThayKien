package com.example.prmfinal.Client.chucNang.Shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prmfinal.Client.model.Product;
import com.example.prmfinal.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private static final String Tag="RecyclerView";
    private Context mContext;
    private ArrayList<Product> productArrayList;

    public ProductAdapter(Context mContext, ArrayList<Product> productArrayList){
        this.mContext=mContext;
        this.productArrayList=productArrayList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_unit,parent,false);

        return new ProductAdapter.ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Text view
        holder.textView.setText(productArrayList.get(position).getName());

        // Image view :Glide library

        Glide.with(mContext)
                .load(productArrayList.get(position).getImgUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Widgets;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView, final ProductAdapter.OnItemClickListener listener){
            super(itemView);

            imageView=itemView.findViewById(R.id.imgProduct);
            textView=itemView.findViewById(R.id.lblNameProduct);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    //variable interface
    private ProductAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ProductAdapter.OnItemClickListener listener){
        mListener=listener;
    }
}
