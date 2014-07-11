/**
 * 
 */
package me.power.speed.frame.storm.sample.sayword;

import org.junit.Before;
import org.junit.Test;

/**
 * @author xuehui.miao
 *
 */
public class TestSayWordTopology {
	private SayWordTopology sayWord;
	
	@Before
	public void before() {
		sayWord =  new SayWordTopology();
	}
	
	@Test
	public void testSayWord() {
		sayWord.sayWord(null);
	}
}
