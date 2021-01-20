package com.example.kabboot.view.viewModel;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.kabboot.data.model.getBookingProductsOrdersRequest.GetBookingProductsOrdersResponce;
import com.example.kabboot.data.model.getBookingServiceOrdersRequest.GetBookingServiceOrdersResponce;
import com.example.kabboot.utils.ToastCreator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.example.kabboot.utils.ToastCreator.onCreateErrorToast;
import static com.example.kabboot.utils.network.InternetState.isConnected;


public class ViewModelGetBookingsLists extends ViewModel {

//    private UserRepository userRepository;
    private MutableLiveData<List<GetBookingServiceOrdersResponce>> getBookingsServicesResponce = new MutableLiveData<>();
    private MutableLiveData<List<GetBookingProductsOrdersResponce>> getBookingsProductsResponce = new MutableLiveData<>();
//    private MutableLiveData<GetBookingPackageResponce> getBookingsHajjAndUmrahResponce = new MutableLiveData<>();
//    private MutableLiveData<GetBookingEvisaResponce> getBookingsEVisaResponce = new MutableLiveData<>();



    public MutableLiveData<List<GetBookingServiceOrdersResponce>> makeGetBookingsServicesDataList() {
        return getBookingsServicesResponce;
    }

    public void getBookingsServicesDataList(final Activity activity, final LinearLayout errorSubView, final Call<List<GetBookingServiceOrdersResponce>> method, final SwipeRefreshLayout flightsHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            flightsHomeFragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);

            method.enqueue(new Callback<List<GetBookingServiceOrdersResponce>>() {
                @Override
                public void onResponse(Call<List<GetBookingServiceOrdersResponce>> call, Response<List<GetBookingServiceOrdersResponce>> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            flightsHomeFragmentSrRefreshRv.setRefreshing(false);
//                            if (response.body().getStatus().equals("success")) {
////
                                getBookingsServicesResponce.postValue(response.body());
//
                                ToastCreator.onCreateSuccessToast(activity, "Success");
//                            } else {
//                                onCreateErrorToast(activity, response.body().getMessage());
////                                new HomeFragment().setError(String.valueOf(R.string.error_list));
//
//                            }

                        } catch(Exception e){

                        }
                    }else {
                        onCreateErrorToast(activity, "No Data");
                    }

                }

