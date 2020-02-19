package com.scribenoteapp.scribe.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import com.scribenoteapp.scribe.model.note.NoteFolder;


/**
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add customized toolbar view to MainActivity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Lower toolbar definition
        final Toolbar recyclerViewToolbar = findViewById(R.id.recycler_view_toolbar);

        // Define floating
        this.fab = findViewById(R.id.fab);
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewToolbar.setVisibility(View.INVISIBLE);
            }
        });

        // Get drawer layout  and add assign a toggle
        this.drawer = findViewById(R.id.drawer_layout);
        this.toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawer.addDrawerListener(toggle);
        this.toggle.syncState();

        // LinearLayoutManager instantiated and RecyclerView is reached from R
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView = findViewById(R.id.recycler_view);

        // Added seperation decoration to recyclerView and LayoutManager is set
        this.recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        this.recyclerView.setLayoutManager(layoutManager);

        // SwipeController is instantiated for recyclerView
        this.swipeController = new SwipeController();
        this.itemTouchHelper = new ItemTouchHelper(swipeController);
        this.itemTouchHelper.attachToRecyclerView(recyclerView);
        this.model = new NoteModel();

        // Instantiate adapter with model then set as RecyclerView's adapter
        this.noteAdapter = new NoteAdapter(this.model);
        this.recyclerView.setAdapter(this.noteAdapter);

        this.initSignalsAndSlots();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void initSignalsAndSlots()
    {

        this.noteAdapter.getItemSelectedSignal().connect("recyclerViewSelectedItem", new Function2<Integer, Boolean, Void>() {
            @Override
            public Void function(Integer position, Boolean aBoolean) {
                // scroll item to ensure it is visible
                recyclerView.getLayoutManager().scrollToPosition(position);
                return null;
            }
        });
        this.noteAdapter.getItemClickedSignal().connect("modelChangeClickedItem", new Function1<ModelIndex, Void>() {
            @Override
            public Void function(ModelIndex modelIndex) {
                Object baseNote = modelIndex.data(ModelRole.USER_ROLE);
                if (baseNote instanceof NoteFolder) {
                    NoteModel model = (NoteModel) modelIndex.model();
                    model.setCurrentFolder(modelIndex);
                }
                return null;
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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

}
