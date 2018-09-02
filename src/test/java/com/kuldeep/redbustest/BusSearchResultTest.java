package com.kuldeep.redbustest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import objectrepository.BusSearchResult;
import objectrepository.HomePage;

public class BusSearchResultTest extends HomePageTest {

	@Test
	public void getHomePage() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(
				"C:\\Users\\KULDEEP\\eclipse-workspace\\redbustest\\src\\test\\java\\com\\kuldeep\\redbustest\\test.properties"));
		System.setProperty("webdriver.chrome.driver", "C:\\Kuldeep\\chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		WebDriver driver = new ChromeDriver(options);

		driver.get("https://www.redbus.in/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		HomePage hp = new HomePage(driver);
		hp.getSource().sendKeys(properties.getProperty("source"));// mumbai

		driver.findElement(By.xpath("//li[@select-id='results[0]']")).click();
		hp.getDestination().sendKeys(properties.getProperty("destination")); // goa
		driver.findElement(By.xpath("//li[@select-id='results[0]']")).click();

		hp.getOnwarddate().click();
		selectDateFromCalender(driver, By.id("rb-calendar_onward_cal"), properties.getProperty("onward.month"),
				properties.getProperty("onward.date"));

		hp.getReturndate().click();
		selectDateFromCalender(driver, By.id("rb-calendar_return_cal"), properties.getProperty("return.month"),
				properties.getProperty("return.date"));

		hp.getSearchbuses().click();

		BusSearchResult bsr = new BusSearchResult(driver);
		bsr.getViewseat().get(0).click();

	}

	private void selectDateFromCalender(WebDriver driver, By by, String month, String date) {
		WebElement onwardCalender = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement calenderTd = onwardCalender.findElement(By.cssSelector("td.monthTitle"));
		System.out.println(calenderTd.getText());
		while (!calenderTd.getText().contains(month)) {
			wait.until(
					ExpectedConditions.visibilityOfNestedElementsLocatedBy(onwardCalender, By.cssSelector("td.next")));

			onwardCalender.findElement(By.cssSelector("td.next")).click();
			onwardCalender = driver.findElement(By.id("rb-calendar_onward_cal"));
			calenderTd = onwardCalender.findElement(By.cssSelector("td.monthTitle"));

		}

		List<WebElement> dates = onwardCalender.findElements(By.xpath("//*[contains(@class,'day')]"));
		int count = dates.size();
		System.out.println("Select day count:" + count);

		for (WebElement elements : dates) {

			if (date.equalsIgnoreCase(elements.getText())) {
				elements.click();
				break;
			}
		}

	}

}
