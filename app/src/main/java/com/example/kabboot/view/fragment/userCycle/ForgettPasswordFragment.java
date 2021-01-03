package com.example.kabboot.view.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.kabboot.R;
import com.example.kabboot.view.fragment.BaSeFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static com.example.kabboot.utils.HelperMethod.replaceFragmentWithAnimation;


public class ForgettPasswordFragment extends BaSeFragment {


    List<TextInputLayout> textInputLayoutList = new ArrayList<>();
    private String email;
    private String password;
//    private ViewModelUser viewModelUser;

    public ForgettPasswordFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_forget_password, container, false);

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
        replaceFragmentWithAnimation(getActivity().getSupportFragmentManager(), R.id.user_activity_fram, new LoginFragment(),"b");
    }





}