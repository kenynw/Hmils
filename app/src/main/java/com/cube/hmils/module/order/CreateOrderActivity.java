package com.cube.hmils.module.order;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cube.hmils.R;
import com.cube.hmils.app.constant.ARouterPaths;
import com.cube.hmils.core.widget.radius.RadiusTextView;
import com.cube.hmils.model.constant.ExtraConstant;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

@RequiresPresenter(CreateOrderPresenter.class)
@Route(path = ARouterPaths.CREATE_ORDER)
public class CreateOrderActivity extends ChainBaseActivity<CreateOrderPresenter> {

    @BindViews({R.id.order_riv_create_set_a, R.id.order_riv_create_set_b})
    RadiusTextView[] mRivSets;
    @BindViews({R.id.order_iv_create_tick_a, R.id.order_iv_create_tick_b})
    ImageView[] mIvTicks;
    @BindView(R.id.rbtn_order_create_online)
    RadioButton mRbtnOnline;
    @BindView(R.id.rbtn_order_create_offline)
    RadioButton mRbtnOffline;
    @BindView(R.id.rg_order_create_pay)
    RadioGroup mRgPay;
    @BindView(R.id.tv_order_create_ok)
    TextView mTvOk;

    @Autowired(name = ExtraConstant.EXTRA_PROJECT_ID)
    String mProjectId;

    private int mCurSetIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_create);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        setToolbarTitle("创建订单");

        ButterKnife.apply(mRivSets, (radiusTextView, i) -> radiusTextView.setOnClickListener(v -> {
            if (!radiusTextView.isSelected()) {
                if (mCurSetIndex >= 0) {
                    mRivSets[mCurSetIndex].setActivated(false);
                    mIvTicks[mCurSetIndex].setVisibility(View.GONE);
                }
                mCurSetIndex = i;
                radiusTextView.setActivated(true);
                mIvTicks[i].setVisibility(View.VISIBLE);
            }
        }));
    }

    @OnClick(R.id.tv_order_create_ok)
    void onOkClick() {
        int payWay = mRbtnOffline.isChecked() ? 0 : 1;
        getPresenter().submit(mCurSetIndex, payWay, mProjectId);
    }

}
