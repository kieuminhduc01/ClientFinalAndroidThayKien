package com.example.prmfinal.Client.chucNang.Excercise;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prmfinal.Client.model.Exercise;
import com.example.prmfinal.R;
import com.example.prmfinal.Client.chucNang.Excercise.Adapter.ClientExerciseUnitAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClientExerciseUnitList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClientExerciseUnitList extends Fragment {

    RecyclerView rvExercises;
    //fire base
    private DatabaseReference myRef;

    //variables
    private ArrayList<Exercise> exercisesList;
    private ClientExerciseUnitAdapter clientExerciseUnitAdapter;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentClientExerciseUnitList() {
        // Required empty public constructor
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
        FragmentClientExerciseUnitList fragment = new FragmentClientExerciseUnitList();
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

        //recycle view
        rvExercises = view.findViewById(R.id.rvExercises);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvExercises.setLayoutManager(layoutManager);
        rvExercises.setHasFixedSize(true);

        //firebase
        myRef = FirebaseDatabase.getInstance().getReference();

        //ArrayList
        exercisesList = new ArrayList<>();

        //Clear Arraylist
        ClearAll();

        //get data method
        GetDataFromFirebase();
    }

    private void GetDataFromFirebase() {


        Query query = myRef.child("Exercise");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Exercise exercise = new Exercise();
                    exercise.setImgUrl(snapshot.child("imgUrl").getValue().toString());
                    exercise.setName(snapshot.child("name").getValue().toString());

                    exercisesList.add(exercise);
                    clientExerciseUnitAdapter = new ClientExerciseUnitAdapter(getContext(), exercisesList);
                    rvExercises.setAdapter(clientExerciseUnitAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ClearAll() {
        if (exercisesList != null) {
            exercisesList.clear();
            if (clientExerciseUnitAdapter != null) {
                clientExerciseUnitAdapter.notifyDataSetChanged();
            }
        }

        exercisesList = new ArrayList<>();
    }
}