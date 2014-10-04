/**
 * 
 */
package me.power.speed.common.rpc.httpclient;

import org.junit.Test;

import me.power.speed.AbstractBaseTest;
import me.power.speed.common.algorithm.compress.CompressException;
import me.power.speed.common.algorithm.compress.CompressUtil;

/**
 * @author xuehui.miao
 *
 */
public class TestHttpclient extends AbstractBaseTest {
	
	@Test
	public void testPostGzipData() {
		String filePath = "E:\\20-work\\40-datafilter\\10-data\\g3\\G3_old.log";
		String content = this.readFileFirstLineData(filePath);
		try {
			byte data[] = CompressUtil.compressData2Zip(content);
			String result = HttpClientUtil.postBytesData("http://localhost:8080/huge/read/stream", "application/json", "utf-8", data);
			this.print(result);
		} catch (CompressException e) {
			this.fail(e);
		}
	}
}
