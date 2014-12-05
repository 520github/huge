/**
 * 
 */
package me.power.speed.test.property.editor;

import org.junit.Test;

import me.power.speed.test.AbstractSpringTest;

/**
 * @author xuehui.miao
 *
 */
public class TestPersonPropertyEditor extends AbstractSpringTest {
	private  PersonPropertyEditor editor = new PersonPropertyEditor();
	
	@Test
	public void testPersonPropertyEditor() {
		String inputValue = "aSam,man,22";
		editor.setAsText(inputValue);
		System.out.println(editor.getValue());
		
		inputValue = "aSam,man,22";
		editor.setAsText(inputValue);
		System.out.println(editor.getValue());
	}
	
}
