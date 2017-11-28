package com.cube.hmils.module.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.module.dialog.OrderTypeDialog;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(ClientDetailPresenter.class)
public class ClientDetailActivity extends BaseDataActivity<ClientDetailPresenter, Client> {

    @BindView(R.id.dv_client_detail_avatar)
    SimpleDraweeView mDvAvatar;

    @BindView(R.id.tv_client_detail_name)
    TextView mTvName;

    @BindView(R.id.tv_client_detail_address)
    TextView mTvAddress;

    @BindView(R.id.tv_client_cooperat_label)
    TextView mTvCooperatTime;

    @BindView(R.id.btn_client_detail_view)
    Button mBtnView;

    @BindView(R.id.btn_client_detail_create)
    Button mBtnCreate;

    private OrderTypeDialog mTypeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_client_detail);
        setToolbarTitle(R.string.text_client_detail);
        ButterKnife.bind(this);

        mTypeDialog = new OrderTypeDialog(this);
        mBtnCreate.setOnClickListener(v -> mTypeDialog.show());
    }

    @Override
    public void setData(Client client) {
        mDvAvatar.setImageURI(client.getCustImg());
        mTvName.setText(client.getCustName());
        mTvAddress.setText(client.getFullAddress());
        mTvCooperatTime.setText(client.getCreatTime());
    }

}
