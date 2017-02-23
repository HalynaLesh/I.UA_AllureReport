package eleks.com.pages_tests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;


@Title("Class: MailBox Page")
@Description("Test Cases for test functionals on MailBox Page")
public class MailBoxPage_Test extends BasePage_Test {


	@Title("Test Case: goToCreateEmailPage")
	@Description("TC verify is user successful navigate to Create Email page")
	@Severity(SeverityLevel.NORMAL)
	@Step
	@Test(groups = { "minor" })
	public void goToCreateEmailPage_test() throws SQLException, ClassNotFoundException {
		mainPage.openMainPage();
		log("Opening main page");
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		log("Login");
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		log("Navigate to Create Email page");
		Assert.assertTrue(createMailPage.isCreateMailPageOpened());
		log("Verifing is Create Email page opened");
		mailBoxPage = createMailPage.navigateToMailBoxPage();
		mailBoxPage.logout();
		log("Logout");

	}

	@Title("Test Case: logout")
	@Description("TC verify is user successful logout")
	@Severity(SeverityLevel.NORMAL)
	@Step
	@Test(groups = { "critical" })
	public void logout_test() throws SQLException, ClassNotFoundException {
		mainPage.openMainPage();
		log("Opening main page");
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		log("Login");
		mainPage = mailBoxPage.logout();
		log("Logout");
		Assert.assertTrue(mainPage.isMainPageOpened());
		log("Verifing is User logout");
	}

}
