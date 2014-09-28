/**
 * 
 */
package me.power.speed.common.algorithm.compress;

/**
 * 压缩字符串算法
 * @author xuehui.miao
 *
 */
public interface Compress {
	public byte[] compressString(String content) throws CompressException;
}
