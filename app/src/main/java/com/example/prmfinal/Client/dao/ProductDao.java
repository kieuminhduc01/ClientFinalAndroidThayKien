package com.example.prmfinal.Client.dao;

import androidx.annotation.NonNull;

import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.Product;
import com.example.prmfinal.Client.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductDao {

    //
    private DatabaseReference myRef;
    private ArrayList<Product> products;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final String path="Products";

    public ProductDao() {
        this.myRef = FirebaseDatabase.getInstance().getReference();
    }

    public void FillDataToExternalDataProduct() {

        readDataGetAllProduct(new  ProductDao.FirebaseCallBack() {

            @Override
            public void onCallBack(ArrayList<Product> list) {
                ExternalData.Products = products;
            }
        });
    }

    private void readDataGetAllProduct(final ProductDao.FirebaseCallBack firebaseCallBack) {
        Query query = myRef.child("Product");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Product product = GetAnProduct(snapshot);
                    products.add(product);
                }
                firebaseCallBack.onCallBack(products);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //BEGIN Function for firebase

    private Product GetAnProduct(DataSnapshot snapshot) {
        Product product=new Product();
        product.setDescriptionl(snapshot.child("descriptionl").getValue().toString());
        product.setId(snapshot.child("id").getValue().toString());
        product.setImgUrl(snapshot.child("imgUrl").getValue().toString());
        product.setName(snapshot.child("name").getValue().toString());
        product.setQuantiy(0);
        product.setSalePrice(Float.parseFloat(snapshot.child("salePrice").getValue().toString()));
        product.setCostOfGoodSold(Float.parseFloat(snapshot.child("costOfGoodSold").getValue().toString()));
        return product;
    }

    private interface FirebaseCallBack {
        void onCallBack(ArrayList<Product> list);
    }
    //END Function for firebase

    //BEGIN Function for list exercise
    private void ClearAll() {
        if (products != null) {
            products.clear();
        }
        products = new ArrayList<>();
    }
    //END Function for list exercise
    public void insertIntoFireBase (Product product){
        HashMap hashMap = new HashMap();
        hashMap.put(String.valueOf(product.getId()),product);
        database.getReference().child(path).updateChildren(hashMap);
    }


}
