package com.example.eko.androidkamus;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.eko.androidkamus.adapter.SearchAdapter;
import com.example.eko.androidkamus.data.KamusHelper;
import com.example.eko.androidkamus.model.KamusModel;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        MaterialSearchBar.OnSearchActionListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.searchBar)
    MaterialSearchBar searchBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private KamusHelper kamusHelper;
    private SearchAdapter adapter;
    private ArrayList<KamusModel> list = new ArrayList<>();
    private boolean isEnglish= true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        searchBar.setOnSearchActionListener(this);

        kamusHelper = new KamusHelper(this);
        setUpList();
        loadData();
        navigationView.getMenu().getItem(0).setChecked(true);

    }

    @Override
    public void onBackPressed() {
      if (drawerLayout.isDrawerOpen(GravityCompat.START)){
          drawerLayout.closeDrawer(GravityCompat.START);
      }else {
          super.onBackPressed();
      }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_englis_indonesia){
            isEnglish = true;
            loadData();
        }
        if (id == R.id.nav_indonesia_english){
            isEnglish = false;
            loadData();
        }
       drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onSearchStateChanged(boolean enabled) {

    }
    public void onSearchConfirmed(CharSequence text) {

        loadData(String.valueOf(text));
    }
    public void onButtonClicked(int buttonCode){

    }


    private void setUpList(){
        adapter = new SearchAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadData( String search){
        try {
            kamusHelper.open();
            if (search.isEmpty()){
                list = kamusHelper.getAllData(isEnglish);
            }else {
                list = kamusHelper.getDataByName(search, isEnglish);
            }

            if (isEnglish) {
                getSupportActionBar().setSubtitle(getResources().getString(R.string.english_indonesia));
            } else {
                getSupportActionBar().setSubtitle(getResources().getString(R.string.indonesia_english));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            kamusHelper.close();
        }
        adapter.replaceAll(list);
    }

    private void loadData(){
        loadData("");
    }
}
