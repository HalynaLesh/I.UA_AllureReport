package eleks.com.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver webDriver;
	
	public BasePage(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver,this);
	}
		
	public boolean isElementPresent(WebElement webElement) {
		try {
			webElement.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	protected void type(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

}
