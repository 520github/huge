/**
 * 
 */
package me.power.speed.common.cache.spring;

/**
 * @author xuehui.miao
 *
 */
public class SpringModuleCache {
	private String name = "matthew";
    
    public String getName() {
        return name;
    }
    
    public String getName(String salution) {
        return salution + " " + name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void changeNameAndNotTellCache(String name) {
        this.name = name;
    }
}
