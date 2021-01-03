package com.example.kabboot.view.activity;

import android.os.Bundle;

import com.example.kabboot.R;
import com.example.kabboot.view.fragment.userCycle.LoginFragment;

import static com.example.kabboot.utils.HelperMethod.replaceFragment;


public class UserCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);
        replaceFragment(getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment());

//        Toast.makeText(this, "here", Toast.LENGTH_LONG)
//                .show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
