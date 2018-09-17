package services;

import java.io.IOException;

import com.epam.reportportal.message.ReportPortalMessage;

import pages.AccountPage;
import pages.BasePage;
import pages.BasketPage;
import pages.CreateEmailPage;
import pages.DraftPage;
import pages.HomePage;
import pages.IncomingPage;
import reporting.MyLogger;
import utils.ScreenShooter;
import utils.WebDriverSingleton;
import bo.Email;
import bo.User;

public class EmailService {

	public boolean login(User user, String text) {
		MyLogger.info("------ Login to mail.ru by user with login: "
				+ user.getLogin() + " and password: " + user.getPassword()
				+ " -------");
		HomePage homePage = new HomePage(
				WebDriverSingleton.getWebDriverInstance());
		homePage.setUserName(user);
		homePage.setUserPassword(user);
		AccountPage accountPage = homePage.clickSubmitBtn();
		MyLogger.info("------ Check if the user is logged ------");
		boolean succeed = accountPage.isTextPresentOnPage(text);
		if (succeed == true) {
			MyLogger.info("------ " + user + " is logged -----");
		} else {
			MyLogger.error("User is not logged");
			ReportPortalMessage message;
			try {
				message = new ReportPortalMessage(ScreenShooter.takeScreenshot(), "User is not logged");
				MyLogger.debug(message);
			} catch (IOException e) {
				System.out.print("Cannot send screenshot to report portal");
				e.printStackTrace();
			}
		}
// 		This logging block can be replaced with MyLogger.info("User " + (succeed ? "logged successfully" : "failed to log in" ));
		return succeed;
	}

	public void createEmail(Email email) {
		MyLogger.info("------ Email creation is started ------");
// 		It's a good practice to add test data to logs. In this very case smth like this:
		MyLogger.info("Create an email: " + email.toString() + " and save it as a draft");
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		CreateEmailPage createEmailPage = accountPage.clickMailCreationBtn();
		createEmailPage.fillMailAddress(email.getRecipient());
		createEmailPage.fillMailSubject(email.getSubject());
		createEmailPage.fillMailBody(email.getTextBody());
		MyLogger.info("------ Save email as draft -----");
		createEmailPage.clickSaveDraftBtn();
	}

	public boolean checkEmailInDraftFolder(Email email) {
		MyLogger.info("------ Check if email exists in Draft folder... -----");
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		DraftPage draftPage = accountPage.clickMailDraftMenuLink();
		boolean succeed = draftPage.isTextPresentOnPage(email.getSubject());
		if (succeed == true) {
			MyLogger.info("------ Email exists in DRAFT folder -------");
		} else {
			MyLogger.info("------ Email does not exist in DRAFT folder ------");
		}
// 		please use "?:" operatot instead if-else
		return succeed;
	}

	public String getIncomingEmailSubject(int index) {
// 		Please add a logger with a general method description: e.g."Get incoming email subject"
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		IncomingPage incomingPage = accountPage.clickMailIncomingMenuLink();
		String actualSubject = incomingPage.getIncomingMailSubject(index);
// 		Add logger of retrved data
		return actualSubject;
	}

	public void openDraftEmail(int index) {
		MyLogger.info("------ Open draft email... ------"); 
// 		+.. " with index " + index
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		DraftPage draftPage = accountPage.clickMailDraftMenuLink();
		draftPage.openDraftMail(index);
	}

	public boolean sendEmail(Email email) {
		MyLogger.info("------ Sent email: " + email +" ------");
// 		please add toString() to email in logger
		CreateEmailPage createEmailPage = new CreateEmailPage(
				WebDriverSingleton.getWebDriverInstance());
		AccountPage accountPage = createEmailPage.clickMailSendBtn();
		boolean succeed = accountPage.isElementPresent(accountPage.mailSentTitle);
		if (succeed == true) {
			MyLogger.info("------ Email: " + email + "was sent -----");
		} else {
			MyLogger.error("Email was not sent");
			ReportPortalMessage message;
			try {
				message = new ReportPortalMessage(ScreenShooter.takeScreenshot(), "Email was not sent");
				MyLogger.debug(message);
			} catch (IOException e) {
				System.out.print("Cannot send screenshot to report portal");
				e.printStackTrace();
			}
		}
// 		"?:" operator can be used
		return succeed;
	}

	public void refreshPage() {
		Logger.info ("Refresh currently opened page")
		BasePage basePage = new CreateEmailPage(
				WebDriverSingleton.getWebDriverInstance());
		basePage.refresh();
	}

