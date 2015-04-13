package com.raipeng.appAB.control.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raipeng.appAB.R;
import com.raipeng.appAB.control.BaseFragment;

/**
 * @author Administrator
 *销售统计
 */
public class StatisticsFragment extends BaseFragment{
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View view =inflater.inflate(R.layout.statistics, container, false);
	return view;
}
}
