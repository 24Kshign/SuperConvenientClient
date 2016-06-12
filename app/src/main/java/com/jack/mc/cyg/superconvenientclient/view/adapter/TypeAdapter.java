package com.jack.mc.cyg.superconvenientclient.view.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jack.mc.cyg.superconvenientclient.R;
import com.jack.mc.cyg.superconvenientclient.cyg.util.CygLog;
import com.jack.mc.cyg.superconvenientclient.view.bean.GoodsItem;
import com.jack.mc.cyg.superconvenientclient.view.fragment.RecordFragment;

import java.util.ArrayList;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {
    public int selectTypeId;
    public ArrayList<GoodsItem> dataList;

    public TypeAdapter(ArrayList<GoodsItem> dataList) {
        this.dataList = dataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodsItem item = dataList.get(position);

        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView type;
        private GoodsItem item;

        public ViewHolder(View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.type);
            itemView.setOnClickListener(this);
        }

        public void bindData(GoodsItem item) {
            this.item = item;
            type.setText(item.typeName);
            CygLog.debug("itemType==" + item.typeId);
            CygLog.debug("selectTypeId==" + selectTypeId);
            if (item.typeId == selectTypeId) {
                itemView.setBackgroundColor(Color.WHITE);
            } else {
                itemView.setBackgroundColor(Color.TRANSPARENT);
            }

        }

        @Override
        public void onClick(View v) {
            RecordFragment.mRecordFragment.onTypeClicked(item.typeId);
        }
    }
}
