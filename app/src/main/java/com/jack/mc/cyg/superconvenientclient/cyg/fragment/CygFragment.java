package com.jack.mc.cyg.superconvenientclient.cyg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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

import com.jack.mc.cyg.superconvenientclient.cyg.util.CygActivityUtil;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygLog;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygEventBus;


/**
 * 对Fragment的包装
 */
public abstract class CygFragment extends Fragment {

    protected abstract int layoutRes();

    /**
     * onViewReallyCreated才是真正创建了View
     */
    protected void onViewReallyCreated(View view) {
    }

    protected boolean isSaveViewStatus() {
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * root view 与 getView() 的区别：
     * isSaveViewStatus()为true的情况下，root view 创建后就一直存在，保存着view的状态
     * getView() 在 Fragment 里控制，通过 FragmentTransaction 进行各种操作的时候会丢失view
     */
    private CygRootViewController mRootViewController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CygEventBus.getInstance().registerSubscriber(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CygEventBus.getInstance().unregisterSubscriber(this);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = null;
        if (mRootViewController != null) {
            rootView = findRootView();
        }
        if (!isSaveViewStatus() || rootView == null) {
            CygLog.debug("inflate root view");
            rootView = inflater.inflate(layoutRes(), null);
            mRootViewController = new CygRootViewController(rootView);
            onViewReallyCreated(rootView);
        } else {
            CygLog.debug("root view is already created");
            // 缓存的mRootView需要判断是否已经被加过parent
            // 如果有parent需要从parent删除，要不然会发生这个mRootView已经有parent的错误。
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                CygLog.debug("parent != null");
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    /**
     * final了，不能Override了
     */
    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected final boolean isAlive() {
        return CygActivityUtil.isAlive(getActivity());
    }

    protected final View layoutInflate(int layoutResource) {
        return CygActivityUtil.layoutInflate(getActivity(), layoutResource);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // delegate CygRootViewController

    public View findRootView() {
        return mRootViewController.findRootView();
    }

    public Button findButton(int id) {
        return mRootViewController.findButton(id);
    }

    public LinearLayout findLinearLayout(int id) {
        return mRootViewController.findLinearLayout(id);
    }

    public void setOnClickListener(int id, @Nullable View.OnClickListener listener) {
        mRootViewController.setOnClickListener(id, listener);
    }

    public EditText findEditText(int id) {
        return mRootViewController.findEditText(id);
    }

    public TextView findTextView(int id) {
        return mRootViewController.findTextView(id);
    }

    public ViewGroup findViewGroup(int id) {
        return mRootViewController.findViewGroup(id);
    }

    public View findViewById(int id) {
        return mRootViewController.findViewById(id);
    }

    public ListView findListView(int id) {
        return mRootViewController.findListView(id);
    }

    public void setOnTouchListener(int id, @Nullable View.OnTouchListener listener) {
        mRootViewController.setOnTouchListener(id, listener);
    }

    public ViewStub findViewStub(int id) {
        return mRootViewController.findViewStub(id);
    }

    public RelativeLayout findRelativeLayout(int id) {
        return mRootViewController.findRelativeLayout(id);
    }

    public ScrollView findScrollView(int id) {
        return mRootViewController.findScrollView(id);
    }

    public FrameLayout findFrameLayout(int id) {
        return mRootViewController.findFrameLayout(id);
    }

    public ImageView findImageView(int id) {
        return mRootViewController.findImageView(id);
    }
}
