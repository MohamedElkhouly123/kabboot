package com.example.kabboot.view.fragment.userCycle;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getUserDataResponce.GetUserDataResponce;
import com.example.kabboot.utils.ToastCreator;
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
import static com.example.kabboot.utils.HelperMethod.disappearKeypad;
import static com.example.kabboot.utils.HelperMethod.replaceFragmentWithAnimation;
import static com.example.kabboot.utils.validation.Validation.cleanError;
import static com.example.kabboot.utils.validation.Validation.validationAllEmpty;
import static com.example.kabboot.utils.validation.Validation.validationConfirmPassword;
import static com.example.kabboot.utils.validation.Validation.validationEmail;
import static com.example.kabboot.utils.validation.Validation.validationLength;
import static com.example.kabboot.utils.validation.Validation.validationPassword;
import static com.example.kabboot.utils.validation.Validation.validationPhone;
import static com.example.kabboot.utils.validation.Validation.validationTextInputLayoutListEmpty;


public class RegisterFragment extends BaSeFragment {


    List<TextInputLayout> textInputLayoutList = new ArrayList<>();
    List<TextInputLayout> textInputLayoutList2 = new ArrayList<>();
    @BindView(R.id.fragment_register_til_phone)
    TextInputLayout fragmentRegisterTilPhone;
    @BindView(R.id.fragment_register_til_password)
    TextInputLayout fragmentRegisterTilPassword;
    @BindView(R.id.fragment_register_til_confirm_password)
    TextInputLayout fragmentRegisterTilConfirmPassword;
    @BindView(R.id.fragment_register_til_user_name)
    TextInputLayout fragmentRegisterTilUserName;
    @BindView(R.id.fragment_register_til_email)
    TextInputLayout fragmentRegisterTilEmail;
    @BindView(R.id.fragment_register_til_city)
    TextInputLayout fragmentRegisterTilCity;

    @BindView(R.id.fragment_register_sp_city)
    Spinner fragmentRegisterSpCity;
//    @BindView(R.id.confirm_email_dialog_til_verify_code)
    TextInputLayout confirmEmailDialogTilVerifyCode;
//    @BindView(R.id.tv_confirmEmailDialog_ok)
    TextView tvConfirmEmailDialogOk;
    private String email;
    private String password;
    private ViewModelUser viewModelUser;
    private boolean first = true;
    private AlertDialog alertDialog;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_register, container, false);

        ButterKnife.bind(this, root);
        disappearKeypad(getActivity(), getView());
        initListener();
        fragmentRegisterTilPassword.getEditText().setTypeface(Typeface.DEFAULT);
        fragmentRegisterTilPassword.getEditText().setTransformationMethod(new PasswordTransformationMethod());
        fragmentRegisterTilConfirmPassword.getEditText().setTypeface(Typeface.DEFAULT);
        fragmentRegisterTilConfirmPassword.getEditText().setTransformationMethod(new PasswordTransformationMethod());


        return root;
    }

    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.makeResetAndNewPasswordResponseAndSignUpAndBooking().observe(getViewLifecycleOwner(), new Observer<GetUserDataResponce>() {
            @Override
            public void onChanged(@Nullable GetUserDataResponce response) {
                if (response != null) {
                    if (response.getSuccess() == 1) {
//                        if (response.getStatus().equals("success")||response.getMessage().equals("The mobile has already been taken.")) {
//                        showToast(getActivity(), "success");
                        if (first) {
                            first = false;
                            showVerifyDialog();
                        } else {
                            alertDialog.dismiss();
                            replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(), "b");
                        }
//                        }
                    }
                }
            }
        });
    }

    @Override
    public void onBack() {
        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(), "b");
    }


//        final EditText et_code = dialog.findViewById(R.id.et_confirmEmailDialog_code);
//        final TextView tv_ok = dialog.findViewById(R.id.tv_confirmEmailDialog_ok);


//        tv_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!TextUtils.isEmpty(et_code.getText().toString())) {
//                    if (code.equals(et_code.getText().toString())) {
//                        dialog.dismiss();
//                        goToMain();
//                    } else {
//                        showToast(getString(R.string.errorCode));
//                    }
//
//                }
//
//            }
//        });


    private void showVerifyDialog() {
        try {
            final View view = getActivity().getLayoutInflater().inflate(R.layout.confirm_email_dialog, null);
//            alertDialog = new AlertDialog.Builder(HomeFragment.this).create();

            alertDialog = new AlertDialog.Builder(getActivity()).create();
//            alertDialog.setTitle("Delete");
            alertDialog.setMessage("Please Check Your Email To enter Verification Code");
            alertDialog.setCancelable(true);
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

    @OnClick({R.id.fragment_register_change_to_login_btn, R.id.fragment_register_register_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_register_change_to_login_btn:
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(), "r");
                break;
            case R.id.fragment_register_register_btn:
                onValidation();
                break;
        }
    }

    private void onValidation() {

        List<EditText> editTexts = new ArrayList<>();
        List<TextInputLayout> textInputLayouts = new ArrayList<>();
        List<Spinner> spinners = new ArrayList<>();

        textInputLayouts.add(fragmentRegisterTilCity);
        textInputLayouts.add(fragmentRegisterTilUserName);
        textInputLayouts.add(fragmentRegisterTilEmail);
        textInputLayouts.add(fragmentRegisterTilPassword);
        textInputLayouts.add(fragmentRegisterTilConfirmPassword);


        textInputLayouts.add(fragmentRegisterTilPhone);


        cleanError(textInputLayouts);

        if (!validationAllEmpty(editTexts, textInputLayouts, spinners, getString(R.string.empty))) {

            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.empty));
            return;
        }


        if (!validationLength(fragmentRegisterTilUserName, getString(R.string.invalid_user_name), 3)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_user_name));
            return;
        }

        if (!validationPhone(getActivity(), fragmentRegisterTilPhone)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_phone1));
            return;
        }

        if (!validationEmail(getActivity(), fragmentRegisterTilEmail)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_email_required_field));
            return;
        }

//        if (!validationLength(fragmentRegisterTilCity, getString(R.string.invalid_city_required_field), 3)) {
//            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_city_required_field));
//
//            return;
//        }

        if (!validationPassword(fragmentRegisterTilPassword, 4, getString(R.string.invalid_password))) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_password));
            return;
        }

        if (!validationConfirmPassword(getActivity(), fragmentRegisterTilPassword, fragmentRegisterTilConfirmPassword)) {
//            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_password));
            return;
        }


        onCall();
    }

    private void onCall() {
        String userName = fragmentRegisterTilUserName.getEditText().getText().toString();
        String city = fragmentRegisterTilCity.getEditText().getText().toString();
        String email = fragmentRegisterTilEmail.getEditText().getText().toString();
        String phone = fragmentRegisterTilPhone.getEditText().getText().toString();
        String password = fragmentRegisterTilConfirmPassword.getEditText().getText().toString();
//        String passwordSave = fragmentSignUpTilConfirmPassword.getEditText().getText().toString();
        Call<GetUserDataResponce> clientCall;


        clientCall = getApiClient().onSignUp(userName, phone, email, "cairo", password);
        viewModelUser.setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(getActivity(), clientCall, "Success Register", true);

    }
}