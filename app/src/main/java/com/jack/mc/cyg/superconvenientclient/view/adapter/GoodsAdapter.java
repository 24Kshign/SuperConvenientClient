package com.jack.mc.cyg.superconvenientclient.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jack.mc.cyg.superconvenientclient.R;
import com.jack.mc.cyg.superconvenientclient.view.bean.GoodsItem;

import java.text.NumberFormat;
import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;


public class GoodsAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private ArrayList<GoodsItem> dataList;
    private Context mContext;
    private NumberFormat nf;
    private LayoutInflater mInflater;

    public GoodsAdapter(ArrayList<GoodsItem> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_goods, parent, false);
            holder = new ItemViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ItemViewHolder) convertView.getTag();
        }
        GoodsItem item = dataList.get(position);
        holder.bindData(item);
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_header_view, parent, false);
        }
        ((TextView) (convertView)).setText(dataList.get(position).typeName);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return dataList.get(position).typeId;
    }

    class ItemViewHolder {

        private TextView name, price;
        private GoodsItem item;

        public ItemViewHolder(View itemView) {
            name = (TextView) itemView.findViewById(R.id.ig_tv_goods_name);
            price = (TextView) itemView.findViewById(R.id.ig_tv_goods_price);
        }

        public void bindData(GoodsItem item) {
            this.item = item;
            name.setText(item.name);
            price.setText(nf.format(item.price));
        }
    }
}
