package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")
	public void verify_loginDDT(String email, String password, String exp_result) {
	
		logger.info("****** Starting TC003_LoginDDT *******");
				
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			Thread.sleep(3000);
			hp.clickLogin();
			Thread.sleep(3000);
			
			LoginPage lp = new LoginPage(driver);
			lp.setLoginEmail(email);
			lp.setLoginPassword(password);
			lp.clickLoginBtn();
					
			MyAccountPage myAcc = new MyAccountPage(driver);
			boolean targetPage = myAcc.isMyAccountPageExists();

			/*
			 * Data is valid -- login success -- test pass -- logout 
			 *               -- login fail    -- test fail
			 * 
			 * Data is invalid -- login success -- test fail -- logout
			 *                 -- login fail    -- test pass 
			*/			
			
			if (exp_result.equalsIgnoreCase("valid")) {
				if (targetPage==true) {
					myAcc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			} else if (exp_result.equalsIgnoreCase("invalid")) {
				if (targetPage==true) {
					myAcc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred: " + e.getMessage());
			Assert.fail();
		}
		
		logger.info("****** Finished TC003_LoginDDT *******");
	}
}
