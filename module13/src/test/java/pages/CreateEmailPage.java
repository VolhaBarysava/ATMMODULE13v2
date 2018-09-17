package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import reporting.MyLogger;
import utils.Highlighter;

public class CreateEmailPage extends BasePage{
	
	public CreateEmailPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "textarea[data-original-name = 'To']")
	private WebElement mailToAddress;
	
	@FindBy(name = "Subject")
	private WebElement mailSubject;

	@FindBy(xpath = "//iframe[starts-with(@id,'toolkit')]")
	private WebElement mailBody;
	
	@FindBy(id = "tinymce")
	private WebElement textBody;
	
	@FindBy(xpath = "//*[@id='b-toolbar__right']//*[@data-name='saveDraft']")
	private WebElement saveAsDraftBtn;
	
	@FindBy(xpath = "//*[@id='b-toolbar__right']//*[@data-name='send']")
	private WebElement sendBtn;
	
	@FindBy(xpath = "//*[@id='b-toolbar__right']//*[@data-mnemo='saveStatus']")
	private WebElement saveStatus;
	
	@FindBy(xpath = "//*[@id='compose_to']")
	private WebElement actualMailToAddress;
	
	
	public CreateEmailPage fillMailAddress(String mailAddress){
		MyLogger.debug("Fill Recipient email address: " + mailAddress + "  to mailAddress field");
		waitForElementVisible(mailToAddress);
		Highlighter.highlightElement(mailToAddress);
		mailToAddress.clear();
		mailToAddress.sendKeys(mailAddress);
		Highlighter.unHighlightElement(mailToAddress);
		return new CreateEmailPage(driver);
	}
	
	public CreateEmailPage fillMailSubject(String subject){
		MyLogger.debug("Fill mail subject " + subject + " to mailSubject field");
		waitForElementVisible(mailSubject);
		Highlighter.highlightElement(mailSubject);
		mailSubject.clear();
		mailSubject.sendKeys(subject);
		Highlighter.unHighlightElement(mailSubject);
		return new CreateEmailPage(driver);
	}
	
	public CreateEmailPage fillMailBody(String body){
		MyLogger.debug("Fill mail body " + body + " to textBody field");
		driver.switchTo().frame(mailBody);
		textBody.clear();
		textBody.sendKeys(body);;
		driver.switchTo().defaultContent();	
		return new CreateEmailPage(driver);
	}
	
	public CreateEmailPage clickSaveDraftBtn(){
		MyLogger.debug("Click save as draft button (saveAsDraftBtn)");
		Highlighter.highlightElement(saveAsDraftBtn);
		saveAsDraftBtn.click();
		Highlighter.unHighlightElement(saveAsDraftBtn);
		waitForElementVisible(saveStatus);
		Highlighter.highlightElement(saveStatus);
		Highlighter.unHighlightElement(saveStatus);
		return new CreateEmailPage(driver); 
	}

	
	public AccountPage clickMailSendBtn(){
		MyLogger.debug("Click on sendBtn");
		waitForElementVisible(mailToAddress);
		sendBtn.click();
		return new AccountPage(driver);
	}
}
