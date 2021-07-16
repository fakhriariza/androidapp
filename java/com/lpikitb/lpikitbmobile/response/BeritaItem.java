package com.lpikitb.lpikitbmobile.response;

import com.google.gson.annotations.SerializedName;

public class BeritaItem{

	@SerializedName("penulis")
	private String penulis;

	@SerializedName("foto")
	private String foto;

	@SerializedName("id")
	private String id;

	@SerializedName("judul_berita")
	private String judulBerita;

	@SerializedName("tanggal_posting")
	private String tanggalPosting;

	@SerializedName("isi_berita")
	private String isiBerita;

	public String getPenulis(){
		return penulis;
	}

	public String getFoto(){
		return foto;
	}

	public String getId(){
		return id;
	}

	public String getJudulBerita(){
		return judulBerita;
	}

	public String getTanggalPosting(){
		return tanggalPosting;
	}

	public String getIsiBerita(){
		return isiBerita;
	}
}