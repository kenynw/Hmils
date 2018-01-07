package com.cube.hmils.module.order;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.Service;
import com.cube.hmils.model.constant.ServiceState;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.exgridview.ExGridView;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

@RequiresPresenter(ServiceDetailPresenter.class)
public class ServiceDetailActivity extends BaseDataActivity<ServiceDetailPresenter, Service> {

    @BindDrawable(R.drawable.ic_marker_timeline_normal)
    Drawable mMarkerNormal;
    @BindDrawable(R.drawable.ic_marker_timeline_selected)
    Drawable mMarkerSelected;

    int mColorNormal = 0xffEFF2F5;
    int mColorSelected = 0xff89CBCB;

    @BindView(R.id.iv_order_complete)
    ImageView mIvComplete;

    @BindViews({R.id.v_service_detail_marker_one, R.id.v_service_detail_marker_two, R.id.v_service_detail_marker_third})
    View[] mVMarkers;
    @BindViews({R.id.v_service_detail_line_one, R.id.v_service_detail_line_two})
    View[] mVLines;
    @BindViews({R.id.tv_service_handed, R.id.tv_service_handing, R.id.tv_service_done})
    TextView[] mTvStates;

    @BindView(R.id.tv_service_name)
    TextView mTvName;
    @BindView(R.id.tv_service_mobile)
    TextView mTvMobile;
    @BindView(R.id.tv_service_address)
    TextView mTvAddress;
    @BindView(R.id.tv_service_desc)
    TextView mTvDesc;
    @BindView(R.id.tv_service_remark)
    TextView mTvRemark;
    @BindView(R.id.tv_service_tel)
    TextView mTvTel;
    @BindView(R.id.exgrid_service_detail_photo)
    ExGridView mExgridPhoto;
    @BindView(R.id.btn_service_contact)
    Button mBtnContact;
    @BindView(R.id.fl_service_tel)
    FrameLayout mFlTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_service_detail);
        ButterKnife.bind(this);
        setToolbarTitle("任务状态");
    }

    @Override
    public void setData(Service service) {
        mTvName.setText(service.getCustName());
        mTvMobile.setText(service.getContTel());
        mTvAddress.setText(service.getContAddr());
        mTvDesc.setText(service.getOrderCnt());
        mTvRemark.setText(service.getMemo());
        for (String photo : service.getPhoto()) {
            if (!TextUtils.isEmpty(photo)) {
                SimpleDraweeView dv = (SimpleDraweeView) View.inflate(this, R.layout.item_service_detail_photo, null);
                dv.setImageURI(photo);
                if (mExgridPhoto.getVisibility() != View.VISIBLE)
                    mExgridPhoto.setVisibility(View.VISIBLE);
                mExgridPhoto.addView(dv);
            }
        }
        ServiceState serviceState = ServiceState.stateFromState(service.getProcCode());
        if (serviceState != null) {
            for (int i = 0; i < mVMarkers.length; i++) {
                mVMarkers[i].setBackgroundDrawable(i <= serviceState.mIndex ? mMarkerSelected : mMarkerNormal);
            }
            for (int i = 0; i < mVLines.length; i++) {
                mVLines[i].setBackgroundColor(i <= serviceState.mIndex - 1 ? mColorSelected : mColorNormal);
            }

            String format = "%1$s<br><font color=\"#1F989B\">%2$s</font>";
            mTvStates[0].setText(Html.fromHtml(String.format(format, service.getCreateTime(), "待接收")));
            mIvComplete.setVisibility(View.GONE);
            switch (serviceState) {
                case HANDING:
                    String str = "%1$s<br><font color=\"#1F989B\">%2$s</font><br><font color=\"#1F989B\">%3$s</font>";
                    mTvStates[1].setText(Html.fromHtml(String.format(str, service.getAppoTime(), "处理中", service.getInstaller() + "正在安装")));
                    mBtnContact.setVisibility(View.VISIBLE);
                    mBtnContact.setOnClickListener(v -> {
                        Uri uri = Uri.parse("tel:" + service.getPhoneNo());
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        startActivity(intent);
                    });
                    mFlTel.setVisibility(View.VISIBLE);
                    mTvTel.setText(service.getPhoneNo());
                    break;

                case PENDING:
                    mTvStates[1].setText(Html.fromHtml(String.format(format, service.getAppoTime(), "处理中")));
                    mTvStates[2].setText(Html.fromHtml(String.format(format, service.getEndTime(), "已完成")));
                    mIvComplete.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

}
