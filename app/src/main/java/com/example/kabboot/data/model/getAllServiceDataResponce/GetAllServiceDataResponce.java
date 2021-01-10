
package com.example.kabboot.data.model.getAllServiceDataResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllServiceDataResponce {

    @SerializedName("main_cat")
    @Expose
    private List<MainCat> mainCat = null;

    public List<MainCat> getMainCat() {
        return mainCat;
    }

    public void setMainCat(List<MainCat> mainCat) {
        this.mainCat = mainCat;
    }

}
