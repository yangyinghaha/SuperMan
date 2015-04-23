package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.administrator.car.R;

import java.util.ArrayList;

import CustomView.MyFragment;
import CustomView.NavigationBar;
import CustomView.TabManagerView;
import adapter.ZiXunFragmentPagerAdapter;
import zixunFragment.ShouYe;


/**
 * Created by Administrator on 2015/4/20.
 */
public class ZixunFragment extends Fragment {

    private View view;
    LinearLayout mRadioGroup_content;
    LinearLayout ll_more_columns;
    RelativeLayout rl_column;
    private ViewPager mViewPager;
    private ImageView button_more_columns;
    private int columnSelectIndex = 0;
    private int mScreenWidth = 0;
    private int mItemWidth = 0;
    public ImageView shade_left;
    public ImageView shade_right;
    private ArrayList<MyFragment> fragments = new ArrayList<MyFragment>();
    private TabManagerView tabManager;
    private NavigationBar navigationBar;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private CheckBox cb6;
    private CheckBox cb7;
    private CheckBox cb0;
    private Button btn_no;
    private Button btn_yes;
    private MyFragment frag0 = new ShouYe(0, "首页");
    private MyFragment frag1 = new ShouYe(1, "新车");
    private MyFragment frag2 = new ShouYe(2, "行情");
    private MyFragment frag3 = new ShouYe(3, "导购");
    private MyFragment frag4 = new ShouYe(4, "文化");
    private MyFragment frag5 = new ShouYe(5, "评测");
    private MyFragment frag6 = new ShouYe(6, "视频");
    private MyFragment frag7 = new ShouYe(7, "技术");
    private ZiXunFragmentPagerAdapter mAdapter;
    private ArrayList<MyFragment> all_fragments = new ArrayList<MyFragment>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.zixun_fragment,null,false);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        mItemWidth = dm.widthPixels / 7;// 一个Item宽度为屏幕的1/7
        initView();
        setChangeView(true);
        setOnListener();
        return view;
    }
    private void initView() {

        navigationBar = (NavigationBar) view
                .findViewById(R.id.navigationBar);
        mRadioGroup_content = (LinearLayout) view
                .findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) view
                .findViewById(R.id.ll_more_columns);
        rl_column = (RelativeLayout) view.findViewById(R.id.rl_column);
        button_more_columns = (ImageView) view
                .findViewById(R.id.button_more_columns);
        mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);
        shade_left = (ImageView) view.findViewById(R.id.shade_left);
        shade_right = (ImageView) view.findViewById(R.id.shade_right);
        tabManager = (TabManagerView) view.findViewById(R.id.tabManager);
        cb1 = tabManager.getCb1();
        cb2 = tabManager.getCb2();
        cb3 = tabManager.getCb3();
        cb4 = tabManager.getCb4();
        cb5 = tabManager.getCb5();
        cb6 = tabManager.getCb6();
        cb7 = tabManager.getCb7();
        cb0 = tabManager.getCb0();
        btn_yes = tabManager.getBtn_yes();
        btn_no = tabManager.getBtn_no();
    }

    private void setChangeView(boolean isfirst) {
        initColumnData(isfirst);
        initTabColumn();
        setViewPager();
    }

    private void initColumnData(boolean isfirst) {
        // 第一次就显示5个
        if (isfirst) {
            fragments.add(frag0);
            fragments.add(frag1);
            fragments.add(frag2);
            fragments.add(frag3);
            fragments.add(frag4);
//			fragments.add(frag5);
//			fragments.add(frag6);
//			fragments.add(frag7);
            all_fragments = fragments;
        }
    }
    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count = fragments.size();
        navigationBar.setParam(getActivity(), mScreenWidth,
                mRadioGroup_content, shade_left, shade_right, ll_more_columns,
                rl_column);

        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    mItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 10;
            params.rightMargin = 10;
            TextView localTextView = new TextView(getActivity());
            localTextView.setTextAppearance(getActivity(),
                    R.style.top_category_scroll_view_item_text);
            localTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
            localTextView.setGravity(Gravity.CENTER);
            localTextView.setPadding(5, 0, 5, 0);
            localTextView.setId(i);
            localTextView.setText(fragments.get(i).getTitle());
            localTextView.setTextColor(getResources().getColorStateList(
                    R.color.top_category_scroll_text_color_day));
            if (columnSelectIndex == i) {
                localTextView.setSelected(true);
            }
            localTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else {
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                }
            });
            mRadioGroup_content.addView(localTextView, i, params);
        }
    }

    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            navigationBar.smoothScrollTo(i2, 0);
        }
        // 判断是否选中
        for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }


    public void setViewPager() {
        mAdapter = new ZiXunFragmentPagerAdapter(getFragmentManager(),
                fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(pageListener);
    }

    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    public void setOnListener() {
        button_more_columns.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tabManager.setVisibility(View.VISIBLE);
                tabManager.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });
                YoYo.with(Techniques.SlideInRight).duration(500)
                        .playOn(tabManager);
            }
        });

        cb0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                isShowTab(isChecked, frag0);
            }
        });
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                isShowTab(isChecked, frag1);
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                isShowTab(isChecked, frag2);

            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                isShowTab(isChecked, frag3);

            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                isShowTab(isChecked, frag4);

            }
        });
        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                isShowTab(isChecked, frag5);
            }
        });
        cb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                isShowTab(isChecked, frag6);
            }
        });
        cb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                isShowTab(isChecked, frag7);
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.SlideOutRight).duration(500)
                        .playOn(tabManager);
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setChangeView(false);
                YoYo.with(Techniques.SlideOutRight).duration(800)
                        .playOn(tabManager);
                //摇晃rl_column组件
                YoYo.with(Techniques.Wobble).duration(800).playOn(rl_column);
            }
        });
    }
    public void isShowTab(boolean isChecked, MyFragment frag) {
        if (isChecked) {
            if (!fragments.contains(frag)) {
                fragments.add(frag);
            }
        } else {
            fragments.remove(frag);
        }
    }
}

