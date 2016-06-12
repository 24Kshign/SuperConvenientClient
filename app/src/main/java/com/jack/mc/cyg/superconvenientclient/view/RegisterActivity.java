package com.jack.mc.cyg.superconvenientclient.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jack.mc.cyg.superconvenientclient.R;
import com.jack.mc.cyg.superconvenientclient.cyg.http.NetCallBack;
import com.jack.mc.cyg.superconvenientclient.cyg.http.RequestUtils;
import com.jack.mc.cyg.superconvenientclient.cyg.url.MyUrl;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygActivityUtil;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygLog;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygToast;
import com.jack.mc.cyg.superconvenientclient.view.util.MyCode;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Jack on 16/6/11.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    public static void start(Activity activity) {
        CygActivityUtil.startActivity(activity, RegisterActivity.class);
    }

    @Bind(R.id.ltb_back_textview)
    TextView mTvTitleLeft;
    @Bind(R.id.ltb_title_textview)
    TextView mTvTitle;
    @Bind(R.id.ltb_right_textview)
    TextView mTvTitleRight;

    @Bind(R.id.ar_et_phone)
    EditText mEtPhone;
    @Bind(R.id.ar_et_pwd)
    EditText mEtPwd;
    @Bind(R.id.ar_et_code)
    EditText mEtCode;
    @Bind(R.id.ar_btn_get_code)
    Button mBtnGetCode;

    private String strPhone;
    private String strPwd;
    private String strCode;
    private boolean isRequestingCode;     //判断是否在请求验证码

    private TimeCount time = null;
    private EventHandler eventHandler;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MyCode.SMSSDK_MSG_INFO) {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                CygLog.debug("event=" + event);
                CygLog.debug("result=" + result);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        LoginActivity.start(RegisterActivity.this, strPhone);
                        finish();
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        CygToast.showToast(R.string.smssdk_virificaition_code_sent);
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        time = new TimeCount(60000, 1000);
        initSDK();
        initListener();
    }

    private void initSDK() {
        SMSSDK.initSDK(this, MyCode.SMSAPP_KEY, MyCode.SMSAPP_SECRET, true);
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = mHandler.obtainMessage();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                msg.what = MyCode.SMSSDK_MSG_INFO;
                mHandler.sendMessage(msg);
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }

    private void initListener() {
        mTvTitle.setText("注册");
        Drawable dwLeft = ContextCompat.getDrawable(this, R.mipmap.icon_back);
        dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
        mTvTitleLeft.setText("返回");
        mTvTitleLeft.setVisibility(View.VISIBLE);
        mTvTitleLeft.setCompoundDrawables(dwLeft, null, null, null);
        mTvTitleRight.setText("完成");
        mTvTitleRight.setVisibility(View.VISIBLE);
        mTvTitleLeft.setOnClickListener(this);
        mTvTitleRight.setOnClickListener(this);
        mBtnGetCode.setOnClickListener(this);

        mEtPhone.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                strPhone = s.toString();
                mBtnGetCode.setEnabled(!isRequestingCode && isMobileNO(strPhone));
            }
        });
        mEtPwd.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                strPwd = s.toString();
            }
        });
        mEtCode.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                strCode = s.toString();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ltb_back_textview:
                finish();
                break;
            case R.id.ar_btn_get_code:
                verifyPhone(strPhone);
                break;
            case R.id.ltb_right_textview:
//                SMSSDK.submitVerificationCode("86", strPhone, strCode);
                LoginActivity.start(this, strPhone);
                finish();
                break;
        }
    }

    private void verifyPhone(final String strPhone) {
        String url = MyUrl.getUrl("/OnlineShopWeb/shop_validate_regist.do");
        RequestParams params = new RequestParams();
        params.put("shop_username", strPhone);
        RequestUtils.ClientPost(url, params, new NetCallBack() {
            @Override
            public void onMySuccess(byte[] response) {
                try {
                    JSONObject jsonObject = new JSONObject(new String(response));
                    if (jsonObject.getString("status").equals(MyCode.RETURN_SUCCESS)) {
                        //通过SDK发送短信验证
                        SMSSDK.getVerificationCode("86", strPhone);
                        time.start();
                        isRequestingCode = true;
                        mBtnGetCode.setEnabled(false);
                    } else if (jsonObject.getString(MyCode.RETURN_ALREADY_EXIST).equals("0")) {
                        CygToast.showToast(jsonObject.getString("msg"));
                    } else {
                        CygToast.showToast(jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyFailure(byte[] response, Throwable throwable) {
                CygLog.debug("link servlet failure------->" + new String(response));
            }
        });
    }

    //改变验证码中的时间
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mBtnGetCode.setEnabled(false);
            mBtnGetCode.setText(millisUntilFinished / 1000 + "s" + "后重发");
        }

        @Override
        public void onFinish() {
            mBtnGetCode.setEnabled(true);
            mBtnGetCode.setText("重新获取");
            isRequestingCode = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁回调监听接口
        SMSSDK.unregisterAllEventHandler();
    }
}
