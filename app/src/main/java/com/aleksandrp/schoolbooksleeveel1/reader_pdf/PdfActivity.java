package com.aleksandrp.schoolbooksleeveel1.reader_pdf;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aleksandrp.schoolbooksleeveel1.R;
import com.aleksandrp.schoolbooksleeveel1.ads.banner.Ads;
import com.aleksandrp.schoolbooksleeveel1.params.StaticParams;
import com.aleksandrp.schoolbooksleeveel1.values.StaticValues;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class PdfActivity extends AppCompatActivity
        implements OnPageChangeListener, OnLoadCompleteListener,
        StaticValues {

    private int pageNumberDef = DEF_NUMBER_PAGE;
    private int pageCount;
    private String title;
    private String path;
    private PDFView mPdfView;

    private ImageView leftArrow;
    private ImageView rightArrow;
    private EditText numberPage;
    private NumberPicker numberPicker;
    private ProgressBar mProgressBar;

    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Ads.showBanner(PdfActivity.this);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.big_banner_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        setPdf();
        setUi();
        setNumberPicker();
        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        if (!mInterstitialAd.isLoaded()) {

            //get EMULATOR deviceID todo потом надо удалить
            String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
            String deviceId = md5(android_id).toUpperCase(Locale.ENGLISH);
            AdRequest adRequest = new AdRequest.Builder()
//                    .addTestDevice(deviceId)
//                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mInterstitialAd.loadAd(adRequest);
        }
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


    private void setPdf() {
        Intent intent = getIntent();
        path = intent.getStringExtra(PATH_FILE);
        File pdfFile = new File(path);
        if (pdfFile.exists()) {
            if (mPdfView == null) {
                try {
                    mPdfView = (PDFView) findViewById(R.id.pdfview);
                    try {
                        mPdfView.fromFile(pdfFile)
                                .defaultPage(pageNumberDef)
                                .swipeVertical(true)
                                .showMinimap(false)
                                .enableSwipe(true)
                                .onLoad(this)
                                .onPageChange(this)
                                .load();
                    } catch (RuntimeException r) {
                        System.out.println("=======ERROR======" + r.getLocalizedMessage());
                    }
                    title = path.substring(path.lastIndexOf("/"), path.lastIndexOf("."));
                    setTitle(title);
                    pageCount = mPdfView.getCurrentPage();
                } catch (Exception e) {
                    Toast.makeText(PdfActivity.this, "Перезагрузите файл", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }else mPdfView.jumpTo(StaticParams.getPageNumber());
        } else Toast.makeText(PdfActivity.this, "Файл поврежден", Toast.LENGTH_SHORT).show();

        pdfFile.canRead();

    }

    private void setUi() {
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        leftArrow = (ImageView) findViewById(R.id.iv_left_arrow);
        rightArrow = (ImageView) findViewById(R.id.iv_right_arrow);
        numberPage = (EditText) findViewById(R.id.tv_number_page);
        numberPage.setText(pageNumberDef + "/" + pageCount);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_pdf_view);

        leftArrow.setOnClickListener(listener);
        rightArrow.setOnClickListener(listener);
        numberPage.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_left_arrow:
                    if (pageNumberDef > 1) pageNumberDef -= DEF_NUMBER_PAGE;
                    mPdfView.jumpTo(pageNumberDef);
                    break;
                case R.id.iv_right_arrow:
                    if (pageNumberDef < pageCount) pageNumberDef += DEF_NUMBER_PAGE;
                    mPdfView.jumpTo(pageNumberDef);
                    break;
                case R.id.tv_number_page:
                    numberPicker.setMinValue(1);
                    numberPicker.setMaxValue(pageCount);
                    String parsString = numberPage.getText().toString();
                    numberPicker.setValue(Integer.parseInt(parsString.substring(0, parsString.indexOf("/"))));
                    visiblePicker();
                    break;
                default:
                    break;
            }
            StaticParams.setPageNumber(pageNumberDef);
        }
    };

    private synchronized void visiblePicker() {
        numberPicker.setVisibility(View.VISIBLE);
        numberPage.setEnabled(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                numberPicker.setVisibility(View.INVISIBLE);
                numberPage.setEnabled(true);
            }
        }, 15000);
    }


    private void setNumberPicker() {
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mPdfView.jumpTo(newVal);
                numberPage.setText(newVal + "/" + pageCount);
            }
        });
    }

    /**
     * Called when the user use swipe to change page
     *
     * @param page      the new page displayed, starting from 1
     * @param pageCount the total page count, starting from 1
     */
    @Override
    public void onPageChanged(int page, int pageCount) {
        numberPage.setText(page + "/" + pageCount);
        numberPicker.setValue(page);
        pageNumberDef = page;
        this.pageCount = pageCount;
        setTitle(String.format("%s %s / %s", title, page, pageCount));
    }

    /**
     * Called when the PDF is loaded
     *
     * @param nPages the number of pages in this PDF file
     */
    @Override
    public void loadComplete(int nPages) {
        if (mPdfView != null) {
            mPdfView.jumpTo(StaticParams.getPageNumber());
        }
        mProgressBar.setVisibility(View.VISIBLE);
        Toast.makeText(PdfActivity.this, "Загрузка " + nPages + " страниц", Toast.LENGTH_SHORT).show();
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}

