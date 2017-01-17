package com.siva.demoapp.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Suresh on 1/4/2017.
 */
public class ApiCallList {
    private static ApiCallList instance = new ApiCallList();

    public static ApiCallList getInstance() {
        return instance;
    }

    // Server Base URL
    final String baseURL = "http://www.divami.com";

    Map<String, ApiDetails> callsList = new HashMap<String, ApiDetails>();

    /**
     * List of API calls
     */
    private ApiCallList() {
        callsList.put("login", new ApiDetails("/authenticate"));
        callsList.put("logout", new ApiDetails("/logout"));
    }

    /**
     * This method will returns the api complete URL based on key
     * @param key is identifier of api call
     * @return complete url of api call
     */
    public String getApiURL(String key) {
        ApiDetails api =  callsList.get(key);
        if(api != null) {
            return baseURL + api.url;
        }
        return key;
    }
}

/**
 * API Object definition
 */
class ApiDetails {
    String url;
    public ApiDetails(String url) {
        this.url = url;
    }
}