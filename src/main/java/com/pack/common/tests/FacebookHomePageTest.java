package com.pack.common.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetup;
import com.pack.common.operations.CommonOprations;

import come.pack.common.pageobjects.FacebookHomePage;
import come.pack.common.pageobjects.FacebookPageLogin;

public class FacebookHomePageTest extends TestBaseSetup{

	private WebDriver driver;
	FacebookHomePage facebookHomePage;
	FacebookPageLogin facebookPageLogin;
	CommonOprations common_operation;
	Properties prop;


	@BeforeClass
	public void setUp() {
		driver=getDriver();
	}


	@Test
	public void verifyloginFunctionality() throws Exception {
		try {
			System.out.println("Facebook Login test...");
			facebookPageLogin = new FacebookPageLogin(driver);
			common_operation=new CommonOprations(driver);
			Properties prop=common_operation.readFromPropertiesFile();
			facebookPageLogin.performLoginOperation(prop.getProperty("userName"), prop.getProperty("password"));
			facebookHomePage = new FacebookHomePage(driver);
			facebookHomePage.createPost(prop.getProperty("messageTOBePosted"));
			facebookHomePage.verifyPost(prop.getProperty("messageTOBePosted"));
			facebookHomePage.deletePost(prop.getProperty("messageTOBePosted"));
		}catch(Exception ex) {
			System.out.println("Exception occured please check ..."+ex.getStackTrace());
		}
	}
}
