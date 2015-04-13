package com.raipeng.appAB.control.myshop;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.raipeng.appAB.R;
import com.raipeng.appAB.control.SettingActivity;
import com.raipeng.appAB.control.BaseFragment;

/**
 * @author Administrator 店铺
 */
public class ShopFragment extends BaseFragment {
	private ImageView menu_btn;
	private FragmentManager fragmentManager;
	private RadioGroup radioGroup;
	private ViewPager viewpage;
	private RadioButton hot_radiobtn, classfy_radiobtn, create_radiobtn;
	private ArrayList<Fragment> fragments = null;
	private PageAdapter adapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.myshop, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		viewpage = (ViewPager) view.findViewById(R.id.viewPager);
		menu_btn = (ImageView) view.findViewById(R.id.menu_btn);
		radioGroup = (RadioGroup) view.findViewById(R.id.shop_radiogroup);
		hot_radiobtn = (RadioButton) view.findViewById(R.id.hotproduct_radiobtn);
		classfy_radiobtn = (RadioButton) view.findViewById(R.id.classfylist_radiobtn);
		create_radiobtn = (RadioButton) view.findViewById(R.id.createproduct_radiobtn);

		menu_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mContext.startActivity(new Intent(mContext, SettingActivity.class));
			}
		});
		fragmentManager = getFragmentManager();
		fragments = new ArrayList<Fragment>();
		fragments.add(new HotFragment());
		fragments.add(new ClassfyFragment());
		fragments.add(new CreateproductFragment());
		adapter = new PageAdapter(fragmentManager, fragments);
		viewpage.setAdapter(adapter);
		initfragments(R.id.hotproduct_radiobtn);
		setStatus(R.id.hotproduct_radiobtn);
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				setStatus(checkedId);
				initfragments(checkedId);
			}

		});
		
		viewpage.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				switch (position) {
					case 0:
						setStatus(R.id.hotproduct_radiobtn);
						break;
					case 1:
						setStatus(R.id.classfylist_radiobtn);
						break;
					case 2:
						setStatus(R.id.createproduct_radiobtn);
					default:
						break;
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	/**fragment切换
	 * @param id
	 */
	private void initfragments(int id) {
		switch (id) {
			case R.id.hotproduct_radiobtn:
				viewpage.setCurrentItem(0);
				break;
			case R.id.classfylist_radiobtn:
				viewpage.setCurrentItem(1);
				break;
			case R.id.createproduct_radiobtn:
				viewpage.setCurrentItem(2);
				break;
		}
	}

	/**
	 * 初始化每个radiobutton颜色
	 */
	private void setStatus(int index) {
		hot_radiobtn.setTextColor(Color.BLACK);
		classfy_radiobtn.setTextColor(Color.BLACK);
		create_radiobtn.setTextColor(Color.BLACK);

		switch (index) {
			case R.id.hotproduct_radiobtn:
				hot_radiobtn.setTextColor(Color.RED);
				break;
			case R.id.classfylist_radiobtn:
				classfy_radiobtn.setTextColor(Color.RED);
				break;
			case R.id.createproduct_radiobtn:
				create_radiobtn.setTextColor(Color.RED);
				break;
		}
	}

	private class PageAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragments = new ArrayList<Fragment>();
		public FragmentManager fm;

		public PageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
			super(fragmentManager);
			this.fragments = fragments;
			this.fm = fm;
		}

		@Override
		public Fragment getItem(int index) {
			return fragments.get(index);
		}

		@SuppressLint("CommitTransaction")
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Fragment fragment = (Fragment) super.instantiateItem(container, position);
			fm.beginTransaction().show(fragment).commit();
			return fragment;
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			Fragment fragment = fragments.get(position);
			fm.beginTransaction().hide(fragment).commit();
		}

	}
}
