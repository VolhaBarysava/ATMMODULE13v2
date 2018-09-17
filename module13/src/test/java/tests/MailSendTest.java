package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MailSendTest extends TestBase {
	private int index = 0;
	

	@Test(description = "Mail sending", dependsOnGroups = { "test_1" }, groups = { "test_2" })
	public void mailRuMailSendingTest() {

		emailService.login(firstUser, MailCreationTest.EXPECTED_FIRST_ACCOUNT);
				
		emailService.openDraftEmail(index);
		emailService.sendEmail(email);
		
		emailService.refreshPage();
		
		Assert.assertFalse(emailService.checkEmailInDraftFolder(email));
		Assert.assertTrue(emailService.checkEmailInSentFolder(email));
		
		emailService.logOut();

		emailService.login(secondUser, EXPECTED_SECOND_ACCOUNT);
		Assert.assertTrue(emailService.checkEmailInIncomingFolder(email));

	}

}
