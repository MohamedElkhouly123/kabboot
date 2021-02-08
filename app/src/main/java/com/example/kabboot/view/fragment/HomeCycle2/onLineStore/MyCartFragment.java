package com.example.kabboot.view.fragment.HomeCycle2.onLineStore;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.kabboot.adapter.GetAllProductsItemsInMyCartAdapter;
import com.example.kabboot.adapter.PromoCodesSpinnerAdapter;
import com.example.kabboot.data.local.DataBase;
import com.example.kabboot.data.model.customerPromoCodesResponce.CustomerPromoCodesResponce;
import com.example.kabboot.data.model.getAllproductsResponce.AllProduct;
import com.example.kabboot.data.model.getAllproductsResponce.AllProductForRom;
import com.example.kabboot.data.model.getSaveOrdersResponce.GetSaveOrdersResponce;
import com.example.kabboot.data.model.getUserDataResponce.UserData;
import com.example.kabboot.data.model.saveStoreOrdersRequest.OrderItemList;
import com.example.kabboot.data.model.saveStoreOrdersRequest.SaveStoreOrdersRequest;
import com.example.kabboot.utils.MyCartAdapterCallback;
import com.example.kabboot.utils.ToastCreator;
import com.example.kabboot.view.activity.MapsActivity;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelUser;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static android.content.ContentValues.TAG;
import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadData;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.kabboot.data.local.SharedPreferencesManger.USER_TOKEN;


public class MyCartFragment extends BaSeFragment implements MyCartAdapterCallback {

    @BindView(R.id.fragment_my_cart_total_items_price)
    TextView fragmentMyCartTotalItemsPriceTv;
    @BindView(R.id.fragment_my_cart_recycler_view)
    RecyclerView fragmentMyCartRecyclerView;
    @BindView(R.id.fragment_my_cart_phone3_tv)
    TextView fragmentMyCartPhone3Tv;
//    @BindView(R.id.fragment_login_phone_etxt)
//    TextInputEditText fragmentLoginPhoneEtxt;

    //    @BindView(R.id.fragment_my_cart_fees)
//    TextView fragmentMyCartFeesTv;
//    @BindView(R.id.fragment_my_cart_tax)
//    TextView fragmentMyCartTaxTv;
    @BindView(R.id.fragment_my_cart_promo_code_sp_promo)
    Spinner fragmentMyCartPromoCodeSpPromo;
    @BindView(R.id.fragment_my_cart_phone_etxt)
    TextInputEditText fragmentMyCartPhoneEtxt;
    @BindView(R.id.fragment_my_cart_til_promo_code)
    TextInputLayout fragmentMyCartTilPromoCode;
    @BindView(R.id.fragment_my_cart_Subtotal)
    TextView fragmentMyCartSubtotalTv;
    @BindView(R.id.fragment_my_cart_discount)
    TextView fragmentMyCartDiscountTv;
    @BindView(R.id.fragment_my_cart_total_price)
    TextView fragmentMyCartTotalPriceTv;

    private NavController navController;
    private String onSoreOrAllProducts;
    private String dealOrPromo;
    private AllProduct productData;
    private AllProductForRom allProductForRom;
    private List<AllProductForRom> items = new ArrayList<>();
    private List<OrderItemList> orderItemsList = new ArrayList<>();
    private DataBase dataBase;
    private UserData userData;
    private ViewModelUser viewModelUser;
    private String userId, userPhone, userToken;
    private LinearLayoutManager lLayout;
    private GetAllProductsItemsInMyCartAdapter getAllProductsItemsInMyCartAdapter;
    private int productTotalPrice = 0;
    private int allProductsTotalPrice = 0;
    public static double myLang = 0;
    public static double myLat = 0;
    public static boolean mabBack = false;
    private String OnSoreOrAllProductsOrDetails;
    private boolean first = true;
    private ViewModelUser viewMode2;
    private int promoSelectedId =0;
    private PromoCodesSpinnerAdapter promoCodesSpinnerAdapter;
    private AdapterView.OnItemSelectedListener promoCodeSpinerListener;
    private String promoFilterValue;
    private String promoFilterValueId;
    private int discountPrice;
    private int finalTotalPrice;

