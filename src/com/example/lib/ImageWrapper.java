/**
 * 
 */
package com.example.lib;

import android.graphics.BitmapFactory;

/**
 * 从huewu移植， ImageWrapper 是图像容器，可以包含一个缩略图的相应属性或操作。
 * @author liuran
 *
 */
public class ImageWrapper {
    /**
     * 默认的宽度
     */
    public int width = 230;

    /**
     * 默认高度，可为图像本身的高度。
     * 
     */
    // todo: 增加自适应高度的功能。
    public int height;

    /**
     * 图像的来源： res 本地资源库， url 网络地址。
     */
    public int res;
    public String url;
    public long id;
    
    
    // TODO 完成自动缩放尺寸的函数；
    
    public int get2Height(){
    	// 获取资源位图的实际尺寸；根据目标宽度，计算返回图像调整后的目标高度；
    	BitmapFactory.Options options = new BitmapFactory.Options();
    	options.inJustDecodeBounds = true;
    	BitmapFactory.decodeResource(null, res, options);
    	Log.e("Test","Bitmap Height = " + options.outHeight);
    	return options.outHeight;
    	
    }

}
