package com.aleksandrp.schoolbooksleeveel1.social_networks;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aleksandrp.schoolbooksleeveel1.R;
import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.core.listener.OnLoginCompleteListener;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.googleplus.GooglePlusSocialNetwork;
import com.github.gorbin.asne.linkedin.LinkedInSocialNetwork;
import com.github.gorbin.asne.odnoklassniki.OkSocialNetwork;
import com.github.gorbin.asne.twitter.TwitterSocialNetwork;
import com.github.gorbin.asne.vk.VkSocialNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.ok.android.sdk.util.OkScope;

public class SocialFragment extends Fragment implements SocialNetworkManager.OnInitializationCompleteListener, OnLoginCompleteListener {
    public static SocialNetworkManager mSocialNetworkManager;
    /**
     * SocialNetwork Ids in ASNE:
     * 1 - Twitter
     * 2 - LinkedIn
     * 3 - Google Plus
     * 4 - Facebook
     * 5 - Vkontakte
     * 6 - Odnoklassniki
     * 7 - Instagram
     */
    private ImageButton facebook;
    private ImageButton twitter;
    private ImageButton linkedin;
    private ImageButton googleplus;
    private ImageButton odnoklassniki;
    private ImageButton vk;

    private TextView textFaceBook;
    ;
    private TextView textTwitter;
    private TextView textLinked;
    private TextView textGoogle;
    private TextView textOdnoklassniki;
    private TextView textVk;

    public SocialFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.social_networks, container, false);
