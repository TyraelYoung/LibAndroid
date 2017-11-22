package wang.tyrael.net;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import wang.tyrael.android.application.ApplicationHolder;
import wang.tyrael.ui.DensityUtil;

public class Utils {

	private static final String TAG = "Utils";
	public static int densityDpi;

	public static void setDensityDpi(Activity context) {
		DisplayMetrics metric = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metric);
		densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
		System.out.println("setDensityDpi = " + densityDpi);
	}

	@Deprecated
	public static int getWindowWidth(Activity activity){

		return DensityUtil.getScreenSize(ApplicationHolder.getApplication())[0];

		/*WindowManager wm = (WindowManager)activity .getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		return width;*/
		
	}

	@Deprecated
	public static int getWindowWidth(){

		return DensityUtil.getScreenSize(ApplicationHolder.getApplication())[0];

		/*WindowManager wm = (WindowManager)activity .getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		return width;*/

	}

	/**
	 * 获得状态栏的高度
	 * @param context
	 * @return
	 * by Hankkin at:2015-10-07 21:16:43
	 */
	public static int getStatusHeight(Context context) {

		int statusHeight = -1;
		try {
			Class  clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

	/**
	 * 动态设置view的margin
	 * @param v		view
	 * @param l		marginleft
	 * @param t		margintop
	 * @param r		marginright
	 * @param b		marginbottom
	 */
	public static void setMargins (View v, int l, int t, int r, int b) {
		if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
			p.setMargins(l, t, r, b);
			v.requestLayout();
		}
	}
}
