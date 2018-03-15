package com.app.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.app.pages.AwsCalculatorPage;
import com.app.pages.EstimateMonthlyBillPage;
import com.app.utilities.TestBase;


public class AWSCalclatorTests extends TestBase{
	
	AwsCalculatorPage calculatorPage = new AwsCalculatorPage();
	
	@Test(priority=0, description="Test 1. Monthly bill should be $0.00 by default")
	public void defaultMonthlyBillTest() {
		
		System.out.println("LabelText = "+calculatorPage.billLabel.getText());
		System.out.println("money in Lable = "+calculatorPage.getMonthlyBillAmount());
		
		assertTrue(calculatorPage.isAt());
		assertEquals(0.0, calculatorPage.getMonthlyBillAmount());
		
		
	}
	
	@Test(priority=1, description = "Test 2")
	/*
	 * 1. Navigate to https://calculator.s3.amazonzws.com/index.html
	 * Add a EC2 instance
	 * Verify Description is blanc
	 * - 1 instances
	 * - 100 usage
	 * - % Utilized/Month
	 * - IS ->Linux on t1.micro
	 * -On-Demand(No Contract)
	 * - Monthly price should match with other tab total price
	 */
	public void addedEc2DefaultValuesTest() throws InterruptedException{
		driver.manage().window().maximize();
		Actions action = new Actions(driver);
		
		action.moveToElement(calculatorPage.addEc2);
		Thread.sleep(500);

		// click on green round button +
		calculatorPage.addEc2.click();
		//calculatorPage.description.sendKeys("test");
		assertTrue(calculatorPage.description.getAttribute("value").isEmpty());
		// 1 in the cell with number
		assertEquals(calculatorPage.getInstanceCount(), 1);
		//100 in usage count cell
		assertEquals(calculatorPage.getUsageCount(), 100);
		//assert what in fiels with % has the same value as in first page
		assertEquals("% Utilized/Month", calculatorPage.getUsageOption());
		assertEquals("Linux on t1.micro", calculatorPage.ec2Type.getText());
		
		assertEquals(calculatorPage.billingOption.getText(),"On-Demand (No Contract)");
		
		Double monthlyCost = calculatorPage.getMonthlyCost();
		assertEquals(calculatorPage.getMonthlyCost(), 14.64);
		
		calculatorPage.billLabel.click();
		Thread.sleep(1000);
		
		EstimateMonthlyBillPage estimatePage = new EstimateMonthlyBillPage();
		assertEquals(estimatePage.getPrice(), monthlyCost);
		
		estimatePage.tabServices.click();
		
		
	} 
	
	@Test (priority=2, description = "Test 3")
		/*
		 * 1.Click on Clear Form
		 * 2. Verify alert is displayed and text is ""
		 * 3. Click on OK
		 * Verify that form is cleared.
		 * 
		 */
		public void clearFormTest() throws InterruptedException {
		
		AwsCalculatorPage calculatorPage2 = new AwsCalculatorPage();
		Thread.sleep(1000);
		calculatorPage.clearForm.click();
		
		assertTrue(calculatorPage.confirmDialog());
		String popupText = calculatorPage.confirmDialog.getText();
		assertTrue(popupText.contains("Please Confirm") && popupText.contains("Are you sure you want to clear all entries in this service form?"));
		
		// click OK on popup
		calculatorPage.buttonOK.click();
		
		Thread.sleep(1000);
		
		//verify that form is cleared
		//System.out.println("main is "+calculatorPage.isEC2InstancesTableClear());
		assertEquals(calculatorPage.isEC2InstancesTableClear(),true);
		
	}
		
//	public void CleatFormTest() {
//		calculatorPage.clearForm.click();
//		assertTrue(calculatorPage.checkClearAlert());
//	}
	
	

}
