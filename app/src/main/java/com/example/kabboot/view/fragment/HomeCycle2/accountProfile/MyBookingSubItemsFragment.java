package com.example.kabboot.view.fragment.HomeCycle2.accountProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.adapter.GetMyAllBookingProductsItemsAdapter;
import com.example.kabboot.adapter.GetShowMyBookingServicesAdapter;
import com.example.kabboot.data.model.getBookingProductsOrdersRequest.GetBookingProductsOrdersResponce;
import com.example.kabboot.data.model.getBookingProductsOrdersRequest.ProductsOrderDetail;
import com.example.kabboot.data.model.getBookingServiceOrdersRequest.GetBookingServiceOrdersResponce;
import com.example.kabboot.data.model.getBookingServiceOrdersRequest.ServiceOrderDetail;
import com.example.kabboot.view.fragment.BaSeFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyBookingSubItemsFragment extends BaSeFragment {


    @BindView(R.id.fragment_my_booking_sub_items_cat_name_tv)
    TextView fragmentMyBookingSubItemsCatNameTv;
    @BindView(R.id.fragment_my_booking_sub_items_name_tv)
    TextView fragmentMyBookingSubItemsNameTv;
    @BindView(R.id.fragment_my_booking_sub_items_total_items_price)
    TextView fragmentMyBookingSubItemsTotalItemsPrice;
    @BindView(R.id.fragment_my_booking_sub_items_sub_cat_name_tv)
    TextView fragmentMyBookingSubItemsSubCatNameTv;
    @BindView(R.id.fragment_my_booking_sub_items_recycler_view)
    RecyclerView fragmentMyBookingSubItemsRecyclerView;
    @BindView(R.id.fragment_my_booking_sub_items_serv_total_price_tv)
    TextView fragmentMyBookingSubItemsServTotalPriceTv;
    @BindView(R.id.fragment_my_booking_sub_items_date_tv)
    TextView fragmentMyBookingSubItemsDateTv;
    @BindView(R.id.fragment_my_booking_sub_items_time_tv)
    TextView fragmentMyBookingSubItemsTimeTv;
    @BindView(R.id.fragment_my_booking_sub_items_date_time_show_ly)
    CardView fragmentMyBookingSubItemsDateTimeShowLy;
    @BindView(R.id.fragment_my_booking_sub_items_total_items_price_ly)
    FrameLayout fragmentMyBookingSubItemsTotalItemsPriceLy;

    private NavController navController;

    private LinearLayoutManager lLayout;
    private GetMyAllBookingProductsItemsAdapter getMyAllBookingProductsItemsAdapter;
    private int productTotalPrice = 0;
    private int allProductsTotalPrice = 0;
    private String bookingType;
    private GetBookingProductsOrdersResponce getBookingProductsOrdersResponcesList;
    private GetBookingServiceOrdersResponce getBookingServiceOrdersResponcesList;
    private List<ProductsOrderDetail> productsOrderDetailList = new ArrayList<>();
    private List<ServiceOrderDetail> serviceOrderDetailList = new ArrayList<>();
    private GetShowMyBookingServicesAdapter getShowMyBookingServicesAdapter;
    private int totalPrice = 0;

    public MyBookingSubItemsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            bookingType = this.getArguments().getString("BookingType");
            getBookingProductsOrdersResponcesList = (GetBookingProductsOrdersResponce) this.getArguments().getSerializable("Object1");
            getBookingServiceOrdersResponcesList = (GetBookingServiceOrdersResponce) this.getArguments().getSerializable("Object2");

        }
        View root = inflater.inflate(R.layout.fragment_my_booking_sub_items, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("g");
        setData();
//        userData = LoadUserData(getActivity());
//        dataBase = DataBase.getInstance(getContext());
        init();


        return root;
    }

    private void setData() {
        if (bookingType.equalsIgnoreCase(getString(R.string.My_Products_Bookings))) {
            productsOrderDetailList = getBookingProductsOrdersResponcesList.getOrderDetails();
            for (int i = 0; i < productsOrderDetailList.size(); i++) {

                productTotalPrice = (int) ((int) Double.parseDouble(productsOrderDetailList.get(i).getProductPrice()) * Double.parseDouble(productsOrderDetailList.get(i).getProductQty()));
                allProductsTotalPrice += productTotalPrice;
            }
            fragmentMyBookingSubItemsCatNameTv.setText("My Booking Products");

            fragmentMyBookingSubItemsTotalItemsPriceLy.setVisibility(View.VISIBLE);
            fragmentMyBookingSubItemsTotalItemsPrice.setText(allProductsTotalPrice + " EGP");
        }
        if (bookingType.equalsIgnoreCase(getString(R.string.My_service_Bookings))) {
            serviceOrderDetailList = getBookingServiceOrdersResponcesList.getOrderDetails();
            for (int i = 0; i < serviceOrderDetailList.size(); i++) {
                totalPrice += (int) Double.parseDouble(serviceOrderDetailList.get(i).getServicePrice());
            }
//        fragmentHomeCompletBookingServicesBookCatNameTv.setText("Book " + mainServiceName);
            fragmentMyBookingSubItemsCatNameTv.setText("My Booking Services");
//            fragmentHomeCompletBookingServicesSubCatNameTv.setText(" Services Booked");
            fragmentMyBookingSubItemsNameTv.setVisibility(View.VISIBLE);
            fragmentMyBookingSubItemsSubCatNameTv.setVisibility(View.VISIBLE);
            fragmentMyBookingSubItemsNameTv.setText(serviceOrderDetailList.get(0).getVendorName());
//        fragmentHomeCompletBookingServicesSubCatPriceTv.setText("11 $");
            fragmentMyBookingSubItemsDateTimeShowLy.setVisibility(View.VISIBLE);
            fragmentMyBookingSubItemsServTotalPriceTv.setVisibility(View.VISIBLE);
            fragmentMyBookingSubItemsServTotalPriceTv.setText("Subtotal : " + totalPrice + " EGP");
            fragmentMyBookingSubItemsDateTv.setText(getBookingServiceOrdersResponcesList.getOrderDate());
            fragmentMyBookingSubItemsTimeTv.setText(getBookingServiceOrdersResponcesList.getOrderTime());
        }


    }


    private void init() {
        if (productsOrderDetailList.size() != 0 || serviceOrderDetailList.size() != 0) {
            lLayout = new LinearLayoutManager(getActivity());
            fragmentMyBookingSubItemsRecyclerView.setLayoutManager(lLayout);
//            fragmentMyBookingSubItemsRecyclerView.setItemAnimator(null);
//            showToast(getActivity(), items.get(0).getProductId());

            if (bookingType.equalsIgnoreCase(getString(R.string.My_Products_Bookings))) {
                getMyAllBookingProductsItemsAdapter = new GetMyAllBookingProductsItemsAdapter(getContext(), getActivity(), navController, productsOrderDetailList);
                fragmentMyBookingSubItemsRecyclerView.setAdapter(getMyAllBookingProductsItemsAdapter);
            }
            if (bookingType.equalsIgnoreCase(getString(R.string.My_service_Bookings))) {
                getShowMyBookingServicesAdapter = new GetShowMyBookingServicesAdapter(getContext(), getActivity(), navController, serviceOrderDetailList);
                fragmentMyBookingSubItemsRecyclerView.setAdapter(getShowMyBookingServicesAdapter);
            }

//            noResultErrorTitle.setVisibility(View.GONE);

        }
    }


    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        if (bookingType.equalsIgnoreCase(getString(R.string.My_Products_Bookings))) {
            Bundle bundle = new Bundle();
            bundle.putString("BookingType", bookingType);
            bundle.putSerializable("Object1", (Serializable) getBookingProductsOrdersResponcesList);
            navController.navigate(R.id.action_myBookingSubItemsFragment_to_myAllBookingFragment, bundle);
        }
        if (bookingType.equalsIgnoreCase(getString(R.string.My_service_Bookings))) {
            Bundle bundle = new Bundle();
            bundle.putString("BookingType", bookingType);
            bundle.putSerializable("Object2", (Serializable) getBookingProductsOrdersResponcesList);
            navController.navigate(R.id.action_myBookingSubItemsFragment_to_myAllBookingFragment, bundle);
        }

    }


    @OnClick(R.id.fragment_my_booking_sub_items_back_img)
    public void onClick() {
        onBack();
    }
}