package com.jack.mc.cyg.superconvenientclient.view.fragment;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jack.mc.cyg.superconvenientclient.R;
import com.jack.mc.cyg.superconvenientclient.cyg.fragment.CygFragment;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygToast;
import com.jack.mc.cyg.superconvenientclient.view.SettingActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 16/6/9.
 */
public class MyselfFragment extends CygFragment implements View.OnClickListener {

    @Bind(R.id.ltb_title_textview)
    TextView mTvTitle;
    @Bind(R.id.ltb_right_textview)
    TextView mTvTitleRight;
    @Bind(R.id.fm_rv_integral)
    RelativeLayout mRvIntegral;
    @Bind(R.id.fm_lv_coupon)
    LinearLayout mLvCoupon;
    @Bind(R.id.fm_lv_cheap_price)
    LinearLayout mLvCheapPrice;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_myself;
    }

    @Override
    protected void onViewReallyCreated(View view) {
        super.onViewReallyCreated(view);

        ButterKnife.bind(this, view);
        initListener();
    }

    private void initListener() {
        mTvTitle.setText("我的");
        Drawable dwRight = ContextCompat.getDrawable(getContext(), R.mipmap.icon_setting);
        dwRight.setBounds(0, 0, dwRight.getMinimumWidth(), dwRight.getMinimumHeight());
        mTvTitleRight.setVisibility(View.VISIBLE);
        mTvTitleRight.setCompoundDrawables(null, null, dwRight, null);
        mTvTitleRight.setOnClickListener(this);
        mRvIntegral.setOnClickListener(this);
        mLvCoupon.setOnClickListener(this);
        mLvCheapPrice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ltb_right_textview:
                SettingActivity.start(getActivity());
                break;
            case R.id.fm_rv_integral:
            case R.id.fm_lv_coupon:
            case R.id.fm_lv_cheap_price:
                CygToast.showToast("暂未开通此功能");
                break;
        }
    }
}
