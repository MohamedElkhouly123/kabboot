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
import com.example.kabboot.data.model.ItemGeneralObjectModel;
import com.example.kabboot.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShowVendorDaysAvailableItemsAdapter extends RecyclerView.Adapter<ShowVendorDaysAvailableItemsAdapter.ViewHolder> {



    private BaseActivity activity;
    private Context context;
    private List<ItemGeneralObjectModel> getDisscoverGetHotelsItemsListData = new ArrayList<>();
    private NavController navController;
    private static boolean show = false;

    public ShowVendorDaysAvailableItemsAdapter(Context context, Activity activity, NavController navController, List<ItemGeneralObjectModel> getHomeDisscoverGetHotelsDataItemsListData) {
        getDisscoverGetHotelsItemsListData.clear();
        this.activity = (BaseActivity) activity;
        this.context = context;
        this.navController = navController;
        this.getDisscoverGetHotelsItemsListData = getHomeDisscoverGetHotelsDataItemsListData;
//        this.itemNum = itemNum;
//                showToast(activity, String.valueOf(itemNum));
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.fragment_vendor_profile_days_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

//        HotelData hotelData = getDisscoverGetHotelsItemsListData.get(position);
//            holder.cardviewHzDiscoverItemRateImgHide.setVisibility(View.VISIBLE);
            holder.fragmentVendorProfileDaysItemDayNameTv.setText(String.valueOf(getDisscoverGetHotelsItemsListData.get(position).getName()));
//            holder.cardviewHzDiscoverItemPriceTv.setText("$ "+hotelData.getMinPrice());
//        showToast(activity, String.valueOf(getDisscoverGetHotelsItemsListData.size()+ "  " +String.valueOf(itemNum)));

    }

    private void setAction(ViewHolder holder, int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return getDisscoverGetHotelsItemsListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fragment_vendor_profile_days_item_day_name_tv)
        TextView fragmentVendorProfileDaysItemDayNameTv;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
//            navController = Navigation.findNavController(activity, R.id.home_activity_fragment);

        }
    }
}
