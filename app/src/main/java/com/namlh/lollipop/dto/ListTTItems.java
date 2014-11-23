package com.namlh.lollipop.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by namlh on 11/21/14.
 */
public class ListTTItems {
    @SerializedName("posts")
    public List<TTItem> posts;
}
