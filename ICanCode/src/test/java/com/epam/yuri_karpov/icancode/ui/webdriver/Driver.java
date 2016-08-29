package com.epam.yuri_karpov.icancode.ui.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Driver {
	private static final String PHANTOMJS_PATH = "C:\\Users\\Yuri_Karpov@epam.com\\workspace\\ICanCode\\phantomjs.exe";

	public static PhantomJSDriver getWebDriverInstance() {
		Capabilities capabilities = new DesiredCapabilities();
		((DesiredCapabilities) capabilities).setJavascriptEnabled(true);
		((DesiredCapabilities) capabilities).setCapability("takesScreenshot", true);
		((DesiredCapabilities) capabilities).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		        PHANTOMJS_PATH);
		PhantomJSDriver driver = new PhantomJSDriver(capabilities);
		driver.manage()
		      .deleteAllCookies();
		driver.manage()
		      .timeouts()
		      .implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage()
		      .window()
		      .maximize();
		return driver;
	}

}
