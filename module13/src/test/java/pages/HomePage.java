package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import reporting.MyLogger;
import utils.Highlighter;
import bo.User;

public class HomePage extends BasePage {
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "mailbox:login")
	private WebElement loginField;

	@FindBy(id = "mailbox:password")
	private WebElement passwordField;

	@FindBy(id = "mailbox:submit")
	private WebElement submitBtn;

	public AccountPage setUserName(User user) {
		MyLogger.debug("Set " + user.getLogin() + " to loginField");
		waitForElementVisible(loginField);
		Highlighter.highlightElement(loginField);
		loginField.clear();
		loginField.sendKeys(user.getLogin());
		Highlighter.unHighlightElement(loginField);
		return new AccountPage(driver);
	}
	
	public AccountPage setUserPassword(User user){
		MyLogger.debug("Set " + user.getPassword() + " to passwordField");
		waitForElementVisible(passwordField);
		Highlighter.highlightElement(passwordField);
		passwordField.clear();
		passwordField.sendKeys(user.getPassword());
		Highlighter.unHighlightElement(passwordField);
		return new AccountPage(driver);
	}
	
	public AccountPage clickSubmitBtn(){
		MyLogger.debug("Click submit button: submitBtn");
		waitForElementVisible(submitBtn);
		Highlighter.highlightElement(submitBtn);
		Highlighter.unHighlightElement(submitBtn);
		submitBtn.click();
		return new AccountPage(driver);
	}

}
