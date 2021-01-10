package com.example.kabboot.view.fragment.HomeCycle2.HomeServices;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.kabboot.R;
import com.example.kabboot.adapter.HomeServicesVendorsVrRvAdapter;
import com.example.kabboot.data.model.DateTxt;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllVendorsDataResponce;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllvendors;
import com.example.kabboot.utils.HelperMethod;
import com.example.kabboot.utils.OnEndLess;
import com.example.kabboot.utils.ToastCreator;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelGetLists;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.utils.HelperMethod.showCalender;
import static com.example.kabboot.utils.validation.Validation.cleanError;
import static com.example.kabboot.utils.validation.Validation.validationAllEmpty;
import static com.example.kabboot.utils.validation.Validation.validationLength;


public class HomeServicesEnterVenderDataFragment extends BaSeFragment {

    @BindView(R.id.fragment_home_services_enter_vendor_data_til_date)
    TextInputLayout fragmentHomeServicesEnterVendorDataTilDate;
    @BindView(R.id.fragment_home_services_enter_vendor_data_til_time)
    TextInputLayout fragmentHomeServicesEnterVendorDataTilTime;
    @BindView(R.id.fragment_home_services_enter_vendor_data_til_address)
    TextInputLayout fragmentHomeServicesEnterVendorDataTilAddress;
    @BindView(R.id.fragment_home_services_enter_vendor_data_recycler_view)
    RecyclerView fragmentHomeServicesEnterVendorDataRecyclerView;
    @BindView(R.id.load_more)
    RelativeLayout loadMore;
    @BindView(R.id.error_title)
    TextView errorTitle;
    @BindView(R.id.error_sub_view)
    LinearLayout errorSubView;
    @BindView(R.id.no_result_error_title)
    TextView noResultErrorTitle;
    @BindView(R.id.fragment_home_services_enter_vendor_data_sr_refresh)
    SwipeRefreshLayout fragmentHomeServicesEnterVendorDataSrRefresh;
    private NavController navController;
    private List<GetAllvendors> getAllvendorsList = new ArrayList<GetAllvendors>();
    private ViewModelGetLists viewModel;
    private HomeServicesVendorsVrRvAdapter homeServicesVendorsVrRvAdapter;
    private LinearLayoutManager lLayout;
    private LinearLayoutManager linearLayout;
    public Integer maxPage = 0;
    private OnEndLess onEndLess;
    private String mainServiceName;
    private List<SubCat> subCatDataList = new ArrayList<SubCat>();;
    private DateTxt checkinDate;
    private int myVendorId;
    private GetAllvendors vendorData;

    public HomeServicesEnterVenderDataFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            mainServiceName = this.getArguments().getString("MainServiceName");
            subCatDataList = (List<SubCat>) this.getArguments().getSerializable("Object");

        }
        View root = inflater.inflate(R.layout.fragment_home_services_enter_vendor_data, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("g");
        initListener();
        init();
        return root;
    }

    private void initListener() {

        viewModel = ViewModelProviders.of(this).get(ViewModelGetLists.class);

        viewModel.makeGetAllvendorsResponce().observe(getViewLifecycleOwner(), new Observer<GetAllVendorsDataResponce>() {
            @Override
            public void onChanged(@Nullable GetAllVendorsDataResponce response) {
                try {
                    if (response != null) {
//                        if (response.getStatus().equals("success")) {
//                            maxPage = response.getData().getLastPage();
//                                showToast(getActivity(), "max="+maxPage);

                        if (response.getMainVendors() != null) {


                            getAllvendorsList.clear();
                            getAllvendorsList.addAll(response.getMainVendors());
//                                showToast(getActivity(), "list="+response.getGetTopHotels().get(1));

                            homeServicesVendorsVrRvAdapter.notifyDataSetChanged();

                        } else {
                            noResultErrorTitle.setVisibility(View.VISIBLE);
                        }
//                                showToast(getActivity(), "success1");

//                        }
                    } else {

                    }

                } catch (Exception e) {
                }
            }
        });

    }


    private void init() {
        linearLayout = new LinearLayoutManager(getActivity());
        fragmentHomeServicesEnterVendorDataRecyclerView.setLayoutManager(linearLayout);

        onEndLess = new OnEndLess(linearLayout, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        loadMore.setVisibility(View.VISIBLE);

                        getAllvendorsList(current_page);


                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentHomeServicesEnterVendorDataRecyclerView.addOnScrollListener(onEndLess);

        homeServicesVendorsVrRvAdapter = new HomeServicesVendorsVrRvAdapter(getContext(), getActivity(), subCatDataList, mainServiceName, getAllvendorsList,navController);
        fragmentHomeServicesEnterVendorDataRecyclerView.setAdapter(homeServicesVendorsVrRvAdapter);
//            showToast(getActivity(), "success adapter");




        if (getAllvendorsList.size() == 0) {
            getAllvendorsList(0);
        }
//        else {
//            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//        }

        fragmentHomeServicesEnterVendorDataSrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                maxPage=0;

                getAllvendorsList(0);


            }
        });
    }

    private void getAllvendorsList(int page) {
        if(page == 0){
            maxPage=0;}
        Call<GetAllVendorsDataResponce> getAllvendorsCall;

//        startShimmer(page);

        reInit();
        getAllvendorsCall = getApiClient().clientGetAgetAllvendors();

//            clientRestaurantsCall = getApiClient().getRestaurantsWithoutFiltter(page);
        viewModel.getAllvendorsResponce(getActivity(), errorSubView, getAllvendorsCall,fragmentHomeServicesEnterVendorDataSrRefresh, loadMore);
//            showToast(getActivity(), "success without fillter");



    }




    private void reInit() {
        onEndLess.previousTotal = 0;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        getAllvendorsList = new ArrayList<>();
        homeServicesVendorsVrRvAdapter = new HomeServicesVendorsVrRvAdapter(getContext(), getActivity(),subCatDataList,mainServiceName, getAllvendorsList,navController);
        fragmentHomeServicesEnterVendorDataRecyclerView.setAdapter(homeServicesVendorsVrRvAdapter);

    }


    public void setError(String errorTitleTxt) {
        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    getAllvendorsList(0);


            }
        };
        errorSubView.setVisibility(View.VISIBLE);
        errorTitle.setText(errorTitleTxt);

    }


    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        Bundle bundle = new Bundle();
        bundle.putString("MainServiceName", mainServiceName);
        bundle.putSerializable("Object", (Serializable) subCatDataList);
        navController.navigate(R.id.action_homeServicesEnterVenderDataFragment_to_homeOnSiteServicesFragment,bundle);
    }


    @OnClick({R.id.fragment_home_services_enter_vendor_data_date_etxt, R.id.fragment_home_services_enter_vendor_data_time_etxt, R.id.fragment_home_services_enter_vendor_data_next_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_home_services_enter_vendor_data_date_etxt:
//                checkinDate = new DateTxt("01", "01", "2021", "01-01-2021");
//                showCalender(getActivity(), getActivity().getString(R.string.select_date), fragmentHomeServicesEnterVendorDataTilDate.getEditText(), checkinDate);
                DialogFragment datePickerFragment = new HelperMethod.DatePickerFragment(fragmentHomeServicesEnterVendorDataTilDate.getEditText());
                datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
                break;
            case R.id.fragment_home_services_enter_vendor_data_time_etxt:
                DialogFragment timePickerFragment = new HelperMethod.TimePickerFragment(fragmentHomeServicesEnterVendorDataTilTime.getEditText());
                timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");

                break;
            case R.id.fragment_home_services_enter_vendor_data_next_btn:
                onVaildate();
                break;
        }
    }

    private void onVaildate() {
        List<EditText> editTexts = new ArrayList<>();
        List<TextInputLayout> textInputLayouts = new ArrayList<>();
        List<Spinner> spinners = new ArrayList<>();

        textInputLayouts.add(fragmentHomeServicesEnterVendorDataTilDate);
        textInputLayouts.add(fragmentHomeServicesEnterVendorDataTilTime);
        textInputLayouts.add(fragmentHomeServicesEnterVendorDataTilAddress);
        cleanError(textInputLayouts);

        if (!validationAllEmpty(editTexts, textInputLayouts, spinners, getString(R.string.empty))) {

            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
            return;
        }



        if (!validationLength(fragmentHomeServicesEnterVendorDataTilDate, getString(R.string.invalid_date_required_field), 1)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_date_required_field));
            return;
        }
        if (!validationLength(fragmentHomeServicesEnterVendorDataTilTime, getString(R.string.invalid_time_required_field), 1)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_time_required_field));
            return;
        }
        if (!validationLength(fragmentHomeServicesEnterVendorDataTilAddress, getString(R.string.invalid_address_required_field), 1)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_address_required_field));
            return;
        }
        myVendorId= homeServicesVendorsVrRvAdapter.vindorId;
        if(myVendorId != -1){
            vendorData= getAllvendorsList.get(myVendorId);
            String date= fragmentHomeServicesEnterVendorDataTilDate.getEditText().getText().toString();
            String time= fragmentHomeServicesEnterVendorDataTilTime.getEditText().getText().toString();
            String address= fragmentHomeServicesEnterVendorDataTilAddress.getEditText().getText().toString();

            Bundle bundle = new Bundle();
            bundle.putString("MainServiceName", mainServiceName);
            bundle.putString("Date", date);
            bundle.putString("Time", time);
            bundle.putString("Address", address);
            bundle.putSerializable("VendorDataObject", vendorData);
            bundle.putSerializable("Object", (Serializable) subCatDataList);
            navController.navigate(R.id.action_homeServicesEnterVenderDataFragment_to_completeBookingServicesFragment,bundle);
        }else {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_vendor_required_field));
            return;
        }

    }

}