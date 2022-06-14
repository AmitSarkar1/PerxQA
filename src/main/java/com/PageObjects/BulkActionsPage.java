package com.PageObjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Generics.BasePage;

public class BulkActionsPage extends BasePage {

	private static final Logger log = Logger.getLogger(BulkActionsPage.class.getName());

	public BulkActionsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "(//button)[1]")
	private WebElement uploadBtn;

	@FindBy(xpath = "//div[@class='ant-upload-drag-container']/p/i")
	private WebElement uploadFileArea;

	@FindBy(xpath = "//div[@class='ant-upload-list-item-info']")
	private WebElement uploadedFileOnForm;

	@FindBy(xpath = "//button[@class='ant-btn ant-btn-primary']")
	private WebElement uploadBtnOnForm;

	@FindBy(xpath = "(//div[@class='ant-message-notice-content'])[1]/div/span")
	private WebElement popupAlert;

	@FindBy(xpath = "//tbody/tr/td[2]")
	private List<WebElement> filesPresentInList;

	public boolean verifyNewlyUploadedFileReflectsInList(String fileName) {

		boolean flag = false;

		List<String> filesPresent = getTextList(filesPresentInList);
		log.info("Files present in list : "+filesPresent);
		if (filesPresent.contains(fileName))
			flag = true;
		return flag;
	}

	public boolean uploadBulkActionsFile(String path, String popupText) {

		boolean flag = false;
		click(uploadBtn);
		log.info("clicked on upload button");
		click(uploadFileArea);
		log.info("clicked on file drop area ");
		uploadFile(path);
		log.info("clicked on upload button on form and uploaded file");
		log.info("Uploading file : "+path.substring(path.lastIndexOf("\\")+1));
		log.info("uploading file of type : " + path.substring(path.lastIndexOf(".") + 1));
		wait.forElementToBeVisible(uploadedFileOnForm);
		click(uploadBtnOnForm);
		String text = getText(popupAlert);
		log.info("Alert poped up as : " + text);

		if (text.equals(popupText))
			flag = true;

		return flag;
	}

}
