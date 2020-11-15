package com.example.prmfinal.Client.chucNang.PersonalInformation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.prmfinal.Client.constant.listModel.ListLevel;
import com.example.prmfinal.Client.constant.listModel.ListMucDichTap;
import com.example.prmfinal.Client.dao.UserDao;
import com.example.prmfinal.Client.model.User;
import com.example.prmfinal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClientBodyInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClientBodyInfo extends Fragment {

    //BEGIN declare
    private EditText txtHeight;
    private EditText txtWeight;
    private EditText txtMaxRepPushUp;
    private EditText txtMaxRepPullUp;
    private EditText txtMaxRepSquats;
    private Spinner spMucDichTap;
    private Spinner spLevel;
    private Button btnSave;

    private static User user;
    //END declare


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentClientBodyInfo(User user) {
        // Required empty public constructor
        this.user=user;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentClientBodyInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentClientBodyInfo newInstance(String param1, String param2) {
        FragmentClientBodyInfo fragment = new FragmentClientBodyInfo(user);
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
        return inflater.inflate(R.layout.fragment_client_body_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtHeight = view.findViewById(R.id.txtHeight);
        txtWeight = view.findViewById(R.id.txtHeight);
        txtMaxRepPushUp = view.findViewById(R.id.txtMaxRepPushUp);
        txtMaxRepPullUp = view.findViewById(R.id.txtMaxRepPullUp);
        txtMaxRepSquats = view.findViewById(R.id.txtMaxRepSquats);
        spMucDichTap = view.findViewById(R.id.spMucDichTap);
        spLevel = view.findViewById(R.id.spLevel);
        btnSave = view.findViewById(R.id.btnSave);

        fillItemsForGoal();
        fillItemsForLevel();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSave(user);
            }
        });

    }


    //Begin set spinner
        private void fillItemsForGoal(){
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, ListMucDichTap.getAll());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spMucDichTap.setAdapter(dataAdapter);
        }
        private  void fillItemsForLevel(){
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, ListLevel.getAll());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spLevel.setAdapter(dataAdapter);
        }
    //end set spinner

    //begin set save
        private void setSave(User user){
            getMoreInforUser(user);

            UserDao userDao=new UserDao();
            userDao.insertIntoFireBase(user);

        }
    //end set save

    //begin set user
        private User getMoreInforUser(User user){

            user.setHeight(Integer.parseInt(txtHeight.getText().toString()));
            user.setWeight(Integer.parseInt(txtWeight.getText().toString()));
            user.setMaxRepPushUp(Integer.parseInt(txtMaxRepPushUp.getText().toString()));
            user.setMaxRepPullUp(Integer.parseInt(txtMaxRepPullUp.getText().toString()));
            user.setMaxRepSquats(Integer.parseInt(txtMaxRepSquats.getText().toString()));
            user.setMucDichTap(spMucDichTap.getSelectedItem().toString());
            user.setLevel(spLevel.getSelectedItem().toString());

            return  user;
        }
    //end set user
}