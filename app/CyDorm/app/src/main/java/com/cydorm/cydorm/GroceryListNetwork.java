package com.cydorm.cydorm;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * GroceryList networking utility class
 *
 * @author Malcolm Boyd
 */
public class GroceryListNetwork {//implements GroceryListNetworkInteface {
    /* Logging String */ private static final String LOG_TAG = "GLNET";
    private String url;
    private String port;
    private Context context;

    /** @param String url The server url
     * @param String port Server port
     */
    public GroceryListNetwork(String url, String port) {
        this.url = url;
        this.port = port;
    }

    /**
     * The default grocery list network and its connections
     * @param Context android context
     **/
    public GroceryListNetwork(Context context) {
        this.url = "http://proj309-vc-05.misc.iastate.edu";
        this.port = "8080";
        this.context = context;
    }

    /** Authenticate with the server
     */
    public void authenticate() {

    }

    /** Callback method calling remove and add on the old and n list items 
     * @param GroceryItem The old version of the grocery item
     * @param GroceryItem The new version of the grocery item
     */
    public void updateListItem(GroceryItem old, GroceryItem n) {
        //Remove the list item
        this.removeListItem(old);
        this.addListItem(n);
    }

    /**
     * Removes the grocery list item from the server
     * @param GroceryItem the item to remove
     */
    public void removeListItem(GroceryItem i) {
         String endpoint = "deleteGroceryItem?id=" + i.getID();
        JsonObjectRequest req = new JsonObjectRequest(getRequestUrl(endpoint),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        VolleySingleton.getInstance(this.context).addToRequestQueue(req,
                "GROCERY");
    }

    /** Adds grocery list item to the server's list
     * @param GroceryItem The item to add
     */
    public void addListItem(GroceryItem i) {
        String endpoint = "addGroceryItem?groceryItem=" + i.getItem() +
                "&groceryPrice=" + i.getPrice() + "&approved=T&firstName=" + i.getAuthorFirst() + "&lastName=" + i.getAuthorLast();
        JsonObjectRequest req = new JsonObjectRequest(getRequestUrl(endpoint),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        VolleySingleton.getInstance(this.context).addToRequestQueue(req,
                "GROCERY");
    }

    public List<GroceryItem> getGroceryList(Response.Listener<JSONArray> listener) {
        final ArrayList<GroceryItem> list = new ArrayList<>();
        Log.d(LOG_TAG, "Now we are in the response 0");
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (getRequestUrl("allGroceries"), listener,
                        new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(LOG_TAG, "An error was thrown: " + error.toString());
                    }
                });

        VolleySingleton.getInstance(this.context).addToRequestQueue(jsonObjectRequest,
                "GROCERY");
        Log.d(LOG_TAG, "NOw returning");
        return list;
    }

    /**
     * Helper method to create the url to request to out of the passed url and 
     * port
     *
     * @param String The endpoint to connect to
     */
    private String getRequestUrl(String endpoint) {
        String req = url;

        //If a port is specified
        if(!port.equals("")) {
            req += ":" + this.port + "/";
        }
        req += endpoint;

        Log.d(LOG_TAG, "The returned url is : " + req);
        return req;
    }
}
