package com.lpikitb.lpikitbmobile.Model;


public class User {
    private String id_user;
    private String institusi;
    private String nama_lengkap;
    private String no_hp;
    private String email;
    private String status;
    private String Uid;

    public User(String id_user, String institusi, String nama_lengkap, String no_hp, String email) {
        this.id_user = id_user;
        this.institusi = institusi;
        this.nama_lengkap = nama_lengkap;
        this.no_hp = no_hp;
        this.email = email;
        this.status = status;
        this.Uid = Uid;
    }

    public User() {
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getInstitusi() {
        return institusi;
    }

    public void setUsername(String username) {
        this.institusi = institusi;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String status) {
        this.Uid = Uid;
    }
}