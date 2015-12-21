package com.aleksandrp.schoolbooksleeveel1.dialods;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.aleksandrp.schoolbooksleeveel1.R;

/**
 * Created by Aleksandr on 22.12.2015.
 */
public class ContextDialogInfo extends Dialog {
    private Context context;

    public ContextDialogInfo(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int context_fragment = R.layout.context_info;
        setContentView(context_fragment);
        ImageButton button = (ImageButton) findViewById(R.id.bt_context_info);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
