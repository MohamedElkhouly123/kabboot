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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.adapter.HomeServicesSubCategoryVrRvAdapter;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.view.fragment.BaSeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeOnSiteServicesFragment extends BaSeFragment {


    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    @BindView(R.id.fragment_home_on_site_services_recycler_view)
    RecyclerView fragmentHomeOnSiteServicesRecyclerView;
    @BindView(R.id.fragment_home_on_site_services_main_cat_name_tv)
    TextView fragmentHomeOnSiteServicesMainCatNameTv;
    //    @BindView(R.id.fragment_home_on_site_services_sr_refresh)
//    SwipeRefreshLayout fragmentHomeOnSiteServicesSrRefresh;
    private NavController navController;
    private List<SubCat> subCatDataList = new ArrayList<SubCat>();
    //    private ViewModelGetLists viewModel;
    private HomeServicesSubCategoryVrRvAdapter homeServicesSubCategoryVrRvAdapter;
    private LinearLayoutManager lLayout;
    private GridLayoutManager gLayout;
    private String mainServiceName;

    public HomeOnSiteServicesFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            mainServiceName = this.getArguments().getString("MainServiceName");
            subCatDataList = (List<SubCat>) this.getArguments().getSerializable("Object");

        }
        View root = inflater.inflate(R.layout.fragment_home_on_site_services, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
//        HomeCycleActivity homeCycleActivity = (HomeCycleActivity) getActivity();
        homeCycleActivity.setNavigation("g");
        init();
        return root;
    }

    private void init() {
        noResultErrorTitle.setVisibility(View.VISIBLE);
        if (subCatDataList.size()!= 0) {
            fragmentHomeOnSiteServicesMainCatNameTv.setText(mainServiceName+" Services");
//        lLayout = new LinearLayoutManager(getActivity());
        gLayout = new GridLayoutManager(getContext(), 2);

        fragmentHomeOnSiteServicesRecyclerView.setLayoutManager(gLayout);
            homeServicesSubCategoryVrRvAdapter = new HomeServicesSubCategoryVrRvAdapter(getContext(), getActivity(),subCatDataList, mainServiceName,navController);
            fragmentHomeOnSiteServicesRecyclerView.setAdapter(homeServicesSubCategoryVrRvAdapter);
            noResultErrorTitle.setVisibility(View.GONE);

        }
//        fragmentHomeOnSiteServicesSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//
//                reInit();
//
//
//            }
//        });

    }

    private void reInit() {

        subCatDataList = new ArrayList<SubCat>();
        homeServicesSubCategoryVrRvAdapter = new HomeServicesSubCategoryVrRvAdapter(getContext(), getActivity(), subCatDataList, mainServiceName, navController);
        fragmentHomeOnSiteServicesRecyclerView.setAdapter(homeServicesSubCategoryVrRvAdapter);


    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        navController.navigate(R.id.action_homeOnSiteServicesFragment_to_navigation_services);
        homeCycleActivity.setNavigation("v");

    }


//    @OnClick(R.id.fragment_home_on_site_services_vendor_data_btn)
//    public void onClick() {
//        navController.navigate(R.id.action_homeOnSiteServicesFragment_to_homeServicesEnterVenderDataFragment);
//    }
}