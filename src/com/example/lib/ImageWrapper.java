/**
 * 
 */
package com.example.lib;

import android.graphics.BitmapFactory;

/**
 * ��huewu��ֲ�� ImageWrapper ��ͼ�����������԰���һ������ͼ����Ӧ���Ի������
 * @author liuran
 *
 */
public class ImageWrapper {
    /**
     * Ĭ�ϵĿ��
     */
    public int width = 230;

    /**
     * Ĭ�ϸ߶ȣ���Ϊͼ����ĸ߶ȡ�
     * 
     */
    // todo: ��������Ӧ�߶ȵĹ��ܡ�
    public int height;

    /**
     * ͼ�����Դ�� res ������Դ�⣬ url �����ַ��
     */
    public int res;
    public String url;
    public long id;
    
    
    // TODO ����Զ����ųߴ�ĺ�����
    
    public int get2Height(){
    	// ��ȡ��Դλͼ��ʵ�ʳߴ磻����Ŀ���ȣ����㷵��ͼ��������Ŀ��߶ȣ�
    	BitmapFactory.Options options = new BitmapFactory.Options();
    	options.inJustDecodeBounds = true;
    	BitmapFactory.decodeResource(null, res, options);
    	Log.e("Test","Bitmap Height = " + options.outHeight);
    	return options.outHeight;
    	
    }

}
