package com.siva.demoapp.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Suresh on 1/4/2017.
 */
public class ApiCallList {
    private static ApiCallList ourInstance = new ApiCallList();

    public static ApiCallList getInstance() {
        return ourInstance;
    }


    // base url of the internal api calls
    final String baseURL = "http://www.divami.com";       //temporary, need to change

    /**
     * List of total internal api calls list goes here
     */
    Map<String, apiDetails> callsList = new HashMap<String, apiDetails>();
    private ApiCallList() {
        callsList.put("login", new apiDetails("/Authenticate", "GET"));
        callsList.put("logout", new apiDetails("/Logout", "POST"));
    }

    /**
     * This method will returns the api complete URL based on key
     * @param key is identifier of api call
     * @return complete url of api call
     */
    public String getApiURL(String key) {
        String apiUrl = null;
        apiUrl =  callsList.get(key).url;
        return baseURL + apiUrl;
    }

    /**
     * This method will returns the api method based on key
     * @param key is identifier of api call
     * @return method of api call
     */
    public String getApiMethod(String key) {
        String method = null;
        method =  callsList.get(key).method;
        return method;
    }
}

/**
 * API Object definition
 */
class apiDetails {
    String url, method;

    public apiDetails(String url, String method) {
        this.url = url;
        this.method = method;
    }
}