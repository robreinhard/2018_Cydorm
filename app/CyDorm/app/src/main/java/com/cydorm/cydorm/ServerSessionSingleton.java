package com.cydorm.cydorm;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import okhttp3.*;
import java.net.CookieManager;

import okhttp3.internal.JavaNetCookieJar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A "session" manager of sorts, managing
 */
public class ServerSessionSingleton {
    private String user;
    private String pass;
    private CookieManager cm;

    private static ServerSessionSingleton sInst = null;


    private ServerSessionSingleton(String user, String pass) {
        this.user = user;
        this.pass = pass;
        login();
    }

    private ServerSessionSingleton() {

    }

    public static ServerSessionSingleton getInstance() {
        if(sInst == null) {
            sInst = new ServerSessionSingleton();
        }
        return sInst;
    }

    //Login

    private void login() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            String URL = "http://proj309-vc-05.misc.iastate.edu:8080/login";
            final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded");

            this.cm = new CookieManager();
            this.cm.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            OkHttpClient client = new OkHttpClient().newBuilder().cookieJar(new JavaNetCookieJar(this.cm))
                    .build();

            String json = "netID=" + this.user + "&password=" + this.pass;
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(URL)
                    .header("Accept", "*/*")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept-Encoding", "gzip, deflate")
                    .post(body)
                    .build();
            client.newCall(request).execute();
            Response response = client.newCall(request).execute();

        } catch (Exception e) {
            Log.e("REQUEST", "THere was an error making the rquest", e);
        }
    }

    public void login(String user, String pass) {
        this.user = user;
        this.pass = pass;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            String URL = "http://proj309-vc-05.misc.iastate.edu:8080/login";
            final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded");


            this.cm = new CookieManager();
            this.cm.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            OkHttpClient client = new OkHttpClient().newBuilder().cookieJar(new JavaNetCookieJar(this.cm))
                    .build();

            String json = "netID=" + user + "&password=" + pass;
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(URL)
                    .header("Accept", "*/*")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept-Encoding", "gzip, deflate")
                    .post(body)
                    .build();
            client.newCall(request).execute();
            Response response = client.newCall(request).execute();
            Log.i("LOGIN", "Login successful");
            Log.d("LOGIN", response.body().string());

        } catch (Exception e) {
            Log.e("REQUEST", "THere was an error making the rquest", e);
        }
    }

    public String getUser() {
        return this.user;
    }

    public String getPass() {
        return this.pass;
    }

    public CookieManager getCookieManager() {
        return this.cm;
    }
}
