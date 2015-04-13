package com.raipeng.appAB.control.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raipeng.appAB.R;
import com.raipeng.appAB.control.BaseFragment;
public class PendingOrederFragment extends BaseFragment{
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View view =inflater.inflate(R.layout.order_pending, container, false);
	return view;
}
}
