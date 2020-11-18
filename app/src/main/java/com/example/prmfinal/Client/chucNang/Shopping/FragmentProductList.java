package com.example.prmfinal.Client.chucNang.Shopping;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prmfinal.Client.chucNang.Excercise.Adapter.ClientExerciseUnitAdapter;
import com.example.prmfinal.Client.chucNang.Excercise.FragmentClientExerciseDetail;
import com.example.prmfinal.Client.constant.model.Order;
import com.example.prmfinal.Client.constant.model.TypeProductList;
import com.example.prmfinal.Client.dao.OrderDao;
import com.example.prmfinal.Client.dao.ProductDao;
import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.Product;
import com.example.prmfinal.R;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProductList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProductList extends Fragment implements ProductAdapter.OnItemClickListener {

    //begin declare
    private RecyclerView rvProducts;
    private ProductAdapter productAdapter;
    private static ArrayList<Product> productArrayList;
    private Button btnOder;
    private TextView txtTotalAmount;
    private static String typeProductList;
    private TextView lblTotalAmount;

    //end declare

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentProductList(ArrayList<Product> productArrayList, String cartOrMarket) {
        // Required empty public constructor
        this.productArrayList = productArrayList;
        this.typeProductList = cartOrMarket;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentProductList.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProductList newInstance(String param1, String param2) {
        FragmentProductList fragment = new FragmentProductList(productArrayList, typeProductList);
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
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnOder = view.findViewById(R.id.btnOrder);

        //recycle view
        rvProducts = view.findViewById(R.id.rvProduct);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvProducts.setLayoutManager(layoutManager);
        rvProducts.setHasFixedSize(true);

        setAdapterItemClick(productArrayList);
        rvProducts.setAdapter(productAdapter);

        txtTotalAmount = (TextView) view.findViewById(R.id.txtTotalAmount);
        lblTotalAmount=(TextView)view.findViewById(R.id.lblTotalAmount);
        setToalAmount();

        if (typeProductList.equals(TypeProductList.Market)) {
            btnOder.setVisibility(View.GONE);
            txtTotalAmount.setVisibility(View.GONE);
            lblTotalAmount.setVisibility(View.GONE);
        }
        if (typeProductList.equals(TypeProductList.Order)) {
            btnOder.setVisibility(View.GONE);
        }

        btnOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Product> productArrayList = new ArrayList<>();
                for (Product product : ExternalData.Products) {
                    if (product.getQuantiy() > 0) {
                        productArrayList.add(product);
                    }
                }

                setExternalOrder(productArrayList);
                OrderDao orderDao=new OrderDao();
                orderDao.insertIntoFireBase(ExternalData.Order);
                Toast.makeText(getContext(),"Success!",Toast.LENGTH_LONG).show();
            }
        });
    }

    //BEGIN Function for Adapter item click
    private void setAdapterItemClick(final ArrayList<Product> productArrayList) {
        productAdapter = new ProductAdapter(getContext(), this, productArrayList, typeProductList);
        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                FragmentProductDetail fragmentProductDetail = new FragmentProductDetail(productArrayList.get(position));
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentProductDetail);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
    }

    //END Function for Adapter item click



    public void setToalAmount() {
        float totalAmount = 0;

        for (Product product : ExternalData.Products) {
            if (product.getQuantiy() != 0) {
                totalAmount += product.getQuantiy() * product.getSalePrice();
            }
        }
        txtTotalAmount.setText(String.valueOf((double) Math.round(totalAmount * 100) / 100));
    }

    public static String getNow() {
        SimpleDateFormat ISO_8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'");
        String now = ISO_8601_FORMAT.format(new Date());
        return now;
    }

    private void setExternalOrder(ArrayList<Product> productArrayList){
        ExternalData.Order.setCreatedDate(getNow());
        ExternalData.Order.setCustomerId(ExternalData.CurrentUser.getId());
        ExternalData.Order.setItems(productArrayList);
        ExternalData.Order.setId(UUID.randomUUID().toString());
        ExternalData.Order.setStatus("Pending");
        ExternalData.Order.setTotalAmount(txtTotalAmount.getText().toString());
    }

}