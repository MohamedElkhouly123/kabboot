
package com.example.kabboot.data.model.getAllproductsResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetAllproductsBySearchResponce implements Serializable {

    @SerializedName("data_founded")
    @Expose
    private List<AllProduct> dataFounded = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<AllProduct> getDataFounded() {
        return dataFounded;
    }

    public void setDataFounded(List<AllProduct> dataFounded) {
        this.dataFounded = dataFounded;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
