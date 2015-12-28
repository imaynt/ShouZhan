package com.zykj.shouzhan.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zykj.shouzhan.R;

public class ImageUtil {
	  /**
     * 加载圆形图片
     * @param container
     * @param url
     */
    public static SimpleImageLoadingListener listener;
    public static <T extends ImageView> void displayImage2Circle(T container, String url, final float roundPx, final Boolean isCircle) {
        if (listener == null) {
            listener = new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    if(isCircle==null?false:isCircle){
                        ((ImageView) view).setImageBitmap(getCircleCornerBitmap(loadedImage, roundPx));
                    }else {
                        ((ImageView) view).setImageBitmap(getRoundedCornerBitmap(loadedImage, roundPx));
                    }
                }
            };
        }
        ImageLoader.getInstance().displayImage(url, container, listener);
    }



    /**
     * 获得正圆图片
     */
    public static Bitmap getCircleCornerBitmap(Bitmap bitmap, float roundPx) {
        // 圆形图片宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 正方形的边长
        int r = 0;
        // 取最短边做边�?
        if (width > height) {
            r = height;
        } else {
            r = width;
        }
        // 构建�?个bitmap
        Bitmap backgroundBmp = Bitmap.createBitmap(r, r,
                Bitmap.Config.ARGB_8888);
        // new�?个Canvas，在backgroundBmp上画�?
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();
        // 设置边缘光滑，去掉锯�?
        paint.setAntiAlias(true);
        // 宽高相等，即正方�?
        RectF rect = new RectF(0, 0, r, r);
        // 通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        // 且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r / 2, r / 2, paint);
        // 设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去�?
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        // canvas将bitmap画在backgroundBmp�?
        canvas.drawBitmap(bitmap, null, rect, paint);
        // 返回已经绘画好的backgroundBmp
        return backgroundBmp;
    }

    /**
     * 获取圆角位图的方�?
     * @param bitmap �?要转化成圆角的位�?
     * @param roundPx 圆角的度数，数�?�越大，圆角越大
     * @return 处理后的圆角位图
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap,float roundPx){

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rectF, paint);
        return output;
    }


	public static DisplayImageOptions getLogoOptions(boolean round) {
		DisplayImageOptions.Builder m_options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.icon_image_default)
				.showImageForEmptyUri(R.drawable.icon_image_default)
				.showImageOnFail(R.drawable.icon_image_default).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true);
		if (round) {// 如果是圆�?
			m_options.displayer(new RoundedBitmapDisplayer(5));
		}
		return m_options.build();
	}

	/**
	 * 
	 * @param round
	 *            true是圆�?
	 * @return
	 */
	public static DisplayImageOptions getGoodsOptions(boolean round) {
		DisplayImageOptions.Builder m_options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.icon_image_default)
				.showImageForEmptyUri(R.drawable.icon_image_default)
				.showImageOnFail(R.drawable.icon_image_default).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true);
		if (round) {// 如果是圆�?
			m_options.displayer(new RoundedBitmapDisplayer(5));
		}
		return m_options.build();
	}
	
	/**
	 * 压缩图片
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image,int options) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, options, baos);//质量压缩方法，这�?100表示不压缩，把压缩后的数据存放到baos�?
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream�?
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

	/**
	 * 根据�?个网络连�?(String)获取bitmap图像
	 * 
	 * @param imageUri
	 * @return
	 * @throws MalformedURLException
	 */
	public static Bitmap getbitmap(String imageUri) {
		// 显示网络上的图片
		Bitmap bitmap = null;
		try {
			URL myFileUrl = new URL(imageUri);
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}
}
