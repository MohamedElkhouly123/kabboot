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
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.kabboot.utils.HelperMethod.onLoadCirImageFromUrl;
import static java.util.Calendar.FRIDAY;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;
import static java.util.Calendar.THURSDAY;
import static java.util.Calendar.TUESDAY;
import static java.util.Calendar.WEDNESDAY;
import static java.util.Calendar.getInstance;


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
    private Calendar currentDate;
    private int currrendDateDay;
    private boolean dayOpened;
    private int currentMin;
    private int currentHour;
    private boolean hourOpened;
    private String anyDayOpened;

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
            availableDaysList = getAllAvailableDaysItemList(getAllvendors.get(position));
            openOrClose(getAllvendor);
            if(dayOpened&&hourOpened){
                holder.cardviewVendorDetailsItem2OpenTv.setVisibility(View.VISIBLE);
//                holder.cardviewVendorDetailsItem2IsOpenTv.setVisibility(View.VISIBLE);
                whenOpened(getAllvendor);
                holder.cardviewVendorDetailsItem2IsOpenTv.setText("");

            }else {
                holder.cardviewVendorDetailsItem2IsCloseTv.setVisibility(View.VISIBLE);
//                holder.cardviewVendorDetailsItem2IsOpenTv.setVisibility(View.VISIBLE);
                whenOpened(getAllvendor);
                holder.cardviewVendorDetailsItem2IsOpenTv.setText("Start Hour : "+getAllvendor.getStartHour()+"    End Hour : "+getAllvendor.getEndHour());

            }


        } catch (Exception e) {

        }

    }

    private void whenOpened(GetAllvendors getAllvendor) {
        for(int i=1;i<8;i++){
            currentDate.add(Calendar.DATE, 1);
            currrendDateDay = currentDate.get(Calendar.DAY_OF_WEEK);
            if (currrendDateDay == SATURDAY && getAllvendor.getSaturday().equalsIgnoreCase("1")){
                anyDayOpened="Sut";
                return;
            }
            if (currrendDateDay == SUNDAY && getAllvendor.getSunday().equalsIgnoreCase("1")){
                anyDayOpened="Sun";
                return;
            }
            if (currrendDateDay == MONDAY && getAllvendor.getMonday().equalsIgnoreCase("1")){
                anyDayOpened="Mon";
                return;
            }
            if (currrendDateDay == TUESDAY && getAllvendor.getTuesday().equalsIgnoreCase("1")){
                anyDayOpened="Tues";
                return;
            }
            if (currrendDateDay == WEDNESDAY && getAllvendor.getWednesday().equalsIgnoreCase("1")){
                anyDayOpened="Wednes";
                return;
            }
            if (currrendDateDay == THURSDAY && getAllvendor.getThursday().equalsIgnoreCase("1")){
                anyDayOpened="Thurs";
                return;
            }
            if (currrendDateDay == FRIDAY && getAllvendor.getFriday().equalsIgnoreCase("1")){
                anyDayOpened="Fri";
                return;
            }



        }
    }

    private void openOrClose(GetAllvendors getAllvendor) {
        currentDate = getInstance();
        currrendDateDay = currentDate.get(Calendar.DAY_OF_WEEK);
        if ((currrendDateDay == FRIDAY && getAllvendor.getFriday().equalsIgnoreCase("1")) ||
                (currrendDateDay == THURSDAY && getAllvendor.getThursday().equalsIgnoreCase("1")) ||
                (currrendDateDay == WEDNESDAY && getAllvendor.getWednesday().equalsIgnoreCase("1")) ||
                (currrendDateDay == TUESDAY && getAllvendor.getTuesday().equalsIgnoreCase("1")) ||
                (currrendDateDay == MONDAY && getAllvendor.getMonday().equalsIgnoreCase("1")) ||
                (currrendDateDay == SUNDAY && getAllvendor.getSunday().equalsIgnoreCase("1")) ||
                (currrendDateDay == SATURDAY && getAllvendor.getSaturday().equalsIgnoreCase("1"))) {

            dayOpened=true;

        }else {
            dayOpened=false;
        }
//        DateFormat.is24HourFormat(activity);
        currentMin=currentDate.get(MINUTE);
        currentHour =currentDate.get(HOUR_OF_DAY);
        String[] minTimeParts = getAllvendor.getStartHour().split(":");
//        String startSec = minTimeParts[2];
        String startMin = minTimeParts[1];
        String startHour = minTimeParts[0];
        String[] maxTimeParts = getAllvendor.getEndHour().split(":");
//        String endSec = maxTimeParts[2];
        String endMin = maxTimeParts[1];
        String endHour = maxTimeParts[0];
//        showToast(activity, currentHour+" : "+currentMin);
        if ((currentHour == Integer.parseInt(startHour) &&currentMin>=Integer.parseInt(startMin)) ||(currentHour > Integer.parseInt(startHour))) {
            if((currentHour == Integer.parseInt(endHour)&&currentMin<=Integer.parseInt(endMin))||(currentHour < Integer.parseInt(endHour))) {
                hourOpened=true;
            }else {
                hourOpened=false;
            }
        }else {
            hourOpened=false;
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
            allDaysItems.add("SATURDAY");
        }
        if (vendorData2.getSunday().equalsIgnoreCase("1")) {
            allDaysItems.add("SUNDAY");
        }
        if (vendorData2.getMonday().equalsIgnoreCase("1")) {
            allDaysItems.add("MONDAY");
        }
        if (vendorData2.getTuesday().equalsIgnoreCase("1")) {
            allDaysItems.add("TUESDAY");
        }
        if (vendorData2.getWednesday().equalsIgnoreCase("1")) {
            allDaysItems.add("WEDNESDAY");
        }
        if (vendorData2.getThursday().equalsIgnoreCase("1")) {
            allDaysItems.add("THURSDAY");
        }
        if (vendorData2.getFriday().equalsIgnoreCase("1")) {
            allDaysItems.add("FRIDAY");
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
