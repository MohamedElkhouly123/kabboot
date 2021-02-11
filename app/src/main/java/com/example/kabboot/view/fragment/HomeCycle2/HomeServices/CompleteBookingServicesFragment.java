package com.example.kabboot.view.fragment.HomeCycle2.HomeServices;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
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
import com.example.kabboot.adapter.PromoCodesSpinnerAdapter;
import com.example.kabboot.data.model.customerPromoCodesResponce.CustomerPromoCodesResponce;
import com.example.kabboot.data.model.customerPromoCodesResponce.CustomerPromocode;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllvendorsResponce.AllVendorService;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllvendors;
import com.example.kabboot.data.model.getSaveOrdersResponce.GetSaveOrdersResponce;
import com.example.kabboot.data.model.getUserDataResponce.UserData;
import com.example.kabboot.data.model.saveServiceOrdersRequest.OrderServiceList;
import com.example.kabboot.data.model.saveServiceOrdersRequest.SaveServiceOrdersRequest;
import com.example.kabboot.utils.ToastCreator;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelUser;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static android.content.ContentValues.TAG;
import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadData;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.kabboot.data.local.SharedPreferencesManger.USER_TOKEN;
import static com.example.kabboot.utils.HelperMethod.showToast;
import static com.example.kabboot.utils.validation.Validation.validationLengthZero;


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
    @BindView(R.id.fragment_home_complet_booking_services_promo_code_sp_promo)
    Spinner fragmentMyServicesPromoCodeSpPromo;
    @BindView(R.id.fragment_home_complet_booking_services_phone_etxt)
    TextInputEditText fragmentHomeCompletBookingServicesPhoneEtxt;
    @BindView(R.id.fragment_home_complet_booking_services_til_promo_code)
    TextInputLayout fragmentHomeCompletBookingServicesTilPromoCode;
    @BindView(R.id.fragment_home_complet_booking_services_Subtotal)
    TextView fragmentHomeCompletBookingServicesSubtotal;
    @BindView(R.id.fragment_home_complet_booking_services_discount)
    TextView fragmentHomeCompletBookingServicesDiscount;
    @BindView(R.id.fragment_home_complet_booking_services_total_price)
    TextView fragmentHomeCompletBookingServicesTotalPrice;

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
    private List<CustomerPromocode> customerPromocodes = new ArrayList<>();
    private LinearLayoutManager lLayout;
    private GetShowSelectedAllServicesAdapter getAllServicesSelectedAdapter;
    private ViewModelUser viewModelUser;
    public static double myLang = 0;
    public static double myLat = 0;
    public static boolean mabBack = false;
    private String userId, userPhone, userName, userCity, userToken;
    private int totalPrice = 0;
    private String SubServiceName;
    private ViewModelUser viewMode2;
    private AdapterView.OnItemSelectedListener promoCodeSpinerListener;
    private PromoCodesSpinnerAdapter promoCodesSpinnerAdapter;
    private int promoSelectedId =0;
    private String promoFilterValue;
    private String promoFilterValueId;
    private double discountPrice;
    private double finalTotalPrice;
    private boolean noPromo=false;
    private boolean foundPromo=false;
    private String promoCodeFromUser;
    private boolean first2=true;

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
        initListener2();
        setData();
        init();
        return root;
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void initListener2() {

        viewMode2 = ViewModelProviders.of(this).get(ViewModelUser.class);

        viewMode2.makeGetSpinnerPromoCodeData().observe(this, new Observer<CustomerPromoCodesResponce>() {
            @Override
            public void onChanged(@Nullable CustomerPromoCodesResponce response) {
                if (response != null) {
                    if (response.getCustomerPromocodes()!=null) {
                        customerPromocodes.addAll(response.getCustomerPromocodes());
                        showToast(getActivity(), "success2");
//                        fragmentHomeCompletBookingServicesTilPromoCode.getEditText().setText("No Available Promo Codes Now");
//
//                    } else {
////                        showToast(getActivity(), "error");
//
                    }
                }
            }
        });

        setSpinner();
    }
    private void setData() {

        for (int i = 0; i < allVendorServiceListSelected.size(); i++) {
            totalPrice += (int) Double.parseDouble(allVendorServiceListSelected.get(i).getServicePrice());
        }
//        fragmentHomeCompletBookingServicesBookCatNameTv.setText("Book " + mainServiceName);
        fragmentHomeCompletBookingServicesBookCatNameTv.setText("Book " + mainServiceName + " Service");
        fragmentHomeCompletBookingServicesSubCatNameTv.setText(" Services Booked");
        fragmentHomeCompletBookingServicesVendorNameTv.setText(vendorData.getVendorName());
//        fragmentHomeCompletBookingServicesSubCatPriceTv.setText("11 $");
        fragmentHomeCompletBookingServicesSubServTotalPriceTv.setText("Subtotal : " + totalPrice + " EGP");
        fragmentHomeCompletBookingServicesSubtotal.setText("Subtotal Before Discount  :  " + totalPrice + " EGP");
        fragmentHomeCompletBookingServicesTotalPrice.setText("Total :  " + totalPrice + " EGP");
        fragmentHomeCompletBookingServicesBookDateTv.setText(date);
        fragmentHomeCompletBookingServicesBookTimeTv.setText(time);


    }

    private void setSpinner() {

        promoCodesSpinnerAdapter = new PromoCodesSpinnerAdapter(getActivity());
        viewMode2.getSpinnerPromoCodeData(getActivity(), fragmentMyServicesPromoCodeSpPromo, promoCodesSpinnerAdapter, "",
                getApiClient().customerPromoCodes(userData.getUserId()), promoSelectedId, null,fragmentHomeCompletBookingServicesTilPromoCode);
//        promoCodeSpinerListener = new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                promoSelectedId = i;
//
//
////                fragmentHomeServicesEnterVendorDataCityTv.setText(getString(R.string.Filters));
//                if(promoSelectedId >1) {
//                    promoFilterValue =String.valueOf(promoCodesSpinnerAdapter.getItem(i));
//                    promoFilterValueId =String.valueOf(promoCodesSpinnerAdapter.getItemId(i));
//                    discountPrice= (totalPrice / 100 ) * Integer.parseInt(promoFilterValue);
//                    finalTotalPrice=totalPrice-discountPrice;
//                    fragmentHomeCompletBookingServicesDiscount.setText("Discount ( " + promoFilterValue + " % ) : "+ discountPrice + " EGP");
//                    fragmentHomeCompletBookingServicesTotalPrice.setText("Total :  " + finalTotalPrice + " EGP");
//
//
////                    fragmentHomeServicesEnterVendorDataCityTv.setText(cityFilterValue);
////                    if (promoSelectedId == 1) {
////
////                    }
////                    if (promoSelectedId > 1) {
////                        showToast(getActivity(), cityFilterValue+ " yes");
//
//                    fragmentHomeCompletBookingServicesTilPromoCode.getEditText().setText("Promo Code "+promoFilterValueId+"   ( "+promoFilterValue+" % Discount )");
//
////                    }
////                showToast(getActivity(), String.valueOf(priceSelectedId1));
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        };
//        fragmentMyServicesPromoCodeSpPromo.setOnItemSelectedListener(promoCodeSpinerListener);

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
        myLat=0;
        myLang=0;
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
        if (mabBack) {
            mabBack = false;
            onBack();
        }
    }

    @OnClick({R.id.fragment_policy_and_conditions_back_img, R.id.fragment_home_complet_booking_services, R.id.fragment_home_complet_booking_services_apply_now_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_policy_and_conditions_back_img:
                onBack();
                break;
            case R.id.fragment_home_complet_booking_services_apply_now_btn:
                checkPromo();
                break;
            case R.id.fragment_home_complet_booking_services:
                if (myLang != 0 && myLat != 0) {
                    initListener();
                    if (fragmentHomeCompletBookingServicesTilPromoCode.getEditText().length()==0&&foundPromo) {
                        ToastCreator.onCreateErrorToast(getActivity(), "Please Apply new Promo code value first");
                        noPromo=true;
                    }else {
                        onCall();
                    }
                }
                break;
        }
    }

    private void checkPromo() {
        if(noPromo||first2) {
            first2=false;
            if (!validationLengthZero(fragmentHomeCompletBookingServicesTilPromoCode, getString(R.string.invalid_user_prom), 0)) {
                ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_user_prom));
                return;
            }
        }
        if(customerPromocodes!=null&&customerPromocodes.size()!=0) {
            foundPromo=false;
            promoCodeFromUser = fragmentHomeCompletBookingServicesTilPromoCode.getEditText().getText().toString();
            for (int i = 0; i < customerPromocodes.size(); i++) {
                if(customerPromocodes.get(i).getPromoCodeName().equalsIgnoreCase(promoCodeFromUser)||customerPromocodes.get(i).getPromoCodePercent().equalsIgnoreCase(promoCodeFromUser)){
                    promoFilterValue =String.valueOf(customerPromocodes.get(i).getPromoCodePercent());

                    promoFilterValueId =String.valueOf(customerPromocodes.get(i).getPromoIdFk());
                    foundPromo=true;
                }
            }
        }else {
            ToastCreator.onCreateErrorToast(getActivity(), "No Available Promo Codes Now");
            return;
        }
        if(foundPromo){
            noPromo=false;
            discountPrice= (totalPrice / 100 ) * Integer.parseInt(promoFilterValue);
            finalTotalPrice=totalPrice-discountPrice;
            fragmentHomeCompletBookingServicesDiscount.setText("Discount ( " + promoFilterValue + " % ) : "+ discountPrice + " EGP");
            fragmentHomeCompletBookingServicesTotalPrice.setText("Total :  " + finalTotalPrice + " EGP");
            ToastCreator.onCreateSuccessToast(getActivity(), "Success Promo Code");
        }else {
            fragmentHomeCompletBookingServicesDiscount.setText("Discount: 0 EGP");
            fragmentHomeCompletBookingServicesTotalPrice.setText("Total :  " + totalPrice + " EGP");

            if(fragmentHomeCompletBookingServicesTilPromoCode.getEditText().length()!=0) {
                ToastCreator.onCreateErrorToast(getActivity(), "InValid Promo Code");
                return;
            }

        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.makeSaveOrderBooking().observe(this, new Observer<GetSaveOrdersResponce>() {
            @Override
            public void onChanged(@Nullable GetSaveOrdersResponce response) {
                if (response != null) {
                    if (response.getSuccess() == 1) {
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
//        showToast(getActivity(), userId + " " + userName + " " + servicesSelectedIds.get(0).getServiceId());
        Call<GetSaveOrdersResponce> saveServiceOrdersCall = null;

        SaveServiceOrdersRequest saveServiceOrdersRequest = new SaveServiceOrdersRequest();
        saveServiceOrdersRequest.setUserId(userId);
        saveServiceOrdersRequest.setUserName(userName);
        saveServiceOrdersRequest.setUserPhone(userPhone);
        saveServiceOrdersRequest.setOrderDate(date);
        saveServiceOrdersRequest.setOrderTime(time);
        saveServiceOrdersRequest.setUserCity("");
        saveServiceOrdersRequest.setMapLong(String.valueOf(myLang));
        saveServiceOrdersRequest.setMapLat(String.valueOf(myLat));
        saveServiceOrdersRequest.setToken(userToken);
//        if(promoSelectedId>1){
//        saveServiceOrdersRequest.setPromo_code(promoFilterValueId);
//        }else {
//            saveServiceOrdersRequest.setPromo_code("");
//
//        }
        if(foundPromo){
            promoCodeFromUser = fragmentHomeCompletBookingServicesTilPromoCode.getEditText().getText().toString();
            if (promoCodeFromUser.length()==0) {
//                showToast(getActivity(), "her"+promoFilterValueId);
                saveServiceOrdersRequest.setPromo_code("");
            }else {
                saveServiceOrdersRequest.setPromo_code(promoFilterValueId);
            }
        }else {
            saveServiceOrdersRequest.setPromo_code("");

        }
        saveServiceOrdersRequest.setOrderServiceList(servicesSelectedIds);
        Log.i(TAG, String.valueOf(saveServiceOrdersRequest.getUserId() + " " + saveServiceOrdersRequest.getUserName() + " " + saveServiceOrdersRequest.getUserPhone() + " "
                + saveServiceOrdersRequest.getUserCity() + " " + saveServiceOrdersRequest.getMapLong() + " " + saveServiceOrdersRequest.getMapLat() + " "
                + saveServiceOrdersRequest.getToken() + " " + saveServiceOrdersRequest.getOrderServiceList().get(0).getServiceId() + " " + saveServiceOrdersRequest.getOrderTime() + "time"
        +"promo "+promoFilterValueId));

//        saveServiceOrdersCall = getApiClient().saveServiceOrders(userId,userName,userPhone,userCity,userToken,servicesSelectedIds);
        saveServiceOrdersCall = getApiClient().saveServiceOrders(saveServiceOrdersRequest);

        viewModelUser.setAndMakeSaveOrderBooking(getActivity(), saveServiceOrdersCall, "Success Service Order Booking", true);


    }
}