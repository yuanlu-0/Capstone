package com.yuanlu.capstone;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class ApiRequest<Result> extends AsyncTask<Void, Void, Result> {

    private String mRequest;
    private Exception mException;
    private RequestCallback<Result> mRequestCallback;

    public ApiRequest(String request, RequestCallback<Result> requestCallback) {
        mRequest = request;
        mRequestCallback = requestCallback;
    }

    @Override
    protected Result doInBackground(Void... params) {
        Result result = null;
        HttpURLConnection urlConnection = null;
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL(mRequest);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response = response.append(inputLine);
            }
            try {
                result = process(new JSONArray(response.toString()));
            } catch (JSONException e) {
                e.printStackTrace();
                mException = e;
            }
        } catch (IOException e) {
            e.printStackTrace();
            mException = e;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(@NonNull Result result) {
        if (mException == null) {
            mRequestCallback.onSuccess(result);
        } else {
            mRequestCallback.onError();
        }
    }

    public abstract Result process(JSONArray jsonArray) throws JSONException;

    public interface RequestCallback<Result> {
        void onSuccess(Result result);
        void onError();
    }
}
