package com.cube.hmils.module.order;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.RoomOrder;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.Presenter;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(ParamDetailPresenter.class)
public class ParamDetailActivity extends BaseDataActivity<ParamDetailPresenter, RoomOrder> implements ViewPager.OnPageChangeListener {

    @BindView(R.id.iv_param_detail_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_param_detail_position)
    TextView mTvPosition;
    @BindView(R.id.iv_param_detail_right)
    ImageView mIvRight;

    @BindView(R.id.toolbar_back_icon)
    ImageView mToolbarBackIcon;
    @BindView(R.id.toolbar_back_text)
    TextView mToolbarBackText;
    @BindView(R.id.vp_param_detail)
    ViewPager mVpParamDetail;
    @BindView(R.id.btn_param_detail_back)
    Button mBtnBack;

    private List<RoomOrderFragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_param_detail);
        ButterKnife.bind(this);

        mToolbarBackIcon.setVisibility(View.GONE);
        mToolbarBackText.setVisibility(View.VISIBLE);

        if (getPresenter().getType() == 0) {
            mToolbarBackText.setText("修改参数");
            mToolbarBackText.setOnClickListener(v ->
                    ParamDetailPresenter.start(this, getPresenter().getProjectId(), 2));
        } else if (getPresenter().getType() == 2) {
            mToolbarBackText.setText("上一步");
            mToolbarBackText.setOnClickListener(v -> {
//                RoomParamsPresenter.start(this, getPresenter().getProjectId(), null, );
            });
            mBtnBack.setVisibility(View.GONE);
        } else {
            mToolbarBackText.setVisibility(View.GONE);
            mBtnBack.setVisibility(View.VISIBLE);
        }
        mBtnBack.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt(Presenter.EVENT_BUS_CODE, EventCode.ROOM_PARAMS_FINISH);
            EventBus.getDefault().post(bundle);
            finish();
        });

        mFragments = new ArrayList<>();
    }

    @Override
    public void setData(RoomOrder roomOrder) {
        if (roomOrder.getRoomPara().size() <= 0) return;
        for (RoomOrder order : roomOrder.getRoomPara()) {
            RoomOrderFragment fragment = RoomOrderFragmentPresenter.newInstance(order, getPresenter().getType() == 2);
            mFragments.add(fragment);
        }
        ParamPageAdapter pagerAdapter = new ParamPageAdapter(getSupportFragmentManager(),
                roomOrder.getRoomPara(), mFragments);
        mVpParamDetail.setAdapter(pagerAdapter);
        mVpParamDetail.addOnPageChangeListener(this);
        mIvLeft.setOnClickListener(v -> mVpParamDetail.setCurrentItem(mVpParamDetail.getCurrentItem() - 1));
        mIvRight.setOnClickListener(v -> mVpParamDetail.setCurrentItem(mVpParamDetail.getCurrentItem() + 1));
        mVpParamDetail.setCurrentItem(0);
        setPositionInfo(0, roomOrder.getRoomPara());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getPresenter().getType() == 0) {
            getMenuInflater().inflate(R.menu.menu_confirm_param, menu);
        }
        if (getPresenter().getType() == 2) {
            getMenuInflater().inflate(R.menu.menu_save, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getPresenter().getType() == 0) {
            OrderDetailPresenter.start(this, getPresenter().getProjectId(), 0);
        }
        if (getPresenter().getType() == 2) {
            List<RoomOrder> roomOrders = new ArrayList<>();
            for (RoomOrderFragment fragment : mFragments) {
                RoomOrder modifyList = fragment.getModifyList();
                if (modifyList != null) {
                    roomOrders.add(modifyList);
                }
            }
            if (roomOrders.size() > 0) {
                String param = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                        .create().toJson(roomOrders);
                LUtils.log("param: " + param);
                getPresenter().updateOrder(param);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        List<RoomOrder> roomPara = getPresenter().getData().getRoomPara();
        if (roomPara != null && roomPara.size() > 0) {
            setPositionInfo(position, roomPara);
            mIvLeft.setEnabled(position != 0);
            mIvRight.setEnabled(position != roomPara.size() - 1);
        } else {
            mIvLeft.setEnabled(false);
            mIvRight.setEnabled(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setPositionInfo(int position, List<RoomOrder> roomPara) {
        mTvPosition.setText(String.format("%1$d of %2$d", position + 1, roomPara.size()));
        setToolbarTitle(roomPara.get(position).getRoomName());
    }

}
