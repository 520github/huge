/**
 * 
 */
package me.power.speed.common.algorithm.compress;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang.StringUtils;

/**
 * gzip压缩算法
 * @author xuehui.miao
 *
 */
public class GzipCompress extends AbstractCompress {

	/* (non-Javadoc)
	 * @see me.power.speed.common.algorithm.compress.Compress#compressString(java.lang.String)
	 */
	@Override
	public byte[] compressString(String content) throws CompressException {
		if(StringUtils.isBlank(content)) {
			return null;
		}
		byte[] result =  null;
		ByteArrayOutputStream byteStream = null;
		GZIPOutputStream gzipStream = null;
		try {
			byteStream = new ByteArrayOutputStream();
			gzipStream = new GZIPOutputStream(byteStream);
			gzipStream.write(content.getBytes());
			
			this.closeOutputStream(gzipStream);
			this.closeOutputStream(byteStream);
			
			result = byteStream.toByteArray();
		} catch (Exception e) {
			throw new CompressException(e);
		}finally {
//			this.closeOutputStream(gzipStream);
//			this.closeOutputStream(byteStream);
		}
		return result;
	}

}
