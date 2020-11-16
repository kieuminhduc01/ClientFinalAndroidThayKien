package com.example.prmfinal.Client.chucNang.Shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prmfinal.Client.constant.model.TypeProductList;
import com.example.prmfinal.Client.model.Product;
import com.example.prmfinal.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Product> productArrayList;
    private String typeProductList;
    private FragmentProductList fragmentProductList;

    //Constructor
    public ProductAdapter(Context mContext,FragmentProductList fragmentProductList, ArrayList<Product> productArrayList, String typeProductList) {
        this.mContext = mContext;
        this.productArrayList = productArrayList;
        this.typeProductList = typeProductList;
        this.fragmentProductList=fragmentProductList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_unit, parent, false);

        return new ProductAdapter.ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        // Text view
        holder.txtName.setText(productArrayList.get(position).getName());
        holder.txtQuantity.setText(String.valueOf(productArrayList.get(position).getQuantiy()));
        holder.txtPrice.setText(String.valueOf(productArrayList.get(position).getSalePrice()));

        // Image view :Glide library

        Glide.with(mContext)
                .load(productArrayList.get(position).getImgUrl())
                .into(holder.imageView);
        //Cart or Market
        if (typeProductList.equals(TypeProductList.Market)) {
            holder.btnIncre.setVisibility(View.GONE);
            holder.btnDecre.setVisibility(View.GONE);
            holder.txtQuantity.setVisibility(View.GONE);
        }
        if(typeProductList.equals(TypeProductList.Order)){
            holder.btnIncre.setVisibility(View.GONE);
            holder.btnDecre.setVisibility(View.GONE);
        }

        fragmentProductList.setToalAmount();
        holder.btnDecre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Product product = productArrayList.get(position);
                int quantity = Integer.parseInt(holder.txtQuantity.getText().toString());
                if (quantity > 0) {
                    quantity--;
                }
                product.setQuantiy(quantity);
                holder.txtQuantity.setText(String.valueOf(quantity));
                fragmentProductList.setToalAmount();
            }
        });
        holder.btnIncre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Product product = productArrayList.get(position);
                int quantity = Integer.parseInt(holder.txtQuantity.getText().toString());
                quantity++;
                product.setQuantiy(quantity);
                holder.txtQuantity.setText(String.valueOf(quantity));
                fragmentProductList.setToalAmount();

            }
        });


    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //Widgets;
        ImageView imageView;
        TextView txtName;
        TextView txtQuantity;
        TextView txtPrice;
        Button btnDecre;
        Button btnIncre;

        public ViewHolder(View itemView, final ProductAdapter.OnItemClickListener listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgProduct);
            txtName = itemView.findViewById(R.id.lblNameProduct);
            txtQuantity = itemView.findViewById(R.id.lblQuantity);
            txtPrice = itemView.findViewById(R.id.lblPrice);
            btnDecre = itemView.findViewById(R.id.btnDecre);
            btnIncre = itemView.findViewById(R.id.btnIncre);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    //variable interface
    private ProductAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ProductAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
}
