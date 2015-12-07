package com.aleksandrp.schoolbooksleeveel1.social_networks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.aleksandrp.schoolbooksleeveel1.R;

public class SocialNetworksActivity extends AppCompatActivity {

    private ImageButton ibFaceBook;
    private ImageButton ibVC;
    private ImageButton ibTwiter;
    private ImageButton ibOdnoclassniki;
    private ImageButton ibLinkedIn;
    private ImageButton ibGooglePlues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_networks);

        ibFaceBook = (ImageButton) findViewById(R.id.ib_facebook);
        ibVC = (ImageButton) findViewById(R.id.ib_vcontacte);
        ibTwiter = (ImageButton) findViewById(R.id.ib_twiter);
        ibOdnoclassniki = (ImageButton) findViewById(R.id.ib_odnoclassniki);
        ibLinkedIn = (ImageButton) findViewById(R.id.ib_linkedin);
        ibGooglePlues = (ImageButton) findViewById(R.id.ib_google_plus);

        ibFaceBook.setOnClickListener(listener);
        ibVC.setOnClickListener(listener);
        ibTwiter.setOnClickListener(listener);
        ibOdnoclassniki.setOnClickListener(listener);
        ibLinkedIn.setOnClickListener(listener);
        ibGooglePlues.setOnClickListener(listener);

    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ib_facebook:
                    break;
                case R.id.ib_google_plus:
                    break;
                case R.id.ib_linkedin:
                    break;
                case R.id.ib_odnoclassniki:
                    break;
                case R.id.ib_twiter:
                    break;
                case R.id.ib_vcontacte:
                    break;

            }
        }
    };
}
