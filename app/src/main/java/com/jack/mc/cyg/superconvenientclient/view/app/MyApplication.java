package com.jack.mc.cyg.superconvenientclient.view.app;

import com.jack.mc.cyg.superconvenientclient.cyg.app.AppFramework;
import com.jack.mc.cyg.superconvenientclient.cyg.app.CygApplication;

/**
 * Created by Jack on 16/5/29.
 */
public class MyApplication extends CygApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AppFramework.init();
    }
}
