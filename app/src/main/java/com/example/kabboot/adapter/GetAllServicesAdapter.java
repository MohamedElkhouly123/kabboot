package com.example.kabboot.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllvendorsResponce.AllVendorService;
import com.example.kabboot.data.model.saveServiceOrdersRequest.OrderServiceList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.kabboot.utils.HelperMethod.showToast;

public class GetAllServicesAdapter extends RecyclerView.Adapter<GetAllServicesAdapter.ViewHolder> {


    private final NavController navController;
    private Context context;
    private Activity activity;
    //    private List<String> oldBloodTypes = new ArrayList<>();
    private List<AllVendorService> allVendorServiceList = new ArrayList<>();
    public List<AllVendorService> allVendorServiceListSelected = new ArrayList<>();
    public List<OrderServiceList> ids = new ArrayList<>();

    public GetAllServicesAdapter(Context context, Activity activity, NavController navController, List<AllVendorService> allVendorServiceList) {
        ids.clear();
        allVendorServiceListSelected.clear();
        this.context = context;
        this.activity = activity;
        this.navController = navController;
        this.allVendorServiceList = allVendorServiceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_complet_booking_services_item,
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

            holder.fragmentHomeCompletBookingServicesItemCbRemember.setChecked(false);
//            ids.add(getAmenity2s.get(position).getId());
            holder.fragmentHomeCompletBookingServicesItemCbRemember.setText(allVendorServiceList.get(position).getServiceName());
            holder.fragmentHomeCompletBookingServicesItemPriceTv.setText(allVendorServiceList.get(position).getServicePrice()+" EGP");

        } catch (Exception e) {

        }
    }

    private void setAction(ViewHolder holder, final int position) {

        try {
            holder.fragmentHomeCompletBookingServicesItemCbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        OrderServiceList orderServiceList=new OrderServiceList();
                        orderServiceList.setServiceId(Integer.valueOf(allVendorServiceList.get(position).getServiceId()));
                        ids.add(orderServiceList);
                        allVendorServiceListSelected.add(allVendorServiceList.get(position));
                    } else {

                        for (int i = 0; i < ids.size(); i++) {
                            if (allVendorServiceListSelected.get(i).getServiceId().equals(allVendorServiceList.get(position).getServiceId())) {
//                                showToast(activity, String.valueOf(ids.size()));
                                ids.remove(i);
                                allVendorServiceListSelected.remove(i);
                            }
                        }
                    }
                }
            });

        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return allVendorServiceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fragment_home_complet_booking_services_item_cb_remember)
        CheckBox fragmentHomeCompletBookingServicesItemCbRemember;
        @BindView(R.id.fragment_home_complet_booking_services_item_price_tv)
        TextView fragmentHomeCompletBookingServicesItemPriceTv;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
