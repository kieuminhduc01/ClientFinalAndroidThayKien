package com.example.prmfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prmfinal.Client.chucNang.PersonalInformation.FragmentLoginRegister;
import com.example.prmfinal.Client.dao.Dao;
import com.example.prmfinal.Client.dao.UserDao;
import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.User;

import java.util.UUID;

public class LoginRegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        FragmentLoginRegister fragmentLoginRegister=new FragmentLoginRegister(this);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.frameLayout,fragmentLoginRegister);
        transaction.commit();
    }

}