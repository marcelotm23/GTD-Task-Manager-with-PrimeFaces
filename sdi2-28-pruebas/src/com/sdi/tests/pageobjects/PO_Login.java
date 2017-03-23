package com.sdi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Login {

	public void rellenaFormulario(WebDriver driver, String plogin,
			String ppassword) {
		WebElement idUsername = driver.findElement(By
				.id("form-principal:username"));
		idUsername.clear();
		idUsername.sendKeys(plogin);
		WebElement idPassword = driver.findElement(By
				.id("form-principal:password"));
		idPassword.clear();
		idPassword.sendKeys(ppassword);
		driver.findElement(By.id("form-principal:btnLogin")).click();
	}

}
