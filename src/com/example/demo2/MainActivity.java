package com.example.demo2;

import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.huewu.pla.lib.MultiColumnListView;
import com.huewu.pla.lib.MultiColumnListView.OnLoadMoreListener;
import com.huewu.pla.lib.internal.PLA_AbsListView.LayoutParams;
import com.example.demo2.PullToRefreshSampleActivity;

import com.example.demo2.ImgResource;
import com.example.demo2.SimpleViewBuilder;
import com.example.lib.ImageWrapper;
import com.lurencun.android.adapter.AbstractAdapter;
import com.lurencun.android.adapter.CommonAdapter;
import com.lurencun.android.system.ActivityUtil;
import com.example.demo2.R;

public class MainActivity extends Activity {
	
	protected AbstractAdapter<ImageWrapper> mAdapter = null;
	private MultiColumnListView mAdapterView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_sample);
		mAdapterView = (MultiColumnListView) findViewById(R.id.list);
		
		initAdapter();
		mAdapterView.setAdapter(mAdapter);
		mAdapterView.setOnLoadMoreListener(new OnLoadMoreListener() {
			@Override
			public void onLoadMore() {
				mAdapter.add(ImgResource.genData());
				ActivityUtil.show(MainActivity.this, "载入图像列表");
				
				new Handler().postDelayed(new Runnable(){
					@Override
					public void run() {
						mAdapterView.onLoadMoreComplete();
					}
				}, 5000);
			}
		});
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
			Intent intent =new Intent(MainActivity.this, MainActivity.class);
			startActivity(intent);
		}
		break;

		case PULL_TO_REFRESH_ID:  // 判断是否打开下拉刷新的页面。
		{
			Intent intent = new Intent(MainActivity.this, PullToRefreshSampleActivity.class);
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

	protected void initAdapter() {
		mAdapter = new CommonAdapter<ImageWrapper>(getLayoutInflater(), new SimpleViewBuilder());
		mAdapter.update(ImgResource.genData());
	}

}//end of class
