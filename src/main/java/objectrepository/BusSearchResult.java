package objectrepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BusSearchResult extends HomePage {

	WebDriver driver;

	public BusSearchResult(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	private By viewseats = By.xpath("//ul[@class='bus-items']//li//div[contains(@class,'button view-seats fr')]");

	public List<WebElement> getViewseat() {
		return driver.findElements(viewseats);
	}
}
