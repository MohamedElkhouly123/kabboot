package com.example.kabboot.view.fragment.HomeCycle2.onLineStore;

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
import com.example.kabboot.adapter.HomePromoCodeVrRvAdapter;
import com.example.kabboot.data.local.DataBase;
import com.example.kabboot.data.model.customerPromoCodesResponce.CustomerPromoCodesResponce;
import com.example.kabboot.data.model.customerPromoCodesResponce.CustomerPromocode;
import com.example.kabboot.data.model.getAllproductsResponce.AllProductForRom;
import com.example.kabboot.data.model.getUserDataResponce.UserData;
import com.example.kabboot.utils.OnEndLess;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelGetLists;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadUserData;


public class PromoCodeFragment extends BaSeFragment {


    @BindView(R.id.fragment_promo_code_name_title_tv)
    TextView fragmentPromoCodeNameTitleTv;
    @BindView(R.id.fragment_promo_code_s_fl_shimmer)
    ShimmerFrameLayout fragmentPromoCodeSFlShimmer;
    @BindView(R.id.fragment_promo_code_recycler_view)
    RecyclerView fragmentPromoCodeRecyclerView;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    @BindView(R.id.fragment_promo_code_sr_refresh)
    SwipeRefreshLayout fragmentPromoCodeSrRefresh;
    private NavController navController;
    private List<CustomerPromocode> customerPromocodeList = new ArrayList<CustomerPromocode>();
    private ViewModelGetLists viewModel;
    private HomePromoCodeVrRvAdapter homePromoCodeVrRvAdapter;
    private LinearLayoutManager lLayout;
    private GridLayoutManager gLayout;
    public Integer maxPage = 0;
    private OnEndLess onEndLess;
    private boolean Filter = false;
    private List<AllProductForRom> items = new ArrayList<>();
    private DataBase dataBase;
    private UserData userData;

    public PromoCodeFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_promo_code, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("v");
        userData = LoadUserData(getActivity());

        initListener();
        init();
        return root;
    }

    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetLists.class);

        viewModel.makeGetCustomerPromoCodesDataList().observe(getViewLifecycleOwner(), new Observer<CustomerPromoCodesResponce>() {
            @Override
            public void onChanged(@Nullable CustomerPromoCodesResponce response) {
                try {
                    if (response != null) {
//                        if (response.getStatus().equals("success")) {

//                                showToast(getActivity(), "max="+maxPage);

                        if (response.getCustomerPromocodes() != null) {
                            customerPromocodeList.clear();
                            customerPromocodeList.addAll(response.getCustomerPromocodes());
//                                showToast(getActivity(), "list="+clientrestaurantsListData.get(1));

                            homePromoCodeVrRvAdapter.notifyDataSetChanged();
//                                if(getHotelsItemsListData.size()){
                            maxPage++;
//                                }
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

    private void init() {
        lLayout = new LinearLayoutManager(getActivity());
//        gLayout = new GridLayoutManager(getContext(), 2);
        fragmentPromoCodeRecyclerView.setLayoutManager(lLayout);

        onEndLess = new OnEndLess(lLayout, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        loadMore.setVisibility(View.VISIBLE);
//                        if (Filter) {
//                            getHajjAndUmrahListByFilter(current_page);
//                        } else {
                        getallProductsList(current_page);
//                        }

                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentPromoCodeRecyclerView.addOnScrollListener(onEndLess);

        homePromoCodeVrRvAdapter = new HomePromoCodeVrRvAdapter(getContext(), getActivity(), customerPromocodeList, navController);
        fragmentPromoCodeRecyclerView.setAdapter(homePromoCodeVrRvAdapter);
//            showToast(getActivity(), "success adapter");


        if (customerPromocodeList.size() == 0) {
            getallProductsList(0);
        }
//        else {
//            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//        }

        fragmentPromoCodeSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                maxPage = 0;
//                if (Filter) {
//                    getHajjAndUmrahListByFilter(0);
//                } else {
                getallProductsList(0);
//                }

            }
        });
    }

//    private void getHajjAndUmrahListByFilter(int page) {
//
//        Filter = true;
//        if(page == 0){ maxPage=0;}
////        keyword = topPartInNavGenralPartSearchTil.getEditText().getText().toString().trim();
////        keyword="jfk";
//        Call<GetAllproductsResponce> getUmrahAndHujjResponceCall;
//        getUmrahAndHujjResponceCall = getApiClient().getAllProductsData();
////        getUmrahAndHujjResponceCall = getApiClient().getHajjAndUmrahItemListByFilter("hajj", page,keyword);
////        startShimmer(page);
//        viewModel.getAllProductsDataList(getActivity(), errorSubView, getUmrahAndHujjResponceCall,fragmentAllProductsSrRefresh, loadMore);
//
//
//    }

    private void getallProductsList(int page) {
        Filter = false;
        if (page == 0) {
            maxPage = 0;
        }
        Call<CustomerPromoCodesResponce> getAllproductsResponceCall;

//        startShimmer(page);

        reInit();
        getAllproductsResponceCall = getApiClient().customerPromoCodes(userData.getUserId());

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getCustomerPromoCodesDataList(getActivity(), errorSubView, getAllproductsResponceCall, fragmentPromoCodeSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");


    }


    private void reInit() {
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        customerPromocodeList = new ArrayList<>();
        homePromoCodeVrRvAdapter = new HomePromoCodeVrRvAdapter(getContext(), getActivity(), customerPromocodeList, navController);
        fragmentPromoCodeRecyclerView.setAdapter(homePromoCodeVrRvAdapter);

    }


    public void setError(String errorTitleTxt) {
        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (Filter) {
//                    getHajjAndUmrahListByFilter(0);
//                } else {
                getallProductsList(0);
//                }

            }
        };
        errorSubView.setVisibility(View.VISIBLE);
        errorTitle.setText(errorTitleTxt);

    }


    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        navController.navigate(R.id.action_promoCodeFragment_to_navigation_services);
        homeCycleActivity.setNavigation("v");

    }


}