package com.example.agentapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agentapplication.Adapter.TrackerAdapter;
import com.example.agentapplication.Interface.RestClient;
import com.example.agentapplication.Model.JBTracker;
import com.example.agentapplication.Model.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity  extends Activity {

    ListView list_view;
    EditText editsearch;
    Button buttonBack;
    ProgressDialog progressDialog;

    ArrayList<Result> resultDetails;
    TrackerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view = (ListView) findViewById(R.id.listview) ;
        editsearch = (EditText) findViewById(R.id.ediittext_serach);


        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

        API();
    }

    public void API()
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(("Fetching Information..."));
        progressDialog.setCancelable(false);
        progressDialog.show();


        try {
            new RestClient(getApplicationContext()).getDataService().tractorsBrand(new Callback<JBTracker>() {

                @Override
                public void success(JBTracker productHistory, Response response) {

                    progressDialog.dismiss();

                    if (productHistory.getResultCount().equals(50)) {
    //                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                        resultDetails = (ArrayList<Result>) productHistory.getResults();
                        Log.d("value", resultDetails.toString());

                        adapter = new TrackerAdapter(MainActivity.this, resultDetails);
                        list_view.setAdapter(adapter);
                    }
                    else if (productHistory != null && productHistory.getResultCount().equals(0))
                    {
    //                    listView.setVisibility(GONE);
    //                    layout_nodata.setVisibility(VISIBLE);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    progressDialog.cancel();
    //                Intent i = new Intent(getApplicationContext(), NoInternet.class);
    //                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    //                startActivity(i);
    //                finish();
                    Toast.makeText(getApplicationContext(), "Network Error occurs,Try again", Toast.LENGTH_LONG).show();
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}