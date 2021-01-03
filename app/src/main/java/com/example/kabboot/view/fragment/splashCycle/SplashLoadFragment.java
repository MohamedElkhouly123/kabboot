package com.example.kabboot.view.fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.kabboot.R;
import com.example.kabboot.view.activity.HomeCycleActivity;
import com.example.kabboot.view.activity.SplashCycleActivity;
import com.example.kabboot.view.activity.UserCycleActivity;
import com.example.kabboot.view.fragment.BaSeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.kabboot.utils.HelperMethod.replaceFragmentWithAnimation;


public class SplashLoadFragment extends BaSeFragment {

    @BindView(R.id.fragment_load_splash_animation_img)
    ImageView fragmentLoadSplashAnimationImg;

    public SplashLoadFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_load_splash, container, false);

        ButterKnife.bind(this, root);
        RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(3000);
        rotate.setInterpolator(new LinearInterpolator());
        fragmentLoadSplashAnimationImg.startAnimation(rotate);
        splashScreen();

//        ImageView view = ... //Initialize ImageView via FindViewById or programatically

//        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

//Setup anim with desired properties
//        anim.setInterpolator(new LinearInterpolator());
//        anim.setRepeatCount(Animation.INFINITE); //Repeat animation indefinitely
//        anim.setDuration(700); //Put desired duration per anim cycle here, in milliseconds

//Start animation
//        Animation animat = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.img_animation);

//        fragmentLoadSplashAnimationImg.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.img_animation));
//Later on, use view.setAnimation(null) to stop it.
        return root;
    }

    private void splashScreen() {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                if (!getActivity().isFinishing() && !getActivity().isDestroyed()) {
                startActivity(new Intent(getActivity(), UserCycleActivity.class));
                getActivity().finish();}

//                if (LoadUserData(getActivity()) != null && LoadBoolean(getActivity(), REMEMBER_ME)) {
//                    startActivity(new Intent(SplashCycleActivity.this, HomeCycleActivity.class));
//                    finish();
//                }else {
//                            replaceFragment(getActivity().getSupportFragmentManager(), R.id.splash_activity_fram, new AboutAppAndIntroFragment());
//                }
//                fragmentLoadSplashAnimationImg.setAnimation(null);
//                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.splash_activity_fram, new SplashLoadFragment(), "b");
//                    startActivity(new Intent(SplashCycleActivity.this, AboutAppActivity.class));

//                    finish();
//                }
            }
        };
        handler.postDelayed(r, 3200);

    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.splash_activity_fram, new LoginFragment());
//        getActivity().finish();
    }
}