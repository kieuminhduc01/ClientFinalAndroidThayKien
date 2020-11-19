package com.example.prmfinal.Client.chucNang.Excercise;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.Client.model.Exercise;
import com.example.prmfinal.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClientExerciseRun#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClientExerciseRun extends Fragment {


    //BEGIN declare timer
    private Button btnRest;
    private Button btnStartPause;
    private Button btnReset;
    private TextView lblTimerTicker;
    private CountDownTimer mCountDownTimer;
    private boolean isTimmerRuning;
    private long mTimeLeftInMillis=ExternalData.thoiGianTap;
    private VideoView vvExercise;
    private MediaController mediaController;

    //End declare timer
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentClientExerciseRun() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentClientExerciseRun.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentClientExerciseRun newInstance(String param1, String param2) {
        FragmentClientExerciseRun fragment = new FragmentClientExerciseRun();
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
        return inflater.inflate(R.layout.fragment_client_exercise_run, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lblTimerTicker = view.findViewById(R.id.lblTimerTicker);
        vvExercise = view.findViewById(R.id.vvExercise);
        btnRest = view.findViewById(R.id.btnRest);
        btnStartPause = view.findViewById(R.id.btnStartPause);
        btnReset = view.findViewById(R.id.btnReset);
        btnReset.setEnabled(false);
        //khi chua chay het list bai tap
        if(ExternalData.IndexExercisesRunning < ExternalData.ExercisesCurrent.size()){
            setVideoView();

            btnRest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveToFragmentRest();
                }
            });

            btnStartPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isTimmerRuning) {
                        pauseTimer();
                    } else {
                        startTimer();
                    }
                }
            });
            btnReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetTimer();
                }
            });
            updateCountDownText();
        }

        //khi da chay het list bai tap
        else {
           moveToFragmentSuccess();
        }

    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                moveToFragmentRest();
            }
        }.start();
        isTimmerRuning = true;
        btnStartPause.setText("pause");
        btnReset.setEnabled(true);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        isTimmerRuning = false;
        btnStartPause.setText("Start");
        btnReset.setEnabled(true);
    }

    private void resetTimer() {
        mTimeLeftInMillis = ExternalData.thoiGianTap;
        mCountDownTimer.cancel();
        isTimmerRuning = false;
        btnReset.setEnabled(false);
        btnStartPause.setText("Start");
        updateCountDownText();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        lblTimerTicker.setText(timeLeftFormatted);
    }
    private void moveToFragmentRest(){
        ExternalData.IndexExercisesRunning++;
        FragmentClientExerciseRest fragmentClientExerciseRest = new FragmentClientExerciseRest();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragmentClientExerciseRest);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void setVideoView(){

        vvExercise.setVideoURI(Uri.parse(ExternalData.ExercisesCurrent.get(ExternalData.IndexExercisesRunning).getVideoUrl()));
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
    private void moveToFragmentSuccess(){
        double totalCalories=0;

        for(Exercise exercise :ExternalData.ExercisesCurrent){
            totalCalories+=exercise.getCaloriesPerRep();
        }

        String totalCaloriesString= String.valueOf((double) Math.round(totalCalories * 100) / 100);
        FragmentClientExerciseSuccess fragmentClientExerciseSuccess = new FragmentClientExerciseSuccess(totalCaloriesString);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragmentClientExerciseSuccess);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}