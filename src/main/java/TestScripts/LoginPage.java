package TestScripts;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import GenericLib.BaseClass;
import GenericLib.Constants;

public class LoginPage extends BaseClass{
	
	@Test
	public void LoginTest() {
		
		String ExpectedPageURL= "https://www.facebook.com/";
		
		d.findElement(By.id("email")).sendKeys(Constants.un);
		d.findElement(By.id("pass")).sendKeys(Constants.pwd);
		d.findElement(By.name("login")).click();
		
		String URL= d.getCurrentUrl();
		System.out.println(URL);
		
		
		  String ActualPageURL=d.getCurrentUrl();
		  
		  //Assert.assertEquals(ActualPageURL, ExpectedPageURL);
		 
	}

}
