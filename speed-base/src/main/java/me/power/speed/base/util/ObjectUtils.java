package me.power.speed.base.util;

import java.util.HashSet;
import java.util.Set;

public class ObjectUtils {
	private static final Set primitivesAndWrappers;
	
	static {
		primitivesAndWrappers = new HashSet();
	    primitivesAndWrappers.add(boolean.class);
	    primitivesAndWrappers.add(Boolean.class);
	    primitivesAndWrappers.add(byte.class);
	    primitivesAndWrappers.add(Byte.class);
	    primitivesAndWrappers.add(char.class);
	    primitivesAndWrappers.add(Character.class);
	    primitivesAndWrappers.add(double.class);
	    primitivesAndWrappers.add(Double.class);
	    primitivesAndWrappers.add(float.class);
	    primitivesAndWrappers.add(Float.class);
	    primitivesAndWrappers.add(int.class);
	    primitivesAndWrappers.add(Integer.class);
	    primitivesAndWrappers.add(long.class);
	    primitivesAndWrappers.add(Long.class);
	    primitivesAndWrappers.add(short.class);
	    primitivesAndWrappers.add(Short.class);
	}
	
	public static boolean isArrayOfPrimitives(Object array) {
		boolean primitiveArray = false;
		
		if (array != null) {
			Class clazz = array.getClass();
			primitiveArray = clazz.isArray()
			          && clazz.getComponentType().isPrimitive();
		}
		return primitiveArray;
	}
	
	/**
	   * Returns <code>true</code> if the given class is any of the following:
	   * <ul>
	   * <li><code>boolean</code></li>
	   * <li>Boolean</li>
	   * <li><code>byte</code></li>
	   * <li>Byte</li>
	   * <li><code>char</code></li>
	   * <li>Character</li>
	   * <li><code>double</code></li>
	   * <li>Double</li>
	   * <li><code>float</code></li>
	   * <li>Float</li>
	   * <li><code>int</code></li>
	   * <li>Integer</li>
	   * <li><code>long</code></li>
	   * <li>Long</li>
	   * <li><code>short</code></li>
	   * <li>Short</li>
	   * </ul>
	   * 
	   * @param clazz
	   *          the given class.
	   * @return <code>true</code> if the given class represents a primitive or a
	   *         wrapper, <code>false</code> otherwise.
	   */
	public static boolean isPrimitiveOrWrapper(Class clazz) {
		return primitivesAndWrappers.contains(clazz);
	}
}
