package com.cdsf.locman.yzy.util;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.cdsf.locman.yzy.application.BaseApplication;

/**
 * Created by wuwenliang on 2017/5/17.
 */

public class MapApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
}
