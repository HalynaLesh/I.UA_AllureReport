package eleks.com.pages_tests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Issue;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Class: Success Sent Mail Page")
@Description("Test Cases for test functionals on SuccessSendMailPage Page")
public class SuccessSendMailPage_Test extends BasePage_Test {

	@Title("Test Case: goToMailBoxPage")
	@Description("TC verify is user successful navigate to MailBox Page")
	@Severity(SeverityLevel.CRITICAL)
	@Step
	@TestCaseId("ID-001")
	@Issue("ITSPP-13258")
	@Test(groups = {"minor"})
	public void goToMailBoxPage_test() throws ClassNotFoundException, SQLException {
		mainPage.openMainPage();
		log("Opening main page");
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		log("Login");
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		log("Navigate to Create Email page");
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		log("Create Email");
		mailBoxPage = successSendMailPage.goToMaiBoxPage();
		log("Navigate to MailBox Page");
		Assert.assertTrue(mailBoxPage.isMailBoxPageOpened());
		log("Verifing is MailBox page opened");
		mailBoxPage.logout();
		log("Logout");
	}

}
