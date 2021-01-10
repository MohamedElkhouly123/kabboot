package com.example.kabboot.view.fragment.userCycle;

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


public class RegisterFragment extends BaSeFragment {


    List<TextInputLayout> textInputLayoutList = new ArrayList<>();
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
    private String email;
    private String password;
    private ViewModelUser viewModelUser;

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
                if(response!=null){
                    if (response.getSuccess()==1) {
//                        if (response.getStatus().equals("success")||response.getMessage().equals("The mobile has already been taken.")) {
//                        showToast(getActivity(), "success");
                            replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(),"b");

//                        }
                    }  }
            }
        });
    }

    @Override
    public void onBack() {
        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(), "b");
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
            ToastCreator.onCreateErrorToast(getActivity(),  getString(R.string.invalid_phone1));
            return;
        }

        if (!validationEmail(getActivity(), fragmentRegisterTilEmail)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_email_required_field));
            return;
        }

        if (!validationLength(fragmentRegisterTilCity, getString(R.string.invalid_city_required_field), 3)) {
            ToastCreator.onCreateErrorToast(getActivity(), getString(R.string.invalid_city_required_field));

            return;
        }

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


        clientCall = getApiClient().onSignUp(userName,phone , email,city, password);
        viewModelUser.setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(getActivity(), clientCall, "Succes Register", true);

    }
}