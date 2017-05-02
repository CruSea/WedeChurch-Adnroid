package com.gcme.wedechurch.Objects;

import android.support.annotation.Keep;

/**
 * Created by kzone on 2/4/2017.
 */

@Keep
public class Church {
    private int id;
    private String churchName;
    private String country;
    private String cities;
    private String location;
    private String latitude;
    private String longitude;
    private String phone;
    private String webUrl;
    private String groupId;
    private String banner;
    private String description;
    private String logo;
    private String parentChurchId;
    private String state;

    public Church(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChurchName() {
        return churchName;
    }

    public void setChurchName(String churchName) {
        this.churchName = churchName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getParentChurchId() {
        return parentChurchId;
    }

    public void setParentChurchId(String parentChurchId) {
        this.parentChurchId = parentChurchId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
