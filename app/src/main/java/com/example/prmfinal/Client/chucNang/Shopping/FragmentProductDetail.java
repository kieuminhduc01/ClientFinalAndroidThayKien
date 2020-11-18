package com.example.prmfinal.Client.chucNang.Shopping;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.Product;
import com.example.prmfinal.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProductDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProductDetail extends Fragment {

    private static Product product;
    private ImageView imgProduct;
    private TextView txtName;
    private TextView txtPrice;
    private TextView txtDescription;
    private EditText txtQuantity;
    private Button btnOrder;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentProductDetail(Product product) {
        // Required empty public constructor
        this.product=product;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentProductDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProductDetail newInstance(String param1, String param2) {
        FragmentProductDetail fragment = new FragmentProductDetail(product);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgProduct=(ImageView) view.findViewById(R.id.imgProduct);
        txtName=(TextView) view.findViewById(R.id.txtName);
        txtPrice=(TextView) view.findViewById(R.id.lblPrice);
        txtQuantity=(EditText) view.findViewById(R.id.txtQuantity);
        txtDescription=(TextView) view.findViewById(R.id.txtDescription);
        btnOrder=(Button) view.findViewById(R.id.btnOrder);

        Glide.with(this)
                .load(product.getImgUrl())
                .into(imgProduct);

        txtName.setText(product.getName());
        txtPrice.setText(String.valueOf(product.getSalePrice()));
        txtQuantity.setText(String.valueOf(product.getQuantiy()));
        txtDescription.setText(product.getDescriptionl());


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setQuantiy(Integer.parseInt(txtQuantity.getText().toString()));
                ArrayList<Product> e=ExternalData.Products;
                Toast.makeText(getContext(),"Ordered",Toast.LENGTH_LONG).show();
            }
        });
    }
}