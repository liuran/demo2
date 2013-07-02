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
import android.widget.ListAdapter;
import android.widget.TextView;

import com.huewu.pla.lib.MultiColumnListView;
import com.huewu.pla.lib.internal.PLA_AdapterView;
import com.huewu.pla.lib.internal.PLA_AbsListView.LayoutParams;
import com.example.demo2.R;

public class PullToRefreshSampleActivity extends Activity {

	private class MySimpleAdapter extends ArrayAdapter<String> {

		public MySimpleAdapter(Context context, int layoutRes) {
			super(context, layoutRes, R.id.text);
		}
	}

	private MultiColumnListView mAdapterView = null;
	private MySimpleAdapter mAdapter = null;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_sample);
		
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

	public static final int PULL_TO_HOME_ID = 1010;
	public static final int PULL_TO_REFRESH_ID = 1011;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, PULL_TO_HOME_ID, 0,"载入图像");
		menu.add(Menu.NONE, PULL_TO_REFRESH_ID, 0, "载入文案");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case PULL_TO_HOME_ID:
		{
			Intent intent =new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		break;

		case PULL_TO_REFRESH_ID:  // 判断是否打开下拉刷新的页面。
		{
			Intent intent = new Intent(this, PullToRefreshSampleActivity.class);
			startActivity(intent);
		}
		break;
		}
			return true;
	}

	@Override
	protected void onResume() {
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
