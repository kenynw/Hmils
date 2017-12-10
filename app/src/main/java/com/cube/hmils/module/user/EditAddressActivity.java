package com.cube.hmils.module.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.cube.hmils.R;
import com.cube.hmils.model.bean.Address;
import com.cube.hmils.model.bean.City;
import com.cube.hmils.model.bean.Dist;
import com.cube.hmils.model.bean.Province;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.Presenter;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(EditAddressPresenter.class)
public class EditAddressActivity extends BaseDataActivity<EditAddressPresenter, Address> {

    @BindView(R.id.tv_edit_address_area)
    TextView mTvAddress;

    @BindView(R.id.et_edit_address_detail)
    EditText mEtDetail;

    @BindView(R.id.fl_edit_address_area)
    FrameLayout mFlArea;

    private OptionsPickerView mPvAddressPicker;

    private Province mProvince;

    private City mCity;

    private Dist mDistrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_edit_address);
        setToolbarTitle(R.string.text_edit_address);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(Address address) {
        mFlArea.setOnClickListener(v -> showPickerView());
    }

    public void showPickerView() {// 弹出选择器
        mPvAddressPicker = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            mProvince = getPresenter().mOptions1Items.get(options1);
            mCity = getPresenter().mOptions2Items.get(options1).get(options2);
            mDistrict = getPresenter().mOptions3Items.get(options1).get(options2).get(options3);
            mTvAddress.setText(mProvince.getProvinceName() + mCity.getCityName() + mDistrict.getDistName());
        })
                .setLayoutRes(R.layout.dialog_province_picker, v -> {
                    TextView tvOk = v.findViewById(R.id.tv_province_ok);
                    tvOk.setOnClickListener(v1 -> {
                        mPvAddressPicker.returnData();
                        mPvAddressPicker.dismiss();
                    });
                })
                .setTitleText("城市选择")
                .setDividerColor(getResources().getColor(R.color.white))
                .setTextColorCenter(getResources().getColor(R.color.textPrimary))
                .setTextColorOut(getResources().getColor(R.color.textSecondary))
                .setContentTextSize(18)
                .setLineSpacingMultiplier(2.0f)
                .build();

        mPvAddressPicker.setPicker(getPresenter().mOptions1Items, getPresenter().mOptions2Items, getPresenter().mOptions3Items);//三级选择器
        mPvAddressPicker.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (TextUtils.isEmpty(mTvAddress.getText().toString().trim())) {
            LUtils.toast("请选择所在地区");
            return super.onOptionsItemSelected(item);
        }
        if (TextUtils.isEmpty(mEtDetail.getText().toString().trim())) {
            LUtils.toast("请输入详细地址");
            return super.onOptionsItemSelected(item);
        }

        Bundle bundle = new Bundle();
        bundle.putInt(Presenter.EVENT_BUS_CODE, EventCode.EDIT_ADDRESS);
        bundle.putParcelable("province", mProvince);
        bundle.putParcelable("city", mCity);
        bundle.putParcelable("district", mDistrict);
        bundle.putString("address", mEtDetail.getText().toString().trim());
        EventBus.getDefault().post(bundle);
        finish();
        return super.onOptionsItemSelected(item);
    }

}
