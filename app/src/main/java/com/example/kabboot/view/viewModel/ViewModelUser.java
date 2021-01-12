package com.example.kabboot.view.viewModel;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kabboot.R;
import com.example.kabboot.data.model.getAppInfoResponce.GetAppInfoResponce;
import com.example.kabboot.data.model.getUserDataResponce.GetUserDataResponce;
import com.example.kabboot.utils.HelperMethod;
import com.example.kabboot.utils.ToastCreator;
import com.example.kabboot.view.activity.HomeCycleActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.example.kabboot.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.example.kabboot.data.local.SharedPreferencesManger.SaveData;
import static com.example.kabboot.data.local.SharedPreferencesManger.USER_DATA;
import static com.example.kabboot.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.kabboot.data.local.SharedPreferencesManger.USER_TOKEN;
import static com.example.kabboot.data.local.SharedPreferencesManger.clean;
import static com.example.kabboot.utils.HelperMethod.dismissProgressDialog;
import static com.example.kabboot.utils.HelperMethod.progressDialog;
import static com.example.kabboot.utils.HelperMethod.showToast;
import static com.example.kabboot.utils.ToastCreator.onCreateErrorToast;
import static com.example.kabboot.utils.network.InternetState.isConnected;


public class ViewModelUser extends ViewModel {

//    private UserRepository userRepository;
    private MutableLiveData<GetUserDataResponce> generalLoginAndUpdateProfileResponse = new MutableLiveData<>();
    private MutableLiveData<GetUserDataResponce> generalRegisterationAndForgetPasswordAndBookingsResponse = new MutableLiveData<>();
    private MutableLiveData<GetAppInfoResponce> getAppInfoResponse = new MutableLiveData<>();
//    private MutableLiveData<AppSettingResponce> appSettingsResponse = new MutableLiveData<>();


    private String token;
    private String apiToken;

    public MutableLiveData<GetUserDataResponce> setGeneralLoginAndUpdateProfile() {
        return generalLoginAndUpdateProfileResponse;
    }

