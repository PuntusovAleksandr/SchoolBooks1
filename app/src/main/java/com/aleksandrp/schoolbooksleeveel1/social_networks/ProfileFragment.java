package com.aleksandrp.schoolbooksleeveel1.social_networks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aleksandrp.schoolbooksleeveel1.R;
import com.aleksandrp.schoolbooksleeveel1.params.StaticParams;
import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.listener.OnPostingCompleteListener;
import com.github.gorbin.asne.core.listener.OnRequestSocialPersonCompleteListener;
import com.github.gorbin.asne.core.persons.SocialPerson;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.googleplus.GooglePlusSocialNetwork;
import com.github.gorbin.asne.linkedin.LinkedInSocialNetwork;
import com.github.gorbin.asne.twitter.TwitterSocialNetwork;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment implements OnRequestSocialPersonCompleteListener {
    private String message = "Ваши дети носят толстые и тяжелые учебники? \nТогда, Вам необходима наша программа!";
    private String link = "https://play.google.com/store/apps/details?id=com.aleksandrp.schoolbooksleeveel1&ah=7A_p-HNtdvozyaOQHY_bezMYhOA&hl=ru";
//    private String link = "https://github.com/gorbin/ASNE";

    private static final String NETWORK_ID = "NETWORK_ID";
    private SocialNetwork socialNetwork;
    private int networkId;
    private ImageView photo;
    private TextView name;
    private TextView id;
    private TextView info;
    //    private Button friends;
    private Button logout;
    private Button share;
    private RelativeLayout frame;

    public static ProfileFragment newInstannce(int id) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt(NETWORK_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        networkId = getArguments().containsKey(NETWORK_ID) ? getArguments().getInt(NETWORK_ID) : 0;
//        ((SocialNetworksActivity)getActivity()).getSupportActionBar().setTitle("Profile");

        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);

        frame = (RelativeLayout) rootView.findViewById(R.id.frame);
        photo = (ImageView) rootView.findViewById(R.id.imageView);
        name = (TextView) rootView.findViewById(R.id.name);
        id = (TextView) rootView.findViewById(R.id.id);
        info = (TextView) rootView.findViewById(R.id.info);
//        friends = (Button) rootView.findViewById(R.id.friends);
        logout = (Button) rootView.findViewById(R.id.bt_logout);
//        friends.setOnClickListener(friendsClick);
        logout.setOnClickListener(logoutListener);
        share = (Button) rootView.findViewById(R.id.share);
        share.setOnClickListener(shareClick);
        colorProfile(networkId);

        socialNetwork = SocialFragment.mSocialNetworkManager.getSocialNetwork(networkId);
        socialNetwork.setOnRequestCurrentPersonCompleteListener(this);
        socialNetwork.requestCurrentPerson();

        SocialNetworksActivity.showProgress("Загрузка данных");
        return rootView;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.main, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_logout:
//                socialNetwork.logout();
//                getActivity().getSupportFragmentManager().popBackStack();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void onRequestSocialPersonSuccess(int i, SocialPerson socialPerson) {
        SocialNetworksActivity.hideProgress();
        name.setText(socialPerson.name);
        id.setText("Ваш текущий id " + socialPerson.id);
        String socialPersonString = socialPerson.toString();
        String infoString = socialPersonString.substring(socialPersonString.indexOf("{") + 1, socialPersonString.lastIndexOf("}"));
        info.setText(infoString.replace(", ", "\n"));
        Picasso.with(getActivity())
                .load(socialPerson.avatarURL)
                .into(photo);
    }

    @Override
    public void onError(int networkId, String requestID, String errorMessage, Object data) {
        SocialNetworksActivity.hideProgress();
        Toast.makeText(getActivity(), "ОШИБКА: " + errorMessage, Toast.LENGTH_LONG).show();
    }

    //    private View.OnClickListener friendsClick = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            FriendsFragment friends = FriendsFragment.newInstannce(networkId);
