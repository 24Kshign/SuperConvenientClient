package com.jack.mc.cyg.superconvenientclient.view.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jack.mc.cyg.superconvenientclient.R;
import com.jack.mc.cyg.superconvenientclient.cyg.fragment.CygFragment;
import com.jack.mc.cyg.superconvenientclient.view.CommonScanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 16/6/9.
 */
public class HomePageFragment extends CygFragment implements View.OnClickListener {

    @Bind(R.id.ltb_title_textview)
    TextView mTvTitle;
    @Bind(R.id.fh_rv_scan)
    RelativeLayout mRvScan;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_homepager;
    }

    @Override
    protected void onViewReallyCreated(View view) {
        super.onViewReallyCreated(view);

        ButterKnife.bind(this, view);
        initListener();
    }

    private void initListener() {
        mTvTitle.setText("主页");
        mRvScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fh_rv_scan:
                CommonScanActivity.start(getActivity());
                break;
        }
    }
}
