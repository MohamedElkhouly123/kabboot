package com.example.kabboot.view.fragment.HomeCycle2.HomeServices;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.kabboot.R;
import com.example.kabboot.adapter.AllCitiesVrRvAdapter;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllcitiesResponce.AllCity;
import com.example.kabboot.data.model.getAllcitiesResponce.GetAllcitiesResponce;
import com.example.kabboot.utils.OnEndLess;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelGetLists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;


public class VendorsCityListFragment extends BaSeFragment {



    @BindView(R.id.fragment_vendors_cty_list_name_tv)
    TextView fragmentVendorsCtyListNameTv;
    @BindView(R.id.fragment_vendors_cty_list_recycler_view)
    RecyclerView fragmentVendorsCtyListRecyclerView;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    @BindView(R.id.fragment_vendors_cty_list_sr_refresh)
    SwipeRefreshLayout fragmentVendorsCtyListSrRefresh;
    //    @BindView(R.id.fragment_home_on_site_services_sr_refresh)
//    SwipeRefreshLayout fragmentHomeOnSiteServicesSrRefresh;
    private NavController navController;
    private List<SubCat> subCatDataList = new ArrayList<SubCat>();
    private List<AllCity> getAllCitiesList = new ArrayList<AllCity>();
    //    private ViewModelGetLists viewModel;
    private AllCitiesVrRvAdapter allCitiesVrRvAdapter;
    private LinearLayoutManager lLayout;
    private GridLayoutManager gLayout;
    public Integer maxPage = 0;
    private OnEndLess onEndLess;
    private boolean Filter = false;
    private String mainServiceName;
    private String subServiceName;
    private ViewModelGetLists viewMode2;

    public VendorsCityListFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            mainServiceName = this.getArguments().getString("MainServiceName");
            subServiceName = this.getArguments().getString("SubServiceName");
            subCatDataList = (List<SubCat>) this.getArguments().getSerializable("Object");

        }

        View root = inflater.inflate(R.layout.fragment_vendors_cty_list, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
//        HomeCycleActivity homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setNavigation("g");
        initListener();
        init();
        return root;
    }
    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {

        viewMode2 = ViewModelProviders.of(this).get(ViewModelGetLists.class);

        viewMode2.makegetCityData().observe(this, new Observer<GetAllcitiesResponce>() {
            @Override
            public void onChanged(@Nullable GetAllcitiesResponce response) {
                if (response != null) {
                    getAllCitiesList.clear();
                    getAllCitiesList.addAll(response.getAllCities());
//                                showToast(getActivity(), "list="+response.getGetTopHotels().get(1));
                    allCitiesVrRvAdapter.notifyDataSetChanged();
                    maxPage++;
                    noResultErrorTitle.setVisibility(View.GONE);

                } else {
                    noResultErrorTitle.setVisibility(View.VISIBLE);
                }

            }
        });
    }
    private void init() {
//        ml = new LinearLayoutManager(getActivity());
        gLayout = new GridLayoutManager(getContext(), 2);
        fragmentVendorsCtyListRecyclerView.setLayoutManager(gLayout);

        onEndLess = new OnEndLess(gLayout, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        loadMore.setVisibility(View.VISIBLE);
//                        if (Filter) {
//                            getHajjAndUmrahListByFilter(current_page);
//                        } else {
                        getAllCitiesList(current_page);
//                        }

                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentVendorsCtyListRecyclerView.addOnScrollListener(onEndLess);

        allCitiesVrRvAdapter = new AllCitiesVrRvAdapter(getContext(), getActivity(), getAllCitiesList,subServiceName,subCatDataList, mainServiceName, navController);
        fragmentVendorsCtyListRecyclerView.setAdapter(allCitiesVrRvAdapter);

//            showToast(getActivity(), "success adapter");


        if (getAllCitiesList.size() == 0) {
            getAllCitiesList(0);
        }
//        else {
//            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//        }

        fragmentVendorsCtyListSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                maxPage = 0;
//                if (Filter) {
//                    getHajjAndUmrahListByFilter(0);
//                } else {
                getAllCitiesList(0);
//                }

            }
        });
    }


    private void getAllCitiesList(int page) {
        Filter = false;
        if (page == 0) {
            maxPage = 0;
        }
        Call<GetAllcitiesResponce> getAllcitiesResponceCall;

//        startShimmer(page);

        reInit();
        getAllcitiesResponceCall = getApiClient().getAllcities();

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewMode2.getCityData(getActivity(), errorSubView, getAllcitiesResponceCall, fragmentVendorsCtyListSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");


    }


    private void reInit() {
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        getAllCitiesList = new ArrayList<>();

        allCitiesVrRvAdapter = new AllCitiesVrRvAdapter(getContext(), getActivity(), getAllCitiesList,subServiceName,subCatDataList, mainServiceName, navController);
        fragmentVendorsCtyListRecyclerView.setAdapter(allCitiesVrRvAdapter);

    }


    public void setError(String errorTitleTxt) {
        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (Filter) {
//                    getHajjAndUmrahListByFilter(0);
//                } else {
                getAllCitiesList(0);
//                }

            }
        };
        errorSubView.setVisibility(View.VISIBLE);
        errorTitle.setText(errorTitleTxt);

    }
    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        Bundle bundle = new Bundle();
        bundle.putString("MainServiceName", mainServiceName);
        bundle.putSerializable("Object", (Serializable) subCatDataList);
        navController.navigate(R.id.action_vendorsCityListFragment_to_homeOnSiteServicesFragment,bundle);
//        homeCycleActivity.setNavigation("v");

    }


    @OnClick(R.id.fragment_vendors_cty_list_back_btn)
    public void onClick() {
        onBack();
    }


}