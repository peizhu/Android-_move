package com.raipeng.appAB.control;

import com.raipeng.appAB.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
public class SettingActivity extends BaseActivity{
	private ImageView common_back;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_setting);
	initView();
}
private void initView(){
	common_back=(ImageView)findViewById(R.id.common_back);
	common_back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			finish();
		}
	});
}
}
