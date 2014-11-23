package com.namlh.lollipop;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.namlh.lollipop.dto.ListTTItems;
import com.squareup.okhttp.OkHttpClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity implements Callback<ListTTItems>, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.mainView)
    RecyclerView mRecyclerView;

    @InjectView(R.id.top_toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.refreshContainer)
    SwipeRefreshLayout refreshLayout;


    private LinearLayoutManager mLayoutManager;
    private CardAdapter mAdapter;
    private WebService webService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initWebService();

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
        webService.getData(this);
    }

    private void initWebService(){
        OkHttpClient client = new OkHttpClient();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://tinhte-api.herokuapp.com")
                .setClient(new OkClient(client))
                .build();
        webService = restAdapter.create(WebService.class);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void success(ListTTItems items, Response response) {
        mAdapter.addItems(items.posts);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e("Request error",error.getMessage(),error.fillInStackTrace());
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mAdapter.clear();
        webService.getData(this);
    }

}
