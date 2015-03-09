/**
 * 
 */
package me.power.speed.common.stream.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * @author xuehui.miao
 *
 */
public class FileUtil {
	public static String FILE_ENCODE = "utf-8";
	
	/**
	 * 读取第一行数据
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String readFirstLineDataFromFile(String filePath) throws Exception {
		List<String> dataList = readLineDataFromFile(filePath);
		if(dataList == null || dataList.size() < 1) {
			return null;
		}
		return dataList.get(0);
	}
	
	public static List<String> readLineDataFromFile(String filePath) throws Exception {
		return readLineDataFromFile(filePath, 0);
	}
	
	public static List<String> readLineDataFromFile(String filePath, int cacheMSize) throws Exception {
		final List<String> resultList = new ArrayList<String>();
		readLineDataFromFile(filePath, 0, new FileHandleAndFilter() {
			public void handleOneLineData(String data) {
				resultList.add(data);
			}
			
			public boolean filterOneLineData(String data) {
				return false;
			}
		});
		
		return resultList;
	}
	
	public static void readLineDataFromFile(String filePath, FileHandleAndFilter handleAndFilter) throws Exception {
		readLineDataFromFile(filePath, 0, handleAndFilter);
	}
	
	public static void readLineDataFromFile(String filePath, int cacheMSize, FileHandleAndFilter handleAndFilter) throws Exception {
		BufferedReader br = null;
		String data = null; 
		try {
			br = getBufferedReader(filePath, cacheMSize);
			while((data = br.readLine())!=null) { 
				if(!handleAndFilter.filterOneLineData(data)) {
					handleAndFilter.handleOneLineData(data);
				}
			}
		} catch (Exception e) {
			throw e;
		}finally {
			if(br != null) {
				try {
					br.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	public static void 	appendWrite2File(String filePath, String message) throws Exception {
		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath, true);
			writer.write(message);
			writer.write("\n");
			writer.flush();
		} catch (Exception e) {
			throw e;
		}finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	public static void write2FileFromInputStream(String filePath, InputStream inputStream) throws FileNotFoundException, IOException {
		OutputStream output = new FileOutputStream(filePath);
		IOUtils.copy(inputStream, output);
		output.close();
	}
	
	public static InputStream readInputStreamFromFile(String filePath) throws IOException {
		return FileUtils.openInputStream(new File(filePath));
	}
	
	public static BufferedReader getBufferedReader(String filePath, int mSize) throws Exception {
		BufferedReader br = null;
		try {
			if(mSize > 0) {
				System.out.println("cache:" + mSize);
				br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), FILE_ENCODE), mSize * 1024 * 1024);
			}
			else {
				br  = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)),FILE_ENCODE));
			}
		} catch (Exception e) {
			throw e;
		}
		return br;
	}
	
	public static BufferedReader getBufferedReader(String filePath) throws Exception {
		 return getBufferedReader(filePath, 0);
	}
}
