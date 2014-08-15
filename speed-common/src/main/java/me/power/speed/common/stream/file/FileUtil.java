/**
 * 
 */
package me.power.speed.common.stream.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuehui.miao
 *
 */
public class FileUtil {
	
	public static List<String> readLineDataFromFile(String filePath) throws Exception {
		final List<String> resultList = new ArrayList<String>();
		readLineDataFromFile(filePath, new FileHandleAndFilter() {
			@Override
			public void handleOneLineData(String data) {
				resultList.add(data);
			}
			
			@Override
			public boolean filterOneLineData(String data) {
				return false;
			}
		});
		
		return resultList;
	}
	
	public static void readLineDataFromFile(String filePath, FileHandleAndFilter handleAndFilter) throws Exception {
		BufferedReader br = null;
		String data = null; 
		try {
			br = getBufferedReader(filePath);
			while((data = br.readLine())!=null) { 
				if(!handleAndFilter.filterOneLineData(data)) {
					handleAndFilter.handleOneLineData(data);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static BufferedReader getBufferedReader(String filePath) throws Exception {
		 BufferedReader br = null;
		 try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)))); 
		 } catch (Exception e) {
			throw e;
		 }
		 return br;
	}
}
