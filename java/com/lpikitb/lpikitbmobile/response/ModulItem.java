package com.lpikitb.lpikitbmobile.response;

import com.google.gson.annotations.SerializedName;

public class ModulItem{

	@SerializedName("thumbnail")
	private String thumbnail;

	@SerializedName("penulis")
	private String penulis;

	@SerializedName("url_youtube")
	private String urlYoutube;

	@SerializedName("id")
	private String id;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("judul")
	private String judul;

	@SerializedName("isi")
	private String isi;

	public String getThumbnail(){
		return thumbnail;
	}

	public String getPenulis(){
		return penulis;
	}

	public String getUrlYoutube(){
		return urlYoutube;
	}

	public String getId(){
		return id;
	}

	public String getTanggal(){
		return tanggal;
	}

	public String getJudul(){
		return judul;
	}

	public String getIsi(){
		return isi;
	}
}