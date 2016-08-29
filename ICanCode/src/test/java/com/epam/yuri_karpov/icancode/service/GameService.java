package com.epam.yuri_karpov.icancode.service;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.epam.yuri_karpov.icancode.bo.Account;
import com.epam.yuri_karpov.icancode.ui.pageobject.GamePage;
import com.epam.yuri_karpov.icancode.ui.pageobject.SignUpPage;

public class GameService {

	private PhantomJSDriver driver;

	public GameService(PhantomJSDriver driver) {
		this.driver = driver;
	}

	public void loginToGame(Account account) {
		SignUpPage signUp = new SignUpPage(driver);
		signUp.setLogin(account);
		signUp.setPassword(account);
		signUp.signIn();
	}

	public void logOut() {
		GamePage gamePage = new GamePage(driver);
		gamePage.logOut();
	}

	public void passLvl() {
		GamePage gamePage = new GamePage(driver);
		gamePage.passLevel();
	}

}
