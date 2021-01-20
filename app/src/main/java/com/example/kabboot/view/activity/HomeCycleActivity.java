package com.example.kabboot.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.kabboot.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.ButterKnife;


public class HomeCycleActivity extends BaseActivity
//        implements BottomNavigationView.OnNavigationItemSelectedListener
{


//    public HomeFragment homeFragment;


    public BottomNavigationView bottomNavView;
//    private ClientData clientData;

    public HomeCycleActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cycle);
        ButterKnife.bind(this);
//        child = this;
//        homeFragment=new HomeFragment();
//        clientData = LoadUserData(this);
//        replaceFragment(getSupportFragmentManager(), R.id.home_activity_fragment,new DiscoverFragment());
        bottomNavView = (BottomNavigationView) findViewById(R.id.nav_view);
//        bottomNavView.setOnNavigationItemSelectedListener(this);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.home_activity_fragment);
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.getNavController());

    }



    private void displayView(int position) {
        switch (position) {
            case 0:
                Intent intentHome = new Intent(HomeCycleActivity.this,HomeCycleActivity.class);
                startActivity(intentHome);
                break;
            case 1:
                Intent intentHome2 = new Intent(HomeCycleActivity.this,HomeCycleActivity.class);
                startActivity(intentHome2);
                break;
        }
    }
    public void setNavigation(String visibility) {
        bottomNavView = (BottomNavigationView) findViewById(R.id.nav_view);
        if (visibility.equals("v")) {
            bottomNavView.setVisibility(View.VISIBLE);
        } else if (visibility.equals("g")) {
            bottomNavView.setVisibility(View.GONE);
        }

    }

}
