/*************************************************************************
 *  
 *  Copyright (C) 2013 SuZhou Raipeng Information Technology co., LTD.
 * 
 *                       All rights reserved.
 *
 *************************************************************************/
package com.raipeng.appAB.control;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.raipeng.appAB.common.Constants;
import com.raipeng.appAB.common.NetWorkUtils;

/**
 * @date: 2013-10-28 下午2:07:19
 * 
 * @email: tchen@raipeng.com
 * 
 * @version: V1.0
 * 
 * @description:
 * 
 */
public abstract class BaseActivity extends Activity {

	protected Context context = null;
	protected ProgressDialog pg = null;
	protected NotificationManager notificationManager = null;
	protected BaseApplication mApplication;
	protected Handler mHandler;
	protected Dialog mLoading_dlg;
	public SharedPreferences mPreferences = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApplication = (BaseApplication) getApplication();
		AppManager.getInstance().addActivity(this);
		
		mPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		context = this;
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		pg = new ProgressDialog(context);
	}

	/** 含有标题、内容、两个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
			String positiveText,
			DialogInterface.OnClickListener onPositiveClickListener,
			String negativeText,
			DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}





	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
