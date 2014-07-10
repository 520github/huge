/**
 * 
 */
package me.power.speed.common.test.mockito;

import static org.mockito.Mockito.*; 

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.InOrder;
/**
 * @author xuehui.miao
 *
 */
public class TestMockitoSample {
	
	@Test
	public void verifyMockData() {
		@SuppressWarnings("unchecked")
		
		//进行相关操作
		List<Object> mockList = mock(List.class);
		mockList.add("one");
		mockList.clear();
		//verify(mockList).add("one");
		
		//校验某个方法是否被调用过，如果没有被调用过，就会报错了
		verify(mockList).add("one");  
		verify(mockList).clear();  
		//verify(mockList).add("2");
	}
	
	@Test
	public void testGetAndReturn() {
		@SuppressWarnings("unused")
		LinkedList<Object> mockedList = mock(LinkedList.class);  
		
		when(mockedList.get(0)).thenReturn("first");  
		when(mockedList.get(1)).thenThrow(new RuntimeException()); 
		
		System.out.println(mockedList.get(0));  
		System.out.println(mockedList.get(0)); 
		System.out.println(mockedList.get(3)); 
		//System.out.println(mockedList.get(1));
		
		verify(mockedList, atLeast(2)).get(0); 
	}
	
	@Test
	public void testSetRepeatValue() {
		@SuppressWarnings("unused")
		LinkedList<Object> mockedList = mock(LinkedList.class);  
		
		when(mockedList.get(0)).thenReturn("first");  
		when(mockedList.get(0)).thenReturn("oops");  
		
		System.out.println(mockedList.get(0));  
		System.out.println(mockedList.get(0)); 
	}
	
	@Test
	public void testMatch() {
		@SuppressWarnings("unused")
		LinkedList<Object> mockedList = mock(LinkedList.class);  
		
		when(mockedList.get(anyInt())).thenReturn("element");
		
		System.out.println(mockedList.get(999));  
		
		verify(mockedList).get(anyInt());
	}
	
	@Test
	public void testVerifyFunction() {
		@SuppressWarnings("unused")
		LinkedList<Object> mockedList = mock(LinkedList.class); 
		
		mockedList.add("once");  
		  
		mockedList.add("twice");  
		mockedList.add("twice");  
		  
		mockedList.add("three times");  
		mockedList.add("three times");  
		mockedList.add("three times");
		
		verify(mockedList).add("once");  
		verify(mockedList, times(1)).add("once");
		
		verify(mockedList, times(2)).add("twice");  
		verify(mockedList, times(3)).add("three times");
		
		verify(mockedList, never()).add("never happened"); 
		
		verify(mockedList, atLeastOnce()).add("three times");  
		//verify(mockedList, atLeast(2)).add("five times");  
		verify(mockedList, atMost(6)).add("three times");  
	}
	
	@Test
	public void testVerifyInOrderFunction() {
		List<Object> singleMock = mock(List.class);  
		
		singleMock.add("was added first");  
		singleMock.add("was added second");
		
		InOrder inOrder = inOrder(singleMock);  
		
		inOrder.verify(singleMock).add("was added first");  
		inOrder.verify(singleMock).add("was added second"); 
		
		List<Object> firstMock = mock(List.class);  
		List<Object> secondMock = mock(List.class);
		
		firstMock.add("was called first");  
		secondMock.add("was called second");
		
		InOrder inOrder2 = inOrder(firstMock, secondMock); 
		
		inOrder2.verify(firstMock).add("was called first");  
		inOrder2.verify(secondMock).add("was called second");  
	}
	
	//http://qiuguo0205.iteye.com/blog/1456528
}
