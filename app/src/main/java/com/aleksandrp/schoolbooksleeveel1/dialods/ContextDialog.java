package com.aleksandrp.schoolbooksleeveel1.dialods;

import android.app.Dialog;
import android.content.Context;
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
        icon.setImageResource(resSmall);
    }

}
