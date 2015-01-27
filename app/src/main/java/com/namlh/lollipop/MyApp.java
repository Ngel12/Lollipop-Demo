package com.namlh.lollipop;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

/**
 * Created by namlh on 12/2/14.
 */
public class MyApp extends Application {

    //Hello world
    //hello good morning
    //hi/
    //how are you

    //hello world - create conflic
    //hi good morning

    ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new ScreenModule(this));
    }

    public static MyApp getInstance(Context context){
        return (MyApp) context.getApplicationContext();
    }

    public static void inject(Context context,Object o){
        getInstance(context.getApplicationContext()).objectGraph.inject(o);
    }

    //Pull request 2
    // easy to create pull request
}
