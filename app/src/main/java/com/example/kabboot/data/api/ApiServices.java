package com.example.kabboot.data.api;


import com.example.kabboot.data.model.getAllServiceDataResponce.GetAllServiceDataResponce;
import com.example.kabboot.data.model.getAllproductsResponce.GetAllproductsResponce;
import com.example.kabboot.data.model.getAllservicesResponce.GetAllservicesResponce;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllVendorsDataResponce;
import com.example.kabboot.data.model.getAppInfoResponce.GetAppInfoResponce;
import com.example.kabboot.data.model.getUserDataResponce.GetUserDataResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {
    @GET("getAllvendors")
    Call<GetAllVendorsDataResponce> clientGetAgetAllvendors(
//            @Query("api_token") String apiToken

    );
    @GET("getAllService_data")
    Call<GetAllServiceDataResponce> getAllServiceData(

    );

    @GET("getAllservices")
    Call<GetAllservicesResponce> getAllservices(

    );

    @GET("getAllproducts")
    Call<GetAllproductsResponce> getAllProductsData(

    );

    @GET("getAppinfo")
    Call<GetAppInfoResponce> getAppInfoData(

    );

    @POST("login_app")
    @FormUrlEncoded
    Call<GetUserDataResponce> userLogin(@Field("user_phone") String phone,
                                        @Field("user_pass") String password);

    @POST("register")
    @FormUrlEncoded
    Call<GetUserDataResponce> onSignUp(@Field("user_name") String name,
                                            @Field("user_phone") String phone,
                                            @Field("user_email") String email,
                                            @Field("user_city") String city,
                                            @Field("user_pass") String password);

    @POST("forgetPassword")
    @FormUrlEncoded
    Call<GetUserDataResponce> userResetPassword(@Field("user_phone") String phone);

    @POST("saveServiceOrders")
    @FormUrlEncoded
    Call<GetUserDataResponce> saveServiceOrders(
            @Field("user_id") String user_id,
            @Field("user_name") String user_name,
            @Field("user_phone") String user_phone,
            @Field("user_city") String user_city,
            @Field("token") String token,
            @Field("orderServiceList") List<Integer> orderServiceList

    );

    @POST("saveStoreOrders")
    @FormUrlEncoded
    Call<GetUserDataResponce> saveStoreOrders(
            @Field("user_id") String user_id,
            @Field("user_phone") String user_phone,
            @Field("token") String token,
            @Field("orderItemList") List<Integer> orderItemList

    );
}
