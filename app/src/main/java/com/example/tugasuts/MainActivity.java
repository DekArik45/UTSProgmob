package com.example.tugasuts;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.tugasuts.adapter.CardMakananAdapter;
import com.example.tugasuts.adapter.GridMakananAdapater;
import com.example.tugasuts.adapter.ListMakananAdapter;
import com.example.tugasuts.data.DataMakanan;
import com.example.tugasuts.model.Makanan;
import com.example.tugasuts.util.DrawerMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMakanan;
    private ArrayList<Makanan> list = new ArrayList<>();

    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;
    Toolbar toolbar;
    ActionBar ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMakanan = findViewById(R.id.main_recyclerview);
        rvMakanan.setHasFixedSize(true);
        toolbar = findViewById(R.id.toolbar);

        this.setSupportActionBar(toolbar);
        ab = this.getSupportActionBar();

//
        DrawerMenu drawer = new DrawerMenu();
        drawer.createDrawer(this, this, toolbar);

        if (savedInstanceState == null) {

            list.addAll(DataMakanan.getListData());
            showRecyclerList();
            mode = R.id.action_list;
        } else {
            ArrayList<Makanan> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);

            list.addAll(stateList);
            setMode(stateMode);
        }

    }

    private void settitle(String title){
        ab.setTitle(title);
    }

    private void showRecyclerList(){
        rvMakanan.setLayoutManager(new LinearLayoutManager(this));
        ListMakananAdapter listMakananAdapter = new ListMakananAdapter(this);
        listMakananAdapter.setListMakanan(list);
        rvMakanan.setAdapter(listMakananAdapter);

        settitle("List View");
    }

    private void showRecyclerGrid(){
        rvMakanan.setLayoutManager(new GridLayoutManager(this, 2));
        GridMakananAdapater gridMakananAdapater = new GridMakananAdapater(this);
        gridMakananAdapater.setListMakanan(list);
        rvMakanan.setAdapter(gridMakananAdapater);

        settitle("Grid View");
    }

    private void showRecyclerCardView(){
        rvMakanan.setLayoutManager(new LinearLayoutManager(this));
        CardMakananAdapter cardViewMakananAdapter = new CardMakananAdapter(this);
        cardViewMakananAdapter.setListMakanan(list);
        rvMakanan.setAdapter(cardViewMakananAdapter);

        settitle("Card View");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());

        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        String title = null;
        switch (selectedMode) {
            case R.id.action_list:
                showRecyclerList();
                break;

            case R.id.action_grid:
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                showRecyclerCardView();
                break;
        }
        mode = selectedMode;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }
}