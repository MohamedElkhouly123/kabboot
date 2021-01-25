package com.example.kabboot.view.fragment.HomeCycle2.onLineStore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.kabboot.data.local.DataBase;
import com.example.kabboot.data.model.getAllproductsResponce.AllProduct;
import com.example.kabboot.data.model.getAllproductsResponce.AllProductForRom;
import com.example.kabboot.data.model.getUserDataResponce.GetUserDataResponce;
import com.example.kabboot.data.model.getUserDataResponce.UserData;
import com.example.kabboot.data.model.saveStoreOrdersRequest.OrderItemList;
import com.example.kabboot.data.model.saveStoreOrdersRequest.SaveStoreOrdersRequest;
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

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadData;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.kabboot.data.local.SharedPreferencesManger.USER_TOKEN;


public class MyCartFragment extends BaSeFragment {

    @BindView(R.id.fragment_my_cart_total_items_price)
    TextView fragmentMyCartTotalItemsPriceTv;
    @BindView(R.id.fragment_my_cart_recycler_view)
    RecyclerView fragmentMyCartRecyclerView;
    @BindView(R.id.fragment_my_cart_phone3_tv)
    TextView fragmentMyCartPhone3Tv;
    @BindView(R.id.fragment_login_phone_etxt)
    TextInputEditText fragmentLoginPhoneEtxt;
    @BindView(R.id.fragment_my_cart_til_promo_code)
    TextInputLayout fragmentMyCartTilPromoCode;
    @BindView(R.id.fragment_my_cart_Subtotal)
    TextView fragmentMyCartSubtotalTv;
    @BindView(R.id.fragment_my_cart_fees)
    TextView fragmentMyCartFeesTv;
    @BindView(R.id.fragment_my_cart_tax)
    TextView fragmentMyCartTaxTv;
    @BindView(R.id.fragment_my_discount)
    TextView fragmentMyDiscountTv;
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
    public static double myLang=0;
    public static double myLat=0;
    public static boolean mabBack=false;
    private String OnSoreOrAllProductsOrDetails;
    private boolean first=true;

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
        for (int i = 0; i < items.size(); i++) {
            OrderItemList orderItemList = new OrderItemList();
            orderItemList.setProductId(items.get(i).getItemId());
            orderItemList.setProductQty(items.get(i).getQuantity());
            orderItemsList.add(orderItemList);
//            productTotalPrice = (int) ((int) Double.parseDouble(items.get(i).getProductPrice()) * Double.parseDouble(items.get(i).getQuantity()));
//            allProductsTotalPrice += productTotalPrice;
        }
//        fragmentMyCartTotalItemsPriceTv.setText(allProductsTotalPrice+" EGP");
//        showToast(getActivity(), orderItemsList.get(1).getId() + "  " + orderItemsList.get(1).getProductQuantity());

    }

    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.makeResetAndNewPasswordResponseAndSignUpAndBooking().observe(this, new Observer<GetUserDataResponce>() {
            @Override
            public void onChanged(@Nullable GetUserDataResponce response) {
                if (response != null) {
                    if (response.getSuccess() == 1) {
//                        showToast(getActivity(), "success");
                        roomClear();
                        navController.navigate(R.id.action_myCartFragment_to_navigation_online_store);
                        homeCycleActivity.setNavigation("v");
                    }
                }
            }
        });
    }

    private void init() {
        if (items.size() != 0) {
            lLayout = new LinearLayoutManager(getActivity());
            fragmentMyCartRecyclerView.setLayoutManager(lLayout);
            fragmentMyCartRecyclerView.setItemAnimator(null);
//            showToast(getActivity(), items.get(0).getProductId());
            getAllProductsItemsInMyCartAdapter = new GetAllProductsItemsInMyCartAdapter(getContext(), getActivity(),fragmentMyCartTotalItemsPriceTv, navController, items);
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

    private void onCall() {

        userId = userData.getUserId();
        userPhone = userData.getUserPhone();
        userToken = LoadData(getActivity(), USER_TOKEN);
//        showToast(getActivity(), userToken+" "+userPhone+" "+userId+" "+orderItemsList.get(0).getProductQuantity());
        Call<GetUserDataResponce> saveStoreOrdersCall = null;
        SaveStoreOrdersRequest saveStoreOrdersRequest = new SaveStoreOrdersRequest();
        saveStoreOrdersRequest.setUserId(userId);
        saveStoreOrdersRequest.setUserName(userData.getUserName());
        saveStoreOrdersRequest.setUserPhone(userPhone);
        saveStoreOrdersRequest.setUserCity(userData.getUserCity());
        saveStoreOrdersRequest.setOrderDate("");
        saveStoreOrdersRequest.setOrderTime("");
        saveStoreOrdersRequest.setMapLong(String.valueOf(myLang));
        saveStoreOrdersRequest.setMapLat(String.valueOf(myLat));
        saveStoreOrdersRequest.setToken(userToken);
        saveStoreOrdersRequest.setOrderItemList(orderItemsList);

        saveStoreOrdersCall = getApiClient().saveStoreOrders(saveStoreOrdersRequest);

        viewModelUser.setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(getActivity(), saveStoreOrdersCall, "Success Products Order", true);


    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        if(OnSoreOrAllProductsOrDetails.equalsIgnoreCase("onLineStore")){
            navController.navigate(R.id.action_myCartFragment_to_navigation_online_store);
        }
        if(OnSoreOrAllProductsOrDetails.equalsIgnoreCase("details")) {
            Bundle bundle = new Bundle();
            bundle.putString("OnSoreOrAllProducts", onSoreOrAllProducts);
            bundle.putSerializable("Object", productData);
            bundle.putString("DealOrPromo", dealOrPromo);
            navController.navigate(R.id.action_myCartFragment_to_productDetailsFragment, bundle);
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        if(mabBack){
            mabBack=false;
            onBack();
        }
    }

    @OnClick({R.id.fragment_my_cart_back_img, R.id.fragment_my_cart_apply_now_btn, R.id.fragment_home_complet_booking_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_my_cart_back_img:
                onBack();
                break;
            case R.id.fragment_my_cart_apply_now_btn:
                break;
            case R.id.fragment_home_complet_booking_order:
                if(first||(myLang!=0&&myLat!=0)){
                    first=false;
                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    intent.putExtra("key","myCard");
                    getActivity().startActivity(intent);
                    ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.location_required));
                }else {
//                    if(myLang!=0&&myLat!=0) {
                        onCall();
//                    }
                }
                break;
        }
    }
}