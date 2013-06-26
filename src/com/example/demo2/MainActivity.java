package com.example.demo2;

import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.huewu.pla.lib.MultiColumnListView;
import com.huewu.pla.lib.internal.PLA_AbsListView.LayoutParams;
import com.huewu.pla.sample.extra.PullToRefreshSampleActivity;
import com.example.demo2.R;

public class MainActivity extends Activity {


	private class MySimpleAdapter extends ArrayAdapter<String> {

		public MySimpleAdapter(Context context, int layoutRes) {
			super(context, layoutRes, android.R.id.text1);
		}
	}

	private MultiColumnListView mAdapterView = null;
	private MySimpleAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//mAdapterView = (PLA_AdapterView<Adapter>) findViewById(R.id.list);

		mAdapterView = (MultiColumnListView) findViewById(R.id.list);

		{
			for( int i = 0; i < 3; ++i ){
				//add header view.
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				tv.setText("Hello Header!! ........................................................................");
				mAdapterView.addHeaderView(tv);
			}
		}
		{
			for( int i = 0; i < 3; ++i ){
				//add footer view.
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				tv.setText("Hello Footer!! ........................................................................");
				mAdapterView.addFooterView(tv);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 1001, 0, "Load More Contents");
		menu.add(Menu.NONE, 1002, 0, "Launch Pull-To-Refresh Activity");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()){
		case 1001:  // 判断是否点击了瀑布流列表。
		{
			int startCount = mAdapter.getCount();
			for( int i = 0; i < 100; ++i){
				//generate 100 random items.

				StringBuilder builder = new StringBuilder();
				builder.append("Hello!![");
				builder.append(startCount + i);
				builder.append("] ");

				char[] chars = new char[mRand.nextInt(100)];
				Arrays.fill(chars, '1');
				builder.append(chars);
				mAdapter.add(builder.toString());
			}
		}
		break;
		case 1002:  // 判断是否打开下拉刷新的页面。
		{
			Intent intent = new Intent(this, PullToRefreshSampleActivity.class);
			startActivity(intent);
		}
		break;
		}
		return true;
	}

	@Override
	protected void onResume() {  // Resume 事件在应用恢复到活动状态时调用。
		super.onResume();
		initAdapter();
		mAdapterView.setAdapter(mAdapter);
	}

	private Random mRand = new Random();
	private void initAdapter() {
		mAdapter = new MySimpleAdapter(this, R.layout.item_sample);

		for( int i = 0; i < 30; ++i){
			//generate 30 random items.

			StringBuilder builder = new StringBuilder();
			builder.append("Hello!![");
			builder.append(i);
			builder.append("] ");

			char[] chars = new char[mRand.nextInt(500)];
			Arrays.fill(chars, '1');
			builder.append(chars);
			mAdapter.add(builder.toString());
		}

	}

}//end of class
