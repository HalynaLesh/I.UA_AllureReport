package eleks.com.pages_tests;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import ru.yandex.qatools.allure.annotations.Step;
import eleks.com.EmailBuilder.Email;
import eleks.com.EmailBuilder.EmailBuilder;
import eleks.com.pages.CreateMailPage;
import eleks.com.pages.MailBoxPage;
import eleks.com.pages.MainPage;
import eleks.com.pages.SentEmailsPage;
import eleks.com.pages.SuccessSendMailPage;
import eleks.com.sql.ConnectMySQL;
import eleks.com.util.Browser;
import eleks.com.util.ConfigProperties;
import eleks.com.webdriver.WebDriverFactory;

public class BasePage_Test {

	Email email = new EmailBuilder().buildTo("to@i.ua")
			.buildSubject("subject_tra_ta_ta").buildBody("body_tra_ta_ta")
			.build();

	ConnectMySQL mySQL = new ConnectMySQL();

	public Browser browser1 = new Browser();
	
	WebDriver webDriver;

	SoftAssert softAssert = new SoftAssert();

	MainPage mainPage;
	MailBoxPage mailBoxPage;
	CreateMailPage createMailPage;
	SuccessSendMailPage successSendMailPage;
	SentEmailsPage sentEmailsPage;

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void setUp(String browser) throws Exception {
		webDriver = WebDriverFactory.getInstance(browser);
		browser1.setName(browser);
		mainPage = new MainPage(webDriver);
		webDriver.get(ConfigProperties.getProperties("url"));
		/*if (mainPage.isLogoutPresent()){
			mainPage.logout();
		}*/
	}
	
	@AfterTest
	public void writeDriver() throws IOException{
		FileWriter fw = new FileWriter(
				"src/main/resources/environment.properties", true);
		fw.write("Browser: " + browser1.getName() + "\n");
		fw.close();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		WebDriverFactory.killDriverInstance();
	}

	@Step
	public void log(String log_text) {

	}

	public String getUserNameSQL() throws SQLException, ClassNotFoundException {
		String login = null;
		ResultSet query = mySQL.executionQuery();
		while (query.next()) {
			login = query.getString("login");
		}
		return login;
	}

	public String getUserPassSQL() throws SQLException, ClassNotFoundException {
		String password = null;
		ResultSet query = mySQL.executionQuery();
		while (query.next()) {
			password = query.getString("password");
		}
		return password;
	}

}
