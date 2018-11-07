package com.cube.hmils.module.order;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cube.hmils.R;
import com.cube.hmils.app.constant.ARouterPaths;
import com.cube.hmils.model.constant.ExtraConstant;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = ARouterPaths.ROOM_NUM)
@RequiresPresenter(RoomNumPresenter.class)
public class RoomNumActivity extends ChainBaseActivity<RoomNumPresenter> implements TextWatcher {

    @BindView(R.id.et_room_num)
    EditText mEtNum;

    @BindView(R.id.btn_room_next)
    Button mBtnNext;

    @Autowired(name = ExtraConstant.EXTRA_PROJECT_ID)
    String mProjectId;
    @Autowired(name = ExtraConstant.CLIENT_NAME)
    String mClientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_num);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        mEtNum.addTextChangedListener(this);
        mBtnNext.setOnClickListener(v -> checkInput());
    }

    private void checkInput() {
        if (mEtNum.getText().length() <= 0) {
            LUtils.toast("请输入房间数");
            return;
        }
        getPresenter().addNum(mEtNum.getText().toString().trim());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mBtnNext.setEnabled(s.length() > 0);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public int[] getHideSoftViewIds() {
        return new int[] {R.id.et_room_num};
    }
}
