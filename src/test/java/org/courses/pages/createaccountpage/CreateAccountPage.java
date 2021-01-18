package org.courses.pages.createaccountpage;

import org.courses.pages.common.CheckBox;
import org.courses.pages.common.ComboBox;
import org.courses.pages.createaccountpage.data.AccountFormData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage {


    private WebDriver driverHere;

    @FindBy(css = "h1.title")
    private WebElement pageHeader;

    @FindBy(css = "[name='tax_id']")
    private WebElement taxID;

    @FindBy(css = "[name='company']")
    private WebElement company;

    @FindBy(css = "[name='firstname']")
    private WebElement firstName;

    @FindBy(css = "[name='lastname']")
    private WebElement lastName;

    @FindBy(css = "[name='address1']")
    private WebElement address1;

    @FindBy(css = "[name='address2']")
    private WebElement address2;

    @FindBy(css = "[name='postcode']")
    private WebElement postCode;

    @FindBy(css = "[name='city']")
    private WebElement city;

    @FindBy(css = "[name='email']")
    private WebElement email;

    @FindBy(css = "[name='phone']")
    private WebElement phone;

    @FindBy(css = "[name='password']")
    private WebElement password;

    @FindBy(css = "[name='confirmed_password']")
    private WebElement passwordConfirmed;

    @FindBy(css = "[name='create_account']")
    private WebElement createAccountButton;

    public CreateAccountPage(WebDriver mePersonalDriver) {
        this.driverHere = mePersonalDriver;
        PageFactory.initElements(driverHere, this);
    }


    public CheckBox getCheckBoxSubscribe() {
        return new CheckBox(driverHere, By.cssSelector("input[name='newsletter']"));
    };

    public ComboBox getCountryComboBox() {
            return new ComboBox(driverHere,
            By.xpath("//td[contains(.,'Country')]//*[@role='combobox']"),
            By.cssSelector(".select2-selection__arrow"),
            By.cssSelector("input.select2-search__field"),
            "//li[contains(@id,'country_code')][.='%s']");
    }

   public Select getDropDown() {
        return new Select(driverHere.findElement(By.cssSelector("select[name='zone_code']")));
   }

    public void fillUsersForm(AccountFormData accountFormData) {

        Wait<WebDriver> wait = new WebDriverWait(driverHere, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name='tax_id']")));

        accountFormData.getData().entrySet().stream().forEach(data -> {
            String dataValue = data.getValue();
            if (!dataValue.isEmpty())
            switch (data.getKey()) {
                case "taxID":
                    taxID.click();
                    taxID.sendKeys(dataValue);
                    break;
                case "company":
                    company.sendKeys(dataValue);
                    break;
                case "firstName":
                    firstName.sendKeys(dataValue);
                    break;
                case "lastName":
                    lastName.sendKeys(dataValue);
                    break;
                case "address1":
                    address1.sendKeys(dataValue);
                    break;
                case "address2":
                    address2.sendKeys(dataValue);
                    break;
                case "postCode":
                    postCode.sendKeys(dataValue);
                    break;
                case "city":
                    city.sendKeys(dataValue);
                    break;
                case "country":
                    getCountryComboBox().click();
                    getCountryComboBox().putDataToComboBox(dataValue);
                    break;
                case "stateZoneProvince":
                    getDropDown().selectByVisibleText(dataValue);
                    break;
                case "email":
                    email.sendKeys(dataValue);
                    break;
                case "phone":
                    phone.sendKeys(dataValue);
                    break;
                case "subscribe":
                    if (dataValue.equals("checked"))
                        getCheckBoxSubscribe().selectCheckBox();
                    else
                        getCheckBoxSubscribe().unSelectCheckBox();
                    break;
                case "password":
                    password.sendKeys(dataValue);
                    break;
                case "passwordConfirmed":
                    passwordConfirmed.sendKeys(dataValue);
                    break;
                default:
                    System.out.println(String.format("The data '%s' : '%s' does not exist",
                            data.getKey(), data.getValue()));
            }

            createAccountButton.click();
        });
    }
}
