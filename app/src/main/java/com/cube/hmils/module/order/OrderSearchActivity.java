package com.cube.hmils.module.order;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.cube.hmils.R;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(OrderSearchPresenter.class)
public class OrderSearchActivity extends BaseListActivity<OrderSearchPresenter> implements TextWatcher {

    @BindView(R.id.et_search_keyword)
    EditText mEtSearchKeyword;
    @BindView(R.id.iv_search_close)
    ImageView mIvSearchClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mEtSearchKeyword.addTextChangedListener(this);
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(parent);
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setContainerLayoutRes(R.layout.order_activity_search);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(s)) getPresenter().onRefresh();
    }

    public String getSearchKeywords() {
        return mEtSearchKeyword.getText().toString().trim();
    }

    @Override
    public int[] getHideSoftViewIds() {
        return new int[] {R.id.et_search_keyword};
    }

}
