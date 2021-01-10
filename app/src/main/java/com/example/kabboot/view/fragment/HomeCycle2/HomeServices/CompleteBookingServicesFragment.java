package com.example.kabboot.view.fragment.HomeCycle2.HomeServices;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllvendors;
import com.example.kabboot.view.fragment.BaSeFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CompleteBookingServicesFragment extends BaSeFragment {

    @BindView(R.id.fragment_home_complet_booking_services_book_cat_name_tv)
    TextView fragmentHomeCompletBookingServicesBookCatNameTv;
    @BindView(R.id.fragment_home_complet_booking_services_vendor_name_tv)
    TextView fragmentHomeCompletBookingServicesVendorNameTv;
    @BindView(R.id.fragment_home_complet_booking_services_sub_cat_name_tv)
    TextView fragmentHomeCompletBookingServicesSubCatNameTv;
    @BindView(R.id.fragment_home_complet_booking_services_sub_cat_price_tv)
    TextView fragmentHomeCompletBookingServicesSubCatPriceTv;
    @BindView(R.id.fragment_home_complet_booking_services_sub_serv_total_price_tv)
    TextView fragmentHomeCompletBookingServicesSubServTotalPriceTv;
    @BindView(R.id.fragment_home_complet_booking_services_book_date_tv)
    TextView fragmentHomeCompletBookingServicesBookDateTv;
    @BindView(R.id.fragment_home_complet_booking_services_book_time_tv)
    TextView fragmentHomeCompletBookingServicesBookTimeTv;
//    @BindView(R.id.fragment_home_complet_booking_services_book_sub_cat_name_tv)
//    TextView fragmentHomeCompletBookingServicesBookSubCatNameTv;
    private NavController navController;
    private String mainServiceName;
    private List<SubCat> subCatDataList = new ArrayList<SubCat>();
    ;
    private String date, time, addresss;
    private GetAllvendors vendorData;

    public CompleteBookingServicesFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            mainServiceName = this.getArguments().getString("MainServiceName");
            date = this.getArguments().getString("Date");
            time = this.getArguments().getString("Time");
            addresss = this.getArguments().getString("Address");
            vendorData = (GetAllvendors) this.getArguments().getSerializable("VendorDataObject");
            subCatDataList = (List<SubCat>) this.getArguments().getSerializable("Object");
        }
        View root = inflater.inflate(R.layout.fragment_home_complet_booking_services, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("g");
        setData();
        return root;
    }

    private void setData() {
        fragmentHomeCompletBookingServicesBookCatNameTv.setText("Book " + mainServiceName);
        fragmentHomeCompletBookingServicesSubCatNameTv.setText("Book " + subCatDataList.get(0).getCategoryName());
        fragmentHomeCompletBookingServicesVendorNameTv.setText(vendorData.getVendorName());
        fragmentHomeCompletBookingServicesSubCatPriceTv.setText("11 $");
        fragmentHomeCompletBookingServicesSubServTotalPriceTv.setText("11 $");
        fragmentHomeCompletBookingServicesBookDateTv.setText(date);
        fragmentHomeCompletBookingServicesBookTimeTv.setText(time);


    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        Bundle bundle = new Bundle();
        bundle.putString("MainServiceName", mainServiceName);
        bundle.putSerializable("Object", (Serializable) subCatDataList);
        navController.navigate(R.id.action_completeBookingServicesFragment_to_homeServicesEnterVenderDataFragment, bundle);

    }


    @OnClick({R.id.fragment_policy_and_conditions_back_img, R.id.fragment_home_complet_booking_services})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_policy_and_conditions_back_img:
                onBack();
                break;
            case R.id.fragment_home_complet_booking_services:
                break;
        }
    }
}