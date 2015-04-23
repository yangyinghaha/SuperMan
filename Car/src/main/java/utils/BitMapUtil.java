package utils;


import android.content.Context;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;

public class BitMapUtil {
	public static void setBitMap(Context context,ImageView img,String url){
		BitmapUtils bitmapUtils = new BitmapUtils(context);
		bitmapUtils.display(img, url);
	}

}
