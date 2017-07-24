package controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import constants.PrefKeys;

/**
 * Created by Nikola on 7/21/2017.
 */

public class MyPreferencesManager<T> {

    private static final String TAG = "MyPreferencesManager";
    private Context context;
    private static final String PREF_NAME = "ordex_p_pre#7ruyd73fwh";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    MyPreferencesManager(Context context) {
        this.context = context;
        int PRIVATE_MODE = 0;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();

    }


    public void storeList(List<?> list, PrefKeys prefModel) {
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Log.d(TAG, "Saved List: " + gson.toJson(list));
        editor.putString(prefModel.name(), gson.toJson(list));
        editor.apply();
    }


    public void storeSingleObject(Object object, PrefKeys prefModel) {
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Log.d(TAG, "Saved Object: " + gson.toJson(object));
        editor.putString(prefModel.name(), gson.toJson(object));
        editor.apply();
    }


    public List<?> getStoredList(PrefKeys prefModel) {
        List<?> storedList = new ArrayList<>();
        Gson gson = new Gson();

        Object[] storedModel;

        String json = sharedPreferences.getString(prefModel.name(), null);
        storedModel = (Object[]) gson.fromJson(json, prefModel.getClassObject());

        if (storedModel == null)
            return null;

        storedList = Arrays.asList(storedModel);
        storedList = new ArrayList<>(storedList);

        return storedList;

    }


    public Object getStoredObject(PrefKeys prefModel) {

        Object storedModel;
        Gson gson = new Gson();

        String json = sharedPreferences.getString(prefModel.name(), null);
        storedModel = gson.fromJson(json, prefModel.getClassObject());

        if (storedModel == null)
            return null;

        return storedModel;

    }


    public void storeBoolean(boolean value, PrefKeys prefkey) {
        editor.putBoolean(prefkey.name(), value);
        editor.apply();
    }


    public Boolean getStoredBoolean(PrefKeys prefModel) {

        return sharedPreferences.getBoolean(prefModel.name(), prefModel.getDefaultValue());

    }


}