    public MyCartFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            onSoreOrAllProducts = this.getArguments().getString("OnSoreOrAllProducts");
            OnSoreOrAllProductsOrDetails = this.getArguments().getString("OnSoreOrAllProductsOrDetails");
            dealOrPromo = this.getArguments().getString("DealOrPromo");
            productData = (AllProduct) this.getArguments().getSerializable("Object");
            items = (List<AllProductForRom>) this.getArguments().getSerializable("Object2");

        }
        View root = inflater.inflate(R.layout.fragment_my_cart, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("g");
        userData = LoadUserData(getActivity());
        dataBase = DataBase.getInstance(getContext());
        getOrderDataList();
        init();
        initListener();

//        roomGetList();
//        getOrderDataList();

        return root;
    }

    private void getOrderDataList() {
        orderItemsList.clear();
        for (int i = 0; i < items.size(); i++) {
            OrderItemList orderItemList = new OrderItemList();
            orderItemList.setProductId(items.get(i).getProductId());
            orderItemList.setProductQty(items.get(i).getQuantity());
            orderItemsList.add(orderItemList);
            productTotalPrice = (int) ((int) Double.parseDouble(items.get(i).getProductPrice()) * Double.parseDouble(items.get(i).getQuantity()));
            allProductsTotalPrice += productTotalPrice;
        }
//        fragmentMyCartTotalItemsPriceTv.setText(allProductsTotalPrice+" EGP");
//        showToast(getActivity(), orderItemsList.get(1).getId() + "  " + orderItemsList.get(1).getProductQuantity());
        fragmentMyCartSubtotalTv.setText("Subtotal Before Discount  :  " + allProductsTotalPrice + " EGP");
        fragmentMyCartTotalPriceTv.setText("Total :  " + allProductsTotalPrice + " EGP");
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.makeSaveOrderBooking().observe(this, new Observer<GetSaveOrdersResponce>() {
            @Override
            public void onChanged(@Nullable GetSaveOrdersResponce response) {
                if (response != null) {
                    if (response.getSuccess() == 1) {
//                        showToast(getActivity(), "success");
                        myLang=0;
                        myLat=0;
                        mabBack=false;
                        roomClear();
                        navController.navigate(R.id.action_myCartFragment_to_navigation_online_store);
                        homeCycleActivity.setNavigation("v");
                    }
                }
            }
        });


            viewMode2 = ViewModelProviders.of(this).get(ViewModelUser.class);

        viewMode2.makeGetSpinnerPromoCodeData().observe(this, new Observer<CustomerPromoCodesResponce>() {
            @Override
            public void onChanged(@Nullable CustomerPromoCodesResponce response) {
                if (response != null) {
//                    if (response.getCustomerPromocodes().size()==0) {
////                        showToast(getActivity(), "success2");
//                        fragmentMyCartTilPromoCode.getEditText().setText("No Available Promo Codes Now");
//
//                    } else {
////                        showToast(getActivity(), "error");
//
//                    }
                }
            }
        });

            setSpinner();
    }

    private void setSpinner() {

        promoCodesSpinnerAdapter = new PromoCodesSpinnerAdapter(getActivity());
        viewMode2.getSpinnerPromoCodeData(getActivity(), fragmentMyCartPromoCodeSpPromo, promoCodesSpinnerAdapter, "",
                getApiClient().customerPromoCodes(userData.getUserId()), promoSelectedId, null, fragmentMyCartTilPromoCode);
        promoCodeSpinerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                promoSelectedId = i;

//                fragmentHomeServicesEnterVendorDataCityTv.setText(getString(R.string.Filters));
                if(promoSelectedId >1) {
                    promoFilterValue =String.valueOf(promoCodesSpinnerAdapter.getItem(i)+" % Discount");
                    promoFilterValueId =String.valueOf(promoCodesSpinnerAdapter.getItemId(i));
                    discountPrice= (allProductsTotalPrice / 100 ) * Integer.parseInt(promoFilterValue);
                    finalTotalPrice=allProductsTotalPrice-discountPrice;
                    fragmentMyCartDiscountTv.setText("Discount ( " + promoFilterValue + " % ) : "+ discountPrice + " EGP");
                    fragmentMyCartTotalPriceTv.setText("Total :  " + finalTotalPrice + " EGP");

//                    fragmentHomeServicesEnterVendorDataCityTv.setText(cityFilterValue);
//                    if (promoSelectedId == 1) {
//
//                    }
//                    if (promoSelectedId > 1) {
////                        showToast(getActivity(), cityFilterValue+ " yes");
                        fragmentMyCartTilPromoCode.getEditText().setText("Promo Code "+promoFilterValueId+"   ( "+promoFilterValue+" )");
//
//
//                    }
//                showToast(getActivity(), String.valueOf(priceSelectedId1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        fragmentMyCartPromoCodeSpPromo.setOnItemSelectedListener(promoCodeSpinerListener);

    }

    private void init() {
        if (items.size() != 0) {
            lLayout = new LinearLayoutManager(getActivity());
            fragmentMyCartRecyclerView.setLayoutManager(lLayout);
            fragmentMyCartRecyclerView.setItemAnimator(null);
//            showToast(getActivity(), items.get(0).getProductId());
            getAllProductsItemsInMyCartAdapter = new GetAllProductsItemsInMyCartAdapter(getContext(), getActivity(), this::onMethodCallback,fragmentMyCartTotalItemsPriceTv, navController, items);
            fragmentMyCartRecyclerView.setAdapter(getAllProductsItemsInMyCartAdapter);

//            noResultErrorTitle.setVisibility(View.GONE);

        }
    }


    private void roomClear() {
        Executors.newSingleThreadExecutor().execute(

                new Runnable() {
                    @Override
                    public void run() {

                        dataBase.addNewOrderItemDao().deletAll();

                    }
                });
    }

    private void roomGetItems() {
        Executors.newSingleThreadExecutor().execute(

                new Runnable() {
                    @Override
                    public void run() {
//                    dataBase.addNewOrderItemDao().deletAll();
                            items.clear();
                            items = dataBase.addNewOrderItemDao().getAllItems();
                            Log.i(TAG,items.get(0).getQuantity() +"  mohamed");
                        }

                });
    }

    private void onCall() {

        userId = userData.getUserId();
        userPhone = userData.getUserPhone();
        userToken = LoadData(getActivity(), USER_TOKEN);
//        showToast(getActivity(), userToken+" "+userPhone+" "+userId+" "+orderItemsList.get(0).getProductQuantity());
        Call<GetSaveOrdersResponce> saveStoreOrdersCall = null;
        SaveStoreOrdersRequest saveStoreOrdersRequest = new SaveStoreOrdersRequest();
        saveStoreOrdersRequest.setUserId(userId);
        saveStoreOrdersRequest.setUserName(userData.getUserName());
        saveStoreOrdersRequest.setUserPhone(userPhone);
        saveStoreOrdersRequest.setUserCity("");
//        userData.getUserCity()
        saveStoreOrdersRequest.setOrderDate("");
        saveStoreOrdersRequest.setOrderTime("");
        saveStoreOrdersRequest.setMapLong(String.valueOf(myLang));
        saveStoreOrdersRequest.setMapLat(String.valueOf(myLat));
        saveStoreOrdersRequest.setToken(userToken);
//        saveStoreOrdersRequest.setPromoCode("2");
        if(promoSelectedId>1){
            saveStoreOrdersRequest.setPromoCode(promoFilterValueId);
        }else {
            saveStoreOrdersRequest.setPromoCode("");

        }
        saveStoreOrdersRequest.setOrderItemList(orderItemsList);
        Log.i(TAG,String.valueOf(saveStoreOrdersRequest.getUserId()+" "+saveStoreOrdersRequest.getUserName()+" "+saveStoreOrdersRequest.getUserPhone()+" "
        +saveStoreOrdersRequest.getUserCity()+" "+saveStoreOrdersRequest.getMapLong()+" "+saveStoreOrdersRequest.getMapLat()+" "
        +saveStoreOrdersRequest.getToken()+" "+saveStoreOrdersRequest.getOrderItemList().get(0).getProductQty()+" "
                + saveStoreOrdersRequest.getOrderItemList().get(0).getProductId()+" "+orderItemsList.size()+" "+saveStoreOrdersRequest.getOrderTime()+"time"));

        saveStoreOrdersCall = getApiClient().saveStoreOrders(saveStoreOrdersRequest);

        viewModelUser.setAndMakeSaveOrderBooking(getActivity(), saveStoreOrdersCall, "Success Products Order", true);


    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        if (OnSoreOrAllProductsOrDetails.equalsIgnoreCase("onLineStore")) {
            navController.navigate(R.id.action_myCartFragment_to_navigation_online_store);
        }
        if (OnSoreOrAllProductsOrDetails.equalsIgnoreCase("details")) {
            Bundle bundle = new Bundle();
            bundle.putString("OnSoreOrAllProducts", onSoreOrAllProducts);
            bundle.putSerializable("Object", productData);
            bundle.putString("DealOrPromo", dealOrPromo);
            navController.navigate(R.id.action_myCartFragment_to_productDetailsFragment, bundle);
        }

    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        if(mabBack){
//            mabBack=true;
////            first=true;
////            onBack();
//        }
//    }

    @OnClick({R.id.fragment_my_cart_back_img, R.id.fragment_home_complet_booking_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_my_cart_back_img:
                onBack();
                break;
//            case R.id.fragment_my_cart_apply_now_btn:
//                break;
            case R.id.fragment_home_complet_booking_order:
//                showToast(getActivity(), "lat="+myLat+" , "+myLang);
                roomGetItems();
                getOrderDataListAfterNewQuantity();
                if (mabBack || (myLang == 0 && myLat == 0)) {
                    mabBack = false;
                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    intent.putExtra("key", "myCard");
                    getActivity().startActivity(intent);
                    ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.location_required));
                } else {
//                    if(myLang!=0&&myLat!=0) {
                    onCall();
//                    }
                }
                break;
        }
    }

    private void getOrderDataListAfterNewQuantity() {
        orderItemsList.clear();
        for (int i = 0; i < items.size(); i++) {
            OrderItemList orderItemList = new OrderItemList();
            orderItemList.setProductId(items.get(i).getProductId());
            orderItemList.setProductQty(items.get(i).getQuantity());
            orderItemsList.add(orderItemList);
        }
    }

    @Override
    public void onMethodCallback(int allProductsTotalPrice2) {
        if(promoSelectedId>1) {
            fragmentMyCartSubtotalTv.setText("Subtotal Before Discount  :  " + allProductsTotalPrice2 + " EGP");
            discountPrice = (allProductsTotalPrice2 / 100) * Integer.parseInt(promoFilterValue);
            finalTotalPrice = allProductsTotalPrice2 - discountPrice;
            fragmentMyCartDiscountTv.setText("Discount ( " + promoFilterValue + " % ) : " + discountPrice + " EGP");
            fragmentMyCartTotalPriceTv.setText("Total :  " + finalTotalPrice + " EGP");
        }else {
            fragmentMyCartSubtotalTv.setText("Subtotal Before Discount  :  " + allProductsTotalPrice2 + " EGP");
            fragmentMyCartTotalPriceTv.setText("Total :  " + allProductsTotalPrice2 + " EGP");
        }
    }
}