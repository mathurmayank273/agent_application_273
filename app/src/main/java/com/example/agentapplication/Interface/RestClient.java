package com.example.agentapplication.Interface;

import android.content.Context;

import java.io.IOException;


public class RestClient extends AbstractRestClient
{
    public static final String BASE_URL = "https://itunes.apple.com";

    //public static final String login = "TMSCustLogin?mobileNo=";
    private Interface DataService;

    public RestClient(Context context) throws IOException {
        super(context, BASE_URL);
    }
    @Override
    public void initApi()
    {
        DataService = restAdapter.create(Interface.class);
    }

    public Interface getDataService()
    {
        return DataService;
    }
}
