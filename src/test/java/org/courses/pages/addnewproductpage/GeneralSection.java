package org.courses.pages.addnewproductpage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class GeneralSection {

    private WebDriver driverHere;
    private String statusEnabledDisabledRadioTemplateXpath = "//label[contains(.,'%s')]/input";
    private By nameFieldBy = By.cssSelector("input[name^='name']");
    private By codeFieldBy = By.cssSelector("input[name='code']");
    private String categoriesCheckBoxTemplateCSS = "input[data-name='%s']";
    private By defaultCategorySelectionBy = By.cssSelector("select[name='default_category_id']");
    private String productGroupCheckBoxTemplateXpath = "//tr[./td[.='%s']]//input";
    private By quantityFieldBy = By.cssSelector("input[name='quantity']");
    private By quantityUnitSelectionBy = By.cssSelector("select[name='quantity_unit_id']");
    private By deliveryStatusSelectionBy = By.cssSelector("select[name='delivery_status_id']");
    private By soldOutStatusSelectionBy = By.cssSelector("select[name='sold_out_status_id']");
    private By uploadImagesFieldBy = By.cssSelector("input[name^='new_images']");
    private By addImageButtonBy = By.cssSelector("a#add-new-image");
    private By dataValidFromCalendarBy = By.cssSelector("input[name='date_valid_from']");
    private By dataValidToCalendarBy = By.cssSelector("input[name='date_valid_to']");


    public GeneralSection(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
    }

    public Select getDropDown(By locatorBy) {
        return new Select(driverHere.findElement(locatorBy));
    }

    public void fillFormProductGeneral(LinkedHashMap<String, Object> generalData) {

        Wait<WebDriver> wait = new WebDriverWait(driverHere, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dataValidToCalendarBy));

        generalData.entrySet().forEach(data -> {
            Object dataValue = data.getValue();

            if (((dataValue instanceof String) && !((String) dataValue).isEmpty()) ||
                    ((dataValue instanceof String[]) && ((String[]) dataValue).length != 0))

                switch (data.getKey()) {
                    case "status":
                        driverHere.findElement(By.xpath(
                                String.format(statusEnabledDisabledRadioTemplateXpath, (String) dataValue))).
                                click();
                        break;
                    case "name":
                        driverHere.findElement(nameFieldBy).sendKeys((String) dataValue);
                        break;
                    case "code":
                        driverHere.findElement(codeFieldBy).sendKeys((String) dataValue);
                        break;
                    case "categories":
                        //Clear from "a very beginning"
                        driverHere.findElement(By.cssSelector(
                                String.format(categoriesCheckBoxTemplateCSS, "Root"))).click();
                        //Then select data from json
                        Arrays.stream(((String[]) dataValue)).forEach(
                                c -> driverHere.findElement(By.cssSelector(
                                        String.format(categoriesCheckBoxTemplateCSS, c))).click());
                        break;
                    case "defaultCategory":
                        getDropDown(defaultCategorySelectionBy).selectByVisibleText((String) dataValue);
                        break;
                    case "productGroups":
                        Arrays.stream(((String[]) dataValue)).forEach(
                                g -> driverHere.findElement(By.xpath(
                                        String.format(productGroupCheckBoxTemplateXpath, g))).click());
                        break;
                    case "quantity":
                        driverHere.findElement(quantityFieldBy).clear();
                        driverHere.findElement(quantityFieldBy).sendKeys((String) dataValue);
                        break;
                    case "quantityUnit":
                        getDropDown(quantityUnitSelectionBy).selectByVisibleText((String) dataValue);
                        break;
                    case "deliveryStatus":
                        getDropDown(deliveryStatusSelectionBy).selectByVisibleText((String) dataValue);
                        break;
                    case "soldOutStatus":
                        getDropDown(soldOutStatusSelectionBy).selectByVisibleText((String) dataValue);
                        break;
                    case "uploadImages":
                        Arrays.stream(((String[]) dataValue)).forEach(f ->
                        {
                            WebElement we = driverHere.findElements(uploadImagesFieldBy).stream().filter(w ->
                                    w.getAttribute("value").isEmpty()).collect(Collectors.toList()).get(0);
                            ClassLoader classLoader = getClass().getClassLoader();
                            String absolutePath = new File(Objects.requireNonNull(classLoader.getResource(f)).
                                    getFile()).getAbsoluteFile().getAbsolutePath();
                            we.sendKeys(absolutePath);
                            driverHere.findElement(addImageButtonBy).click();
                        });
                        break;
                    case "dateValidFrom":
                        driverHere.findElement(dataValidFromCalendarBy).sendKeys((String) dataValue);
                        break;
                    case "dateValidTo":
                        driverHere.findElement(dataValidToCalendarBy).sendKeys((String) dataValue);
                        break;
                    default:
                        System.out.println(String.format("The data '%s' : '%s' does not exist",
                                data.getKey(), data.getValue()));
                }
        });
    }
}

