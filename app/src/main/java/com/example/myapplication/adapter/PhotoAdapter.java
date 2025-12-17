package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.util.List;

public class PhotoAdapter extends BaseAdapter {

    private final List<String> paths;
    private final LayoutInflater inflater;

    public PhotoAdapter(Context ctx, List<String> paths) {
        this.paths = paths;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override public int getCount() { return paths.size(); }
    @Override public Object getItem(int position) { return paths.get(position); }
    @Override public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Holder h;

        if (v == null) {
            v = inflater.inflate(R.layout.item_photo, parent, false);
            h = new Holder();
            h.img = v.findViewById(R.id.imgPhotoItem);
            v.setTag(h);
        } else {
            h = (Holder) v.getTag();
        }

        String path = paths.get(position);
        h.img.setImageBitmap(loadThumb(path));

        return v;
    }

    private Bitmap loadThumb(String path) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opt);

        opt.inSampleSize = calculateInSampleSize(opt, 300, 300);
        opt.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, opt);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqW, int reqH) {
        int h = options.outHeight;
        int w = options.outWidth;
        int inSampleSize = 1;

        while ((h / inSampleSize) > reqH && (w / inSampleSize) > reqW) {
            inSampleSize *= 2;
        }
        return inSampleSize;
    }

    static class Holder {
        ImageView img;
    }
}
