package com.example.kabboot.view.fragment.HomeCycle2.HomeServices;

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
import com.example.kabboot.adapter.HomeServicesMainCategoryHzRvAdapter;
import com.example.kabboot.data.model.getAllServiceDataResponce.GetAllServiceDataResponce;
import com.example.kabboot.data.model.getAllServiceDataResponce.MainCat;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelGetLists;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.utils.HelperMethod.showToast;


public class HomeServicesFragment extends BaSeFragment {

    @BindView(R.id.fragment_home_services_rv_item_hz_rv)
    RecyclerView fragmentHomeServicesRvItemHzRv;
    @BindView(R.id.fragment_home_services_sr_refresh)
    SwipeRefreshLayout fragmentHomeServicesSrRefresh;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    private NavController navController;
    private List<MainCat> getAllServiceData = new ArrayList<com.example.kabboot.data.model.getAllServiceDataResponce.MainCat>();
    private ViewModelGetLists viewModel;
    private HomeServicesMainCategoryHzRvAdapter homeServicesMainCategoryHzRvAdapter;
    private LinearLayoutManager linearLayoutHorizental;

    public HomeServicesFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_services, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        initListener();
//        clientData = LoadUserData(getActivity());
//        if (isConnected(getActivity())) {
        initHozental();
//        }
        getHotelsHomeList();
        return root;
    }

    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetLists.class);

        viewModel.makeGetAllServiceDataResponce().observe(getViewLifecycleOwner(), new Observer<GetAllServiceDataResponce>() {
            @Override
            public void onChanged(@Nullable GetAllServiceDataResponce response) {
                try {
                    if (response != null) {
//                        if (response.getStatus().equals("success")) {
//                            maxPage = response.getData().getLastPage();
//                                showToast(getActivity(), "max="+maxPage);

                        if (response.getMainCat() != null) {


                            getAllServiceData.clear();
                            getAllServiceData.addAll(response.getMainCat());
//                                showToast(getActivity(), "list="+response.getGetTopHotels().get(1));
                            homeServicesMainCategoryHzRvAdapter.notifyDataSetChanged();

                        } else {
                            noResultErrorTitle.setVisibility(View.VISIBLE);
                        }
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

    private void initHozental() {
        linearLayoutHorizental = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        fragmentHomeServicesRvItemHzRv.setLayoutManager(linearLayoutHorizental);
        fragmentHomeServicesRvItemHzRv.setHasFixedSize(true);
//        clientGetRestaurantsFiltterList(0);

        homeServicesMainCategoryHzRvAdapter = new HomeServicesMainCategoryHzRvAdapter(getContext(), getActivity(), getAllServiceData, navController);
        fragmentHomeServicesRvItemHzRv.setAdapter(homeServicesMainCategoryHzRvAdapter);

    }

    private void getHotelsHomeList() {
        fragmentHomeServicesSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                getHotelsHomeList();


            }
        });
        Call<GetAllServiceDataResponce> getDiscoverHomeResponceCall;

//        startShimmer(page);

//        reInit();
        getDiscoverHomeResponceCall = getApiClient().getAllServiceData();

        viewModel.getAllServiceDataResponce(getActivity(), errorSubView, getDiscoverHomeResponceCall, fragmentHomeServicesSrRefresh, loadMore);


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
                Bundle bundle = new Bundle();
                bundle.putString("DealOrPromo", "hotDeal");
                navController.navigate(R.id.action_navigation_services_to_allProductsFragment,bundle);
                homeCycleActivity.setNavigation("g");
                break;
            case R.id.fragment_home_services_promo_codes_btn:
//                Bundle bundle2 = new Bundle();
//                bundle2.putString("DealOrPromo", "onStock");
                navController.navigate(R.id.action_navigation_services_to_promoCodeFragment);
                homeCycleActivity.setNavigation("g");
                break;
        }
    }
}