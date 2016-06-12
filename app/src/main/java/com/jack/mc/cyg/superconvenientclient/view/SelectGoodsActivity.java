package com.jack.mc.cyg.superconvenientclient.view;

import android.app.Activity;
import android.os.Bundle;

import com.jack.mc.cyg.superconvenientclient.R;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygActivityUtil;

/**
 * Created by Jack on 16/6/12.
 */
public class SelectGoodsActivity extends BaseActivity {

    public static void start(Activity activity) {
        CygActivityUtil.startActivity(activity, SelectGoodsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_goods);
    }
}
