package com.lpikitb.lpikitbmobile.Network;


import com.lpikitb.lpikitbmobile.response.ResponseBerita;
import com.lpikitb.lpikitbmobile.response.ResponseModul;
import com.lpikitb.lpikitbmobile.response.ResponseTenant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    //@TIPEMETHOD("API_END_POINT")
    @GET("tampil_berita.php")
    Call<ResponseBerita> request_show_all_berita();
    // <ModelData> nama_method()
     @GET("tampil_tenant.php")
    Call<ResponseTenant> request_show_all_tenant();
     @GET ("tampil_berita.php")
     Call<ResponseModul> request_show_all_modul();


    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseBody> signup(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama_lengkap") String namalengkap,
            @Field("email") String email,
            @Field("no_hp") String nomorHP
    );

}