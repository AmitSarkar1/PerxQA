package com.TestCases;

import static com.Generics.Configuration.url;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Generics.TestBase;
import com.PageObjects.LoginPage;
import com.PageObjects.OverviewPage;
import com.PageObjects.NewRewardPage;
import com.PageObjects.RewardsPage;

public class NewRewardTest extends TestBase {

	LoginPage lp;
	OverviewPage op;
	RewardsPage rp;
	NewRewardPage rcp;
	SoftAssert sa = new SoftAssert();
	
	@BeforeMethod(alwaysRun = true)
	public void openPage() {
		driver.get(url.asString());
		lp = new LoginPage(driver);
		op = new OverviewPage(driver);
		rp = new RewardsPage(driver);
		rcp = new NewRewardPage(driver);
	}

	@Test
	public void verifyRewardValidityPeriodTest() {
		
		sa.assertTrue(lp.doRewardAdminLogin(), "Reward Admin Login failed");
		sa.assertTrue(op.goToRewardsSection(),"User not navigated to Rewards section");
		sa.assertTrue(rp.clickCreateNewButton(),"user not navigated to create reward page");
		sa.assertTrue(rcp.verifyRewardValidityPeriodHaveBothStartAndEndDates());
		sa.assertAll();

	}
	
	
	@Test
	public void verifySuccessfulSubmissionTest() {
		
		sa.assertTrue(lp.doRewardAdminLogin(), "Reward Admin Login failed");
		sa.assertTrue(op.goToRewardsSection(),"User not navigated to Rewards section");
		sa.assertTrue(rp.clickCreateNewButton(),"user not navigated to create reward page");
		sa.assertTrue(rcp.verifySuccessfulSubmissionHappensOnlyWhenPayloadContainsMandInfo());
		sa.assertAll();
	}
	
	
	@Test
	public void verifyPrivateTypeRewardTest() {
		
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(lp.doRewardAdminLogin(), "Reward Admin Login failed");
		sa.assertTrue(op.goToRewardsSection(),"User not navigated to Rewards section");
		sa.assertTrue(rp.clickCreateNewButton(),"user not navigated to create reward page");
		sa.assertTrue(rcp.verifyBrandsTagsCategoriesLabelsFieldDisappersInPrivateTypeReward(),"fields did not disappear on private type of reward");
		sa.assertAll();

		
		
	}
	
}
