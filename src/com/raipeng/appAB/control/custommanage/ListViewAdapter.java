package com.raipeng.appAB.control.custommanage;

import java.util.List;
import com.raipeng.appAB.R;
import com.raipeng.appAB.model.PersonEntity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	private Context context;
	private List<PersonEntity> list;
	private ViewHolder viewHolder;
private boolean isEng;//false时显示搜索结果，true联系人列表
	public ListViewAdapter(Context context, List<PersonEntity> list,boolean isEng) {
		this.context = context;
		this.list = list;
		this.isEng=isEng;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		if (list.get(position).getName().length() == 1)// 如果是字母索引
			return false;// 表示不能点击
		return super.isEnabled(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PersonEntity  person = list.get(position);
		String item=list.get(position).getName();
		viewHolder = new ViewHolder();
		if(isEng){
		if (item.length() == 1) {
			convertView = LayoutInflater.from(context).inflate(R.layout.letter_index,
					null);
			viewHolder.indexTv = (TextView) convertView
					.findViewById(R.id.indexTv);
		} else {
			convertView = LayoutInflater.from(context).inflate(R.layout.friend_item,
					null);
			viewHolder.itemTv = (TextView) convertView
					.findViewById(R.id.itemTv);
			viewHolder.person_iv=(ImageView)convertView.findViewById(R.id.person_icon_iv);
			viewHolder.person_tel=(TextView)convertView.findViewById(R.id.person_tel);
		}
		if (item.length() == 1) {
			viewHolder.indexTv.setText(list.get(position).getName());
		} else {
			viewHolder.itemTv.setText(person.getName());
//			viewHolder.person_tel.setText(person.getPhone_num());
		}}else{
			convertView = LayoutInflater.from(context).inflate(R.layout.friend_item,
					null);
			viewHolder.itemTv = (TextView) convertView
					.findViewById(R.id.itemTv);
			viewHolder.itemTv.setText(person.getName());
		}
		
		return convertView;
	}

	private class ViewHolder {
		private TextView indexTv;
		private TextView itemTv;
		private ImageView person_iv;
		private TextView person_tel;
	}

}
