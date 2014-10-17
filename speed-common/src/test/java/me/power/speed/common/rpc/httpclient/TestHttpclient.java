/**
 * 
 */
package me.power.speed.common.rpc.httpclient;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.AbstractBaseTest;
import me.power.speed.common.algorithm.compress.CompressException;
import me.power.speed.common.algorithm.compress.CompressUtil;

/**
 * @author xuehui.miao
 *
 */
public class TestHttpclient extends AbstractBaseTest {
	private String rootPath = "E:\\20-work\\40-datafilter\\10-data\\";
	private String filePath = null;
	
	@Before
	public void before() {
		filePath = rootPath + "g3\\G3_old.log";
		
		filePath = rootPath + "big\\big_2.log";
		filePath = rootPath + "big\\big_2_remove_network.log";
		filePath = rootPath + "big\\big_2_remove_network_g8.log";
		
		//filePath = rootPath + "big\\big_3_source.log";
		//filePath = rootPath + "big\\big_3_remove_network.log";
		//filePath = rootPath + "big\\big_3_remove_network_g8.log";
		
		//filePath = rootPath + "big\\big_4_source.log";
		//filePath = rootPath + "big\\big_4_remove_network.log";
	}
	
	@Test
	public void testPostGzipDataByGroup() {
		try {
			String preName = "big_40_";
			rootPath = rootPath + "big\\";
			
			filePath = rootPath + preName + "source.log";
			testPostGzipData();
			
			filePath = rootPath + preName + "remove_network.log";
			testPostGzipData();
			
			filePath = rootPath + preName + "remove_network_g8.log";
			testPostGzipData();
			
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testPostGzipData() {
		String content = this.readDataFromFile();
		try {
			byte data[] = CompressUtil.compressData2Zip(content);
			this.postData(content, data);
		} catch (CompressException e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testPostDeflateData() {
		String content = this.readDataFromFile();
		try {
			byte data[] = CompressUtil.compressData2Deflate(content);
			this.postData(content, data);
		} catch (CompressException e) {
			this.fail(e);
		}
	}
	
	private String readDataFromFile() {
		return this.readFileFirstLineData(filePath);
	}
	
	private void postData(String content, byte data[]) {
		this.print("source length:" + content.getBytes().length + ";compress length:" + data.length);
		String result = HttpClientUtil.postBytesData("http://localhost:80/huge/servlet/read/stream", "application/json", "utf-8", data);
		this.print(result);
	}
	
	
}
