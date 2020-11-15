package com.example.prmfinal.Client.dao;

import androidx.annotation.NonNull;

import com.example.prmfinal.Client.data.ExternalData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dao {
    private boolean isExist;
    private String userName;

    public Dao(String userName) {
        this.userName = userName;
    }

    public void IsExist() {
        readDataCheckUserName(userName,new  Dao.FirebaseCallBack() {
            @Override
            public void onCallBack(boolean isExist) {
                ExternalData.IsUserNameExist=isExist;
            }
        });
    }

    private void readDataCheckUserName(String username,final Dao.FirebaseCallBack firebaseCallBack) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference
                .child("User")
                .orderByChild("userName")
                .equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    isExist=true;

                } else {
                    isExist=false;
                }
                firebaseCallBack.onCallBack(isExist);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private interface FirebaseCallBack {
        void onCallBack(boolean isExist);
    }
}
