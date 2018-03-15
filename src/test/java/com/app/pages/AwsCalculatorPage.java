package com.app.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.app.utilities.Driver;





public class AwsCalculatorPage {
	
	private WebDriver driver;
	
	public AwsCalculatorPage() {
		this.driver = Driver.getDriver(null);
		PageFactory.initElements(driver, this);
	}
	
	//find element with text = Estimate of your Monthly Bill ($ 0.00)
	@FindBy (className = "billLabel")
	public WebElement billLabel;
	
	// green button with +
	@FindBy (xpath="(//*[@class='gwt-PushButton small gwt-PushButton-up'])[1]")
	
	//@FindBy(xpath="(//*[@class='gwt-PushButton small gwt-PushButton-up']/input)[1]")
	public WebElement addEc2;  //addEc2;
	
	// description field
	@FindBy (xpath="//tr[@class='EC2InstanceRow itemsTableDataRow table']//input[@class='gwt-TextBox input']")
	public WebElement description; 
	
	//instances count field
	@FindBy(xpath="//tr[@class='EC2InstanceRow itemsTableDataRow table']//table[@class='SF_EC2_INSTANCE_FIELD_INSTANCES field integerNumericField']//input[@class='gwt-TextBox numericTextBox input']")
	public WebElement instanceCount;
		
	//Usage-1 (count) field
	@FindBy(xpath="//table[@class='SF_EC2_INSTANCE_FIELD_USAGE field usageField']//input")
	public WebElement usageCount;
	
	//Usage-2  dropdown
	@FindBy(xpath="//table[@class='SF_EC2_INSTANCE_FIELD_USAGE field usageField']//select")
	public WebElement usage;
	
	// Type  "Linux on t1.micro"
	@FindBy(xpath="//div[@class='gwt-HTML field SF_EC2_INSTANCE_FIELD_TYPE instanceTypeField rowDialogSelectorFieldView']")
	public WebElement ec2Type;
	
	// Billing option
	@FindBy (xpath = "//div[@class='gwt-HTML field SF_COMMON_FIELD_BILLING instanceBillingField rowDialogSelectorFieldView']")
	public WebElement billingOption;
	
	// monthy Cost  ($14.64) 
	@FindBy(xpath="//div[@class='gwt-HTML DynamicPrice DynamicPricePricing']")
	public WebElement monthlyCost;
	// link "Estimate of your Monhly Bill
	
	
	// button Clear
	@FindBy(xpath="//button[@class='gwt-Button reset small']")
	public WebElement clearForm;
	
	
//	pop-up dialog "Please confirm"
	@FindBy(xpath="//div[@class='gwt-DialogBox ConfirmDialog Dialog']")
	public WebElement confirmDialog;
	
	//OK button on pop-up
	@FindBy(xpath="(//button[@class='gwt-Button'])[2]")
	public WebElement buttonOK;
	
	//Please confirm on popUp
	@FindBy(xpath="//div[@class='Caption']")
	public WebElement titlePopUp;
	
	// label on POpup
	@FindBy(xpath="//div[@class='gwt-HTML ConfirmBody']")
	public WebElement textPopUp;
	
//	public boolean confirmDialog1() {
//	try {
//		confirmDialog.isDisplayed();
//		return true;
//	} catch(NoAlertPresentException e) {
//		return false;
//	}

	
	//==========================
	
	public boolean isAt() {
		System.out.println(driver.getTitle().toString());
		return driver.getTitle().equals("Amazon Web Services Simple Monthly Calculator");
	}
	
	//Usage-2  dropdown. Return  first select option
	    public String getUsageOption() {
		Select select = new Select(usage);
		return select.getFirstSelectedOption().getText();
		}
	
	 // monthy Cost  ($14.64). Get double monthly cost 
	    public double getmonthlyCost() {
			return Double.parseDouble(monthlyCost.getText().replace(" $", ""));
		}
	
	  //instances count field. Return int - number of instances
	    public int getInstanceCount() {
		return Integer.parseInt(instanceCount.getAttribute("value"));
	}
	  //usage count field. Return int - number of usage count
	    public int getUsageCount() {
		return Integer.parseInt(usageCount.getAttribute("value")); 
	    }
	
	// take money from TAB string = Estimate of your Monthly Bill ($ 0.00). Return double
	public double getMonthlyBillAmount() {
		String billText = billLabel.getText();
		String[] arrText = billText.split("\\$ ");
		String bill = arrText[1].replace(")", "");
		
		return Double.parseDouble(bill);
	}
	// return $ 14.64
	public double getMonthlyCost() {
		return Double.parseDouble(monthlyCost.getText().replace("$ ", ""));
	}
	//pop-up is Displayed
	public boolean confirmDialog() {
		System.out.println(confirmDialog.getText());
		return confirmDialog.isDisplayed();
	}
	
	//table is clear after click the Clear button
	public boolean isEC2InstancesTableClear() {
		
		return driver.findElements(By.xpath("//tr[@class='EC2InstanceRow itemsTableDataRow table']")).isEmpty();
		
		
	}
	
	
//public boolean checkClearAlert() {
//	
//	try {
//		Alert alert = driver.switchTo().alert();
//	} catch(NoAlertPresentException e) {
//		return false;
//	}
//}

//public boolean confirmDialog() {
//	return confirmDialog.isDisplayed();
//}


}