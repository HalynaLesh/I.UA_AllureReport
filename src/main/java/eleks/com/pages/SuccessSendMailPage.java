package eleks.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessSendMailPage extends MainPage{

	public SuccessSendMailPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	@FindBy(xpath = "//descendant::div[@class='block_confirmation']/descendant::div[@class='content clear']")
	private WebElement success_message;

	@FindBy(xpath = "//descendant::ul[@class='list_line']/descendant::a[1]")
	private WebElement newMail_link;

	@FindBy(xpath = "//descendant::ul[@class='list_line']/descendant::a[2]")
	private WebElement checkMailBox_link;

	@FindBy(xpath = "//descendant::ul[@class='list_underlined']/descendant::li[2]")
	private WebElement goToSentEmails;
	
	@FindBy(xpath =".//*[@id='newMail']/a")
	private WebElement goToMailBox_icon;

	public boolean isSuccessSendMailPageOpened() {
		if (isElementPresent(success_message) && isElementPresent(newMail_link)
				&& isElementPresent(checkMailBox_link)) {
			return true;
		} else {
			return false;
		}
	}
	
	public MailBoxPage goToMaiBoxPage() {
		goToMailBox_icon.click();
		return PageFactory.initElements(webDriver, MailBoxPage.class);
	}
	
	public SentEmailsPage goToSentEmailsPage(){
		goToSentEmails.click();
		return PageFactory.initElements(webDriver, SentEmailsPage.class);
		
	}

}
