package me.power.speed.box;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import me.power.speed.AbstractBaseTest;
import me.power.speed.box.datapackage.AddDataPackage2List;
import me.power.speed.common.json.JsonUtil;
import me.power.speed.common.msqueue.kestrel.KestrelMessageQueue;
import me.power.speed.common.util.ObjectUtil;
import me.power.speed.entity.test.DataPackage;

public class TestGetDataPackageFromKestrel extends AbstractBaseTest {
	private KestrelMessageQueue kestrelQueue = new KestrelMessageQueue();
	
	@Before
	public void before() {
		if(kestrelQueue == null) {
			kestrelQueue = new KestrelMessageQueue();
		}
	}
	
	@Test
	public void testStringLength() {
		String str = "��������������������������������01140719144041";
		this.print(str.getBytes().length);
	}
	
	@Test
	public void getOneDataPackage() {
		String message = kestrelQueue.getMessage();
		DataPackage dp = JsonUtil.getDataPackage(message);
		this.print(dp);
	}
	
	@Test
	public void cycleGetDataPackage() {
		while(true) {
			String message = kestrelQueue.getMessage();
			if(StringUtils.isBlank(message)) {
				this.sleep(1000);
			}
			try {
				DataPackage dp = JsonUtil.getDataPackage(message);
				dp.getUserProfile();
				ObjectUtil.convertBean(dp.getAppProfile());
				//this.print(dp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		AddDataPackage2List obj = new AddDataPackage2List();
		//obj.cycleGetDataPackage();
		obj.addDataPackage2List();
	}
}
