package com.example.prmfinal.Client.constant.listModel;

import com.example.prmfinal.Client.constant.model.MuscleFocus;

import java.util.ArrayList;
import java.util.List;

public class ListMuscleFocus {
    public static List<String> getAll(){
        List<String> muscles=new ArrayList<>();
        muscles.add(MuscleFocus.Chest);
        muscles.add(MuscleFocus.Abs);
        muscles.add(MuscleFocus.Back);
        muscles.add(MuscleFocus.Arms);
        muscles.add(MuscleFocus.Legs);
        muscles.add(MuscleFocus.FullBody);
       
        return muscles;
    }
}
