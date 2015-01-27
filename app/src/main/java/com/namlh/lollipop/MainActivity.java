package com.namlh.lollipop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.namlh.lollipop.data.TTItemDatabase;
import com.namlh.lollipop.data.WebService;
import com.namlh.lollipop.dto.ListTTItems;
import com.namlh.lollipop.dto.TTItem;

import javax.inject.Inject;

import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;


public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.mainView)
    RecyclerView mRecyclerView;

    @InjectView(R.id.top_toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.refreshContainer)
    SwipeRefreshLayout refreshLayout;


    private LinearLayoutManager mLayoutManager;
    private CardAdapter mAdapter;

    @Inject
    TTItemDatabase database;

    Observable<TTItem> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"Hash: "+user.hash,Toast.LENGTH_LONG).show();

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_launcher);
        mRecyclerView.setHasFixedSize(true);

        refreshLayout.setOnRefreshListener(this);
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getTheme().resolveAttribute(R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        refreshLayout.setProgressViewEndTarget(false,actionBarHeight);

        mAdapter = new CardAdapter();
        mRecyclerView.setAdapter(mAdapter);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        webService.getData(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Observable<TTItem> observable = database.getData();

        observable.subscribe(new Observer<TTItem>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TTItem ttItem) {
                mAdapter.addItem(ttItem);
            }
        });

        observable.subscribe(new Observer<TTItem>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TTItem ttItem) {
                Log.e("oNNext",ttItem.title);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,MainActivity2.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void success(ListTTItems items, Response response) {
//        mAdapter.addItems(items.posts);
//        Observable.just(items)
//                .flatMap(new Func1<ListTTItems, Observable<TTItem>>() {
//                    @Override
//                    public Observable<TTItem> call(ListTTItems items) {
//                        return Observable.from(items.posts);
//                    }
//                })
//                .subscribe(new Observer<TTItem>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(TTItem ttItem) {
//                        mAdapter.addItem(ttItem);
//                    }
//                });
//        bus.post(items);
//        refreshLayout.setRefreshing(false);
//    }

//    @Subscribe
//    public void getItemsSuccess(ListTTItems items){
//        mAdapter.addItems(items.posts);
//        refreshLayout.setRefreshing(false);
//    }

//    @Override
//    public void failure(RetrofitError error) {
//        Log.e("Request error",error.getMessage(),error.fillInStackTrace());
//        refreshLayout.setRefreshing(false);
//    }

    @Override
    public void onRefresh() {
        mAdapter.clear();
//        webService.getData(this);
    }

}
