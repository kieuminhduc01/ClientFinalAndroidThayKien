package com.example.prmfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.prmfinal.Client.chucNang.Excercise.FragmentClientExercise;
import com.example.prmfinal.Client.chucNang.Excercise.FragmentClientExerciseUnitList;
import com.example.prmfinal.Client.chucNang.PersonalInformation.FragmentClientPersonalInformation;
import com.example.prmfinal.Client.chucNang.PersonalInformation.FragmentUser;
import com.example.prmfinal.Client.chucNang.Shopping.FragmentProductList;
import com.example.prmfinal.Client.constant.model.ExerciseFillterType;
import com.example.prmfinal.Client.constant.model.Level;
import com.example.prmfinal.Client.constant.model.TypeProductList;
import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.logicUtil.ScuccessLogic;
import com.example.prmfinal.Client.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExternalData.ExercisesSuggess = ScuccessLogic.ExercisesSuggest();
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
                            selectedFragment = new FragmentClientExercise();
                            break;

                        case R.id.nav_choseProduct:
                            selectedFragment = new FragmentProductList(ExternalData.Products, TypeProductList.Market);
                            break;

                        case R.id.nav_ordered:
                            selectedFragment = new FragmentProductList(ExternalData.Order.getItems(), TypeProductList.Order);
                            break;

                        case R.id.nav_shopping:
                            ArrayList<Product> productArrayList = new ArrayList<>();
                            for (Product product : ExternalData.Products) {
                                if (product.getQuantiy() > 0) {
                                    productArrayList.add(product);
                                }
                            }
                            selectedFragment = new FragmentProductList(productArrayList, TypeProductList.Cart);
                            break;
                        case R.id.nav_user:
                            selectedFragment = new FragmentUser();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

}