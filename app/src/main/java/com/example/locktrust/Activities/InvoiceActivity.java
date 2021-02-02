package com.example.locktrust.Activities;

import android.os.Bundle;

import com.example.locktrust.Fragments.HomeFragment;
import com.example.locktrust.Fragments.InvoiceFragment;
import com.example.locktrust.R;

public class InvoiceActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        init();  //initialize first function
    }

    private void init() {

        selectFooterMenu(1);
        toolbar=findViewById(R.id.toolbar);
        InvoiceFragment invoiceFragment=new InvoiceFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,invoiceFragment).commit();
        screenNumber = 2;
    }
}
