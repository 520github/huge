package me.power.speed.test.cache.key;

import java.io.Serializable;
import java.lang.reflect.Method;

import me.power.speed.base.hashcode.HashCodeCalculator;
import me.power.speed.base.hashcode.HashCodeUtils;
import org.aopalliance.intercept.MethodInvocation;

public class HashCodeCacheKeyGenerator implements CacheKeyGenerator {

	private boolean generateArgumentHashCode;

	public HashCodeCacheKeyGenerator() {
		super();
	}

	public HashCodeCacheKeyGenerator(boolean generateArgumentHashCode) {
		this();
		setGenerateArgumentHashCode(generateArgumentHashCode);
	}

	public final Serializable generateKey(MethodInvocation methodInvocation) {
		HashCodeCalculator hashCodeCalculator = new HashCodeCalculator();

		Method method = methodInvocation.getMethod();
		hashCodeCalculator.append(System.identityHashCode(method));

		Object[] methodArguments = methodInvocation.getArguments();
		if (methodArguments != null) {
			int methodArgumentCount = methodArguments.length;

			for (int i = 0; i < methodArgumentCount; i++) {
				Object methodArgument = methodArguments[i];
				int hash = 0;

				if (generateArgumentHashCode) {
					hash = HashCodeUtils.reflectionHashCode(methodArgument);
				} else {
					hash = HashCodeUtils.nullSafeHashCodeObject(methodArgument);
				}

				hashCodeCalculator.append(hash);
			}
		}

		long checkSum = hashCodeCalculator.getCheckSum();
		int hashCode = hashCodeCalculator.getHashCode();

		Serializable cacheKey = new HashCodeCacheKey(checkSum, hashCode);
		return cacheKey;
	}

	public final void setGenerateArgumentHashCode(
			boolean newGenerateArgumentHashCode) {
		generateArgumentHashCode = newGenerateArgumentHashCode;
	}

}
