package com.example.locktrust.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.locktrust.Adapters.WelcomeAdapter;
import com.example.locktrust.R;
import com.example.locktrust.Utils.AppDataHolder;
import com.example.locktrust.interfaces.WelcomeCallback;
import com.example.locktrust.widgets.CustomButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeScreenActivity extends AppCompatActivity implements WelcomeCallback {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.btnNext)
    CustomButton btnNext;
    private int[] layouts;
    private WelcomeAdapter welcomeAdapter;
    private int cntPosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        ButterKnife.bind(this);
        init();//Initial method



    }

    //initial method
    private void init() {
        //this set value false for user already open this  Welcome Screen
        AppDataHolder.getSession(com.example.locktrust.Activities.WelcomeScreenActivity.this).setIsFirstTimeOpenApp(false);

        layouts = new int[]{
                R.layout.welcome_screen1,
                R.layout.welcome_screen2,
                R.layout.welcome_screen3};
        welcomeAdapter=new WelcomeAdapter(this,layouts);
        viewPager.setAdapter(welcomeAdapter);
        welcomeAdapter.setWelcomeCallback(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                cntPosition=position;
                if (position == layouts.length - 1) {
                    btnNext.setText(R.string.get_started);
                }else
                {
                    btnNext.setText(R.string.next);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.btnNext)
    public void onViewClicked() {
        if(cntPosition!=layouts.length - 1)
        {
            viewPager.setCurrentItem(cntPosition+1);

        }else
        {
            finish();
            startActivity(new Intent(com.example.locktrust.Activities.WelcomeScreenActivity.this, com.example.locktrust.Activities.LoginActivity.class));
        }


    }

    @Override
    public void myWelcomeCallback() {
        finish();
        startActivity(new Intent(com.example.locktrust.Activities.WelcomeScreenActivity.this, com.example.locktrust.Activities.LoginActivity.class));
    }
}