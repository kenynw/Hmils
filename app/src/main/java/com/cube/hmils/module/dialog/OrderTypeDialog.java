package com.cube.hmils.module.dialog;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cube.hmils.R;
import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.module.order.ChangeDevicePresenter;
import com.cube.hmils.module.order.RoomNumPresenter;
import com.cube.hmils.utils.LUtils;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by Carol on 2017/11/5.
 */

public class OrderTypeDialog extends BottomSheetDialog {

    private static final String[] mTypes = new String[]{"01", "02", "03"};

    @BindView(R.id.iv_order_type_close)
    ImageView mIvClose;

    @BindViews({R.id.rl_order_type_new, R.id.rl_order_type_old, R.id.rl_order_type_change})
    RelativeLayout[] mRlTypes;

    @BindView(R.id.btn_order_type_ok)
    Button mBtnOk;

    private Client mClient;

    private int mSelectedIndex;
    private Order mOrder;

    public OrderTypeDialog(@NonNull Context context, Client client) {
        super(context);
        mClient = client;
        View view = View.inflate(context, R.layout.dialog_order_type, null);
        ButterKnife.bind(this, view);
        ImmersionBar bar = ImmersionBar
                .with((Activity) context, this, "type")
                .statusBarDarkFont(true, 0.3f);
        bar.init();

        setContentView(view);
        setOnDismissListener(dialog1 -> {
            bar.destroy();
        });

        ButterKnife.apply(mRlTypes, (ButterKnife.Action<RelativeLayout>) (view1, index) -> {
            view1.setOnClickListener(rl -> {
                if (!view1.isSelected()) {
                    view1.setSelected(true);
                    mSelectedIndex = index;
                    for (RelativeLayout rlType : mRlTypes) {
                        if (rlType != view1) {
                            rlType.setSelected(false);
                        }
                    }
                }
            });
        });
        if (client.getCustType() == 0) {
            mRlTypes[0].setVisibility(View.VISIBLE);
            mRlTypes[1].setVisibility(View.GONE);
            mRlTypes[0].setSelected(true);
        } else {
            mRlTypes[0].setVisibility(View.GONE);
            mRlTypes[1].setVisibility(View.VISIBLE);
            mRlTypes[1].setSelected(true);
        }
        mIvClose.setOnClickListener(close -> dismiss());
        mBtnOk.setOnClickListener(ok -> {
            ClientModel.getInstance().createOrder(mClient.getCustId(), mClient.getProjectId(), mTypes[mSelectedIndex])
                    .subscribe(new ServicesResponse<Order>() {
                        @Override
                        public void onNext(Order order) {
                            dismiss();
                            order.setCustName(mClient.getCustName());
                            order.setProjectId(order.getProjectId());
                            mOrder = order;
                            if (mSelectedIndex == 2) {
                                ChangeDevicePresenter.start(context, order);
                            } else {
                                RoomNumPresenter.start(context, order);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            LUtils.log(Log.getStackTraceString(e));
                        }
                    });
        });
    }

    public Order getOrder() {
        return mOrder;
    }

    public int getSelectedIndex() {
        return mSelectedIndex;
    }

}
