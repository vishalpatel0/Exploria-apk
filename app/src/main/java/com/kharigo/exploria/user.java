package com.kharigo.exploria;

import android.content.Context;
import android.content.SharedPreferences;

public class user {


    Context context;
    SharedPreferences sharedPreferences;

    private String m;
    private String p;
    private String name;
    private String email;
    private String info;

    public void x() {
        sharedPreferences.edit().clear().commit();
    }

    public String getM() {
        m = sharedPreferences.getString("m","");
        return m;
    }
    public String getP() {
        p = sharedPreferences.getString("p","");
        return p;
    }
    public String getName() {
        name = sharedPreferences.getString("name","");
        return name;
    }
    public String getInfo() {
        info = sharedPreferences.getString("info","");
        return info;
    }



    public void set_log(String m, String p, String name, String info) {
        this.m = m;
        this.p = p;
        this.name = name;
        this.info = info;
        sharedPreferences.edit().putString("m",m).commit();
        sharedPreferences.edit().putString("p",p).commit();
        sharedPreferences.edit().putString("name",name).commit();
        sharedPreferences.edit().putString("info",info).commit();
    }


    public user(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("my_log", Context.MODE_PRIVATE);
    }



}
