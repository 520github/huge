package me.power.speed.test.storage.redis;

import java.util.List;

import org.junit.Test;

import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.impl.ConciseBitmapImpl;

public class TestRedisBitmapOperate extends AbstractRedisTest {
	private boolean isSortWithRandom = false;
	Bitmap fbitmap = new ConciseBitmapImpl();
	Bitmap sbitmap = new ConciseBitmapImpl();
	
	@Test
	public void testRedisBitmapOrOperate() {
		this.isSortWithRandom = true;
		this.setOffsetToBitmap();
		this.handleBitmapOrOperate(fbitmap, sbitmap);
	}
	
	@Test
	public void testMulitRedisBitmapOrOperate() {
		this.isSortWithRandom = true;
		int cycleNum = 5;
		this.handleMulitBitmapOrOperate(this.getBitmaps(cycleNum));
	}
	
	@Test
	public void testRedisBitmapAndOperate() {
		this.isSortWithRandom = true;
		this.setOffsetToBitmap();
		this.handleBitmapAndOperate(fbitmap, sbitmap);
		this.handleMulitBitmapAndOperate(fbitmap, sbitmap);
	}
	
	@Test
	public void testMulitRedisBitmapAndOperate() {
		this.isSortWithRandom = true;
		int cycleNum = 5;
		this.handleMulitBitmapAndOperate(this.getBitmaps(cycleNum));
	}
	
	private void setOffsetToBitmap() {
		int offsetArray1[] = this.getRandomIntArrays(100000, 10000000);
		List<Integer> sortList1 = this.getSortOffsetListByOffsetArray(offsetArray1);
		
		int offsetArray2[] = this.getRandomIntArrays(100000, 10000000);
		List<Integer> sortList2 = this.getSortOffsetListByOffsetArray(offsetArray2);
		
		if(isSortWithRandom) {
			this.handleSetOffsetsToBitmap(fbitmap, sortList1);
			this.handleSetOffsetsToBitmap(sbitmap, sortList2);
		}else {
			this.handleSetOffsetsToBitmap(fbitmap, offsetArray1);
			this.handleSetOffsetsToBitmap(sbitmap, offsetArray2);
		}	
	}
	
	private Bitmap[] getBitmaps(int cycleNum) {
		Bitmap[] bitmaps = new Bitmap[cycleNum];
		for(int i=0; i< cycleNum;i++) {
			int offsets[] = this.getRandomIntArrays(100000, 10000000);
			List<Integer> offsetList = this.getSortOffsetListByOffsetArray(offsets);
			Bitmap bitmap = new ConciseBitmapImpl();
			if(isSortWithRandom) {
				this.handleSetOffsetsToBitmap(bitmap, offsetList);
			}
			else {
				this.handleSetOffsetsToBitmap(bitmap, offsets);
			}
			bitmaps[i] = bitmap;
		}
		return bitmaps;
	}
}
