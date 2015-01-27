package com.namlh.lollipop;

import android.content.Context;

import com.namlh.lollipop.data.DataModule;
import com.namlh.lollipop.dto.UserDto;

import dagger.Module;
import dagger.Provides;

/**
 * Created by namlh on 12/2/14.
 */
@Module(
        includes = {MainModule.class, DataModule.class}
)
public class ScreenModule {

    private  Context context;
    public ScreenModule(Context mainActivity) {
        context = mainActivity;
    }

}
