package GenericLib;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import TestScripts.LoginPage;

public class BaseClass {
	
	public static WebDriver d;
	public static ExtentReports extent;
	public static ExtentTest test;

	static {
	Calendar calendar=Calendar.getInstance();
	SimpleDateFormat formater=new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
	extent=new ExtentReports("./src/test/resources/ExtentReports/"+formater.format(calendar.getTime())+".html", true);
}
	public void getTest(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + "Test is Pass");
		}else if(result.getStatus()==ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName() + "Test failed & reason is " + result.getThrowable());
		}else if(result.getStatus()==ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + "Test skipped & reason is " + result.getThrowable());
	}else if(result.getStatus()==ITestResult.STARTED) {
		test.log(LogStatus.INFO, result.getName() + "Test started");
	}
}

/*
 * @BeforeMethod public void startTest(Method result) {
 * test=extent.startTest(result.getName()); test.log(LogStatus.INFO,
 * result.getName() + "Test started"); }
 * 
 * @AfterMethod public void callGetTest(ITestResult result) { getTest(result); }
 * 
 * @AfterClass(alwaysRun=true) public void endTest() { extent.endTest(test);
 * extent.flush(); d.close(); }
 */
	@BeforeClass
	public void configBC() {
		if(Constants.browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/BrowserDriver/chromedriver.exe");
			d=new ChromeDriver();
		}else if(Constants.browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./src/main/resources/Resources/geckodriver.exe");
			d=new FirefoxDriver();
		}else if(Constants.browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "./src/main/resources/Resources/MicrosoftWebDriver.exe");
			d=new EdgeDriver();
		}else if(Constants.browser.equals("phantomjs")) {
			System.setProperty("phantomjs.binary.path", "src/main/resources/Resources/phantomjs.exe");
			d=new PhantomJSDriver();
		}
	}

	
	  @BeforeMethod public void configBM() {
	  WebDriverCommLibs wait=new
	  WebDriverCommLibs();
	  wait.implicitWait(20); 
	  d.get(Constants.url); 
	  LoginPage login=PageFactory.initElements(d, LoginPage.class);
	  login.LoginTest();
	  }
	 
	
	/*
	 * @AfterMethod public void configAM() { LogOutFromApp
	 * logout=PageFactory.initElements(d, LogOutFromApp.class); logout.logout(); }
	 */
	@AfterClass
	public void configAC() {
		d.close();
	}

}
