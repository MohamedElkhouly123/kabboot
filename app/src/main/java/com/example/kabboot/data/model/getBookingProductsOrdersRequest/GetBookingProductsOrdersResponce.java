
package com.example.kabboot.data.model.getBookingProductsOrdersRequest;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBookingProductsOrdersResponce implements Serializable {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("order_time")
    @Expose
    private String orderTime;
    @SerializedName("map_long")
    @Expose
    private String mapLong;
    @SerializedName("map_lat")
    @Expose
    private String mapLat;
    @SerializedName("halet_talab")
    @Expose
    private String haletTalab;
    @SerializedName("all_sum")
    @Expose
    private Integer allSum;
    @SerializedName("order_details")
    @Expose
    private List<ProductsOrderDetail> orderDetails = null;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getMapLong() {
        return mapLong;
    }

    public void setMapLong(String mapLong) {
        this.mapLong = mapLong;
    }

    public String getMapLat() {
        return mapLat;
    }

    public void setMapLat(String mapLat) {
        this.mapLat = mapLat;
    }

    public String getHaletTalab() {
        return haletTalab;
    }

    public void setHaletTalab(String haletTalab) {
        this.haletTalab = haletTalab;
    }

    public Integer getAllSum() {
        return allSum;
    }

    public void setAllSum(Integer allSum) {
        this.allSum = allSum;
    }

    public List<ProductsOrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<ProductsOrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
