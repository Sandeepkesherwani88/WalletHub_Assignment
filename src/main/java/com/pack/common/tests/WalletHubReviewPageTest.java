package com.pack.common.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetup;
import com.pack.common.operations.CommonOprations;

import come.pack.common.pageobjects.WalletHubLoginPage;
import come.pack.common.pageobjects.WalletHubReviewPage;

public class WalletHubReviewPageTest extends TestBaseSetup{

	private WebDriver driver;
	WalletHubReviewPage walletHubReviewPage;
	WalletHubLoginPage	walletHubLoginPage;
	CommonOprations common_operation;
	Properties prop;


	@BeforeClass
	public void setUp() {
		driver=getDriver();
	}


	@Test
	public void verifyReviewOnPage() throws Exception {
		try {
			System.out.println("Page Title...");
			walletHubReviewPage = new WalletHubReviewPage(driver);
			walletHubReviewPage.verifyPageTitle();
			walletHubReviewPage.verifyAllFiveStartsToBePresent();
			walletHubReviewPage.clickingOnRequestedNumberOfStars();
			walletHubReviewPage.verifyAllselectedOnFinalPage();
			walletHubReviewPage.selectPolicyValue();
			walletHubReviewPage.enter200ReviewComments();

			System.out.println("Login Functionality..");		
			walletHubLoginPage = new WalletHubLoginPage(driver);
			common_operation=new CommonOprations(driver);
			Properties prop=common_operation.readFromPropertiesFile();
			walletHubLoginPage.clickOnLoginLink();
			walletHubLoginPage.enterEmailAddress(prop.getProperty("walletHubEmail"));
			walletHubLoginPage.enterPassword(prop.getProperty("walletHubPassword"));
			walletHubLoginPage.clickOnLoginButton();

			walletHubReviewPage.verifyPostedReviewComments();
		}catch(Exception ex) {
			System.out.println("Exception occured please check ..."+ex.getStackTrace());
		}
	}
}
