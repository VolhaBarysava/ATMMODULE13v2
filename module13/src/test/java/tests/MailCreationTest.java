package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MailCreationTest extends TestBase {
	
	@Test(description = "Login to mail.ru", groups={"test_1"})
	public void mailRuLoginTest() {
		Assert.assertTrue(emailService.login(firstUser, EXPECTED_FIRST_ACCOUNT));
	}
	
	@Test(description = "Mail creation", dependsOnMethods = { "mailRuLoginTest" }, groups={"test_1"})
	public void mailRuMailCreationTest(){
		emailService.createEmail(email);	
		Assert.assertTrue(emailService.checkEmailInDraftFolder(email));
	}

}
