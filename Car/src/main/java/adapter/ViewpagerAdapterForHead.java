package adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import CustomView.MyViewPager;

public class ViewpagerAdapterForHead extends PagerAdapter {
	List<View> list;

	Context context;
	MyViewPager viewPager;
	List<Map<String, Object>> list_map;

	public ViewpagerAdapterForHead(List<View> list, Context context,
                                   MyViewPager viewPager) {
		this.list = list;
		this.context = context;
		this.viewPager = viewPager;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		View view=list.get(position);
		container.addView(view);
		return list.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));
	}

	public void addList(List<Map<String, Object>> list_map) {
		this.list_map = list_map;
	}
}
