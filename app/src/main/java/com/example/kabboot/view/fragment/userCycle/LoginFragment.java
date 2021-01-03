package com.example.kabboot.view.fragment.userCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.kabboot.R;
import com.example.kabboot.view.activity.HomeCycleActivity;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.kabboot.utils.HelperMethod.replaceFragmentWithAnimation;


public class LoginFragment extends BaSeFragment {


    List<TextInputLayout> textInputLayoutList = new ArrayList<>();
    @BindView(R.id.fragment_login_til_phone)
    TextInputLayout fragmentLoginTilPhone;
    @BindView(R.id.fragment_login_til_password)
    TextInputLayout fragmentLoginTilPassword;
    private String email;
    private String password;
//    private ViewModelUser viewModelUser;

    public LoginFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, root);
//        addKeyboardToggleListener();
//        textInputLayoutList.add(fragmentLoginForTilEmail);
//        textInputLayoutList.add(fragmentLoginForTilPassword);
//        clientData = LoadUserData(getActivity());
//        fragmentLoginForTilPassword.getEditText().setTypeface(Typeface.DEFAULT);
//        fragmentLoginForTilPassword.getEditText().setTransformationMethod(new PasswordTransformationMethod());
//        initListener();

        return root;
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
                startActivity(new Intent(getActivity(), HomeCycleActivity.class));
                getActivity().finish();
                break;
            case R.id.fragment_login_forget_password_btn:
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new ForgettPasswordFragment(),"b");
                break;
        }
    }
}