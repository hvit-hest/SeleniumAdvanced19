package org.courses.tests;

import org.courses.pages.adminpage.AdminPage;
import org.courses.pages.common.CommonTable;
import org.courses.pages.createaccountpage.CreateAccountPage;
import org.courses.pages.mainpage.MainPage;
import org.courses.pages.securitysettingspage.SecuritySettingsPage;
import org.courses.pages.createaccountpage.data.AccountFormData;
import org.courses.utils.Utils;
import org.courses.utils.WebDriverSelection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UserRegistrationTest {

    private WebDriver myPersonalDriver;
    String itemName = "Settings";
    String subMenuItemName = "Security";

    @BeforeClass
    public void beforeClass() {

        myPersonalDriver = new WebDriverSelection().getDriverFromProperties();
        myPersonalDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //Turn off CAPTCHA
        String adminName = new Utils().getTestProperties().getProperty("adminName");
        String adminPassword = new Utils().getTestProperties().getProperty("adminPassword");
        AdminPage adminPage = new AdminPage(myPersonalDriver);
        adminPage.open();
        adminPage.login(adminName, adminPassword);
        adminPage.getMainAdminMenu().selectMenuOption(itemName);
        adminPage.getMainAdminMenu().selectSubMenuOption(subMenuItemName);
        SecuritySettingsPage securitySettingsPage = new SecuritySettingsPage(myPersonalDriver);
        securitySettingsPage.open();
        CommonTable securitySettingsTable = securitySettingsPage.getSettingsSecurityTable();
        WebElement row = securitySettingsTable.getRowByText("CAPTCHA");
        if (row.getText().contains("True")) {
            securitySettingsTable.getRowCells(row).get(2).findElement(By.cssSelector("a[href]")).click();
            securitySettingsPage.setCaptchaRadioButton("False");
            securitySettingsPage.clickButtonSave();
        }
    }


    @Test
    public void userRegistrationTest() {
        AccountFormData accountFormData = new AccountFormData();
        MainPage mainPage = new MainPage(myPersonalDriver);
        mainPage.open();
        mainPage.clickNewCustomerLink();
        CreateAccountPage createAccountPage = new CreateAccountPage(myPersonalDriver);
        createAccountPage.fillUsersForm(accountFormData);
        mainPage = new MainPage(myPersonalDriver);
        Assert.assertTrue(mainPage.isLogoutButtonPresent(), "Login was not successful");
        mainPage.logout();
        Assert.assertTrue(mainPage.isUserLoginFormOpened(), "Logout was not successful");
        mainPage.login(accountFormData.getEmail(), accountFormData.getPassword());
        Assert.assertTrue(mainPage.isLogoutButtonPresent(), "Login was not successful");
        mainPage.logout();
        Assert.assertTrue(mainPage.isUserLoginFormOpened(), "Logout was not successful");
    }

    @AfterClass
    public void afterClass() {
        myPersonalDriver.quit();
    }
}