                @Override
                public void onFailure(Call<List<GetBookingServiceOrdersResponce>> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        flightsHomeFragmentSrRefreshRv.setRefreshing(false);
                        onCreateErrorToast(activity, t.getMessage());
//                        onCreateErrorToast(activity, "No Data");
                        Log.i(TAG,String.valueOf(t.getMessage()));
                        Log.i(TAG,String.valueOf(t.getCause()));
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getBookingsServicesResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                flightsHomeFragmentSrRefreshRv.setRefreshing(false);
                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
//                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }

    public MutableLiveData<List<GetBookingProductsOrdersResponce>> makeGetBookingsProductsDataList() {
        return getBookingsProductsResponce;
    }

    public void getHBookingsProductsDataList(final Activity activity, final LinearLayout errorSubView, final Call<List<GetBookingProductsOrdersResponce>> method, final SwipeRefreshLayout hotelsHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
        if (isConnected(activity)) {

            hotelsHomeFragmentSrRefreshRv.setRefreshing(true);
            errorSubView.setVisibility(View.GONE);


            method.enqueue(new Callback<List<GetBookingProductsOrdersResponce>>() {
                @Override
                public void onResponse(Call<List<GetBookingProductsOrdersResponce>> call, Response<List<GetBookingProductsOrdersResponce>> response) {

                    if (response.body() != null) {
                        try {
//                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                            loadMore.setVisibility(View.GONE);
                            hotelsHomeFragmentSrRefreshRv.setRefreshing(false);
//                            if (response.body().getStatus().equals("success")) {
//
                                getBookingsProductsResponce.postValue(response.body());

                                ToastCreator.onCreateSuccessToast(activity, "Success");
//                            } else {
//                                onCreateErrorToast(activity, response.body().getMessage());
////                                new HomeFragment().setError(String.valueOf(R.string.error_list));

//                            }

                        } catch(Exception e){

                        }
                    }

                }

                @Override
                public void onFailure(Call<List<GetBookingProductsOrdersResponce>> call, Throwable t) {
                    try {
//                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                        loadMore.setVisibility(View.GONE);
                        hotelsHomeFragmentSrRefreshRv.setRefreshing(false);
//                        new HomeFragment().setError(String.valueOf(R.string.error_list));
                        getBookingsProductsResponce.postValue(null);
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            try {
//                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
//                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
                loadMore.setVisibility(View.GONE);
                hotelsHomeFragmentSrRefreshRv.setRefreshing(false);
                errorSubView.setVisibility(View.VISIBLE);
//                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
//                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }
    }

//    public MutableLiveData<GetBookingPackageResponce> makeGetBookingsHajjAndUmrahDataList() {
//        return getBookingsHajjAndUmrahResponce;
//    }
//
//    public void getBookingsHajjAndUmrahDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetBookingPackageResponce> method, final SwipeRefreshLayout hajjAndUmrahHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
//        if (isConnected(activity)) {
//
//            hajjAndUmrahHomeFragmentSrRefreshRv.setRefreshing(true);
//            errorSubView.setVisibility(View.GONE);
//
//            method.enqueue(new Callback<GetBookingPackageResponce>() {
//                @Override
//                public void onResponse(Call<GetBookingPackageResponce> call, Response<GetBookingPackageResponce> response) {
//
//                    if (response.body() != null) {
//                        try {
////                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
////                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//                            loadMore.setVisibility(View.GONE);
//                            hajjAndUmrahHomeFragmentSrRefreshRv.setRefreshing(false);
//                            if (response.body().getStatus().equals("success")) {
////
//                                getBookingsHajjAndUmrahResponce.postValue(response.body());
//
//                                ToastCreator.onCreateSuccessToast(activity, "Success");
//                            } else {
//                                onCreateErrorToast(activity, response.body().getMessage());
////                                new HomeFragment().setError(String.valueOf(R.string.error_list));
//
//                            }
//
//                        } catch(Exception e){
//
//                        }
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<GetBookingPackageResponce> call, Throwable t) {
//                    try {
////                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
////                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//                        loadMore.setVisibility(View.GONE);
//                        hajjAndUmrahHomeFragmentSrRefreshRv.setRefreshing(false);
////                        new HomeFragment().setError(String.valueOf(R.string.error_list));
//                        getBookingsHajjAndUmrahResponce.postValue(null);
//                    } catch (Exception e) {
//
//                    }
//                }
//            });
//        } else {
//            try {
////                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
////                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//                loadMore.setVisibility(View.GONE);
//                hajjAndUmrahHomeFragmentSrRefreshRv.setRefreshing(false);
//                errorSubView.setVisibility(View.VISIBLE);
////                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
////                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
//            } catch (Exception e) {
//
//            }
//
//        }
//    }
//
//
//
//    public MutableLiveData<GetBookingEvisaResponce> makeGetBookingsEVisaDataList() {
//        return getBookingsEVisaResponce;
//    }
//
//    public void getBookingsEVsaDataList(final Activity activity, final LinearLayout errorSubView, final Call<GetBookingEvisaResponce> method, final SwipeRefreshLayout flightsHomeFragmentSrRefreshRv, final RelativeLayout loadMore) {
//        if (isConnected(activity)) {
//
//            flightsHomeFragmentSrRefreshRv.setRefreshing(true);
//            errorSubView.setVisibility(View.GONE);
//
//            method.enqueue(new Callback<GetBookingEvisaResponce>() {
//                @Override
//                public void onResponse(Call<GetBookingEvisaResponce> call, Response<GetBookingEvisaResponce> response) {
//
//                    if (response.body() != null) {
//                        try {
////                            clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
////                            clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//                            loadMore.setVisibility(View.GONE);
//                            flightsHomeFragmentSrRefreshRv.setRefreshing(false);
//                            if (response.body().getStatus().equals("success")) {
////
//                                getBookingsEVisaResponce.postValue(response.body());
//
//                                ToastCreator.onCreateSuccessToast(activity, "Success");
//                            } else {
//                                onCreateErrorToast(activity, response.body().getMessage());
////                                new HomeFragment().setError(String.valueOf(R.string.error_list));
//
//                            }
//
//                        } catch(Exception e){
//
//                        }
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<GetBookingEvisaResponce> call, Throwable t) {
//                    try {
////                        clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
////                        clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//                        loadMore.setVisibility(View.GONE);
//                        flightsHomeFragmentSrRefreshRv.setRefreshing(false);
////                        onCreateErrorToast(activity, t.getMessage());
//
////                        new HomeFragment().setError(String.valueOf(R.string.error_list));
//                        getBookingsEVisaResponce.postValue(null);
//                    } catch (Exception e) {
//
//                    }
//                }
//            });
//        } else {
//            try {
////                clientAndRestaurantHomeFragmentSFlShimmer.stopShimmer();
////                clientAndRestaurantHomeFragmentSFlShimmer.setVisibility(View.GONE);
//                loadMore.setVisibility(View.GONE);
//                flightsHomeFragmentSrRefreshRv.setRefreshing(false);
//                errorSubView.setVisibility(View.VISIBLE);
////                new HomeFragment().setError(String.valueOf(R.string.error_inter_net));
////                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
//            } catch (Exception e) {
//
//            }
//
//        }
//    }
}
