package com.example.prmfinal.Client.data;

import com.example.prmfinal.Client.logicUtil.ScuccessLogic;
import com.example.prmfinal.Client.model.Exercise;
import com.example.prmfinal.Client.model.Product;
import com.example.prmfinal.Client.model.User;

import java.util.ArrayList;

public class ExternalData {
    public static ArrayList<Exercise> Exercises=new ArrayList<>();
    public static ArrayList<Exercise> ExercisesSuggess= new ArrayList<>();
    public static ArrayList<Exercise> ExercisesCurrent= new ArrayList<>();
    public static int IndexExercisesRunning=0;
    public static ArrayList<Product> Products=new ArrayList<>();
    public static ArrayList<User> Users=new ArrayList<>();
    public static boolean IsUserNameExist=true;
    public static int thoiGianTap=40000;
    public static int thoiGianNghi=10000;

}
