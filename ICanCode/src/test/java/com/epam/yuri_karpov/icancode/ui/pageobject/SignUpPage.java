package com.epam.yuri_karpov.icancode.ui.pageobject;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.epam.yuri_karpov.icancode.bo.Account;

import junit.framework.Assert;

public class SignUpPage {
	private static final String GREETINGS_DONT_EXIST = "Greetings don't exist!";
	private static final String CONNECTING_TO_ROBOT_MESSAGE = "//div[contains(text(), 'Connecting to Robot')]";
	private static final String EMAIL_FIELD = "//input[@type = 'email']";
	private PhantomJSDriver driver;
	private static final Logger LOG = Logger.getLogger(SignUpPage.class);

	@FindBy(xpath = EMAIL_FIELD)
	private WebElement loginInput;

	@FindBy(xpath = "//input[@type = 'password']")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[@id='submit-button']")
	private WebElement submitButton;

	public SignUpPage(PhantomJSDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public SignUpPage setLogin(Account account) {
		LOG.info("start 'setLogin'");
		LOG.info("Login:" + account.getLogin());
		FluentWait<PhantomJSDriver> wait = new FluentWait<PhantomJSDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
		                                                                          .pollingEvery(3, TimeUnit.SECONDS)
		                                                                          .ignoring(
		                                                                                  NoSuchElementException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(EMAIL_FIELD)));
		loginInput.sendKeys(account.getLogin());
		LOG.info("finish 'setLogin'");
		return this;
	}

	public SignUpPage setPassword(Account account) {
		LOG.info("start 'setPassword'");
		passwordInput.sendKeys(account.getPassword());
		LOG.info("finish 'setPassword'");
		return this;
	}

	public GamePage signIn() {
		LOG.info("start 'signIn'");
		submitButton.click();
		FluentWait<PhantomJSDriver> wait = new FluentWait<PhantomJSDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
		                                                                          .pollingEvery(3, TimeUnit.SECONDS)
		                                                                          .ignoring(
		                                                                                  NoSuchElementException.class);
		WebElement greetings = wait.until(
		        ExpectedConditions.presenceOfElementLocated(By.xpath(CONNECTING_TO_ROBOT_MESSAGE)));
		Assert.assertTrue(GREETINGS_DONT_EXIST, greetings.isDisplayed());
		LOG.info("finish 'signIn'");
		return new GamePage(this.driver);
	}

}
