package com.aleksandrp.schoolbooksleeveel1.reader_pdf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.aleksandrp.schoolbooksleeveel1.R;
import com.aleksandrp.schoolbooksleeveel1.values.StaticValues;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

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

    private float SIZE_ZOOM_FINAL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        setPdf();
        setUi();
        setNumberPicker();
        SIZE_ZOOM_FINAL = mPdfView.getZoom();
    }

    private void setPdf() {
        Intent intent = getIntent();
        path = intent.getStringExtra(PATH_FILE);
        File pdfFile = new File(path);
        mPdfView = (PDFView) findViewById(R.id.pdfview);
        mPdfView.fromFile(pdfFile)
                .defaultPage(pageNumberDef)
                .swipeVertical(true)
                .showMinimap(true)
                .enableSwipe(true)
                .onLoad(this)
                .onPageChange(this)
                .load();

        title = path.substring(path.lastIndexOf("/"), path.lastIndexOf("."));
        setTitle(title);
        pageCount = mPdfView.getCurrentPage();
    }

    private float getZoom() {
        if (mPdfView !=null)
        return mPdfView.getZoom();
        return  1;
    }

    private void setUi() {

        numberPicker = (NumberPicker) findViewById(R.id.numberPicker1);
        leftArrow = (ImageView) findViewById(R.id.iv_left_arrow);
        rightArrow = (ImageView) findViewById(R.id.iv_right_arrow);
        numberPage = (EditText) findViewById(R.id.tv_number_page);
        numberPage.setText(pageNumberDef + "/" + pageCount);

        mPdfView.setOnClickListener(listener);
        leftArrow.setOnClickListener(listener);
        rightArrow.setOnClickListener(listener);
        numberPage.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        float zoom = getZoom();
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_left_arrow:
                    if (pageNumberDef > 1) pageNumberDef -= DEF_NUMBER_PAGE;
                    mPdfView.jumpTo(pageNumberDef);
//                    numberPage.setText(pageNumberDef);
                    break;
                case R.id.iv_right_arrow:
                    if (pageNumberDef < pageCount) pageNumberDef += DEF_NUMBER_PAGE;
                    mPdfView.jumpTo(pageNumberDef);
//                    numberPage.setText(pageNumberDef);
                    break;
                case R.id.tv_number_page:
                    numberPicker.setVisibility(View.VISIBLE);
                    numberPicker.setMinValue(1);
                    numberPicker.setMaxValue(pageCount);
                    String parsString = numberPage.getText().toString();
                    numberPicker.setValue(Integer.parseInt(parsString.substring(0, parsString.indexOf("/"))));
                    break;
                default:
                    break;
            }
        }
    };

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
        Toast.makeText(PdfActivity.this, "loaded " + nPages + " pages", Toast.LENGTH_SHORT).show();

    }

}
