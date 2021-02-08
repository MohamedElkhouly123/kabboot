package com.example.kabboot.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getBookingServiceOrdersRequest.GetBookingServiceOrdersResponce;
import com.example.kabboot.data.model.getBookingServiceOrdersRequest.ServiceOrderDetail;
import com.example.kabboot.data.model.getUserDataResponce.UserData;
import com.example.kabboot.utils.ToastCreator;
import com.example.kabboot.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.kabboot.data.local.SharedPreferencesManger.LoadUserData;


public class GetBookingsServicesItemsAdapter extends RecyclerView.Adapter<GetBookingsServicesItemsAdapter.ViewHolder> {


    private final UserData userData;
    private final String bookingType;
    private BaseActivity activity;
    private Context context;
    private List<GetBookingServiceOrdersResponce> getBookingServiceOrdersResponcesList;
    List<ServiceOrderDetail> serviceOrderDetailsList = new ArrayList<ServiceOrderDetail>();
    ;
    private NavController navController;
    private int i;

    public GetBookingsServicesItemsAdapter(Activity activity, Context context, String bookingType, List<GetBookingServiceOrdersResponce> getBookingServiceOrdersResponcesList,
                                           NavController navController) {
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.bookingType = bookingType;
        this.getBookingServiceOrdersResponcesList = getBookingServiceOrdersResponcesList;
        this.navController = navController;
        userData = LoadUserData(activity);
//        this.photoGallaryAdapterCallback = photoGallaryAdapterCallback;
//        showToast(activity, "date="+flightsListData.get(0).getFlight().getReservationTo());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_book_service_and_product_orders_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {
        holder.position = position;
        serviceOrderDetailsList = getBookingServiceOrdersResponcesList.get(position).getOrderDetails();
        ServiceOrderDetail serviceOrderDetails = serviceOrderDetailsList.get(0);
        i=position+1;
        holder.cardviewBookServiceAndProductOrdersItemOrderNumTv.setText("Order " +i);
        holder.cardviewBookServiceAndProductOrdersItemUserNameTv.setText("Name : " +userData.getUserName());
        holder.cardviewBookServiceAndProductOrdersItemPhoneTv.setText("Contact Number : " +userData.getUserPhone());
        holder.cardviewBookServiceAndProductOrdersItemItemVendorNameTv.setVisibility(View.VISIBLE);
        holder.cardviewBookServiceAndProductOrdersItemTotalPriceTv.setText("Total Price : " + getBookingServiceOrdersResponcesList.get(position).getAllSum() + " EGP");
        holder.cardviewBookServiceAndProductOrdersItemItemVendorNameTv.setText("Vendor Name : " +serviceOrderDetails.getVendorName());
        String status =getBookingServiceOrdersResponcesList.get(position).getHaletTalab();
        if (status.equalsIgnoreCase("neworder")){
            holder.cardviewBookServiceAndProductOrdersItemStatusTv.setText("PENDING ORDER");
        }
        if (status.equalsIgnoreCase("inprogress")){
            holder.cardviewBookServiceAndProductOrdersItemStatusTv.setText("INPROGRESS ORDER");

        }
        if (status.equalsIgnoreCase("completed")){
            holder.cardviewBookServiceAndProductOrdersItemStatusTv.setText("COMPLETED ORDER");

        }
        if (status.equalsIgnoreCase("cancelled")){
            holder.cardviewBookServiceAndProductOrdersItemStatusTv.setText("CANCELED ORDER");

        }

    }


    private void setAction(ViewHolder holder, int position) {
        holder.cardviewBookServiceAndProductOrdersItemCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceOrderDetailsList = getBookingServiceOrdersResponcesList.get(position).getOrderDetails();
                if (serviceOrderDetailsList!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("BookingType", bookingType);
                    bundle.putSerializable("Object2",  getBookingServiceOrdersResponcesList.get(position));
                    navController.navigate(R.id.action_myAllBookingFragment_to_myBookingSubItemsFragment, bundle);
                }else {
                    ToastCreator.onCreateErrorToast(activity, activity.getString(R.string.no_data));

                }
            }
        });
//        holder.cardviewBookEVisaOffersItemMoreBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return getBookingServiceOrdersResponcesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_book_service_and_product_orders_item_status_tv)
        TextView cardviewBookServiceAndProductOrdersItemStatusTv;
        @BindView(R.id.cardview_book_service_and_product_orders_item_status_ly)
        LinearLayout cardviewBookServiceAndProductOrdersItemStatusLy;
        @BindView(R.id.cardview_book_service_and_product_orders_item_order_num_tv)
        TextView cardviewBookServiceAndProductOrdersItemOrderNumTv;
        @BindView(R.id.cardview_book_service_and_product_orders_item_user_name_tv)
        TextView cardviewBookServiceAndProductOrdersItemUserNameTv;
        @BindView(R.id.cardview_book_service_and_product_orders_item_item_vendor_name_tv)
        TextView cardviewBookServiceAndProductOrdersItemItemVendorNameTv;
        @BindView(R.id.cardview_book_service_and_product_orders_item_phone_tv)
        TextView cardviewBookServiceAndProductOrdersItemPhoneTv;
        @BindView(R.id.cardview_book_service_and_product_orders_item_card_btn)
        CardView cardviewBookServiceAndProductOrdersItemCardBtn;
        @BindView(R.id.cardview_book_service_and_product_orders_item_total_price_tv)
        TextView cardviewBookServiceAndProductOrdersItemTotalPriceTv;
        View view;
        private int position;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }
}
