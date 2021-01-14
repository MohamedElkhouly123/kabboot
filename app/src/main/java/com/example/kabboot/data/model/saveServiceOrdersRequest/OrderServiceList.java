
package com.example.kabboot.data.model.saveServiceOrdersRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderServiceList implements Serializable {

    @SerializedName("service_id")
    @Expose
    private Integer serviceId;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

}
