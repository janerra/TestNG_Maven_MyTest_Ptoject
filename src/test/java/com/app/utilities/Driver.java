package com.app.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.internal.Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	private static WebDriver driver;

	public static WebDriver getDriver(String browser) {
		
		String driverType = browser==null? ConfigurationReader.getProperty("browser") : browser;
		
		// if browser has value, use browser
		// else use the value from the configuration file
		
		if (driver == null) {
			// create webdriver based on the value of browser parameter
			if(driverType.toLowerCase().equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			if(driverType.toLowerCase().equals("safari")) {
				driver = new SafariDriver();
			}
			if(driverType.toLowerCase().equals("opera")) {
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
			}
			if(driverType.toLowerCase().equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			if(driverType.toLowerCase().equals("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
		
		}	// return
		return driver;
	}
	
	public static void quit() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
