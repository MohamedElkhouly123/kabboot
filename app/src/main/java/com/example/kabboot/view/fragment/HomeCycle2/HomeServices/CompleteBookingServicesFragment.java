package com.example.kabboot.view.fragment.HomeCycle2.HomeServices;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.adapter.GetShowSelectedAllServicesAdapter;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllvendorsResponce.AllVendorService;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllvendors;
import com.example.kabboot.data.model.getUserDataResponce.GetUserDataResponce;
import com.example.kabboot.data.model.getUserDataResponce.UserData;
import com.example.kabboot.data.model.saveServiceOrdersRequest.OrderServiceList;
import com.example.kabboot.data.model.saveServiceOrdersRequest.SaveServiceOrdersRequest;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadData;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.kabboot.data.local.SharedPreferencesManger.USER_TOKEN;


public class CompleteBookingServicesFragment extends BaSeFragment {

    @BindView(R.id.fragment_home_complet_booking_services_book_cat_name_tv)
    TextView fragmentHomeCompletBookingServicesBookCatNameTv;
    @BindView(R.id.fragment_home_complet_booking_services_vendor_name_tv)
    TextView fragmentHomeCompletBookingServicesVendorNameTv;
    @BindView(R.id.fragment_home_complet_booking_services_sub_cat_name_tv)
    TextView fragmentHomeCompletBookingServicesSubCatNameTv;
    //    @BindView(R.id.fragment_home_complet_booking_services_sub_cat_price_tv)
//    TextView fragmentHomeCompletBookingServicesSubCatPriceTv;
    @BindView(R.id.fragment_home_complet_booking_services_sub_serv_total_price_tv)
    TextView fragmentHomeCompletBookingServicesSubServTotalPriceTv;
    @BindView(R.id.fragment_home_complet_booking_services_book_date_tv)
    TextView fragmentHomeCompletBookingServicesBookDateTv;
    @BindView(R.id.fragment_home_complet_booking_services_book_time_tv)
    TextView fragmentHomeCompletBookingServicesBookTimeTv;
    @BindView(R.id.fragment_home_complet_booking_services_recycler_view)
    RecyclerView fragmentHomeCompletBookingServicesRecyclerView;

    //    @BindView(R.id.fragment_home_complet_booking_services_book_sub_cat_name_tv)
//    TextView fragmentHomeCompletBookingServicesBookSubCatNameTv;
    private NavController navController;
    private String mainServiceName;
    private List<SubCat> subCatDataList = new ArrayList<SubCat>();
    private List<AllVendorService> allVendorServiceListSelected = new ArrayList<>();
    private String date, time, addresss;
    private GetAllvendors vendorData;
    private UserData userData;
    private List<OrderServiceList> servicesSelectedIds = new ArrayList<>();
    private LinearLayoutManager lLayout;
    private GetShowSelectedAllServicesAdapter getAllServicesSelectedAdapter;
    private ViewModelUser viewModelUser;
    public static double myLang=0;
    public static double myLat=0;
    public static boolean mabBack=false;
    private String userId,userPhone,userName,userCity,userToken;
    private int totalPrice=0;
    private String SubServiceName;

