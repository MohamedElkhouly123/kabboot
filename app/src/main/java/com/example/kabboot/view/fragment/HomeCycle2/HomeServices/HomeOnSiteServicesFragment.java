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


public class HomeOnSiteServicesFragment extends BaSeFragment {

    private NavController navController;

    public HomeOnSiteServicesFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_on_site_services, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);

        return root;
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        navController.navigate(R.id.action_homeOnSiteServicesFragment_to_navigation_services);
        homeCycleActivity.setNavigation("v");

    }


    @OnClick(R.id.fragment_home_on_site_services_vendor_data_btn)
    public void onClick() {
        navController.navigate(R.id.action_homeOnSiteServicesFragment_to_homeServicesEnterVenderDataFragment);
    }
}