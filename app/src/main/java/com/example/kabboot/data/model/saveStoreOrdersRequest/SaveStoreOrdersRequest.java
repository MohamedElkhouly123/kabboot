
package com.example.kabboot.data.model.saveStoreOrdersRequest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveStoreOrdersRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("orderItemList")
    @Expose
    private List<OrderItemList> orderItemList = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<OrderItemList> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemList> orderItemList) {
        this.orderItemList = orderItemList;
    }

}
