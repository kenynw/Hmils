package com.cube.hmils.module.main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.utils.StringUtil;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carol on 2017/11/12.
 */

public class ClientViewHolder extends BaseViewHolder<Client> {

    @BindView(R.id.tv_client_letter)
    TextView mTvLetter;

    @BindView(R.id.tv_client_name)
    TextView mTvName;

    public ClientViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_client);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Client data) {
        if (getItemViewType() == 0) {
            mTvLetter.setVisibility(View.VISIBLE);
            mTvLetter.setText(StringUtil.getFirstLetter(data.getCustName()));
        } else {
            mTvLetter.setVisibility(View.GONE);
        }
        mTvName.setText(data.getCustName());
    }

}
