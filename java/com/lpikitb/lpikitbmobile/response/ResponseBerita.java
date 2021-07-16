package com.lpikitb.lpikitbmobile.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseBerita{

	@SerializedName("berita")
	private List<BeritaItem> berita;

	@SerializedName("status")
	private boolean status;

	public List<BeritaItem> getBerita(){
		return berita;
	}

	public boolean isStatus(){
		return status;
	}
}