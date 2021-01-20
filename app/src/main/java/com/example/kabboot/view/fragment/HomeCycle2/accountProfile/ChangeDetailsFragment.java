package com.example.kabboot.view.fragment.HomeCycle2.accountProfile;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getUserDataResponce.GetUserDataResponce;
import com.example.kabboot.data.model.getUserDataResponce.UserData;
import com.example.kabboot.utils.ToastCreator;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelUser;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.okhttp.RequestBody;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadData;
import static com.example.kabboot.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.kabboot.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.kabboot.utils.HelperMethod.convertFileToMultipart;
import static com.example.kabboot.utils.HelperMethod.convertToRequestBody;
import static com.example.kabboot.utils.HelperMethod.disappearKeypad;
import static com.example.kabboot.utils.HelperMethod.onLoadCirImageFromUrl2;
import static com.example.kabboot.utils.HelperMethod.openGalleryِAlpom;
import static com.example.kabboot.utils.validation.Validation.cleanError;
import static com.example.kabboot.utils.validation.Validation.validationAllEmpty;
import static com.example.kabboot.utils.validation.Validation.validationConfirmPassword;
import static com.example.kabboot.utils.validation.Validation.validationEmail;
import static com.example.kabboot.utils.validation.Validation.validationLength;
import static com.example.kabboot.utils.validation.Validation.validationPassword;
import static com.example.kabboot.utils.validation.Validation.validationPhone;


public class ChangeDetailsFragment extends BaSeFragment {


    @BindView(R.id.fragment_change_details_my_profile_photo_circularImageView)
    CircleImageView fragmentChangeDetailsMyProfilePhotoCircularImageView;
    @BindView(R.id.fragment_change_details_my_profile_til_user_name)
    TextInputLayout fragmentChangeDetailsMyProfileTilUserName;
    @BindView(R.id.fragment_change_details_my_profile_til_phone)
    TextInputLayout fragmentChangeDetailsMyProfileTilPhone;
    @BindView(R.id.fragment_change_details_my_profile_til_email)
    TextInputLayout fragmentChangeDetailsMyProfileTilEmail;
    @BindView(R.id.fragment_change_details_my_profile_til_city)
    TextInputLayout fragmentChangeDetailsMyProfileTilCity;
    @BindView(R.id.fragment_change_details_my_profile_sp_city)
    Spinner fragmentChangeDetailsMyProfileSpCity;
    @BindView(R.id.fragment_change_details_my_profile_til_password)
    TextInputLayout fragmentChangeDetailsMyProfileTilPassword;
    @BindView(R.id.fragment_change_details_my_profile_til_confirm_password)
    TextInputLayout fragmentChangeDetailsMyProfileTilConfirmPassword;
    private NavController navController;
    private UserData userData;
    private ViewModelUser viewModelUser;
    private static ArrayList<AlbumFile> alpom = new ArrayList<>();
    private static String mPath;
    private static final String CLIENTPROFILEIMAGE = "image";

    public ChangeDetailsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_change_details, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        userData = LoadUserData(getActivity());
        disappearKeypad(getActivity(), getView());
        setUpActivity();
        homeCycleActivity.setNavigation("g");
        initListener();
        setUserData();
        fragmentChangeDetailsMyProfileTilPassword.getEditText().setTypeface(Typeface.DEFAULT);
        fragmentChangeDetailsMyProfileTilPassword.getEditText().setTransformationMethod(new PasswordTransformationMethod());
        fragmentChangeDetailsMyProfileTilConfirmPassword.getEditText().setTypeface(Typeface.DEFAULT);
        fragmentChangeDetailsMyProfileTilConfirmPassword.getEditText().setTransformationMethod(new PasswordTransformationMethod());

