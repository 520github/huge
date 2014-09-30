/**
 * 
 */
package me.power.speed.common.algorithm.compress;

import org.junit.Before;
import org.junit.Test;

/**
 * @author xuehui.miao
 *
 */
public class TestDeflateCompress extends AbstractCompressTest {
	@Before
	public void before() {
		super.before();
		this.compress = new DeflateCompress();
	}
	
	@Test
	public void testDeflateCompress() {
		this.compressString();
	}
}
