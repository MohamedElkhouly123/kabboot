package com.example.kabboot.view.fragment.HomeCycle2.HomeServices;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.kabboot.R;
import com.example.kabboot.adapter.CitySpinnerAdapter;
import com.example.kabboot.adapter.HomeServicesVendors2VrRvAdapter;
import com.example.kabboot.adapter.SpinnerAdapter;
import com.example.kabboot.data.model.DateTxt;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllcitiesResponce.GetAllcitiesResponce;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllVendorsDataResponce;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllvendors;
import com.example.kabboot.utils.OnEndLess;
import com.example.kabboot.utils.ToastCreator;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelGetLists;
import com.example.kabboot.view.viewModel.ViewModelUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.utils.HelperMethod.showToast;


public class HomeServicesEnterVenderDataFragment extends BaSeFragment {

    //    @BindView(R.id.fragment_home_services_enter_vendor_data_til_date)
//    TextInputLayout fragmentHomeServicesEnterVendorDataTilDate;
//    @BindView(R.id.fragment_home_services_enter_vendor_data_til_time)
//    TextInputLayout fragmentHomeServicesEnterVendorDataTilTime;
//    @BindView(R.id.fragment_home_services_enter_vendor_data_til_address)
//    TextInputLayout fragmentHomeServicesEnterVendorDataTilAddress;
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
    @BindView(R.id.fragment_home_services_enter_vendor_data_sr_refresh)
    SwipeRefreshLayout fragmentHomeServicesEnterVendorDataSrRefresh;
    @BindView(R.id.fragment_home_services_enter_vendor_data_title_tv)
    TextView fragmentHomeServicesEnterVendorDataTitleTv;
    @BindView(R.id.fragment_home_services_enter_vendor_data_providers_num_tv)
    TextView fragmentHomeServicesEnterVendorDataProvidersNumTv;
    @BindView(R.id.fragment_home_services_enter_vendor_data_sub_cat_tv)
    TextView fragmentHomeServicesEnterVendorDataSubCatTv;
    @BindView(R.id.fragment_home_services_enter_vendor_data_sp_sub_cat)
    Spinner fragmentHomeServicesEnterVendorDataSpSubCat;
    @BindView(R.id.fragment_home_services_enter_vendor_data_sp_city)
    Spinner fragmentHomeServicesEnterVendorDataSpCity;
    @BindView(R.id.fragment_home_services_enter_vendor_data_city_tv)
    TextView fragmentHomeServicesEnterVendorDataCityTv;
    private NavController navController;
    private List<GetAllvendors> getAllvendorsList = new ArrayList<GetAllvendors>();
    private ViewModelGetLists viewModel;
    private HomeServicesVendors2VrRvAdapter homeServicesVendorsVrRvAdapter,homeServicesVendorsVrRvAdapter2;
    private LinearLayoutManager lLayout;
    private LinearLayoutManager linearLayout;
    public Integer maxPage = 0;
    private OnEndLess onEndLess;
    private String mainServiceName;
    private List<SubCat> subCatDataList = new ArrayList<SubCat>();
    private DateTxt checkinDate;
    private int myVendorId;
    private GetAllvendors vendorData;
    private List<String> availableDaysList = new ArrayList<>();
    private String subServiceName;
    private SpinnerAdapter subCatSpinnerAdapter;
    private AdapterView.OnItemSelectedListener subCatListener;
    private int subCatSelectedId1=0;
    private ViewModelUser viewMode2;
    private CitySpinnerAdapter cityFilterAdapter;
    private int citiesSelectedId=0;
    private AdapterView.OnItemSelectedListener citySpinerListener;
    private String cityFilterValue;
//    private List<GetAllvendors> getAllvendorsListByFilter = new ArrayList<GetAllvendors>();
    private List<GetAllvendors> getAllvendorsListByFilter = new ArrayList<GetAllvendors>();
    private boolean filter=false;

    public HomeServicesEnterVenderDataFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            mainServiceName = this.getArguments().getString("MainServiceName");
            subServiceName = this.getArguments().getString("SubServiceName");
            subCatDataList = (List<SubCat>) this.getArguments().getSerializable("Object");

        }
        View root = inflater.inflate(R.layout.fragment_home_services_enter_vendor_data, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("g");
        fragmentHomeServicesEnterVendorDataTitleTv.setText(mainServiceName + " Service");
        initListener();
        setData();
        init();
        return root;
    }


    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetLists.class);
        viewMode2 = ViewModelProviders.of(this).get(ViewModelUser.class);

        viewMode2.makegetSpinnerData().observe(this, new Observer<GetAllcitiesResponce>() {
            @Override
            public void onChanged(@Nullable GetAllcitiesResponce response) {
                if (response != null) {
//                    if (response.getStatus() == 1) {
////                        showToast(getActivity(), "success2");
//
//                    } else {
////                        showToast(getActivity(), "error");
//
//                    }
                }
            }
        });

        setSpinner();


        viewModel.makeGetAllvendorsResponce().observe(getViewLifecycleOwner(), new Observer<GetAllVendorsDataResponce>() {
            @Override
            public void onChanged(@Nullable GetAllVendorsDataResponce response) {
                try {
                    if (response != null) {
//                        if (response.getStatus().equals("success")) {
//                            maxPage = response.getData().getLastPage();
//                                showToast(getActivity(), "max="+maxPage);

                        if (response.getMainVendors() != null) {


                            getAllvendorsList.clear();
                            getAllvendorsList.addAll(response.getMainVendors());
                            fragmentHomeServicesEnterVendorDataProvidersNumTv.setText(response.getMainVendors().size() + " Providers nearby");

//                                showToast(getActivity(), "list="+response.getGetTopHotels().get(1));

                            homeServicesVendorsVrRvAdapter.notifyDataSetChanged();
                            noResultErrorTitle.setVisibility(View.GONE);

                        } else {
                            noResultErrorTitle.setVisibility(View.VISIBLE);
                        }
//                                showToast(getActivity(), "success1");

//                        }
                    } else {

                    }

                } catch (Exception e) {
                }
            }
        });

    }

    private void setSpinner() {

        cityFilterAdapter = new CitySpinnerAdapter(getActivity());
        viewMode2.getSpinnerData(getActivity(), fragmentHomeServicesEnterVendorDataSpCity, cityFilterAdapter, "",
                getApiClient().getAllcities(), citiesSelectedId, null);
        citySpinerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                citiesSelectedId = i;
                cityFilterValue =String.valueOf(cityFilterAdapter.getItem(i));
                fragmentHomeServicesEnterVendorDataCityTv.setText(getString(R.string.Filters));
                if(citiesSelectedId>0) {
                    fragmentHomeServicesEnterVendorDataCityTv.setText(cityFilterValue);
                    if (citiesSelectedId == 1) {
                        getAllvendorsList(0);
                        filter=false;
                    }
                    if (citiesSelectedId > 1) {
//                        showToast(getActivity(), cityFilterValue+ " yes");

                        filter=true;
                        getAllvendorsListByFilter = new ArrayList<>();
                        getVendorDataListByFilter(0);
                    }
//                showToast(getActivity(), String.valueOf(priceSelectedId1));
            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        fragmentHomeServicesEnterVendorDataSpCity.setOnItemSelectedListener(citySpinerListener);

    }

    private void getVendorDataListByFilter(int page) {
        if (page == 0) {
            maxPage = 0;
        }


//                    filter=true;
                    getAllvendorsListByFilter.clear();
//                    getVendorDataListByFilter(0);
//        showToast(getActivity(),""+ getAllvendorsList.size());
        for(int i=0;i<getAllvendorsList.size();i++) {
            if (getAllvendorsList.get(i).getVendorCity().equalsIgnoreCase(cityFilterValue)) {
                getAllvendorsListByFilter.add(getAllvendorsList.get(i));
//                            showToast(getActivity(), getAllvendorsList.get(i).getVendorCity()+" "+cityFilterValue);

            }
        }
                    if (getAllvendorsListByFilter.size() != 0) {
                        noResultErrorTitle.setVisibility(View.GONE);
                        reInit2();
                    } else {
                        noResultErrorTitle.setVisibility(View.VISIBLE);
                        reInit2();
                    }





    }

    private void setData() {


        subCatSpinnerAdapter = new SpinnerAdapter(getActivity());
        subCatSpinnerAdapter.setData(subCatDataList, "");
        fragmentHomeServicesEnterVendorDataSpSubCat.setAdapter(subCatSpinnerAdapter);
        fragmentHomeServicesEnterVendorDataSpSubCat.setSelection(subCatSelectedId1);
        subCatListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                subCatSelectedId1 = i;
                fragmentHomeServicesEnterVendorDataSubCatTv.setText(String.valueOf(subCatSpinnerAdapter.getItem(subCatSelectedId1)));
//                showToast(getActivity(), String.valueOf(priceSelectedId1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        fragmentHomeServicesEnterVendorDataSpSubCat.setOnItemSelectedListener(subCatListener);
    }


    private void init() {
        linearLayout = new LinearLayoutManager(getActivity());
        fragmentHomeServicesEnterVendorDataRecyclerView.setLayoutManager(linearLayout);

        onEndLess = new OnEndLess(linearLayout, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        loadMore.setVisibility(View.VISIBLE);
                        if(filter){
                            getVendorDataListByFilter(current_page);
                        }else {
                            getAllvendorsList(current_page);
                        }

                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentHomeServicesEnterVendorDataRecyclerView.addOnScrollListener(onEndLess);

        homeServicesVendorsVrRvAdapter = new HomeServicesVendors2VrRvAdapter(getContext(), getActivity(), subServiceName, subCatDataList, mainServiceName, getAllvendorsList, navController);
        fragmentHomeServicesEnterVendorDataRecyclerView.setAdapter(homeServicesVendorsVrRvAdapter);
//            showToast(getActivity(), "success adapter");

        if(filter){
            if (getAllvendorsListByFilter.size() == 0) {
                getVendorDataListByFilter(0);
            }
        }else {
        if (getAllvendorsList.size() == 0) {
            getAllvendorsList(0);
        }}
//        else {
//            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//        }

        fragmentHomeServicesEnterVendorDataSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                maxPage = 0;

                if(filter){
                    getVendorDataListByFilter(0);
                }else {
                    getAllvendorsList(0);

                }
            }
        });
    }

    private void getAllvendorsList(int page) {
        if (page == 0) {
            maxPage = 0;
        }
        filter = false;
        Call<GetAllVendorsDataResponce> getAllvendorsCall;

//        startShimmer(page);

        reInit();
        getAllvendorsCall = getApiClient().clientGetAgetAllvendors();

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getAllvendorsResponce(getActivity(), errorSubView, getAllvendorsCall, fragmentHomeServicesEnterVendorDataSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");


    }


    private void reInit() {
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        getAllvendorsList = new ArrayList<>();
        homeServicesVendorsVrRvAdapter = new HomeServicesVendors2VrRvAdapter(getContext(), getActivity(), subServiceName, subCatDataList, mainServiceName, getAllvendorsList, navController);
        fragmentHomeServicesEnterVendorDataRecyclerView.setAdapter(homeServicesVendorsVrRvAdapter);

    }

    private void reInit2() {
        fragmentHomeServicesEnterVendorDataSrRefresh.setRefreshing(true);
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        fragmentHomeServicesEnterVendorDataProvidersNumTv.setText(getAllvendorsListByFilter.size() + " Providers nearby");
//        showToast(getActivity(),  ""+getAllvendorsListByFilter.size());
        homeServicesVendorsVrRvAdapter2 = new HomeServicesVendors2VrRvAdapter(getContext(), getActivity(), subServiceName, subCatDataList, mainServiceName, getAllvendorsListByFilter, navController);
        fragmentHomeServicesEnterVendorDataRecyclerView.setAdapter(homeServicesVendorsVrRvAdapter2);
//        getAllvendorsList.addAll(response.getMainVendors());

//                                showToast(getActivity(), "list="+response.getGetTopHotels().get(1));

        homeServicesVendorsVrRvAdapter.notifyDataSetChanged();
        fragmentHomeServicesEnterVendorDataSrRefresh.setRefreshing(false);

    }

    public void setError(String errorTitleTxt) {
        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(filter){
                    getVendorDataListByFilter(0);
                }else {
                    getAllvendorsList(0);
                }

            }
        };
        errorSubView.setVisibility(View.VISIBLE);
        errorTitle.setText(errorTitleTxt);

    }


    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
