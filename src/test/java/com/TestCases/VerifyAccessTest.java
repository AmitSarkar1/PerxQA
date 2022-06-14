package com.TestCases;

import static com.Generics.Configuration.url;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Generics.TestBase;
import com.PageObjects.LoginPage;
import com.PageObjects.OverviewPage;

public class VerifyAccessTest extends TestBase {

	LoginPage lp;
	OverviewPage op;
	SoftAssert sa  = new SoftAssert();
	
	@BeforeMethod(alwaysRun = true)
	public void openURL() {
		driver.get(url.asString());
		lp = new LoginPage(driver);
		op = new OverviewPage(driver);
	}
	
	
	@Test
	public void verifyRewardUserAccessToOtherSectionsTest() {
		
		
		
		sa.assertTrue(lp.doRewardAdminLogin(),"Reward Admin Login failed");
		sa.assertTrue(op.verifyRewardAdminDoesNotHaveAccessToOtherSections(),"Reward user has access to other sections");
		sa.assertAll();
		
	}
	

	@Test
	public void verifyRewardUserAccessToCreateRewardTest() {
		

		sa.assertTrue(lp.doRewardAdminLogin(),"Reward admin Login failed");
		sa.assertTrue(op.verifyUserHasSufficientPermissionToCreateReward(),"Reward admin do not have permission to create reward");
		sa.assertAll();
		
	}
	
	@Test
	public void verifyAdminForBulkFileUploadPage() {
		

		sa.assertTrue(lp.doAdminLogin()," admin Login failed");
		sa.assertTrue(op.verifyUserHasSufficientPermissionToVisitBulkFileUploadPage()," admin do not have permission to visit bulk file uploade page");
		sa.assertAll();
		
		
	}

}
