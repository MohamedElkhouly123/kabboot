package com.example.kabboot.view.fragment.HomeCycle2.HomeServices;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.adapter.GetAllServicesAdapter;
import com.example.kabboot.adapter.HomeServicesVendorsVrRvAdapter;
import com.example.kabboot.data.model.DateTxt;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllvendorsResponce.AllVendorService;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllvendors;
import com.example.kabboot.data.model.saveServiceOrdersRequest.OrderServiceList;
import com.example.kabboot.utils.HelperMethod;
import com.example.kabboot.utils.ToastCreator;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.kabboot.utils.validation.Validation.cleanError;
import static com.example.kabboot.utils.validation.Validation.validationAllEmpty;
import static com.example.kabboot.utils.validation.Validation.validationLength;


public class HomeServicesEnterVenderData2Fragment extends BaSeFragment implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {


    @BindView(R.id.fragment_home_services_enter_vendor_data2_book_cat_name_tv)
    TextView fragmentHomeServicesEnterVendorData2BookCatNameTv;
    @BindView(R.id.fragment_home_complet_booking_services_vendor_name_tv)
    TextView fragmentHomeCompletBookingServicesVendorNameTv;
    @BindView(R.id.fragment_home_services_enter_vendor_data2_recycler_view)
    RecyclerView fragmentHomeServicesEnterVendorData2RecyclerView;
    //    @BindView(R.id.fragment_home_services_enter_vendor_data2_sub_cat_name_tv)
//    TextView fragmentHomeServicesEnterVendorData2SubCatNameTv;
//    @BindView(R.id.fragment_home_services_enter_vendor_data2_sub_cat_price_tv)
//    TextView fragmentHomeServicesEnterVendorData2SubCatPriceTv;
//    @BindView(R.id.fragment_home_services_enter_vendor_data2_sub_serv_total_price_tv)
//    TextView fragmentHomeServicesEnterVendorData2SubServTotalPriceTv;
    @BindView(R.id.fragment_home_services_enter_vendor_data2_til_address)
    TextInputLayout fragmentHomeServicesEnterVendorData2TilAddress;
    @BindView(R.id.fragment_home_services_enter_vendor_data2_til_date)
    TextInputLayout fragmentHomeServicesEnterVendorData2TilDate;
    @BindView(R.id.fragment_home_services_enter_vendor_data2_til_time)
    TextInputLayout fragmentHomeServicesEnterVendorData2TilTime;
    @BindView(R.id.fragment_home_services_enter_vendor_data2_sub_cat_name_tv)
    TextView fragmentHomeServicesEnterVendorData2SubCatNameTv;
    private NavController navController;
    private List<AllVendorService> allVendorServiceList = new ArrayList<AllVendorService>();
    private HomeServicesVendorsVrRvAdapter homeServicesVendorsVrRvAdapter;
    private List<AllVendorService> allVendorServiceListSelected = new ArrayList<>();
    private List<OrderServiceList> ids = new ArrayList<>();
    private LinearLayoutManager lLayout;
    private String mainServiceName;
    private List<SubCat> subCatDataList = new ArrayList<SubCat>();
    ;
    private DateTxt checkinDate;
    private GetAllvendors vendorData;
    private GetAllServicesAdapter getAllServicesAdapter;
    private String startHour, endHour;
    private List<String> availableDaysList = new ArrayList<>();
//    private int myVendorId;
//    private GetAllvendors vendorData;

