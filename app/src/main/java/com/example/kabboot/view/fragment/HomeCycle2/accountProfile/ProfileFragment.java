package com.example.kabboot.view.fragment.HomeCycle2.accountProfile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabboot.R;
import com.example.kabboot.adapter.AccountMyBookingsVrRvAdapter;
import com.example.kabboot.data.model.ItemGeneralObjectModel;
import com.example.kabboot.data.model.getUserDataResponce.UserData;
import com.example.kabboot.view.activity.SplashCycleActivity;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.kabboot.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.kabboot.data.local.SharedPreferencesManger.clean;
import static com.example.kabboot.utils.HelperMethod.onLoadCirImageFromUrl2;
import static com.example.kabboot.utils.ToastCreator.onCreateErrorToast;
import static com.example.kabboot.utils.network.InternetState.isConnected;


public class ProfileFragment extends BaSeFragment {

    @BindView(R.id.fragment_home_account_profile_photo_circularImageView)
    CircleImageView fragmentHomeAccountProfilePhotoCircularImageView;
    @BindView(R.id.fragment_my_profile_name_tv)
    TextView fragmentMyProfileNameTv;
    @BindView(R.id.fragment_profile_phone_tv)
    TextView fragmentProfilePhoneTv;
    @BindView(R.id.fragment_profile_email_tv)
    TextView fragmentProfileEmailTv;
    @BindView(R.id.fragment_profile_city_tv)
    TextView fragmentProfileCityTv;
    @BindView(R.id.card_view_tool)
    CardView cardViewTool;
    @BindView(R.id.fragment_home_account_profile_recycler_view)
    RecyclerView fragmentHomeAccountProfileRecyclerView;
    private NavController navController;
    private UserData userData;
    private ViewModelUser viewModelUser;
    private LinearLayoutManager lLayout;
    private List<ItemGeneralObjectModel> rowListItem;
    private AccountMyBookingsVrRvAdapter accountMyBookingsVrRvAdapter;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("v");
        setData();
        init();
        if (!isConnected(getActivity())) {
            onCreateErrorToast(getActivity(), getString(R.string.error_inter_net));
        }
        return root;
    }

    private void setData() {

        userData = LoadUserData(getActivity());
        fragmentMyProfileNameTv.setText(userData.getUserName());
        fragmentProfileEmailTv.setText(userData.getUserEmail());
        fragmentProfilePhoneTv.setText(userData.getUserPhone());
        fragmentProfileCityTv.setText(userData.getUserCity());
            if(userData.getM_image()!=null){
                String personalImage = "https://www.kabboot.com/uploads/user/"+userData.getM_image().trim();
                onLoadCirImageFromUrl2(fragmentHomeAccountProfilePhotoCircularImageView,personalImage.trim(), getContext());
            }

    }

    private void init() {

        lLayout = new LinearLayoutManager(getActivity());

        fragmentHomeAccountProfileRecyclerView.setLayoutManager(lLayout);
//        if(getHomeDisscoverGetUmrahDataItemsListData.size()!=0 || getHomeDisscoverGetHajjDataItemsListData.size()!=0 || getHomeDisscoverGetHotelsDataItemsListData.size()!=0) {
        rowListItem = getAllItemList();
//        }
        accountMyBookingsVrRvAdapter = new AccountMyBookingsVrRvAdapter(getContext(), getActivity(), navController, rowListItem);
        fragmentHomeAccountProfileRecyclerView.setAdapter(accountMyBookingsVrRvAdapter);


    }

    private List<ItemGeneralObjectModel> getAllItemList() {

        List<ItemGeneralObjectModel> allItems = new ArrayList<ItemGeneralObjectModel>();
        allItems.add(new ItemGeneralObjectModel(getString(R.string.My_service_Bookings),getString(R.string.My_service_Bookings),R.drawable.ic_baseline_servise_book_24));
        allItems.add(new ItemGeneralObjectModel(getString(R.string.My_Products_Bookings),getString(R.string.My_Products_Bookings),R.drawable.ic_baseline_store_mall_directory_24));

        return allItems;
    }
    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        navController.navigate(R.id.action_navigation_profile_to_navigation_services);

    }





    private void showLogOutDialog() {
        try {
//                final View view = activity.getLayoutInflater().inflate(R.layout.dialog_restaurant_add_category, null);
//            alertDialog = new AlertDialog.Builder(HomeFragment.this).create();
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(getActivity()).create();
//            alertDialog.setTitle("Delete");
            alertDialog.setMessage("Are you sure you want log out ?");
            alertDialog.setCancelable(true);

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Log Out", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    clean(getActivity());
                    Intent i = new Intent(getActivity(), SplashCycleActivity.class);

                    getActivity().startActivity(i);
                    // close this activity
                    getActivity().finish();

                }
            });


            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });

//                alertDialog.setView(view);
//            alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
//                @SuppressLint("ResourceAsColor")
//                @Override
//                public void onShow(DialogInterface arg0) {
//                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.pink);
//                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.pink);
//
//                }
//            });

            alertDialog.show();

        } catch (Exception e) {

        }
    }

    @OnClick({R.id.fragment_profile_more_img_btn, R.id.fragment_home_account_profile_photo_circularImageView, R.id.fragment_home_account_profile_photo_circularImageView_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_profile_more_img_btn:
                navController.navigate(R.id.action_navigation_profile_to_changeDetailsMoreFragment);
                break;
            case R.id.fragment_home_account_profile_photo_circularImageView:
                navController.navigate(R.id.action_navigation_profile_to_changeDetailsFragment);
                break;
            case R.id.fragment_home_account_profile_photo_circularImageView_btn:
                navController.navigate(R.id.action_navigation_profile_to_changeDetailsFragment);
                break;
//            case R.id.fragment_profile_logout_btn:
//                showLogOutDialog();
//                break;
        }
    }
}