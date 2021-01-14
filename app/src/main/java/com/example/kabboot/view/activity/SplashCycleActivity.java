package com.example.kabboot.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;

import com.example.kabboot.R;
import com.example.kabboot.view.fragment.splashCycle.SplashFragment;
import com.example.kabboot.view.fragment.splashCycle.SplashLoadFragment;

import static com.example.kabboot.data.local.SharedPreferencesManger.LoadBoolean;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.kabboot.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.example.kabboot.utils.HelperMethod.replaceFragment;
import static com.example.kabboot.utils.HelperMethod.replaceFragmentWithAnimation;


public class SplashCycleActivity extends BaseActivity {
//    @BindView(R.id.progressbar)
//    ProgressBar progressBar;

    //Boolean variable to mark if the transaction is safe
    public static boolean isTransactionSafe;
    //Boolean variable to mark if there is any transaction pending
//    private boolean isTransactionPending;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cycle);
//        clean(this);
        replaceFragment(getSupportFragmentManager(), R.id.splash_activity_fram, new SplashFragment());
        splashScreen();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    private void splashScreen() {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

                if (LoadUserData(SplashCycleActivity.this) != null && LoadBoolean(SplashCycleActivity.this, REMEMBER_ME)) {
                    startActivity(new Intent(SplashCycleActivity.this, HomeCycleActivity.class));
                    finish();
                }else {
//                            replaceFragment(getActivity().getSupportFragmentManager(), R.id.splash_activity_fram, new AboutAppAndIntroFragment());
//                }
//                fragmentLoadSplashAnimationImg.setAnimation(null);
                if(isTransactionSafe) {
                    replaceFragmentWithAnimation(getSupportFragmentManager(), R.id.splash_activity_fram, new SplashLoadFragment(), "b");
                }
                    //                    startActivity(new Intent(SplashCycleActivity.this, AboutAppActivity.class));
//                }
//                    finish();
                }
            }
        };
        handler.postDelayed(r, 2000);

    }

    public void onPostResume(){
        super.onPostResume();
        isTransactionSafe=true;

    }

    public void onPause(){
        super.onPause();
        isTransactionSafe=false;

    }
    @Override
    public void onResume() {
        super.onResume();
        if(isTransactionSafe||!isFinishing() && !isDestroyed()) {
         splashScreen();
        }
    }
//    @Override
//    public void onBackPressed() {
//        finish();
//    }
}
