package com.example.kabboot.view.fragment.HomeCycle2.accountProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.example.kabboot.adapter.GetBookingsProductsItemsAdapter;
import com.example.kabboot.adapter.GetBookingsServicesItemsAdapter;
import com.example.kabboot.data.model.getBookingProductsOrdersRequest.GetBookingProductsOrdersResponce;
import com.example.kabboot.data.model.getBookingServiceOrdersRequest.GetBookingServiceOrdersResponce;
import com.example.kabboot.data.model.getUserDataResponce.UserData;
import com.example.kabboot.utils.OnEndLess;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelGetBookingsLists;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.kabboot.utils.HelperMethod.showToast;


public class MyAllBookingFragment extends BaSeFragment  {

    @BindView(R.id.fragment_my_all_booking_name_title_tv)
    TextView fragmentMyAllBookingNameTitleTv;
    @BindView(R.id.fragment_my_all_booking_recycler_view)
    RecyclerView fragmentMyAllBookingRecyclerView;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    @BindView(R.id.fragment_my_all_booking_sr_refresh)
    SwipeRefreshLayout fragmentMyAllBookingSrRefresh;
//    @BindView(R.id.content_bottom_sheet_photo_gallery_item_img)
//    TouchImageView cardviewHzHajjDetailsPhotoGalleryItemImg2;
    private NavController navController;
    private String bookingType;
    private UserData userData;
    private LinearLayoutManager linearLayout;
    private List<GetBookingProductsOrdersResponce> getProductsOrdersItemsListData = new ArrayList<GetBookingProductsOrdersResponce>();
    private GetBookingsProductsItemsAdapter getBookingsProductsItemsAdapter;
    private List<GetBookingServiceOrdersResponce> getServicesOrdersItemsListData = new ArrayList<GetBookingServiceOrdersResponce>();
    private GetBookingsServicesItemsAdapter getBookingsServicesItemsAdapter;
//    private List<BookingPackage> getTopUmarAndTophajjPackagesData = new ArrayList<BookingPackage>();
//    private GetBookingsHajjAndUmrahItemsAdapter getHajjAndUmrahItemsAdapter;
//    private GetBookingsEVisaItemsAdapter getBookingsEVisaItemsAdapter;
//    private List<EVisaDate> eVisaDates= new ArrayList<EVisaDate>();
    private BottomSheetBehavior bottomSheetBehavior;
    private boolean openSheet = false;
    private ViewModelGetBookingsLists viewModel;
    public Integer maxPage = 0;
    private OnEndLess onEndLess;
    public MyAllBookingFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            bookingType = this.getArguments().getString("BookingType");
//            getProductsOrdersItemsListData = (List<GetBookingProductsOrdersResponce>) this.getArguments().getSerializable("Object1");
//            getServicesOrdersItemsListData = (List<GetBookingServiceOrdersResponce>) this.getArguments().getSerializable("Object2");

        }
        View root = inflater.inflate(R.layout.fragment_get_all_bookings, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        fragmentMyAllBookingNameTitleTv.setText(bookingType);
        userData = LoadUserData(getActivity());
//        bottomSheetBehavior = BottomSheetBehavior.from(root.findViewById(R.id.bottom1));
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        setUpActivity();
        homeCycleActivity.setNavigation("g");
        initListener();
        init();
        return root;
    }

    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetBookingsLists.class);
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Products_Bookings))) {

           viewModel.makeGetBookingsProductsDataList().observe(getViewLifecycleOwner(), new Observer<List<GetBookingProductsOrdersResponce>>() {
            @Override
            public void onChanged(@Nullable List<GetBookingProductsOrdersResponce> response) {
                try {
                    if (response != null) {
//                        showToast(getActivity(), "success1");
//                        if (response.getStatus().equals("success")) {
//                            maxPage = response.getData().getLastPage();

//                            if (response.getFlights() != null) {
                                getProductsOrdersItemsListData.clear();
                                getProductsOrdersItemsListData.addAll(response);
//                                showToast(getActivity(), "list="+getFlightsItemsListData.get(0));

                        getBookingsProductsItemsAdapter.notifyDataSetChanged();
                                maxPage++;
                                noResultErrorTitle.setVisibility(View.GONE);
                                if(getProductsOrdersItemsListData.size()==0){
                                    noResultErrorTitle.setVisibility(View.VISIBLE);

                                }
//                            }else {
//                                noResultErrorTitle.setVisibility(View.VISIBLE);
//                            }
//
//                        }
                    } else {
//                        showToast(getActivity(), "success1");

                    }

                } catch (Exception e) {
                }
            }
        });
       }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_service_Bookings))){

            viewModel.makeGetBookingsServicesDataList().observe(getViewLifecycleOwner(), new Observer<List<GetBookingServiceOrdersResponce>>() {
            @Override
            public void onChanged(@Nullable List<GetBookingServiceOrdersResponce> response) {
                try {
                    if (response != null) {
//                        if (response.getStatus().equals("success")) {

//                                showToast(getActivity(), "max="+maxPage);

//                            if (response.getHotels() != null ) {
                                getServicesOrdersItemsListData.clear();
                                getServicesOrdersItemsListData.addAll(response);
//                                showToast(getActivity(), "list="+getServicesOrdersItemsListData.get(1).getCustomerId());

                        getBookingsServicesItemsAdapter.notifyDataSetChanged();
//                                if(getHotelsItemsListData.size()){
                                maxPage++;
                                noResultErrorTitle.setVisibility(View.GONE);
                                if(getServicesOrdersItemsListData.size()==0){
                                    noResultErrorTitle.setVisibility(View.VISIBLE);

                                }
//                            }else {
//                                noResultErrorTitle.setVisibility(View.VISIBLE);
//                            }
////                                showToast(getActivity(), "success1");
//
//                        }
                    } else {
                        showToast(getActivity(), "success1");
                    }

                } catch (Exception e) {
                }
            }
        });
        }



    }
    private void init() {
        linearLayout = new LinearLayoutManager(getActivity());
        fragmentMyAllBookingRecyclerView.setLayoutManager(linearLayout);

        onEndLess = new OnEndLess(linearLayout, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        loadMore.setVisibility(View.VISIBLE);
                        getByType(current_page);


                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentMyAllBookingRecyclerView.addOnScrollListener(onEndLess);

        if(bookingType.equalsIgnoreCase(getString(R.string.My_Products_Bookings))) {
            getBookingsProductsItemsAdapter = new GetBookingsProductsItemsAdapter(getActivity(), getContext(),bookingType, getProductsOrdersItemsListData,navController);
            fragmentMyAllBookingRecyclerView.setAdapter(getBookingsProductsItemsAdapter);
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_service_Bookings))){
            getBookingsServicesItemsAdapter = new GetBookingsServicesItemsAdapter(getActivity(), getContext(), bookingType,getServicesOrdersItemsListData, navController);
            fragmentMyAllBookingRecyclerView.setAdapter(getBookingsServicesItemsAdapter);
        }

