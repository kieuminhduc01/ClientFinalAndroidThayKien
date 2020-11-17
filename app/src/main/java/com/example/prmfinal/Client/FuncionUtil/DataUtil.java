package com.example.prmfinal.Client.FuncionUtil;

import android.content.Context;
import android.content.SharedPreferences;

public class DataUtil {
    public static void deleteAllUserNamePassword(Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("id", "");
        editor.putString("userName", "");
        editor.putString("password", "");
        editor.commit();
    }
}
