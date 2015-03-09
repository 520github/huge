package me.power.speed.common.stream.file;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.AbstractBaseTest;

public class TestFileUtil extends AbstractBaseTest {
	private String filePath;
	
	@Before
	public void before() {
		this.filePath = "F:\\data\\df\\event\\2014-07-26-datafilter01_event.log";
	}
	
	@Test
	public void testReadLineDataFromFile() {
		try {
			List<String> resultList = FileUtil.readLineDataFromFile(filePath);
			this.print(resultList);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testCustomeReadLineDataFromFile() {
		try {
			FileUtil.readLineDataFromFile(filePath, new FileHandleAndFilter() {
				public void handleOneLineData(String data) {
					System.out.println(data);
				}
				
				public boolean filterOneLineData(String data) {
					return false;
				}
			});
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testWrite2FileFromInputStream() {
		try {
			String writeFilePath = "F:\\data\\df\\event\\test.log";
			String readFilePath = filePath;
			FileUtil.write2FileFromInputStream(writeFilePath, FileUtil.readInputStreamFromFile(readFilePath));
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
