package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MailDeleteTest extends TestBase {
	int indexOfemail = 0;

	@Test(description = "Mail delete", dependsOnGroups = { "test_2" }, groups = { "test_3" })
	public void mailRuMailDeleteTest() {

		emailService.login(secondUser, EXPECTED_SECOND_ACCOUNT);
		String subjectDeleteIncomingMail = emailService
				.getIncomingEmailSubject(indexOfemail);

		emailService.deleteIncomingMail(indexOfemail);
		Assert.assertTrue(emailService
				.checkSubjectlInBasket(subjectDeleteIncomingMail));
	}

	@Test(description = "Mail drag and drop from Basket to DRAFT", dependsOnGroups = { "test_2" }, dependsOnMethods = { "mailRuMailDeleteTest" }, groups = { "test_3" })
	public void dragNDropMailTest() {
		String subjectOfDeletedMail = emailService
				.getDeletedMailSubject(indexOfemail);

		emailService.moveEmailFromBasketToDraft(indexOfemail);
		Assert.assertTrue(emailService
				.checkEmailInDraftFolderBySubject(subjectOfDeletedMail));
	}

}
