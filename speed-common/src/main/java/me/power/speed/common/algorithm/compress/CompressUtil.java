/**
 * 
 */
package me.power.speed.common.algorithm.compress;

/**
 * @author xuehui.miao
 *
 */
public class CompressUtil {
	
	public static byte[] compressData2Zip(String data) throws CompressException {
		Compress compress = new GzipCompress();
		return compress.compressString(data);
	}
	
	public static byte[] compressData2Deflate(String data) throws CompressException {
		Compress compress = new DeflateCompress();
		return compress.compressString(data);
	}
}
