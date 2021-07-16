package com.lpikitb.lpikitbmobile.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseModul{

	@SerializedName("modul")
	private List<ModulItem> modul;

	@SerializedName("status")
	private boolean status;

	public List<ModulItem> getModul(){
		return modul;
	}

	public boolean isStatus(){
		return status;
	}
}