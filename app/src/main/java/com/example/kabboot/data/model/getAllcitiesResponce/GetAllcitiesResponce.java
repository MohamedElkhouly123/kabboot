
package com.example.kabboot.data.model.getAllcitiesResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllcitiesResponce {

    @SerializedName("all_cities")
    @Expose
    private List<AllCity> allCities = null;

    public List<AllCity> getAllCities() {
        return allCities;
    }

    public void setAllCities(List<AllCity> allCities) {
        this.allCities = allCities;
    }

}
