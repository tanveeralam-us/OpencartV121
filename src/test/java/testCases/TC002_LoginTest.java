package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity","Master"})
	public void verify_login() throws InterruptedException {
	
		logger.info("****** Starting TC002_LoginTest *******");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			LoginPage lp = new LoginPage(driver);
			lp.setLoginEmail(p.getProperty("email"));
			lp.setLoginPassword(p.getProperty("password"));
			lp.clickLoginBtn();
			
			MyAccountPage myAcc = new MyAccountPage(driver);
			boolean targetPage = myAcc.isMyAccountPageExists();
			
			Assert.assertTrue(targetPage);
			
		} catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("****** Finished TC002_LoginTest *******");
	}
}