    public void setGeneralLoginAndUpdateProfile(final Activity activity, final Call<GetUserDataResponce> method, final String password, final boolean remember, final boolean auth) {
        if (isConnected(activity)) {

            if (progressDialog == null) {
                HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
            } else {
                if (!progressDialog.isShowing()) {
                    HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
                }
            }
//            userRepository = new UserRepository();

//            UserRepository.getInstance().clientLogin(activity, email, password, remember, auth)

                method.enqueue(new Callback<GetUserDataResponce>() {
                @Override
                public void onResponse(Call<GetUserDataResponce> call, Response<GetUserDataResponce> response) {

                    if (response.body() != null) {
                        try {

                            if (response.body().getSuccess()==1) {
//                                if (response.body().getMessage() != "the user is not vertified to login please visit your email") {
                                clean(activity);
                                SaveData(activity, USER_PASSWORD, password);
                                SaveData(activity, USER_TOKEN, response.body().getToken());
                                SaveData(activity, USER_DATA, response.body().getMember());
                                SaveData(activity, REMEMBER_ME, remember);
                                if (auth) {
//                                    Call<UserLoginGeneralResponce> tokenCall = null;
//                                    token=new ClientFireBaseToken().getToken();
//                                    apiToken=response.body().getData().getApiToken();
//                                    showToast(activity, "token"+apiToken);
//                                    if (ISCLIENT.equals("true")) {
//
//                                        tokenCall = getApiClient().clientSignUpToken(token, "android",apiToken);
//                                    } else if(ISCLIENT=="false") {
//                                        tokenCall = getApiClient().restaurantSignUpToken(token, "android",apiToken);
//                                    }
//
//
//                                    getAndMakeRegisterToken(activity,tokenCall);
                                    Intent intent = new Intent(activity, HomeCycleActivity.class);
                                    activity.startActivity(intent);
//                                    showToast(activity, t.getCause());
                                    activity.finish();
                                }
//                            }

                                dismissProgressDialog();
                                generalLoginAndUpdateProfileResponse.postValue(response.body());
//                                if (response.body().getMessage()!=null) {
                                ToastCreator.onCreateSuccessToast(activity, "Success");

//                                }

                            } else {
                                dismissProgressDialog();
//                                showLongToast(activity, response.body().getMessage());
                                onCreateErrorToast(activity, response.body().getMessage());
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetUserDataResponce> call, Throwable t) {
                    dismissProgressDialog();
//                    showToast(activity, String.valueOf(t.getLocalizedMessage()));
                    onCreateErrorToast(activity, activity.getString(R.string.error));
                    generalLoginAndUpdateProfileResponse.postValue(null);
                }
            });
        } else {
            try {
                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }

    }

    public MutableLiveData<GetUserDataResponce> makeResetAndNewPasswordResponseAndSignUpAndBooking() {
        return generalRegisterationAndForgetPasswordAndBookingsResponse;
    }

    public void setAndMakeResetAndNewPasswordResponseAndSignUpAndBooking(final Activity activity, final Call<GetUserDataResponce> method, String succes_send, boolean book) {
        if (isConnected(activity)) {

            if (progressDialog == null) {
                HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
            } else {
                if (!progressDialog.isShowing()) {
                    HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
                }
            }

            method.enqueue(new Callback<GetUserDataResponce>() {
                @Override
                public void onResponse(Call<GetUserDataResponce> call, Response<GetUserDataResponce> response) {

                    if (response.body() != null) {
                        try {

                            if (response.body().getSuccess()==1) {
                                dismissProgressDialog();
                                generalRegisterationAndForgetPasswordAndBookingsResponse.postValue(response.body());
                                ToastCreator.onCreateSuccessToast(activity, succes_send);
//                                if(!book){
//                                    ToastCreator.onCreateSuccessToast(activity, response.body().getMessage());
//                                }
                        } else {
                                dismissProgressDialog();
                                onCreateErrorToast(activity, response.body().getMessage() );

                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetUserDataResponce> call, Throwable t) {
                    dismissProgressDialog();
                    showToast(activity, String.valueOf(t.getMessage()));
                    Log.i(TAG,String.valueOf(t.getMessage()));
                    Log.i(TAG,String.valueOf(t.getCause()));
                    onCreateErrorToast(activity, activity.getString(R.string.error));
                    generalRegisterationAndForgetPasswordAndBookingsResponse.postValue(null);
                }
            });
        } else {
            try {
                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }

    }

    public MutableLiveData<GetAppInfoResponce> makeGetAppInfo() {
        return getAppInfoResponse;
    }

    public void getAppInfo(final Activity activity, final Call<GetAppInfoResponce> method) {
        if (isConnected(activity)) {

            if (progressDialog == null) {
                HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
            } else {
                if (!progressDialog.isShowing()) {
                    HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
                }
            }

            method.enqueue(new Callback<GetAppInfoResponce>() {
                @Override
                public void onResponse(Call<GetAppInfoResponce> call, Response<GetAppInfoResponce> response) {

                    if (response.body() != null) {
                        try {

//                            if (response.body().getStatus().equals("success")) {
                                dismissProgressDialog();
                                getAppInfoResponse.postValue(response.body());
                                ToastCreator.onCreateSuccessToast(activity, "Success");
//                            } else {
//                                dismissProgressDialog();
//                                onCreateErrorToast(activity, response.body().getMessage());
//
//                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetAppInfoResponce> call, Throwable t) {
                    dismissProgressDialog();
//                    Log.e(TAG, t.getLocalizedMessage());
//                    Log.e(TAG, t.getMessage());
//                    t.printStackTrace();
//                    showToast(activity, String.valueOf(t.getCause()));
                    onCreateErrorToast(activity, activity.getString(R.string.error));
                    getAppInfoResponse.postValue(null);
                }
            });
        } else {
            try {
                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
            } catch (Exception e) {

            }

        }

    }
//
//    public MutableLiveData<AppSettingResponce> makeGetAppSettings() {
//        return appSettingsResponse;
//    }
//
//    public void getAppSettings(final Activity activity, final Call<AppSettingResponce> method, boolean dimiss) {
//        if (isConnected(activity)) {
//
//            if (progressDialog == null) {
//                HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
//            } else {
//                if (!progressDialog.isShowing()) {
//                    HelperMethod.showProgressDialog(activity, activity.getString(R.string.wait));
//                }
//            }
//
//            method.enqueue(new Callback<AppSettingResponce>() {
//                @Override
//                public void onResponse(Call<AppSettingResponce> call, Response<AppSettingResponce> response) {
//
//                    if (response.body() != null) {
//                        try {
//
//                            if (response.body().getStatus().equals("success")) {
//                                dismissProgressDialog();
//                                appSettingsResponse.postValue(response.body());
//                                ToastCreator.onCreateSuccessToast(activity, "Success");
//                            } else {
//                                dismissProgressDialog();
//                                onCreateErrorToast(activity, response.body().getMessage());
//
//                            }
//                        } catch (Exception e) {
//
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<AppSettingResponce> call, Throwable t) {
//                    dismissProgressDialog();
////                    Log.e(TAG, t.getLocalizedMessage());
////                    Log.e(TAG, t.getMessage());
////                    t.printStackTrace();
////                    showToast(activity, String.valueOf(t.getCause()));
//                    onCreateErrorToast(activity, activity.getString(R.string.error));
//                    appSettingsResponse.postValue(null);
//                }
//            });
//        } else {
//            try {
//                onCreateErrorToast(activity, activity.getString(R.string.error_inter_net));
//            } catch (Exception e) {
//
//            }
//
//        }
//
//    }
//


}
