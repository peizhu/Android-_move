package com.raipeng.appAB.control;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.raipeng.appAB.R;
import com.raipeng.appAB.control.myshop.ShopFragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

public class MainActivity extends  SlidingFragmentActivity {
	private BaseApplication mApplication = null;
	private Fragment mContent = null;
	private CanvasTransformer mTransformer;
	private SlidingMenu slidingMenu;
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.common_content_frame);
	mApplication = (BaseApplication) getApplication();
	
	mContent = new ShopFragment();
	switchContent(mContent);
	
	initScaleAnimation();
	setBehindContentView(R.layout.common_menu_frame);
	getSupportFragmentManager().beginTransaction()
	.add(R.id.menu_frame, new NavigationFragment())
	.addToBackStack("NavigationFragment").commit();
	
	slidingMenu = getSlidingMenu();
	slidingMenu.setBehindOffsetRes(R.dimen.width_140_160);//SlidingMenu划出时主页面显示的剩余宽度
	slidingMenu.setFadeDegree(0.35f);//SlidingMenu滑动时的渐变程度
	slidingMenu.setBehindScrollScale(0.0f);
	slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
//	 slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
	//设置滑动的屏幕范围，从左边滑动
	slidingMenu.setBehindCanvasTransformer(mTransformer);
	
	 
}
	public void initScaleAnimation() {
		mTransformer = new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				canvas.scale(percentOpen, 1, 0, 0);
			}

		};
	}
	
public void switchContent(Fragment fragment) {
	mContent = fragment;
	getSupportFragmentManager().beginTransaction()
			.replace(R.id.content_frame, mContent).addToBackStack(null)
			.commit();
	new Handler(getMainLooper()).postDelayed(new Runnable() {
		public void run() {
			getSlidingMenu().showContent();
		}
	}, 50);
}
@Override
public void onPostCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onPostCreate(savedInstanceState);
	new Handler().post(new Runnable() {
		public void run() {
			 //初始状态显示左侧菜单
			slidingMenu.showMenu(true);
		}
		});
}
}
