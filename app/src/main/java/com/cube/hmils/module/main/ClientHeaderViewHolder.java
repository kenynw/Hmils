package com.cube.hmils.module.main;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.module.user.ProfileActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cube.hmils.module.user.ProfilePresenter.EXTRA_CLIENT;

/**
 * Created by Carol on 2017/11/12.
 */

public class ClientHeaderViewHolder extends BaseViewHolder<Client> {

    @BindView(R.id.tv_client_header_from)
    TextView mTvFrom;

    @BindView(R.id.tv_client_header_username)
    TextView mTvUsername;

    @BindView(R.id.tv_client_header_mobile)
    TextView mTvMobile;

    @BindView(R.id.btn_client_header_complete)
    Button mBtnComplete;

    public ClientHeaderViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_client_header);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Client data) {
        mTvFrom.setText("来自" + data.getContTel() + "客户的绑定");
        mTvUsername.setText(data.getCustName());
        mTvMobile.setText(data.getContTel());
        mBtnComplete.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ProfileActivity.class);
            intent.putExtra(EXTRA_CLIENT, data);
            getContext().startActivity(intent);
        });
    }

}
