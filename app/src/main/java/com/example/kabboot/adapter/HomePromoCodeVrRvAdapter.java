package com.example.kabboot.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.customerPromoCodesResponce.CustomerPromocode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomePromoCodeVrRvAdapter extends RecyclerView.Adapter<HomePromoCodeVrRvAdapter.ViewHolder> {
    private final NavController navController;



//    private final DialogAdapterCallback dialogAdapterCallback;

    private Context context;
    private Activity activity;
    private LinearLayoutManager lLayout;
    private int itemNum;
    private List<CustomerPromocode> getCustomerPromocodesList = new ArrayList<>();
    int selectedItem = -1;
    private String payload;
    public int vindorId = -1;
    private List<String> availableDaysList;

    public HomePromoCodeVrRvAdapter(Context context, Activity activity,
                                    List<CustomerPromocode> getCustomerPromocodesList,
                                    NavController navController) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.getCustomerPromocodesList = getCustomerPromocodesList;
        this.navController = navController;
//        this.dialogAdapterCallback = dialogAdapterCallback;
//        showToast(activity, "list=" + getHomeDisscoverGetHotelsDataItemsListData.get(0).getCity());

//        showToast(activity, String.valueOf(itemNum));
//        clientData = LoadUserData(activity);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_promo_codes_item2,
                parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        setData(holder, position);
        setAction(holder, position);


    }


    private void setData(ViewHolder holder, int position) {
        try {

//            final int itemType = getItemViewType(position);
            holder.position = position;

            CustomerPromocode getPromocode = this.getCustomerPromocodesList.get(position);
//            showToast(activity, String.valueOf(position));

//            if (getAllvendor.getImage() != null) {
//                String vindorImage = "https://www.kabboot.com/uploads/vendor/" + getAllvendor.getImage().trim();
//                onLoadCirImageFromUrl(holder.cardviewVendorDetailsItem2PhotoCircularImageView, vindorImage.trim(), context);
//            }
            holder.cardviewPromoCodesNamTv.setText(getPromocode.getPromoCodePercent()+" % Discount");
//            holder.cardviewPromoCodesItem2NumeTv.setText("Promo Code "+getPromocode.getPromoIdFk());
            holder.cardviewPromoCodesItem2NumeTv.setText("Promo Code : "+getPromocode.getPromoCodeName());


        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public int getItemCount() {

        return getCustomerPromocodesList.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_book_service_and_product_orders_name_tv)
        TextView cardviewPromoCodesNamTv;
        @BindView(R.id.cardview_book_service_and_product_orders_item_status_ly)
        LinearLayout cardviewPromoCodesStatusLy;
        @BindView(R.id.cardview_promo_codes_item2_nume_tv)
        TextView cardviewPromoCodesItem2NumeTv;
        @BindView(R.id.cardview_promo_codes_item2_promo_details_tv)
        TextView cardviewPromoCodesItem2PromoDetailsTv;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
