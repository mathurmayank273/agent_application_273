package com.example.agentapplication.Interface;





import com.example.agentapplication.Model.JBTracker;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface Interface
{




    @GET("/search?term=all")
    void tractorsBrand(
            Callback<JBTracker> hello);


}
