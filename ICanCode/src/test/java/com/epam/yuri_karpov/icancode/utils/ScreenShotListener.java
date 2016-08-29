package com.epam.yuri_karpov.icancode.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.epam.yuri_karpov.icancode.GameTest;

public class ScreenShotListener implements ITestListener {

	@SuppressWarnings("unused")
	private void screenMake(ITestResult result) {
		Object inst = result.getInstance();

		if (inst == null) {
			return;
		}
		if (!(inst instanceof GameTest)) {
			return;
		}
		GameTest gameTest = (GameTest) inst;
		WebDriver driver = gameTest.getDriver();

		if (driver != null) {
			ScreenShot.make(driver, "AutoScreenShot");
		}

	}

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
