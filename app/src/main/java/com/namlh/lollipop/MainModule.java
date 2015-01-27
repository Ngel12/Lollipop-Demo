package com.namlh.lollipop;

import com.namlh.lollipop.data.WebService;
import com.namlh.lollipop.dto.UserDto;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by namlh on 12/2/14.
 */
@Module(
        injects = {MainActivity.class,MainActivity2.class},
        library = true
)
public class MainModule {

//    private MyApp app;

//    public MainModule(MyApp myApp) {
//        app = myApp;
//    }

    @Provides
    @Singleton
    public WebService provideWebService(){
        OkHttpClient client = new OkHttpClient();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://tinhte-api.herokuapp.com")
                .setClient(new OkClient(client))
                .build();
        return restAdapter.create(WebService.class);
    }

    @Provides public Bus provideBus(){
        return new Bus("MyApp");
    }


    @Provides
    @Singleton
    public UserDto provideUserDto(){
        UserDto user = new UserDto();
        user.name = "NAM";
        user.address = "DI LINH - LAM DONG";
        user.hash = user.hashCode();
        return user;
    }
}
