/*************************************************************************
 *  
 *  Copyright (C) 2013 SuZhou Raipeng Information Technology co., LTD.
 * 
 *                       All rights reserved.
 *
 *************************************************************************/
package com.raipeng.appAB.control;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;


/** 
 *  @date: 2013-9-17 下午4:06:18
 *
 *  @email: tchen@raipeng.com
 *
 *  @version: 1.0
 *
 *  @description: 应用程序中所有Activity管理�?
 *
 */
public class AppManager {
	private static Stack<Activity> mActivityStack;
	private static AppManager mAppManager;
//	private static List<Activity> acitivityList = new ArrayList<Activity>();
	private AppManager() {
	}

	/**
	 * 单一实例
	 */
	public static AppManager getInstance() {
		if (mAppManager == null) {
			mAppManager = new AppManager();
		}
		return mAppManager;
	}

	/**
	 * 添加Activity到堆�?
	 */
	public void addActivity(Activity activity) {
		if (mActivityStack == null) {
			mActivityStack = new Stack<Activity>();
		}
		mActivityStack.add(activity);
	}

	/**
	 * 获取栈顶Activity（堆栈中�?���?��压入的）
	 */
	public Activity getTopActivity() {
		Activity activity = mActivityStack.lastElement();
		return activity;
	}

	/**
	 * 结束栈顶Activity（堆栈中�?���?��压入的）
	 */
	public void killTopActivity() {
		Activity activity = mActivityStack.lastElement();
		killActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void killActivity(Activity activity) {
		if (activity != null) {
			mActivityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void killActivity(Class<?> cls) {
		for (Activity activity : mActivityStack) {
			if (activity.getClass().equals(cls)) {
				killActivity(activity);
			}
		}
	}

	/**
	 * 结束�?��Activity
	 */
	public void killAllActivity() {
		for (int i = 0, size = mActivityStack.size(); i < size; i++) {
			if (null != mActivityStack.get(i)) {
				mActivityStack.get(i).finish();
			}
		}
		mActivityStack.clear();
	}

	/**
	 * �?��应用程序
	 */
	public void AppExit(Context context) {
		try {
			killAllActivity();
			ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {}
	}
	

//	public static void addAllActivity(Activity activity) {
//		acitivityList.add(activity);
//	}
//
//	public static void removeActivity(Activity activity) {
//		acitivityList.remove(activity);
//	}
//
//	public static void finishProgrom() {
//		for (Activity activity : acitivityList) {
//			if (null != activity) {
//				activity.finish();
//			}
//		}
//		android.os.Process.killProcess(android.os.Process.myPid());
//	}
}
