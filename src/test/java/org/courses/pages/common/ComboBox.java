package org.courses.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ComboBox {
    private WebDriver driverHere;
    private By comboBoxRootBy;
    private By comboBoxArrowBy;
    private By comboBoxInputBy;
    private String selectionTemplateXpath;

    public ComboBox(WebDriver myPersonalDriver, By comboBoxRootBy, By comboBoxArrowBy, By comboBoxInputBy,
                    String selectionTemplateXpath) {
        this.driverHere = myPersonalDriver;
        this.comboBoxRootBy = comboBoxRootBy;
        this.comboBoxArrowBy = comboBoxArrowBy;
        this.comboBoxInputBy = comboBoxInputBy;
        this. selectionTemplateXpath = selectionTemplateXpath;
    }

    public void click() {
        driverHere.findElement(comboBoxRootBy).findElement(comboBoxArrowBy).click();
    }

    public void select(String optionToSelect) {
        driverHere.findElement(By.xpath(String.format(selectionTemplateXpath, optionToSelect))).click();
    }

    public void putDataToComboBox(String data) {
        driverHere.findElement(comboBoxInputBy).sendKeys(data + Keys.ENTER);
    }
}
