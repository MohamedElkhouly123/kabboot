package com.example.kabboot.data.api;


import com.example.kabboot.data.model.customerPromoCodesResponce.CustomerPromoCodesResponce;
import com.example.kabboot.data.model.getAllServiceDataResponce.GetAllServiceDataResponce;
import com.example.kabboot.data.model.getAllcitiesResponce.GetAllcitiesResponce;
import com.example.kabboot.data.model.getAllproductCategoryResponce.GetAllproductCategoryResponce;
import com.example.kabboot.data.model.getAllproductsResponce.GetAllproductsBySearchResponce;
import com.example.kabboot.data.model.getAllproductsResponce.GetAllproductsResponce;
import com.example.kabboot.data.model.getAllservicesResponce.GetAllservicesResponce;
import com.example.kabboot.data.model.getAllvendorsResponce.GetAllVendorsDataResponce;
import com.example.kabboot.data.model.getAppInfoResponce.GetAppInfoResponce;
import com.example.kabboot.data.model.getBookingProductsOrdersRequest.GetBookingProductsOrdersResponce;
import com.example.kabboot.data.model.getBookingServiceOrdersRequest.GetBookingServiceOrdersResponce;
import com.example.kabboot.data.model.getSaveOrdersResponce.GetSaveOrdersResponce;
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
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiServices {

    @GET("getAllvendors")
    Call<GetAllVendorsDataResponce> clientGetAgetAllvendors(
//            @Query("api_token") String apiToken

    );

    @GET("getAllproductCategory")
    Call<GetAllproductCategoryResponce> getAllproductCategory(

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

//    @Headers({
//            "Cookie: ci_session=553dd672d95a2bb7d9979a47c071c70db78a37b2"})
    @POST("search_product")
    @FormUrlEncoded
    Call<GetAllproductsBySearchResponce> getAllProductsData(
            @Header("Cookie") String userCookie,
            @Header("Authorization") String userAuth,
            @Field("product_name") String product_name,
            @Field("product_price") String product_price,
            @Field("from_price") String from_price,
            @Field("to_price") String to_price,
            @Field("product_cat") String product_cat,
            @Field("vendor_id") String vendor_id
    );

    @GET("customer_product_orders/{id}")
    Call<List<GetBookingProductsOrdersResponce>> getAllBookingsProductsData(
            @Path("id") String userId
    );

    @GET("customer_service_orders/{id}")
    Call<List<GetBookingServiceOrdersResponce>> getAllBookingsServicesData(
            @Path("id") String userId
    );

    @GET("customer_promocodes/{id}")
    Call<CustomerPromoCodesResponce> customerPromoCodes(
            @Path("id") String userId
    );
//    @GET("{fullUrl}")
//    Call<Users> getUsers(@Path(value = "fullUrl", encoded = true) String fullUrl);


    @GET("getAllcities")
    Call<GetAllcitiesResponce> getAllcities(

    );

    @GET("getAppinfo")
    Call<GetAppInfoResponce> getAppInfoData(

    );

    @POST("login_app")
    @FormUrlEncoded
    Call<GetUserDataResponce> userLogin(@Field("user_phone") String phone,
                                        @Field("user_pass") String password);

    @POST("Verify")
    @FormUrlEncoded
    Call<GetUserDataResponce> Verify(
            @Field("rand_key") String rand_key
                                      );

    @POST("register")
    @FormUrlEncoded
    Call<GetUserDataResponce> onSignUp(@Field("user_name") String name,
                                            @Field("user_phone") String phone,
                                            @Field("user_email") String email,
                                            @Field("user_city") String city,
                                            @Field("user_pass") String password);
//    @POST("edit_profile")
//    @FormUrlEncoded
//    Call<GetUserDataResponce> editProfile(
//            @Field("user_id") String user_id,
//            @Field("user_name") String name,
//                                       @Field("user_phone") String phone,
//                                       @Field("user_email") String email,
//                                       @Field("user_city") String city,
//                                       @Field("user_pass") String password);

    @Multipart
    @POST("edit_profile")
    Call<GetUserDataResponce> editProfile(
            @Part("user_id") RequestBody userId,
            @Part("user_name") RequestBody name,
            @Part("user_email") RequestBody email,
            @Part("user_phone") RequestBody phone,
            @Part("user_city") RequestBody mobile,
            @Part("user_pass") RequestBody password,
            @Part() MultipartBody.Part profileImage
    );

    @POST("ForgetPass")
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

    @Headers("Content-Type: application/json")
    @POST("saveStoreOrders")
    Call<GetSaveOrdersResponce> saveStoreOrders(@Body SaveStoreOrdersRequest saveStoreOrdersRequest);

    @Headers("Content-Type: application/json")
    @POST("saveServiceOrders")
    Call<GetSaveOrdersResponce> saveServiceOrders(@Body SaveServiceOrdersRequest saveServiceOrdersRequest);
}
