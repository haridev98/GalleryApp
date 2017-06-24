package com.hd.galleryapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Haridev on 22-06-2017.
 */

public class message {
    public static void message(Context context, String s){
        Toast.makeText(context,s, Toast.LENGTH_LONG).show();
    }
}
