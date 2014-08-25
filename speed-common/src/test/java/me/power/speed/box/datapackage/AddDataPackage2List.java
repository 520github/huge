/**
 * 
 */
package me.power.speed.box.datapackage;

import java.util.ArrayList;
import java.util.List;

import me.power.speed.AbstractBaseTest;
import me.power.speed.common.json.JsonUtil;
import me.power.speed.common.stream.file.FileUtil;
import me.power.speed.entity.test.DataPackage;

import org.apache.commons.lang.StringUtils;

/**
 * @author xuehui.miao
 *
 */
public class AddDataPackage2List extends AbstractBaseTest {
	public void addDataPackage2List() {
		List<DataPackage> dataList = new ArrayList<DataPackage>(); 
		while(true) {
			String message = null;
			//message = kestrelQueue.getMessage();
			message = getDataPackageFromFile();
			if(StringUtils.isBlank(message)) {
				this.sleep(1000);
			}
			try {
				DataPackage dp = JsonUtil.getDataPackage(message);
				dataList.add(dp);
				this.sleep(500);
				System.out.println(dataList.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getDataPackageFromFile() {
		try {
			String filePath = "E:\\20-work\\40-datafilter\\10-data\\g9\\G9_new_ok.log";
			List<String> dataList = FileUtil.readLineDataFromFile(filePath);
			if(dataList != null && dataList.size() > 0) {
				return dataList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
