package com.example.prmfinal.Client.chucNang.Excercise;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.prmfinal.Client.model.Exercise;
import com.example.prmfinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClientExerciseDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClientExerciseDetail extends Fragment {

    private static Exercise exercise;
    private VideoView vvExercise;
    private TextView lblEquipment;
    private TextView lblcaloriesPerRep;
    private TextView lblNameExercise;
    private TextView lblMuscleGroup;
    private TextView lblLevel;
    private MediaController mediaController;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentClientExerciseDetail(Exercise exercise) {
        // Required empty public constructor
        this.exercise=exercise;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentClientExerciseDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentClientExerciseDetail newInstance(String param1, String param2) {
        FragmentClientExerciseDetail fragment = new FragmentClientExerciseDetail(exercise);
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
        return inflater.inflate(R.layout.fragment_client_exercise_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //BEGIN declare
            vvExercise=view.findViewById(R.id.vvExercise);
            lblEquipment=view.findViewById(R.id.lblEquipment);
            lblcaloriesPerRep=view.findViewById(R.id.lblcaloriesPerRep);
            lblNameExercise=view.findViewById(R.id.lblNameExercise);
            lblMuscleGroup=view.findViewById(R.id.lblMuscleGroup);
            lblLevel=view.findViewById(R.id.lblLevel);
        //END declare

        //BEGIN set component
            lblEquipment.setText(exercise.getEquipment());
            lblcaloriesPerRep.setText(String.valueOf(exercise.getCaloriesPerRep()));
            lblNameExercise.setText(exercise.getName());
            lblMuscleGroup.setText(exercise.getEquipment());
            lblLevel.setText(exercise.getLevel());

        //END set compnent
            setVideoView();


    }
    private void setVideoView(){

        vvExercise.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/workoutapp-9e3e6.appspot.com/o/Video%2FThe%20Push-Up.mp4?alt=media&token=fd7c69db-99d3-4678-86f4-a90561270954"));
        mediaController=new MediaController(getContext());
        mediaController.setAnchorView(vvExercise);
        vvExercise.setMediaController(mediaController);

        vvExercise.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                // restart on completion
                vvExercise.start();
            }
        });
    }
}