//        showToast(getActivity(), "lat="+myLat+" , "+myLang);
        Bundle bundle = new Bundle();
        bundle.putString("MainServiceName", mainServiceName);
        bundle.putSerializable("Object", (Serializable) subCatDataList);
        navController.navigate(R.id.action_homeServicesEnterVenderDataFragment_to_homeOnSiteServicesFragment, bundle);
    }


    @OnClick({R.id.fragment_home_services_enter_vendor_data_next_btn, R.id.fragment_policy_and_conditions_back_img})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.fragment_home_services_enter_vendor_data_date_etxt:
////                checkinDate = new DateTxt("01", "01", "2021", "01-01-2021");
////                showCalender(getActivity(), getActivity().getString(R.string.select_date), fragmentHomeServicesEnterVendorDataTilDate.getEditText(), checkinDate);
//                DialogFragment datePickerFragment = new HelperMethod.DatePickerFragment(fragmentHomeServicesEnterVendorDataTilDate.getEditText());
//                datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
//                break;
//            case R.id.fragment_home_services_enter_vendor_data_time_etxt:
//                DialogFragment timePickerFragment = new HelperMethod.TimePickerFragment(fragmentHomeServicesEnterVendorDataTilTime.getEditText());
//                timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
//
//                break;
            case R.id.fragment_policy_and_conditions_back_img:
                onBack();
                break;
            case R.id.fragment_home_services_enter_vendor_data_next_btn:
                onVaildate();
                break;
        }
    }

    private void onVaildate() {
//        List<EditText> editTexts = new ArrayList<>();
//        List<TextInputLayout> textInputLayouts = new ArrayList<>();
//        List<Spinner> spinners = new ArrayList<>();
//
//        textInputLayouts.add(fragmentHomeServicesEnterVendorDataTilDate);
//        textInputLayouts.add(fragmentHomeServicesEnterVendorDataTilTime);
//        textInputLayouts.add(fragmentHomeServicesEnterVendorDataTilAddress);
//        cleanError(textInputLayouts);
//
//        if (!validationAllEmpty(editTexts, textInputLayouts, spinners, getString(R.string.empty))) {
//
//            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
//            return;
//        }
//
//
//
//        if (!validationLength(fragmentHomeServicesEnterVendorDataTilDate, getString(R.string.invalid_date_required_field), 1)) {
//            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_date_required_field));
//            return;
//        }
//        if (!validationLength(fragmentHomeServicesEnterVendorDataTilTime, getString(R.string.invalid_time_required_field), 1)) {
//            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_time_required_field));
//            return;
//        }
//        if (!validationLength(fragmentHomeServicesEnterVendorDataTilAddress, getString(R.string.invalid_address_required_field), 1)) {
//            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_address_required_field));
//            return;
//        }
        myVendorId = homeServicesVendorsVrRvAdapter.vindorId;
        if (myVendorId != -1) {
            vendorData = getAllvendorsList.get(myVendorId);
            availableDaysList = getAllAvailableDaysItemList(vendorData);
            if (vendorData.getAllVendorServices().size() != 0) {

                if (availableDaysList.size() != 0) {
//            String date= fragmentHomeServicesEnterVendorDataTilDate.getEditText().getText().toString();
//            String time= fragmentHomeServicesEnterVendorDataTilTime.getEditText().getText().toString();
//            String address= fragmentHomeServicesEnterVendorDataTilAddress.getEditText().getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("MainServiceName", mainServiceName);
//            bundle.putString("Date", date);
//            bundle.putString("Time", time);
//            bundle.putString("Address", address);
                    bundle.putString("SubServiceName", subServiceName);
                    bundle.putSerializable("VendorDataObject", vendorData);
                    bundle.putSerializable("Object", (Serializable) subCatDataList);
                    navController.navigate(R.id.action_homeServicesEnterVenderDataFragment_to_homeServicesEnterVenderData2Fragment, bundle);
                } else {
                    ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_vendor_no_days));
                    return;
                }
            } else {
                ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_vendor_no_services));
                return;
            }
        } else {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_vendor_required_field));
            return;
        }

    }

    private List<String> getAllAvailableDaysItemList(GetAllvendors vendorData2) {

        List<String> allDaysItems = new ArrayList<String>();
        if (vendorData2.getSaturday().equalsIgnoreCase("1")) {
            allDaysItems.add("Saturday");
        }
        if (vendorData2.getSunday().equalsIgnoreCase("1")) {
            allDaysItems.add("Sunday");
        }
        if (vendorData2.getMonday().equalsIgnoreCase("1")) {
            allDaysItems.add("Monday");
        }
        if (vendorData2.getTuesday().equalsIgnoreCase("1")) {
            allDaysItems.add("Tuesday");
        }
        if (vendorData2.getWednesday().equalsIgnoreCase("1")) {
            allDaysItems.add("Wednesday");
        }
        if (vendorData2.getThursday().equalsIgnoreCase("1")) {
            allDaysItems.add("Thursday");
        }
        if (vendorData2.getFriday().equalsIgnoreCase("1")) {
            allDaysItems.add("Friday");
        }
        return allDaysItems;
    }


}