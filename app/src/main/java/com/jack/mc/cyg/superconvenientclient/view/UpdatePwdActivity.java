package com.jack.mc.cyg.superconvenientclient.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jack.mc.cyg.superconvenientclient.R;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygActivityUtil;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygToast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 16/6/12.
 */
public class UpdatePwdActivity extends BaseActivity implements View.OnClickListener {

    public static void start(Activity activity) {
        CygActivityUtil.startActivity(activity, UpdatePwdActivity.class);
    }

    @Bind(R.id.ltb_back_textview)
    TextView mTvTitleLeft;
    @Bind(R.id.ltb_title_textview)
    TextView mTvTitle;
    @Bind(R.id.ltb_right_textview)
    TextView mTvTitleRight;
    @Bind(R.id.aup_et_old_pwd)
    EditText mEtOldPwd;
    @Bind(R.id.aup_et_new_pwd)
    EditText mEtNewPwd;

    private String strOldPwd;
    private String strNewPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);

        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        mTvTitle.setText("修改密码");
        Drawable dwLeft = ContextCompat.getDrawable(this, R.mipmap.icon_back);
        dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
        mTvTitleLeft.setText("返回");
        mTvTitleLeft.setVisibility(View.VISIBLE);
        mTvTitleLeft.setCompoundDrawables(dwLeft, null, null, null);
        mTvTitleRight.setText("完成");
        mTvTitleRight.setVisibility(View.VISIBLE);
        mTvTitleLeft.setOnClickListener(this);
        mTvTitleRight.setOnClickListener(this);

        mEtOldPwd.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                strOldPwd = s.toString();
            }
        });
        mEtNewPwd.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                strNewPwd = s.toString();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ltb_back_textview:
                finish();
                break;
            case R.id.ltb_right_textview:
                CygToast.showToast("修改密码成功");
                break;
        }
    }
}
