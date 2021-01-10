
package com.example.kabboot.data.model.getAllvendorsResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllVendorsDataResponce {

    @SerializedName("main_vendors")
    @Expose
    private List<GetAllvendors> mainVendors = null;

    public List<GetAllvendors> getMainVendors() {
        return mainVendors;
    }

    public void setMainVendors(List<GetAllvendors> mainVendors) {
        this.mainVendors = mainVendors;
    }

}
