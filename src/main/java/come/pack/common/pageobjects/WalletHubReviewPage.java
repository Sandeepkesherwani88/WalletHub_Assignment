package come.pack.common.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.pack.common.operations.CommonOprations;

public class WalletHubReviewPage {

	protected WebDriver driver;
	CommonOprations common_operations;
	Logger logger=LogManager.getLogger(FacebookHomePage.class);
	
	int numberOfStartsRequired=4;
	private String valueToBeSelectedFromPolicyDropdown="Health Insurance";
	private String pageTitle = "test insurance company metatitle test";
	private String reviewComment="Test ";
	private String reviewStarts ="((//*[text()='Most Recent'])/preceding::review-star[1]//child::*/descendant::button)";
	private By getHighlightedStarsCount=By.xpath("(//*[text()='Most Recent'])/preceding::review-star[1]//child::*/descendant::*[@fill='none']");
	private By findCheapestInsuranceLabel = By.xpath("//*[text()='Test Insurance Company Reviews']");	
	private By reviewStartsSelectedOnFinalPage = By.xpath("//span[text()='Select...']/preceding::review-star[1]//child::*/descendant::*[@fill='none']");
	private By policyDropdown = By.xpath("//span[text()='Select...']");
	private By policyValueToBeselected = By.xpath("//li[text()='"+valueToBeSelectedFromPolicyDropdown+"']");	
	private By reviewTextArea = By.xpath("//textarea[@placeholder='Write your review...']");
	private By submitButton = By.xpath("//div[text()='Submit']");
	private By firstPostedReview = By.xpath("//span[text()='Most Recent']/following::article[@itemprop='review'][1]");
	private By postedReviewStarts = By.xpath("//span[text()='Most Recent']/following::article[@itemprop='review'][1]/child::*/descendant::*[@fill='none']");
	private By postedReviewMessage = By.xpath("//span[text()='Most Recent']/following::div[@itemprop='description'][1]");
	
	

	public WalletHubReviewPage(WebDriver driver) {
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

	public void scrollTillReviewStarts() {
		common_operations.scrollIntoView(findCheapestInsuranceLabel);
	}

	public void verifyAllFiveStartsToBePresent() {	
		scrollTillReviewStarts();
		for(int i=1;i<=5;i++) {		
			String xpaths=reviewStarts+"["+i+"]/preceding-sibling::*[1]";
			By xpaths_new = By.xpath(xpaths);
			common_operations.performMouseHover(xpaths_new);
			int count=common_operations.getSizeOfFindElements(getHighlightedStarsCount);
			if(count==i) {
				logger.info("The Stars are highlighting with colors");
			}else {
				logger.error("The Stars are not highlighting with colors");
			}

		}

	}
	
	public void verifyAllselectedOnFinalPage() {
		int count=common_operations.getSizeOfFindElements(reviewStartsSelectedOnFinalPage);
		if(count==numberOfStartsRequired) {
			logger.info("The Stars are highlighting with colors and correct on final page");
			}else {
				logger.error("The Stars are highlighting with colors and not correct on final page");
			}

		}
	
	public void enter200ReviewComments() {
		common_operations.clickOnElement(reviewTextArea);
		int numberLimit=(int)200/reviewComment.length();
		for (int i=0;i<=numberLimit+1;i++) {			
			common_operations.enterValueInTextBox(reviewTextArea, reviewComment);
		}
		common_operations.clickOnElement(submitButton);
	}
	
	public void selectPolicyValue() {
		common_operations.clickOnElement(policyDropdown);
		common_operations.clickOnElement(policyValueToBeselected);
	}


	public void clickingOnRequestedNumberOfStars() {
		verifyAllFiveStartsToBePresent();
		String xpaths="(//*[text()='Most Recent'])/preceding::review-star[1]/child::*/descendant::button["+numberOfStartsRequired+"]/preceding-sibling::*[1]";
		By xpaths_new = By.xpath(xpaths);
		common_operations.performMouseHover(xpaths_new);
		int count=common_operations.getSizeOfFindElements(getHighlightedStarsCount);
		common_operations.performMouseHoverAndClick(xpaths_new);
		if(count==numberOfStartsRequired) {
			logger.info("The Stars are highlighting with colors and clicked");
		}else {
			logger.error("The Stars are not highlighting with colors and not clicked");
		}			
	}

	public void verifyPostedReviewComments() throws Exception {
		common_operations.checkVisibilityOfElement(firstPostedReview);
		int countOfSelectedStarts=common_operations.getSizeOfFindElements(postedReviewStarts);
		if(countOfSelectedStarts==numberOfStartsRequired) {
			System.out.println("The number of starts are posted correctly ");
			if(common_operations.getText(postedReviewMessage).contains(reviewComment)) {
				common_operations.takeSnapShot(WalletHubReviewPage.class.getSimpleName());
				logger.info("The review message are posted correctly ");
			}else {
				logger.error("The review message are not posted correctly ");
			}
		}else {
			logger.error("The number of starts are not posted correctly ");
		}
		
	}
	


}
