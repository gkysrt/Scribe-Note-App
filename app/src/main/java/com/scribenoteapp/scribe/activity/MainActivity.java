package com.scribenoteapp.scribe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.scribenoteapp.scribe.adapter.NoteAdapter;
import com.scribenoteapp.scribe.R;
import com.scribenoteapp.scribe.controller.SwipeController;
import com.scribenoteapp.scribe.framework.ModelIndex;
import com.scribenoteapp.scribe.framework.namespace.ModelRole;
import com.scribenoteapp.scribe.framework.slots.Function1;
import com.scribenoteapp.scribe.framework.slots.Function2;
import com.scribenoteapp.scribe.model.NoteModel;
import com.scribenoteapp.scribe.model.note.BaseNote;
import com.scribenoteapp.scribe.model.note.Note;
import com.scribenoteapp.scribe.model.note.NoteFolder;


/**
 *
 */
public class MainActivity extends AppCompatActivity {

    // Defining the variables of main activity
    private NoteAdapter noteAdapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private NoteModel model;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private SwipeController swipeController;
    private ItemTouchHelper itemTouchHelper;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start by instantiating the model
        this.model = new NoteModel();

        // Setup Ui and attach necessary bindings
        this.setupUi();

        // Set listeners
        this.initListeners();

        // Bind signals to relevant slot functions
        this.initSignalsAndSlots();
    }

    public void setupUi()
    {
        // Add customized toolbar view to MainActivity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Define floating
        this.fab = findViewById(R.id.fab);

        // Get drawer layout  and add assign a toggle
        this.drawer = findViewById(R.id.drawer_layout);
        this.toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawer.addDrawerListener(toggle);
        this.toggle.syncState();

        // LinearLayoutManager instantiated and RecyclerView is reached from R
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView = findViewById(R.id.recycler_view);

        // Added separation decoration to recyclerView and LayoutManager is set
        this.recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        this.recyclerView.setLayoutManager(layoutManager);

        // SwipeController is instantiated for recyclerView
        this.swipeController = new SwipeController();
        this.itemTouchHelper = new ItemTouchHelper(swipeController);
        this.itemTouchHelper.attachToRecyclerView(recyclerView);

        // Instantiate adapter with model then set as RecyclerView's adapter
        this.noteAdapter = new NoteAdapter(this.model);
        this.recyclerView.setAdapter(this.noteAdapter);
        this.navigationView = findViewById(R.id.nav_view);
    }

    public void initListeners()
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_camera) {
                    // Handle the camera action
                } else if (id == R.id.nav_gallery) {

                } else if (id == R.id.nav_slideshow) {

                } else if (id == R.id.nav_manage) {

                } else if (id == R.id.nav_share) {

                } else if (id == R.id.nav_send) {

                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note("Test", "Bu bir test");
                MainActivity.this.model.addItem(note);
            }
        });
    }

    public void initSignalsAndSlots()
    {
        this.noteAdapter.itemSelectedSignal.connect("recyclerViewSelectedItem", new Function2<Integer, Boolean, Void>() {
            @Override
            public Void function(Integer position, Boolean aBoolean) {
                // scroll item to ensure it is visible
                recyclerView.getLayoutManager().scrollToPosition(position);
                return null;
            }
        });

        this.noteAdapter.itemClickedSignal.connect("modelChangeClickedItem", new Function1<ModelIndex, Void>() {
            @Override
            public Void function(ModelIndex modelIndex) {
                BaseNote baseNote = (BaseNote) modelIndex.data(ModelRole.USER_ROLE);
                if (baseNote instanceof NoteFolder) {
                    MainActivity.this.model.setCurrentFolder((NoteFolder) baseNote);
                }
                else
                {
                    Intent intentToStartEditNoteActivity = new Intent(MainActivity.this, EditActivity.class);
                    intentToStartEditNoteActivity.putExtra("clickedNote", MainActivity.this.model.getItem(modelIndex));
                    intentToStartEditNoteActivity.putExtra("noteRow", modelIndex.row());
                    MainActivity.this.startActivityForResult(intentToStartEditNoteActivity, 0);
                }
                return null;
            }
        });
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("Bundle", savedInstanceState.getString("Test"));
    }

    // Invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("Test", "Heyo");

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    // Invoked when user presses back button on MainActivity
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (this.model.getCurrentFolder() != this.model.rootFolder())
        {
            this.model.setCurrentFolder(this.model.getCurrentFolder().getParent());
        }
        else {
            super.onBackPressed();
        }
    }

    // Here MainActivity handles any result and data returned from a child activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                if(data.hasExtra("noteResult") && data.hasExtra("noteRow"))
                {
                    int noteRowToChange = data.getIntExtra("noteRow", -1);
                    Note resultNoteObject = data.getParcelableExtra("noteResult");
                    this.model.setData(this.model.index(noteRowToChange, 0), resultNoteObject);
                }
            }
        }

        if (requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                if (data.hasExtra("noteResult"))
                {
                    Note resultNoteObject = data.getParcelableExtra("noteResult");
                    this.model.addItem(resultNoteObject);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // Here MainActivity creates its related menus, e.g '+' button on toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Here MainActivity handles clicks on menu items, e.g '+' button on toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_note_button) {
            Intent intentToStartEditNoteActivity = new Intent(this, EditActivity.class);
            startActivityForResult(intentToStartEditNoteActivity, 1);
        }

        return super.onOptionsItemSelected(item);
    }

}
