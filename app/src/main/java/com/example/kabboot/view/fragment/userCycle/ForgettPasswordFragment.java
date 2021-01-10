package com.example.kabboot.view.fragment.userCycle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import static com.example.kabboot.utils.validation.Validation.validationPhone;
import static com.example.kabboot.utils.validation.Validation.validationTextInputLayoutListEmpty;


public class ForgettPasswordFragment extends BaSeFragment {


    List<TextInputLayout> textInputLayoutList = new ArrayList<>();
    @BindView(R.id.fragment_forget_password_up_til_phone)
    TextInputLayout fragmentForgetPasswordUpTilPhone;
    private String phone;
    private ViewModelUser viewModelUser;

    public ForgettPasswordFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_forget_password, container, false);

        ButterKnife.bind(this, root);
        initListener();
        textInputLayoutList.add(fragmentForgetPasswordUpTilPhone);

        return root;
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void initListener() {
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelUser.makeResetAndNewPasswordResponseAndSignUpAndBooking().observe(this, new Observer<GetUserDataResponce>() {
            @Override
            public void onChanged(@Nullable GetUserDataResponce response) {
                if(response!=null) {
                    if (response.getSuccess()==1) {
                        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(),"b");
//                        showToast(getActivity(), "success");

                    }
                }
            }
        });
    }

    @Override
    public void onBack() {
        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(), "b");
    }


    @OnClick(R.id.fragment_forget_password_rest_btn)
    public void onClick() {
        onValidData();
    }

    private void onValidData() {

        cleanError(textInputLayoutList);

        if (!validationTextInputLayoutListEmpty(textInputLayoutList, getString(R.string.empty))) {
            return;
        }

        if (!validationPhone(getActivity(), fragmentForgetPasswordUpTilPhone)) {
//            ToastCreator.onCreateErrorToast(getActivity(),  getString(R.string.invalid_phone1));
            return;
        }


        onCall();
    }

    private void onCall() {
        phone = fragmentForgetPasswordUpTilPhone.getEditText().getText().toString();


        Call<GetUserDataResponce> resetPasswordCall = null;

        resetPasswordCall = getApiClient().userResetPassword(phone);

        viewModelUser.setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(getActivity(),resetPasswordCall, "Succes Set New Password", true);


    }

}