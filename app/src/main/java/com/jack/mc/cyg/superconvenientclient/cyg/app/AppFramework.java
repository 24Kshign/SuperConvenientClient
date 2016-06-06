package com.jack.mc.cyg.superconvenientclient.cyg.app;


import com.jack.mc.cyg.superconvenientclient.cyg.url.HttpServletAddress;

/**
 * Created by Jack on 16/5/29.
 */
public class AppFramework {

    private AppFramework() {
    }

    public static void init() {
        // Http服务器地址
        HttpServletAddress.getInstance().setOnlineAddress("http://www.best8023.com:8080");

    }
}
