package org.courses.tests;

import org.courses.pages.adminpage.AdminPage;
import org.courses.pages.countriespage.CountriesPage;
import org.courses.pages.editcountrypage.EditCountryPage;
import org.courses.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Map;
import java.util.Set;

public class AddWindowTest extends BaseTest {
    private WebDriver myPersonalDriver;

    @BeforeClass
    public void beforeClass() {

        myPersonalDriver = getMyPersonalDriver();
        String adminName = getAdminName();
        String adminPassword = getAdminPassword();
        AdminPage adminPage = new AdminPage(myPersonalDriver);
        adminPage.open();
        adminPage.login(adminName, adminPassword);
        adminPage.getMainAdminMenu().selectMenuOption("Countries");
    }

    @Test
    public void addWindowTest() {
        CountriesPage countriesPage = new CountriesPage(myPersonalDriver);
        countriesPage.getCountryByName("Russian Federation").click();
        EditCountryPage editCountryPage = new EditCountryPage(myPersonalDriver);
        SoftAssert softAssertion = new SoftAssert();
        Map<String,String> testData = new Utils().readAddWindowTestDataFromJson("AddWindowTest.json");

        WebDriverWait wait = new WebDriverWait(myPersonalDriver,7);

        editCountryPage.getHrefList().forEach(href -> {
            String originalWindow = myPersonalDriver.getWindowHandle();
            Set<String> existingWindows = myPersonalDriver.getWindowHandles();
            String link = href.getAttribute("href");
            href.click();
            String newWindow = wait.until((WebDriver d) -> {
                Set<String> handles = d.getWindowHandles();
                handles.removeAll(existingWindows);
                return handles.size() >0 ? handles.iterator().next():null;
            });
            myPersonalDriver.switchTo().window(newWindow);
            String header = myPersonalDriver.findElement(By.cssSelector("h1")).getText();
            softAssertion.assertEquals(testData.get(header), link,
                    String.format("header '%s' is not expected for '%s'", header, link));
            myPersonalDriver.close();
            myPersonalDriver.switchTo().window(originalWindow);
        });
        softAssertion.assertAll();
    }

    @AfterClass
    public void afterClass() {
        myPersonalDriver.quit();
    }
}