package com.example.kabboot.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kabboot.view.fragment.BaSeFragment;


public class BaseActivity extends AppCompatActivity {

    public BaSeFragment baseFragment;
//    ​

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
//​


    public void superBackPressed() {
        super.onBackPressed();
    }
}

