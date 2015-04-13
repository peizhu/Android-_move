/*************************************************************************
 *  
 *  Copyright (C) 2013 SuZhou Raipeng Information Technology co., LTD.
 * 
 *                       All rights reserved.
 *
 *************************************************************************/
package com.raipeng.appAB.control;


import com.raipeng.appAB.common.CommonUtils;
import com.raipeng.appAB.common.Constants;
import com.raipeng.appAB.common.ImageLoaderConfig;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

/**
 * @date: 2013-10-28 下午2:14:56
 * 
 * @email: tchen@raipeng.com
 * 
 * @version: V1.0
 * 
 * @description:
 * 
 */
public class BaseApplication extends Application {

	private static final String TAG = BaseApplication.class.getSimpleName();

	public SharedPreferences mSharedPreferences;
	private static BaseApplication mInstance = null;
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		CommonUtils.L(TAG, "onConfigurationChanged");
	}
	@Override
	public void onCreate() {
		super.onCreate();
		CommonUtils.L(TAG, "onCreate");
		ImageLoaderConfig.initImageLoader(this,
				Constants.BASE_IMAGELOADER_CACHE_PATH);
		mInstance = this;
		mSharedPreferences = getSharedPreferences(
				Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		CommonUtils.L(TAG, "onLowMemory");
	}

	public static BaseApplication getInstance() {
		return mInstance;
	}


}
