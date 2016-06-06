package com.jack.mc.cyg.superconvenientclient.cyg.url;

/**
 * Created by Jack on 16/5/29.
 */
public class MyUrl {

    private static String getDomain() {
        return HttpServletAddress.getInstance().getAddress();
    }

    public static String getUrl(String url) {
        return getDomain() + url;
    }
}