package eleks.com.pages_tests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import eleks.com.util.ConfigProperties;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Issue;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Class: Main Page")
@Description("Test Cases for test functionals on Main Page")
public class MainPage_Test extends BasePage_Test {

	/*
	 * @BeforeMethod public void init(){
	 * webDriver.get(ConfigProperties.getProperties("url")); if
	 * (mainPage.isLogoutPresent()){ mainPage.logout(); } }
	 */

	@Title("Test Case: isMainPageOpened")
	@Description("TC verify is Main Page opened")
	@Severity(SeverityLevel.BLOCKER)
	@Step
	@TestCaseId("ID-001")
	@Issue("ITSPP-13258")
	@Parameters({ "testGroup" })
	@Test(groups = { "normal" })
	public void isMainPageOpened_test() {
		mainPage.openMainPage();
		log("Opening main page");
		Assert.assertTrue(mainPage.isMainPageOpened());
		log("Verifing is Main page opened");
	}

	@Title("Test Case: isMainPageOpened")
	@Description("TC verify is Main Page opened")
	@Severity(SeverityLevel.BLOCKER)
	@Step
	@TestCaseId("ID-002")
	@Issue("ITSPP-13258")
	@Parameters({ "testGroup" })
	@Test(groups = { "critical" })
	public void isMainPageOpened_test2() {
		mainPage.openMainPage();
		log("Opening main page");
		Assert.assertTrue(mainPage.isMainPageOpened());
		log("Verifing is Main page opened");
	}

	@Title("Test Case: positive loginAs")
	@Description("TC verify is user logged with correct credentials")
	@Severity(SeverityLevel.CRITICAL)
	@Step
	@TestCaseId("ID-001")
	@Issue("ITSPP-13258")
	@Test
	public void positive_loginAs_test() throws ClassNotFoundException,
			SQLException {
		mainPage.openMainPage();
		log("Opening main page");
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		log("Login with correct credentials");
		Assert.assertTrue(mailBoxPage.isMailBoxPageOpened());
		log("Verifing is MailBox page opened");
		mailBoxPage.logout();
		log("Logout");
	}

	@Title("Test Case: negative loginAs")
	@Description("TC verify is user logged with incorrect credentials")
	@Severity(SeverityLevel.CRITICAL)
	@Step
	@Test
	public void negative_loginAs_test() throws ClassNotFoundException,
			SQLException {
		mainPage.openMainPage();
		log("Opening main page");
		mailBoxPage = mainPage.loginAs("gdgdg", "dgdfgd");
		log("Login with incorrect credentials");
		Assert.assertTrue(mailBoxPage.isMailBoxPageOpened());
	}

}
