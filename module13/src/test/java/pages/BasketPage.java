package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import reporting.MyLogger;
import utils.Highlighter;

public class BasketPage extends AccountPage {
	
	public BasketPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[contains(@href,'https://e.mail.ru/thread/')]")
	private List <WebElement> mailsInBasket;
	
	public BasketPage dragNDropMailFromBasketToDraft(int index){
		WebElement element = mailsInBasket.get(index);
		Highlighter.highlightElement(element);
		WebElement target = mailDraftMenuLink;
		Highlighter.highlightElement(mailDraftMenuLink);
		Highlighter.unHighlightElement(mailDraftMenuLink);
		Highlighter.unHighlightElement(element);
		new Actions(driver).dragAndDrop(element,target).build().perform();
		MyLogger.debug("DragAndDrop was successeful");
		return this;
	}
	
	public String getDeleteMailSubject(int index) {
		WebElement deleteMail = mailsInBasket.get(index);
		return deleteMail.getAttribute("data-subject");
	}
}
