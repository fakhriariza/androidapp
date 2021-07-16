package com.lpikitb.lpikitbmobile.Model;

public class ChatListModel {
    private String userid;
    private String nama_lengkap;
    private String unreadcount;
    private String lastmessage;
    private String lastmessageTime;
    private String institusi;

    public ChatListModel(String userid, String nama_lengkap, String unreadcount, String lastmessage, String lastmessageTime, String institusi) {
        this.userid = userid;
        this.nama_lengkap = nama_lengkap;
        this.unreadcount = unreadcount;
        this.lastmessage = lastmessage;
        this.lastmessageTime = lastmessageTime;
        this.institusi = institusi;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getUnreadcount() {
        return unreadcount;
    }

    public void setUnreadcount(String unreadcount) {
        this.unreadcount = unreadcount;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public String getLastmessageTime() {
        return lastmessageTime;
    }

    public void setLastmessageTime(String lastmessageTime) {
        this.lastmessageTime = lastmessageTime;
    }
    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }
}
