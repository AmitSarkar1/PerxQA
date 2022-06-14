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

public class RewardsTest extends TestBase {

	LoginPage lp;
	OverviewPage op;
	RewardsPage rp;
	NewRewardPage rcp;
	SoftAssert sa = new SoftAssert();

	@BeforeMethod(alwaysRun = true)
	public void openURL() {
		driver.get(url.asString());
		lp = new LoginPage(driver);
		op = new OverviewPage(driver);
		rp = new RewardsPage(driver);
		rcp = new NewRewardPage(driver);
	}

	@Test
	public void verifyCreateNewReward() {

		sa.assertTrue(lp.doRewardAdminLogin(), "Reward Admin Login failed");
		sa.assertTrue(op.goToRewardsSection(), "User not naivated to Rewards section");
		sa.assertTrue(rp.clickCreateNewButton(), "click create new Button test failed");

		sa.assertAll();
	}

	@Test
	public void VerifynewlyCreatedRewardInListTest() {

	
		sa.assertTrue(lp.doRewardAdminLogin(), "Reward Admin Login failed");
		sa.assertTrue(op.goToRewardsSection(), "User not naivated to Rewards section");
		sa.assertTrue(rp.clickCreateNewButton(), "click create new Button test failed");
		sa.assertTrue(rcp.createNewReward("TestNewReward", "30 Jun 2022"), "Reward creation failed");
		sa.assertTrue(op.goToRewardsSection(), "User not naivated to Rewards section");
		sa.assertTrue(rp.newlyCreatedRewardPresentInList("TestNewReward"));
		sa.assertAll();
	}
}