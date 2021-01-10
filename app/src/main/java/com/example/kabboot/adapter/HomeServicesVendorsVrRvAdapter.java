package com.example.kabboot.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllvendors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.kabboot.utils.HelperMethod.onLoadCirImageFromUrl;


public class HomeServicesVendorsVrRvAdapter extends RecyclerView.Adapter<HomeServicesVendorsVrRvAdapter.ViewHolder> {
    private final NavController navController;
    private final String mainServiceName;
    private final List<SubCat> subCatDataList;


//    private final DialogAdapterCallback dialogAdapterCallback;

    private Context context;
    private Activity activity;
    private LinearLayoutManager lLayout;
    private int itemNum;
    private List<GetAllvendors> getAllvendors = new ArrayList<>();
    int selectedItem = -1;
    private String payload;
    public int vindorId=-1;

    public HomeServicesVendorsVrRvAdapter(Context context, Activity activity,
                                          List<SubCat> subCatDataList, String mainServiceName, List<GetAllvendors> getAllvendors,
                                          NavController navController) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.getAllvendors = getAllvendors;
        this.subCatDataList = subCatDataList;
        this.mainServiceName = mainServiceName;
        this.navController = navController;
//        this.dialogAdapterCallback = dialogAdapterCallback;
//        showToast(activity, "list=" + getHomeDisscoverGetHotelsDataItemsListData.get(0).getCity());

//        showToast(activity, String.valueOf(itemNum));
//        clientData = LoadUserData(activity);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_vendor_details_item,
                parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        setData(holder, position);
        setAction(holder, position);
        if (payload != null && payload.contains("BACKGROUND")) {
            updateBackgroud(holder, position);
        }

    }

    private void updateBackgroud(ViewHolder holder, int position) {
        if (position == selectedItem) {
            holder.cardviewVendorDetailsItemChangeBackgroundLy.setBackgroundResource(R.drawable.orange_shape);
//                    notifyItemChanged(position);
        }else {
            holder.cardviewVendorDetailsItemChangeBackgroundLy.setBackgroundResource(R.drawable.white_shape);
//                    notifyItemChanged(position);
        }
    }

    private void setData(ViewHolder holder, int position) {
        try {

//            final int itemType = getItemViewType(position);
            holder.position = position;

            GetAllvendors getAllvendor = this.getAllvendors.get(position);
//            showToast(activity, String.valueOf(position));

            if (getAllvendor.getImage() != null) {
                String vindorImage = "https://www.kabboot.com/uploads/vendor/" + getAllvendor.getImage().trim();
                onLoadCirImageFromUrl(holder.cardviewVendorDetailsItemPhotoCircularImageView, vindorImage.trim(), context);
            }
            holder.cardviewVendorDetailsItemNameTv.setText(getAllvendor.getVendorName());

//            holder.cardviewVendorDetailsItemStartHourTv.setText("Start Hour : "+getAllvendors.getStartHour());
//            holder.cardviewVendorDetailsItemEndHourTv.setText("End Hour : "+getAllvendors.getEndHour());

        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem=position;
                vindorId=position;
                payload="BACKGROUND";
                notifyItemRangeChanged(0, getItemCount(), "BACKGROUND");
//                int position2 = position;
//// Make sure your position is available on your list.
//                if (position2 != RecyclerView.NO_POSITION) {
//                    if (position2 == selectedItem) {
//                        return;// Here, I don't want to generate a click event on an already selected item.
//                    }
//
//                    int currentSelected = selectedItem;// Create a temp var to deselect an existing one, if any.
//                    selectedItem = position2;// Check item.
//
//                    if (currentSelected != -1) {
//                        notifyItemChanged(currentSelected);// Deselected the previous item.
//                    }
//
//                    notifyItemChanged(selectedItem);// Select the current item.
//                }



            }
        });
        holder.cardviewVendorDetailsItemOpenVendorDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("MainServiceName", mainServiceName);
                bundle.putSerializable("Object", (Serializable) subCatDataList);
                bundle.putSerializable("VendorObject", getAllvendors.get(position));
                navController.navigate(R.id.action_homeServicesEnterVenderDataFragment_to_vendorProfileFragment,bundle);

            }
        });
    }


    @Override
    public int getItemCount() {

        return getAllvendors.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_vendor_details_item_photo_circularImageView)
        CircleImageView cardviewVendorDetailsItemPhotoCircularImageView;
        @BindView(R.id.cardview_vendor_details_item_name_tv)
        TextView cardviewVendorDetailsItemNameTv;
//        @BindView(R.id.cardview_vendor_details_item_start_hour_tv)
//        TextView cardviewVendorDetailsItemStartHourTv;
//        @BindView(R.id.cardview_vendor_details_item_end_hour_tv)
//        TextView cardviewVendorDetailsItemEndHourTv;
        @BindView(R.id.cardview_vendor_details_item_open_vendor_details)
        ImageButton cardviewVendorDetailsItemOpenVendorDetails;
        @BindView(R.id.cardview_vendor_details_item_change_background_ly)
        FrameLayout cardviewVendorDetailsItemChangeBackgroundLy;

        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
