package com.example.prmfinal.Client.chucNang.PersonalInformation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prmfinal.Client.chucNang.Excercise.FragmentClientExercise;
import com.example.prmfinal.Client.chucNang.Excercise.FragmentClientExerciseDetail;
import com.example.prmfinal.Client.dao.UserDao;
import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.User;
import com.example.prmfinal.LoginRegisterActivity;
import com.example.prmfinal.MainActivity;
import com.example.prmfinal.R;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLoginRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLoginRegister extends Fragment {

    //BEGIN declare
    private final String ROLE = "user";
    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;
    private Button btnRegister;
    private static Context context;
    private CheckBox cbRememberMe;
    //End declare


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public FragmentLoginRegister(Context context) {
        // Required empty public constructor
        this.context=context;
    }
    public FragmentLoginRegister() {
    }
    public static FragmentLoginRegister newInstance(String param1, String param2) {
        FragmentLoginRegister fragment = new FragmentLoginRegister(context);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtUsername = (EditText) view.findViewById(R.id.txtUserNameRegister);
        txtPassword = (EditText) view.findViewById(R.id.txtPassword);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        cbRememberMe=(CheckBox) view.findViewById(R.id.cbRememberMe);

        User existUser = getUserLogin();
        ExternalData.CurrentUser=existUser;
        if (!existUser.getId().equals("")) {
            txtUsername.setText(existUser.getUserName());
            txtPassword.setText(existUser.getPassword());
            login();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    register();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    //Begin function for login register
    private void register()  {
        boolean isUserExist = false;
        for (User user : ExternalData.Users) {
            if (user.getUserName().equals(txtUsername.getText().toString())) {
                isUserExist = true;
                break;
            }
        }

        if (isUserExist) {
            Toast.makeText(getContext(), "This username is exist!", Toast.LENGTH_LONG).show();
        } else {
            //create user
            User user = new User();
            user.setUserName(txtUsername.getText().toString());
            user.setPassword(txtPassword.getText().toString());
            user.setRole(ROLE);
            user.setId(UUID.randomUUID().toString());

            FragmentClientPersonalInformation fragmentClientPersonalInformation = new FragmentClientPersonalInformation(user);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frameLayout, fragmentClientPersonalInformation);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }

    private void login() {
        boolean isUserExist = false;

        User userCurrent = new User();
        for (User user : ExternalData.Users) {
            if (user.getUserName().equals(txtUsername.getText().toString()) &&
                    user.getPassword().equals(txtPassword.getText().toString())) {
                isUserExist = true;
                userCurrent = user;
                break;
            }
        }

        if (isUserExist) {
            if(cbRememberMe.isChecked()) {//luu lai trang thai login
                saveUserLogin(userCurrent);
            }
            else {
                removeUserLogin();
            }
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        } else {

        }
    }

    //End function for login register

    //BEGIN function for shared preference
    private void saveUserLogin(User userCurrent) {
        SharedPreferences sharedpreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("id", userCurrent.getId());
        editor.putString("userName", userCurrent.getUserName());
        editor.putString("password", userCurrent.getPassword());
        editor.commit();
    }

    private void removeUserLogin() {
        SharedPreferences sharedpreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("id", "");
        editor.putString("userName", "");
        editor.putString("password", "");
        editor.commit();
    }

    private User getUserLogin() {
        User user = new User();
        SharedPreferences sharedpreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        user.setId(sharedpreferences.getString("id", ""));
        user.setPassword(sharedpreferences.getString("password", ""));
        user.setUserName(sharedpreferences.getString("userName", ""));
        return user;
    }

    //END function for shared preference
}