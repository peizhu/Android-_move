package com.raipeng.appAB.control.myshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.raipeng.appAB.R;
import com.raipeng.appAB.control.BaseActivity;

public class AddNewProduct extends BaseActivity{
	private TextView title_tv,back_tv;
	private LinearLayout common_back,add_color;
	private ImageView add_tv;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_addproduct);
	initView();
}
private void initView(){
	add_color=(LinearLayout)findViewById(R.id.add_color);
	back_tv.setText("我的店铺");
	title_tv.setText("新增产品");
	add_tv.setVisibility(View.GONE);
	common_back.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			finish();
		}
	});
	add_color.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
startActivity(new Intent(AddNewProduct.this,AddProductColor.class));			
		}
	});
}
}
