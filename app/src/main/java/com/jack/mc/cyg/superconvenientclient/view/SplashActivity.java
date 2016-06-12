package com.jack.mc.cyg.superconvenientclient.view;

import android.os.Bundle;

/**
 * Created by Jack on 16/6/11.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivity.start(this, null);
        finish();
    }
}
