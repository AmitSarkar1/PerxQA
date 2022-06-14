package com.TestCases;

import static com.Generics.Configuration.url;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Generics.TestBase;
import com.PageObjects.BulkActionsPage;
import com.PageObjects.LoginPage;
import com.PageObjects.OverviewPage;

public class BulkActionsTest extends TestBase {

	LoginPage lp;
	OverviewPage op;
	BulkActionsPage bap;

	@BeforeMethod(alwaysRun = true)
	public void openPage() {
		driver.get(url.asString());
		lp = new LoginPage(driver);
		op = new OverviewPage(driver);
		bap = new BulkActionsPage(driver);
	}

	@Test
	public void verifyUploadExcelTypeFile() {
		SoftAssert sa = new SoftAssert();		
		String excelPath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Assets\\TestDocuments\\sampleExcel.xlsx";

		sa.assertTrue(lp.doAdminLogin(),"admin login failed");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.uploadBulkActionsFile(excelPath, "File uploaded"),"Excel type file not accepted");
		sa.assertAll();
	}
	
	@Test
	public void verifyUploadTxtTypeFileTest() {

		SoftAssert sa = new SoftAssert();

		String txtPath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Assets\\TestDocuments\\sampleText.txt";

		sa.assertTrue(lp.doAdminLogin(),"admin login failed");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.uploadBulkActionsFile(txtPath, "File uploaded"),"Text type file not accepted");
		sa.assertAll();
	}
	
	@Test
	public void verifyUploadCsvTypeFileTest() {

		SoftAssert sa = new SoftAssert();

		String csvPath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Assets\\TestDocuments\\demo.csv";

		sa.assertTrue(lp.doAdminLogin(),"admin login failed");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.uploadBulkActionsFile(csvPath, "File uploaded"),"csv type file not accepted");
		sa.assertAll();
	}

	
	@Test
	public void verifyUserNotAbleToUploadPDFTypeFileTest() {
		SoftAssert sa = new SoftAssert();
		String pdfPath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Assets\\TestDocuments\\sample.pdf";
		sa.assertTrue(lp.doAdminLogin(),"admin login failed");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.uploadBulkActionsFile(pdfPath, "Error uploading file."),"pdf type file is being accepted");
		sa.assertAll();
	}
	
	
	@Test
	public void verifyNewlyUploadedFileInListTest() {
		SoftAssert sa = new SoftAssert();
		String path = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Assets\\TestDocuments\\demo.csv";
		
		sa.assertTrue(lp.doAdminLogin(),"admin login failed");
		sa.assertTrue(op.goToBulkActionsSection(),"admin not navigated to bulk actions page");
		sa.assertTrue(bap.uploadBulkActionsFile(path, "File uploaded"),"File not uploaded");
		sa.assertTrue(bap.verifyNewlyUploadedFileReflectsInList("demo.csv"),"File not present in list");
		sa.assertAll();
	}

}
