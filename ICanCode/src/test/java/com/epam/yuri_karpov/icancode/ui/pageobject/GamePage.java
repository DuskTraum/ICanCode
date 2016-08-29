package com.epam.yuri_karpov.icancode.ui.pageobject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class GamePage {
	private static final String THE_CURRENT_FIRST_LEVEL = "//li[@class='training level-current' and @level ='1']";
	private PhantomJSDriver driver;
	private static final Logger LOG = Logger.getLogger(GamePage.class);

	@FindBy(xpath = "//li[@level ='1']")
	private WebElement firstLvlButton;

	@FindBy(xpath = "//a[@class='mCSB_buttonLeft']")
	private WebElement leftLevelScrollButton;

	@FindBy(xpath = "//div[@class='ace_line'][1]")
	private WebElement ideField;

	@FindBy(xpath = "//div[@class='ace_content']")
	private WebElement field;

	@FindBy(id = "ide-commit")
	private WebElement commitButton;

	@FindBy(xpath = "//a[@id='register-link']")
	private WebElement logOutButton;

	@FindBy(xpath = "//pre//textarea")
	private WebElement textField;

	@FindBy(xpath = "//li[@level ='1']")
	private WebElement firstLevel;

	public GamePage(PhantomJSDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void passLevel() {
		LOG.info("start 'passLevel'");
		FluentWait<PhantomJSDriver> wait = new FluentWait<PhantomJSDriver>(driver).withTimeout(120, TimeUnit.SECONDS)
		                                                                          .pollingEvery(3, TimeUnit.SECONDS)
		                                                                          .ignoring(
		                                                                                  NoSuchElementException.class);
		wait.until(ExpectedConditions.elementToBeClickable(commitButton));
		JavascriptExecutor jsExecutor = driver;
		jsExecutor.executeScript("arguments[0].click();", commitButton);

		LOG.info("finish 'passLevel'");
	}

	public void setFirstLevel() {
		LOG.info("start 'setFirstLevel'");
		new Actions(driver).clickAndHold(leftLevelScrollButton)
		                   .build()
		                   .perform();
		FluentWait<PhantomJSDriver> wait = new FluentWait<PhantomJSDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
		                                                                          .pollingEvery(4, TimeUnit.SECONDS)
		                                                                          .ignoring(
		                                                                                  MoveTargetOutOfBoundsException.class);
		wait.until(ExpectedConditions.elementToBeClickable(firstLevel));

		new Actions(driver).release(leftLevelScrollButton)
		                   .build()
		                   .perform();
		firstLevel.click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(THE_CURRENT_FIRST_LEVEL)));
		LOG.info("finish 'setFirstLevel'");
	}

	public void pasteWalkthrough() {
		LOG.info("start 'pasteWalkthrough'");
		FluentWait<PhantomJSDriver> wait = new FluentWait<PhantomJSDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
		                                                                          .pollingEvery(4, TimeUnit.SECONDS)
		                                                                          .ignoring(
		                                                                                  NoSuchElementException.class);
		wait.until(ExpectedConditions.elementToBeClickable(field));
		field.click();
		textField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		textField.sendKeys(getWalkthrough());
		LOG.info("finish 'pasteWalkthrough'");

	}

	public String getWalkthrough() {
		StringBuilder fileContents = new StringBuilder();
		try (BufferedReader input = new BufferedReader(new FileReader("walkthrough.txt"))) {
			String line;
			while ((line = input.readLine()) != null) {
				fileContents.append(line);
			}
		}
		catch (FileNotFoundException fe) {
			System.err.println(fe);
		}
		catch (IOException e) {
			System.err.println(e);
		}
		return fileContents.toString();
	}

	public void logOut() {
		logOutButton.click();
	}

}
