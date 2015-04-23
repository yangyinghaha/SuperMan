package zixunFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.car.R;
import com.example.administrator.car.SearchDetailActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import CustomView.MyFragment;
import CustomView.MyViewPager;
import adapter.MyAdapterShouYe;
import adapter.ViewpagerAdapterForHead;
import data.Contents;
import utils.BitMapUtil;
import utils.ParseJson;
import utils.StringJoint;
import xlistview.XListView;

public class Fragment_ZiXun_base extends MyFragment implements
        XListView.IXListViewListener {

    public Fragment_ZiXun_base(int fid, String title) {
        super(fid, title);
    }

    /** 全部的数据集合 */
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    /** 整个的下拉刷新listview */
	@ViewInject(R.id.listView_fragment_shouye)
	XListView listView_fragment_shouye;

    /** 基础 listview 的 adapter */
    private MyAdapterShouYe adapter;
    /** 头部的 viewpager 需要的 view 集合 */
    List<View> view_list = new ArrayList<View>();
    /** 头部的 放 viewpager的 框架 */
    View headView;
    /** 头部的 viewpager */
    MyViewPager myViewPager;
    /** 头部的 viewpager适配器 */
    ViewpagerAdapterForHead adapterForHead;
    /** 下面的 listview */
    ListView down_listview;
    int position2;
    /** 包含两个集合 */
    List<Object> list_obj;
    /** 首页 viewpage 的数据 */
    List<Map<String, Object>> list2;
    //	AlertDialog dialog;
//	CustomProgressDialog myDialog;
    boolean isfirst = true;
    boolean isfirst2 = true;
    private int pagenumber = 1;
    View view;
    String str;

    Handler handler;
    HttpUtils http;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(
                R.layout.shouye, container, false);
        ViewUtils.inject(getActivity());
        initView();
        creatHeadViewPage();
//		myDialog.show();
        initData();
        return view;
    }

    private void initView() {
        if (adapter == null) {
            adapter = new MyAdapterShouYe(getActivity());
        }
        headView = LayoutInflater.from(getActivity()).inflate(
                R.layout.viewpager_head_layout, null);
        myViewPager = (MyViewPager) headView
                .findViewById(R.id.myViewpager_head);
		listView_fragment_shouye = (XListView) view
				.findViewById(R.id.listView_fragment_shouye);
		listView_fragment_shouye.setPullLoadEnable(true);
		listView_fragment_shouye.setPullRefreshEnable(true);

		listView_fragment_shouye.setOnScrollListener(new PauseOnScrollListener(
				adapter.mBitmapUtils, false, true));
        handler = new Handler();

		listView_fragment_shouye.setAdapter(adapter);
		listView_fragment_shouye.setXListViewListener(this);
//		myDialog = CustomProgressDialog.createDialog(getActivity());
        http = new HttpUtils();
    }

    public String setStr() {
        return Contents.shouye;
    }

    protected void initData() {
        isfirst = true;
		listView_fragment_shouye.setRefreshing();
    }

    @Override
    public void onRefresh() {

        pagenumber = 1;
        networkGetData();
    }

    @Override
    public void onLoadMore() {
        networkGetData();
    }

    private void onCompleted() {
		listView_fragment_shouye.stopRefresh();
		listView_fragment_shouye.stopLoadMore();
		listView_fragment_shouye.setRefreshTime(getTime());
    }

    private void networkGetData() {

        http.send(HttpRequest.HttpMethod.GET,
                setStr().replace("pagenumber", pagenumber + ""),
                new RequestCallBack<String>() {

                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        onCompleted();
//						Toast.makeText(getActivity(), "网络异常",
//								Toast.LENGTH_SHORT).show();
//						myDialog.dismiss();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        String result = arg0.result;

                        if (result != null) {
                            if (pagenumber == 1) {
                                adapter.clearData();
                            }
                            List<Map<String, Object>> listadd = ParseJson
                                    .parseJson(result, "data");
                            list.addAll(listadd);
                            adapter.addList(listadd);
//							myDialog.dismiss();
//							Toast.makeText(getActivity(),
//									"新获取了" + listadd.size() + "条数据",
//									Toast.LENGTH_SHORT).show();

							jump();
                        }
                        onCompleted();
                        pagenumber++;
                    }

                });

    }

    private void creatHeadViewPage() {
        if (setStr().equals(Contents.shouye)) {
            http.send(HttpRequest.HttpMethod.GET, setStr(),
                    new RequestCallBack<String>() {

                        @Override
                        public void onFailure(HttpException arg0, String arg1) {
                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> arg0) {
                            String result = arg0.result;
                            List<Map<String, Object>> listdown = ParseJson
                                    .parseJson(result, "focus");
                            initHeadViewPage(listdown);

                        }
                    });
        } else {

            http.send(HttpRequest.HttpMethod.GET, setStr(),
                    new RequestCallBack<String>() {

                        @Override
                        public void onFailure(HttpException arg0, String arg1) {
                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> arg0) {
                            String result = arg0.result;
                            List<Map<String, Object>> listdown = ParseJson
                                    .parseJson(result, "data");
                            initHeadViewPage(listdown);

                        }
                    });
        }

    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA)
                .format(new Date());
    }

	public void jump() {
		listView_fragment_shouye
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						String old_url = (String) list.get(position - 2).get(
								"url");
						String url = StringJoint.joinString(old_url);
						Intent intent = new Intent(getActivity(),
                                SearchDetailActivity.class);
						intent.putExtra("url", url);
						intent.putExtra("title", (String) list
								.get(position - 2).get("title"));
						startActivity(intent);

					}
				});

	}

    public void initHeadViewPage(final List<Map<String, Object>> listdown) {
        view_list.clear();
        if (listdown.size() > 3 && listdown.size() < 10) {

            for (int i = 0; i < listdown.size(); i++) {
                Map<String, Object> map = listdown.get(i);
                View view = LayoutInflater.from(getActivity()).inflate(
                        R.layout.view_myviewpage_child, null);
                ImageView iv = (ImageView) view
                        .findViewById(R.id.imv_myViewpage_child);
                TextView tv = (TextView) view
                        .findViewById(R.id.tv_myViewpage_child);
                BitMapUtil.setBitMap(getActivity(), iv,
                        (String) map.get("image"));
                tv.setVisibility(View.GONE);
                view_list.add(view);

            }
        } else {
            for (int i = 0; i < 4; i++) {
                Map<String, Object> map = listdown.get(i + 3);
                View view = LayoutInflater.from(getActivity()).inflate(
                        R.layout.view_myviewpage_child, null);
                ImageView iv = (ImageView) view
                        .findViewById(R.id.imv_myViewpage_child);
                TextView tv = (TextView) view
                        .findViewById(R.id.tv_myViewpage_child);
                BitMapUtil.setBitMap(getActivity(), iv,
                        (String) map.get("image"));
                tv.setText((CharSequence) map.get("title"));
                view_list.add(view);
            }
        }
        adapterForHead = new ViewpagerAdapterForHead(view_list, getActivity(),
                myViewPager);
        adapterForHead.addList(listdown);
        adapterForHead.notifyDataSetChanged();
        myViewPager.setAdapter(adapterForHead);
		listView_fragment_shouye.addHeaderView(headView);
        myViewPager.setOnSingleTouchListener(new MyViewPager.OnSingleTouchListener() {

            @Override
            public void onSingleTouch(View v) {
                String old_url = (String) listdown.get(
                        myViewPager.getCurrentItem()).get("url");
                String url = StringJoint.joinString(old_url);
                Intent intent = new Intent(getActivity(),
                        SearchDetailActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title",
                        (String) listdown.get(myViewPager.getCurrentItem())
                                .get("title"));
                startActivity(intent);
            }
        });
    }

}
