package objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
	}

	private By source = By.id("src");

	private By destination = By.id("dest");

	private By onwarddate = By.xpath("//span[contains(@class,'icon-onward-calendar')]");

	private By returndate = By.xpath("//span[contains(@class,'icon-return-calendar')]");

	private By searchbuses = By.id("search_btn");

	public WebElement getSource() {

		return driver.findElement(source);

	}

	public WebElement getDestination() {

		return driver.findElement(destination);

	}

	public WebElement getOnwarddate() {

		return driver.findElement(onwarddate);

	}

	public WebElement getReturndate() {

		return driver.findElement(returndate);
	}

	public WebElement getSearchbuses() {
		return driver.findElement(searchbuses);
	}

}
