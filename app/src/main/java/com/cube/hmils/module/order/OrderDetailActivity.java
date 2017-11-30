package com.cube.hmils.module.order;

import android.os.Bundle;
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
import com.cube.hmils.model.bean.RoomOrder;
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
public class OrderDetailActivity extends BaseDataActivity<OrderDetailPresenter, RoomOrder> {

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
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
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
        mBtnConfirm.setOnClickListener(v -> getPresenter().confirm(mRbtnOnline.isChecked() ? 0 : 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modify_order, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
