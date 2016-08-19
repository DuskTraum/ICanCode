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
	private PhantomJSDriver driver;
	private static final Logger LOG = Logger.getLogger(SignUpPage.class);

	@FindBy(xpath = "//input[@type = 'email']")
	private WebElement loginInput;

	@FindBy(xpath = "//input[@type = 'password']")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[@id='submit-button']")
	private WebElement submitBtn;

	public SignUpPage(PhantomJSDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public SignUpPage setLogin(Account account) {
		LOG.info("start 'setLogin'");
		LOG.info("Login:" + account.getLogin());
		FluentWait<PhantomJSDriver> wait = new FluentWait<PhantomJSDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type = 'email']")));
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
		submitBtn.click();
		FluentWait<PhantomJSDriver> wait = new FluentWait<PhantomJSDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement greetings = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), 'Connecting to Robot')]")));
		Assert.assertTrue("Greetings don't exist!", greetings.isDisplayed());
		LOG.info("finish 'signIn'");
		return new GamePage(this.driver);
	}

}
