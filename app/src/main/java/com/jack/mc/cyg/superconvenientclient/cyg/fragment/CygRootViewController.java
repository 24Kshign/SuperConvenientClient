package com.jack.mc.cyg.superconvenientclient.cyg.fragment;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jack.mc.cyg.superconvenientclient.cyg.util.CygViewUtil;


/**
 *
 */
public class CygRootViewController {

    public CygRootViewController(View rootView) {
        mRootView = rootView;
    }

    private View mRootView;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 查找View

    public View findRootView() { return mRootView; }
    public View findViewById(int id) { return mRootView.findViewById(id); }

    public ViewStub findViewStub(int id) { return CygViewUtil.findViewStub(mRootView, id); }
    public TextView findTextView(int id) { return CygViewUtil.findTextView(mRootView, id); }
    public Button findButton(int id) { return CygViewUtil.findButton(mRootView, id); }
    public ImageView findImageView(int id) { return CygViewUtil.findImageView(mRootView, id); }
    public EditText findEditText(int id) { return CygViewUtil.findEditText(mRootView, id); }
    public ViewGroup findViewGroup(int id) { return CygViewUtil.findViewGroup(mRootView, id); }
    public LinearLayout findLinearLayout(int id) { return CygViewUtil.findLinearLayout(mRootView, id); }
    public RelativeLayout findRelativeLayout(int id) { return CygViewUtil.findRelativeLayout(mRootView, id); }
    public FrameLayout findFrameLayout(int id) { return CygViewUtil.findFrameLayout(mRootView, id); }
    public ScrollView findScrollView(int id) { return CygViewUtil.findScrollView(mRootView, id); }
    public ListView findListView(int id) { return CygViewUtil.findListView(mRootView, id); }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 给view设置listener

    public final void setOnClickListener(int id, @Nullable View.OnClickListener listener) {
        findViewById(id).setOnClickListener(listener);
    }

    public final void setOnTouchListener(int id, @Nullable View.OnTouchListener listener) {
        findViewById(id).setOnTouchListener(listener);
    }
}
