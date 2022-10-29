package GenericLib;

import java.util.concurrent.TimeUnit;

public class WebDriverCommLibs {
	
	public void implicitWait(int time) {
		BaseClass.d.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

}
