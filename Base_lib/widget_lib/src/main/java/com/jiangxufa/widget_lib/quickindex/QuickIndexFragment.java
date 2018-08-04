package com.jiangxufa.widget_lib.quickindex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jiangxufa.fa_lib.base.BaseFragment;
import com.jiangxufa.widget_lib.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 创建时间：2018/6/25
 * 编写人：lenovo
 * 功能描述：
 */

public class QuickIndexFragment extends BaseFragment<QuickIndexPresenter> implements QuickIndexContract.IView{

    Unbinder unbinder;
    private ArrayList<Friend> friends = new ArrayList<>();
    private ListView listView;
    private QuickIndex index;

    public static QuickIndexFragment newInstance() {
        return new QuickIndexFragment();
    }

    @Override
    protected int getViewResourse() {
        return R.layout.widget_lib_fragment_quickindex;
    }

    @Override
    protected void initView(View view) {
        listView = view.findViewById(R.id.listview);
        index = view.findViewById(R.id.quickindex);
    }

    // 虚拟数据
    private void prepareData() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        prepareData();

        index.setOnIndexListener(new QuickIndex.IndexListener() {
            @Override
            public void onLetterchange(String letter) {
                for (int i = 0; i < friends.size(); i++) {
                    String firstword = friends.get(i).pinyin.charAt(0) + "";
                    if (firstword.equals(letter)) {
                        //找到了直接置顶
                        listView.setSelection(i);
                        break;
                    }
                }
            }

            @Override
            public void onHeightChange(int height) {
            }

            @Override
            public void onTouchEnd(boolean end) {
            }
        });

        //排序
        Collections.sort(friends);

        //填充listview
        listView.setAdapter(new FriendAdapter());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getContacts();
    }

    @Override
    protected QuickIndexPresenter initPresenter(BaseFragment baseFragment) {
        return new QuickIndexPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showContacts(List<Friend> contact) {
        if (contact.isEmpty()) return;
        if (isAdded()) {
            friends.clear();
            friends.addAll(contact);
            ((FriendAdapter) listView.getAdapter()).notifyDataSetChanged();
        }
    }

    private class FriendAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return friends.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), R.layout.widget_lib_adapter_friend, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //设置数据
            Friend friend = friends.get(position);

            //获取上一个条目的首字母
            String firstWord = friend.pinyin.charAt(0) + "";
            if (position > 0) {
                String lastLetter = friends.get(position - 1).pinyin.charAt(0) + "";
                if (firstWord.equals(lastLetter)) {
                    //说明需要隐藏当前的首字母TextView
                    holder.tvLetter.setVisibility(View.GONE);
                } else {
                    //说明不想同，那么应该显示当前的首字母
                    //需要显示的时候设置为可见
                    holder.tvLetter.setVisibility(View.VISIBLE);
                    holder.tvLetter.setText(firstWord);
                }
            } else {
                //说明就是第0个条目,不需要跟上一个比较，直接显示
                //需要显示的时候设置为可见
                holder.tvLetter.setVisibility(View.VISIBLE);
                holder.tvLetter.setText(firstWord);
            }

            holder.tvName.setText(friend.name);

            return convertView;
        }


    }

    static class ViewHolder {

        private final TextView tvLetter;
        private final TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            tvLetter = view.findViewById(R.id.tv_letter);
            tvName = view.findViewById(R.id.tv_name);
        }
    }
}
