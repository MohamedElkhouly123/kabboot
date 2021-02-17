package com.example.kabboot.view.fragment.userCycle;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getUserDataResponce.GetUserDataResponce;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.example.kabboot.view.viewModel.ViewModelUser;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.example.kabboot.data.api.ApiClient.getApiClient;
import static com.example.kabboot.utils.HelperMethod.replaceFragmentWithAnimation;
import static com.example.kabboot.utils.validation.Validation.cleanError;
import static com.example.kabboot.utils.validation.Validation.validationPassword;
import static com.example.kabboot.utils.validation.Validation.validationPhone;
import static com.example.kabboot.utils.validation.Validation.validationTextInputLayoutListEmpty;


public class LoginFragment extends BaSeFragment {


    List<TextInputLayout> textInputLayoutList = new ArrayList<>();
    List<TextInputLayout> textInputLayoutList2 = new ArrayList<>();
    @BindView(R.id.fragment_login_til_phone)
    TextInputLayout fragmentLoginTilPhone;
    @BindView(R.id.fragment_login_til_password)
    TextInputLayout fragmentLoginTilPassword;
//    @BindView(R.id.tv_confirmEmailDialog_ok)
    TextView tvConfirmEmailDialogOk;
    //    @Nullable
//    @BindView(R.id.confirm_email_dialog_til_verify_code)
    private TextInputLayout confirmEmailDialogTilVerifyCode;
    private String phone;
    private String password;
    private ViewModelUser viewModelUser;
    private AlertDialog alertDialog;
    private boolean dialoghide=false;

//    private ViewModelUser viewModelUser;

    public LoginFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, root);
//        addKeyboardToggleListener();
        textInputLayoutList.add(fragmentLoginTilPhone);
        textInputLayoutList.add(fragmentLoginTilPassword);
//        clientData = LoadUserData(getActivity());
        fragmentLoginTilPassword.getEditText().setTypeface(Typeface.DEFAULT);
        fragmentLoginTilPassword.getEditText().setTransformationMethod(new PasswordTransformationMethod());
        initListener();

        return root;
    }

    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.setGeneralLoginAndUpdateProfile().observe(getViewLifecycleOwner(), new Observer<GetUserDataResponce>() {
            @Override
            public void onChanged(@Nullable GetUserDataResponce response) {
                if (response != null) {
                    if (response.getSuccess() == 0&&response.getMessage().equalsIgnoreCase("الرجاء تفعيل الحساب ")) {
//                        showToast(getActivity(),"success");
//                        if (response.getMember().getStatus().equalsIgnoreCase("0")) {

                            showVerifyDialog();
//                        }
                    }
//                    if (response.getSuccess()==1) {
//                        alertDialog.dismiss();
//                    }
                }else {

                }
            }
        });

        viewModelUser.makeResetAndNewPasswordResponseAndSignUpAndBooking().observe(getViewLifecycleOwner(), new Observer<GetUserDataResponce>() {
            @Override
            public void onChanged(@Nullable GetUserDataResponce response) {
                if (response != null) {
                    if (response.getSuccess() == 1) {

                        alertDialog.dismiss();


//                        }
                    }
                }
            }
        });
    }


    @Override
    public void onBack() {

        if(dialoghide){
            alertDialog.dismiss();
            dialoghide=false;
        }else {
            getActivity().finish();
        }
    }


    @OnClick({R.id.fragment_login_change_to_register_btn, R.id.fragment_login_login_btn, R.id.fragment_login_forget_password_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_login_change_to_register_btn:
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new RegisterFragment(), "l");
                break;
            case R.id.fragment_login_login_btn:
                onValidData();
//                startActivity(new Intent(getActivity(), HomeCycleActivity.class));
//                getActivity().finish();
                break;
            case R.id.fragment_login_forget_password_btn:
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new ForgettPasswordFragment(), "b");
                break;
        }
    }


    private void onValidData() {

        cleanError(textInputLayoutList);

        if (!validationTextInputLayoutListEmpty(textInputLayoutList, getString(R.string.empty))) {
            return;
        }

        if (!validationPhone(getActivity(), fragmentLoginTilPhone)) {
//            ToastCreator.onCreateErrorToast(getActivity(),  getString(R.string.invalid_phone1));
            return;
        }

        if (!validationPassword(fragmentLoginTilPassword, 4, getString(R.string.invalid_password))) {
            return;
        }

        onCall();
    }

    private void onCall() {
        phone = fragmentLoginTilPhone.getEditText().getText().toString();
        password = fragmentLoginTilPassword.getEditText().getText().toString();
//         apiToken=clientData.getApiToken();
//         token=new ClientFireBaseToken().getToken();
        boolean remember = true;

//        Call<ClientGeneralResponse> clientCall = getApiClient().clientLogin(email, password);


        Call<GetUserDataResponce> loginCall = null;

        loginCall = getApiClient().userLogin(phone, password);

        viewModelUser.setGeneralLoginAndUpdateProfile(getActivity(), loginCall, password, remember, true);


    }

    private void showVerifyDialog() {
        try {
            final View view = getActivity().getLayoutInflater().inflate(R.layout.confirm_email_dialog, null);
//            alertDialog = new AlertDialog.Builder(HomeFragment.this).create();

            alertDialog = new AlertDialog.Builder(getActivity()).create();
//            alertDialog.setTitle("Delete");
            alertDialog.setMessage("Please Check Your Email To enter Verification Code");
            alertDialog.setCancelable(false);
            dialoghide=true;
            confirmEmailDialogTilVerifyCode = (TextInputLayout) view.findViewById(R.id.confirm_email_dialog_til_verify_code);
            tvConfirmEmailDialogOk = (TextView) view.findViewById(R.id.tv_confirmEmailDialog_ok);

            textInputLayoutList2.add(confirmEmailDialogTilVerifyCode);
            tvConfirmEmailDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (TextUtils.isEmpty(confirmEmailDialogTilVerifyCode.getEditText().getText().toString())) {
//                    if (code.equals(et_code.getText().toString())) {
                    cleanError(textInputLayoutList2);

                    if (!validationTextInputLayoutListEmpty(textInputLayoutList2, getString(R.string.empty))) {
//                    ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
                    return;
                } else {

                    virifyOnCall();
                }

            }
        });
//            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    if (TextUtils.isEmpty(confirmEmailDialogTilVerifyCode.getEditText().getText().toString())) {
////                    if (code.equals(et_code.getText().toString())) {
////                    cleanError(textInputLayoutList2);
//
////                    if (!validationTextInputLayoutListEmpty(textInputLayoutList2, getString(R.string.empty))) {
//                        ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
//                        return;
//                    } else {
//
//                        virifyOnCall();
//                    }
//
//
//                }
//            });


//            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    alertDialog.dismiss() ;
//                }
//            });

            alertDialog.setView(view);
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

    private void virifyOnCall() {
        String verifyCode = confirmEmailDialogTilVerifyCode.getEditText().getText().toString();
//        String passwordSave = fragmentSignUpTilConfirmPassword.getEditText().getText().toString();
        Call<GetUserDataResponce> verifyCall;


        verifyCall = getApiClient().Verify(verifyCode);
        viewModelUser.setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(getActivity(), verifyCall, "Success Verification Can Login Now", true);

    }
}