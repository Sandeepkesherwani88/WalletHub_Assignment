package come.pack.common.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.pack.common.operations.CommonOprations;

public class WalletHubLoginPage {

	protected WebDriver driver;
	CommonOprations common_operations;
	private String pageTitle = "Join WalletHub";
	private By signUpLink = By.xpath("//a[text()='Sign Up']");
	private By loginLink = By.xpath("//a[text()='Login']");
	private By emailAddressTextBox = By.xpath("//input[@placeholder='Email Address']");
	private By passwordTextBox = By.xpath("//input[@placeholder='Password']");
	private By confirmPasswordTextBox = By.xpath("//input[@placeholder='Confirm Password']");
	private By getMyFreeScoreLabel = By.xpath("//span[text()='Get my free credit score & report']");
	private By joinButton = By.xpath("//span[text()='Join']");
	private By loginButton = By.xpath("//span[text()='Login']");
	private By checkBoxAttribute = By.xpath("//span[text()='Get my free credit score & report']/preceding::input[1]");
	private By successMessgaeAfterSignUp=By.xpath("//*[text()='Thank you for registering with WalletHub!']");
	
	public WalletHubLoginPage(WebDriver driver) {
		this.driver = driver;
		common_operations=new CommonOprations(this.driver);
	}
	
	public String getPageTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	public boolean verifyPageTitle() {
		return getPageTitle().contains(pageTitle);
	}
	
	public void clickOnSignUpLink() {
		common_operations.clickOnElement(signUpLink);
	}
	
	public void clickOnLoginLink() {
		common_operations.clickOnElement(loginLink);
	}
	
	public void clickOnJoinButton() {
		common_operations.clickOnElement(joinButton);
	}
	
	public void clickOnLoginButton() {
		common_operations.clickOnElement(loginButton);
	}
	
	public void enterEmailAddress(String emailAddress) {
		common_operations.enterValueInTextBox(emailAddressTextBox, emailAddress);
	}
	
	public void enterPassword(String password) {
		common_operations.enterValueInTextBox(passwordTextBox, password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		common_operations.enterValueInTextBox(confirmPasswordTextBox, confirmPassword);
	}
	
	public void uncheckTheScoreLabelCheckbox() {
		common_operations.checkVisibilityOfElement(getMyFreeScoreLabel);
		String attributeValue=common_operations.getAttributValue(checkBoxAttribute, "class");
		if(attributeValue.contains("ng-not-empty")) {
			common_operations.clickOnElement(getMyFreeScoreLabel);
		}
		
	}
	
	public void verifySuccessfullySignupMessage() {
		common_operations.checkVisibilityOfElement(successMessgaeAfterSignUp);	
	}
	
}
