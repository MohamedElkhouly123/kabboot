
package com.example.kabboot.data.model.getBookingServiceOrdersRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServiceOrderDetail implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("order_id_fk")
    @Expose
    private String orderIdFk;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("service_price")
    @Expose
    private String servicePrice;
    @SerializedName("vendor_id_fk")
    @Expose
    private String vendorIdFk;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderIdFk() {
        return orderIdFk;
    }

    public void setOrderIdFk(String orderIdFk) {
        this.orderIdFk = orderIdFk;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getVendorIdFk() {
        return vendorIdFk;
    }

    public void setVendorIdFk(String vendorIdFk) {
        this.vendorIdFk = vendorIdFk;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

}
