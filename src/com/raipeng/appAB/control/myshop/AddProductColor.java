package com.raipeng.appAB.control.myshop;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.raipeng.appAB.R;
import com.raipeng.appAB.control.BaseActivity;
public class AddProductColor extends BaseActivity{
	private TextView title_tv,back_tv;
	private LinearLayout common_back;	
	private ImageView add_tv;
	private ListView listView;
	private List<String> colorList=new ArrayList<String>();
	private ColorAdapter mAdapter;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_addproduct_color);
initView();
mAdapter=new ColorAdapter();
String black="黑色";
String white="白色";
colorList.add(white);
colorList.add(black);
listView.setAdapter(mAdapter);
}
private void initView(){
	listView=(ListView)findViewById(R.id.listview);
	add_tv.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			
		}
	});
	common_back.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			finish();
		}
	});
	back_tv.setText("新增产品");
	title_tv.setText("颜色");
}
private class ColorAdapter extends BaseAdapter{

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return colorList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return colorList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View conventView, ViewGroup arg2) {
		ViewHolder viewHolder;
		if(conventView==null){
			viewHolder=new ViewHolder();
			LayoutInflater infalter=LayoutInflater.from(AddProductColor.this);
			conventView=infalter.inflate(R.layout.color_item,null);
			viewHolder.colorText=(TextView)conventView.findViewById(R.id.color_tv);
			conventView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder)conventView.getTag();
		}
		viewHolder.colorText.setText(colorList.get(position));
		return conventView;
	}
	
class ViewHolder{
	TextView colorText;
}
}
}
