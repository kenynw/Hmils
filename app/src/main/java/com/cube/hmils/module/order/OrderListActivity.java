package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.cube.hmils.R;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carol on 2017/12/17.
 */
@RequiresPresenter(OrderListActivityPresenter.class)
public class OrderListActivity extends ChainBaseActivity<OrderListActivityPresenter> {

    @BindView(R.id.fl_order_list_container)
    FrameLayout mFlContainer;

    public static void start(Context context, int userId) {
        Intent intent = new Intent(context, OrderListActivity.class);
        intent.putExtra("client_id", userId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_list);
        setToolbarTitle("查看订单");
        ButterKnife.bind(this);

        int userId = getIntent().getIntExtra("client_id", 0);
        OrderListFragment fragment = OrderListFragment.newInstance(userId, 0, 0);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_order_list_container, fragment);
        ft.commit();
    }
}
