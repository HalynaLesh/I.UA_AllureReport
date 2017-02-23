package eleks.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateMailPage extends BasePage {

	public CreateMailPage(WebDriver webDriver) {
		super(webDriver);
	}

	@FindBy(xpath = ".//*[@id='newMail']/a")
	private WebElement goTomailBox_icon;

	@FindBy(xpath = "//input[@name='send']")
	private WebElement send_button;

	@FindBy(xpath = ".//*[@id='to']")
	private WebElement to_field;

	@FindBy(xpath = "//input[@name='subject']")
	private WebElement subject_field;

	@FindBy(xpath = ".//*[@id='text']")
	private WebElement body_field;

	public boolean isCreateMailPageOpened() {
		if (isElementPresent(send_button) && isElementPresent(to_field)
				&& isElementPresent(subject_field)) {
			return true;
		} else {
			return false;
		}
	}

	public MailBoxPage navigateToMailBoxPage() {
		goTomailBox_icon.click();
		return PageFactory.initElements(webDriver, MailBoxPage.class);
	}

	public SuccessSendMailPage createEmail(String to, String subject,
			String body) {
		type(to_field, to);
		type(subject_field, subject);
		type(body_field, body);
		send_button.click();
		return PageFactory.initElements(webDriver, SuccessSendMailPage.class);
	}	
}
