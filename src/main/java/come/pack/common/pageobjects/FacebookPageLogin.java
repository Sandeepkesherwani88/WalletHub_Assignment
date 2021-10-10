package come.pack.common.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.pack.common.operations.CommonOprations;

public class FacebookPageLogin {

	protected WebDriver driver;
	CommonOprations common_operations;

	
	private String pageTitle = "Facebook – log in or sign up";
	private By emailAddressOrNumberTextBox = By.id("email");
	private By passTextBox = By.id("pass");
	private By logInButton = By.xpath("//button[text()='Log In']");
	private By forgotPasswordLink = By.xpath("//a[text()='Forgotten account?']");
	private By englishLanguageButton = By.xpath("//a[text()='English (UK)']");
	
	public FacebookPageLogin(WebDriver driver) {
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

	public void enterUserName(String username) {
		common_operations.checkVisibilityOfElement(emailAddressOrNumberTextBox);
		common_operations.enterValueInTextBox(emailAddressOrNumberTextBox,username);
	}

	public void clickOnLogInButton() {	
		common_operations.checkVisibilityOfElement(logInButton);
		common_operations.clickOnElement(logInButton);
	}
	
	public void clickOnLanguageButton() {
		common_operations.checkVisibilityOfElement(englishLanguageButton);
		common_operations.clickOnElement(englishLanguageButton);
	}

	public void enterPassword(String password) {
		common_operations.checkVisibilityOfElement(passTextBox);
		common_operations.enterValueInTextBox(passTextBox,password);
	}

	public void verifyForgottenAccountLink() {
		common_operations.checkVisibilityOfElement(forgotPasswordLink);
	}
	
	public void performLoginOperation(String userName,String password) {
		if(common_operations.checkVisibilityOfElement(englishLanguageButton)) {
			clickOnLanguageButton();
		}
		enterUserName(userName);
		enterPassword(password);
		verifyForgottenAccountLink();
		clickOnLogInButton();		
	}


}
