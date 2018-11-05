package com.cydorm.cydorm;

import android.util.Log;
import com.techdew.stomplibrary.Stomp;
import com.techdew.stomplibrary.StompClient;
import org.java_websocket.WebSocket;

import java.util.Collections;
import java.util.Map;

public class StompConnection {

    public StompClient sc;
    private String sessionID;
    private String url = "http://proj309-vc-05.misc.iastate" +
            ".edu:8080/gs-guide-websocket/websocket";

    public StompConnection(String sessionID) {
        this.sessionID = sessionID;
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

    public void makeRequest(String endpoint, String json) {
        this.sc.send(endpoint, json).subscribe();
    }
}
