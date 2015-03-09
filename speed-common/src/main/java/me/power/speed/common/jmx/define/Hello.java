/**
 * 
 */
package me.power.speed.common.jmx.define;


/**
 * @author xuehui.miao
 *
 */
public class Hello implements HelloMBean {
	private final String name = "Reginald";   
    private int cacheSize = DEFAULT_CACHE_SIZE;   
    private static final int DEFAULT_CACHE_SIZE = 200; 
	

	/* (non-Javadoc)
	 * @see me.power.speed.common.jmx.define.HelloMBean#sayHello()
	 */
	public void sayHello() {
		System.out.println("hello, world");
	}

	/* (non-Javadoc)
	 * @see me.power.speed.common.jmx.define.HelloMBean#add(int, int)
	 */
	public int add(int x, int y) {
		return x + y;
	}

	/* (non-Javadoc)
	 * @see me.power.speed.common.jmx.define.HelloMBean#getName()
	 */
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see me.power.speed.common.jmx.define.HelloMBean#getCacheSize()
	 */
	public int getCacheSize() {
		return this.cacheSize;
	}

	/* (non-Javadoc)
	 * @see me.power.speed.common.jmx.define.HelloMBean#setCacheSize(int)
	 */
	public void setCacheSize(int size) {
		this.cacheSize = size;   
        System.out.println("Cache size now " + this.cacheSize);
	}

}
