package com.example.kabboot.view.fragment.userCycle;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getUserDataResponce.GetUserDataResponce;
import com.example.kabboot.utils.ToastCreator;
import com.example.kabboot.view.activity.HomeCycleActivity;
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
    @BindView(R.id.fragment_login_til_phone)
    TextInputLayout fragmentLoginTilPhone;
    @BindView(R.id.fragment_login_til_password)
    TextInputLayout fragmentLoginTilPassword;
    private String phone;
    private String password;
    private ViewModelUser viewModelUser;
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
                if(response!=null){
                    if (response.getSuccess()==1) {
//                        showToast(getActivity(),"success");

                    }  }
            }
        });
    }

    @Override
    public void onBack() {
        getActivity().finish();
    }


    @OnClick({R.id.fragment_login_change_to_register_btn, R.id.fragment_login_login_btn, R.id.fragment_login_forget_password_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_login_change_to_register_btn:
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new RegisterFragment(),"l");
                break;
            case R.id.fragment_login_login_btn:
                onValidData();
//                startActivity(new Intent(getActivity(), HomeCycleActivity.class));
//                getActivity().finish();
                break;
            case R.id.fragment_login_forget_password_btn:
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new ForgettPasswordFragment(),"b");
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

        viewModelUser.setGeneralLoginAndUpdateProfile(getActivity(),loginCall, password, remember, true);


    }
}