package com.pack.common.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.pack.base.TestBaseSetup;
import com.pack.common.operations.CommonOprations;
import come.pack.common.pageobjects.WalletHubLoginPage;

public class WalletHubLoginPageTest extends TestBaseSetup{

	private WebDriver driver;
	WalletHubLoginPage walletHubLoginPage;
	CommonOprations common_operation;
	Properties prop;


	@BeforeClass
	public void setUp() {
		driver=getDriver();
	}


	@Test
	public void verifySignUpFunctionality() throws Exception {
		try {
			System.out.println("SignUp Functionality..");
			common_operation=new CommonOprations(driver);
			Properties prop=common_operation.readFromPropertiesFile();
			walletHubLoginPage = new WalletHubLoginPage(driver);
			walletHubLoginPage.verifyPageTitle();
			walletHubLoginPage.clickOnSignUpLink();
			walletHubLoginPage.enterEmailAddress(prop.getProperty("walletHubEmail"));
			walletHubLoginPage.enterPassword(prop.getProperty("walletHubPassword"));
			walletHubLoginPage.enterConfirmPassword(prop.getProperty("walletHubPassword"));
			walletHubLoginPage.uncheckTheScoreLabelCheckbox();
			walletHubLoginPage.clickOnJoinButton();
			walletHubLoginPage.verifySuccessfullySignupMessage();
		}catch(Exception ex) {
			System.out.println("Exception occured please check ..."+ex.getStackTrace());
		}
	}

	//Code functionality we can check in WalletHubReviewPageTest class
	//	@Test
	//	public void verifyLogInFunctionality() throws Exception {
	//		System.out.println("Login Functionality..");		
	//		walletHubLoginPage = new WalletHubLoginPage(driver);
	//		common_operation=new CommonOprations(driver);
	//		Properties prop=common_operation.readFromPropertiesFile();
	//		walletHubLoginPage.clickOnLoginLink();
	//		walletHubLoginPage.enterEmailAddress(prop.getProperty("walletHubEmail"));
	//		walletHubLoginPage.enterPassword(prop.getProperty("walletHubPassword"));
	//		walletHubLoginPage.clickOnLoginButton();
	//	}



}
