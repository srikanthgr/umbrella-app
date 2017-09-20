package com.foo.umbrella.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.foo.umbrella.R;


public class Utils {

    public static String getSharedPreferenceUnits(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(context.getString(R.string.key_units), context.getString(R.string.default_metric_value));
    }
}
