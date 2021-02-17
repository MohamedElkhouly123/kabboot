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
import com.example.kabboot.adapter.GetAllProductsItemsAdapter;
import com.example.kabboot.data.model.getAllproductsResponce.AllProduct;
import com.example.kabboot.data.model.getAllproductsResponce.GetAllproductsResponce;
import com.example.kabboot.utils.OnEndLess;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelGetLists;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;


public class AllProductsFragment extends BaSeFragment {

    @BindView(R.id.ffragment_all_products_recycler_view)
    RecyclerView ffragmentAllProductsRecyclerView;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    @BindView(R.id.fragment_all_products_sr_refresh)
    SwipeRefreshLayout fragmentAllProductsSrRefresh;
    @BindView(R.id.ffragment_all_products_title_name_tv)
    TextView ffragmentAllProductsTitleNameTv;
    private NavController navController;
    private List<AllProduct> allProductsList = new ArrayList<AllProduct>();
    private List<AllProduct> getPromoAllProductsList = new ArrayList<AllProduct>();
    private List<AllProduct> getHotDealAllProductsList = new ArrayList<AllProduct>();
    private ViewModelGetLists viewModel;
    private GetAllProductsItemsAdapter getAllProductsItemsAdapter;
    private LinearLayoutManager lLayout;
    private GridLayoutManager gLayout;
    public Integer maxPage = 0;
    private OnEndLess onEndLess;
    private boolean Filter = false;
    private String dealOrPromo;

    public AllProductsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            dealOrPromo = this.getArguments().getString("DealOrPromo");

        }
        View root = inflater.inflate(R.layout.fragment_all_products, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("v");
        if (dealOrPromo.equalsIgnoreCase("hotDeal")) {
            ffragmentAllProductsTitleNameTv.setText("Hot Deal Products");
        }
        if (dealOrPromo.equalsIgnoreCase("onStock")) {
            ffragmentAllProductsTitleNameTv.setText("On Stock Products");
        }
        initListener();
        init();
        return root;
    }

    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetLists.class);

        viewModel.makeGetAllProductsDataList().observe(getViewLifecycleOwner(), new Observer<GetAllproductsResponce>() {
            @Override
            public void onChanged(@Nullable GetAllproductsResponce response) {
                try {
                    if (response != null) {
//                        if (response.getStatus().equals("success")) {

//                                showToast(getActivity(), "max="+maxPage);

                        if (response.getAllProducts() != null) {
                            allProductsList.clear();
                            allProductsList.addAll(response.getAllProducts());
//                                showToast(getActivity(), "list="+clientrestaurantsListData.get(1));
                            getDealsAndPromoList();

                            getAllProductsItemsAdapter.notifyDataSetChanged();
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

    private void getDealsAndPromoList() {
        for (int i = 0; i < allProductsList.size(); i++) {
            if (allProductsList.get(i).getHotdeals().equalsIgnoreCase("1")) {
                getHotDealAllProductsList.add(allProductsList.get(i));
            }
            if (allProductsList.get(i).getInStock().equalsIgnoreCase("yes")) {
                getPromoAllProductsList.add(allProductsList.get(i));
            }
        }
    }

    private void init() {
//        ml = new LinearLayoutManager(getActivity());
        gLayout = new GridLayoutManager(getContext(), 2);
        ffragmentAllProductsRecyclerView.setLayoutManager(gLayout);

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
        ffragmentAllProductsRecyclerView.addOnScrollListener(onEndLess);
        if (dealOrPromo.equalsIgnoreCase("hotDeal")) {
            getAllProductsItemsAdapter = new GetAllProductsItemsAdapter(getContext(), getActivity(), dealOrPromo, "AllProduct", navController, getHotDealAllProductsList);
            ffragmentAllProductsRecyclerView.setAdapter(getAllProductsItemsAdapter);
        }
        if (dealOrPromo.equalsIgnoreCase("onStock")) {
            getAllProductsItemsAdapter = new GetAllProductsItemsAdapter(getContext(), getActivity(), dealOrPromo, "AllProduct", navController, getPromoAllProductsList);
            ffragmentAllProductsRecyclerView.setAdapter(getAllProductsItemsAdapter);
        }
//            showToast(getActivity(), "success adapter");


        if (allProductsList.size() == 0 || getHotDealAllProductsList.size() == 0 || getPromoAllProductsList.size() == 0) {
            getallProductsList(0);
        }
//        else {
//            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//        }

        fragmentAllProductsSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        Call<GetAllproductsResponce> getAllproductsResponceCall;

//        startShimmer(page);

        reInit();
        getAllproductsResponceCall = getApiClient().getAllProductsData();

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getAllProductsDataList(getActivity(), errorSubView, getAllproductsResponceCall, fragmentAllProductsSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");


    }


    private void reInit() {
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        allProductsList = new ArrayList<>();
        getPromoAllProductsList = new ArrayList<>();
        getHotDealAllProductsList = new ArrayList<>();
        if (dealOrPromo.equalsIgnoreCase("hotDeal")) {
            getAllProductsItemsAdapter = new GetAllProductsItemsAdapter(getContext(), getActivity(), dealOrPromo, "AllProduct", navController, getHotDealAllProductsList);
            ffragmentAllProductsRecyclerView.setAdapter(getAllProductsItemsAdapter);
        }
        if (dealOrPromo.equalsIgnoreCase("onStock")) {
            getAllProductsItemsAdapter = new GetAllProductsItemsAdapter(getContext(), getActivity(), dealOrPromo, "AllProduct", navController, getPromoAllProductsList);
            ffragmentAllProductsRecyclerView.setAdapter(getAllProductsItemsAdapter);
        }
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


        navController.navigate(R.id.action_allProductsFragment_to_navigation_services);
        homeCycleActivity.setNavigation("v");

    }


}