        return root;
    }

    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.setGeneralLoginAndUpdateProfile().observe(getViewLifecycleOwner(), new Observer<GetUserDataResponce>() {
            @Override
            public void onChanged(@Nullable GetUserDataResponce response) {
                if(response!=null){
                    if (response.getSuccess()==1) {
//                        showToast(getActivity(),"success");

                    }  }
            }
        });
    }

    private void setUserData() {
//        if(userData.getImage()!=null){
//            String personalImage = "https://www.barakatravel.net/"+userData.getImage().trim();
//            onLoadCirImageFromUrl2(fragmentChangeDetailsMyProfilePhotoCircularImageView,personalImage.trim(), getContext());
//        }
        fragmentChangeDetailsMyProfileTilUserName.getEditText().setText(userData.getUserName());
        fragmentChangeDetailsMyProfileTilPhone.getEditText().setText(userData.getUserPhone());
        fragmentChangeDetailsMyProfileTilEmail.getEditText().setText(userData.getUserEmail());
        String password=LoadData(getActivity(), USER_PASSWORD);
        fragmentChangeDetailsMyProfileTilPassword.getEditText().setText(password);
        fragmentChangeDetailsMyProfileTilConfirmPassword.getEditText().setText(password);

    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new AccountFragment());
        navController.navigate(R.id.action_changeDetailsFragment_to_navigation_profile);
        homeCycleActivity.setNavigation("v");
//        homeCycleActivity.bottomNavView.getMenu().getItem(4).setChecked(true);
    }

    @OnClick({R.id.fragment_change_details_my_profile_photo_circularImageView, R.id.fragment_change_details_my_profile_photo_btn, R.id.fragment_change_details_my_profile_save_changes_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_change_details_my_profile_photo_circularImageView:
                openGalleryِAlpom(getActivity(), alpom, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        mPath = result.get(0).getPath();
                        if(mPath!=null){
                            onLoadCirImageFromUrl2(fragmentChangeDetailsMyProfilePhotoCircularImageView,mPath, getContext());}
                    }
                }, 1);
                break;
            case R.id.fragment_change_details_my_profile_photo_btn:
                openGalleryِAlpom(getActivity(), alpom, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        mPath = result.get(0).getPath();
                        if(mPath!=null){
                            onLoadCirImageFromUrl2(fragmentChangeDetailsMyProfilePhotoCircularImageView,mPath, getContext());}
                    }
                }, 1);
                break;
            case R.id.fragment_change_details_my_profile_save_changes_btn:
                onValidation();
                break;
        }
    }

//    @OnClick({R.id.fragment_change_details_my_profile_photo_circularImageView, R.id.fragment_change_details_my_profile_photo_btn, R.id.fragment_change_details_save_changes_btn})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.fragment_change_details_my_profile_photo_circularImageView:
////                new MakeChangesDialog().showDialog(getActivity());
////                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new ChooseStaticEVisaFragment());
////                navController.navigate(R.id.action_changeDetailsFragment_to_paymentsFragment);
//                openGalleryِAlpom(getActivity(), alpom, new Action<ArrayList<AlbumFile>>() {
//                    @Override
//                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
//                        mPath = result.get(0).getPath();
//                        if(mPath!=null){
//                            onLoadCirImageFromUrl2(fragmentChangeDetailsMyProfilePhotoCircularImageView,mPath, getContext());}
//                    }
//                }, 1);
//                break;
//            case R.id.fragment_change_details_my_profile_photo_btn:
//                openGalleryِAlpom(getActivity(), alpom, new Action<ArrayList<AlbumFile>>() {
//                    @Override
//                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
//                        mPath = result.get(0).getPath();
//                        if(mPath!=null){
//                            onLoadCirImageFromUrl2(fragmentChangeDetailsMyProfilePhotoCircularImageView,mPath, getContext());}
//                    }
//                }, 1);
//                break;
//            case R.id.fragment_change_details_save_changes_btn:
//                onValidation();
//
////                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment, new ChangeDetailsMoreFragment());
////                navController.navigate(R.id.action_changeDetailsFragment_to_changeDetailsMoreFragment);
//
//                break;
//        }
//    }
//
//
    private void onValidation() {

        List<EditText> editTexts = new ArrayList<>();
        List<TextInputLayout> textInputLayouts = new ArrayList<>();
        List<Spinner> spiner = new ArrayList<>();

        textInputLayouts.add(fragmentChangeDetailsMyProfileTilUserName);
        textInputLayouts.add(fragmentChangeDetailsMyProfileTilEmail);
        textInputLayouts.add(fragmentChangeDetailsMyProfileTilPhone);
        textInputLayouts.add(fragmentChangeDetailsMyProfileTilPassword);
        textInputLayouts.add(fragmentChangeDetailsMyProfileTilConfirmPassword);




        cleanError(textInputLayouts);


        if (!validationAllEmpty(editTexts, textInputLayouts, spiner, getString(R.string.empty))) {

            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
            return;
        }





        if (!validationLength(fragmentChangeDetailsMyProfileTilUserName, getString(R.string.invalid_user_name), 3)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_user_name));
            return;
        }

        if (!validationPhone(getActivity(), fragmentChangeDetailsMyProfileTilPhone)) {
            ToastCreator.onCreateErrorToast(getActivity(),  getString(R.string.invalid_phone1));
            return;
        }

        if (!validationEmail(getActivity(), fragmentChangeDetailsMyProfileTilEmail)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_email_required_field));
            return;
        }

