/**
 * 
 */
package com.example.demo2;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.lib.ImageWrapper;
import com.lurencun.android.adapter.ViewBuilder;

/**
 * 根据资源xml生成一个可视化组件. 每个组件扩展包含ImageWrapper对象，保持图像的相应属性。
 * 
 * @author liuran
 * 
 */
public class SimpleViewBuilder extends ViewBuilder<ImageWrapper> {

	@Override
	public View createView(LayoutInflater inflater, int position,
			ImageWrapper data) {
		View view = inflater.inflate(R.layout.item_sample, null);
		updateView(view, position, data);
		return view;
	}

	@Override
	public void updateView(View view, int position, ImageWrapper data) {
		ImageView image = (ImageView) view.findViewById(R.id.thumbnail);
		TextView text = (TextView) view.findViewById(R.id.text);
		text.setText("W=" + data.width + ", H=" + data.height + ", ID="
				+ String.valueOf(data.id));

		LinearLayout.LayoutParams params = (LayoutParams) image
				.getLayoutParams();
		params.width = data.width;
		params.height = data.height;
		image.setLayoutParams(params);
		image.setAdjustViewBounds(false);
		image.setBackgroundColor((int) (0xFF555555 + data.id * 255 * 24));
		image.setImageResource(data.res);
		image.invalidate();
	}

}
