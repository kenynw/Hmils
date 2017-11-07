package com.cube.hmils.module.user;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.cube.hmils.R;
import com.cube.hmils.model.bean.Province;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(EditAddressPresenter.class)
public class EditAddressActivity extends ChainBaseActivity<EditAddressPresenter> {

    @BindView(R.id.tv_edit_address_area)
    TextView mTvArea;

    @BindView(R.id.tv_edit_address_street)
    TextView mTvStreet;

    @BindView(R.id.et_edit_address_detail)
    EditText mEtDetail;
    @BindView(R.id.fl_edit_address_area)
    FrameLayout mFlArea;
    @BindView(R.id.fl_edit_address_street)
    FrameLayout mFlStreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_edit_address);
        setToolbarTitle(R.string.text_edit_address);
        ButterKnife.bind(this);

        mFlArea.setOnClickListener(v -> getPresenter().parseData());
    }

    public void showPickerView(List<Province> options1Items, ArrayList<ArrayList<String>> options2Items,
                               List<ArrayList<ArrayList<String>>> options3Items) {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

            }
        })
                .setLayoutRes(R.layout.dialog_province_picker, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        WheelView view = new WheelView(EditAddressActivity.this);
                    }
                })
                .setTitleText("城市选择")
                .setDividerColor(getResources().getColor(R.color.white))
                .setTextColorCenter(getResources().getColor(R.color.textPrimary))
                .setTextColorOut(getResources().getColor(R.color.textSecondary))
                .setContentTextSize(18)
                .setLineSpacingMultiplier(2.0f)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
