package org.courses.utils;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class WebDriverSelection {

    private String getDriverNameFromTestProperties() {
        return new Utils().getTestProperties().getProperty("driver");
    }

    private String getRemoteIpFromProperties() {
        return new Utils().getTestProperties().getProperty("remote_url");
    }

    public WebDriver getDriverFromProperties() {
        WebDriver myPersonalDriver = null;
        switch (getDriverNameFromTestProperties().toLowerCase()) {
            case "firefox":
                myPersonalDriver = new FirefoxDriver();
                break;
            case "ie":
                myPersonalDriver = new InternetExplorerDriver();
                break;
            case "chrome":
                myPersonalDriver = new ChromeDriver();
                break;
            case "remote_chrome":
                try {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    myPersonalDriver = new RemoteWebDriver(new URL(getRemoteIpFromProperties()), chromeOptions);
                } catch (MalformedURLException mue) {
                    mue.printStackTrace();
                }
                break;
            case "remote_firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                try {
                    myPersonalDriver = new RemoteWebDriver(new URL(getRemoteIpFromProperties()), firefoxOptions);
                } catch (MalformedURLException mue) {
                    mue.printStackTrace();
                }
                break;
            case "remote_ie":
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                ieOptions.setCapability("ensureCleanSession", true);
                ieOptions.setCapability("ignoreProtectedModeSettings", true);
                ieOptions.setCapability("inforceCreateProcessApi", true);
                try {
                    myPersonalDriver = new RemoteWebDriver(new URL(getRemoteIpFromProperties()), ieOptions);
                } catch (MalformedURLException mue) {
                    mue.printStackTrace();
                }
                break;
            case "remote_ie_win8":
                InternetExplorerOptions ieOpts = new InternetExplorerOptions();
                ieOpts.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                ieOpts.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                ieOpts.setCapability("ensureCleanSession", true);
                ieOpts.setCapability("ignoreProtectedModeSettings", true);
                ieOpts.setCapability("inforceCreateProcessApi", true);
                ieOpts.setCapability("platform", "WINDOWS");
                try {
                    myPersonalDriver = new RemoteWebDriver(new URL(getRemoteIpFromProperties()), ieOpts);
                } catch (MalformedURLException mue) {
                    mue.printStackTrace();
                }
                break;
            case "chrome_saucelab":
                try {

                    MutableCapabilities sauceOpts = new MutableCapabilities();
                    /** In order to use w3c you must set the seleniumVersion **/
                    sauceOpts.setCapability("seleniumVersion", "3.141.59");
                    sauceOpts.setCapability("name", "my name");

                    /**
                     * in this exercise we set additional capabilities below that align with
                     * testing best practices such as tags, timeouts, and build name/numbers.
                     *
                     * Tags are an excellent way to control and filter your test automation
                     * in Sauce Analytics. Get a better view into your test automation.
                     */
                    List<String> tags = Arrays.asList("sauceDemo", "demoTest", "moduleXXX", "javaTest");
                    sauceOpts.setCapability("tags", tags);
                    /** Another of the most important things that you can do to get started
                     * is to set timeout capabilities for Sauce based on your organizations needs. For example:
                     * How long is the whole test allowed to run?*/
                    sauceOpts.setCapability("maxDuration", 3600);
                    /** A Selenium crash might cause a session to hang indefinitely.
                     * Below is the max time allowed to wait for a Selenium command*/
                    sauceOpts.setCapability("commandTimeout", 600);
                    /** How long can the browser wait for a new command */
                    sauceOpts.setCapability("idleTimeout", 1000);

                    /** Setting a build name is one of the most fundamental pieces of running
                     * successful test automation. Builds will gather all of your tests into a single
                     * 'test suite' that you can analyze for results.
                     * It's a best practice to always group your tests into builds. */
                    sauceOpts.setCapability("build", "Onboarding Sample App - Java-TestNG");
                    ChromeOptions chromeOpts = new ChromeOptions();
                    chromeOpts.setExperimentalOption("w3c", true);

                    /** Set a second MutableCapabilities object to pass Sauce Options and Chrome Options **/
                    MutableCapabilities capabilities = new MutableCapabilities();
                    capabilities.setCapability("sauce:options", sauceOpts);
                    capabilities.setCapability("goog:chromeOptions", chromeOpts);
                    capabilities.setCapability("browserName", "chrome");
                    capabilities.setCapability("platformName", "Win10");
                    capabilities.setCapability("browserVersion", "latest");

                    myPersonalDriver = new RemoteWebDriver(new URL(getRemoteIpFromProperties()), capabilities);
                } catch (MalformedURLException mue) {
                    mue.printStackTrace();
                }
                break;
            case "proxy_chrome":
                Proxy proxy = new Proxy();
                proxy.setHttpProxy("localhost:8888");
                /*DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("proxy", proxy);
                myPersonalDriver = new ChromeDriver(caps);*/

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability("proxy", proxy);
                myPersonalDriver = new ChromeDriver(chromeOptions);

                break;
            default:
                Assert.fail("Error! Check your browser's type");
        }
        return myPersonalDriver;
    }
}

