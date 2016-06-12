package com.jack.mc.cyg.superconvenientclient.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jack.mc.cyg.superconvenientclient.R;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygActivityUtil;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygStringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 16/6/11.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static void start(Activity activity, String strPhone) {
        Bundle bundle = new Bundle();
        bundle.getString("phone", strPhone);
        CygActivityUtil.startActivity(activity, LoginActivity.class, bundle);
    }

    @Bind(R.id.ltb_title_textview)
    TextView mTvTitle;
    @Bind(R.id.ltb_right_textview)
    TextView mTvTitleRight;

    @Bind(R.id.al_et_phone)
    EditText mEtPhone;
    @Bind(R.id.al_et_pwd)
    EditText mEtPwd;
    @Bind(R.id.al_btn_login)
    Button mBtnLogin;

    private String strPhone;
    private String strPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        strPhone = getIntent().getStringExtra("phone");
        initListener();
    }

    private void initListener() {
        mTvTitleRight.setText("注册");
        mTvTitle.setText("登录");
        mTvTitleRight.setVisibility(View.VISIBLE);
        mTvTitleRight.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);

        if (strPhone != null) {
            mEtPhone.setText(strPhone);
            mEtPhone.setSelection(strPhone.length());
        }

        mEtPhone.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                strPhone = s.toString();
                WhetherCanLogin();    //登陆按钮是否能点击
            }
        });
        mEtPwd.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                strPwd = s.toString();
                WhetherCanLogin();
            }
        });
    }

    private void WhetherCanLogin() {
        if (isMobileNO(strPhone) && !CygStringUtil.isEmpty(strPwd)) {
            mBtnLogin.setEnabled(true);
        } else {
            mBtnLogin.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ltb_right_textview:
                RegisterActivity.start(this);
                break;
            case R.id.al_btn_login:
                MainActivity.start(this);
                finish();
                break;
        }
    }
}
