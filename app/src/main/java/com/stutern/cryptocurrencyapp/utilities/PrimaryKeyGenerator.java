package com.stutern.cryptocurrencyapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.core.content.ContextCompat;

import com.stutern.cryptocurrencyapp.R;

public class PrimaryKeyGenerator {

    public int getPrimaryKey(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        int primaryKey = sharedPref.getInt(context.getResources().getString(R.string.database_primary_key), 0);
        primaryKey++;

        final SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(context.getResources().getString(R.string.database_primary_key), primaryKey);
        editor.commit();

        return primaryKey;

    }
}
