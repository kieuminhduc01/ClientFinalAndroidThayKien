package com.example.prmfinal.Client.logicUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomSuffle {


    public RandomSuffle(int maxNumber) {

    }

    public static List<Integer> getFiveNumber(int size) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        if (list.size() > 4){

            return list.subList(0, 4);
        }
        else {
            return list;
        }
    }
}
