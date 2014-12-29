/**
 * 
 */
package me.power.speed.storage.redis.bitmap.exception;

/**
 * @author xuehui.miao
 *
 */
public class RedisBitmapException extends Exception {
	private static final long serialVersionUID = -1353097676289300514L;
	
	public RedisBitmapException(String message) {
		super(message);
	}
	
	public RedisBitmapException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
