package com.example.kabboot.view.fragment.HomeCycle2.accountProfile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAppInfoResponce.GetAppInfoResponce;
import com.example.kabboot.data.model.getUserDataResponce.UserData;
import com.example.kabboot.view.activity.SplashCycleActivity;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelUser;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.kabboot.data.local.SharedPreferencesManger.clean;


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
    private NavController navController;
    private UserData userData;
    private ViewModelUser viewModelUser;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setData();
        return root;
    }

    private void setData() {

            userData = LoadUserData(getActivity());
            fragmentMyProfileNameTv.setText(userData.getUserName());
            fragmentProfileEmailTv.setText(userData.getUserEmail());
            fragmentProfilePhoneTv.setText(userData.getUserPhone());
            fragmentProfileCityTv.setText(userData.getUserCity());
//            if(userData.getImage()!=null){
//                String personalImage = "https://www.barakatravel.net/"+userData.getImage().trim();
//                onLoadCirImageFromUrl2(fragmentHomeAccountProfilePhotoCircularImageView,personalImage.trim(), getContext());
//            }

    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        navController.navigate(R.id.action_navigation_profile_to_navigation_services);

    }


    @OnClick({R.id.fragment_profile_more_img_btn, R.id.fragment_profile_logout_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_profile_more_img_btn:
                initListener();
                onCall();
                break;
            case R.id.fragment_profile_logout_btn:
                showLogOutDialog();
                break;
        }
    }

    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.makeGetAppInfo().observe(getViewLifecycleOwner(), new Observer<GetAppInfoResponce>() {
            @Override
            public void onChanged(@Nullable GetAppInfoResponce response) {
                if (response != null) {
//                    if (response.getSuccess()==1) {
//                        showToast(getActivity(),"success");
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Object",  response);
                    navController.navigate(R.id.action_navigation_profile_to_aboutUsFragment,bundle);

//                    }
                }
            }
        });
    }

    private void onCall() {

        boolean remember = true;

//        Call<ClientGeneralResponse> clientCall = getApiClient().clientLogin(email, password);


        Call<GetAppInfoResponce> aboutAppCall = null;

        aboutAppCall = getApiClient().getAppInfoData();

        viewModelUser.getAppInfo(getActivity(), aboutAppCall);


    }


    private void showLogOutDialog(){
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
                    alertDialog.dismiss() ;
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
}