package come.pack.common.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.pack.common.operations.*;

public class FacebookHomePage {

	private WebDriver driver;
	CommonOprations common_operations;
	Logger logger=LogManager.getLogger(FacebookHomePage.class);
	
	private String pageTitle = "Facebook";
	private By myProfile = By.xpath("//a[@role='link' and @href='/me/']/descendant::span[last()]");
	private By whatOnMindTextBox = By.xpath("//span[text()='Posts']/preceding::span[contains(text(),'on your mind')]");
	private By createPostLable = By.xpath("//span[text()='Create Post']");
	private By closeButton = By.xpath("//div[@aria-label='Close']");
	private By whatOnYourMind = By.xpath("//div[contains(@aria-label, 'your mind?')]");
	private By postButton = By.xpath("//span[text()='Post']");
	private By justNowTimerRepresenter = By.xpath("//*[@aria-label='1m']");
	private By actionForThisPostButton = By.xpath("(//*[@aria-label='Actions for this post'])[1]");
	private By enteredText = By.xpath("(//*[@aria-label='Actions for this post'])[1]/following::div[1]/descendant::div[last()]");
	private By moveToTrashButton = By.xpath("//span[text()='Move to trash']");
	private By moveToYourTrashlabel = By.xpath("//span[text()='Move to Your Trash?']");
	private By trashMessage = By.xpath("//span[text()='Items in your trash will be automatically deleted after 30 days. You can delete them earlier from your Trash by going to Activity Log in Settings.']");
	private By moveButton = By.xpath("(//span[text()='Items in your trash will be automatically deleted after 30 days. You can delete them earlier from your Trash by going to Activity Log in Settings.']/following::span[text()='Move'])[1]");
	private By cancelButton = By.xpath("(//span[text()='Items in your trash will be automatically deleted after 30 days. You can delete them earlier from your Trash by going to Activity Log in Settings.']/following::span[text()='Cancel'])[1]");

	
	public FacebookHomePage(WebDriver driver) {
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
	
	public void createPost(String postMessage) {
		common_operations.clickOnElement(myProfile);
		common_operations.clickOnElement(whatOnMindTextBox);
		if(common_operations.checkVisibilityOfElement(createPostLable)) {
			if(common_operations.checkVisibilityOfElement(closeButton)) {
				if(common_operations.checkVisibilityOfElement(whatOnYourMind)) {
					common_operations.clickOnElement(whatOnYourMind);
					common_operations.enterValueInTextBox(whatOnYourMind,postMessage);
					if(common_operations.checkVisibilityOfElement(postButton)) {
						common_operations.clickOnElement(postButton);
					}else {
						logger.error("The Post button is not visible for xpath "+postButton);
						
					}
				}else {
					logger.error("The Post button is not visible for xpath "+postButton);
					
				}
			}else {
				logger.error("The Close button is not visible for xpath "+closeButton);
				
			}
		}else {
			logger.error("The Create Post label is not visible for xpath "+createPostLable);
			
		}
	}
	
	
	public void verifyPost(String postMessage) throws Exception {
		if(common_operations.checkVisibilityOfElement(justNowTimerRepresenter)) {
			if(common_operations.checkVisibilityOfElement(enteredText)) {
				common_operations.scrollIntoView(whatOnMindTextBox);
					common_operations.takeSnapShot(FacebookHomePage.class.getSimpleName());
				String enteredTextValue=common_operations.getText(enteredText);
				if(enteredTextValue.equals(postMessage)) {
					logger.info("The Created Post has been verified");
				}else {
					logger.error("The Created Post is missing");
				}
			}else {
				logger.error("The Created Post is not visible for xpath "+enteredText);
			}
		}else {
			logger.error("The Created Post is not visible for xpath "+justNowTimerRepresenter);
		}
	}

	public void deletePost(String postMessage) {		
		common_operations.refreshPage();
		if(common_operations.checkVisibilityOfElement(actionForThisPostButton)) {
			common_operations.clickOnElement(actionForThisPostButton);
			if(common_operations.checkVisibilityOfElement(moveToTrashButton)) {
				common_operations.clickOnElement(moveToTrashButton);
				if(common_operations.checkVisibilityOfElement(moveToYourTrashlabel) 
						&& common_operations.checkVisibilityOfElement(moveButton) && common_operations.checkVisibilityOfElement(trashMessage) ) {
					common_operations.clickOnElement(moveButton);
					common_operations.refreshPage();//clicking on refresh button twice to check the post should not be visible
					common_operations.refreshPage();
					if(common_operations.checkElementAbsence(justNowTimerRepresenter)) {
						logger.info("The created post deleted successfully for  "+ justNowTimerRepresenter);
					}else {
						logger.error("The Created Post is visible after deleting for xpath "+enteredText);
						
					}
				}else {
					logger.error("The element is not visible for xpath  "+moveToYourTrashlabel+" "+trashMessage+" "+moveButton+" "+cancelButton);
				}
			}else {
				logger.error("The element is not visible for xpath  "+moveToTrashButton);
			}
		}else {
			logger.error("The element is not visible for xpath "+justNowTimerRepresenter);
		}
	}
	
	
}
