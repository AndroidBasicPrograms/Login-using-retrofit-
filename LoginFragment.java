package com.example.jayhind.meditationapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jayhind.meditationapp.Api.apiClient;
import com.example.jayhind.meditationapp.Api.apiService;
import com.example.jayhind.meditationapp.Model.user;
import com.example.jayhind.meditationapp.user.Activity.userDrawerActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    Button loginbtn;
    private Context context;
    apiService service;

    EditText etemail,etpass;
    private String email,pass;

    public LoginFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        context=getActivity();
        loginbtn=view.findViewById(R.id.login);
        loginbtn.setOnClickListener(this);
        service= apiClient.getClient().create(apiService.class);
        etemail=view.findViewById(R.id.username);
        etpass=view.findViewById(R.id.password);
        readdata();
        return view;
    }
    @Override
    public void onClick(View view) {
        email=etemail.getText().toString();
        pass=etpass.getText().toString();
        if(email.isEmpty() && pass.isEmpty())
        {
            Toast.makeText(context, "Enter details properly..", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Call<String> call = service.checkUser(email, pass);
            call.enqueue(new Callback<String>()
            {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String result;
                    result = response.body();
                    checkUser(result);
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
            });
        }
    }

    private void checkUser(String result) {
        if(result.equals("Wrong username") || result.equals("Wrong pass"))
        {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
        else
        {
            SharedPreferences sp=getActivity().getSharedPreferences("loginData",MODE_PRIVATE);
            SharedPreferences.Editor e=sp.edit();
            e.putString("id",result);
            e.commit();
            Intent i = new Intent(context, userDrawerActivity.class);
            startActivity(i);

        }
    }

    private void readdata() {
        SharedPreferences s=getActivity().getSharedPreferences("loginData",MODE_PRIVATE);
        String ss=s.getString("id",null);
        if(ss!=null)
        {
            Intent i = new Intent(context, userDrawerActivity.class);
            startActivity(i);
        }
    }
}
