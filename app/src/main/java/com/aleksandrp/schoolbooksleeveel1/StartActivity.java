package com.aleksandrp.schoolbooksleeveel1;

import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.aleksandrp.schoolbooksleeveel1.adapter.TabAdapter;
import com.aleksandrp.schoolbooksleeveel1.db.functions_db.DBImpl;
import com.aleksandrp.schoolbooksleeveel1.frament.BooksFragment;
import com.aleksandrp.schoolbooksleeveel1.frament.GDZFragment;
import com.aleksandrp.schoolbooksleeveel1.get_and_view_books.GetAndShowFile;
import com.aleksandrp.schoolbooksleeveel1.values.StaticValues;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        StaticValues {

    private GetAndShowFile getAndShowFile;

    public static int selectLevel = 1;

    private DBImpl db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = new DBImpl(StartActivity.this);
        db.openDb();

        setUi();
    }

    private void setUi() {

        FragmentManager mFragmentManager = getFragmentManager();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(toolbar);
        }

        getAndShowFile = new GetAndShowFile(StartActivity.this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.books));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.gdz));

        final ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        TabAdapter mTabAdapter = new TabAdapter(mFragmentManager, COUNT_FRAGMENTS);

        mViewPager.setAdapter(mTabAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.level_1) {
            if (selectLevel != 1)selectLevel = 1;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_2) {
            if (selectLevel != 2)selectLevel = 2;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_3) {
            if (selectLevel != 31)selectLevel = 3;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_4) {
            if (selectLevel != 4)selectLevel = 4;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_5) {
            if (selectLevel != 5)selectLevel = 5;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_6) {
            if (selectLevel != 6)selectLevel = 6;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_7) {
            if (selectLevel != 7)selectLevel = 7;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_8) {
            if (selectLevel != 8)selectLevel = 8;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_9) {
            if (selectLevel != 9)selectLevel = 9;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_10) {
            if (selectLevel != 10)selectLevel = 10;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_11) {
            if (selectLevel != 11)selectLevel = 11;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        return true;
    }

    private void updateData() {
        BooksFragment.getInstance().updateList();
        GDZFragment.getInstance().update();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