//        ((SocialNetworksActivity)getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        // init buttons and set Listener
        facebook = (ImageButton) rootView.findViewById(R.id.ib_facebook);
        facebook.setOnClickListener(loginClick);
        twitter = (ImageButton) rootView.findViewById(R.id.ib_twitter);
        twitter.setOnClickListener(loginClick);
        linkedin = (ImageButton) rootView.findViewById(R.id.ib_linkedin);
        linkedin.setOnClickListener(loginClick);
        googleplus = (ImageButton) rootView.findViewById(R.id.ib_google_plus);
        googleplus.setOnClickListener(loginClick);
        odnoklassniki = (ImageButton) rootView.findViewById(R.id.ib_odnoclassniki);
        odnoklassniki.setOnClickListener(loginClick);
        vk = (ImageButton) rootView.findViewById(R.id.ib_vkontacte);
        vk.setOnClickListener(loginClick);

        textFaceBook = (TextView) rootView.findViewById(R.id.text_face_book);
        textTwitter = (TextView) rootView.findViewById(R.id.text_twitter);
        textLinked = (TextView) rootView.findViewById(R.id.text_linkedink);
        textGoogle = (TextView) rootView.findViewById(R.id.text_google_plus_social);
        textOdnoklassniki = (TextView) rootView.findViewById(R.id.text_odnoklassniki);
        textVk = (TextView) rootView.findViewById(R.id.text_vk);

        //Get Keys for initiate SocialNetworks
        String TWITTER_CONSUMER_KEY = getActivity().getString(R.string.twitter_consumer_key);
        String TWITTER_CONSUMER_SECRET = getActivity().getString(R.string.twitter_consumer_secret);
        String TWITTER_CALLBACK_URL = "oauth://ASNE";
        String LINKEDIN_CONSUMER_KEY = getActivity().getString(R.string.linkedin_consumer_key);
        String LINKEDIN_CONSUMER_SECRET = getActivity().getString(R.string.linkedin_consumer_secret);
        String LINKEDIN_CALLBACK_URL = "https://asneTutorial";
        String VK_CONSUMER_KEY = getActivity().getString(R.string.vk_app_key);
        String OK_APP_ID = getActivity().getString(R.string.ok_app_id);
        String OK_APP_PUB_KEY = getActivity().getString(R.string.ok_pub_key);
        String OK_APP_SEC_KEY = getActivity().getString(R.string.app_sec_key);


        //Chose permissions
        ArrayList<String> fbScope = new ArrayList<String>();
        fbScope.addAll(Arrays.asList("public_profile, email, user_friends"));
        String linkedInScope = "r_basicprofile+r_fullprofile+rw_nus+r_network+w_messages+r_emailaddress+r_contactinfo";
        String[] vkScope = {"user_id", "photos", "wall", "ads"};
        String[] okScope = {OkScope.VALUABLE_ACCESS, OkScope.PHOTO_CONTENT, OkScope.SET_STATUS};

        //Use manager to manage SocialNetworks
        mSocialNetworkManager = (SocialNetworkManager) getFragmentManager().findFragmentByTag(SocialNetworksActivity.SOCIAL_NETWORK_TAG);

        //Check if manager exist
        if (mSocialNetworkManager == null) {
            mSocialNetworkManager = new SocialNetworkManager();

            //Init and add to manager FacebookSocialNetwork
            FacebookSocialNetwork fbNetwork = new FacebookSocialNetwork(this, fbScope);
            mSocialNetworkManager.addSocialNetwork(fbNetwork);

            //Init and add to manager TwitterSocialNetwork
            TwitterSocialNetwork twNetwork = new TwitterSocialNetwork(this, TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET, TWITTER_CALLBACK_URL);
            mSocialNetworkManager.addSocialNetwork(twNetwork);

            //Init and add to manager LinkedInSocialNetwork
            LinkedInSocialNetwork liNetwork = new LinkedInSocialNetwork(this, LINKEDIN_CONSUMER_KEY, LINKEDIN_CONSUMER_SECRET, LINKEDIN_CALLBACK_URL, linkedInScope);
            mSocialNetworkManager.addSocialNetwork(liNetwork);

            //Init and add to manager GooglePlusSocialNetwork
            GooglePlusSocialNetwork gpNetwork = new GooglePlusSocialNetwork(this);
            mSocialNetworkManager.addSocialNetwork(gpNetwork);

            //Init and add to manager VK
            VkSocialNetwork vkNetwork = new VkSocialNetwork(this, VK_CONSUMER_KEY, vkScope);
            mSocialNetworkManager.addSocialNetwork(vkNetwork);

            //Init and add to manager Odnoklassniki
            OkSocialNetwork odNetwork = new OkSocialNetwork(this, OK_APP_ID, OK_APP_PUB_KEY, OK_APP_SEC_KEY, okScope);
            mSocialNetworkManager.addSocialNetwork(odNetwork);

            //Initiate every network from mSocialNetworkManager
            getFragmentManager().beginTransaction().add(mSocialNetworkManager, SocialNetworksActivity.SOCIAL_NETWORK_TAG).commit();
            mSocialNetworkManager.setOnInitializationCompleteListener(this);
        } else {
            //if manager exist - get and setup login only for initialized SocialNetworks
            if (!mSocialNetworkManager.getInitializedSocialNetworks().isEmpty()) {
                List<SocialNetwork> socialNetworks = mSocialNetworkManager.getInitializedSocialNetworks();
                for (SocialNetwork socialNetwork : socialNetworks) {
                    socialNetwork.setOnLoginCompleteListener(this);
                    initSocialNetwork(socialNetwork);
                }
            }
        }
        return rootView;
    }

    private void initSocialNetwork(SocialNetwork socialNetwork) {
        if (socialNetwork.isConnected()) {
            switch (socialNetwork.getID()) {
                case FacebookSocialNetwork.ID:
                    textFaceBook.setText(R.string.show_profile);
                    break;
                case TwitterSocialNetwork.ID:
                    textTwitter.setText(R.string.show_profile);
                    break;
                case LinkedInSocialNetwork.ID:
                    textLinked.setText(R.string.show_profile);
                    break;
                case GooglePlusSocialNetwork.ID:
                    textGoogle.setText(R.string.show_profile);
                    break;
                case VkSocialNetwork.ID:
                    textVk.setText(R.string.show_profile);
                    break;
                case OkSocialNetwork.ID:
                    textVk.setText(R.string.show_profile);
                    break;
            }
        }
    }

    @Override
    public void onSocialNetworkManagerInitialized() {
        //when init SocialNetworks - get and setup login only for initialized SocialNetworks
        for (SocialNetwork socialNetwork : mSocialNetworkManager.getInitializedSocialNetworks()) {
            socialNetwork.setOnLoginCompleteListener(this);
            initSocialNetwork(socialNetwork);
        }
    }

    //Login listener

    private View.OnClickListener loginClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int networkId = 0;
            switch (view.getId()) {
                case R.id.ib_facebook:
                    networkId = FacebookSocialNetwork.ID;
                    break;
                case R.id.ib_twitter:
                    networkId = TwitterSocialNetwork.ID;
                    break;
                case R.id.ib_linkedin:
                    networkId = LinkedInSocialNetwork.ID;
                    break;
                case R.id.ib_google_plus:
                    networkId = GooglePlusSocialNetwork.ID;
                    break;
                case R.id.ib_vkontacte:
                    networkId = VkSocialNetwork.ID;
                    break;
                case R.id.ib_odnoclassniki:
                    networkId = OkSocialNetwork.ID;
                    break;
                default:
                    Snackbar.make(view, "Такая соц сеть не найдена", Snackbar.LENGTH_SHORT).show();
                    break;
            }
            SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
            if (!socialNetwork.isConnected()) {
                    if (networkId != 0) {
                        socialNetwork.requestLogin();
                        SocialNetworksActivity.showProgress("Загрузка данных профиля");
                    } else {
                        Snackbar.make(view, "Wrong networkId", Snackbar.LENGTH_SHORT).show();
                }
            } else {
                startProfile(socialNetwork.getID());
            }
        }
    };

    @Override
    public void onLoginSuccess(int networkId) {
        SocialNetworksActivity.hideProgress();
        startProfile(networkId);
    }

    @Override
    public void onError(int networkId, String requestID, String errorMessage, Object data) {
        SocialNetworksActivity.hideProgress();
        Toast.makeText(getActivity(), "ОШИБКА: " + errorMessage, Toast.LENGTH_LONG).show();
    }

    private void startProfile(int networkId) {
        ProfileFragment profile = ProfileFragment.newInstannce(networkId);
        getActivity().getSupportFragmentManager().beginTransaction()
                .addToBackStack("profile")
                .replace(R.id.container, profile)
                .commit();
    }
}
