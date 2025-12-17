package com.example.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;

import java.util.ArrayList;

public class PhotoStorage {

    private static final String PREFS = "photos_prefs";
    private static final String KEY = "photo_paths";

    public static void addPhotoPath(Context ctx, String path) {
        ArrayList<String> all = getPhotoPaths(ctx);
        all.add(0, path); // newest first
        save(ctx, all);
    }

    public static ArrayList<String> getPhotoPaths(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String raw = sp.getString(KEY, "[]");
        ArrayList<String> list = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(raw);
            for (int i = 0; i < arr.length(); i++) list.add(arr.getString(i));
        } catch (Exception ignored) {}
        return list;
    }

    private static void save(Context ctx, ArrayList<String> list) {
        JSONArray arr = new JSONArray();
        for (String s : list) arr.put(s);

        SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        sp.edit().putString(KEY, arr.toString()).apply();
    }
}
