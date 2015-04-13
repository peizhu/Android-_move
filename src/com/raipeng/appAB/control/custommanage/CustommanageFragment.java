package com.raipeng.appAB.control.custommanage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.raipeng.appAB.R;
import com.raipeng.appAB.common.StringHelper;
import com.raipeng.appAB.common.StringUtils;
import com.raipeng.appAB.control.BaseFragment;
import com.raipeng.appAB.model.PersonEntity;

/**
 * @author Administrator
 *客户管理
 */
public class CustommanageFragment extends BaseFragment{
	private HashMap<String, Integer> selector;// 存放含有索引字母的位置
	private LinearLayout layoutIndex;
	private ListView listView;
	private TextView tv_show;
	private ListViewAdapter adapter;
	private EditText search_edit;
	private View blackview;
	private boolean addTextChangedListener = true;
	private String[] indexStr = { "#", "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };
	private List<PersonEntity> persons = null;
	private List<PersonEntity> newPersons = new ArrayList<PersonEntity>();
	private int height;// 字体高度
	private boolean flag = false;
	private String searchText;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View view =inflater.inflate(R.layout.customer_book, container, false);
	initView(view);
	setData();
	String[] allNames = sortIndex(persons);
	sortList(allNames);

	selector = new HashMap<String, Integer>();
	for (int j = 0; j < indexStr.length; j++) {// 循环字母表，找出newPersons中对应字母的位置
		for (int i = 0; i < newPersons.size(); i++) {
			if (newPersons.get(i).getName().equals(indexStr[j])) {
				selector.put(indexStr[j], i);
			}
		}

	}
	adapter = new ListViewAdapter(mContext, newPersons, true);
	listView.setAdapter(adapter);
	return view;
}
	private List<PersonEntity> search(String string) {
		List<PersonEntity> searchList = new ArrayList<PersonEntity>();
		for (int i = 0; i < newPersons.size(); i++) {
			Log.i("TAG", "=========" + newPersons.get(i).getName());
			PersonEntity per = newPersons.get(i);
			if (!StringUtils.isEmpty(per.getName())) {
				if (per.getName().contains(string)||StringHelper.getPingYin(per.getName()).contains(string)) {//汉字相同或拼音首字母相同
					searchList.add(per);
				}
			}
			if (!StringUtils.isEmpty(per.getPhone_num())) {
				if (per.getPhone_num().contains(string)) {
					searchList.add(per);
				}
			}
		}
		return searchList;
	}

	private void initView(View view) {
		layoutIndex = (LinearLayout)view.findViewById(R.id.layout);
		layoutIndex.setBackgroundColor(Color.parseColor("#00ffffff"));
		listView = (ListView) view.findViewById(R.id.listView);
		tv_show = (TextView) view.findViewById(R.id.tv);
		search_edit = (EditText) view.findViewById(R.id.search_edit);
		blackview = (View) view.findViewById(R.id.blackview);
		tv_show.setVisibility(View.GONE);
		search_edit.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				searchText = search_edit.getText().toString().trim();
				if (addTextChangedListener) {
					if (StringUtils.isEmpty(searchText)) {
						blackview.setVisibility(View.VISIBLE);
						layoutIndex.setVisibility(View.VISIBLE);
					} else {
						blackview.setVisibility(View.GONE);
						layoutIndex.setVisibility(View.GONE);
						List<PersonEntity> list = search(searchText);
						adapter = new ListViewAdapter(mContext, list,
								false);
						listView.setAdapter(adapter);
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

			}
		});
		search_edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				addTextChangedListener=true;
				blackview.setVisibility(View.VISIBLE);
			}
		});
	}

	/**
	 * 重新排序获得一个新的List集合
	 * 
	 * @param allNames
	 */
	private void sortList(String[] allNames) {
		for (int i = 0; i < allNames.length; i++) {
			if (allNames[i].length() != 1) {
				for (int j = 0; j < persons.size(); j++) {
					if (allNames[i].equals(persons.get(j).getPinYinName())) {
						PersonEntity p = new PersonEntity(persons.get(j).getName(), persons
								.get(j).getPinYinName());
						newPersons.add(p);
					}
				}
			} else {
				newPersons.add(new PersonEntity(allNames[i]));
			}
		}
	}

