package org.courses.tests;

import org.courses.pages.adminpage.AdminPage;
import org.courses.pages.catalogpage.CatalogPage;
import org.courses.pages.editproductpage.EditProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class BrowsersLogTest extends BaseTest {
    private WebDriver myPersonalDriver;

    @BeforeClass
    public void beforeClass() {
        myPersonalDriver = getMyPersonalDriver();
        String adminName = getAdminName();
        String adminPassword = getAdminPassword();
        AdminPage adminPage = new AdminPage(myPersonalDriver);
        adminPage.open();
        adminPage.login(adminName, adminPassword);
        adminPage.getMainAdminMenu().selectMenuOption("Catalog");
    }

    @Test
    public void BrowserLogTest() {
        CatalogPage catalogPage = new CatalogPage(myPersonalDriver);
        //1. open all folders in catalog
        catalogPage.openAllFoldersInCatalog();
        //2. how many ducks to sold?
        List<WebElement> ducks = catalogPage.getAllDucks();
        int ducksNumber = ducks.size();

        SoftAssert softAssertion = new SoftAssert();
        //3. clean the log
        myPersonalDriver.manage().logs().get("browser");

        for (int i = 0; i < ducksNumber; i++) {
            String ducksName = ducks.get(i).getText();
            //open edit page
            ducks.get(i).click();
            EditProductPage editProductPage = new EditProductPage(myPersonalDriver);
            //check 'edit product' page's name
            softAssertion.assertTrue(editProductPage.getPageHeader().contains(ducksName));

            List<LogEntry> browsersLog = myPersonalDriver.manage().logs().get("browser").getAll();
            //check records in log
            softAssertion.assertTrue(browsersLog.size() == 0,
                    String.format("new '%s 'records in browser's log, page '%s'",
                            browsersLog.size(),
                            myPersonalDriver.getCurrentUrl()));
            //if browsersLog.size() == 0 it will do nothing
            browsersLog.forEach(System.out::println);

            //return to list of ducks/catalog
            myPersonalDriver.navigate().back();
            //renew page object
            catalogPage = new CatalogPage(myPersonalDriver);
            //take new ducks' list instead of stale one
            ducks = catalogPage.getAllDucks();
        }

        softAssertion.assertAll();
    }

    @AfterClass
    public void afterClass() {
        myPersonalDriver.quit();
    }
}

