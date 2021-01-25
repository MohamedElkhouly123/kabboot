
package com.example.kabboot.data.model.getUserDataResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_city")
    @Expose
    private String userCity;
    @SerializedName("user_pass")
    @Expose
    private String userPass;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("rand_key")
    @Expose
    private String rand_key;
    @SerializedName("m_image")
    @Expose
    private String m_image;

    public String getRand_key() {
        return rand_key;
    }

    public void setRand_key(String rand_key) {
        this.rand_key = rand_key;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getM_image() {
        return m_image;
    }

    public void setM_image(String m_image) {
        this.m_image = m_image;
    }
}
