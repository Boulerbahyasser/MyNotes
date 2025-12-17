package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Note;

import java.util.List;

public class NoteAdapter extends BaseAdapter {

    private final Context context;
    private final List<Note> notes;
    private final LayoutInflater inflater;

    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
        this.inflater = LayoutInflater.from(context);
    }

    @Override public int getCount() { return notes.size(); }
    @Override public Object getItem(int position) { return notes.get(position); }
    @Override public long getItemId(int position) { return position; }

    private int colorForPriority(String priority) {
        if (priority == null) return Color.LTGRAY;
        switch (priority) {
            case "Haute": return Color.parseColor("#FFCDD2");   // rouge clair
            case "Moyenne": return Color.parseColor("#FFE0B2"); // orange clair
            case "Basse": return Color.parseColor("#C8E6C9");   // vert clair
            default: return Color.LTGRAY;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder h;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_note, parent, false);
            h = new ViewHolder();
            h.tvNom = convertView.findViewById(R.id.tvNom);
            h.tvDate = convertView.findViewById(R.id.tvDate);
            h.priorityBar = convertView.findViewById(R.id.priorityBar);
            convertView.setTag(h);
        } else {
            h = (ViewHolder) convertView.getTag();
        }

        Note n = notes.get(position);
        h.tvNom.setText(n.getNom());
        h.tvDate.setText(n.getDate());
        h.priorityBar.setBackgroundColor(colorForPriority(n.getPriorite()));

        return convertView;
    }

    static class ViewHolder {
        TextView tvNom;
        TextView tvDate;
        View priorityBar;
    }
}
