
package com.example.kabboot.data.model.saveServiceOrdersRequest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveServiceOrdersRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_city")
    @Expose
    private String userCity;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("orderServiceList")
    @Expose
    private List<OrderServiceList> orderServiceList = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<OrderServiceList> getOrderServiceList() {
        return orderServiceList;
    }

    public void setOrderServiceList(List<OrderServiceList> orderServiceList) {
        this.orderServiceList = orderServiceList;
    }

}
