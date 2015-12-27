package com.aleksandrp.schoolbooksleeveel1.social_networks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.aleksandrp.schoolbooksleeveel1.R;

public class SocialNetworksActivity extends ActionBarActivity implements FragmentManager.OnBackStackChangedListener {

    public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK_TAG";
    private static ProgressDialog pd;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_main);
        context = this;
        getSupportFragmentManager().addOnBackStackChangedListener(this);
//        homeAsUpByBackStack();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SocialFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onBackStackChanged() {
//        homeAsUpByBackStack();
    }

    private void homeAsUpByBackStack() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            try {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exeption :: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected static void showProgress(String message) {
        pd = new ProgressDialog(context);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage(message);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    protected static void hideProgress() {
        pd.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}

//
//{
//
//    private ImageButton ibFaceBook;
//    private ImageButton ibVC;
//    private ImageButton ibTwiter;
//    private ImageButton ibOdnoclassniki;
//    private ImageButton ibLinkedIn;
//    private ImageButton ibGooglePlues;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.social_networks);
//
//        ibFaceBook = (ImageButton) findViewById(R.id.ib_facebook);
//        ibVC = (ImageButton) findViewById(R.id.ib_vcontacte);
//        ibTwiter = (ImageButton) findViewById(R.id.ib_twiter);
//        ibOdnoclassniki = (ImageButton) findViewById(R.id.ib_odnoclassniki);
//        ibLinkedIn = (ImageButton) findViewById(R.id.ib_linkedin);
//        ibGooglePlues = (ImageButton) findViewById(R.id.ib_google_plus);
//
//        ibFaceBook.setOnClickListener(listener);
//        ibVC.setOnClickListener(listener);
//        ibTwiter.setOnClickListener(listener);
//        ibOdnoclassniki.setOnClickListener(listener);
//        ibLinkedIn.setOnClickListener(listener);
//        ibGooglePlues.setOnClickListener(listener);
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//        overridePendingTransition(R.anim.anime_to, R.anim.anime_from);
//    }
//
//    View.OnClickListener listener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.ib_facebook:
//                    break;
//                case R.id.ib_google_plus:
//                    break;
//                case R.id.ib_linkedin:
//                    break;
//                case R.id.ib_odnoclassniki:
//                    break;
//                case R.id.ib_twiter:
//                    break;
//                case R.id.ib_vcontacte:
//                    break;
//            }
//        }
//    };
//}
