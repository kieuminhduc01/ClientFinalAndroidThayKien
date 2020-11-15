package com.example.prmfinal.Client.chucNang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.prmfinal.Client.dao.ExerciseDao;
import com.example.prmfinal.Client.dao.UserDao;
import com.example.prmfinal.LoginRegisterActivity;
import com.example.prmfinal.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(LoginRegisterActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundResource(R.drawable.splash_screen);
        View easySplashScreen = config.create();
        setContentView(easySplashScreen);

        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.FillDataToExternalDataExercise();

        UserDao userDao = new UserDao();
        userDao.FillDataToExternalDataUserName();
    }
}