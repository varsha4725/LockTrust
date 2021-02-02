package com.example.locktrust.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;

public class CustomDrawer extends DrawerLayout
{
    public CustomDrawer(Context context) {
        super(context);
    }

    public CustomDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

   /* @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        View drawer = getChildAt(1);

        if (getDrawerLockMode(drawer) == LOCK_MODE_LOCKED_CLOSED && ev.getRawX() > drawer.getWidth()) {
            return false;
        } else {
          return super.addStatesFromChildren();

           *//* if(null!=ev)
            {
                return super.onInterceptTouchEvent(ev);
            }else
            {
                return false;
            }*//*

        }
    }*/
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (ArrayIndexOutOfBoundsException e ) {
            return false;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        View drawer = getChildAt(1);

        if (ev.getX() > 30 && ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isDrawerOpen(drawer) || isDrawerVisible(drawer)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
}