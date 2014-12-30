package me.power.speed.storage.redis.bitmap.util;

import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.common.BitmapHandler;

public class CompressBitmapUtil {
	
	/**
	 * 获取多个bitmap进行and操作之后的结果
	 * @param bitmaps
	 * @return
	 */
	public static int getMulitBitmapAndHandleResult(Bitmap... bitmaps) {
		if(bitmaps == null || bitmaps.length < 1) {
			return 0;
		}
		Bitmap result = null;
		for(Bitmap bitmap: bitmaps) {
			if(result == null) {
				result = bitmap;
			}
			else {
				result = result.and(bitmap);
			}
		}
		return result.cardinary();
	}
	
	/**
	 * 获取多个bitmap进行or操作之后的结果
	 * @param bitmaps
	 * @return
	 */
	public static int getMulitBitmapOrHandleResult(Bitmap... bitmaps) {
		if(bitmaps == null || bitmaps.length < 1) {
			return 0;
		}
		Bitmap result = null;
		for(Bitmap bitmap: bitmaps) {
			if(result == null) {
				result = bitmap;
			}
			else {
				result = result.or(bitmap);
			}
		}
		return result.cardinary();
	}
	
	/**
	 * 从字节数组反序列成bitmap对象
	 * @param datas
	 * @return
	 */
	public static Bitmap getBitmapFromBytes(byte[] datas) {
		if(datas == null) {
			return null;
		}
		return BitmapHandler.byteArrayToBitmap(datas);
	}
	
	/**
	 * 把bitmap对象序列化成字节数组
	 * @param bitmap
	 * @return
	 */
	public static byte[] getBytesFromBitmap(Bitmap bitmap) {
		byte datas[]= BitmapHandler.bitmapToByteArray(bitmap);
		return datas;
	}
}
