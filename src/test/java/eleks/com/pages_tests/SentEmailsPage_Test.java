package eleks.com.pages_tests;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Issue;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Class: Sent Email Page")
@Description("Test Cases for test functionals on Sent Email Page")
public class SentEmailsPage_Test extends BasePage_Test {
	
	@BeforeMethod
	public void checkLogout(){
		if (mainPage.isLogoutPresent()){
			mainPage.logout();
		}
	}

	@Title("Test Case: positive_verifyEmailSubject")
	@Description("TC verify is correct Email subject")
	@Severity(SeverityLevel.CRITICAL)
	@Step
	@TestCaseId("ID-001")
	@Issue("ITSPP-13258")
	@Test(groups = { "normal" })
	public void positive_verifySubject_test() throws ClassNotFoundException,
			SQLException {
		mainPage.openMainPage();
		log("Opening main page");
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		log("Login");
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		log("Navigate to Create Email page");
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		log("Create Email");
		sentEmailsPage = successSendMailPage.goToSentEmailsPage();
		Assert.assertTrue(sentEmailsPage.isCorrectSubject(email.getSubject()));
		log("Verifing subject");
		mailBoxPage = sentEmailsPage.navigateToMaiBoxPage();
		mailBoxPage.logout();
		log("Logout");
	}

	@Title("Test Case: negative_verifyEmailSubject")
	@Description("TC verify is correct Email subject")
	@Severity(SeverityLevel.CRITICAL)
	@Step
	@TestCaseId("ID-001")
	@Issue("ITSPP-13258")
	@Test(dependsOnMethods = { "positive_verifySubject_test" }, groups = {"normal"})
	public void negative_verifySubject_test() throws ClassNotFoundException,
			SQLException {
		mainPage.openMainPage();
		log("Opening main page");
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		log("Login");
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		log("Navigate to Create Email page");
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		log("Create Email");
		sentEmailsPage = successSendMailPage.goToSentEmailsPage();
		Assert.assertFalse(sentEmailsPage.isCorrectSubject("dgdfg"));
		System.out.println("tra ta ta");
		log("Verifing Email subject");
		mailBoxPage = sentEmailsPage.navigateToMaiBoxPage();
		mailBoxPage.logout();
		log("Logout");
	}

	@Title("Test Case: positive_verifyBody")
	@Description("TC verify is correct Email Body")
	@Severity(SeverityLevel.CRITICAL)
	@Step
	@TestCaseId("ID-001")
	@Issue("ITSPP-13258")
	@Test(dependsOnMethods = { "positive_verifySubject_test" }, groups = {"normal"})
	public void positive_verifyBody_test() throws ClassNotFoundException,
			SQLException {
		mainPage.openMainPage();
		log("Opening main page");
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		log("Login");
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		log("Navigate to Create Email page");
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		log("Create Email");
		sentEmailsPage = successSendMailPage.goToSentEmailsPage();
		sentEmailsPage.openSentEmail(email.getSubject());
		log("Opening Email");
		Assert.assertTrue(sentEmailsPage.isCorrectBodyOnEmail(email.getBody()));
		log("Verifing Email body field");
		Assert.assertTrue(sentEmailsPage.isCorrectToOnEmail(email.getTo()));
		log("Verifing Email To field");
		Assert.assertTrue(sentEmailsPage.isCorrectSubjectOnEmail(email.getSubject()));
		log("Verifing Email Subject field");
		softAssert.assertAll();
		mailBoxPage = sentEmailsPage.navigateToMaiBoxPage();
		mailBoxPage.logout();
		log("Logout");
	}

	@Title("Test Case: negative_verifyBody")
	@Description("TC verify is correct Email Body")
	@Severity(SeverityLevel.CRITICAL)
	@Step
	@Test (groups = {"normal"})
	public void negative_verifyBody_test() throws ClassNotFoundException,
			SQLException {
		mainPage.openMainPage();
		log("Opening main page");
		mailBoxPage = mainPage.loginAs(getUserNameSQL(), getUserPassSQL());
		log("Login");
		createMailPage = mailBoxPage.navigateToCreateEmailPage();
		log("Navigate to Create Email page");
		successSendMailPage = createMailPage.createEmail(email.getTo(),
				email.getSubject(), email.getBody());
		log("Create Email");
		sentEmailsPage = successSendMailPage.goToSentEmailsPage();
		sentEmailsPage.openSentEmail(email.getSubject());
		log("Opening Email");
		Assert.assertFalse(sentEmailsPage.isCorrectBodyOnEmail(email.getBody()));
		log("Verifing Email body field");
		Assert.assertFalse(sentEmailsPage.isCorrectToOnEmail(email.getTo()));
		log("Verifing Email To field");
		Assert.assertFalse(sentEmailsPage.isCorrectSubjectOnEmail(email.getSubject()));
		log("Verifing Email Subject field");
		softAssert.assertAll();
		log("Verifing Email body");
		mailBoxPage = sentEmailsPage.navigateToMaiBoxPage();
		mailBoxPage.logout();
		log("Logout");
	}

}
