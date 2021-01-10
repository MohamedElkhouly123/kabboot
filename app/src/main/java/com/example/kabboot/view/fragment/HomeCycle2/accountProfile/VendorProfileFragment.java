package com.example.kabboot.view.fragment.HomeCycle2.accountProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.adapter.ShowVendorDaysAvailableItemsAdapter;
import com.example.kabboot.data.model.ItemGeneralObjectModel;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllvendors;
import com.example.kabboot.view.fragment.BaSeFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.kabboot.utils.HelperMethod.onLoadCirImageFromUrl2;


public class VendorProfileFragment extends BaSeFragment {

    @BindView(R.id.fragment_vendor_profile_photo_circularImageView)
    CircleImageView fragmentVendorProfilePhotoCircularImageView;
    @BindView(R.id.fragment_vendor_profile_name_tv)
    TextView fragmentVendorProfileNameTv;
    @BindView(R.id.fragment_vendor_profile_phone3_tv)
    TextView fragmentVendorProfilePhone3Tv;
    @BindView(R.id.fragment_vendor_profile_email_tv)
    TextView fragmentVendorProfileEmailTv;
    @BindView(R.id.fragment_vendor_profile_main_services_tv)
    TextView fragmentVendorProfileMainServicesTv;
    @BindView(R.id.fragment_vendor_profile_sub_services_tv)
    TextView fragmentVendorProfileSubServicesTv;
    @BindView(R.id.fragment_vendor_profile_start_hour_tv)
    TextView fragmentVendorProfileStartHourTv;
    @BindView(R.id.fragment_vendor_profile_end_hour_tv)
    TextView fragmentVendorProfileEndHourTv;
    @BindView(R.id.fragment_vendor_profile_days_avalible_rv_item_hz_rv)
    RecyclerView fragmentVendorProfileDaysAvalibleRvItemHzRv;
    @BindView(R.id.fragment_vendor_profile_description_tv)
    TextView fragmentVendorProfileDescriptionTv;
    private NavController navController;
    private String mainServiceName;
    private List<SubCat> subCatDataList = new ArrayList<SubCat>();
    private GetAllvendors vendorDataList;
    private ShowVendorDaysAvailableItemsAdapter showVendorDaysAvailableItemsAdapter;
    private List<ItemGeneralObjectModel> rowListItem;
    private LinearLayoutManager linearLayoutHorizental;

    public VendorProfileFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            mainServiceName = this.getArguments().getString("MainServiceName");
            subCatDataList = (List<SubCat>) this.getArguments().getSerializable("Object");
            vendorDataList = (GetAllvendors) this.getArguments().getSerializable("VendorObject");
        }

        View root = inflater.inflate(R.layout.fragment_vendor_profile, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("g");
        setData();
        return root;
    }

    private void setData() {
        if (vendorDataList.getImage() != null) {
            String vindorImage = "https://www.kabboot.com/uploads/vendor/" + vendorDataList.getImage().trim();
            onLoadCirImageFromUrl2(fragmentVendorProfilePhotoCircularImageView, vindorImage.trim(), getContext());
        }
        fragmentVendorProfileNameTv.setText(vendorDataList.getVendorName());
        fragmentVendorProfileEmailTv.setText(vendorDataList.getEmail());
        fragmentVendorProfilePhone3Tv.setText(vendorDataList.getMob());
        fragmentVendorProfileMainServicesTv.setText("Main Service : "+vendorDataList.getMainCategoryName());
        fragmentVendorProfileSubServicesTv.setText("Sub Service : "+vendorDataList.getSubCategoryName());
        fragmentVendorProfileStartHourTv.setText("Start Hour :  "+vendorDataList.getStartHour());
        fragmentVendorProfileEndHourTv.setText("End Hour :  "+vendorDataList.getEndHour());
        fragmentVendorProfileDescriptionTv.setText(vendorDataList.getDetails());
        initHozental();
    }

    private List<ItemGeneralObjectModel> getAllItemList() {

        List<ItemGeneralObjectModel> allItems = new ArrayList<ItemGeneralObjectModel>();
        if(vendorDataList.getSaturday().equalsIgnoreCase("1")){
            allItems.add(new ItemGeneralObjectModel("Saturday"));
        }
        if(vendorDataList.getSunday().equalsIgnoreCase("1")){
            allItems.add(new ItemGeneralObjectModel("Sunday"));
        }
        if(vendorDataList.getMonday().equalsIgnoreCase("1")){
            allItems.add(new ItemGeneralObjectModel("Monday"));
        }
        if(vendorDataList.getTuesday().equalsIgnoreCase("1")){
            allItems.add(new ItemGeneralObjectModel("Tuesday"));
        }
        if(vendorDataList.getWednesday().equalsIgnoreCase("1")){
            allItems.add(new ItemGeneralObjectModel("Wednesday"));
        }
        if(vendorDataList.getThursday().equalsIgnoreCase("1")){
            allItems.add(new ItemGeneralObjectModel("Thursday"));
        }
        if(vendorDataList.getFriday().equalsIgnoreCase("1")){
            allItems.add(new ItemGeneralObjectModel("Friday"));
        }



        return allItems;
    }

    private void initHozental() {
        linearLayoutHorizental = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        fragmentVendorProfileDaysAvalibleRvItemHzRv.setLayoutManager(linearLayoutHorizental);
        fragmentVendorProfileDaysAvalibleRvItemHzRv.setHasFixedSize(true);
            rowListItem = getAllItemList();

        showVendorDaysAvailableItemsAdapter = new ShowVendorDaysAvailableItemsAdapter(getContext(), getActivity(),navController,rowListItem);
        fragmentVendorProfileDaysAvalibleRvItemHzRv.setAdapter(showVendorDaysAvailableItemsAdapter);



    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        Bundle bundle = new Bundle();
        bundle.putString("MainServiceName", mainServiceName);
        bundle.putSerializable("Object", (Serializable) subCatDataList);
        navController.navigate(R.id.action_vendorProfileFragment_to_homeServicesEnterVenderDataFragment, bundle);

    }


    @OnClick(R.id.fragment_policy_and_conditions_back_img)
    public void onClick() {
        onBack();
    }
}