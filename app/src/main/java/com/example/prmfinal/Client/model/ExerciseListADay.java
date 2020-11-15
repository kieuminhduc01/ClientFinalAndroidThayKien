package com.example.prmfinal.Client.model;

import java.util.ArrayList;

public class ExerciseListADay {
    private ArrayList<Exercise>  exercises;
    private boolean isFinish;

    public ExerciseListADay() {
    }

    public ExerciseListADay(ArrayList<Exercise> exercises, boolean isFinish) {
        this.exercises = exercises;
        this.isFinish = isFinish;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }
}
