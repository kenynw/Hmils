package com.dsk.chain.expansion.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dsk.chain.R;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public abstract class BaseListActivity<P extends BaseListActivityPresenter> extends ChainBaseActivity<P> {

    private EasyRecyclerView mListView;

    private ListConfig mListConfig;

    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListConfig = getListConfig();
        setContentView();
        findRecycleView();
        initRecycle();
        initAdapter();
    }

    private void setContentView() {
        if (getLayout() != 0) {
            setContentView(getLayout());
        } else if (mListConfig.mContainerLayoutRes > 0) {
            setContentView(mListConfig.mContainerLayoutRes);
        } else if (mListConfig.mContainerLayoutView != null) {
            setContentView(mListConfig.mContainerLayoutView);
        } else {
            mListView = new EasyRecyclerView(this);
            mListView.setId(R.id.recycle);
            mListView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            setContentView(mListView);
        }
    }

    private void findRecycleView() {
        if (mListView == null) mListView = (EasyRecyclerView) findViewById(R.id.recycle);
        if (mListView == null) throw new RuntimeException("No found RecycleView with id 'recycle'");
        mListView.setLayoutManager(mLayoutManager = new LinearLayoutManager(this));
    }

    private void initRecycle() {
        if (mListConfig.mRefreshAble) mListView.setRefreshListener(getPresenter());
        if (mListConfig.mContainerProgressAble) {
            if (mListConfig.mContainerProgressView != null) mListView.setProgressView(mListConfig.mContainerProgressView);
            else if (mListConfig.mContainerProgressRes > 0) mListView.setProgressView(mListConfig.mContainerProgressRes);
        }
        if (mListConfig.mContainerErrorAble) {
            if (mListConfig.mContainerErrorView != null) mListView.setErrorView(mListConfig.mContainerErrorView);
            else if (mListConfig.mContainerErrorRes > 0) mListView.setErrorView(mListConfig.mContainerErrorRes);
        }
        if (mListConfig.mContainerEmptyAble) {
            if (mListConfig.mContainerEmptyView != null) mListView.setEmptyView(mListConfig.mContainerEmptyView);
            else if (mListConfig.mContainerEmptyRes > 0) mListView.setEmptyView(mListConfig.mContainerEmptyRes);
        }
        if (mListConfig.mHasItemDecoration) {
            if (mListConfig.mItemDecoration != null) {
                mListView.addItemDecoration(mListConfig.mItemDecoration);
            } else {
                DividerDecoration decoration = new DividerDecoration(android.R.color.transparent, 4);
                decoration.setDrawHeaderFooter(false);
                mListView.addItemDecoration(decoration);
            }
        }
    }

    private void initAdapter() {
        final BaseListActivityPresenter.DataAdapter adapter = getPresenter().getAdapter();
        if (mListConfig.mContainerProgressAble) mListView.setAdapterWithProgress(adapter);
        else mListView.setAdapter(adapter);
        if (mListConfig.mFooterErrorAble) {
            if (mListConfig.mFooterErrorView != null)
                adapter.setError(mListConfig.mFooterErrorView);
            else if (mListConfig.mFooterErrorRes > 0) adapter.setError(mListConfig.mFooterErrorRes);
        }
        if (mListConfig.mLoadMoreAble) {
            if (mListConfig.mFooterMoreView != null) adapter.setMore(mListConfig.mFooterMoreView, getPresenter());
            else if (mListConfig.mFooterMoreRes > 0) adapter.setMore(mListConfig.mFooterMoreRes, getPresenter());
        }
        if (mListConfig.mNoMoreAble) {
            if (mListConfig.mFooterNoMoreView != null) adapter.setNoMore(mListConfig.mFooterNoMoreView);
            else if (mListConfig.mFooterNoMoreRes > 0) adapter.setNoMore(mListConfig.mFooterNoMoreRes);
        }
    }

    public void stopRefresh() {
        mListView.getSwipeToRefresh().setRefreshing(false);
    }

    public void showError() {
        mListView.showError();
    }

    protected int getLayout() {
        return 0;
    }

    public ListConfig getListConfig() {
        return ListConfig.DEFAULT.clone();
    }

    public EasyRecyclerView getListView() {
        return mListView;
    }

    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public int getViewType(int position){
        return 0;
    }

    protected abstract BaseViewHolder createViewHolder(ViewGroup parent, int viewType);

}
