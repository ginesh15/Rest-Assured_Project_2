package Utilities;
/***Import necessary libraries***/
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/***Define a class named "ExtentSetup"***/
public class ExtentSetup  {
	
	/***Declare a static variable to hold the ExtentReports instance***/
	public static ExtentReports extent;
	
	/***Declare a static method to set up the ExtentReport***/
	public static ExtentReports setupExtentReport() {
		
		/***Create a date format for generating the report timestamp***/
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		
		/***Get the current date***/
		Date date = new Date();
		
		/***Format the date as per the defined format***/
		String actualDate = format.format(date);
		
		/***Define the path where the report will be saved***/
		String reportPath = System.getProperty("user.dir")+"/Reports/ExtentReport.html";
		
		/***Create an ExtentSparkReporter instance with the specified report path***/
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
		
		/***Create a new ExtentReports instance***/
		extent = new ExtentReports(); 
		
		/***Attach the ExtentSparkReporter to the ExtentReports instance***/  
		extent.attachReporter(sparkReport);
		
		/***Configure the document title for the report***/
		sparkReport.config().setDocumentTitle("Extent Report");
		
		/***Set the report theme to dark***/
		sparkReport.config().setTheme(Theme.DARK);
		
		/***Set the name of the report***/
		sparkReport.config().setReportName("Ginesh Goyal");
		
		/***Return the ExtentReports instance that has been set up***/
		return extent;
	}
}
