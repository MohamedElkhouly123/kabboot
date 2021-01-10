
package com.example.kabboot.data.model.getAllservicesResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllService {

    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("service_price")
    @Expose
    private String servicePrice;
    @SerializedName("main_cat")
    @Expose
    private String mainCat;
    @SerializedName("sub_cat")
    @Expose
    private String subCat;
    @SerializedName("vendor_id_fk")
    @Expose
    private String vendorIdFk;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("main_category_name")
    @Expose
    private String mainCategoryName;
    @SerializedName("sub_category_name")
    @Expose
    private String subCategoryName;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getMainCat() {
        return mainCat;
    }

    public void setMainCat(String mainCat) {
        this.mainCat = mainCat;
    }

    public String getSubCat() {
        return subCat;
    }

    public void setSubCat(String subCat) {
        this.subCat = subCat;
    }

    public String getVendorIdFk() {
        return vendorIdFk;
    }

    public void setVendorIdFk(String vendorIdFk) {
        this.vendorIdFk = vendorIdFk;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

}
