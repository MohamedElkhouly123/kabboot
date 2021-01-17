package com.example.kabboot.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllvendors;
import com.example.kabboot.utils.ToastCreator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.kabboot.utils.HelperMethod.onLoadCirImageFromUrl;


public class HomeServicesVendors2VrRvAdapter extends RecyclerView.Adapter<HomeServicesVendors2VrRvAdapter.ViewHolder> {
    private final NavController navController;
    private final String mainServiceName;
    private final List<SubCat> subCatDataList;
    private final String subServiceName;



//    private final DialogAdapterCallback dialogAdapterCallback;

    private Context context;
    private Activity activity;
    private LinearLayoutManager lLayout;
    private int itemNum;
    private List<GetAllvendors> getAllvendors = new ArrayList<>();
    int selectedItem = -1;
    private String payload;
    public int vindorId = -1;
    private List<String> availableDaysList;

    public HomeServicesVendors2VrRvAdapter(Context context, Activity activity,
                                           String subServiceName, List<SubCat> subCatDataList, String mainServiceName, List<GetAllvendors> getAllvendors,
                                           NavController navController) {
        this.context = context;
        this.activity = activity;
//        this.clientRestaurantsDataList.clear();
        this.getAllvendors = getAllvendors;
        this.subCatDataList = subCatDataList;
        this.subServiceName = subServiceName;
        this.mainServiceName = mainServiceName;
        this.navController = navController;
//        this.dialogAdapterCallback = dialogAdapterCallback;
//        showToast(activity, "list=" + getHomeDisscoverGetHotelsDataItemsListData.get(0).getCity());

//        showToast(activity, String.valueOf(itemNum));
//        clientData = LoadUserData(activity);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_vendor_details_item2,
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

            GetAllvendors getAllvendor = this.getAllvendors.get(position);
//            showToast(activity, String.valueOf(position));

            if (getAllvendor.getImage() != null) {
                String vindorImage = "https://www.kabboot.com/uploads/vendor/" + getAllvendor.getImage().trim();
                onLoadCirImageFromUrl(holder.cardviewVendorDetailsItem2PhotoCircularImageView, vindorImage.trim(), context);
            }
            holder.cardviewVendorDetailsItem2VendorNameTv.setText(getAllvendor.getVendorName());
            holder.cardviewVendorDetailsItem2SubCat2NameTv.setText(subServiceName + " Services");


//            holder.cardviewVendorDetailsItemStartHourTv.setText("Start Hour : "+getAllvendors.getStartHour());
//            holder.cardviewVendorDetailsItemEndHourTv.setText("End Hour : "+getAllvendors.getEndHour());

        } catch (Exception e) {

        }

    }


    private void setAction(final ViewHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                myVendorId = homeServicesVendorsVrRvAdapter.vindorId;
//                if (myVendorId != -1) {
//                    vendorData = getAllvendorsList.get(myVendorId);
                availableDaysList = getAllAvailableDaysItemList(getAllvendors.get(position));
                if (getAllvendors.get(position).getAllVendorServices().size() != 0) {

                    if (availableDaysList.size() != 0) {
//            String date= fragmentHomeServicesEnterVendorDataTilDate.getEditText().getText().toString();
//            String time= fragmentHomeServicesEnterVendorDataTilTime.getEditText().getText().toString();
//            String address= fragmentHomeServicesEnterVendorDataTilAddress.getEditText().getText().toString();

                        Bundle bundle = new Bundle();
                        bundle.putString("MainServiceName", mainServiceName);
//            bundle.putString("Date", date);
//            bundle.putString("Time", time);
//            bundle.putString("Address", address);
                        bundle.putString("SubServiceName", subServiceName);
                        bundle.putSerializable("VendorDataObject", getAllvendors.get(position));
                        bundle.putSerializable("Object", (Serializable) subCatDataList);
                        navController.navigate(R.id.action_homeServicesEnterVenderDataFragment_to_homeServicesEnterVenderData2Fragment, bundle);
                    } else {
                        ToastCreator.onCreateErrorToast(activity, activity.getString(R.string.invalid_vendor_no_days));
                        return;
                    }
                } else {
                    ToastCreator.onCreateErrorToast(activity, activity.getString(R.string.invalid_vendor_no_services));
                    return;
                }
//                }
//                else {
//                    ToastCreator.onCreateErrorToast(activity, activity.getString(R.string.invalid_vendor_required_field));
//                    return;
//                }
            }
        });
//        holder.cardviewVendorDetailsItemOpenVendorDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("MainServiceName", mainServiceName);
//                bundle.putSerializable("Object", (Serializable) subCatDataList);
//                bundle.putSerializable("VendorObject", getAllvendors.get(position));
//                navController.navigate(R.id.action_homeServicesEnterVenderDataFragment_to_vendorProfileFragment, bundle);
//
//            }
//        });
    }

    private List<String> getAllAvailableDaysItemList(GetAllvendors vendorData2) {

        List<String> allDaysItems = new ArrayList<String>();
        if (vendorData2.getSaturday().equalsIgnoreCase("1")) {
            allDaysItems.add("Saturday");
        }
        if (vendorData2.getSunday().equalsIgnoreCase("1")) {
            allDaysItems.add("Sunday");
        }
        if (vendorData2.getMonday().equalsIgnoreCase("1")) {
            allDaysItems.add("Monday");
        }
        if (vendorData2.getTuesday().equalsIgnoreCase("1")) {
            allDaysItems.add("Tuesday");
        }
        if (vendorData2.getWednesday().equalsIgnoreCase("1")) {
            allDaysItems.add("Wednesday");
        }
        if (vendorData2.getThursday().equalsIgnoreCase("1")) {
            allDaysItems.add("Thursday");
        }
        if (vendorData2.getFriday().equalsIgnoreCase("1")) {
            allDaysItems.add("Friday");
        }
        return allDaysItems;
    }


    @Override
    public int getItemCount() {

        return getAllvendors.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview_vendor_details_item2_vendor_name_tv)
        TextView cardviewVendorDetailsItem2VendorNameTv;
        @BindView(R.id.cardview_vendor_details_item2_sub_cat2_name_tv)
        TextView cardviewVendorDetailsItem2SubCat2NameTv;
        @BindView(R.id.cardview_vendor_details_item2_is_close_tv)
        TextView cardviewVendorDetailsItem2IsCloseTv;
        @BindView(R.id.cardview_vendor_details_item2_open_tv)
        TextView cardviewVendorDetailsItem2OpenTv;
        @BindView(R.id.cardview_vendor_details_item2_is_open_tv)
        TextView cardviewVendorDetailsItem2IsOpenTv;
        @BindView(R.id.cardview_vendor_details_item2_photo_circularImageView)
        CircleImageView cardviewVendorDetailsItem2PhotoCircularImageView;
        private View view;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
