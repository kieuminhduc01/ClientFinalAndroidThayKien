package com.example.prmfinal.Client.constant.listModel;

import com.example.prmfinal.Client.constant.model.MucDichTap;

import java.util.ArrayList;
import java.util.List;

public class ListMucDichTap {
    public static ArrayList<String> getAll(){
        ArrayList<String> mucDichTaps=new ArrayList<>();
        mucDichTaps.add(MucDichTap.GainMuscle);
        mucDichTaps.add(MucDichTap.LoseFat);
        mucDichTaps.add(MucDichTap.StrengthTraining);
        return mucDichTaps;
    }
}
