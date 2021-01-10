
package com.example.kabboot.data.model.getAllservicesResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllservicesResponce {

    @SerializedName("all_services")
    @Expose
    private List<AllService> allServices = null;

    public List<AllService> getAllServices() {
        return allServices;
    }

    public void setAllServices(List<AllService> allServices) {
        this.allServices = allServices;
    }

}
