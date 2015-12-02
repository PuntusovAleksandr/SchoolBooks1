package com.aleksandrp.schoolbooksleeveel1.dialods;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandrp.schoolbooksleeveel1.R;


/**
 * Created by Aleksandr on 28.11.2015.
 */
public class ContextDialog extends Dialog {
    private Context context;
    private String nameBook;
    private int resSmall;

    public ContextDialog(Context context, String nameBook, int resSmall) {
        super(context);
        this.context = context;
        this.nameBook = nameBook;
        this.resSmall = resSmall;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int context_fragment = R.layout.context_fragment;
        setContentView(context_fragment);
        TextView title = (TextView) findViewById(R.id.tv_title_context);
        title.setText(nameBook);

        ImageView icon = (ImageView) findViewById(R.id.iv_icon_context);
//        icon.setImageResource(resSmall);
        icon.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(),
                resSmall, 250, 100));
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
