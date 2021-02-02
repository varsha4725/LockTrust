package com.example.locktrust.Activities;

import android.os.Bundle;

import com.example.locktrust.Fragments.HomeFragment;
import com.example.locktrust.R;

public class HomeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        init();  //initialize first function
    }

    private void init() {

        selectFooterMenu(1);
        toolbar=findViewById(R.id.toolbar);
        HomeFragment homeFragment=new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        screenNumber = 1;
    }

}
