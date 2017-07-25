package controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.PrefModel;

/**
 * Created by Nikola on 7/21/2017.
 */

public class MyPreferencesManager<T> {

    private static final String TAG = "MyPreferencesManager";
    private Context context;
    private static final String PREF_NAME = "dvc_pref#7ruyd73fwh";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    MyPreferencesManager(Context context) {
        this.context = context;
        int PRIVATE_MODE = 0;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();

    }


    public void storeList(List<?> list, PrefModel prefModel) {
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Log.d(TAG, "Saved List: " + gson.toJson(list));
        editor.putString(prefModel.getPrefName(), gson.toJson(list));
        editor.apply();
    }


    public void storeSingleObject(Object object, PrefModel prefModel) {
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Log.d(TAG, "Saved Object: " + gson.toJson(object));
        editor.putString(prefModel.getPrefName(), gson.toJson(object));
        editor.apply();
    }


    public List<?> getStoredList(PrefModel prefModel) {
        List<?> storedList = new ArrayList<>();
        Gson gson = new Gson();

        Object[] storedModel;

        String json = sharedPreferences.getString(prefModel.getPrefName(), null);
        storedModel = (Object[]) gson.fromJson(json, prefModel.getClassObject());

        if (storedModel == null)
            return null;

        storedList = Arrays.asList(storedModel);
        storedList = new ArrayList<>(storedList);

        return storedList;

    }


    public Object getStoredObject(PrefModel prefModel) {

        Object storedModel;
        Gson gson = new Gson();

        String json = sharedPreferences.getString(prefModel.getPrefName(), null);
        storedModel = gson.fromJson(json, prefModel.getClassObject());

        if (storedModel == null)
            return null;

        return storedModel;

    }


    public void storeBoolean(boolean value, PrefModel prefkey) {
        editor.putBoolean(prefkey.getPrefName(), value);
        editor.apply();
    }


    public Boolean getStoredBoolean(PrefModel prefModel) {

        return sharedPreferences.getBoolean(prefModel.getPrefName(), prefModel.getDefaultValueBoolean());

    }


    public void storeIntValue(int value, PrefModel prefkey) {
        editor.putInt(prefkey.getPrefName(), value);
        editor.apply();
    }


    public int getStoredInt(PrefModel prefModel) {

        return sharedPreferences.getInt(prefModel.getPrefName(), prefModel.getDefaultValueInt());

    }


}
