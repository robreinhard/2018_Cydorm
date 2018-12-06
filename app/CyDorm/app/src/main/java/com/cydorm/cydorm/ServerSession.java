package com.cydorm.cydorm;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A "session" manager of sorts, managing
 */
public class ServerSession {
    private Context context;
    private String user;
    private String pass;
    private String response;


    public ServerSession(String user, String pass, Context context) {
        this.user = user;
        this.pass = pass;
        this.context = context;
        login();
    }

    //Login

    public void login() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            //String URL = "https://httpbin.org/post";
            //String URL = "http://proj309-vc-05.misc.iastate.edu:8080/login";
            String URL = "http://localhost:8080/login";
             final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded");
                    //= MediaType.parse("application/json; charset=utf-8");

            OkHttpClient client = new OkHttpClient();

            String json = "netID=test&password=test";
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();
            client.newCall(request).execute();
            Response response = client.newCall(request).execute();
            Log.i("REQUEST", "Here is the request" + response.body().string());

        } catch (Exception e) {
            Log.e("REQUEST", "THere was an error making the rquest", e);
        }

            /*
        try {

            JSONObject payload = new JSONObject();
            payload.put("netID", "test");
            payload.put("password", "test");

            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, URL, payload,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("REQUEST", response.toString());
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("REQUEST", "Error making the request", error);
                        }

                    }) {

                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded; charset=UTF-8";
                }

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("netID", "test");
                    params.put("password", "test");
                    return params;
                }
            };

            Log.d("REQUEST", jor.toString());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String r) {
                        try {
                            //VolleyLog.v("Response: %n %s", response);
                            System.out.printf("\n\n\n XXXXXXXXXXXXXXXXXXXXXXXXXX Response is: %s \n\n\n", r);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("netID", "test");
                params.put("password", "test");

                return params;
            }
        };
        VolleySingleton.getInstance(this.context).addToRequestQueue(jor,"LOGIN");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
    }
}
