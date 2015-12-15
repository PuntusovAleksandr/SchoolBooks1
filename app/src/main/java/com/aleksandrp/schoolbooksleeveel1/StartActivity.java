package com.aleksandrp.schoolbooksleeveel1;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aleksandrp.schoolbooksleeveel1.adapter.TabAdapter;
import com.aleksandrp.schoolbooksleeveel1.db.functions_db.DBImpl;
import com.aleksandrp.schoolbooksleeveel1.frament.BooksFragment;
import com.aleksandrp.schoolbooksleeveel1.frament.GDZFragment;
import com.aleksandrp.schoolbooksleeveel1.get_and_view_books.GetAndShowFile;
import com.aleksandrp.schoolbooksleeveel1.social_networks.SocialNetworksActivity;
import com.aleksandrp.schoolbooksleeveel1.values.StaticValues;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        StaticValues {

    private GetAndShowFile getAndShowFile;

    public static int selectItem = DEF_COUNT_ITEMS;

    private DBImpl db;

    public static ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setUi();
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setIndeterminateDrawable(this.getResources()
                .getDrawable(R.drawable.download_icon));
        printHashKey();
    }


    // for social network FACEBOOK
    public void printHashKey() {
        try {
            PackageInfo info = getApplicationContext().getPackageManager().getPackageInfo("com.aleksandrp.schoolbooksleeveel1",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("HASH KEY:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = DBImpl.getInstanceDB(StartActivity.this);
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
            finish();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.level_all) {
            if (selectItem != 100) selectItem = 100;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_1) {
            if (selectItem != 1) selectItem = 1;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_2) {
            if (selectItem != 2) selectItem = 2;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_3) {
            if (selectItem != 3) selectItem = 3;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_4) {
            if (selectItem != 4) selectItem = 4;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_5) {
            if (selectItem != 5) selectItem = 5;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_6) {
            if (selectItem != 6) selectItem = 6;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_7) {
            if (selectItem != 7) selectItem = 7;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_8) {
            if (selectItem != 8) selectItem = 8;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_9) {
            if (selectItem != 9) selectItem = 9;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_10) {
            if (selectItem != 10) selectItem = 10;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_11) {
            if (selectItem != 11) selectItem = 11;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.level_12) {
            if (selectItem != 12) selectItem = 12;
            updateData();
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(StartActivity.this, SocialNetworksActivity.class);
            Bundle bndlanimation =
                    ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anime_to,
                            R.anim.anime_from).toBundle();
            startActivity(intent, bndlanimation);
//            startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_send) {
            Toast.makeText(StartActivity.this, "Этот раздел нахоится в разработке", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void updateData() {
        BooksFragment.getInstance().updateList();
        GDZFragment.getInstance().update();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
