/*************************************************************************
 *  
 *  Copyright (C) 2013 SuZhou Raipeng Information Technology co., LTD.
 * 
 *                       All rights reserved.
 *
 *************************************************************************/
package com.raipeng.appAB.common;
import java.lang.reflect.Field;
import java.util.UUID;

import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.ColorMatrixColorFilter;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
/**
 * @date: 2013-10-28 下午2:16:20
 * 
 * @email: tchen@raipeng.com
 * 
 * @version: V1.0
 * 
 * @description: 公用工具类
 * 
 */
public class CommonUtils {
	protected static ProgressDialog mProgressDialog;
	// 按钮pressed后的状态
	public final static float[] BT_PRESSED = new float[] { 1, 0, 0, 0, -50, 0,
			1, 0, 0, -50, 0, 0, 1, 0, -50, 0, 0, 0, 1, 0 };

	// 按钮初始的状态
	public final static float[] BT_FORMER = new float[] { 1, 0, 0, 0, 0, 0, 1,
			0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };

	public static void L(String TAG, String msg) {
		Log.i("TAG", TAG + " >>>>>>>> " + msg);
	}

	public static void E(String TAG, String msg) {
		Log.e("TAG", TAG + ">>>>>>>> " + msg);
	}

	public static void D(String TAG, String msg) {
		Log.d("TAG", TAG + ">>>>>>>> " + msg);
	}

	public static void showLoadingDialog(Context context,boolean show, String msg) {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(context);
		}
		mProgressDialog.setCancelable(true);
		mProgressDialog.setMessage(TextUtils.isEmpty(msg) ? "请稍等!" : msg);
		if (show) {
			mProgressDialog.show();
		} else {
			mProgressDialog.dismiss();
		}
	}
	/**
	 * 当按钮按下时的touch事件
	 */
	private static OnTouchListener listener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_PRESSED));
				v.setBackgroundDrawable(v.getBackground());
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_FORMER));
				v.setBackgroundDrawable(v.getBackground());
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_FORMER));
				v.setBackgroundDrawable(v.getBackground());
			}
			return false;
		}
	};

	/**
	 * 当View被按下时，修改其Alpha值，使得按钮变暗
	 * 
	 * @param v
	 */
	public static void onViewStateChanged(View v) {
		v.setOnTouchListener(listener);
	}

	/**
	 * 显示Toast消息
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示简单的AlertDialog
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 */
	public static void showAlert(Context context, String title, String msg) {
		Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton("确定", null);
		builder.create();
		builder.show();
	}


	// 获取UUID
	public static String newRandomUUID() {
		String uuidRaw = UUID.randomUUID().toString();
		return uuidRaw.replaceAll("-", "");
	}
	/**
	 * 获取状态栏高度 下面方法在oncreate中调用时获得的状态栏高度为0，不能用 Rect frame = new Rect();
	 * getWindow().getDecorView().getWindowVisibleDisplayFrame(frame); int
	 * statusBarHeight = frame.top;
	 */
	public static int getStatusBarHeight(Context context) {
		try {
			Class<?> c = Class.forName("com.android.internal.R$dimen");
			Object obj = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = Integer.parseInt(field.get(obj).toString());
			return context.getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取测试设备的识别信息，用于友盟统计
	 * @param context
	 * @return
	 */
	public static String getDeviceInfo(Context context) {
		try {
			org.json.JSONObject json = new org.json.JSONObject();
			android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			String device_id = tm.getDeviceId();

			android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);

			String mac = wifi.getConnectionInfo().getMacAddress();
			json.put("mac", mac);

			if (TextUtils.isEmpty(device_id)) {
				device_id = mac;
			}

			if (TextUtils.isEmpty(device_id)) {
				device_id = android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
			}

			json.put("device_id", device_id);

			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getVerName(Context context) {
		String verName = "";
		try {
			verName = context.getPackageManager()
					.getPackageInfo(context.getPackageName(),
							PackageManager.GET_CONFIGURATIONS).versionName;
			Log.i("****版本名****", verName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return verName;
	}
	/**
	 * 显示或隐藏输入法键盘
	 * 
	 * @param context
	 */
	public static void showInputMethod(Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	// 判断sd卡是否存在
		public static boolean isSdCardExsit() {
			return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		}
}
