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


public class ClientExerciseUnitAdapter extends RecyclerView.Adapter<ClientExerciseUnitAdapter.ViewHolder> {
    private static final String Tag="RecyclerView";
    private Context mContext;
    private ArrayList<Exercise> exerciseArrayList;

    public ClientExerciseUnitAdapter(Context mContext, ArrayList<Exercise> exerciseArrayList){
        this.mContext=mContext;
        this.exerciseArrayList=exerciseArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_client_exercise_unit,parent,false);

        return new ViewHolder(view,mListener);
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

        public ViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);

            imageView=itemView.findViewById(R.id.imgExercise);
            textView=itemView.findViewById(R.id.txtExerciseName);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    //variable interface
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }
}
