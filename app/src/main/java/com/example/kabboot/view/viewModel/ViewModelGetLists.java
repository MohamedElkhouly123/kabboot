package com.example.kabboot.view.viewModel;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.kabboot.data.model.customerPromoCodesResponce.CustomerPromoCodesResponce;
import com.example.kabboot.data.model.getAllServiceDataResponce.GetAllServiceDataResponce;
import com.example.kabboot.data.model.getAllproductsResponce.GetAllproductsBySearchResponce;
import com.example.kabboot.data.model.getAllproductsResponce.GetAllproductsResponce;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllVendorsDataResponce;
import com.example.kabboot.utils.ToastCreator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.example.kabboot.utils.network.InternetState.isConnected;


public class ViewModelGetLists extends ViewModel {

//    private UserRepository userRepository;
    private MutableLiveData<GetAllVendorsDataResponce> getAllvendorsResponce = new MutableLiveData<>();
    private MutableLiveData<GetAllServiceDataResponce> getAllServiceDataResponce = new MutableLiveData<>();
    private MutableLiveData<GetAllproductsResponce> getAllProductsResponce = new MutableLiveData<>();
    private MutableLiveData<GetAllproductsBySearchResponce> getAllProductsBySearchResponce = new MutableLiveData<>();
    private MutableLiveData<CustomerPromoCodesResponce> getCustomerPromoCodesResponce = new MutableLiveData<>();
//    private MutableLiveData<GetFaqResponce> getFaqResponce = new MutableLiveData<>();



    public MutableLiveData<GetAllVendorsDataResponce> makeGetAllvendorsResponce() {
        return getAllvendorsResponce;
    }

    public void getAllvendorsResponce(final Activity activity, final LinearLayout errorSubView, final Call<GetAllVendorsDataResponce> method, final SwipeRefreshLayout FragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            FragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);

            method.enqueue(new Callback<GetAllVendorsDataResponce>() {
                @Override
                public void onResponse(Call<GetAllVendorsDataResponce> call, Response<GetAllVendorsDataResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            FragmentSrRefreshRv.setRefreshing(false);
//                            if (response.body().getStatus().equals("success")) {
//
                                getAllvendorsResponce.postValue(response.body());

                                ToastCreator.onCreateSuccessToast(activity, "Success");
//                            } else {
//                                onCreateErrorToast(activity, response.body().getMessage());
////                                new HomeFragment().setError(String.valueOf(R.string.error_list));
//
//                            }

                        } catch(Exception e){

                        }
                    }

                }

                @Override
                public void onFailure(Call<GetAllVendorsDataResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        FragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getAllvendorsResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                FragmentSrRefreshRv.setRefreshing(false);
                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
//                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }

    public MutableLiveData<GetAllServiceDataResponce> makeGetAllServiceDataResponce() {
        return getAllServiceDataResponce;
    }

    public void getAllServiceDataResponce(final Activity activity, final LinearLayout errorSubView, final Call<GetAllServiceDataResponce> method, final SwipeRefreshLayout FragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            FragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);


            method.enqueue(new Callback<GetAllServiceDataResponce>() {
                @Override
                public void onResponse(Call<GetAllServiceDataResponce> call, Response<GetAllServiceDataResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            FragmentSrRefreshRv.setRefreshing(false);
//                            if (response.body().getStatus().equals("success")) {
//
                                getAllServiceDataResponce.postValue(response.body());

                                ToastCreator.onCreateSuccessToast(activity, "Success");
//                            } else {
//                                onCreateErrorToast(activity, response.body().getMessage());
////                                new HomeFragment().setError(String.valueOf(R.string.error_list));
//
//                            }

                        } catch(Exception e){

                        }
                    }

                }

                @Override
                public void onFailure(Call<GetAllServiceDataResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        FragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getAllServiceDataResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                FragmentSrRefreshRv.setRefreshing(false);
                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
//                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }

    public MutableLiveData<GetAllproductsResponce> makeGetAllProductsDataList() {
        return getAllProductsResponce;
    }

    public void getAllProductsDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetAllproductsResponce> method, final SwipeRefreshLayout FragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            FragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);

            method.enqueue(new Callback<GetAllproductsResponce>() {
                @Override
                public void onResponse(Call<GetAllproductsResponce> call, Response<GetAllproductsResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            FragmentSrRefreshRv.setRefreshing(false);
//                            if (response.body().getStatus().equals("success")) {
//
                                getAllProductsResponce.postValue(response.body());

                                ToastCreator.onCreateSuccessToast(activity, "Success");
//                            } else {
//                                onCreateErrorToast(activity, response.body().getMessage());
////                                new HomeFragment().setError(String.valueOf(R.string.error_list));
//
//                            }

                        } catch(Exception e){

                        }
                    }

                }

                @Override
                public void onFailure(Call<GetAllproductsResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        FragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getAllProductsResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                FragmentSrRefreshRv.setRefreshing(false);
                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
//                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }

    public MutableLiveData<GetAllproductsBySearchResponce> makeGetAllProductsBySearchDataList() {
        return getAllProductsBySearchResponce;
    }

    public void getAllProductsBySearchDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetAllproductsBySearchResponce> method, final SwipeRefreshLayout FragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            FragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);

            method.enqueue(new Callback<GetAllproductsBySearchResponce>() {
                @Override
                public void onResponse(Call<GetAllproductsBySearchResponce> call, Response<GetAllproductsBySearchResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            FragmentSrRefreshRv.setRefreshing(false);
//                            if (response.body().getStatus().equals("success")) {
//
                            getAllProductsBySearchResponce.postValue(response.body());

                            ToastCreator.onCreateSuccessToast(activity, "Success");
//                            } else {
//                                onCreateErrorToast(activity, response.body().getMessage());
////                                new HomeFragment().setError(String.valueOf(R.string.error_list));
//
//                            }

                        } catch(Exception e){

                        }
                    }

                }

                @Override
                public void onFailure(Call<GetAllproductsBySearchResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        FragmentSrRefreshRv.setRefreshing(false);
                        Log.i(TAG,String.valueOf(t.getMessage()));
                        Log.i(TAG,String.valueOf(t.getCause()));
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getAllProductsBySearchResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                FragmentSrRefreshRv.setRefreshing(false);
                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
//                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }
    public MutableLiveData<CustomerPromoCodesResponce> makeGetCustomerPromoCodesDataList() {
        return getCustomerPromoCodesResponce;
    }

    public void getCustomerPromoCodesDataList(final Activity activity, final LinearLayout errorSubView, final Call<CustomerPromoCodesResponce> method, final SwipeRefreshLayout FragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            FragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);

            method.enqueue(new Callback<CustomerPromoCodesResponce>() {
                @Override
                public void onResponse(Call<CustomerPromoCodesResponce> call, Response<CustomerPromoCodesResponce> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            FragmentSrRefreshRv.setRefreshing(false);
//                            if (response.body().getStatus().equals("success")) {
//
                            getCustomerPromoCodesResponce.postValue(response.body());

                            ToastCreator.onCreateSuccessToast(activity, "Success");
//                            } else {
//                                onCreateErrorToast(activity, response.body().getMessage());
////                                new HomeFragment().setError(String.valueOf(R.string.error_list));
//
//                            }

                        } catch(Exception e){

                        }
                    }

                }

                @Override
                public void onFailure(Call<CustomerPromoCodesResponce> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        FragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getCustomerPromoCodesResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                FragmentSrRefreshRv.setRefreshing(false);
                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
//                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }

}
