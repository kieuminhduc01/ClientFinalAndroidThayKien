package com.example.prmfinal.Client.dao;

import androidx.annotation.NonNull;

import com.example.prmfinal.Client.constant.model.UserConst;
import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDao {

    //
    private DatabaseReference myRef;
    private ArrayList<User> userList;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final String path="User";

    public UserDao() {
        this.myRef = FirebaseDatabase.getInstance().getReference();
    }

    public void FillDataToExternalDataUserName() {

        readDataGetAllUserName(new  UserDao.FirebaseCallBack() {

            @Override
            public void onCallBack(ArrayList<User> list) {
                ExternalData.Users = userList;
            }
        });
    }

    private void readDataGetAllUserName(final UserDao.FirebaseCallBack firebaseCallBack) {
        Query query = myRef.child("User");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    User user = GetAnUserName(snapshot);
                    userList.add(user);
                }
                firebaseCallBack.onCallBack(userList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //BEGIN Function for firebase

    private User GetAnUserName(DataSnapshot snapshot) {
        User user=new User();
        user.setId(snapshot.child(UserConst.id).getValue().toString());
        user.setUserName(snapshot.child(UserConst.userName).getValue().toString());
        user.setPassword(snapshot.child(UserConst.password).getValue().toString());
        user.setRole(snapshot.child(UserConst.role).getValue().toString());
        user.setEmail(snapshot.child(UserConst.email).getValue().toString());
        user.setPhoneNumber(snapshot.child(UserConst.phoneNumber).getValue().toString());
        user.setName(snapshot.child(UserConst.name).getValue().toString());
        user.setHeight(Float.parseFloat(snapshot.child(UserConst.height).getValue().toString()));
        user.setWeight(Float.parseFloat(snapshot.child(UserConst.weight).getValue().toString()));
        user.setMaxRepPullUp(Integer.parseInt(snapshot.child(UserConst.maxRepPullUp).getValue().toString()));
        user.setMaxRepPushUp(Integer.parseInt(snapshot.child(UserConst.maxRepPushUp).getValue().toString()));
        user.setMaxRepSquats(Integer.parseInt(snapshot.child(UserConst.maxRepSquats).getValue().toString()));
        user.setMucDichTap(snapshot.child(UserConst.mucDichTap).getValue().toString());
        user.setLevel(snapshot.child(UserConst.level).getValue().toString());

        return user;
    }

    private interface FirebaseCallBack {
        void onCallBack(ArrayList<User> list);
    }
    //END Function for firebase

    //BEGIN Function for list exercise
    private void ClearAll() {
        if (userList != null) {
            userList.clear();
        }
        userList = new ArrayList<>();
    }
    //END Function for list exercise

    //BEGIN Insert User

        public void insertIntoFireBase (User user){
            HashMap hashMap = new HashMap();
            hashMap.put(String.valueOf(user.getId()),user);
            database.getReference().child(path).updateChildren(hashMap);
        }
    //END Insert User


}
