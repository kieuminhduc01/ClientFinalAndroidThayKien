package com.example.prmfinal.Client.chucNang.Excercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prmfinal.Client.constant.model.ExerciseFillterType;
import com.example.prmfinal.Client.constant.model.Level;
import com.example.prmfinal.Client.constant.model.MucDichTap;
import com.example.prmfinal.Client.constant.model.MuscleFocus;
import com.example.prmfinal.Client.constant.model.TypeOfExercise;
import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.Exercise;
import com.example.prmfinal.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClientExercise#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClientExercise extends Fragment {

    //BEGIN declare button
        private ImageView btnBeginner;
        private ImageView btnAdvanced;
        private ImageView btnIntermediate;
        private ImageView btnAbs;
        private ImageView btnChest;
        private ImageView btnBack;
        private ImageView btnArms;
        private ImageView btnFullBody;
        private ImageView btnLegs;
        private ImageView btnLoseFat;
        private ImageView btnGainMuscle;
        private ImageView btnStrengthTraning;
        private ImageView btnPush;
        private ImageView btnPull;
        private ImageView btnSquats;
        private ImageView btnPlank;

        private ImageView btnSuggess;
    //END declare button

    //BEGIN declare other
        private ArrayList<Exercise> exerciseArrayList=new ArrayList<>();
    //END declare other

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentClientExercise() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentClientExercise.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentClientExercise newInstance(String param1, String param2) {
        FragmentClientExercise fragment = new FragmentClientExercise();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_exercise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        defineAllBtn(view);
        setEventForAllButton();
    }

    private void defineAllBtn(View view){
        btnBeginner=view.findViewById(R.id.btnBeginner);
        btnAdvanced=view.findViewById(R.id.btnAdvanced);
        btnIntermediate=view.findViewById(R.id.btnIntermediate);
        btnAbs=view.findViewById(R.id.btnAbs);
        btnChest=view.findViewById(R.id.btnChest);
        btnBack=view.findViewById(R.id.btnBack);
        btnArms=view.findViewById(R.id.btnArms);
        btnFullBody=view.findViewById(R.id.btnFullBody);
        btnLegs=view.findViewById(R.id.btnLegs);
        btnLoseFat=view.findViewById(R.id.btnLoseFat);
        btnGainMuscle=view.findViewById(R.id.btnGainMuscle);
        btnPush=view.findViewById(R.id.btnPush);
        btnStrengthTraning=view.findViewById(R.id.btnGainMuscle);
        btnPull=view.findViewById(R.id.btnPull);
        btnSquats=view.findViewById(R.id.btnSquats);
        btnPlank=view.findViewById(R.id.btnPlank);

        btnSuggess=view.findViewById(R.id.imgTranningRecommend);
        btnSuggess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentClientExerciseUnitList fragmentClientExerciseUnitList = new FragmentClientExerciseUnitList(ExternalData.ExercisesSuggess);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentClientExerciseUnitList);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }
    private void setEventForAllButton(){


        //BEGIN set for Body Path
            setOnClickForAnButton(btnAbs,ExerciseFillterType.MuscleFocus,MuscleFocus.Abs);
            setOnClickForAnButton(btnChest,ExerciseFillterType.MuscleFocus,MuscleFocus.Chest);
            setOnClickForAnButton(btnBack,ExerciseFillterType.MuscleFocus,MuscleFocus.Back);
            setOnClickForAnButton(btnArms,ExerciseFillterType.MuscleFocus,MuscleFocus.Arms);
            setOnClickForAnButton(btnFullBody,ExerciseFillterType.MuscleFocus,MuscleFocus.FullBody);
            setOnClickForAnButton(btnLegs,ExerciseFillterType.MuscleFocus,MuscleFocus.Legs);
        //End set for Body Path

        // BEGIN set for Level
            setOnClickForAnButton(btnBeginner,ExerciseFillterType.Level, Level.Beginner);
            setOnClickForAnButton(btnAdvanced,ExerciseFillterType.Level, Level.Advanced);
            setOnClickForAnButton(btnIntermediate,ExerciseFillterType.Level, Level.Intermediate);
        //End set for Level

        // BEGIN set for Goal
            setOnClickForAnButton(btnLoseFat,ExerciseFillterType.MucDichTap, MucDichTap.LoseFat);
            setOnClickForAnButton(btnGainMuscle,ExerciseFillterType.MucDichTap, MucDichTap.GainMuscle);
            setOnClickForAnButton(btnStrengthTraning,ExerciseFillterType.MucDichTap, MucDichTap.StrengthTraining);
        //End set for Goal

        // BEGIN set for kind of exercise
            setOnClickForAnButton(btnPush,ExerciseFillterType.TypeOfExercise, TypeOfExercise.Push);
            setOnClickForAnButton(btnPull,ExerciseFillterType.TypeOfExercise, TypeOfExercise.Pull);
            setOnClickForAnButton(btnPlank,ExerciseFillterType.TypeOfExercise, TypeOfExercise.Plank);
            setOnClickForAnButton(btnSquats,ExerciseFillterType.TypeOfExercise, TypeOfExercise.Squat);
        // END set for kind of exercise

    }
    private void setOnClickForAnButton(ImageView btn, final String fillterTypeExercise, final String fillterContentExercise ){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseArrayList.clear();
                for(Exercise exercise:ExternalData.Exercises){
                    if(isAnExercisePassedWithCondition(exercise, fillterTypeExercise, fillterContentExercise)){
                        exerciseArrayList.add(exercise);
                    }
                }

                FragmentClientExerciseUnitList fragmentClientExerciseUnitList = new FragmentClientExerciseUnitList(exerciseArrayList);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentClientExerciseUnitList);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
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
}