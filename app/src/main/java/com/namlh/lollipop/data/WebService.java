package com.namlh.lollipop.data;

import com.namlh.lollipop.dto.ListTTItems;
import com.namlh.lollipop.dto.TTItem;

import retrofit.Callback;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by namlh on 11/21/14.
 */
public interface WebService {

    @GET("/")
    Observable<ListTTItems> getData();
}
