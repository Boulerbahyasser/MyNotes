package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NoteAdapter;
import com.example.myapplication.model.Note;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {

    public static final String EXTRA_NOM = "EXTRA_NOM";
    public static final String EXTRA_DESC = "EXTRA_DESC";
    public static final String EXTRA_DATE = "EXTRA_DATE";
    public static final String EXTRA_PRIORITE = "EXTRA_PRIORITE";

    private final ArrayList<Note> notes = new ArrayList<>();
    private NoteAdapter adapter;

    private final ActivityResultLauncher<Intent> addNoteLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String nom = data.getStringExtra(EXTRA_NOM);
                    String desc = data.getStringExtra(EXTRA_DESC);
                    String date = data.getStringExtra(EXTRA_DATE);
                    String prio = data.getStringExtra(EXTRA_PRIORITE);

                    notes.add(new Note(nom, desc, date, prio));
                    adapter.notifyDataSetChanged();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        ListView listView = findViewById(R.id.listViewNotes);
        ImageButton btnAdd = findViewById(R.id.btnAdd);
        ImageButton btnPhotos = findViewById(R.id.btnPhotos);

        // exemples (tu peux enlever)
        notes.add(new Note("Cours Android", "Créer 3 activités", "2025-12-17", "Moyenne"));
        notes.add(new Note("TP2 MyNotes", "Adapter + intents + finish()", "2025-12-18", "Haute"));

        adapter = new NoteAdapter(this, notes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Note n = notes.get(position);
            Intent i = new Intent(NoteListActivity.this, DetailsNoteActivity.class);
            i.putExtra(EXTRA_NOM, n.getNom());
            i.putExtra(EXTRA_DESC, n.getDescription());
            i.putExtra(EXTRA_DATE, n.getDate());
            i.putExtra(EXTRA_PRIORITE, n.getPriorite());
            startActivity(i);
        });

        btnAdd.setOnClickListener(v -> {
            Intent i = new Intent(NoteListActivity.this, AddNoteActivity.class);
            addNoteLauncher.launch(i);
        });

        btnPhotos.setOnClickListener(v -> {
            Intent i = new Intent(NoteListActivity.this, com.example.myapplication.ui.PhotoCaptureActivity.class);
            startActivity(i);
        });
    }
}
