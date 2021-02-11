
package com.example.kabboot.data.model.getAllcitiesResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllCity {

    @SerializedName("city_id")
    @Expose
    private int cityId;
    @SerializedName("city_name")
    @Expose
    private String cityName;

    public AllCity(int cityId, String hint) {
        this.cityId = cityId;
        this.cityName = hint;
    }

    public AllCity(String cityName) {
        this.cityName = cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
