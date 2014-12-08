package me.power.speed.base.hashcode;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import me.power.speed.base.reflection.ReflectionUtils;
import me.power.speed.base.util.ObjectUtils;

public abstract class HashCodeUtils {
	private static final int INITIAL_HASH = 7;

	private static final int MULTIPLIER = 31;

	public static int reflectionHashCode(Object obj) {
		if (obj == null) {
			return 0;
		}
		Class targetClass = obj.getClass();
		if (ObjectUtils.isArrayOfPrimitives(obj)
				|| ObjectUtils.isPrimitiveOrWrapper(targetClass)) {
			return nullSafeHashCodeObject(obj);
		}
		if (targetClass.isArray()) {
			return nullSafeHashCodeObjectArray((Object[]) obj);
		}
		if (obj instanceof Collection) {
			return hashCodeCollection((Collection) obj);
		}
		if (obj instanceof Map) {
			return hashCodeMap((Map) obj);
		}

		Class clazz = (obj instanceof Class) ? (Class) obj : obj.getClass();
		Method hashCodeMethod = ReflectionUtils.findMethod(clazz, "hashCode",
				new Class[0]);
		if (hashCodeMethod != null) {
			return obj.hashCode();
		}

		int hash = INITIAL_HASH;
		try {
			while (targetClass != null) {
				Field[] fields = targetClass.getDeclaredFields();
				AccessibleObject.setAccessible(fields, true);

				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					int modifiers = field.getModifiers();

					if (!Modifier.isStatic(modifiers)
							&& !Modifier.isTransient(modifiers)) {
						hash = MULTIPLIER * hash
								+ reflectionHashCode(field.get(obj));
					}
				}
				targetClass = targetClass.getSuperclass();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return hash;
	}

	public static int hashCodeCollection(Collection collection) {
		int hash = INITIAL_HASH;

		for (Iterator i = collection.iterator(); i.hasNext();) {
			hash = MULTIPLIER * hash + reflectionHashCode(i.next());
		}

		return hash;
	}

	public static int hashCodeMap(Map map) {
		int hash = INITIAL_HASH;

		for (Iterator i = map.entrySet().iterator(); i.hasNext();) {
			Map.Entry entry = (Map.Entry) i.next();
			hash = MULTIPLIER * hash + reflectionHashCode(entry);
		}

		return hash;
	}

	public static int hashCodeBoolean(boolean bool) {
		return bool ? 1231 : 1237;
	}

	public static int hashCodeDouble(double dbl) {
		long bits = Double.doubleToLongBits(dbl);
		return hashCodeLong(bits);
	}

	public static int hashCodeLong(long lng) {
		return (int) (lng ^ (lng >>> 32));
	}

	public static int hashCodeFloat(float flt) {
		return Float.floatToIntBits(flt);
	}

	public static int nullSafeHashCodeObject(Object obj) {
		if (obj == null)
			return 0;

		if (obj instanceof boolean[]) {
			return nullSafeHashCodeBooleanArray((boolean[]) obj);
		}
		if (obj instanceof byte[]) {
			return nullSafeHashCodeByteArray((byte[]) obj);
		}
		if (obj instanceof char[]) {
			return nullSafeHashCodeCharArray((char[]) obj);
		}
		if (obj instanceof double[]) {
			return nullSafeHashCodeDoubleArray((double[]) obj);
		}
		if (obj instanceof float[]) {
			return nullSafeHashCodeFloatArray((float[]) obj);
		}
		if (obj instanceof int[]) {
			return nullSafeHashCodeIntArray((int[]) obj);
		}
		if (obj instanceof long[]) {
			return nullSafeHashCodeLongArray((long[]) obj);
		}
		if (obj instanceof short[]) {
			return nullSafeHashCodeShortArray((short[]) obj);
		}
		if (obj instanceof Object[]) {
			return nullSafeHashCodeObjectArray((Object[]) obj);
		}

		return obj.hashCode();
	}

	public static int nullSafeHashCodeBooleanArray(boolean[] array) {
		if (array == null)
			return 0;

		int hash = INITIAL_HASH;
		int arraySize = array.length;
		for (int i = 0; i < arraySize; i++) {
			hash = MULTIPLIER * hash + hashCodeBoolean(array[i]);
		}

		return hash;
	}

	public static int nullSafeHashCodeByteArray(byte[] array) {
		if (array == null)
			return 0;

		int hash = INITIAL_HASH;
		int arraySize = array.length;
		for (int i = 0; i < arraySize; i++) {
			hash = MULTIPLIER * hash + array[i];
		}

		return hash;
	}

	public static int nullSafeHashCodeCharArray(char[] array) {
		if (array == null)
			return 0;

		int hash = INITIAL_HASH;
		int arraySize = array.length;
		for (int i = 0; i < arraySize; i++) {
			hash = MULTIPLIER * hash + array[i];
		}

		return hash;
	}

	public static int nullSafeHashCodeDoubleArray(double[] array) {
		if (array == null)
			return 0;

		int hash = INITIAL_HASH;
		int arraySize = array.length;
		for (int i = 0; i < arraySize; i++) {
			hash = MULTIPLIER * hash + hashCodeDouble(array[i]);
		}

		return hash;
	}

	public static int nullSafeHashCodeFloatArray(float[] array) {
		if (array == null)
			return 0;

		int hash = INITIAL_HASH;
		int arraySize = array.length;
		for (int i = 0; i < arraySize; i++) {
			hash = MULTIPLIER * hash + hashCodeFloat(array[i]);
		}

		return hash;
	}

	public static int nullSafeHashCodeIntArray(int[] array) {
		if (array == null)
			return 0;

		int hash = INITIAL_HASH;
		int arraySize = array.length;
		for (int i = 0; i < arraySize; i++) {
			hash = MULTIPLIER * hash + array[i];
		}

		return hash;
	}

	public static int nullSafeHashCodeLongArray(long[] array) {
		if (array == null)
			return 0;

		int hash = INITIAL_HASH;
		int arraySize = array.length;
		for (int i = 0; i < arraySize; i++) {
			hash = MULTIPLIER * hash + hashCodeLong(array[i]);
		}

		return hash;
	}

	public static int nullSafeHashCodeShortArray(short[] array) {
		if (array == null)
			return 0;

		int hash = INITIAL_HASH;
		int arraySize = array.length;
		for (int i = 0; i < arraySize; i++) {
			hash = MULTIPLIER * hash + array[i];
		}

		return hash;
	}

	public static int nullSafeHashCodeObjectArray(Object[] array) {
		if (array == null)
			return 0;

		int hash = INITIAL_HASH;
		int arraySize = array.length;
		for (int i = 0; i < arraySize; i++) {
			hash = MULTIPLIER * hash + nullSafeHashCodeObject(array[i]);
		}

		return hash;
	}
}
