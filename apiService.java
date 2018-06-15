package com.example.jayhind.meditationapp.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/**
 * Created by Jay Hind on 6/13/2018.
 */
public interface apiService
{
        @FormUrlEncoded
        @POST("login.php")
//      Call<loginData> checkUser(@Field("uname") String Email, @Field("pass") String Password);
        Call<String> checkUser(@Field("uname") String Email, @Field("pass") String Password);

}
