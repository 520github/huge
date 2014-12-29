package me.power.speed.common.util;

import org.junit.Test;

import com.mongodb.util.Hash;

import me.power.speed.AbstractBaseTest;

public class TestMemoryUtil extends AbstractBaseTest {
	
	@Test
	public void testMemory() {
		this.print(MemoryUtil.getMaxMemory());
		this.print(MemoryUtil.getTotalMemory());
		this.print(MemoryUtil.getFreeMemory());
		this.print(MemoryUtil.getUsedMemory());
		
		this.print(MemoryUtil.sizeof(new Hash()));
	}
}
