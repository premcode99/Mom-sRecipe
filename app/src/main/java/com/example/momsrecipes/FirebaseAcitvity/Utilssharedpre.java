package com.example.momsrecipes.FirebaseAcitvity;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.share.Share;

public class Utilssharedpre {

    final static String filename = "MyFileName";

    public static String readsharedsetting(Context context,String settingname,String defaultvalue)
    {
        SharedPreferences preferences =context.getSharedPreferences(filename,context.MODE_PRIVATE);
        return preferences.getString(settingname,defaultvalue);
    }

    public static void saveSharedSetting(Context context,String settingname, String settingvalue)
    {
        SharedPreferences preferences = context.getSharedPreferences(filename,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(settingname,settingvalue);
        editor.apply();
    }

    public static void shareprefsave(Context context,String name)
    {
        SharedPreferences preferences = context.getSharedPreferences("name",0);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("name",name);
        editor.commit();
    }

}
