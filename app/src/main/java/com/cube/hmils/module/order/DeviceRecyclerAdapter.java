package com.cube.hmils.module.order;

import android.content.Context;
import android.view.ViewGroup;

import com.cube.hmils.model.bean.Device;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by Carol on 2017/11/22.
 */

public class DeviceRecyclerAdapter extends RecyclerArrayAdapter<Device> {

    public DeviceRecyclerAdapter(Context context, List<Device> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeviceViewHolder(parent);
    }

}
