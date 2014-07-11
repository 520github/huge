/**
 * 
 */
package me.power.speed.frame.storm.sample.counterword;

import org.junit.Before;
import org.junit.Test;

/**
 * @author xuehui.miao
 *
 */
public class TestCounterWordTopology {
	private CounterWordTopology counterWord;
	
	@Before
	public void before() {
		counterWord = new CounterWordTopology();
	}
	
	@Test
	public void testCreateAndExecuteTopology() {
		counterWord.createAndExecuteTopology();
	}
}
