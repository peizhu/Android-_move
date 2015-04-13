package com.raipeng.appAB.control.order;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.raipeng.appAB.R;
import com.raipeng.appAB.control.BaseFragment;
public class OrderFragment extends BaseFragment{
	private FragmentManager fragmentManager;
	private FragmentTransaction transaction;
	private RadioGroup radioGroup;
	private RadioButton pending_orders_rb, pending_pay_rb, order_done_rb;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View view =inflater.inflate(R.layout.ordermanage, container,false);
	initView(view);
	return view;
}
private void initView(View view){
	radioGroup = (RadioGroup) view.findViewById(R.id.order_radiogroup);
	pending_orders_rb = (RadioButton) view
			.findViewById(R.id.pending_orders);
	pending_pay_rb = (RadioButton) view
			.findViewById(R.id.pending_pay);
	order_done_rb = (RadioButton) view
			.findViewById(R.id.order_done);
	fragmentManager =getFragmentManager();
	setStatus(R.id.pending_orders);
	getInstanceByIndex(R.id.pending_orders);
	radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup arg0, int checkedId) {
			setStatus(checkedId);
			getInstanceByIndex(checkedId);
		}
	});
}
/**
 * 初始化每个radiobutton颜色
 */
private void setStatus(int index) {
	pending_orders_rb.setTextColor(Color.BLACK);
	pending_pay_rb.setTextColor(Color.BLACK);
	order_done_rb.setTextColor(Color.BLACK);

	switch (index) {
	case R.id.pending_orders:
		pending_orders_rb.setTextColor(Color.RED);
		break;
	case R.id.pending_pay:
		pending_pay_rb.setTextColor(Color.RED);
		break;
	case R.id.order_done:
		order_done_rb.setTextColor(Color.RED);
		break;
	}
}
private  void getInstanceByIndex(int index) {
	transaction = fragmentManager.beginTransaction();
	Fragment fragment = null;
	switch (index) {
	case R.id.pending_orders:
		fragment = new PendingOrederFragment();
		break;
	case R.id.pending_pay:
		fragment = new PendingPayFragment();
		break;
	case R.id.order_done:
		fragment = new OrderDoneFragment();
		break;
	}
	transaction.replace(R.id.order_content, fragment);
	transaction.commit();
}
}
