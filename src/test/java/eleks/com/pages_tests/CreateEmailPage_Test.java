package eleks.com.pages_tests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Issue;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;


@Title("Class: Create Email Page")
@Description("Test Cases for test functionals on Create Email Page")
public class CreateEmailPage_Test extends BasePage_Test {

	@Title("Test Case: createEmail")
	@Description("TC verify is user successful create Email")
	@Severity(SeverityLevel.CRITICAL)
	@Step
	@TestCaseId("ID-001")
	@Issue("ITSPP-13258")
	@Test
	public void createEmail_test() throws ClassNotFoundException, SQLException {
		mainPage.openMainPage();
		log("Opening main page");
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		log("Login");
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		log("Navigate to Create Email page");
		Assert.assertTrue(createMailPage.isCreateMailPageOpened());
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		log("Create Email");
		Assert.assertTrue(successSendMailPage.isSuccessSendMailPageOpened());
		log("Vrifing is Email created - is success massege display");
		mailBoxPage = successSendMailPage.goToMaiBoxPage();
		log("Verifing is MailBox page opened");
		mailBoxPage.logout();
		log("Logout");
	}

}
