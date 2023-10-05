package Utilities;

/***Import necessary libraries***/
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/***Listener implementation class that implements the ITestListener interface***/
public class ListnerImplementaion implements ITestListener{
	
	/***Counters to keep track of passed and failed tests***/
    public int count_pass =0;
    public int count_fail = 0;
    
    /***Static fields to hold the ExtentReports instance and the current test case's ExtentTest instance***/
	public static ExtentReports extent;
	public static ExtentTest test;
	
	/***This method is called when a test starts***/
	@Override
	public void onTestStart(ITestResult result) {
		
		/***Create a new test in the ExtentReports instance with the test method's name***/
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	/***This method is called when a test succeeds***/
	@Override
	public void onTestSuccess(ITestResult result) {
		
		/***Log the test as passed with its method name***/
		test.log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+ "is Passed");
	}
	
	/***This method is called when a test fails***/
	@Override
	public void onTestFailure(ITestResult result) {
		
		/***Log the test as failed with its method name***/
		test.log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ "is Failed");
		
		/***Log the throwable/exception that caused the failure***/
		test.log(Status.FAIL, result.getThrowable());
				
	}
	
	/***Following methods are not implemented in this code, hence no action is taken for them.***/
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}
	
	/***This method is called when the test suite starts***/
	@Override
	public void onStart(ITestContext context) {
		
	/***Initialize the extent instance by calling the setupExtentReport method from ExtentSetup class***/
		extent = ExtentSetup.setupExtentReport();
	}

	/***This method is called when the test suite finishes***/
	@Override
	public void onFinish(ITestContext context) {
		
	/***Flush the extent reports to save the data and generate the report***/
		extent.flush();
       
	}

}


