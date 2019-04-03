package com.example.ch05;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TabhostActivity extends FragmentActivity implements View.OnClickListener {


    private ImageView mChatImageView;
    private ImageView mContactsImageView;
    private ImageView mDiscoverImageView;
    private ImageView mMineImageView;

    private TextView mChatTextView;
    private TextView mContactsTextView;
    private TextView mDiscoverTextView;
    private TextView mMineTextView;

    private List<TabInfo> mTabs = new ArrayList<TabInfo>();
    private TabInfo mLastTab;

    private static final int TAB_INDEX_CHAT = 0;
    private static final int TAB_INDEX_CONTACTS = 1;
    private static final int TAB_INDEX_DISCOVER = 2;
    private static final int TAB_INDEX_MINE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);

        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_chat_layout:
                changePage(TAB_INDEX_CHAT);
                break;
            case R.id.tab_contacts_layout:
                changePage(TAB_INDEX_CONTACTS);
                break;
            case R.id.tab_discover_layout:
                changePage(TAB_INDEX_DISCOVER);
                break;
            case R.id.tab_mine_layout:
                changePage(TAB_INDEX_MINE);
                break;
        }
    }

    private void changePage(int index) {
        changeFragment(index);
        changeTab(index);
    }

    private void changeFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        TabInfo tab = mTabs.get(index);
        if (mLastTab == tab) {
            return;
        }
        if (mLastTab != null) {
            transaction.hide(mLastTab.fragment);// 隐藏上一次显示的tab页
        }
        if (tab.fragment == null) {
            tab.fragment = Fragment.instantiate(this, tab.clazz.getName(), tab.args);
            transaction.add(R.id.main_content, tab.fragment);// 如果当前点击的tab页首次创建，将其添加进来
        } else {
            transaction.show(tab.fragment);// 显示当前点击的tab页
        }
        transaction.commitAllowingStateLoss();
        mLastTab = tab;
    }

    private void addTab(Class<? extends Fragment> clazz, Bundle args) {
        TabInfo tabInfo = new TabInfo(clazz, args);
        mTabs.add(tabInfo);
    }

    private class TabInfo {
        private Fragment fragment;
        private Class<?> clazz;// Fragment类的class对象
        private Bundle args;// 传递参数的Bundle

        TabInfo(Class<? extends Fragment> clazz, Bundle args) {
            this.clazz = clazz;
            this.args = args;
        }
    }

    private void changeTab(int index) {
        switch (index) {
            case TAB_INDEX_CHAT:
                setTabIcon(true, false, false, false);
                break;
            case TAB_INDEX_CONTACTS:
                setTabIcon(false, true, false, false);
                break;
            case TAB_INDEX_DISCOVER:
                setTabIcon(false, false, true, false);
                break;
            case TAB_INDEX_MINE:
                setTabIcon(false, false, false, true);
                break;
        }
    }

    private void setTabIcon(boolean isChatSelected, boolean isContactsSelected, boolean isDiscoverSelected, boolean
            isMineSelected) {
        mChatImageView.setSelected(isChatSelected);
        mContactsImageView.setSelected(isContactsSelected);
        mDiscoverImageView.setSelected(isDiscoverSelected);
        mMineImageView.setSelected(isMineSelected);

        mChatTextView.setSelected(isChatSelected);
        mContactsTextView.setSelected(isContactsSelected);
        mDiscoverTextView.setSelected(isDiscoverSelected);
        mMineTextView.setSelected(isMineSelected);
    }

    private void initView() {
        mChatImageView = (ImageView) findViewById(R.id.tab_chat_icon);
        mContactsImageView = (ImageView) findViewById(R.id.tab_contacts_icon);
        mDiscoverImageView = (ImageView) findViewById(R.id.tab_discover_icon);
        mMineImageView = (ImageView) findViewById(R.id.tab_mine_icon);
        mChatTextView = (TextView) findViewById(R.id.tab_chat_text);
        mContactsTextView = (TextView) findViewById(R.id.tab_contacts_text);
        mDiscoverTextView = (TextView) findViewById(R.id.tab_discover_text);
        mMineTextView = (TextView) findViewById(R.id.tab_mine_text);
        findViewById(R.id.tab_chat_layout).setOnClickListener(this);
        findViewById(R.id.tab_contacts_layout).setOnClickListener(this);
        findViewById(R.id.tab_discover_layout).setOnClickListener(this);
        findViewById(R.id.tab_mine_layout).setOnClickListener(this);

        // 根据实际需求，可以通过Bundle往Fragment中传递参数
        Bundle args = new Bundle();
        addTab(ChatFragment.class, args);
        addTab(ContactsFragment.class, null);
        addTab(DiscoverFragment.class, null);
        addTab(MineFragment.class, null);

        // 默认选择第一个tab
        changePage(0);
    }
}
