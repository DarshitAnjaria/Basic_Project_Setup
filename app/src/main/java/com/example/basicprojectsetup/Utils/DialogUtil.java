package com.example.basicprojectsetup.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.LayoutRes;

import com.example.basicprojectsetup.Callback.CallbackDialogButtonClick;
import com.example.basicprojectsetup.R;

public class DialogUtil {

    private Activity activity;
    private Context context;

    public DialogUtil(Activity activity) {
        this.activity = activity;
    }

    public DialogUtil(Context context) {
        this.context = context;
    }

    private Dialog buildDialogView(@LayoutRes int layout) {
        final Dialog dialog = new Dialog(activity, R.style.DialogLevelCompleted);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(layout);
        dialog.setCancelable(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80000000")));

        return dialog;
    }

    //Dialog Msg
    public Dialog buildDialogMessage(String message, final CallbackDialogButtonClick callback) {
        final Dialog dialog = buildDialogView(R.layout.dialog_msg);
        ((TextView) dialog.findViewById(R.id.txt_msg)).setText(message);
        ((TextView) dialog.findViewById(R.id.txt_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onButtonClick(dialog);
            }
        });

        return dialog;
    }

    //loading dialog
    public Dialog buildDialogLoading(String message) {
        final Dialog dialog = buildDialogView(R.layout.custom_loading_dalog);
        ((TextView) dialog.findViewById(R.id.txt_loading_msg)).setText(message);
        return dialog;
    }

}
