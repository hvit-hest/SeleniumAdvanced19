package org.courses.pages.mainpage.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserLoginForm {
    private WebDriver driverHere;
    private String searchEmailFieldCSS = "[name='email']";
    private String searchPasswordFieldCSS = "[name='password']";
    private String searchLoginButtonCSS = "button[name='login']";

    public UserLoginForm(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
    }

    public void login (String userEmail, String userPassword) {
        driverHere.findElement(By.cssSelector(searchEmailFieldCSS)).sendKeys(userEmail);
        driverHere.findElement(By.cssSelector(searchPasswordFieldCSS)).sendKeys(userPassword);

        driverHere.findElement(By.cssSelector(searchLoginButtonCSS)).click();
    }
}
