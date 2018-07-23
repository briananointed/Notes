package com.example.brianwachira.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    EditText editText;

    int noteId;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(NoteEditorActivity.this, "Note saved", Toast.LENGTH_SHORT).show();
        Log.i("Backkey","Back key pressed");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        Intent intent = getIntent();
        editText = findViewById(R.id.editText);

         noteId = intent.getIntExtra("noteId",-1);

        if(noteId !=-1){

          editText.setText(MainActivity.notes.get(noteId));
        }else{

            MainActivity.notes.add("New note");
            noteId = MainActivity.notes.size() - 1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                MainActivity.notes.set(noteId,String.valueOf(s));

                MainActivity.arrayAdapter.notifyDataSetChanged();


                HashSet<String> set = new HashSet<>(MainActivity.notes);

                MainActivity.sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
