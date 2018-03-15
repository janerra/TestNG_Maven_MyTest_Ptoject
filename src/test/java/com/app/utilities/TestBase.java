package com.app.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {
	protected static WebDriver driver;

	@BeforeClass(alwaysRun=true)
	@Parameters("browser")
	public void setUp(@Optional String browser) {
		driver=Driver.getDriver(browser);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(ConfigurationReader.getProperty("url"));
	}

		@AfterClass(alwaysRun=true)
		public void tearDown() {
			//driver.quit();
			//Driver.quit();
		}	

}
