package com.example.kabboot.view.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.example.kabboot.R;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.kabboot.utils.HelperMethod.replaceFragmentWithAnimation;


public class RegisterFragment extends BaSeFragment {


    List<TextInputLayout> textInputLayoutList = new ArrayList<>();
    @BindView(R.id.fragment_register_til_phone)
    TextInputLayout fragmentRegisterTilPhone;
    @BindView(R.id.fragment_register_til_gender)
    TextInputLayout fragmentRegisterTilGender;
    @BindView(R.id.fragment_register_sp_gender)
    Spinner fragmentRegisterSpGender;
    @BindView(R.id.fragment_register_til_password)
    TextInputLayout fragmentRegisterTilPassword;
    @BindView(R.id.fragment_register_til_confirm_password)
    TextInputLayout fragmentRegisterTilConfirmPassword;
    private String email;
    private String password;
//    private ViewModelUser viewModelUser;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_register, container, false);

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
        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(), "b");
    }


    @OnClick({R.id.fragment_register_change_to_login_btn, R.id.fragment_register_register_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_register_change_to_login_btn:
                replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(),"r");
                break;
            case R.id.fragment_register_register_btn:
                break;
        }
    }
}