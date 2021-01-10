package com.example.kabboot.view.fragment.HomeCycle2.onLineStore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAllproductsResponce.AllProduct;
import com.example.kabboot.view.fragment.BaSeFragment;

import butterknife.ButterKnife;


public class MyCartFragment extends BaSeFragment {

    private NavController navController;
    private String onSoreOrAllProducts;
    private String dealOrPromo;
    private AllProduct productData;

    public MyCartFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            onSoreOrAllProducts = this.getArguments().getString("OnSoreOrAllProducts");
            dealOrPromo = this.getArguments().getString("DealOrPromo");
            productData = (AllProduct) this.getArguments().getSerializable("Object");
        }
        View root = inflater.inflate(R.layout.fragment_my_cart, container, false);

        ButterKnife.bind(this, root);
        navController = Navigation.findNavController(getActivity(), R.id.home_activity_fragment);
        setUpActivity();
        homeCycleActivity.setNavigation("g");
        return root;
    }

    @Override
    public void onBack() {
//        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fragment,new SelectPaymentMethodFragment());
        Bundle bundle = new Bundle();
        bundle.putString("OnSoreOrAllProducts", onSoreOrAllProducts);
        bundle.putSerializable("Object", productData);
        bundle.putString("DealOrPromo", dealOrPromo);
        navController.navigate(R.id.action_myCartFragment_to_productDetailsFragment,bundle);

    }


}