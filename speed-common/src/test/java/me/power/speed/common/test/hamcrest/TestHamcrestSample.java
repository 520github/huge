/**
 * 
 */
package me.power.speed.common.test.hamcrest;

import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;
//import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author xuehui.miao
 *
 */
public class TestHamcrestSample {
	private HamcrestSample sample;
	
	@Before
	public void before() {
		sample = new HamcrestSample();
	}
	
	@Test
	public void testAdd() {
		int result = sample.add(1, 2);
		int expected = 3;
		assertEquals(result, expected);
		
		//所有条件必须成立
		assertThat(result, allOf(greaterThan(1), lessThan(4)));
		
		//只要有一个条件成立，测试就通过
		assertThat(result, anyOf(greaterThan(5), lessThan(2)));
		
		//任何条件都成立
		assertThat(result, anything());
		
		//等于测试通过
		assertThat(result, is(3));
		
		//不等于测试通过
		assertThat(result, not(1));
	}
	
	@Test
	public void testDiv() {
		double result = sample.div(10, 3);
		
		// closeTo：浮点型变量的值在3.0±0.5范围内，测试通过
		assertThat(result, closeTo(3.0, 0.5));
		
		//greaterThan：变量的值大于指定值时，测试通过
		assertThat(result, greaterThan(3.0));
		
		// greaterThanOrEuqalTo：变量的值大于等于指定值时，测试通过
        assertThat(result, greaterThanOrEqualTo(3.3));
        // lessThanOrEqualTo：变量的值小于等于指定值时，测试通过
        assertThat(result, lessThanOrEqualTo(3.4));
	}
	
	@Test
    public void testGetName() {
		// 字符串匹配符
        String result = sample.getName("Magci");
        
     // containsString：字符串变量中包含指定字符串时，测试通过
        assertThat(result, containsString("ci"));
        // startsWith：字符串变量以指定字符串开头时，测试通过
        assertThat(result, startsWith("Ma"));
        // endsWith：字符串变量以指定字符串结尾时，测试通过
        assertThat(result, endsWith("i"));
        // euqalTo：字符串变量等于指定字符串时，测试通过
        assertThat(result, equalTo("Magci"));
        // equalToIgnoringCase：字符串变量在忽略大小写的情况下等于指定字符串时，测试通过
        assertThat(result, equalToIgnoringCase("magci"));
        // equalToIgnoringWhiteSpace：字符串变量在忽略头尾任意空格的情况下等于指定字符串时，测试通过
        assertThat(result, equalToIgnoringWhiteSpace(" Magci   "));
	}
	
	@Test
    public void testGetList() {
		// 集合匹配符
        List<String> result = sample.getList("Magci");
        // hasItem:Iterable变量中含有指定元素时，测试通过
        assertThat(result, hasItem("Magci"));
	}
	
	@Test
    public void testGetMap() {
		Map<String, String> result = sample.getMap("mgc", "Magci");
		// hasEntry：Map变量中含有指定键值对时，测试通过
        assertThat(result, hasEntry("mgc", "Magci"));
        // hasKey：Map变量中含有指定键时，测试通过
        assertThat(result, hasKey("mgc"));
        // hasValue：Map变量中含有指定值时，测试通过
        assertThat(result, hasValue("Magci"));
	}
}
