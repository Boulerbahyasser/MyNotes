package com.example.myapplication.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etNom, etDesc, etDate;
    private Spinner spPriorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etNom = findViewById(R.id.etNom);
        etDesc = findViewById(R.id.etDesc);
        etDate = findViewById(R.id.etDate);
        spPriorite = findViewById(R.id.spPriorite);
        Button btnSave = findViewById(R.id.btnSave);

        String[] priorities = {"Basse", "Moyenne", "Haute"};
        spPriorite.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorities));

        etDate.setOnClickListener(v -> showDatePicker());

        btnSave.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            String desc = etDesc.getText().toString().trim();
            String date = etDate.getText().toString().trim();
            String prio = spPriorite.getSelectedItem().toString();

            if (nom.isEmpty() || desc.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Remplis tous les champs.", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent data = new Intent();
            data.putExtra(NoteListActivity.EXTRA_NOM, nom);
            data.putExtra(NoteListActivity.EXTRA_DESC, desc);
            data.putExtra(NoteListActivity.EXTRA_DATE, date);
            data.putExtra(NoteListActivity.EXTRA_PRIORITE, prio);

            setResult(RESULT_OK, data);
            finish(); // important
        });
    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            String mm = String.format("%02d", month + 1);
            String dd = String.format("%02d", dayOfMonth);
            etDate.setText(year + "-" + mm + "-" + dd);
        }, y, m, d);

        dlg.show();
    }
}
