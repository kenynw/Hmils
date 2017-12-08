package com.cube.hmils.module.user;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(QRCodePresenter.class)
public class QRCodeActivity extends ChainBaseActivity<QRCodePresenter> {

    @BindView(R.id.dv_qr_code_avatar)
    SimpleDraweeView mDvAvatar;

    @BindView(R.id.tv_qr_code_name)
    TextView mTvName;

    @BindView(R.id.tv_qr_code_phone)
    TextView mTvPhone;

    @BindView(R.id.dv_qr_code)
    SimpleDraweeView mDvQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_qrcode);
        setToolbarTitle(R.string.text_my_qr_code);
        ButterKnife.bind(this);

        int size = LUtils.getScreenWidth() - LUtils.dp2px(60);
        ViewGroup.LayoutParams lp = mDvQrCode.getLayoutParams();
        lp.height = size;
        mDvQrCode.setLayoutParams(lp);

        mDvQrCode.setImageURI(getIntent().getStringExtra("url"));
    }

}
