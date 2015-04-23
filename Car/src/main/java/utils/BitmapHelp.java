package utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.example.administrator.car.R;
import com.lidroid.xutils.BitmapUtils;

import java.io.File;

@SuppressLint("NewApi")
public class BitmapHelp {

	private static final String dirName = "ixdf" + File.separator + "cache" + File.separator;
	private static BitmapUtils bitmapUtils;
	private static BitmapUtils bitmapUtils_headIcon;

	private BitmapHelp() {
	}

	public static BitmapUtils getDefaultBitmapUtils(Context context) {
		if (bitmapUtils == null) {
			bitmapUtils = getBitmapUtils(context, R.drawable.icon_default);
		}
		return bitmapUtils;
	}

	public static BitmapUtils getBitmapUtils(Context context, int resId) {
		BitmapUtils bitmapUtils = new BitmapUtils(context, getDiskCacheDir(context));
		bitmapUtils.configDefaultLoadFailedImage(resId);
		bitmapUtils.configDefaultLoadingImage(resId);
		bitmapUtils.configDiskCacheEnabled(true);
		bitmapUtils.configMemoryCacheEnabled(true);
		bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
		return bitmapUtils;
	}

	public static String getDiskCacheDir(Context context) {
		String cachePath = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			File externalCacheDir = context.getExternalCacheDir();
			if (externalCacheDir != null) {
				cachePath = externalCacheDir.getPath();
			}
		}
		if (cachePath == null) {
			File cacheDir = Environment.getExternalStorageDirectory();
			if (cacheDir != null && cacheDir.exists()) {
				cachePath = cacheDir.toString();
			}
		}
		if (cachePath == null) {
			File cacheDir = new File("/storage/sdcard0/");

			if (cacheDir != null && cacheDir.exists()) {
				cachePath = cacheDir.getPath();
			}
		}
		if (cachePath == null) {
			File cacheDir = context.getCacheDir();
			if (cacheDir != null && cacheDir.exists()) {
				cachePath = cacheDir.getPath();
			}
		}
		return cachePath + File.separator + dirName;
	}

}
