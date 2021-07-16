package com.lpikitb.lpikitbmobile.Model;

public class FindFriendModel {
    private String nama_lengkap;
    private String userid;
    private boolean requestSent;

    public FindFriendModel(String nama_lengkap, String userid, boolean requestSent) {
        this.nama_lengkap = nama_lengkap;
        this.userid = userid;
        this.requestSent = requestSent;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isRequestSent() {
        return requestSent;
    }

    public void setRequestSent(boolean requestSent) {
        this.requestSent = requestSent;
    }
}