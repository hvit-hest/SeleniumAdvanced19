package org.courses.tests;

import org.courses.pages.addnewproductpage.AddNewProductPage;
import org.courses.pages.addnewproductpage.components.NewProductTabs;
import org.courses.pages.addnewproductpage.data.ProductDataModel;
import org.courses.pages.adminpage.AdminPage;
import org.courses.pages.catalogpage.CatalogPage;
import org.courses.utils.Utils;
import org.courses.utils.WebDriverSelection;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

public class AddProductTest {

    private WebDriver myPersonalDriver;

    private LinkedHashMap<String, String> informationData;
    private LinkedHashMap<String, Object> generalData;
    private LinkedHashMap<String, String> pricesData;

    @BeforeClass
    public void beforeClass() {

        myPersonalDriver = new WebDriverSelection().getDriverFromProperties();
        myPersonalDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String adminName = new Utils().getTestProperties().getProperty("adminName");
        String adminPassword = new Utils().getTestProperties().getProperty("adminPassword");
        AdminPage adminPage = new AdminPage(myPersonalDriver);
        adminPage.open();
        adminPage.login(adminName, adminPassword);
        adminPage.getMainAdminMenu().selectMenuOption("Catalog");
        ProductDataModel productData = new Utils().readProductDataFromJson("ProductForm.json");
        generalData = productData.getGeneralData();
        informationData = productData.getInformationData();
        pricesData = productData.getPricesData();
    }

    @Test
    public void addProductTest() {
        CatalogPage catalogPage = new CatalogPage(myPersonalDriver);
        catalogPage.clickAddNewProductButton();
        AddNewProductPage addNewProductPage = new AddNewProductPage(myPersonalDriver);
        //explicit wait in all 'fill forms' methods was used before putting data into forms
        addNewProductPage.getGeneralSection().fillFormProductGeneral(generalData);
        addNewProductPage.openTab(NewProductTabs.INFORMATION);
        addNewProductPage.getInformationSection().fillFormProductInformation(informationData);
        addNewProductPage.openTab(NewProductTabs.PRICES);
        addNewProductPage.getPricesSection().fillFormProductPrices(pricesData);
        addNewProductPage.clickButtonSave();
        catalogPage = new CatalogPage(myPersonalDriver);

        Assert.assertTrue(catalogPage.getCatalogTable().
                        getCellsByText((String) generalData.get("name")).size() > 0,
                String.format("Duck '%s' is not found", generalData.get("name")));
    }

    @AfterClass
    public void afterClass() {
        myPersonalDriver.quit();
    }
}
