package com.raipeng.appAB.control.myshop;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.raipeng.appAB.R;
import com.raipeng.appAB.common.CommonUtils;
import com.raipeng.appAB.common.Constants;
import com.raipeng.appAB.common.HttpUtils;
import com.raipeng.appAB.control.BaseFragment;
import com.raipeng.appAB.model.ProductEntity;

public class HotFragment extends BaseFragment {
	private PullToRefreshGridView mPullGridView;
	private TextView nonedata_tv;
	private HotProductAdapter mAdapter;
	private List<ProductEntity> listData = new ArrayList<ProductEntity>();
	private TextView footView;
	private List<ProductEntity> moreListData = new ArrayList<ProductEntity>();
	private int limit = 9;
	private int start = 0;
	private int totalCount = 0;
	private boolean isloadMore = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.hotproduct, container, false);
		mPullGridView = (PullToRefreshGridView) view
				.findViewById(R.id.common_gridview);
		nonedata_tv = (TextView) view.findViewById(R.id.common_none_tv);
		footView = (TextView) view.findViewById(R.id.footView);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case Constants.SHOW_LOADING_DIALOG:
					CommonUtils.showLoadingDialog(mContext, true, "请稍后...");
					break;
				case Constants.LOAD_DATA_SUCCESS:
					initGrid();
					CommonUtils.showLoadingDialog(mContext, false, "");
					break;
				case Constants.LOAD_DATA_FAILURE:
					initGrid();
					CommonUtils.showToast(mContext, "加载失败");
					CommonUtils.showLoadingDialog(mContext, false, "");
				default:
					break;
				}
			}
		};
		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { loadData(listData); } }).start();
		 */

		for (int i = 0; i < 10; i++) {
			ProductEntity data = new ProductEntity(
					"http://d.hiphotos.baidu.com/zhidao/pic/item/7c1ed21b0ef41bd50ea7f2c152da81cb39db3d0a.jpg",
					"驴牌包包", "8888", 89, 8099);
			listData.add(data);
		}
		initGrid();
		return view;
	}

	/**
	 * 初始化adapter
	 */
	private void initGrid() {
		if (listData.size() == 0) {
			nonedata_tv.setVisibility(View.VISIBLE);
			mPullGridView.setVisibility(View.GONE);
		} else {
			mPullGridView.setVisibility(View.VISIBLE);
			nonedata_tv.setVisibility(View.GONE);
			if (null == mAdapter) {
				mAdapter = new HotProductAdapter(listData);
				mPullGridView.setAdapter(mAdapter);
			} else {
				mAdapter.refresh(listData);
			}
			mPullGridView
					.setOnRefreshListener(new OnRefreshListener<GridView>() {
						@Override
						public void onRefresh(
								PullToRefreshBase<GridView> refreshView) {
							new LoadMore().execute();
						}
					});
			footView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mPullGridView.setRefreshing(true);
				}
			});
		}
	}

	private class LoadMore extends AsyncTask<Boolean, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Boolean... arg0) {
			if (mPullGridView.getCurrentMode() == Mode.PULL_FROM_END) {
				limit = 9;
				start = 0;
				isloadMore = false;
				loadData(listData);
			} else {
				isloadMore = true;
				calculate();
				loadData(listData);
				listData.addAll(moreListData);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			mAdapter.refresh(listData);
			if (totalCount <= mAdapter.getCount()) {
				CommonUtils.showToast(mContext, "数据加载完毕");
				mPullGridView.onRefreshComplete();
				mPullGridView.setMode(Mode.PULL_FROM_START);
			} else {
				mPullGridView.setMode(Mode.BOTH);
			}

			if (listData.size() == 0) {
				mPullGridView.setVisibility(View.GONE);
			} else {
				mPullGridView.setVisibility(View.VISIBLE);
			}
			mPullGridView.onRefreshComplete();
		}

	}

	private void calculate() {
		int current = mAdapter.getCount();
		if (current + limit < totalCount) {
			start = current;
		} else {
			start = current;
			limit = totalCount - current;
		}
	}

	/**
	 * @param mListData
	 *            读取网络数据
	 */
	@SuppressWarnings("unchecked")
	private void loadData(List<ProductEntity> mListData) {
		if (!isloadMore) {
			mHandler.sendEmptyMessage(Constants.SHOW_LOADING_DIALOG);
		}
		mListData.clear();
		JSONObject params = new JSONObject();
		Gson gson = new Gson();
		try {
			params.put("", "");
			String result = HttpUtils.getHttpString(Constants.HOTPRODUCT_URL,
					params.toString());
			JSONObject object = new JSONObject(result);
			Boolean success = object.getBoolean("success");
			if (success) {
				String records = object.getString("record");
				Type listType = new TypeToken<List<ProductEntity>>() {
				}.getType();
				mListData = (List<ProductEntity>) gson.fromJson(records,
						listType);
				if (!isloadMore) {
					mHandler.sendEmptyMessage(Constants.LOAD_DATA_SUCCESS);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			mHandler.sendEmptyMessage(Constants.LOAD_DATA_FAILURE);
		}
	}

	/**
	 * @author Administrator 热卖商品Adapter
	 */
	private class HotProductAdapter extends BaseAdapter {
		private List<ProductEntity> dataList;

		private HotProductAdapter(List<ProductEntity> dataList) {
			this.dataList = dataList;

		}

		private void refresh(List<ProductEntity> dataList) {
			this.dataList = dataList;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View conventView, ViewGroup parent) {
			ViewHolder holder = null;
			if (conventView == null) {
				holder = new ViewHolder();
				LayoutInflater inflater = LayoutInflater.from(mContext);
				conventView = inflater.inflate(R.layout.hotproduct_item,
						parent, false);
				holder.product_picture_iv = (ImageView) conventView
						.findViewById(R.id.product_picture);
				holder.product_name_tv = (TextView) conventView
						.findViewById(R.id.product_name);
				holder.price_tv = (TextView) conventView
						.findViewById(R.id.price);
				holder.inventory_tv = (TextView) conventView
						.findViewById(R.id.inventory);
				holder.sales_tv = (TextView) conventView
						.findViewById(R.id.sales);
				conventView.setTag(holder);
			} else {
				holder = (ViewHolder) conventView.getTag();
			}
			holder.product_name_tv.setText(dataList.get(position).getName());
			holder.price_tv.setText("¥"+dataList.get(position).getPrice());
			holder.inventory_tv.setText("库存"+dataList.get(position)
					.getInventoryNum()+"件");
			holder.sales_tv.setText("销量"+dataList.get(position).getSalesNum()+"件");
			ImageLoader.getInstance().displayImage(
					dataList.get(position).getProduct_image_url(),
					holder.product_picture_iv);
			return conventView;
		}

		class ViewHolder {
			ImageView product_picture_iv;
			TextView product_name_tv;
			TextView price_tv;
			TextView inventory_tv;
			TextView sales_tv;
		}
	}
}
