package com.example.kabboot.view.fragment.HomeCycle2.HomeServices;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeServicesEnterVenderDataFragment extends BaSeFragment {

    @BindView(R.id.fragment_home_services_enter_vendor_data_til_date)
    TextInputLayout fragmentHomeServicesEnterVendorDataTilDate;
    @BindView(R.id.fragment_home_services_enter_vendor_data_til_time)
    TextInputLayout fragmentHomeServicesEnterVendorDataTilTime;
    @BindView(R.id.fragment_home_services_enter_vendor_data_til_address)
    TextInputLayout fragmentHomeServicesEnterVendorDataTilAddress;
    @BindView(R.id.fragment_home_services_enter_vendor_data_recycler_view)
    RecyclerView fragmentHomeServicesEnterVendorDataRecyclerView;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    private NavController navController;

    public HomeServicesEnterVenderDataFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_services_enter_vendor_data, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);

        return root;
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        navController.navigate(R.id.action_homeServicesEnterVenderDataFragment_to_homeOnSiteServicesFragment);
    }


    @OnClick({R.id.fragment_home_services_enter_vendor_data_date_etxt, R.id.fragment_home_services_enter_vendor_data_time_etxt, R.id.fragment_home_services_enter_vendor_data_next_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_home_services_enter_vendor_data_date_etxt:
                break;
            case R.id.fragment_home_services_enter_vendor_data_time_etxt:
                break;
            case R.id.fragment_home_services_enter_vendor_data_next_btn:
                navController.navigate(R.id.action_homeServicesEnterVenderDataFragment_to_completeBookingServicesFragment);
                break;
        }
    }
}