//            showToast(getActivity(), "success adapter");



        getSizeByType();

//        else {
//            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//        }

        fragmentMyAllBookingSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                maxPage=0;
                getByType(0);



            }
        });
    }

    private void getSizeByType() {
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Products_Bookings))) {
            if (getProductsOrdersItemsListData.size() == 0) {
                getBookingProductsList(0);
            }
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_service_Bookings))){
            if (getServicesOrdersItemsListData.size() == 0) {
                getBookServicesList(0);
            }
        }


        }

    private void getByType(int page) {
        if(bookingType.equalsIgnoreCase(getString(R.string.My_Products_Bookings))) {
            getBookingProductsList(page);

        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_service_Bookings))){
            getBookServicesList(page);

        }

        }






    private void getBookingProductsList(int page) {
        if (page == 0) {
            maxPage = 0;
        }

        Call<List<GetBookingProductsOrdersResponce>> getBookingProductsResponceCall;
//        startShimmer(page);

        reInit();
        getBookingProductsResponceCall = getApiClient().getAllBookingsProductsData(userData.getUserId());

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getHBookingsProductsDataList(getActivity(), errorSubView, getBookingProductsResponceCall, fragmentMyAllBookingSrRefresh, loadMore);
//            showToast(getActivity(), "success ");


    }

    private void getBookServicesList(int page) {
        if(page == 0){ maxPage=0;}
        Call<List<GetBookingServiceOrdersResponce>> getBookingServicesResponceCall;

//        startShimmer(page);

        reInit();
        getBookingServicesResponceCall = getApiClient().getAllBookingsServicesData(userData.getUserId());

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getBookingsServicesDataList(getActivity(), errorSubView, getBookingServicesResponceCall,fragmentMyAllBookingSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");



    }
    private void reInit() {
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;

        if(bookingType.equalsIgnoreCase(getString(R.string.My_Products_Bookings))) {
            getProductsOrdersItemsListData = new ArrayList<>();
            getBookingsProductsItemsAdapter = new GetBookingsProductsItemsAdapter(getActivity(), getContext(), bookingType, getProductsOrdersItemsListData,navController);
            fragmentMyAllBookingRecyclerView.setAdapter(getBookingsProductsItemsAdapter);
        }
        if(bookingType.equalsIgnoreCase(getString(R.string.My_service_Bookings))) {
            getServicesOrdersItemsListData = new ArrayList<>();
            getBookingsServicesItemsAdapter = new GetBookingsServicesItemsAdapter(getActivity(), getContext(), bookingType, getServicesOrdersItemsListData, navController);
            fragmentMyAllBookingRecyclerView.setAdapter(getBookingsServicesItemsAdapter);

        }

        }


    public void setError(String errorTitleTxt) {
        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getByType(0);



            }
        };
        errorSubView.setVisibility(View.VISIBLE);
        errorTitle.setText(errorTitleTxt);

    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new AccountFragment());

        navController.navigate(R.id.action_myAllBookingFragment_to_navigation_profile);
        homeCycleActivity.setNavigation("v");
    }

    @OnClick(R.id.fragment_my_all_booking_back_img)
    public void onViewClicked() {
        onBack();
    }

}