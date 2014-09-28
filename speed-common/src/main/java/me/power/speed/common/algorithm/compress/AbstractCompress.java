/**
 * 
 */
package me.power.speed.common.algorithm.compress;

import java.io.OutputStream;

/**
 * 压缩算法抽象类型
 * @author xuehui.miao
 *
 */
public abstract class AbstractCompress implements Compress {

	/* (non-Javadoc)
	 * @see me.power.speed.common.algorithm.compress.Compress#compressString(java.lang.String)
	 */
	@Override
	public byte[] compressString(String content) throws CompressException {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void closeOutputStream(OutputStream outStream) {
		if(outStream == null) {
			return;
		}
		try {
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
