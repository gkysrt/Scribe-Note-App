package com.scribenoteapp.scribe.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scribenoteapp.scribe.R;
import com.scribenoteapp.scribe.model.note.Note;

/***
 *
 */
public class EditActivity extends AppCompatActivity {

//    private EditText bodyEditText;
    private FloatingActionButton fab;
    private int row;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Note noteObjectToReturn;
    private Window editActivityWindow;
    private ImageView toolbarBackground;

    private TextView tvTitle;
    private TextView tvBody;
    private EditText etTitle;
    private EditText etBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_edit);
        this.setupUi();
        this.initListeners();

        // Get intent that started this activity and check if a note object is sent
        Intent intentStartedThisActivity = this.getIntent();

        this.noteObjectToReturn = new Note(null, null, null);
        if (intentStartedThisActivity.hasExtra("clickedNote"))
        {
            // TODO: If preview mode enabled when activity starts: implement and trigger previewMode()
            this.noteObjectToReturn = (Note) intentStartedThisActivity.getParcelableExtra("clickedNote");

            this.tvBody.setText(noteObjectToReturn.getBody());
            this.tvTitle.setText(noteObjectToReturn.getTitle());

            this.etBody.setText(noteObjectToReturn.getBody());
            this.etTitle.setText(noteObjectToReturn.getTitle());
            // TODO: Some other gui setup according to the input will be added here (e.g attachment visuals)
        }

        this.row = intentStartedThisActivity.getIntExtra("noteRow", -1);
    }

    public void setupUi()
    {
        int scrimColor = this.colorFromResource(R.drawable.dummy_background);

        this.toolbarBackground = findViewById(R.id.toolbar_background);
        this.toolbarBackground.setImageResource(R.drawable.dummy_background);

        this.collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        this.collapsingToolbarLayout.setContentScrimColor(scrimColor);
        this.collapsingToolbarLayout.setStatusBarScrimColor(scrimColor);

        // Customize StatusBar color
        this.editActivityWindow = this.getWindow();
        this.editActivityWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.editActivityWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.editActivityWindow.setStatusBarColor(scrimColor);

        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.fab = findViewById(R.id.multipurposeFab);

        this.tvTitle = findViewById(R.id.tv_create_note_title);
        this.tvBody = findViewById(R.id.tv_create_note_body);
        this.etTitle = findViewById(R.id.create_note_title);
        this.etBody = findViewById(R.id.create_note_body);
    }

    // This is where clicks on back arrow that's on the toolbar are handled
    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }

    public void initListeners()
    {
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                EditActivity.this.noteObjectToReturn.setBody(EditActivity.this.etBody.getText().toString());
                EditActivity.this.noteObjectToReturn.setTitle(EditActivity.this.etTitle.getText().toString());

                resultIntent.putExtra("noteResult", EditActivity.this.noteObjectToReturn);
                resultIntent.putExtra("noteRow", EditActivity.this.row);
                setResult(Activity.RESULT_OK, resultIntent);

                finish();
            }
        });

        this.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTitle.setVisibility(View.INVISIBLE);
                etTitle.setVisibility(View.VISIBLE);
                etTitle.requestFocus();
            }
        });

        this.tvBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvBody.setVisibility(View.INVISIBLE);
                etBody.setVisibility(View.VISIBLE);
                etBody.requestFocus();
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
        setResult(Activity.RESULT_CANCELED, new Intent());
        super.onDestroy();
    }

    public int colorFromResource(int id)
    {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(getResources(), id);
        Palette palette = Palette.from(bitmap).generate();

//        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
//        Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
//        Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
//        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
//        Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
//        Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
        Palette.Swatch dominantSwatch = palette.getDominantSwatch();

        return dominantSwatch != null ? dominantSwatch.getRgb() : 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }
}
