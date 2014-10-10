/**
 * 
 */
package me.power.speed.huge.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xuehui.miao
 *
 */
public class ReadStreamServlet extends HttpServlet {
	public final static int GZIP_MAGIC = 0x8b1f;
	
	private static final long serialVersionUID = 8006632775705029908L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.handleRequestStream(req);
	}
	
	private void handleRequestStream(HttpServletRequest req) {
		long contentLength = req.getContentLength();
		if(contentLength < 1) {
			System.out.println("find illegal content length " + contentLength);
			return;
		}
		
		InputStream is = null;
		int inputStreamLength = 0;
		try {
			is = req.getInputStream();
			inputStreamLength = is.available();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		BufferedInputStream bis = new BufferedInputStream(is);
		int magic = 0;
		try {
			bis.mark(2);
			magic = readUShort(bis);
			bis.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BufferedReader br = null;
		String type = "unknow";
		if(magic == GZIP_MAGIC) {
			bis.mark(18);
			try {
				br = new BufferedReader(new InputStreamReader(new GZIPInputStream(bis)));
				type = "gzip";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				br = new BufferedReader(new InputStreamReader(new InflaterInputStream(bis,new Inflater(true))));
				type = "inflater";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String result = this.getStringFromStream(br);
		
		try {
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("result:" + result);
		
		System.out.println("compressType:"+type+";contentLength:" + contentLength + ";inputStreamLength:" + inputStreamLength + ";after length:" + result.getBytes().length);
	}
	
	private String getStringFromStream(BufferedReader br) {
		StringBuffer resultTmp= new StringBuffer("");
		String line = null;
		try {
			while((line=br.readLine()) != null) {
				resultTmp.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return resultTmp.toString();
	}
	
	 /*
     * Reads unsigned short in Intel byte order.
     */
    private int readUShort(InputStream in) throws IOException {
        int b = readUByte(in);
        return ((int)readUByte(in) << 8) | b;
    }

    /*
     * Reads unsigned byte.
     */
    private int readUByte(InputStream in) throws IOException {
        int b = in.read();
        if (b == -1) {
            throw new EOFException();
        }
        if (b < -1 || b > 255) {
            // Report on this.in, not argument in; see read{Header, Trailer}.
            throw new IOException("readUByte() returned value out of range -1..255: " + b);
        }
        return b;
    }
}
