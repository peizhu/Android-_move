/*************************************************************************
 *  
 *  Copyright (C) 2013 SuZhou Raipeng Information Technology co., LTD.
 * 
 *                       All rights reserved.
 *
 *************************************************************************/
package com.raipeng.appAB.control;

import java.util.HashMap;
import java.util.Map;

import com.raipeng.appAB.control.custommanage.CustommanageFragment;
import com.raipeng.appAB.control.myshop.ShopFragment;
import com.raipeng.appAB.control.order.OrderFragment;
import com.raipeng.appAB.control.statistics.StatisticsFragment;

import android.support.v4.app.Fragment;

/**
 * @version: V1.0
 * @description: BaseFragment控制中心
 * 
 */
public class BaseFragmentController {

	private static BaseFragmentController instance;
	private Map<String, Fragment> mFragmentDatas = new HashMap<String, Fragment>();

	private BaseFragmentController() {
	}

	public static synchronized BaseFragmentController getInstance() {
		if (instance == null) {
			instance = new BaseFragmentController();
		}
		return instance;
	}

	/**
	 * 添加Fragment
	 * 
	 * @param tag
	 * @param fragment
	 */
	public void putFragmentDatas(String tag, Fragment fragment) {
		mFragmentDatas.put(tag, fragment);
	}

	/**
	 * 获取店铺fragment
	 * 
	 * @return
	 */
	public Fragment getShopFragment() {
		Fragment fragment = mFragmentDatas.get(FragmentBuilder.SHOP_FRAGMENT);
		if (fragment == null) {
			fragment = FragmentBuilder.getShopFragment();
			mFragmentDatas.put(FragmentBuilder.SHOP_FRAGMENT, fragment);
		}
		return fragment;
	}

	/**
	 * 获取订单Fragment
	 * 
	 * @return
	 */
	public Fragment getOrderFragment() {
		Fragment fragment = mFragmentDatas
				.get(FragmentBuilder.ORDER_FRAGMENT);
		if (fragment == null) {
			fragment = FragmentBuilder.getOrderFragment();
			mFragmentDatas.put(FragmentBuilder.ORDER_FRAGMENT, fragment);
		}
		return fragment;
	}

	/**
	 * 获取客户管理
	 * 
	 * @return
	 */
	public Fragment getCustomManageFragment() {
		Fragment fragment = mFragmentDatas.get(FragmentBuilder.CUSTOMMANAGE_FRAGMENT);
		if (fragment == null) {
			fragment = FragmentBuilder.getCustomManageFragment();
			mFragmentDatas.put(FragmentBuilder.CUSTOMMANAGE_FRAGMENT, fragment);
		}
		return fragment;
	}

	/**
	 * 获取统计
	 * 
	 * @return
	 */
	public Fragment getStatisticsFragment() {
		Fragment fragment = mFragmentDatas
				.get(FragmentBuilder.STATISTICS_FRAGMENT);
		if (fragment == null) {
			fragment = FragmentBuilder.getStatisticsFragment();
			mFragmentDatas.put(FragmentBuilder.STATISTICS_FRAGMENT, fragment);
		}
		return fragment;
	}




	public static class FragmentBuilder {

		public static final String SHOP_FRAGMENT = "ShopFragment";
		public static final String ORDER_FRAGMENT = "OrderFragment";
		public static final String CUSTOMMANAGE_FRAGMENT = "CustommanageFragment";
		public static final String STATISTICS_FRAGMENT = "StatisticsFragment";

		/**
		 * 获取店铺
		 * 
		 * @return
		 */
		public static Fragment getShopFragment() {
			ShopFragment fragment = new ShopFragment();
			return fragment;
		}

		/**
		 * 获取订单
		 * 
		 * @return
		 */
		public static Fragment getOrderFragment() {
			OrderFragment fragment = new OrderFragment();
			return fragment;
		}

		/**
		 * 获取客户管理
		 * 
		 * @return
		 */
		public static Fragment getCustomManageFragment() {
			CustommanageFragment fragment = new CustommanageFragment();
			return fragment;
		}

		/**
		 * 获取统计
		 * 
		 * @return
		 */
		public static Fragment getStatisticsFragment() {
//			PictureShowFragment fragment = new PictureShowFragment();
			StatisticsFragment fragment = new StatisticsFragment();
			return fragment;
		}

	}
}
