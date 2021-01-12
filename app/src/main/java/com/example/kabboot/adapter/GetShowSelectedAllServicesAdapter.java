package com.example.kabboot.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllvendorsResponce.AllVendorService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetShowSelectedAllServicesAdapter extends RecyclerView.Adapter<GetShowSelectedAllServicesAdapter.ViewHolder> {


    private final NavController navController;
    private Context context;
    private Activity activity;
    private List<AllVendorService> allVendorServiceList = new ArrayList<>();


    public GetShowSelectedAllServicesAdapter(Context context, Activity activity, NavController navController, List<AllVendorService> allVendorServiceList) {

        this.context = context;
        this.activity = activity;
        this.navController = navController;
        this.allVendorServiceList = allVendorServiceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_complet_booking_selected_services_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.setIsRecyclable(false);
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        try {

//            holder.checkboxItemChbx.setChecked(false);

//            holder.fragmentHomeCompletBookingServicesItemCbRemember.setChecked(false);
//            ids.add(getAmenity2s.get(position).getId());
            holder.fragmentHomeCompletBookingSelectedServicesItemCbRememberedTv.setText(allVendorServiceList.get(position).getServiceName());
            holder.fragmentHomeCompletBookingSelectedServicesItemPriceTv.setText(allVendorServiceList.get(position).getServicePrice() + " EGP");

        } catch (Exception e) {

        }
    }

    private void setAction(ViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return allVendorServiceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fragment_home_complet_booking_selected_services_item_cb_remembered_tv)
        TextView fragmentHomeCompletBookingSelectedServicesItemCbRememberedTv;
        @BindView(R.id.fragment_home_complet_booking_selected_services_item_price_tv)
        TextView fragmentHomeCompletBookingSelectedServicesItemPriceTv;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
