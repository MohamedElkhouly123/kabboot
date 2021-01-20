package com.example.kabboot.data.api;


import com.example.kabboot.data.model.getAllServiceDataResponce.GetAllServiceDataResponce;
import com.example.kabboot.data.model.getAllproductsResponce.GetAllproductsResponce;
import com.example.kabboot.data.model.getAllservicesResponce.GetAllservicesResponce;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllVendorsDataResponce;
import com.example.kabboot.data.model.getAppInfoResponce.GetAppInfoResponce;
import com.example.kabboot.data.model.getBookingProductsOrdersRequest.GetBookingProductsOrdersResponce;
import com.example.kabboot.data.model.getBookingServiceOrdersRequest.GetBookingServiceOrdersResponce;
import com.example.kabboot.data.model.getUserDataResponce.GetUserDataResponce;
import com.example.kabboot.data.model.saveServiceOrdersRequest.SaveServiceOrdersRequest;
import com.example.kabboot.data.model.saveStoreOrdersRequest.SaveStoreOrdersRequest;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @GET("customer_product_orders/21")
    Call<List<GetBookingProductsOrdersResponce>> getAllBookingsProductsData(

    );

    @GET("customer_service_orders/12")
    Call<List<GetBookingServiceOrdersResponce>> getAllBookingsServicesData(

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
    @POST("edit_profile")
    @FormUrlEncoded
    Call<GetUserDataResponce> editProfile(
            @Field("user_id") String user_id,
            @Field("user_name") String name,
                                       @Field("user_phone") String phone,
                                       @Field("user_email") String email,
                                       @Field("user_city") String city,
                                       @Field("user_pass") String password);

//    @Multipart
//    @POST("edit_profile")
//    Call<GetUserDataResponce> userChangeProfileDetails(
//            @Part("user_id") RequestBody userId,
//            @Part("user_name") RequestBody email,
//            @Part("user_phone") RequestBody first_name,
//            @Part("user_email") RequestBody last_name,
//            @Part("user_city") RequestBody mobile,
//            @Part("user_pass") RequestBody password,
//            @Part() MultipartBody.Part profileImage
//    );

    @POST("forgetPassword")
    @FormUrlEncoded
    Call<GetUserDataResponce> userResetPassword(@Field("user_email") String email);

//    @POST("saveServiceOrders")
//    @FormUrlEncoded
//    Call<GetUserDataResponce> saveServiceOrders(
//            @Field("user_id") String user_id,
//            @Field("user_name") String user_name,
//            @Field("user_phone") String user_phone,
//            @Field("user_city") String user_city,
//            @Field("token") String token,
//            @Field("orderServiceList[\"service_id\"]") List<Integer> orderServiceList
//
//    );

//    @POST("saveStoreOrders")
//    @FormUrlEncoded
//    Call<GetUserDataResponce> saveStoreOrders(
//            @Field("user_id") String user_id,
//            @Field("user_phone") String user_phone,
//            @Field("token") String token,
//            @Field("orderItemList[\"product_id\"\"product_qty\"]") List<OrderProductsItemsListData> orderItemList
//
//    );

    @Headers({"Accept: application/json"})
    @POST("saveStoreOrders")
    Call<GetUserDataResponce> saveStoreOrders(@Body SaveStoreOrdersRequest saveStoreOrdersRequest);

    @Headers("Content-Type: application/json")
    @POST("saveServiceOrders")
    Call<GetUserDataResponce> saveServiceOrders(@Body SaveServiceOrdersRequest saveServiceOrdersRequest);
}
