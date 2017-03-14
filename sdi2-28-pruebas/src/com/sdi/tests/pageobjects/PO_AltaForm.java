package com.sdi.tests.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PO_AltaForm {

	
	
   public void rellenaFormulario(WebDriver driver, String pemail, String plogin, String ppassword, String ppasswordAgain)
   {
	   	WebElement idcorreo = driver.findElement(By.id("form-principal:correo"));
		idcorreo.click();
		idcorreo.clear();
		idcorreo.sendKeys(pemail);
		WebElement login = driver.findElement(By.id("form-principal:login"));
		login.click();
		login.clear();
		login.sendKeys(plogin);
		WebElement password = driver.findElement(By.id("form-principal:password"));
		password.click();
		password.clear();
		password.sendKeys(ppassword);
		WebElement passwordAgain = driver.findElement(By.id("form-principal:passwordAgain"));
		passwordAgain.click();
		passwordAgain.clear();
		passwordAgain.sendKeys(ppasswordAgain);
		
		//Pulsar el boton de guardar.
		By boton = By.id("form-principal:btnGuardar");
		driver.findElement(boton).click();	   
   }
	
	
	
}
