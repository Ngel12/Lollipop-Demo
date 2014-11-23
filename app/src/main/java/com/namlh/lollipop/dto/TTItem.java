package com.namlh.lollipop.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by namlh on 11/21/14.
 */
public class TTItem {
    @SerializedName("id")
    public long id;
    @SerializedName("title")
    public String title;
    @SerializedName("viewer")
    public long viewer;

    @SerializedName("link_images")
    public String[] images;
}
