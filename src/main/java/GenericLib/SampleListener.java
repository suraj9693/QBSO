package GenericLib;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class SampleListener implements ITestListener {
	
	public void onTestFailure(ITestResult result) {
		String failedTest=result.getMethod().getMethodName();
		
		EventFiringWebDriver efw=new EventFiringWebDriver(BaseClass.d);
		File src=efw.getScreenshotAs(OutputType.FILE);
		File des=new File("./src/test/resources/Screenshots/"+failedTest+".png");
		
		try {
			FileUtils.copyFile(src, des);
		}catch(Throwable t) {
			t.printStackTrace();
		}

}
}