/*	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// 在oncreate里面执行下面的代码没反应，因为oncreate里面得到的getHeight=0
		if (!flag) {// 这里为什么要设置个flag进行标记
			height = layoutIndex.getMeasuredHeight() / indexStr.length;
			getIndexView(true);
			flag = true;
		}
	}*/

	/**
	 * 获取排序后的新数据
	 * 
	 * @param persons
	 * @return
	 */
	public String[] sortIndex(List<PersonEntity> persons) {
		TreeSet<String> set = new TreeSet<String>();
		// 获取初始化数据源中的首字母，添加到set中
		for (PersonEntity person : persons) {
			set.add(StringHelper.getPinYinHeadChar(person.getName()).substring(
					0, 1));
		}
		// 新数组的长度为原数据加上set的大小
		String[] names = new String[persons.size() + set.size()];
		int i = 0;
		for (String string : set) {
			names[i] = string;
			i++;
		}
		String[] pinYinNames = new String[persons.size()];
		for (int j = 0; j < persons.size(); j++) {
			persons.get(j).setPinYinName(
					StringHelper
							.getPingYin(persons.get(j).getName().toString()));
			pinYinNames[j] = StringHelper.getPingYin(persons.get(j).getName()
					.toString());
		}
		// 将原数据拷贝到新数据中
		System.arraycopy(pinYinNames, 0, names, set.size(), pinYinNames.length);
		// 自动按照首字母排序
		Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
		return names;
	}

	/**
	 * 绘制索引列表
	 */
	public void getIndexView(boolean isEngVisible) {
		LinearLayout.LayoutParams params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, height);
		for (int i = 0; i < indexStr.length; i++) {
			final TextView tv = new TextView(mContext);
			tv.setLayoutParams(params);
			tv.setText(indexStr[i]);
			tv.setPadding(10, 0, 10, 0);
			layoutIndex.addView(tv);
			layoutIndex.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event)

				{
					float y = event.getY();
					int index = (int) (y / height);
					if (index > -1 && index < indexStr.length) {// 防止越界
						String key = indexStr[index];
						if (selector.containsKey(key)) {
							int pos = selector.get(key);
							if (listView.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。
								listView.setSelectionFromTop(
										pos + listView.getHeaderViewsCount(), 0);
							} else {
								listView.setSelectionFromTop(pos, 0);// 滑动到第一项
							}
							tv_show.setVisibility(View.VISIBLE);
							tv_show.setText(indexStr[index]);
						}
					}
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						layoutIndex.setBackgroundColor(Color
								.parseColor("#606060"));
						break;

					case MotionEvent.ACTION_MOVE:

						break;
					case MotionEvent.ACTION_UP:
						layoutIndex.setBackgroundColor(Color
								.parseColor("#00ffffff"));
						tv_show.setVisibility(View.GONE);
						break;
					}
					return true;
				}
			});
		}
	}

	/**
	 * 设置模拟数据
	 */
	private void setData() {
		persons = new ArrayList<PersonEntity>();
		PersonEntity p1 = new PersonEntity();
		p1.setName("耿琦");
		p1.setPhone_num("15506154113");
		persons.add(p1);
		PersonEntity p2 = new PersonEntity();
		p2.setName("王宝强");
		p2.setPhone_num("15506154123");
		persons.add(p2);
		PersonEntity p3 = new PersonEntity();
		p3.setName("柳岩");
		p3.setPhone_num("15506154123");
		persons.add(p3);
		PersonEntity p4 = new PersonEntity("文章");
		persons.add(p4);
		PersonEntity p5 = new PersonEntity("马伊琍");
		persons.add(p5);
		PersonEntity p6 = new PersonEntity("李晨");
		persons.add(p6);
		PersonEntity p7 = new PersonEntity("张馨予");
		persons.add(p7);
		PersonEntity p8 = new PersonEntity("韩红");
		persons.add(p8);
		PersonEntity p9 = new PersonEntity("韩寒");
		persons.add(p9);
		PersonEntity p10 = new PersonEntity("丹丹");
		persons.add(p10);
		PersonEntity p11 = new PersonEntity("丹凤眼");
		persons.add(p11);
		PersonEntity p12 = new PersonEntity("哈哈");
		persons.add(p12);
		PersonEntity p13 = new PersonEntity("萌萌");
		persons.add(p13);
		PersonEntity p14 = new PersonEntity("蒙混");
		persons.add(p14);
		PersonEntity p15 = new PersonEntity("烟花");
		persons.add(p15);
		PersonEntity p16 = new PersonEntity("眼黑");
		persons.add(p16);
		PersonEntity p17 = new PersonEntity("S三多");
		persons.add(p17);
		PersonEntity p18 = new PersonEntity("程咬金");
		persons.add(p18);
		PersonEntity p19 = new PersonEntity("程哈哈");
		persons.add(p19);
		PersonEntity p20 = new PersonEntity("爱死你");
		persons.add(p20);
		PersonEntity p21 = new PersonEntity("邱大虾");
		persons.add(p21);

	}
	/*@Override
	public void onBackPressed() {
		// super.onBackPressed();
		if (blackview.getVisibility() == View.VISIBLE
				|| !StringUtils.isEmpty(searchText)) {
			addTextChangedListener=false;
			blackview.setVisibility(View.GONE);
			search_edit.setText(null);
			adapter = new ListViewAdapter(mContext, newPersons, true);
			listView.setAdapter(adapter);
			layoutIndex.setVisibility(View.VISIBLE);
		} else {
			finish();
		}
	}*/
}
