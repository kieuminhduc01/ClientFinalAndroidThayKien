package com.example.prmfinal.Client.chucNang.PersonalInformation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prmfinal.Client.model.User;
import com.example.prmfinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClientPersonalInformation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClientPersonalInformation extends Fragment {

    private TextView txtUserNameRegister;
    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPhoneNumber;
    private Button btnSave;
    private static User user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentClientPersonalInformation(User user) {
        // Required empty public constructor
        this.user=user;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentClientPersonalInformation.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentClientPersonalInformation newInstance(String param1, String param2) {
        FragmentClientPersonalInformation fragment = new FragmentClientPersonalInformation(user);
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
        return inflater.inflate(R.layout.fragment_client_personal_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        txtName = (EditText) view.findViewById(R.id.txtName);
        txtPhoneNumber = (EditText) view.findViewById(R.id.txtPhoneNumber);
        txtUserNameRegister = (TextView) view.findViewById(R.id.txtUserNameRegister);
        btnSave = (Button) view.findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMoreUserInfor();

                FragmentClientBodyInfo fragmentClientPersonalInformation = new FragmentClientBodyInfo(user);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragmentClientPersonalInformation);
                transaction.addToBackStack(null);
                transaction.commit();
                Toast.makeText(getContext(),"Saved",Toast.LENGTH_LONG).show();
            }
        });

    }
    private void setMoreUserInfor(){
        user.setEmail(txtEmail.getText().toString());
        user.setName(txtName.getText().toString());
        user.setPhoneNumber(txtPhoneNumber.getText().toString());
    }

}