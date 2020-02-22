package com.scribenoteapp.scribe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import com.scribenoteapp.scribe.R;
import com.scribenoteapp.scribe.model.note.Note;

public class EditActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText bodyEditText;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        this.setupUi();
        this.initListeners();

        // Get intent that started this activity and check if a note object is sent
        Intent intentStartedThisActivity = this.getIntent();
        Note retrievedNoteObject = null;
        if (intentStartedThisActivity.hasExtra("clickedNote"))
        {
            retrievedNoteObject = (Note) intentStartedThisActivity.getParcelableExtra("clickedNote");
            this.bodyEditText.setText(retrievedNoteObject.getBody());
            this.titleEditText.setText(retrievedNoteObject.getTitle());
        }
    }

    public void setupUi()
    {
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.fab = findViewById(R.id.fab);
        this.titleEditText = findViewById(R.id.create_note_title);
        this.bodyEditText = findViewById(R.id.create_note_body);
    }

    public void initListeners()
    {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.edit, menu);
        return true;
    }
}
