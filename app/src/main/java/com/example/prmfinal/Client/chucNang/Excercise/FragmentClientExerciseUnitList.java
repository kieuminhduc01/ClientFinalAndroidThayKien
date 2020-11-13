package com.example.prmfinal.Client.chucNang.Excercise;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.prmfinal.Client.Data.ExternalData;
import com.example.prmfinal.Client.constant.model.ExerciseFillterType;
import com.example.prmfinal.Client.dao.Dao;
import com.example.prmfinal.Client.model.Exercise;
import com.example.prmfinal.R;
import com.example.prmfinal.Client.chucNang.Excercise.Adapter.ClientExerciseUnitAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Arrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClientExerciseUnitList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClientExerciseUnitList extends Fragment implements ClientExerciseUnitAdapter.OnItemClickListener {

    //BEGIN declare
    private RecyclerView rvExercises;
    private DatabaseReference myRef;
    private ArrayList<Exercise> exercisesList;
    private ClientExerciseUnitAdapter clientExerciseUnitAdapter;
    private static String fillterTypeExercise;
    private static String fillterContentExercise;
    //END Declare

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentClientExerciseUnitList(String fillterTypeExercise,String fillterContentExercise) {
        // Required empty public constructor
        this.fillterTypeExercise=fillterTypeExercise;
        this.fillterContentExercise=fillterContentExercise;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentClientExerciseUnitList.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentClientExerciseUnitList newInstance(String param1, String param2) {
        FragmentClientExerciseUnitList fragment = new FragmentClientExerciseUnitList(fillterTypeExercise,fillterContentExercise);
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
        return inflater.inflate(R.layout.fragment_client_exercise_unit_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //BEGIN define
        //recycle view
        rvExercises = view.findViewById(R.id.rvExercises);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvExercises.setLayoutManager(layoutManager);
        rvExercises.setHasFixedSize(true);

        //firebase
        myRef = FirebaseDatabase.getInstance().getReference();

        //ArrayList
        exercisesList = new ArrayList<>();
        //END define

        //Clear Arraylist
        ClearAll();

        //get data method
        FillDataToRecycleView();

    }

    private void FillDataToRecycleView() {

        readData(new FirebaseCallBack() {
            @Override
            public void onCallBack(ArrayList<Exercise> list) {
                ExternalData.Exercises=exercisesList;
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


    private boolean isAnExercisePassedWithCondition(Exercise exercise) {
        String conditionsWithComma="";//condition with comma between items
        switch (fillterTypeExercise) {
            case "Level":
                conditionsWithComma=exercise.getLevel();
                break;
            case "MucDichTap":
                conditionsWithComma=exercise.getMucDichTap();
                break;
            case "MuscleFocus":
                conditionsWithComma=exercise.getMuscleFocus();
                break;
            case "TypeOfExercise":
                conditionsWithComma=exercise.getTypeOfExercise();
                break;
        }
        String[] arrayTempConditionCurrentExercise=conditionsWithComma.split(",");//array of conditions
        ArrayList<String> listConditionsCurrentExercise=new ArrayList<>( Arrays.asList(arrayTempConditionCurrentExercise));//pass array condition to list

        //chech this exercise is pass with filterContent condition
        if(listConditionsCurrentExercise.contains(fillterContentExercise)){
            return true;
        }
        return  false;
    }

    private  void readData(final FirebaseCallBack firebaseCallBack){
        Query query = myRef.child("Exercise");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Exercise exercise = GetAnExercise(snapshot);
                    if(isAnExercisePassedWithCondition(exercise))  {
                        exercisesList.add(exercise);
                        setAdapterItemClick();

                        rvExercises.setAdapter(clientExerciseUnitAdapter);

                    }

                }
                firebaseCallBack.onCallBack(exercisesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private interface FirebaseCallBack{
        void onCallBack(ArrayList<Exercise> list);
    }
    //END Function for firebase


    //BEGIN Function for Adapter item click
    private void setAdapterItemClick() {
        clientExerciseUnitAdapter = new ClientExerciseUnitAdapter(getContext(), exercisesList);
        clientExerciseUnitAdapter.setOnItemClickListener(new ClientExerciseUnitAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                FragmentClientExerciseDetail fragmentClientExerciseDetail = new FragmentClientExerciseDetail(exercisesList.get(position));
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentClientExerciseDetail);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }
    //END Function for Adapter item click

    //BEGIN Function for list exercise
    private void ClearAll() {
        if (exercisesList != null) {
            exercisesList.clear();
            if (clientExerciseUnitAdapter != null) {
                clientExerciseUnitAdapter.notifyDataSetChanged();
            }
        }

        exercisesList = new ArrayList<>();
    }
    //END Function for list exercise


}