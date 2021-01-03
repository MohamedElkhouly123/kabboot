package com.example.kabboot.view.fragment.HomeCycle2.HomeServices;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kabboot.R;
import com.example.kabboot.view.fragment.BaSeFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeServicesFragment extends BaSeFragment {

    private NavController navController;

    public HomeServicesFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_services, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);

        return root;
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
//        navController.navigate(R.id.action_successfulPaymentFragment_to_selectPaymentMethodFragment);
        getActivity().finish();
    }


    @OnClick({R.id.fragment_home_services_hot_deals_btn, R.id.fragment_home_services_promo_codes_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_home_services_hot_deals_btn:
                navController.navigate(R.id.action_navigation_services_to_homeOnSiteServicesFragment);
                homeCycleActivity.setNavigation("g");
                break;
            case R.id.fragment_home_services_promo_codes_btn:

//                navController.navigate(R.id.action_navigation_services_to_homeOnSiteServicesFragment);
//                homeCycleActivity.setNavigation("g");
                break;
        }
    }
}