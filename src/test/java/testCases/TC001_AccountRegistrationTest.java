package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test (groups={"Regression", "Master"})
	public void verify_account_registration() {
	
		logger.info("****** Starting TC001_AccountRegistrationTest *******");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account link..");

			hp.clickRegister();
			logger.info("Clicked on Register link..");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

			logger.info("Providing customer details...");
			regpage.setFirstName("Tanveer");
			regpage.setLastName("Alam");
			regpage.setEmail("tanveeralam78228@gmail.com");
			regpage.setTelephone("123456789");

			String password = "Abcxyz123!";
			regpage.setPwd(password);
			regpage.setConfirmPwd(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();

			logger.info("Validating expected message...");
			String confmsg = regpage.getConfirmationMsg();

			if(confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test failed..");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("****** Finished TC001_AccountRegistrationTest *******");
	}
}
