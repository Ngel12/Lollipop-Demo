package com.namlh.lollipop.data;

import com.namlh.lollipop.dto.ListTTItems;
import com.namlh.lollipop.dto.TTItem;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by namlh on 12/4/14.
 */
@Singleton
public class TTItemDatabase {

    private final WebService webService;

    @Inject
    public TTItemDatabase(WebService webService){
        this.webService = webService;
    }

    public Observable<TTItem> getData(){
        return webService.getData().flatMap(new Func1<ListTTItems, Observable<TTItem>>() {
            @Override
            public Observable<TTItem> call(ListTTItems items) {
                return Observable.from(items.posts);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
          ;
    }

}
