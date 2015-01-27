package com.namlh.lollipop;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;

import com.namlh.lollipop.dto.UserDto;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by namlh on 12/2/14.
 */
public abstract class BaseActivity extends ActionBarActivity{

    //test rebase 2 - commit 1
    //test rebase 2 - commit 1
    //test rebase 2 - commit 2
    //test rebase 2 - commit 2


    //test rebase 2 - commit 3
    //test rebase 2 - commit 3
    //test rebase 2 - commit 3
    //test rebase 2 - commit 3

    //Test rebase 1 - commit 1


    @Inject
    UserDto user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.inject(this,this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
    }
}
