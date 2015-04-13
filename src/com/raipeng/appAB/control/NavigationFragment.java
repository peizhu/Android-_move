package com.raipeng.appAB.control;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.raipeng.appAB.R;
import com.raipeng.appAB.common.CommonUtils;
public class NavigationFragment extends BaseFragment implements OnClickListener{


	private static final String TAG = NavigationFragment.class.getSimpleName();
	private RelativeLayout shop_layout = null;
	private RelativeLayout order_layout = null;
	private RelativeLayout center_layout = null;
	private RelativeLayout statistics_layout = null;
	private View mMenuView = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		CommonUtils.L(TAG, "onCreateView.");
		mMenuView = inflater.inflate(R.layout.navigation_menu, null);
		initView(mMenuView);
		return mMenuView;
	}

	private void initView(View view) {
		shop_layout=(RelativeLayout)view.findViewById(R.id.shop_layout);
		order_layout=(RelativeLayout)view.findViewById(R.id.order_layout);
		center_layout=(RelativeLayout)view.findViewById(R.id.custom_manage_layout);
		statistics_layout=(RelativeLayout)view.findViewById(R.id.statistics_layout);
		
		shop_layout.setOnClickListener(this);
		order_layout.setOnClickListener(this);
		center_layout.setOnClickListener(this);
		statistics_layout.setOnClickListener(this);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		CommonUtils.L(TAG, "onActivityCreated.");
		mHandler = new Handler(mContext.getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {

				default:
					break;
				}
			}
		};


	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		CommonUtils.L(TAG, "onResume.");
		super.onResume();
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		CommonUtils.L(TAG, "onDetach.");
		super.onDetach();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Fragment newContent = null;
		switch (v.getId()) {
		case R.id.shop_layout:
			newContent = BaseFragmentController.getInstance().getShopFragment();
			break;
		case R.id.order_layout:
			newContent = BaseFragmentController.getInstance().getOrderFragment();
			break;
		case R.id.custom_manage_layout:
			newContent = BaseFragmentController.getInstance().getCustomManageFragment();
			break;
		case R.id.statistics_layout:
			newContent = BaseFragmentController.getInstance().getStatisticsFragment();
			break;
		default:
			break;
		}
		if (newContent != null) {
			switchFragment(newContent);
		}
		
	}
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchContent(fragment);
		}
	}

}
