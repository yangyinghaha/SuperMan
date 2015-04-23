package com.example.administrator.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import CustomView.Titlebar;
import fragment.BbsFragment;
import fragment.ZixunFragment;

/**
 * Created by Administrator on 2015/4/20.
 */
public class ZhuyaoActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener {
    private ArrayList<Fragment> fragmentlist;
    private long mExitTime;
    private FragmentManager fm ;
    private Titlebar mTitlebar;
    private FrameLayout fragment_container;
    private RadioGroup radioGroup;
    private int f_last;
    private ImageButton fl_title_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuyao_activity_main);
        ZixunFragment fragment1=new ZixunFragment();
        BbsFragment fragment2=new BbsFragment();
        fragmentlist=new ArrayList<Fragment>();
        fm = getSupportFragmentManager();
        fragmentlist.add(fragment1);
        fragmentlist.add(fragment2);
        fragmentlist.add(fragment2);
        fragmentlist.add(fragment2);
        fragmentlist.add(fragment2);
        fm.beginTransaction()
                .add(R.id.fragment_container, fragmentlist.get(0), "0").commit();
        initView();
    }

    private void initView() {
        fragment_container = (FrameLayout) findViewById(R.id.fragment_container);
        mTitlebar = (Titlebar) findViewById(R.id.titlebar_main);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup_main);
        radioGroup.setOnCheckedChangeListener(this);
        fl_title_search = mTitlebar.getFl_title_search();
        fl_title_search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhuyaoActivity.this,
                        SearchDetailActivity.class);
//                intent.putExtra("url", Contents.sousuo);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.zixun_main:
                switchContent(0);
                mTitlebar.setVisibility(View.VISIBLE);
                mTitlebar.setMode("汽车之家");
                break;
            case R.id.luntan_main:
                switchContent(1);
                mTitlebar.setVisibility(View.VISIBLE);
                mTitlebar.setMode("论坛");
                break;
            case R.id.zhaoche_main:
                switchContent(2);
                mTitlebar.setVisibility(View.VISIBLE);
                mTitlebar.setMode("找车");
                break;
            case R.id.youhui_main:
                switchContent(3);
                mTitlebar.setVisibility(View.VISIBLE);
                mTitlebar.setMode("优惠");
                break;
            case R.id.wode_main:
                switchContent(4);
                mTitlebar.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    //点击下方按钮时切换Fragment的事件处理
    public void switchContent(int f_next) {
        Fragment from = fragmentlist.get(f_last);
        Fragment to = fragmentlist.get(f_next);
        if (f_last != f_next) {
            f_last = f_next;
            //加入系统预定义的动画透明化进入时0.5秒,结束0.4秒
            FragmentTransaction transaction = fm.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(from).add(R.id.fragment_container, to)
                        .commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "在按一次退出汽车网", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
