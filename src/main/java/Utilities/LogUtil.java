package Utilities;
/***Import the necessary log4j classes for logging functionality.***/
import org.apache.logging.log4j.*;

/***Define a utility class called LogUtil for handling logging operations.***/
public class LogUtil 
{
     	/***Create a static logger instance using LogManager, associated with the LogUtil class.***/
		private static Logger LogObj = LogManager.getLogger(LogUtil.class);
		
		/***Log a message to indicate the start of a test case***/
		public static void startTestCase(String testCaseName){
			LogObj.info("Test Case "+testCaseName+ " Started");
			} 
 
		/***Log a message to indicate the end of a test case***/
		public static void endTestCase(String testCaseName){
			LogObj.info("Test Case "+testCaseName+ " End");
		}
		/***Log an informational message.***/
		public static void info(String msg) {
				LogObj.info(msg);
		}
		/***Log a warning message.***/
		public static void warn(String msg) {
		    LogObj.warn(msg);
		}
		/***Log an error message.***/
		public static void error(String msg) {
		    LogObj.error(msg);
		}
		/***Log a fatal error message.***/
		 public static void fatal(String msg) {
		    LogObj.fatal(msg);
		}
}  
