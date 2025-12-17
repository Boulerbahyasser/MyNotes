package com.example.myapplication.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class DetailsNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_note);

        TextView tvNom = findViewById(R.id.tvNomDetails);
        TextView tvDesc = findViewById(R.id.tvDescDetails);
        TextView tvDate = findViewById(R.id.tvDateDetails);
        TextView tvPrio = findViewById(R.id.tvPrioDetails);
        Button btnBack = findViewById(R.id.btnBack);

        tvNom.setText(getIntent().getStringExtra(NoteListActivity.EXTRA_NOM));
        tvDesc.setText(getIntent().getStringExtra(NoteListActivity.EXTRA_DESC));
        tvDate.setText(getIntent().getStringExtra(NoteListActivity.EXTRA_DATE));
        tvPrio.setText(getIntent().getStringExtra(NoteListActivity.EXTRA_PRIORITE));

        btnBack.setOnClickListener(v -> finish());
    }
}
