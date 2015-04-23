package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.car.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.BitmapHelp;

public class MyAdapterShouYe extends BaseAdapter {

	private List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
	private Context mContext;
	public BitmapUtils mBitmapUtils;
	private LayoutInflater mInflater;


	public MyAdapterShouYe(Context context) {
		super();
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
		mBitmapUtils = BitmapHelp.getDefaultBitmapUtils(mContext);
	}

	public void addList(List<Map<String, Object>> list) {
		this.mList.addAll(list);
		notifyDataSetChanged();
	}

	public void updateList(List<Map<String, Object>> list) {
		this.mList = list;
		notifyDataSetChanged();
	}

	public void clearData() {
		this.mList.clear();
	}

	@Override
	public int getCount() {
		return mList != null ? mList.size() : 0;
	}

	@Override
	public Object getItem(int arg0) {
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		final Map<String, Object> item = mList.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.shouye_item, viewGroup,
					false);
			holder.listview_item_channename = (ImageView) convertView
					.findViewById(R.id.listview_item_channename);
			holder.listview_item_comments = (TextView) convertView
					.findViewById(R.id.listview_item_comments);
			holder.listview_item_Image = (ImageView) convertView
					.findViewById(R.id.listview_item_Image);
			holder.listview_item_Time = (TextView) convertView
					.findViewById(R.id.listview_item_Time);
			holder.listview_item_Title = (TextView) convertView
					.findViewById(R.id.listview_item_Title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.listview_item_Title.setText(item.get("title").toString());
		int count = Integer.parseInt(item.get("count").toString());
		if (count == 0) {
			holder.listview_item_comments.setText("抢沙发");
		}else {
			holder.listview_item_comments.setText(item.get("count").toString()
					+ "评论");
		}
		holder.listview_item_Time.setText(item.get("pubDate").toString());

		String channelName = item.get("channelName") + "";
		holder.listview_item_channename.setVisibility(View.VISIBLE);
		if (channelName.equals("新车")) {
			holder.listview_item_channename
					.setBackgroundResource(R.drawable.channename_xinche);
		} else if (channelName.equals("技术")) {
			holder.listview_item_channename
					.setBackgroundResource(R.drawable.channename_jishu);
		} else if (channelName.equals("行业")) {
			holder.listview_item_channename
					.setBackgroundResource(R.drawable.channename_hangye);
		} else {
			holder.listview_item_channename.setVisibility(View.GONE);
		}
		mBitmapUtils.display(holder.listview_item_Image, item.get("image")
				.toString());

		return convertView;

	}
	private class ViewHolder {
		private ImageView listview_item_Image;
		private TextView listview_item_Title;
		private TextView listview_item_comments;
		private TextView listview_item_Time;
		private ImageView listview_item_channename;
	}

}
