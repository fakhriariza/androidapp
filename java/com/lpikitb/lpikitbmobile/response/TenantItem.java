package com.lpikitb.lpikitbmobile.response;

import com.google.gson.annotations.SerializedName;

public class TenantItem{

	@SerializedName("owner")
	private String owner;

	@SerializedName("foto")
	private String foto;

	@SerializedName("nama")
	private String nama;

	@SerializedName("telepon")
	private String telepon;

	@SerializedName("id")
	private String id;

	@SerializedName("isi")
	private String isi;

	@SerializedName("alamat")
	private String alamat;

	public String getOwner(){
		return owner;
	}

	public String getFoto(){
		return foto;
	}

	public String getNama(){
		return nama;
	}

	public String getTelepon(){
		return telepon;
	}

	public String getId(){
		return id;
	}

	public String getIsi(){
		return isi;
	}

	public String getAlamat(){
		return alamat;
	}
}