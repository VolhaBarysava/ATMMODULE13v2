package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import reporting.MyLogger;
import utils.Highlighter;

public class IncomingPage extends BasePage {

	public IncomingPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[contains(@href,'https://e.mail.ru/thread/')]")
	private List<WebElement> incomingMails;

	@FindBy(xpath = "//div[@class='b-datalist__item__body']//*[@class='b-checkbox__box']")
	private List<WebElement> checkBoxIncomingMails;

	@FindBy(xpath = "//*[@id='b-toolbar__right']//div[@data-name='remove']")
	private WebElement deleteBtn;

	public String getIncomingMailSubject(int index) {
		return incomingMails.get(index).getAttribute("data-subject");
	}

	public IncomingPage deleteIncomingMail(int index) {
		MyLogger.debug("Click deleteBtn to delete incoming mail with index: " + index);
		WebElement firstIncomingMailCheckBox = checkBoxIncomingMails.get(index);
		Highlighter.highlightElement(firstIncomingMailCheckBox);
		firstIncomingMailCheckBox.click();
		Highlighter.unHighlightElement(firstIncomingMailCheckBox);
		deleteBtn.click();
		return new IncomingPage(driver);
	}
}
