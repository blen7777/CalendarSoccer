package com.applaudo.testapplaudo.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by jose.lopez04 on 22/11/2017.
 */

public class Team implements Parcelable{

    private String ID;
    private String since;
    private String coach;
    private String team_nickname;
    private String stadiun;
    private String img_logo;
    private String img_stadiun;
    private String latitude;
    private String longitude;
    private String website;
    private String adress;
    private String phone_number;
    private String description;
    private String video_url;
    private String name;

    public Team() {
       super();
    }

    public Team(Parcel parcel) {
        this.ID=parcel.readString();
        this.since=parcel.readString();
        this.coach=parcel.readString();
        this.team_nickname=parcel.readString();
        this.stadiun=parcel.readString();
        this.img_logo=parcel.readString();
        this.img_stadiun=parcel.readString();
        this.latitude=parcel.readString();
        this.longitude=parcel.readString();
        this.website=parcel.readString();
        this.adress=parcel.readString();
        this.phone_number=parcel.readString();
        this.description=parcel.readString();
        this.video_url=parcel.readString();
        this.name=parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.ID);
        parcel.writeString(this.since);
        parcel.writeString(this.coach);
        parcel.writeString(this.team_nickname);
        parcel.writeString(this.stadiun);
        parcel.writeString(this.img_logo);
        parcel.writeString(this.img_stadiun);
        parcel.writeString(this.latitude);
        parcel.writeString(this.longitude);
        parcel.writeString(this.website);
        parcel.writeString(this.adress);
        parcel.writeString(this.phone_number);
        parcel.writeString(this.description);
        parcel.writeString(this.video_url);
        parcel.writeString(this.name);
    }

    public static final Creator<Team> CREATOR= new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel parcel) {
            return new Team(parcel);
        }

        @Override
        public Team[] newArray(int i) {
            return new Team[i];
        }
    };

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getTeam_nickname() {
        return team_nickname;
    }

    public void setTeam_nickname(String team_nickname) {
        this.team_nickname = team_nickname;
    }

    public String getStadiun() {
        return stadiun;
    }

    public void setStadiun(String stadiun) {
        this.stadiun = stadiun;
    }

    public String getImg_logo() {
        return img_logo;
    }

    public void setImg_logo(String img_logo) {
        this.img_logo = img_logo;
    }

    public String getImg_stadiun() {
        return img_stadiun;
    }

    public void setImg_stadiun(String img_stadiun) {
        this.img_stadiun = img_stadiun;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String wensite) {
        this.website = website;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
