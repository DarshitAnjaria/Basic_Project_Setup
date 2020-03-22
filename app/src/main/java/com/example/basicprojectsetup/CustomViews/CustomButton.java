package com.example.basicprojectsetup.CustomViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.basicprojectsetup.R;

@SuppressLint("AppCompatCustomView")
public class CustomButton extends Button {
    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomButton);
            String fontName = typedArray.getString(R.styleable.CustomButton_font);

            try {
                if (fontName != null) {
                    Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                    setTypeface(typeface);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            typedArray.recycle();
        }
    }
}
