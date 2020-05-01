package com.example.basicprojectsetup.Base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicprojectsetup.Callback.CallbackDialogButtonClick;
import com.example.basicprojectsetup.Callback.CallbackOkCancel;
import com.example.basicprojectsetup.R;
import com.example.basicprojectsetup.Utils.DialogUtil;

import butterknife.ButterKnife;

/*Created by: Darshit Anjaria*/

public abstract class BaseActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        context = this;
        Fullscreen();
    }

    protected abstract int getLayout();

    public void Fullscreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected void hideKeyboard(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                View v = getCurrentFocus();
                if (v != null) {
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputManager != null) {
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                return false;
            }
        });

    }

    protected void showCustomAlert(String message) {
        Dialog dialog = new DialogUtil(context).buildDialogMessage(message, new CallbackDialogButtonClick() {
            @Override
            public void onButtonClick(Dialog dialog) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    protected void showAlert(String message, String positiveButtonText, String negativeButtonText, CallbackOkCancel callbackOkCancel) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callbackOkCancel.onOkClick((Dialog) dialog);
                    }
                })
                .setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callbackOkCancel.onCancelClick((Dialog) dialog);
                    }
                })
                .setCancelable(false)
                .show();
    }

    public void startActivity(Class classname) {
        Intent intent = new Intent(this, classname);
        startActivity(intent);
    }

    protected void showToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
        TextView txt_toast_msg = (TextView) layout.findViewById(R.id.txt_toast_msg);
        txt_toast_msg.setText(message);
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        toast.setView(layout);
        toast.show();
    }

    //orientation = 0 -> vertical, orientation = 1 -> horizontal
    protected void setRecyclerView(Activity activity, RecyclerView recyclerView, int orientation) {
        RecyclerView.LayoutManager layoutManager;
        switch (orientation) {
            case 1:
                layoutManager = new LinearLayoutManager(activity.getApplicationContext(), RecyclerView.HORIZONTAL, false);
                break;
            case 0:
            default:
                layoutManager = new LinearLayoutManager(activity.getApplicationContext());
                break;
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }
}
