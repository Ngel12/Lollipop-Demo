package com.namlh.lollipop;

import com.namlh.lollipop.dto.ListTTItems;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by namlh on 11/21/14.
 */
public interface WebService {

    @GET("/")
    void getData(Callback<ListTTItems> callback);
}
