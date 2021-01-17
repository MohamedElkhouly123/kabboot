package com.example.kabboot.view.fragment.splashCycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import com.example.kabboot.R;
import com.example.kabboot.view.fragment.BaSeFragment;

import butterknife.ButterKnife;

import static com.example.kabboot.utils.HelperMethod.replaceFragment;
import static com.example.kabboot.utils.HelperMethod.replaceFragmentWithAnimation;


public class SplashFragment extends BaSeFragment {

    public SplashFragment() {
        // Required empty public constructor
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_splash, container, false);

        ButterKnife.bind(this, root);
        return root;
    }


    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.splash_activity_fram, new LoginFragment());
//        getActivity().finish();
    }
}