package me.power.speed.test.storage.bitmap;

import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.common.BitmapHandler;

public class BitmapUtil {
	
	public static byte[] bitmapToByteArray(Bitmap bitmap) {
		return BitmapHandler.bitmapToByteArray(bitmap);
	}
	
	public static Bitmap byteArrayToBitmap(byte[] data) {
		return BitmapHandler.byteArrayToBitmap(data);
	}
	
	public static void setOffsetToBitmap(Bitmap bitmap, int offset) {
		bitmap.set(offset);
	}
	
	public static int getOffsetCountFromBitmap(Bitmap bitmap) {
		return bitmap.cardinary();
	}
}
