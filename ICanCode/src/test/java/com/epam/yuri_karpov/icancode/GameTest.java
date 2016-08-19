package com.epam.yuri_karpov.icancode;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import com.epam.yuri_karpov.icancode.bo.Account;
import com.epam.yuri_karpov.icancode.service.GameService;
import com.epam.yuri_karpov.icancode.ui.pageobject.GamePage;
import com.epam.yuri_karpov.icancode.ui.webdriver.Driver;
import com.epam.yuri_karpov.icancode.utils.ScreenShotListener;

@Listeners({ HTMLReporter.class, ScreenShotListener.class })
public class GameTest {

	private static final Logger LOG = Logger.getLogger(GameTest.class);
	private PhantomJSDriver driver;
	ResourceBundle resource = ResourceBundle.getBundle("config");
	private Account account = new Account(resource.getString("login"), resource.getString("password"));
	private GameService gameService;
	private GamePage gamePage;

	public PhantomJSDriver getDriver() {
		return this.driver;
	}

	@BeforeTest
	public void startBrowser() {
		LOG.warn("start 'startBrowser'");
		driver = Driver.getWebDriverInstance();
		gameService = new GameService(driver);
		LOG.warn("finish 'startBrowser'");
	}

	@Test
	public void gameLogin() {
		LOG.info("start 'gameLogin'");
		driver.get(resource.getString("mainURL"));
		gameService.loginToGame(account);
		gamePage = new GamePage(driver);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("d:\\before1.jpeg"),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		gamePage.setFirstLevel();
		LOG.info("finish 'gameLogin'");
	}

	@Test(dependsOnMethods = "gameLogin")
	public void prepareGame() {
		LOG.info("start 'prepareGame'");
		gamePage = new GamePage(driver);
		gamePage.pasteWalkthrough();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("d:\\pasteWalk.jpeg"),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("finish 'prepareGame'");
	}

	@Test(dependsOnMethods = "prepareGame")
	public void pass1A() {
		LOG.info("start 'pass1A'");
		gameService.passLvl();
		LOG.info("finish 'pass1A'");
	}

	@Test(dependsOnMethods = "pass1A")
	public void pass2A() {
		LOG.info("start 'pass2A'");
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("d:\\after1.jpeg"),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		gamePage = new GamePage(driver);
		gameService.passLvl();
		LOG.info("finish 'pass2A'");
	}

	@Test(dependsOnMethods = "pass2A")
	public void pass3A() {
		LOG.info("start 'pass3A'");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("d:\\after2.jpeg"),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		gameService.passLvl();
		LOG.info("finish 'pass3A'");
	}

	@Test(dependsOnMethods = "pass3A")
	public void pass4A() {
		LOG.info("start 'pass4A'");
		gameService.passLvl();
		LOG.info("finish 'pass4A'");
	}

	@Test(dependsOnMethods = "pass4A")
	public void pass5A() {
		LOG.info("start 'pass5A'");
		gameService.passLvl();
		LOG.info("finish 'pass5A'");
	}

	@Test(dependsOnMethods = "pass5A")
	public void pass6A() {
		LOG.info("start 'pass6A'");
		gameService.passLvl();
		LOG.info("finish 'pass6A'");
	}

	@Test(dependsOnMethods = "pass6A")
	public void pass7A() {
		LOG.info("start 'pass7A'");
		gameService.passLvl();
		LOG.info("finish 'pass7A'");
	}

	@Test(dependsOnMethods = "pass7A")
	public void pass8A() {
		LOG.info("start 'pass8A'");
		gameService.passLvl();
		LOG.info("finish 'pass8A'");
	}

	@Test(dependsOnMethods = "pass8A")
	public void pass9A() {
		LOG.info("start 'pass9A'");
		gameService.passLvl();
		LOG.info("finish 'pass9A'");
	}

	@Test(dependsOnMethods = "pass9A")
	public void passB() {
		LOG.info("start 'passB'");
		gameService.passLvl();
		LOG.info("finish 'passB'");
	}

	@Test(dependsOnMethods = "passB")
	public void passC() {
		LOG.info("start 'passC'");
		gameService.passLvl();
		LOG.info("finish 'passC'");
	}

	@AfterTest
	public void closeBrowser() {
		LOG.warn("start 'closeBrowser'");
		gameService.logOut();
		driver.quit();
		LOG.warn("start 'closeBrowser'");
	}

}
