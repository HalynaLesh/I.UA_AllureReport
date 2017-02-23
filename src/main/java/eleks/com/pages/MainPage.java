package eleks.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eleks.com.util.ConfigProperties;


public class MainPage extends BasePage {

	public MainPage(WebDriver webDriver) {
		super(webDriver);
	}

	@FindBy(xpath = "//p[2]/input")
	private WebElement login_username;

	@FindBy(xpath = "//input[@name='pass']")
	private WebElement login_password;

	@FindBy(xpath = "//form/p/input")
	private WebElement submitLogin;
	
	@FindBy(xpath = "//a[@class='mbox_16']/descendant::i")
	private WebElement navigateMailBox;
	
	@FindBy(xpath = "//img[@title='I.UA']")
	private WebElement iua_icon;
	
	@FindBy(xpath="//a[starts-with(@href, 'http://i.ua/logout.php')]")
	private WebElement logout;

	public void openMainPage() {
		webDriver.get(ConfigProperties.getProperties("url"));
	}

	public boolean isMainPageOpened() {
		if (isElementPresent(login_username)
				&& isElementPresent(login_password)
				&& isElementPresent(submitLogin)) {
			return true;
		} else {
			return false;
		}
	}

	public MailBoxPage loginAs(String username, String pass) {
		login_username.click();
		type(login_username, username);
		type(login_password, pass);
		submitLogin.click();
		if (webDriver instanceof InternetExplorerDriver){
			navigateMailBox.click();
		}
		return PageFactory.initElements(webDriver, MailBoxPage.class);
	}
	
	public MainPage returnToMainPage(){
		iua_icon.click();
		return this;
	}
	
	public boolean isLogoutPresent() {
		if (isElementPresent(logout)){
			return true;
		}
		else {
			return false;
		}
	}
	
	public MainPage logout(){
		logout.click();
		return this;
	}
	

}
