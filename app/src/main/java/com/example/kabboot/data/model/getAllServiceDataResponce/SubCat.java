
package com.example.kabboot.data.model.getAllServiceDataResponce;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCat implements Serializable {

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("ttype")
    @Expose
    private String ttype;
    @SerializedName("main_cat_fk")
    @Expose
    private String mainCatFk;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("updated")
    @Expose
    private Object updated;
    @SerializedName("sub_category_vendors")
    @Expose
    private List<SubCategoryVendor> subCategoryVendors = null;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getMainCatFk() {
        return mainCatFk;
    }

    public void setMainCatFk(String mainCatFk) {
        this.mainCatFk = mainCatFk;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Object getUpdated() {
        return updated;
    }

    public void setUpdated(Object updated) {
        this.updated = updated;
    }

    public List<SubCategoryVendor> getSubCategoryVendors() {
        return subCategoryVendors;
    }

    public void setSubCategoryVendors(List<SubCategoryVendor> subCategoryVendors) {
        this.subCategoryVendors = subCategoryVendors;
    }

}
