package com.example.phobia.final_project;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by Phobia on 4/26/2017.
 */

public class get_data extends AsyncTask<String,Void,String>{
    private Context context;

    public get_data(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[0]).build();
            Response response = okHttpClient.newCall(request).execute();
            String resJSON = response.body().string();
            Log.d("getdata", "getdata==>" + resJSON);

            return resJSON;
        }
        catch (Exception e) {
            Log.d("getdata", "getdata==>" + e.toString());
            return null;
        }
    }
}
