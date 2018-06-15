package com.example.jayhind.meditationapp.Api;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Jay Hind on 6/13/2018.
 */
public class apiClient {
    static Retrofit r=null;
    public static Retrofit getClient() {
        if(r==null)
        {
            r=new Retrofit.Builder().baseUrl("http://192.168.43.55/MeditationApp/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return r;
    }
}
