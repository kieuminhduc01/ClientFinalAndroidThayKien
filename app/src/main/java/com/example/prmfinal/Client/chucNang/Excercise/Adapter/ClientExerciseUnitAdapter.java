package com.example.prmfinal.Client.chucNang.Excercise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmfinal.R;
import com.example.prmfinal.Client.model.Exercise;

import java.util.ArrayList;
import com.bumptech.glide.Glide;


public class ClientExerciseUnit extends RecyclerView.Adapter<ClientExerciseUnit.ViewHolder> {
    private static final String Tag="RecyclerView";
    private Context mContext;
    private ArrayList<Exercise> exerciseArrayList;

    public ClientExerciseUnit(Context mContext,ArrayList<Exercise> exerciseArrayList){
        this.mContext=mContext;
        this.exerciseArrayList=exerciseArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_client_exercise_unit,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Text view
        holder.textView.setText(exerciseArrayList.get(position).getName());

        // Image view :Glide library

        Glide.with(mContext)
                .load(exerciseArrayList.get(position).getImgUrl())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Widgets;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView){
            super(itemView);

            imageView=itemView.findViewById(R.id.imgExercise);
            textView=itemView.findViewById(R.id.lblNameOfExercise);
        }
    }
}