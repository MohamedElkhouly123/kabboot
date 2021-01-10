
package com.example.kabboot.data.model.getAllServiceDataResponce;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainCat implements Serializable {

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
    private String updated;
    @SerializedName("sub_cat")
    @Expose
    private List<SubCat> subCat = null;
    @SerializedName("main_category_vendors")
    @Expose
    private List<MainCategoryVendor> mainCategoryVendors = null;

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

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<SubCat> getSubCat() {
        return subCat;
    }

    public void setSubCat(List<SubCat> subCat) {
        this.subCat = subCat;
    }

    public List<MainCategoryVendor> getMainCategoryVendors() {
        return mainCategoryVendors;
    }

    public void setMainCategoryVendors(List<MainCategoryVendor> mainCategoryVendors) {
        this.mainCategoryVendors = mainCategoryVendors;
    }

}
