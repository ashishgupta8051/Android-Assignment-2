
package com.example.androidinternassignment2.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "crewTable")
public class CrewDetails {
    @SerializedName("agency")
    private String mAgency;
    @NonNull
    @PrimaryKey
    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("name")
    private String mName;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("wikipedia")
    private String mWikipedia;

    public String getAgency() {
        return mAgency;
    }

    public void setAgency(String agency) {
        mAgency = agency;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getWikipedia() {
        return mWikipedia;
    }

    public void setWikipedia(String wikipedia) {
        mWikipedia = wikipedia;
    }

}
