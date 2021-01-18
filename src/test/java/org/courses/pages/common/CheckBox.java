package org.courses.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBox {
    private WebDriver driverHere;
    private By checkBoxBy;
    public CheckBox(WebDriver myPersonalDriver, By checkBoxBy) {
        this.driverHere = myPersonalDriver;
        this.checkBoxBy = checkBoxBy;
    }

    public void selectCheckBox() {
        WebElement we = driverHere.findElement(checkBoxBy);
        if (!we.isSelected()) {
            we.click();
        }
    }

    public void unSelectCheckBox() {
        WebElement we = driverHere.findElement(checkBoxBy);
        if (we.isSelected()) {
            we.click();
        }
    }
}
