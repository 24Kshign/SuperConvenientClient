package com.jack.mc.cyg.superconvenientclient.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jack.mc.cyg.superconvenientclient.R;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygActivityUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 16/6/12.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    public static void start(Activity activity) {
        CygActivityUtil.startActivity(activity, SettingActivity.class);
    }

    @Bind(R.id.ltb_back_textview)
    TextView mTvTitleLeft;
    @Bind(R.id.ltb_title_textview)
    TextView mTvTitle;
    @Bind(R.id.as_tv_update_pwd)
    TextView mTvUpdatePwd;
    @Bind(R.id.as_btn_exit)
    Button mBtnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        mTvTitle.setText("设置");
        Drawable dwLeft = ContextCompat.getDrawable(this, R.mipmap.icon_back);
        dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
        mTvTitleLeft.setText("返回");
        mTvTitleLeft.setVisibility(View.VISIBLE);
        mTvTitleLeft.setCompoundDrawables(dwLeft, null, null, null);
        mTvTitleLeft.setOnClickListener(this);
        mTvUpdatePwd.setOnClickListener(this);
        mBtnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ltb_back_textview:
                finish();
                break;
            case R.id.as_btn_exit:
                LoginActivity.start(this, null);
                finish();
                break;
            case R.id.as_tv_update_pwd:
                UpdatePwdActivity.start(this);
                break;
        }
    }
}
