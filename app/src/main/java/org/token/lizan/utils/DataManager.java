package org.token.lizan.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class DataManager {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public DataManager(Context context) {
        preferences = context.getSharedPreferences(getClass().getSimpleName(),Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setFirstEntry(boolean b){
        editor.putBoolean(Contacts.FIRST_ENTRY,b);
        editor.apply();
    }

    public boolean isFirstEntry(){
        return preferences.getBoolean(Contacts.FIRST_ENTRY,true);
    }

    public boolean isFirstSetLanguage(){
        return preferences.getBoolean(Contacts.FIRST_SET_LANGUAGE,true);
    }
    public void setFirstSetLanguage(boolean b){
        editor.putBoolean(Contacts.FIRST_SET_LANGUAGE,b);
        editor.apply();
    }
}
