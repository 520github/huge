/**
 * 
 */
package me.power.speed.test.property.editor;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author xuehui.miao
 * 
 */
public class PersonPropertyEditor extends PropertyEditorSupport {
	public void setAsText(String text) {
		setValue(parseString(text));
	}

	private Object parseString(String text) {
		String[] parts = tokenizeToStringArray(text, ", ", false, false);
		String name = (parts.length > 0 ? parts[0] : "undefine");
		String sex = (parts.length > 1 ? parts[1] : "undefine");
		int age = (parts.length > 2 ? Integer.valueOf(parts[2]) : 0);
		return (text.length() > 0 ? new Person(name, sex, age) : null);
	}

	private String[] tokenizeToStringArray(String str, String delimiters,
			boolean trimTokens, boolean ignoreEmptyTokens) {
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
	}

	private String[] toStringArray(Collection collection) {
		if (collection == null) {
			return null;
		}
		return (String[]) collection.toArray(new String[collection.size()]);
	}
}
