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
		
		//���������������
		assertThat(result, allOf(greaterThan(1), lessThan(4)));
		
		//ֻҪ��һ���������������Ծ�ͨ��
		assertThat(result, anyOf(greaterThan(5), lessThan(2)));
		
		//�κ�����������
		assertThat(result, anything());
		
		//���ڲ���ͨ��
		assertThat(result, is(3));
		
		//�����ڲ���ͨ��
		assertThat(result, not(1));
	}
	
	@Test
	public void testDiv() {
		double result = sample.div(10, 3);
		
		// closeTo�������ͱ�����ֵ��3.0��0.5��Χ�ڣ�����ͨ��
		assertThat(result, closeTo(3.0, 0.5));
		
		//greaterThan��������ֵ����ָ��ֵʱ������ͨ��
		assertThat(result, greaterThan(3.0));
		
		// greaterThanOrEuqalTo��������ֵ���ڵ���ָ��ֵʱ������ͨ��
        assertThat(result, greaterThanOrEqualTo(3.3));
        // lessThanOrEqualTo��������ֵС�ڵ���ָ��ֵʱ������ͨ��
        assertThat(result, lessThanOrEqualTo(3.4));
	}
	
	@Test
    public void testGetName() {
		// �ַ���ƥ���
        String result = sample.getName("Magci");
        
     // containsString���ַ��������а���ָ���ַ���ʱ������ͨ��
        assertThat(result, containsString("ci"));
        // startsWith���ַ���������ָ���ַ�����ͷʱ������ͨ��
        assertThat(result, startsWith("Ma"));
        // endsWith���ַ���������ָ���ַ�����βʱ������ͨ��
        assertThat(result, endsWith("i"));
        // euqalTo���ַ�����������ָ���ַ���ʱ������ͨ��
        assertThat(result, equalTo("Magci"));
        // equalToIgnoringCase���ַ��������ں��Դ�Сд������µ���ָ���ַ���ʱ������ͨ��
        assertThat(result, equalToIgnoringCase("magci"));
        // equalToIgnoringWhiteSpace���ַ��������ں���ͷβ����ո������µ���ָ���ַ���ʱ������ͨ��
        assertThat(result, equalToIgnoringWhiteSpace(" Magci   "));
	}
	
	@Test
    public void testGetList() {
		// ����ƥ���
        List<String> result = sample.getList("Magci");
        // hasItem:Iterable�����к���ָ��Ԫ��ʱ������ͨ��
        assertThat(result, hasItem("Magci"));
	}
	
	@Test
    public void testGetMap() {
		Map<String, String> result = sample.getMap("mgc", "Magci");
		// hasEntry��Map�����к���ָ����ֵ��ʱ������ͨ��
        assertThat(result, hasEntry("mgc", "Magci"));
        // hasKey��Map�����к���ָ����ʱ������ͨ��
        assertThat(result, hasKey("mgc"));
        // hasValue��Map�����к���ָ��ֵʱ������ͨ��
        assertThat(result, hasValue("Magci"));
	}
}
