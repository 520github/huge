/**
 * 
 */
package me.power.speed.common.outofmemory.direct;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * @author xuehui.miao
 *
 */
public class DirectMemoryOOM {
	private static final int _1MB = 1024 * 1024;
	
	public static void main(String[] args) {
		try {
			Field unsafeFiled = Unsafe.class.getDeclaredFields()[0];
			unsafeFiled.setAccessible(true);
			Unsafe unsafe = (Unsafe)unsafeFiled.get(null);
			while(true) {
				unsafe.allocateMemory(_1MB);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
