package com.cydorm.cydorm;

import android.util.Log;
import com.techdew.stomplibrary.Stomp;
import com.techdew.stomplibrary.StompClient;
import java.net.HttpCookie;
import org.java_websocket.WebSocket;

import java.util.Collections;
import java.util.Map;

/**
 * Stomp connection class to manage conneting to a stomp instance
 * @author Malcolm Boyd
 */
public class StompConnection {

    public StompClient sc;
    private String sessionID;
    private String url = "http://proj309-vc-05.misc.iastate" +
            ".edu:8080/gs-guide-websocket/websocket";

    /** New stomp connection based on sessionID*/
    public StompConnection(String s) {

        for(HttpCookie c : ServerSessionSingleton.getInstance().getCookieManager().getCookieStore().getCookies()) {
            if(c.getName().equals("JSESSIONID")) {
                this.sessionID = c.getValue();
                Log.d("STOMP", "Just set stomp connection session id to " + this.sessionID);
            }
        }
        initStomp();
    }

    private void initStomp() {

        Map<String, String> headers = Collections.singletonMap("Cookie",
                "JSESSIONID=" + this.sessionID);

        this.sc = Stomp.over(WebSocket.class, url, headers);
        this.sc.connect();

        this.sc.lifecycle()
                .subscribe(lifecycleEvent -> {
                    switch (lifecycleEvent.getType()) {
                        case OPENED:
                            Log.d("STOMP","Stomp connection opened");
                            break;
                        case ERROR:
                            Log.d("STOMP","Stomp connection error");
                            break;
                        case CLOSED:
                            Log.d("STOMP","Stomp connection closed");
                    }
                });
    }

    /** Send a request to the endpoint 
     * @param String endpoint the stomp endpoint to send the request to
     * @param String json The json payload
     */
    public void makeRequest(String endpoint, String json) {
        this.sc.send(endpoint, json).subscribe();
    }
}
