package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Highlighter {
	
	public static void highlightElement(WebElement element) {
		WebDriver driver = WebDriverSingleton.getWebDriverInstance();
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].style.backgroundColor = 'yellow'", element);
	}

	public static void unHighlightElement(WebElement element) {
		WebDriver driver = WebDriverSingleton.getWebDriverInstance();
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].style.backgroundColor = ''", element);
	}

}
