package com.example.kabboot.view.fragment.HomeCycle2.accountProfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllServiceDataResponce.SubCat;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllvendors;
import com.example.kabboot.data.model.getAppInfoResponce.GetAppInfoResponce;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.utils.HelperMethod.onPermission;


public class AboutUsFragment extends BaSeFragment {


    @BindView(R.id.fragment_about_us_name_title_tv)
    TextView fragmentAboutUsNameTitleTv;
    @BindView(R.id.fragment_about_us_title_tv)
    TextView fragmentAboutUsTitleTv;
    @BindView(R.id.fragment_about_us_description_tv)
    TextView fragmentAboutUsDescriptionTv;
    @BindView(R.id.fragment_about_us_address_tv)
    TextView fragmentAboutUsAddressTv;
    @BindView(R.id.fragment_about_us_phone1_tv)
    TextView fragmentAboutUsPhone1Tv;
    @BindView(R.id.fragment_about_us_phone2_tv)
    TextView fragmentAboutUsPhone2Tv;
    @BindView(R.id.fragment_about_us_email_tv)
    TextView fragmentAboutUsEmailTv;
    @BindView(R.id.fragment_about_us_wv_mv_map)
    WebView fragmentAboutUsWvMvMap;
    private NavController navController;
    private ViewModelUser viewModelUser;
    private GetAppInfoResponce getAppInfoResponce;

    public AboutUsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {

            getAppInfoResponce = (GetAppInfoResponce) this.getArguments().getSerializable("Object");
        }
        View root = inflater.inflate(R.layout.fragment_about_us, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);

        setData();

        return root;
    }



    private void setData() {
//        onPermission(getActivity());
//        getActivity().startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", getAppInfoResponce.getAppInfo().get(0).getTelepon(), null)));
        fragmentAboutUsTitleTv.setText("Welcome to Kabboot App , "+getAppInfoResponce.getAppInfo().get(0).getAbbreviationName());
        fragmentAboutUsDescriptionTv.setText(Html.fromHtml(getAppInfoResponce.getAppInfo().get(0).getAboutApp()));
        fragmentAboutUsAddressTv.setText(Html.fromHtml(getAppInfoResponce.getAppInfo().get(0).getAddress()));
        fragmentAboutUsPhone1Tv.setText(getAppInfoResponce.getAppInfo().get(0).getMobile());
        fragmentAboutUsPhone2Tv.setText(getAppInfoResponce.getAppInfo().get(0).getTelepon());
        fragmentAboutUsEmailTv.setText(getAppInfoResponce.getAppInfo().get(0).getEmail());
//        fragmentAboutUsAboutBarkaEmailTv.setText(appSetting.getEmailwebsite());
//        fragmentAboutUsAboutUsDescriptionTv.setText(Html.fromHtml(appSetting.getAboutUsDescription()));
        String iframe = getAppInfoResponce.getAppInfo().get(0).getGoogleMap();
        fragmentAboutUsWvMvMap.canZoomOut();
        fragmentAboutUsWvMvMap.setInitialScale(160);
        fragmentAboutUsWvMvMap.getSettings().setJavaScriptEnabled(true);
        fragmentAboutUsWvMvMap.loadData(iframe, "text/html", "utf-8");
        fragmentAboutUsWvMvMap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }


    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new ConfirmBookingFragment());
        navController.navigate(R.id.action_aboutUsFragment_to_navigation_profile);

    }

    @OnClick(R.id.fragment_about_us_back_img)
    public void onClick() {
        onBack();
    }
}