//            getActivity().getSupportFragmentManager().beginTransaction()
//                    .addToBackStack("friends")
//                    .replace(R.id.container, friends)
//                    .commit();
//        }
//    };
    private View.OnClickListener logoutListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            socialNetwork.logout();
            getActivity().getSupportFragmentManager().popBackStack();
        }
    };
    private Dialog dialog;
    private View.OnClickListener shareClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.custom_dialog_share);
            dialog.setTitle("Поделиться программой?");
            Button buttonOk = (Button) dialog.findViewById(R.id.bt_share_ok);
            Button buttonCancel = (Button) dialog.findViewById(R.id.bt_share_cancel);
            buttonOk.setOnClickListener(listenerShare);
            buttonCancel.setOnClickListener(listenerShare);
            dialog.show();

//            AlertDialog.Builder ad = alertDialogInit("Вы хотите поделится этой программой у Вас на стрничке?", link);
//            ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    StaticParams.setProccessAsyn(true);
//                    Bundle postParams = new Bundle();
//                    postParams.putString(SocialNetwork.BUNDLE_NAME, "Школьные учебники у вас в телефоне");
//                    postParams.putString(SocialNetwork.BUNDLE_LINK, link);
//                    if (networkId == GooglePlusSocialNetwork.ID) {
//                        socialNetwork.requestPostDialog(postParams, postingComplete);
//                    } else {
//                        socialNetwork.requestPostLink(postParams, message, postingComplete);
//                    }
//                }
//            });
//            ad.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int i) {
//                    dialog.cancel();
//                }
//            });
//            ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                public void onCancel(DialogInterface dialog) {
//                    dialog.cancel();
//                }
//            });
//            ad.create().show();
        }
    };

    View.OnClickListener listenerShare = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_share_ok:
                    StaticParams.setProccessAsyn(true);
                    Bundle postParams = new Bundle();
                    postParams.putString(SocialNetwork.BUNDLE_NAME, "Школьные учебники у вас в телефоне");
                    postParams.putString(SocialNetwork.BUNDLE_LINK, link);
                    if (networkId == GooglePlusSocialNetwork.ID) {
                        socialNetwork.requestPostDialog(postParams, postingComplete);
                    } else {
                        socialNetwork.requestPostLink(postParams, message, postingComplete);
                    }
                case R.id.bt_share_cancel:
            }
            dialog.dismiss();
        }
    };


    private OnPostingCompleteListener postingComplete = new OnPostingCompleteListener() {
        @Override
        public void onPostSuccessfully(int socialNetworkID) {
            StaticParams.setProccessAsyn(false);
            Toast.makeText(getActivity(), "Передано", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(int socialNetworkID, String requestID, String errorMessage, Object data) {
            Toast.makeText(getActivity(), "Ошибка передачи: " + errorMessage, Toast.LENGTH_LONG).show();
        }
    };

    private void colorProfile(int networkId) {
        int color = getResources().getColor(R.color.dark);
        int image = R.drawable.user;
        switch (networkId) {
            case TwitterSocialNetwork.ID:
                color = getResources().getColor(R.color.twitter);
                image = R.drawable.twitter_user;
                break;
            case LinkedInSocialNetwork.ID:
                color = getResources().getColor(R.color.linkedin);
                image = R.drawable.linkedin_user;
                break;
            case GooglePlusSocialNetwork.ID:
                color = getResources().getColor(R.color.googleplus);
                image = R.drawable.linkedin_user;
                break;
            case FacebookSocialNetwork.ID:
                color = getResources().getColor(R.color.facebook);
                image = R.drawable.com_facebook_profile_picture_blank_square;
                break;
        }
        frame.setBackgroundColor(color);
        name.setTextColor(color);
//        friends.setBackgroundColor(color);
        logout.setBackgroundColor(color);
        share.setBackgroundColor(color);
        photo.setBackgroundColor(color);
        photo.setImageResource(image);
    }

    private AlertDialog.Builder alertDialogInit(String title, String message) {
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
        ad.setTitle(title);
        ad.setMessage(message);
        ad.setCancelable(true);
        return ad;
    }
}
