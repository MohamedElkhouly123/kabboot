package com.example.kabboot.view.fragment.HomeCycle2.onLineStore;

import android.os.Bundle;
import android.util.Base64;
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
import com.example.kabboot.data.local.DataBase;
import com.example.kabboot.data.model.getAllproductsResponce.AllProduct;
import com.example.kabboot.data.model.getAllproductsResponce.AllProductForRom;
import com.example.kabboot.data.model.getAllproductsResponce.GetAllproductsBySearchResponce;
import com.example.kabboot.utils.OnEndLess;
import com.example.kabboot.utils.OnLineStoteFilterSearchDialog;
import com.example.kabboot.utils.SearchDialogCallback;
import com.example.kabboot.utils.ToastCreator;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelGetLists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;


public class OnLineStoreFragment extends BaSeFragment implements SearchDialogCallback {


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
    private NavController navController;
    private List<AllProduct> allProductsList = new ArrayList<AllProduct>();
    private ViewModelGetLists viewModel;
    private GetAllProductsItemsAdapter getAllProductsItemsAdapter;
    private LinearLayoutManager lLayout;
    private GridLayoutManager gLayout;
    public Integer maxPage = 0;
    private OnEndLess onEndLess;
    private boolean Filter = false;
    private List<AllProductForRom> items= new ArrayList<>();
    private DataBase dataBase;
    private String credentials;
    private String cookie;
    private String searchName;
    private String searchprice;
    private String vendorIdValue;
    private String productCatIdValue;

    public OnLineStoreFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_on_line_store, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("v");
        dataBase = DataBase.getInstance(getContext());
        initListener();
        init();
        return root;
    }

    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetLists.class);

        viewModel.makeGetAllProductsBySearchDataList().observe(getViewLifecycleOwner(), new Observer<GetAllproductsBySearchResponce>() {
            @Override
            public void onChanged(@Nullable GetAllproductsBySearchResponce response) {
                try {
                    if (response != null) {
//                        if (response.getStatus().equals("success")) {

//                                showToast(getActivity(), "max="+maxPage);

                        if (response.getDataFounded() != null) {
                            allProductsList.clear();
                            allProductsList.addAll(response.getDataFounded());
//                                showToast(getActivity(), "list="+clientrestaurantsListData.get(1));

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
//                        showToast(getActivity(), "success1");
                    }

                } catch (Exception e) {
                }
            }
        });

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

        getAllProductsItemsAdapter = new GetAllProductsItemsAdapter(getContext(), getActivity(), "", "onStore", navController, allProductsList);
        ffragmentAllProductsRecyclerView.setAdapter(getAllProductsItemsAdapter);
//            showToast(getActivity(), "success adapter");


        if (allProductsList.size() == 0) {
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
//        Filter = false;
        if (page == 0) {
            maxPage = 0;
        }
        Call<GetAllproductsBySearchResponce> getAllproductsResponceCall;

//        startShimmer(page);

        reInit();
        cookie="ci_session=90a7ccf8c0d9d2e3e58d978ccfdd3820c2c3ab40";
        credentials="MDEwMDA3ODc2MTk6MTIz";
        final String basic =
                "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT);
        if(Filter) {
            getAllproductsResponceCall = getApiClient().getAllProductsData(cookie, credentials, searchName, "","0",searchprice, productCatIdValue, vendorIdValue);
        }else {
            getAllproductsResponceCall = getApiClient().getAllProductsData(cookie, credentials, "", "", "", "","","");
        }
//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getAllProductsBySearchDataList(getActivity(), errorSubView, getAllproductsResponceCall, fragmentAllProductsSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");


    }


    private void reInit() {
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        allProductsList = new ArrayList<>();
        getAllProductsItemsAdapter = new GetAllProductsItemsAdapter(getContext(), getActivity(), "", "onStore", navController, allProductsList);
        ffragmentAllProductsRecyclerView.setAdapter(getAllProductsItemsAdapter);

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
        navController.navigate(R.id.action_navigation_online_store_to_navigation_services);

    }


    @OnClick({R.id.fragment_on_line_store_my_care_filter_btn, R.id.fragment_on_line_store_filter_btn, R.id.ffragment_all_products_floating_action_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_on_line_store_my_care_filter_btn:
//                navController.navigate(R.id.action_navigation_online_store_to_allProductsFragment);
//                homeCycleActivity.setNavigation("g");
//                final OnLineStoteMyCarSearchDialog dialog = new OnLineStoteMyCarSearchDialog(this::filterOnMethodCallback,"hotel");
//                dialog.show(getActivity().getSupportFragmentManager(), "example");
                break;
            case R.id.fragment_on_line_store_filter_btn:
                final OnLineStoteFilterSearchDialog dialog2 = new OnLineStoteFilterSearchDialog(this::filterOnMethodCallback,"hotel");
                dialog2.show(getActivity().getSupportFragmentManager(), "example");
                break;
            case R.id.ffragment_all_products_floating_action_btn:
                roomAddAndGetItem();
                break;
        }
    }
    private void roomAddAndGetItem() {
        Executors.newSingleThreadExecutor().execute(

                new Runnable() {
                    @Override
                    public void run() {
//                    dataBase.addNewOrderItemDao().deletAll();
                            items.clear();
//                            allProductForRom = new AllProductForRom(productData.getProductId(), productData.getProductName(), productData.getProductPrice(), productData.getProductCat(), productData.getVendorIdFk(), productData.getProductSpecification(), productData.getProductDesc(), productData.getImage(), productData.getInStock(), productData.getHotdeals(), productData.getMainCategoryName(), productData.getVendorName(), String.valueOf(productItemsNum));
//                            dataBase.addNewOrderItemDao().insert(allProductForRom);
                            items = dataBase.addNewOrderItemDao().getAllItems();
//                            Log.i(TAG,items.get(0).getQuantity() +"  mohamed");
                        if(items.size()>0){
                            Bundle bundle = new Bundle();
                            bundle.putString("OnSoreOrAllProductsOrDetails", "onLineStore");
//                            bundle.putString("OnSoreOrAllProducts", onSoreOrAllProducts);
//                            bundle.putSerializable("Object", productData);
                            bundle.putSerializable("Object2", (Serializable) items);
//                            bundle.putString("DealOrPromo", dealOrPromo);
                            navController.navigate(R.id.action_navigation_online_store_to_myCartFragment,bundle);
                        }else {
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.no_data_in_your_cart));
//                            Toast.makeText(getActivity(), getString(R.string.no_data_in_your_cart), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }

                });
    }


    @Override
    public void filterOnMethodCallback(String searchName, String searchprice, String vendorIdValue, String productCatIdValue) {
        Filter=true;
        allProductsList.clear();
        this.searchName=searchName;
        this.searchprice=searchprice;
        this.vendorIdValue=vendorIdValue;
        this.productCatIdValue=productCatIdValue;
//        showToast(getActivity(), String.valueOf(searchName+"  "+searchprice+ "  "+ vendorIdValue+ "  "+productCatIdValue));
        getallProductsList(0);
    }
}