/**
 * 
 */
package me.power.speed.common.algorithm.compress;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import org.apache.commons.lang.StringUtils;

/**
 * Deflate压缩算法
 * @author xuehui.miao
 *
 */
public class DeflateCompress extends AbstractCompress {

	/* (non-Javadoc)
	 * @see me.power.speed.common.algorithm.compress.Compress#compressString(java.lang.String)
	 */
	@Override
	public byte[] compressString(String content) throws CompressException {
		if(StringUtils.isBlank(content)) {
			return null;
		}
		byte[] result = null;
		ByteArrayOutputStream byteStream = null;
		DeflaterOutputStream deflaterStream = null;
		try {
			byteStream = new ByteArrayOutputStream();
			deflaterStream = new DeflaterOutputStream(byteStream,new Deflater(9, true));
		} catch (Exception e) {
			throw new CompressException(e);
		}finally {
			this.closeOutputStream(deflaterStream);
			this.closeOutputStream(byteStream);
		}
		return result;
	}
}
