package com.raipeng.appAB.control.myshop;

import java.util.List;

import com.raipeng.appAB.R;
import com.raipeng.appAB.model.ProductClassfyEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassfyProductAdapter extends BaseExpandableListAdapter {
	private Context mContext;
	private List<ProductClassfyEntity> mListData;

	public ClassfyProductAdapter(Context mContext, List<ProductClassfyEntity> mListData) {
		super();
		this.mContext = mContext;
		this.mListData = mListData;
	}

	public void refresh(List<ProductClassfyEntity> mListData) {
		this.mListData = mListData;
		notifyDataSetChanged();
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		if (null != mListData && mListData.size() > 0) {

			if (mListData.get(arg0).getProductList().size() > 0) {
				return mListData.get(arg0).getProductList().get(arg1);
			} else {
				return null;
			}

		} else {
			return null;
		}
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		return arg1;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View childView, ViewGroup arg4) {
		return null;
	}

	@Override
	public int getChildrenCount(int arg0) {
		if (null != mListData.get(arg0).getProductList() && mListData.get(arg0).getProductList().size() != 0) {
			return mListData.get(arg0).getProductList().size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getGroup(int arg0) {
		if (null != mListData && mListData.size() > arg0) {
			return mListData.get(arg0);
		} else {
			return null;
		}
	}

	@Override
	public int getGroupCount() {
		return mListData.size();
	}

	@Override
	public long getGroupId(int arg0) {
		return arg0;
	}

	@Override
	public View getGroupView(int position, boolean isExpanded, View groupView, ViewGroup group) {
		GroupViewHolder groupViewHolder;
		if (groupView == null) {
			groupViewHolder = new GroupViewHolder();
			groupView = LayoutInflater.from(mContext).inflate(R.layout.classfyproduct_group_item, group, false);
			groupViewHolder.classfyName = (TextView) groupView.findViewById(R.id.classfyname);
			groupViewHolder.classfyNum = (TextView) groupView.findViewById(R.id.classfy_num);
			groupViewHolder.expandmark = (ImageView) groupView.findViewById(R.id.expandmark);
			groupView.setTag(groupViewHolder);
		} else {
			groupViewHolder = (GroupViewHolder) groupView.getTag();
		}
		if (isExpanded) {
			groupViewHolder.expandmark.setImageResource(R.drawable.expand);
		} else {
			groupViewHolder.expandmark.setImageResource(R.drawable.common_right_arrow);
		}
		groupViewHolder.classfyName.setText(mListData.get(position).getClassfyname());
		groupViewHolder.classfyNum.setText("(" + mListData.get(position).getNum() + ")" + "件");
		return groupView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		Boolean child = false;
		if (null != getChild(arg0, arg1)) {
			child = true;
		}
		return child;
	}

	class GroupViewHolder {
		ImageView expandmark;
		TextView classfyName;
		TextView classfyNum;
	}
}
