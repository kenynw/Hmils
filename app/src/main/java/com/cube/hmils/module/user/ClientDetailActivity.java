package com.cube.hmils.module.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.app.Navigator;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.module.dialog.OrderTypeDialog;
import com.cube.hmils.module.order.ChangeDevicePresenter;
import com.cube.hmils.module.order.OrderListActivity;
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

    @BindView(R.id.tv_client_cooperat)
    TextView mTvCooperatTime;

    @BindView(R.id.btn_client_detail_view)
    Button mBtnView;

    @BindView(R.id.btn_client_detail_create)
    Button mBtnCreate;

    @BindView(R.id.btn_client_contact)
    Button mBtnContact;

    private OrderTypeDialog mTypeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_client_detail);
        setToolbarTitle(R.string.text_client_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(Client client) {
        mDvAvatar.setImageURI(client.getCustImg());
        mTvName.setText(client.getCustName());
        mTvAddress.setText(client.getFullAddress());
        mTvCooperatTime.setText(client.getCreatTime());

        mBtnView.setOnClickListener(v -> OrderListActivity.start(this, client.getCustId()));
        mBtnCreate.setOnClickListener(v -> {
            if (mTypeDialog != null && mTypeDialog.getOrder() != null) {
                if (mTypeDialog.getSelectedIndex() == 2) {
                    ChangeDevicePresenter.start(ClientDetailActivity.this, mTypeDialog.getOrder());
                } else {
                    Order order = mTypeDialog.getOrder();
                    Navigator.getInstance().openRoomNumActivity(order.getProjectId() + "",
                            order.getCustName());
                }
            } else {
                getPresenter().createOrder();
            }
        });
        mBtnContact.setOnClickListener(v -> {
            Uri uri = Uri.parse("tel:" + client.getPhoneNo());
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ProfilePresenter.start(this, getPresenter().getClient());
        return super.onOptionsItemSelected(item);
    }

}
