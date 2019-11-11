package Report;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class Report {
	ExtentReports extent = new ExtentReports();
	public static ExtentTest logger;
	public void setup()
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
	    
		ExtentHtmlReporter reporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"//Reports//HTML_Report_"+timeStamp+".html");
			    
	    extent.attachReporter(reporter);
	    
	    }
	
	public void createrepo(String TestCase)
	{
			    
		 logger=extent.createTest(TestCase);
	}
	
	
	
	public void tearDown() 
	{
		
			
		extent.flush();
		
		
	}
	

}
