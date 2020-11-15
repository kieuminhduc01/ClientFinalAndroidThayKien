package com.example.prmfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.prmfinal.Client.chucNang.Excercise.FragmentClientExercise;
import com.example.prmfinal.Client.chucNang.Excercise.FragmentClientExerciseUnitList;
import com.example.prmfinal.Client.chucNang.PersonalInformation.FragmentClientPersonalInformation;
import com.example.prmfinal.Client.chucNang.Shopping.FragmentProductList;
import com.example.prmfinal.Client.constant.model.ExerciseFillterType;
import com.example.prmfinal.Client.constant.model.Level;
import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.logicUtil.ScuccessLogic;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExternalData.ExercisesSuggess= ScuccessLogic.ExercisesSuggest();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemReselectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentClientExercise()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener =

            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_exercise:

                            break;

                        case R.id.nav_personal_information:
                            selectedFragment=new FragmentProductList();
                            break;

                        case R.id.nav_nutries:
                            selectedFragment = new FragmentClientExercise();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

}