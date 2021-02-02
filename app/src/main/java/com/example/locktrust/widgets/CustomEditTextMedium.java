package com.example.locktrust.widgets;

/**
 * Created by Asmita on 06-04-2016.
 */


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.locktrust.R;


public class CustomEditTextMedium extends EditText
{
    private Typeface tf;
    public  final String DEFAULT_FONT = "fonts/NunitoRegular.ttf";
    public CustomEditTextMedium(Context context)
    {
        super(context);
        if (!isInEditMode())
            init(null);
    }
    public CustomEditTextMedium(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        if (!isInEditMode())
            init(attrs);
    }

    public CustomEditTextMedium(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        if (!isInEditMode())
            init(attrs);
    }

    private void init(AttributeSet attrs)
    {
        if(attrs!=null)
        {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextViewRegular);
            String fontName = a.getString(R.styleable.CustomTextViewRegular_typeface);
            if (fontName != null)
                tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/"+fontName+".ttf");
            else
                tf = Typeface.createFromAsset(getContext().getAssets(),DEFAULT_FONT);  //default font for app

            Drawable drawableLeft = null;
            Drawable drawableRight = null;
            Drawable drawableBottom = null;
            Drawable drawableTop = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawableLeft = a.getDrawable(R.styleable.CustomTextViewRegular_drawableLeftCompat);
                drawableRight = a.getDrawable(R.styleable.CustomTextViewRegular_drawableRightCompat);
                drawableBottom = a.getDrawable(R.styleable.CustomTextViewRegular_drawableBottomCompat);
                drawableTop = a.getDrawable(R.styleable.CustomTextViewRegular_drawableTopCompat);
            } else {
                final int drawableLeftId = a.getResourceId(R.styleable.CustomTextViewRegular_drawableLeftCompat, -1);
                final int drawableRightId = a.getResourceId(R.styleable.CustomTextViewRegular_drawableRightCompat, -1);
                final int drawableBottomId = a.getResourceId(R.styleable.CustomTextViewRegular_drawableBottomCompat, -1);
                final int drawableTopId = a.getResourceId(R.styleable.CustomTextViewRegular_drawableTopCompat, -1);

                if (drawableLeftId != -1)
                    drawableLeft = AppCompatResources.getDrawable(getContext(), drawableLeftId);
                if (drawableRightId != -1)
                    drawableRight = AppCompatResources.getDrawable(getContext(), drawableRightId);
                if (drawableBottomId != -1)
                    drawableBottom = AppCompatResources.getDrawable(getContext(), drawableBottomId);
                if (drawableTopId != -1)
                    drawableTop = AppCompatResources.getDrawable(getContext(), drawableTopId);
            }
            setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);


            a.recycle();
            if(tf!=null)
                setTypeface(tf);
        }
    }
}

