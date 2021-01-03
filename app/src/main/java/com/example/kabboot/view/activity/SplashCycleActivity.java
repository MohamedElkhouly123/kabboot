package com.example.kabboot.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.kabboot.R;
import com.example.kabboot.view.fragment.splashCycle.SplashFragment;
import com.example.kabboot.view.fragment.splashCycle.SplashLoadFragment;

import static com.example.kabboot.utils.HelperMethod.replaceFragment;
import static com.example.kabboot.utils.HelperMethod.replaceFragmentWithAnimation;


public class SplashCycleActivity extends BaseActivity {
//    @BindView(R.id.progressbar)
//    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cycle);
//        clean(this);
        replaceFragment(getSupportFragmentManager(), R.id.splash_activity_fram, new SplashFragment());
        splashScreen();

    }

    private void splashScreen() {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

//                if (LoadUserData(getActivity()) != null && LoadBoolean(getActivity(), REMEMBER_ME)) {
//                    startActivity(new Intent(SplashCycleActivity.this, HomeCycleActivity.class));
//                    finish();
//                }else {
//                            replaceFragment(getActivity().getSupportFragmentManager(), R.id.splash_activity_fram, new AboutAppAndIntroFragment());
//                }
//                fragmentLoadSplashAnimationImg.setAnimation(null);
                if (!isFinishing() && !isDestroyed()) {
                    replaceFragmentWithAnimation(getSupportFragmentManager(), R.id.splash_activity_fram, new SplashLoadFragment(), "b");
                }
                    //                    startActivity(new Intent(SplashCycleActivity.this, AboutAppActivity.class));

//                    finish();
//                }
            }
        };
        handler.postDelayed(r, 2000);

    }

//    @Override
//    public void onBackPressed() {
//        finish();
//    }
}
