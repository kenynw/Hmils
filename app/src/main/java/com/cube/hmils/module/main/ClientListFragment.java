package com.cube.hmils.module.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.utils.StringUtil;
import com.cube.hmils.widget.ScrollIndexer;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Carol on 2017/10/11.
 */
@RequiresPresenter(ClientListPresenter.class)
public class ClientListFragment extends BaseListFragment<ClientListPresenter, Client> implements TextWatcher {

    @BindView(R.id.indexer_client_list)
    ScrollIndexer mIndexer;

    @BindView(R.id.tv_client_list_index)
    TextView mTvIndex;

    @BindView(R.id.et_client_list_search)
    EditText mEtSearch;

    private Unbinder mUnbinder;

    private SearchListTask mSearchListTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        mEtSearch.addTextChangedListener(this);
        mIndexer.setOnTextSelectedListener(new ScrollIndexer.OnTextSelectedListener() {
            @Override
            public void onTextSelected(int position, String text) {
                mTvIndex.setVisibility(View.VISIBLE);
                mTvIndex.setText(text);

                List<Client> allData = getPresenter().getAdapter().getAllData();
                int count = allData.size();
                for (int i = 0; i < count; i++) {
                    if (text.equals(StringUtil.getFirstLetter(allData.get(i).getCustName()))) {
                        scrollPosition(i);
                        break;
                    }
                }
            }

            @Override
            public void onTouchUp() {
                mTvIndex.setVisibility(View.GONE);
            }

        });
        return view;
    }

    @Override
    public BaseViewHolder<Client> createViewHolder(ViewGroup parent, int viewType) {
        return new ClientViewHolder(parent);
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setRefreshAble(false)
                .setLoadMoreAble(false)
                .setNoMoreAble(false)
                .setContainerLayoutRes(R.layout.main_activity_client_list);
    }

    @Override
    public int getViewType(int position) {
        if (position == 0) return 0;
        String curLetter = getPresenter().getAdapter().getItem(position).getCustName();
        String lastLetter = getPresenter().getAdapter().getItem(position - 1).getCustName();
        return TextUtils.equals(StringUtil.getFirstLetter(curLetter),
                StringUtil.getFirstLetter(lastLetter)) ? 1 : 0;
    }

    private void scrollPosition(int index) {
        int firstPosition = getLayoutManager().findFirstVisibleItemPosition();
        int lastPosition = getLayoutManager().findLastVisibleItemPosition();
        if (index <= firstPosition) {
            getListView().scrollToPosition(index);
        } else if (index <= lastPosition) {
            int top = getListView().getRecyclerView().getChildAt(index - firstPosition).getTop();
            getListView().getRecyclerView().scrollBy(0, top);
        } else {
            getListView().scrollToPosition(index);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String keywords = s.toString().trim().toUpperCase();
        if (mSearchListTask != null && mSearchListTask.getStatus() == AsyncTask.Status.FINISHED) {
            try {
                mSearchListTask.cancel(true);
            } catch (Exception ignored) {

            }
        }
        mSearchListTask = new SearchListTask(getPresenter().getClients());
        mSearchListTask.execute(keywords);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private class SearchListTask extends AsyncTask<String, Void, String> {

        private List<Client> mAllData;

        private List<Client> mFilterList = new ArrayList<>();

        private boolean mInSearchMode;

        public SearchListTask(List<Client> allData) {
            mAllData = allData;
        }

        @Override
        protected String doInBackground(String... params) {
            mFilterList.clear();

            String keyword = params[0];

            mInSearchMode = (keyword.length() > 0);

            if (mInSearchMode && mAllData != null) {
                for (Client client : mAllData) {
                    boolean isChinese = client.getCustName().contains(keyword);
                    if (isChinese) mFilterList.add(client);
                }
            }

            return keyword;
        }

        @Override
        protected void onPostExecute(String result) {
            getPresenter().getAdapter().clear();
            getPresenter().getAdapter().addAll(mInSearchMode ? mFilterList : mAllData);
        }

    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            if (menuVisible) {
                getView().setVisibility(View.VISIBLE);
            } else {
                getView().setVisibility(View.INVISIBLE);
            }
        }
    }

}
