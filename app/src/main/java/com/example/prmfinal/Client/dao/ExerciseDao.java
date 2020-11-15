package com.example.prmfinal.Client.dao;

import androidx.annotation.NonNull;

import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.Exercise;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class ExerciseDao {
    private DatabaseReference myRef;
    private ArrayList<Exercise> exercisesList;

    public ExerciseDao() {
        this.myRef = FirebaseDatabase.getInstance().getReference();

    }

    public void FillDataToExternalDataExercise() {

        readDataGetAllExercise(new ExerciseDao.FirebaseCallBack() {
            @Override
            public void onCallBack(ArrayList<Exercise> list) {
                ExternalData.Exercises = exercisesList;

            }
        });
    }

    private void readDataGetAllExercise(final ExerciseDao.FirebaseCallBack firebaseCallBack) {
        Query query = myRef.child("Exercise");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Exercise exercise = GetAnExercise(snapshot);
                    exercisesList.add(exercise);
                }
                firebaseCallBack.onCallBack(exercisesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //BEGIN Function for firebase

    private Exercise GetAnExercise(DataSnapshot snapshot) {
        Exercise exercise = new Exercise();
        exercise.setCaloriesPerRep(Float.parseFloat(snapshot.child("caloriesPerRep").getValue().toString()));
        exercise.setEquipment(snapshot.child("equipment").getValue().toString());
        exercise.setImgUrl(snapshot.child("imgUrl").getValue().toString());
        exercise.setLevel(snapshot.child("level").getValue().toString());
        exercise.setMucDichTap(snapshot.child("mucDichTap").getValue().toString());
        exercise.setMuscleFocus(snapshot.child("muscleFocus").getValue().toString());
        exercise.setName(snapshot.child("name").getValue().toString());
        exercise.setTypeOfExercise(snapshot.child("typeOfExercise").getValue().toString());
        exercise.setVideoUrl(snapshot.child("videoUrl").getValue().toString());

        return exercise;
    }


    private boolean isAnExercisePassedWithCondition(Exercise exercise, String fillterTypeExercise, String fillterContentExercise) {
        String conditionsWithComma = "";//condition with comma between items
        switch (fillterTypeExercise) {
            case "Level":
                conditionsWithComma = exercise.getLevel();
                break;
            case "MucDichTap":
                conditionsWithComma = exercise.getMucDichTap();
                break;
            case "MuscleFocus":
                conditionsWithComma = exercise.getMuscleFocus();
                break;
            case "TypeOfExercise":
                conditionsWithComma = exercise.getTypeOfExercise();
                break;
        }
        String[] arrayTempConditionCurrentExercise = conditionsWithComma.split(",");//array of conditions
        ArrayList<String> listConditionsCurrentExercise = new ArrayList<>(Arrays.asList(arrayTempConditionCurrentExercise));//pass array condition to list

        //chech this exercise is pass with filterContent condition
        if (listConditionsCurrentExercise.contains(fillterContentExercise)) {
            return true;
        }
        return false;
    }


    private interface FirebaseCallBack {
        void onCallBack(ArrayList<Exercise> list);
    }
    //END Function for firebase

    //BEGIN Function for list exercise
    private void ClearAll() {
        if (exercisesList != null) {
            exercisesList.clear();
        }
        exercisesList = new ArrayList<>();
    }
    //END Function for list exercise

}
