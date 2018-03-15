package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;



public class EstimateMonthlyBillPage {
private WebDriver driver;
	
	public EstimateMonthlyBillPage() {
		this.driver = Driver.getDriver(null);
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//table[@class='value']//input[@class='gwt-TextBox gwt-TextBox-readonly']")
	WebElement price;
	
	@FindBy(xpath="//div[@class='restLabel']")
	public WebElement tabServices;
		
	

	public double getPrice() {
		System.out.println("++**"+price.getAttribute("value"));
		return Double.parseDouble(price.getAttribute("value"));
	}

}