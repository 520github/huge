/**
 * 
 */
package me.power.speed.common.algorithm.compress;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;

import me.power.speed.AbstractBaseTest;
import me.power.speed.common.stream.file.FileUtil;

/**
 * @author xuehui.miao
 *
 */
public abstract class AbstractCompressTest extends AbstractBaseTest {
	protected Compress compress = null;
	protected String content = null;
	protected String filePath = null;
	protected static String rootPath = "E:\\20-work\\40-datafilter\\10-data\\";
	
	@Before
	public void before() {
		this.filePath = rootPath + "g8\\g8.log";
	}
	
	protected void compressString() {
		try {
			if(StringUtils.isNotBlank(filePath)) {
				this.content = this.readDataFromFile();
			}
			this.print("before compress length: " + this.content.getBytes().length);
			byte[] result = compress.compressString(this.content);
			this.print("after compress length: " + result.length);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	protected String readDataFromFile() {
		try {
			return FileUtil.readFirstLineDataFromFile(filePath);
		} catch (Exception e) {
			this.fail(e);
		}
		return null;
	}
}
