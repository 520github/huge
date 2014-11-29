/**
 * 
 */
package me.power.speed.log.logback;

import java.io.File;
import java.io.IOException;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * loader logback config file
 * @author keke
 *
 */
public class LogBackConfigLoader {
	
	/**
	 * 从classPath加载logBack日志配置文件
	 * @throws IOException
	 * @throws JoranException
	 */
	public static void loadFromClassPath(String logBackFile) throws IOException, JoranException {
		String classPath = LogBackConfigLoader.class.getClassLoader().getResource("").getPath();
		load(classPath + logBackFile);
	}
	
	public static void load (String configFileLocation) throws IOException, JoranException  {
		LoggerContext lc = new LoggerContext();
		File externalConfigFile = new File(configFileLocation);
		if(!externalConfigFile.exists()){ 
			throw new IOException("Logback External Config File Parameter ["+configFileLocation+"] does not reference a file that exists");
		}
		if(!externalConfigFile.isFile()){
			throw new IOException("Logback External Config File Parameter ["+configFileLocation+"] exists, but does not reference a file");
		}
		if(!externalConfigFile.canRead()){  
			throw new IOException("Logback External Config File ["+configFileLocation+"] exists and is a file, but cannot be read."); 
		}
		JoranConfigurator configurator = new JoranConfigurator(); 
		configurator.setContext(lc);
		lc.reset();
		configurator.doConfigure(configFileLocation);
		StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
	}
	
}
