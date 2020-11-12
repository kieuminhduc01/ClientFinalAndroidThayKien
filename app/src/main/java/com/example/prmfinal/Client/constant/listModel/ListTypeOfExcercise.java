package com.example.prmfinal.Client.constant.listModel;


import com.example.prmfinal.Client.constant.model.TypeOfExercise;

import java.util.ArrayList;
import java.util.List;

public class ListTypeOfExcercise {
    public static List<String> getAll(){
        List<String> types=new ArrayList<>();
        types.add(TypeOfExercise.Plank);
        types.add(TypeOfExercise.Pull);
        types.add(TypeOfExercise.Push);
        types.add(TypeOfExercise.Squat);

        return types;
    }
}
