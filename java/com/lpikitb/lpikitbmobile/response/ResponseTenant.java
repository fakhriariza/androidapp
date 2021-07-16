package com.lpikitb.lpikitbmobile.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseTenant{

	@SerializedName("tenant")
	private List<TenantItem> tenant;

	@SerializedName("status")
	private boolean status;

	public List<TenantItem> getTenant(){
		return tenant;
	}

	public boolean isStatus(){
		return status;
	}
}