    public HomeServicesEnterVenderData2Fragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            mainServiceName = this.getArguments().getString("MainServiceName");
            subCatDataList = (List<SubCat>) this.getArguments().getSerializable("Object");
            vendorData = (GetAllvendors) this.getArguments().getSerializable("VendorDataObject");

        }
        View root = inflater.inflate(R.layout.fragment_home_services_enter_vendor_data2, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("g");
        availableDaysList = getAllAvailableDaysItemList();
//        showToast(getActivity(), getAllAvailableDaysItemList().get(0));
        setData();
        init();
        return root;
    }

    private void setData() {
        fragmentHomeServicesEnterVendorData2BookCatNameTv.setText("Book " + mainServiceName + " Service");
        fragmentHomeServicesEnterVendorData2SubCatNameTv.setText(subCatDataList.get(0).getCategoryName() + " Services Booked");
        fragmentHomeCompletBookingServicesVendorNameTv.setText(vendorData.getVendorName());
        startHour = vendorData.getStartHour();
        endHour = vendorData.getEndHour();
        allVendorServiceList = vendorData.getAllVendorServices();
    }

    private List<String> getAllAvailableDaysItemList() {

        List<String> allDaysItems = new ArrayList<String>();
        if (vendorData.getSaturday().equalsIgnoreCase("1")) {
            allDaysItems.add("Saturday");
        }
        if (vendorData.getSunday().equalsIgnoreCase("1")) {
            allDaysItems.add("Sunday");
        }
        if (vendorData.getMonday().equalsIgnoreCase("1")) {
            allDaysItems.add("Monday");
        }
        if (vendorData.getTuesday().equalsIgnoreCase("1")) {
            allDaysItems.add("Tuesday");
        }
        if (vendorData.getWednesday().equalsIgnoreCase("1")) {
            allDaysItems.add("Wednesday");
        }
        if (vendorData.getThursday().equalsIgnoreCase("1")) {
            allDaysItems.add("Thursday");
        }
        if (vendorData.getFriday().equalsIgnoreCase("1")) {
            allDaysItems.add("Friday");
        }
        return allDaysItems;
    }

    private void init() {
        if (allVendorServiceList.size() != 0) {
            lLayout = new LinearLayoutManager(getActivity());
            fragmentHomeServicesEnterVendorData2RecyclerView.setLayoutManager(lLayout);
//            showToast(getActivity(), allVendorServiceList.get(0).getServiceName());
            getAllServicesAdapter = new GetAllServicesAdapter(getContext(), getActivity(), navController, allVendorServiceList);
            fragmentHomeServicesEnterVendorData2RecyclerView.setAdapter(getAllServicesAdapter);

//            noResultErrorTitle.setVisibility(View.GONE);

        } else {

        }
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        Bundle bundle = new Bundle();
        bundle.putString("MainServiceName", mainServiceName);
        bundle.putSerializable("Object", (Serializable) subCatDataList);
        navController.navigate(R.id.action_homeServicesEnterVenderData2Fragment_to_homeServicesEnterVenderDataFragment, bundle);

    }


    @OnClick({R.id.fragment_home_services_enter_vendor_data2_back_img, R.id.fragment_home_services_enter_vendor_data2_next_btn, R.id.fragment_home_services_enter_vendor_data2_date_etxt, R.id.fragment_home_services_enter_vendor_data2_time_etxt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_home_services_enter_vendor_data2_back_img:
                onBack();
                break;
            case R.id.fragment_home_services_enter_vendor_data2_date_etxt:
                //                checkinDate = new DateTxt("01", "01", "2021", "01-01-2021");
//                showCalender(getActivity(), getActivity().getString(R.string.select_date), fragmentHomeServicesEnterVendorDataTilDate.getEditText(), checkinDate);
//                DialogFragment datePickerFragment = new HelperMethod.DatePickerFragment(fragmentHomeServicesEnterVendorData2TilDate.getEditText());
//                datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

               showDate();
                break;
            case R.id.fragment_home_services_enter_vendor_data2_time_etxt:
                DialogFragment timePickerFragment = new HelperMethod.TimePickerFragment(fragmentHomeServicesEnterVendorData2TilTime.getEditText());
                timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");

                break;
            case R.id.fragment_home_services_enter_vendor_data2_next_btn:
                onVaildate();
                break;
        }
    }

    private void showDate() {
        Calendar now = Calendar.getInstance();

        DatePickerDialog dpd = DatePickerDialog.newInstance(this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection

        );
        dpd.setMinDate(now);
// If you're calling this from a support Fragment
        dpd.show(getFragmentManager(), "Datepickerdialog");
// If you're calling this from an AppCompatActivity
// dpd.show(getSupportFragmentManager(), "Datepickerdialog");
    }


    private void showTime() {

            Calendar now = Calendar.getInstance();
            final Calendar c = Calendar.getInstance();

            TimePickerDialog mTimePickerDialog = TimePickerDialog.newInstance(this,
                    now.get(Calendar.HOUR_OF_DAY), // Initial year selection
                    now.get(Calendar.MINUTE), // Initial month selection
                    DateFormat.is24HourFormat(getActivity()));
            mTimePickerDialog.setMinTime(11,5,0);
        mTimePickerDialog.setMaxTime(11,5,0);

// If you're calling this from a support Fragment
//            mTimePickerDialog.enableSeconds(true);
            mTimePickerDialog.show(getFragmentManager(), "TimePickerDialog");// If you're calling this from an AppCompatActivity
// dpd.show(getSupportFragmentManager(), "Datepickerdialog");
        }
    private void onVaildate() {

        allVendorServiceListSelected=getAllServicesAdapter.allVendorServiceListSelected;
        ids=getAllServicesAdapter.ids;

        List<EditText> editTexts = new ArrayList<>();
        List<TextInputLayout> textInputLayouts = new ArrayList<>();
        List<Spinner> spinners = new ArrayList<>();

        textInputLayouts.add(fragmentHomeServicesEnterVendorData2TilDate);
        textInputLayouts.add(fragmentHomeServicesEnterVendorData2TilTime);
        textInputLayouts.add(fragmentHomeServicesEnterVendorData2TilAddress);
        cleanError(textInputLayouts);
        if(ids.size()==0&&allVendorServiceListSelected.size()==0){
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_services_required_field));
            return;
        }

        if (!validationAllEmpty(editTexts, textInputLayouts, spinners, getString(R.string.empty))) {

            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
            return;
        }


        if (!validationLength(fragmentHomeServicesEnterVendorData2TilDate, getString(R.string.invalid_date_required_field), 1)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_date_required_field));
            return;
        }
        if (!validationLength(fragmentHomeServicesEnterVendorData2TilTime, getString(R.string.invalid_time_required_field), 1)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_time_required_field));
            return;
        }
        if (!validationLength(fragmentHomeServicesEnterVendorData2TilAddress, getString(R.string.invalid_address_required_field), 1)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_address_required_field));
            return;
        }
//        myVendorId = homeServicesVendorsVrRvAdapter.vindorId;
//        if (myVendorId != -1) {
//            vendorData = getAllvendorsList.get(myVendorId);
        String date = fragmentHomeServicesEnterVendorData2TilDate.getEditText().getText().toString();
        String time = fragmentHomeServicesEnterVendorData2TilTime.getEditText().getText().toString();
        String address = fragmentHomeServicesEnterVendorData2TilAddress.getEditText().getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("MainServiceName", mainServiceName);
        bundle.putString("Date", date);
        bundle.putString("Time", time);
        bundle.putString("Address", address);
        bundle.putSerializable("VendorDataObject", vendorData);
        bundle.putSerializable("ServicesSelectedIds", (Serializable) ids);
        bundle.putSerializable("ServicesSelected", (Serializable) allVendorServiceListSelected);
        bundle.putSerializable("Object", (Serializable) subCatDataList);
        navController.navigate(R.id.action_homeServicesEnterVenderData2Fragment_to_completeBookingServicesFragment, bundle);
//        } else {
//            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_vendor_required_field));
//            return;
//        }

    }




    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
//        dateTextView.setText(date);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

    }
}