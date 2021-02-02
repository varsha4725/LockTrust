package com.example.locktrust.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.locktrust.R;
import com.example.locktrust.interfaces.WelcomeCallback;


public class WelcomeAdapter extends PagerAdapter {
    private int[] layout;
    private Context context;
    private WelcomeCallback welcomeCallback;
    View view = null;
    public WelcomeAdapter(Context context, int[] layout) {
        this.context = context;
        this.layout = layout;
    }

    public void setWelcomeCallback(WelcomeCallback welcomeCallback) {
        this.welcomeCallback = welcomeCallback;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(layoutInflater != null)
        {
            view = layoutInflater.inflate(layout[position], container, false);
            if(layout[position]==R.layout.welcome_screen1)
            {
                TextView tvSkipScreenFirst=view.findViewById(R.id.tvSkipScreenFirst);
                tvSkipScreenFirst.setOnClickListener(v->
                {
                    welcomeCallback.myWelcomeCallback();


                });
            }else if(layout[position]==R.layout.welcome_screen2)
            {
                TextView tvSkipScreenSecond=view.findViewById(R.id.tvSkipScreenSecond);
                tvSkipScreenSecond.setOnClickListener(v->
                {
                    welcomeCallback.myWelcomeCallback();


                });
            }

            container.addView(view);
        }
        return view;
    }

    @Override
    public int getCount() {
        return layout.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
