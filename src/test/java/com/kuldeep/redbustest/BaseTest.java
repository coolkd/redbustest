package com.kuldeep.redbustest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
	protected WebDriver driver;
	protected Properties properties;

	protected void initializeWebDriver() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(
					"C:\\Users\\KULDEEP\\eclipse-workspace\\redbustest\\src\\test\\java\\com\\kuldeep\\redbustest\\test.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperty("webdriver.chrome.driver", "C:\\Kuldeep\\chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
	}

}
