package com.aleksandrp.schoolbooksleeveel1.ads.banner;

import android.app.Activity;
import android.provider.Settings;
import android.view.View;

import com.aleksandrp.schoolbooksleeveel1.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Created by Aleksandr on 22.12.2015.
 */
public class Ads {

    public static void showBanner(final Activity activity) {

        //get EMULATOR deviceID todo потом надо удалить
        String android_id = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        String deviceId = md5(android_id).toUpperCase(Locale.ENGLISH);

        final AdView banner = (AdView) activity.findViewById(R.id.banner);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(deviceId)
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        banner.loadAd(adRequest);

        banner.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                setupContentViewPadding(activity, banner.getHeight());
            }
        });
    }

    public static final String md5(final String s)
    {
        try
        {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e)
        {
            //Logger.logStackTrace(TAG,e);
            System.out.println(e.getLocalizedMessage());
        }
        return "";
    }

    public static void setupContentViewPadding(Activity activity, int padding) {
        View view = activity.findViewById(R.id.content_frame);
view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), padding);
    }
}
