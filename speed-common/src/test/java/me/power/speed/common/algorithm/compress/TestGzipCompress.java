package me.power.speed.common.algorithm.compress;

import org.junit.Before;
import org.junit.Test;

public class TestGzipCompress extends AbstractCompressTest {
	
	@Before
	public void before() {
		super.before();
		this.compress = new GzipCompress();
	}
	
	@Test
	public void testGzipCompress() {
		this.compressString();
	}
	
}
