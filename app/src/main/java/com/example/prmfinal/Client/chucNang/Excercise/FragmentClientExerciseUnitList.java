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
import android.widget.Button;

import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.Exercise;
import com.example.prmfinal.R;
import com.example.prmfinal.Client.chucNang.Excercise.Adapter.ClientExerciseUnitAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClientExerciseUnitList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClientExerciseUnitList extends Fragment implements ClientExerciseUnitAdapter.OnItemClickListener {

    //BEGIN declare
    private Button btnStart;
    private RecyclerView rvExercises;
    private ClientExerciseUnitAdapter clientExerciseUnitAdapter;
    private static ArrayList<Exercise> exerciseArrayList;

    //END Declare

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentClientExerciseUnitList(ArrayList<Exercise> exerciseArrayList) {
        // Required empty public constructor
        this.exerciseArrayList=exerciseArrayList;
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
        FragmentClientExerciseUnitList fragment = new FragmentClientExerciseUnitList(exerciseArrayList);
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
        btnStart = view.findViewById(R.id.btnStart);

        //recycle view
        rvExercises = view.findViewById(R.id.rvExercises);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvExercises.setLayoutManager(layoutManager);
        rvExercises.setHasFixedSize(true);

        setAdapterItemClick(exerciseArrayList);
        rvExercises.setAdapter(clientExerciseUnitAdapter);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ExternalData.ExercisesCurrent=exerciseArrayList;
                ExternalData.IndexExercisesRunning=0;

                FragmentClientExerciseRun fragmentClientExerciseRun = new FragmentClientExerciseRun();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentClientExerciseRun);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }

    //BEGIN Function for Adapter item click
    private void setAdapterItemClick(final ArrayList<Exercise> exercisesList) {
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


}