//        if (!validationLength(fragmentChangeDetailsMyProfileTilCity, getString(R.string.invalid_city_required_field), 3)) {
//            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_city_required_field));
//
//            return;
//        }

        if (!validationPassword(fragmentChangeDetailsMyProfileTilPassword, 4, getString(R.string.invalid_password))) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_password));
            return;
        }

        if (!validationConfirmPassword(getActivity(), fragmentChangeDetailsMyProfileTilPassword, fragmentChangeDetailsMyProfileTilConfirmPassword)) {
//            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_password));
            return;
        }
//        if(userData.getImage()!=null){
        onCall();
//        }

//        if (mPath !=null) {
//            onCall();
//        }else {
//            if(userData.getImage()!=null){
//                mPath = "https://www.barakatravel.net/"+userData.getImage();
//                onCall();
//            } else {
//                ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.select_image));
//                return;
//            }
//        }

    }

    private void onCall() {
        String userId = String.valueOf(userData.getUserId());
        String email = fragmentChangeDetailsMyProfileTilEmail.getEditText().getText().toString();
        String name = fragmentChangeDetailsMyProfileTilUserName.getEditText().getText().toString();
        String phone = fragmentChangeDetailsMyProfileTilPhone.getEditText().getText().toString();
        String password = fragmentChangeDetailsMyProfileTilPassword.getEditText().getText().toString();
//        String clientProfilePhoto = mPath;

//        RequestBody userId = convertToRequestBody(String.valueOf(userData.getUserId()));
//        RequestBody email = convertToRequestBody(fragmentChangeDetailsMyProfileTilEmail.getEditText().getText().toString());
//        RequestBody name = convertToRequestBody(fragmentChangeDetailsMyProfileTilUserName.getEditText().getText().toString());
//        RequestBody phone = convertToRequestBody(fragmentChangeDetailsMyProfileTilPhone.getEditText().getText().toString());
//        RequestBody password = convertToRequestBody(fragmentChangeDetailsMyProfileTilPassword.getEditText().getText().toString());
//        MultipartBody.Part clientProfilePhoto = convertFileToMultipart(mPath, CLIENTPROFILEIMAGE,getActivity());

//        showToast(getActivity(), String.valueOf(clientProfilePhoto));

        boolean remember = true;
        String passwordSave = fragmentChangeDetailsMyProfileTilPassword.getEditText().getText().toString();
        Call<GetUserDataResponce> clientCall;


        clientCall = getApiClient().editProfile(userId,name,phone , email,"cairo", password);
        viewModelUser.setGeneralLoginAndUpdateProfile(getActivity(),clientCall, passwordSave, remember, false);





    }

}