package me.power.speed.common.outofmemory.list;

import java.util.ArrayList;
import java.util.List;

public class SubListOutOfMemory {
	protected static List<Object> dataList = new ArrayList<Object>();
	
	//
	public static void subListMilt() {
		try {
			//List<Object> list = new ArrayList<Object>();
			for (int i = 0; i < 50000; i++) {
				dataList.add(BigDto.getBigDto());
			}
			
			while(true) {
				for (int j = 0; j < 100000; j++) { 
					Thread.sleep(100);
					if(j%100 == 0) {
						dataList.add(j);
					}
					else {
						dataList.add(BigDto.getBigDto());
					}    
	            } 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//mainֱ�ӵ�������������ڴ�ܿ�ͱ���
	public static void subListInMethod() {
		List<List<Integer>> cache = new ArrayList<List<Integer>>(); 
		try {
			while(true) {
				List<Integer> list = new ArrayList<Integer>(); 
	            for (int j = 0; j < 100000; j++) { 
	                list.add(j); 
	            } 
	            List<Integer> sublist = list.subList(0, 1); 
	            cache.add(sublist); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("cache size = " + cache.size()); 
		}
	}
	
	//����new List����������
	public static void subListNewInMethod() {
		List<List<Integer>> cache = new ArrayList<List<Integer>>(); 
		try {
			while(true) {
				List<Integer> list = new ArrayList<Integer>(); 
	            for (int j = 0; j < 100000; j++) { 
	                list.add(j); 
	            } 
	            List<Integer> sublist = new ArrayList<Integer>(list.subList(0, 1));
	            cache.add(sublist); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("cache size = " + cache.size()); 
		}
	}
	
	//���̴߳���û������
	public static void subList() {
		int i =0 ;
		while(true) {
			String data = "this is test message " + i;
			if(dataList.size() > 20) {
				dataList.remove(1);
				dataList.add(data);
			}
			else {
				dataList.add(data);
			}
			if(i%10000 ==0) {
				System.out.println("clear: " + i);
				dataList = dataList.subList(0, 1);
			}
			i++;
		}
	}
	
	public static void main(String[] args) {
		try {
			Thread.sleep(30000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//subListInMethod();
		//subListNewInMethod();
		//subList();
		subListMilt();
	}
}
