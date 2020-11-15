package com.example.prmfinal.Client.logicUtil;

import com.example.prmfinal.Client.constant.model.MucDichTap;
import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.Exercise;
import com.example.prmfinal.Client.model.ExerciseListADay;
import com.example.prmfinal.Client.model.ExerciseListTenDays;
import com.example.prmfinal.Client.model.User;

import java.util.ArrayList;

public class ScuccessLogic {

    public static ArrayList<Exercise> ExercisesSuggest() {
        ArrayList<Exercise> exerciseArrayList = new ArrayList<>();
        ArrayList<Integer> randomIndexExercise = RandomSuffle.getFiveNumber(ExternalData.Exercises.size());

        for (int i = 0; i < ExternalData.Exercises.size(); i++) {
            exerciseArrayList.add(ExternalData.Exercises.get(randomIndexExercise.get(i)));
            if (i == 4){
                break;
            }

        }
        return  exerciseArrayList;
    }
}
