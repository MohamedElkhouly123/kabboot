package com.example.kabboot.utils;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.kabboot.adapter.ProductCatSpinnerAdapter;
import com.example.kabboot.adapter.VendorsSpinnerAdapter;
import com.example.kabboot.data.model.getAllproductCategoryResponce.GetAllproductCategoryResponce;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllVendorsDataResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralRequest {

    public static void getVendorSpinnerData(Call<GetAllVendorsDataResponce> call, VendorsSpinnerAdapter adapter, String hint, Spinner spinner, AdapterView.OnItemSelectedListener listener) {
        call.enqueue(new Callback<GetAllVendorsDataResponce>() {
            @Override
            public void onResponse(Call<GetAllVendorsDataResponce> call, Response<GetAllVendorsDataResponce> response) {
                try {
                    if (response.body()!=null) {
                        adapter.setData(response.body().getMainVendors(), hint);
                        spinner.setAdapter(adapter);
//                        spinner.setOnItemSelectedListener(listener);
                    }
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<GetAllVendorsDataResponce> call, Throwable t) {
            }
        });
    }
    public static void getProductCategorySpinnerData(Call<GetAllproductCategoryResponce> call, ProductCatSpinnerAdapter adapter, String hint, Spinner spinner, AdapterView.OnItemSelectedListener listener) {
        call.enqueue(new Callback<GetAllproductCategoryResponce>() {
            @Override
            public void onResponse(Call<GetAllproductCategoryResponce> call, Response<GetAllproductCategoryResponce> response) {
                try {
                    if (response.body()!=null) {
                        adapter.setData(response.body().getProductCategory(), hint);
                        spinner.setAdapter(adapter);
//                        spinner.setOnItemSelectedListener(listener);
                    }
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<GetAllproductCategoryResponce> call, Throwable t) {
            }
        });
    }
}