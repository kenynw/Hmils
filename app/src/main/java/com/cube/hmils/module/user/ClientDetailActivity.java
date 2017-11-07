package com.cube.hmils.module.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.module.dialog.OrderTypeDialog;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(ClientDetailPresenter.class)
public class ClientDetailActivity extends ChainBaseActivity<ClientDetailPresenter> {

    @BindView(R.id.dv_client_detail_avatar)
    SimpleDraweeView mDvAvatar;

    @BindView(R.id.tv_client_detail_name)
    TextView mTvName;

    @BindView(R.id.tv_client_detail_address)
    TextView mTvAddress;

    @BindView(R.id.btn_client_detail_view)
    Button mBtnView;

    @BindView(R.id.btn_client_detail_create)
    Button mBtnCreate;

    OrderTypeDialog mTypeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_client_detail);
        setToolbarTitle(R.string.text_client_detail);
        ButterKnife.bind(this);

        mTypeDialog = new OrderTypeDialog(this);

        mBtnCreate.setOnClickListener(v -> mTypeDialog.show());
    }

}
