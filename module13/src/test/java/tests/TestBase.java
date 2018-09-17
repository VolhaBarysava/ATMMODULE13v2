package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import bo.Email;
import bo.EmailUtils;
import bo.User;
import services.EmailService;
import utils.WebDriverSingleton;
import pages.AccountPage;
import pages.HomePage;

public class TestBase {
	
	private static final String MAILRU_LOGIN_FIRST_ACCOUNT = "vra_atmmodule5";
	private static final String MAILRU_PASSWORD_FIRST_ACCOUNT = "123456789_Vra";
	private static final String MAILRU_LOGIN_SECOND_ACCOUNT = "vra_atmmodule6";
	private static final String MAILRU_PASSWORD_SECOND_ACCOUNT = "123456789_Vra";
	private static final String MAILRU_URL = "https://mail.ru/";
	static final String EXPECTED_FIRST_ACCOUNT = "vra_atmmodule5@mail.ru"; 
	static final String EXPECTED_SECOND_ACCOUNT = "vra_atmmodule6@mail.ru"; 
	
	protected WebDriver driver;
	protected EmailService emailService = new EmailService();
	
	User firstUser = new User(MAILRU_LOGIN_FIRST_ACCOUNT,MAILRU_PASSWORD_FIRST_ACCOUNT);
	User secondUser = new User(MAILRU_LOGIN_SECOND_ACCOUNT,MAILRU_PASSWORD_SECOND_ACCOUNT);
	
	Email email = EmailUtils.getDefaultEmail();

	@BeforeClass
	public HomePage startBrowser(){
		WebDriver driver = WebDriverSingleton.getWebDriverInstance();
		driver.get(MAILRU_URL);
		return new HomePage(driver);
	}
	
	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		AccountPage accountPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), AccountPage.class);	
		accountPage.clickLogOut();
		WebDriverSingleton.kill();
	}

}
