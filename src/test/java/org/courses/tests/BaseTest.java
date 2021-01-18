package org.courses.tests;

import org.courses.utils.Utils;
import org.courses.utils.WebDriverSelection;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {


    private static String userPassword = new Utils().getTestProperties().getProperty("userPassword");
    private static String userEmail = new Utils().getTestProperties().getProperty("userEmail");
    private static WebDriver myPersonalDriver = new WebDriverSelection().getDriverFromProperties();
    private static String adminName = new Utils().getTestProperties().getProperty("adminName");
    private static String adminPassword = new Utils().getTestProperties().getProperty("adminPassword");
    private static int implicitWait = Integer.parseInt(new Utils().getTestProperties().getProperty("implicitWait"));

    public BaseTest() {
        myPersonalDriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static WebDriver getMyPersonalDriver() {
        return myPersonalDriver;
    }

    public static String getAdminName() {
        return adminName;
    }

    public static String getAdminPassword() {
        return adminPassword;
    }

    public static int getImplicitWait() {
        return implicitWait;
    }

}