    public CompleteBookingServicesFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            mainServiceName = this.getArguments().getString("MainServiceName");
            date = this.getArguments().getString("Date");
            time = this.getArguments().getString("Time");
            SubServiceName = this.getArguments().getString("SubServiceName");
            addresss = this.getArguments().getString("Address");
            vendorData = (GetAllvendors) this.getArguments().getSerializable("VendorDataObject");
            allVendorServiceListSelected = (List<AllVendorService>) this.getArguments().getSerializable("ServicesSelected");
            servicesSelectedIds = (List<OrderServiceList>) this.getArguments().getSerializable("ServicesSelectedIds");
            subCatDataList = (List<SubCat>) this.getArguments().getSerializable("Object");

        }
        View root = inflater.inflate(R.layout.fragment_home_complet_booking_services, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        userData = LoadUserData(getActivity());
        homeCycleActivity.setNavigation("g");
        setData();
        init();
        return root;
    }

    private void setData() {

        for(int i=0;i<allVendorServiceListSelected.size();i++){
           totalPrice+= (int) Double.parseDouble(allVendorServiceListSelected.get(i).getServicePrice());
        }
//        fragmentHomeCompletBookingServicesBookCatNameTv.setText("Book " + mainServiceName);
        fragmentHomeCompletBookingServicesBookCatNameTv.setText("Book " + mainServiceName + " Service");
        fragmentHomeCompletBookingServicesSubCatNameTv.setText(" Services Booked");
        fragmentHomeCompletBookingServicesVendorNameTv.setText(vendorData.getVendorName());
//        fragmentHomeCompletBookingServicesSubCatPriceTv.setText("11 $");
        fragmentHomeCompletBookingServicesSubServTotalPriceTv.setText("Subtotal : "+totalPrice+" EGP");
        fragmentHomeCompletBookingServicesBookDateTv.setText(date);
        fragmentHomeCompletBookingServicesBookTimeTv.setText(time);


    }

    private void init() {
        if (allVendorServiceListSelected.size() != 0) {
            lLayout = new LinearLayoutManager(getActivity());
            fragmentHomeCompletBookingServicesRecyclerView.setLayoutManager(lLayout);
//            showToast(getActivity(), allVendorServiceList.get(0).getServiceName());
            getAllServicesSelectedAdapter = new GetShowSelectedAllServicesAdapter(getContext(), getActivity(), navController, allVendorServiceListSelected);
            fragmentHomeCompletBookingServicesRecyclerView.setAdapter(getAllServicesSelectedAdapter);

//            noResultErrorTitle.setVisibility(View.GONE);

        } else {

        }
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
//        showToast(getActivity(), "lat="+myLat+" , "+myLang);
        Bundle bundle = new Bundle();
        bundle.putString("MainServiceName", mainServiceName);
        bundle.putSerializable("VendorDataObject", vendorData);
        bundle.putString("SubServiceName", SubServiceName);
        bundle.putSerializable("Object", (Serializable) subCatDataList);
        navController.navigate(R.id.action_completeBookingServicesFragment_to_homeServicesEnterVenderData2Fragment, bundle);

    }


    @Override
    public void onResume() {
        super.onResume();
        if(mabBack){
            mabBack=false;
            onBack();
        }
    }

    @OnClick({R.id.fragment_policy_and_conditions_back_img, R.id.fragment_home_complet_booking_services})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_policy_and_conditions_back_img:
                onBack();
                break;
            case R.id.fragment_home_complet_booking_services:
                if(myLang!=0&&myLat!=0){
                initListener();
                onCall();
                }
                break;
        }
    }
    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.makeResetAndNewPasswordResponseAndSignUpAndBooking().observe(this, new Observer<GetUserDataResponce>() {
            @Override
            public void onChanged(@Nullable GetUserDataResponce response) {
                if(response!=null) {
                    if (response.getSuccess()==1) {
                        navController.navigate(R.id.action_completeBookingServicesFragment_to_navigation_services);
                        homeCycleActivity.setNavigation("v");

                    }
                }
            }
        });
    }

    private void onCall() {
        userId = userData.getUserId();
        userName = userData.getUserName();
        userPhone = userData.getUserPhone();
        userCity = userData.getUserCity();
        userToken = LoadData(getActivity(), USER_TOKEN);
//        showToast(getActivity(), userId+" "+userName+" "+servicesSelectedIds.get(0).getServiceId());
        Call<GetUserDataResponce> saveServiceOrdersCall = null;

        SaveServiceOrdersRequest saveServiceOrdersRequest =new SaveServiceOrdersRequest();
        saveServiceOrdersRequest.setUserId(userId);
        saveServiceOrdersRequest.setUserName(userName);
        saveServiceOrdersRequest.setUserPhone(userPhone);
        saveServiceOrdersRequest.setOrderDate(date);
        saveServiceOrdersRequest.setOrderTime(time);
        saveServiceOrdersRequest.setUserCity(userCity);
        saveServiceOrdersRequest.setMapLong(String.valueOf(myLang));
        saveServiceOrdersRequest.setMapLat(String.valueOf(myLat));
        saveServiceOrdersRequest.setToken(userToken);
        saveServiceOrdersRequest.setOrderServiceList(servicesSelectedIds);
//        saveServiceOrdersCall = getApiClient().saveServiceOrders(userId,userName,userPhone,userCity,userToken,servicesSelectedIds);
        saveServiceOrdersCall = getApiClient().saveServiceOrders(saveServiceOrdersRequest);

        viewModelUser.setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(getActivity(),saveServiceOrdersCall, "Success Service Order Booking", true);


    }
}