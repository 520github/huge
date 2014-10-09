/**
 * 
 */
package me.power.speed.common.serialize;

import java.io.Serializable;

/**
 * @author xuehui.miao
 *
 */
public class SerializeObject implements Serializable {
	
	private static final long serialVersionUID = -4548427645830386969L;
	
	private String name;
	private String nation;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public static SerializeObject getSerializeObject() {
		SerializeObject object = new SerializeObject();
		object.setNation("china");
		object.setName("lala");
		
		return object;
	}
	
}
