package com.jack.mc.cyg.superconvenientclient.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.jack.mc.cyg.superconvenientclient.R;
import com.jack.mc.cyg.superconvenientclient.cyg.fragment.CygFragment;
import com.jack.mc.cyg.superconvenientclient.view.DividerDecoration;
import com.jack.mc.cyg.superconvenientclient.view.adapter.GoodsAdapter;
import com.jack.mc.cyg.superconvenientclient.view.adapter.TypeAdapter;
import com.jack.mc.cyg.superconvenientclient.view.bean.GoodsItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by Jack on 16/6/9.
 */
public class RecordFragment extends CygFragment {

    @Bind(R.id.ltb_title_textview)
    TextView mTvTitle;
    @Bind(R.id.typeRecyclerView)
    RecyclerView mRvType;
    @Bind(R.id.itemListView)
    StickyListHeadersListView mListView;
    private ArrayList<GoodsItem> dataList, typeList;
    private GoodsAdapter myAdapter;
    private TypeAdapter typeAdapter;

    public static RecordFragment mRecordFragment;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_record;
    }

    @Override
    protected void onViewReallyCreated(View view) {
        super.onViewReallyCreated(view);

        ButterKnife.bind(this, view);
        mRecordFragment = this;
        initListener();
    }

    private void initListener() {
        mTvTitle.setText("记录");
        dataList = GoodsItem.getGoodsList();
        typeList = GoodsItem.getTypeList();

        mRvType.setLayoutManager(new LinearLayoutManager(getActivity()));
        typeAdapter = new TypeAdapter(typeList);
        mRvType.setAdapter(typeAdapter);
        mRvType.addItemDecoration(new DividerDecoration(getActivity()));

        myAdapter = new GoodsAdapter(dataList, getActivity());
        mListView.setAdapter(myAdapter);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                GoodsItem item = dataList.get(firstVisibleItem);
                if (typeAdapter.selectTypeId != item.typeId) {
                    typeAdapter.selectTypeId = item.typeId;
                    typeAdapter.notifyDataSetChanged();
                    mRvType.smoothScrollToPosition(getSelectedGroupPosition(item.typeId));
                }
            }
        });
    }

    //根据类别id获取分类的Position 用于滚动左侧的类别列表
    public int getSelectedGroupPosition(int typeId) {
        for (int i = 0; i < typeList.size(); i++) {
            if (typeId == typeList.get(i).typeId) {
                return i;
            }
        }
        return 0;
    }

    public void onTypeClicked(int typeId) {
        mListView.setSelection(getSelectedPosition(typeId));
    }

    private int getSelectedPosition(int typeId) {
        int position = 0;
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).typeId == typeId) {
                position = i;
                break;
            }
        }
        return position;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecordFragment = null;
    }
}
