package com.example.prmfinal.Client.chucNang.Excercise;

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
import android.widget.TextView;

import com.example.prmfinal.Client.data.ExternalData;
import com.example.prmfinal.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClientExerciseRest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClientExerciseRest extends Fragment {

    //BEGIN declare
    private Button btnNextExercise;
    private Button btnStartPause;
    private Button btnReset;
    private TextView lblTimerTicker;
    private CountDownTimer mCountDownTimer;
    private boolean isTimerRunning;
    private long   mTimeLeftInMillis = ExternalData.thoiGianNghi;
    //End declare


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentClientExerciseRest() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentClientExerciseRest.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentClientExerciseRest newInstance(String param1, String param2) {
        FragmentClientExerciseRest fragment = new FragmentClientExerciseRest();
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
        return inflater.inflate(R.layout.fragment_client_exercise_rest, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lblTimerTicker = view.findViewById(R.id.lblTimerTicker);
        btnNextExercise = view.findViewById(R.id.btnNextExercise);
        btnStartPause = view.findViewById(R.id.btnStartPause);
        btnReset = view.findViewById(R.id.btnReset);
        btnReset.setEnabled(false);

        btnNextExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentClientExerciseRun fragmentClientExerciseRest = new FragmentClientExerciseRun();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentClientExerciseRest);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
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

    private void startTimer() {

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                btnStartPause.setText("Start");
                btnStartPause.setVisibility(View.INVISIBLE);
                btnReset.setVisibility(View.VISIBLE);
                moveToFragmentRun();
            }
        }.start();
        isTimerRunning = true;
        btnStartPause.setText("pause");
        btnReset.setEnabled(true);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        isTimerRunning = false;
        btnStartPause.setText("Start");
        btnReset.setEnabled(true);
    }

    private void resetTimer() {
        mTimeLeftInMillis = ExternalData.thoiGianNghi;
        mCountDownTimer.cancel();
        isTimerRunning = false;
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
    private void moveToFragmentRun(){
        FragmentClientExerciseRun fragmentClientExerciseRun = new FragmentClientExerciseRun();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragmentClientExerciseRun);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}