	public boolean checkEmailInSentFolder(Email email) {
		MyLogger.info("Check if email: " + email + " is in sent folder...");
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		accountPage.clickMailSentMenuLink();
		boolean succeed =  accountPage.isTextPresentOnPage(email.getSubject());
		if (succeed == true) {
			MyLogger.info("------ Email exists in SENT folder -----");
		} else {
			MyLogger.error("Email does NOT exist in SENT folder");
			ReportPortalMessage message;
			try {
				message = new ReportPortalMessage(ScreenShooter.takeScreenshot(), "Email does NOT exist in SENT folder");
				MyLogger.debug(message);
			} catch (IOException e) {
				System.out.print("Cannot send screenshot to report portal");
				e.printStackTrace();
			}
		}
// 		"?:" can be used
		return succeed;
	}

	public boolean checkEmailInIncomingFolder(Email email) {
		MyLogger.info("------ Check that email: " + email + " exists in INCOMING folder ------");
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		accountPage.clickMailIncomingMenuLink();
		boolean succeed = accountPage.isTextPresentOnPage(email.getSubject());
		if (succeed == true) {
			MyLogger.info("------ Email exists in INCOMING folder -----");
		} else {
			MyLogger.error("Email does NOT exist in INCOMING folder");
			ReportPortalMessage message;
			try {
				message = new ReportPortalMessage(ScreenShooter.takeScreenshot(), "Email does NOT exist in INCOMING folder");
				MyLogger.debug(message);
			} catch (IOException e) {
				System.out.print("Cannot send screenshot to report portal");
				e.printStackTrace();
			}
		}
		// 		"?:" can be used
		return succeed;
	}

	public void deleteIncomingMail(int indexOfemail) {
		MyLogger.info("Delete email...");
		IncomingPage incomingPage = new IncomingPage(
				WebDriverSingleton.getWebDriverInstance());
		incomingPage.deleteIncomingMail(indexOfemail);
	}

	public boolean checkEmailInIncomingFolderBySubject(
			String subjectDeleteIncomingMail) {
		IncomingPage incomingPage = new IncomingPage(
				WebDriverSingleton.getWebDriverInstance());
		return incomingPage.isTextPresentOnPage(subjectDeleteIncomingMail);
	}

	public boolean checkSubjectlInBasket(String subjectDeleteIncomingMail) {
		MyLogger.info("------ Check if deleted email with subject: " + subjectDeleteIncomingMail + " exists in Basket ------");
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		refreshPage();
// 		refreshPage is a separate service method. You can call from a test, so it's better to remove it from this service method and all further method when it's used
		BasketPage basketPage = accountPage.clickBasketMenuLink();
		boolean succeed = basketPage.isTextPresentOnPage(subjectDeleteIncomingMail);
		if (succeed == true) {
			MyLogger.info("------ Email with subject " + subjectDeleteIncomingMail + " exists in Basket -----");
		} else {
			MyLogger.error("Email does NOT exist in Basket");
			ReportPortalMessage message;
			try {
				message = new ReportPortalMessage(ScreenShooter.takeScreenshot(), "Email does NOT exist in Basket");
				MyLogger.debug(message);
			} catch (IOException e) {
				System.out.print("Cannot send screenshot to report portal");
				e.printStackTrace();
			}
		}
		// 		"?:" can be used
		return succeed;
	}

	public String getDeletedMailSubject(int index) {
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		refreshPage();
		BasketPage basketPage = accountPage.clickBasketMenuLink();
		MyLogger.info("------ Email with subject " + basketPage.getDeleteMailSubject(index) + " will be deleted ------");
// 		It seems that this method doesn't remove, but gets a subject of the already removed email
		return basketPage.getDeleteMailSubject(index);
	}

	public void moveEmailFromBasketToDraft(int index) {
		MyLogger.info("------ Move email from Basket to Draft folder via drag&drop ------");
		BasketPage basketPage = new BasketPage(
				WebDriverSingleton.getWebDriverInstance());
		refreshPage();
		basketPage.dragNDropMailFromBasketToDraft(index);
	}

	public boolean checkEmailInDraftFolderBySubject(String subjectOfDeletedMail) {
		MyLogger.info("------ Check that email with subject: " + subjectOfDeletedMail + " exists in DRAFT folder ------");
		DraftPage draftPage = new DraftPage(
				WebDriverSingleton.getWebDriverInstance());
		boolean succeed =  draftPage.isTextPresentOnPage(subjectOfDeletedMail);
		if (succeed == true) {
			MyLogger.info("------ Moved Email with subject " + subjectOfDeletedMail + " exists in DRAFT -----");
		} else {
			MyLogger.error("Moved Email does NOT exist in DRAFT");
			ReportPortalMessage message;
			try {
				message = new ReportPortalMessage(ScreenShooter.takeScreenshot(), "Moved Email does NOT exist in DRAFT");
				MyLogger.debug(message);
			} catch (IOException e) {
				System.out.print("Cannot send screenshot to report portal");
				e.printStackTrace();
			}
		}
		// 		"?:" can be used
		return succeed;
	}

	public void logOut() {
		MyLogger.info("------ LogOut by user ------");
		new AccountPage(WebDriverSingleton.getWebDriverInstance())
				.clickLogOut();
	}
}
