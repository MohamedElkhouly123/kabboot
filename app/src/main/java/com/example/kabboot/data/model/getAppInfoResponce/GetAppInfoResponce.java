
package com.example.kabboot.data.model.getAppInfoResponce;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAppInfoResponce implements Serializable {

    @SerializedName("app_info")
    @Expose
    private List<AppInfo> appInfo = null;

    public List<AppInfo> getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(List<AppInfo> appInfo) {
        this.appInfo = appInfo;
    }

}
