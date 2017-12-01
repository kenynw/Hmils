package com.cube.hmils.module.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.InstallInfo;
import com.cube.hmils.model.bean.RoomOrder;
import com.cube.hmils.module.dialog.BaseAlertDialog;
import com.cube.hmils.module.dialog.DialogCallback;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Liaopeikun
 *         <p>
 *         总订单详情
 */
@RequiresPresenter(OrderDetailPresenter.class)
public class OrderDetailActivity extends BaseDataActivity<OrderDetailPresenter, RoomOrder> implements DialogCallback {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.ll_order_detail_top)
    LinearLayout mLlTop;
    @BindView(R.id.fl_order_detail_container)
    FrameLayout mFlContainer;
    @BindView(R.id.tv_order_detail_more)
    TextView mTvMore;
    @BindView(R.id.tv_order_detail_num)
    TextView mTvNum;
    @BindView(R.id.tv_order_detail_price)
    TextView mTvPrice;
    @BindView(R.id.rbtn_order_detail_online)
    RadioButton mRbtnOnline;
    @BindView(R.id.rbtn_order_detail_offline)
    RadioButton mRbtnOffline;
    @BindView(R.id.rg_order_detail_pay)
    RadioGroup mRgPay;
    @BindView(R.id.btn_order_detail_confirm)
    Button mBtnConfirm;
    @BindView(R.id.ll_order_detail_pay)
    LinearLayout mLlPay;
    @BindView(R.id.ll_order_detail_state)
    LinearLayout mLlState;
    @BindView(R.id.btn_order_detail_contact)
    Button mBtnContact;
    @BindView(R.id.btn_order_detail_log)
    Button mBtnLog;

    @BindView(R.id.ll_order_detail_install)
    LinearLayout mLlInstall;
    @BindView(R.id.tv_order_install_label)
    TextView mTvInstallLabel;
    @BindView(R.id.tv_order_install_info)
    TextView mTvInstallInfo;
    @BindView(R.id.btn_order_install_contact)
    Button mBtnInstallContact;
    @BindView(R.id.tv_order_detail_time)
    TextView mTvTime;
    @BindView(R.id.tv_order_detail_state)
    TextView mTvState;

    private int mState;

    RoomOrderFragment mOrderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_detail);
        ButterKnife.bind(this);

        setToolbarTitle("总订单详情");

        mState = getIntent().getIntExtra("state", 0);

        switch (mState) {
            case 1:
                mLlPay.setVisibility(View.VISIBLE);
                mBtnConfirm.setVisibility(View.VISIBLE);
                break;
            case 2:
                mLlState.setVisibility(View.VISIBLE);
                mBtnLog.setVisibility(View.GONE);
//                mTvState.setText();
                break;
            case 3:
                mLlState.setVisibility(View.VISIBLE);
                break;
            case 4:
                mLlState.setVisibility(View.VISIBLE);
                mBtnContact.setVisibility(View.GONE);
                break;
            case 5:
                mLlState.setVisibility(View.VISIBLE);
                mBtnContact.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void setData(RoomOrder roomOrder) {
        mOrderFragment = RoomOrderFragmentPresenter.newInstance(roomOrder);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_order_detail_container, mOrderFragment)
                .commit();
        mTvPrice.setText(roomOrder.getRoomOrder().getTotalPrice());
        mTvNum.setText(String.format(getString(R.string.text_count_product), roomOrder.getRoomOrder().getTotalGoods()));
        mBtnConfirm.setOnClickListener(v -> showConfirmDialog());

        if (roomOrder.getInstallInfo() != null && roomOrder.getInstallInfo().getOrderStatus().equals("待安装")) {
            InstallInfo install = roomOrder.getInstallInfo();
            mLlInstall.setVisibility(View.VISIBLE);
            String installStr = "%1$s\n%2$s\n%3$s\n<font color=\"#5DBA68\">%4$s</font>";
            mTvInstallInfo.setText(String.format(installStr, install.getMobile(), install.getName(),
                    install.getAppoTime(), install.getOrderStatus()));
        }
    }

    private void showConfirmDialog() {
        BaseAlertDialog.newInstance(R.layout.dialog_order_confirm, this)
                .show(getSupportFragmentManager(), "confirm");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modify_order, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ParamListPresenter.start(this, getPresenter().getData());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPositiveClick(@NonNull View view) {
        if (getSupportFragmentManager().findFragmentByTag("confirm") != null) {
            getPresenter().confirm(mRbtnOnline.isChecked() ? 0 : 1);
        }
    }

    @Override
    public void onNegativeClick(@NonNull View view) {

    }

}
