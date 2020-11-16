package com.example.prmfinal.Client.dao;

import androidx.annotation.NonNull;

import com.example.prmfinal.Client.constant.model.Order;
import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class OrderDao {
    private DatabaseReference myRef;
    private Order order;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final String path="Order";

    public OrderDao() {
    }

    //END Function for list Order
    public void insertIntoFireBase (Order order){
        HashMap hashMap = new HashMap();
        hashMap.put(String.valueOf(order.getId()),order);
        database.getReference().child(path).updateChildren(hashMap);
    }
}
