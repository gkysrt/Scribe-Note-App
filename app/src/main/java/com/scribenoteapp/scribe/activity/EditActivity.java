package com.scribenoteapp.scribe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

/***
 *
 */
public class EditActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText bodyEditText;
    private FloatingActionButton fab;
    private int row;
    private Toolbar toolbar;
    private Note noteObjectToReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        this.setupUi();
        this.initListeners();

        // Get intent that started this activity and check if a note object is sent
        Intent intentStartedThisActivity = this.getIntent();

        this.noteObjectToReturn = new Note(null, null, null);
        if (intentStartedThisActivity.hasExtra("clickedNote"))
        {
            noteObjectToReturn = (Note) intentStartedThisActivity.getParcelableExtra("clickedNote");
            this.bodyEditText.setText(noteObjectToReturn.getBody());
            this.titleEditText.setText(noteObjectToReturn.getTitle());
            // Some other gui setup according to the input will be added here (e.g attachment visuals)
        }

        this.row = intentStartedThisActivity.getIntExtra("noteRow", -1);
    }

    public void setupUi()
    {
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.fab = findViewById(R.id.fab);
        this.titleEditText = findViewById(R.id.create_note_title);
        this.bodyEditText = findViewById(R.id.create_note_body);
    }

    // This is where clicks on back arrow that's on the toolbar are handled
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void initListeners()
    {
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                EditActivity.this.noteObjectToReturn.setBody(EditActivity.this.bodyEditText.getText().toString());
                EditActivity.this.noteObjectToReturn.setTitle(EditActivity.this.titleEditText.getText().toString());

                resultIntent.putExtra("noteResult", EditActivity.this.noteObjectToReturn);
                resultIntent.putExtra("noteRow", EditActivity.this.row);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                Snackbar.make(view, "Note is saved", Snackbar.LENGTH_SHORT)
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

    @Override
    protected void onDestroy() {
        Log.d("plg0,","dpfg");
        setResult(Activity.RESULT_CANCELED, new Intent());
        super.onDestroy();
    }
}
