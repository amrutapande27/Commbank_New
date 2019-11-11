package CommTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Report.Report;




public class CommTest {

	public static WebDriver driver;
	public static void main(String[] args) {
		OpenBrowser();
	}
		// TODO Auto-generated method stub
		
	public static void OpenBrowser() {	
        try {
        	
        	    Report htmlrepo=new Report();
        	    htmlrepo.setup();
        	    htmlrepo.createrepo("Test Commbank");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver\\chromedriver.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--no-sandbox");
				options.addArguments("test-type");
				options.addArguments("start-maximized");
				
				capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				
				driver = new ChromeDriver(options);
				driver.get("https://www.commbank.com.au");
				if(driver.getTitle().contains("CommBank"))
				Report.logger.pass("On CommBank Website");
				
					
				//unable to find oversea options on webpage hence used Buiness Usecase
				
				driver.findElement(By.xpath("//*[contains(@title, 'Business')]")).click();
				List<WebElement> list=driver.findElements(By.xpath("//*[@class='section-navigation']/descendant::*"));
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getAttribute("title").contains("Business accounts & cards"))
						Report.logger.pass("Subtitle Business accounts & cards Matched under Business");	
					if(list.get(i).getAttribute("title").contains("Equipment & car finance"))
						Report.logger.pass("Subtitle Equipment & car finance Matched under Business");	
					if(list.get(i).getAttribute("title").contains("Industry expertise"))	
						Report.logger.pass("Subtitle Industry expertise Matched under Business");
					
				}
				driver.findElement(By.xpath("//*[contains(@class, 'log-on-text')]")).click();
				WebDriverWait wait = new WebDriverWait(driver, 60);
				
				WebElement iFrame= driver.findElement(By.tagName("iframe"));
				driver.switchTo().frame(iFrame);
				WebElement usename=driver.findElement(By.id("txtMyClientNumber_label"));
				WebElement Password=driver.findElement(By.xpath("//input[@id='txtMyPassword_field']"));
				if(usename.isDisplayed() || Password.isDisplayed()) {
					System.out.println("On Login Page");
					Report.logger.pass("On NetBank Page");
				}
				else 
					Report.logger.fail("UserName and Password field Not displayed");
				driver.close();
				htmlrepo.tearDown();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			
				
				
			}
	
		
	}
	
	
}
