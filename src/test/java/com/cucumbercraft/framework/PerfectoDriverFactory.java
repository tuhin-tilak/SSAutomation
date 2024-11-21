package com.cucumbercraft.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class PerfectoDriverFactory {

    private static Properties mobileProperties;

    private PerfectoDriverFactory() {
        // To prevent external instantiation of this class
    }

    private static URL getUrl(String remoteUrl) {
        URL url = null;
        try {
            url = new URL(remoteUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * Function to return the Perfecto MobileCloud {@link RemoteWebDriver} object
     * based on the parameters passed
     *
     * @param deviceId  The ID of the Perfecto MobileCloud device to be used for the
     *                  test execution
     * @param browser   The {@link Browser} to be used for the test execution
     * @param remoteUrl The Perfecto MobileCloud URL to be used for the test
     *                  execution
     * @return The corresponding {@link RemoteWebDriver} object
     */
    public static WebDriver getPerfectoRemoteWebDriver(String deviceId, Browser browser, String remoteUrl) {
        DesiredCapabilities desiredCapabilities = getPerfectoExecutionCapabilities(browser);
        desiredCapabilities.setCapability("deviceName", deviceId);

        URL url = getUrl(remoteUrl);

        return new RemoteWebDriver(url, desiredCapabilities);
    }

    private static DesiredCapabilities getPerfectoExecutionCapabilities(Browser browser) {
        validatePerfectoSupports(browser);

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(browser.getValue());
        desiredCapabilities.setPlatform(Platform.ANY);
//        desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
        // remote execution

        mobileProperties = Settings.getInstance();
        desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
        desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));

        return desiredCapabilities;
    }

    private static void validatePerfectoSupports(Browser browser) {
        switch (browser) {
            case INTERNET_EXPLORER:
            case FIREFOX:

            default:
                break;
        }
    }

    /**
     * Function to return the Perfecto MobileCloud {@link RemoteWebDriver} object
     * based on the parameters passed
     *
     * @param platformName    The device platform to be used for the test execution
     *                        (iOS, Android, etc.)
     * @param platformVersion The device platform version to be used for the test
     *                        execution
     * @param browser         The {@link Browser} to be used for the test execution
     * @param remoteUrl       The Perfecto MobileCloud URL to be used for the test
     *                        execution
     * @return The corresponding {@link RemoteWebDriver} object
     */
    public static WebDriver getPerfectoRemoteWebDriverByDevicePlatform(String deviceId, String osVersionVersion,
                                                                       Browser browser, String remoteUrl, MobileExecutionPlatform executionPlatform) {
        String platformName = "";
        if (executionPlatform.equals("WEB_ANDROID")) {
            platformName = "Android";
        } else if (executionPlatform.equals("WEB_IOS")) {
            platformName = "ios";
        }
        DesiredCapabilities desiredCapabilities = getPerfectoExecutionCapabilities(browser);
        desiredCapabilities.setBrowserName(browser.getValue());
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("platformVersion", osVersionVersion);

        URL url = getUrl(remoteUrl);

        return new RemoteWebDriver(url, desiredCapabilities);
    }

    /**
     * Function to return the Perfecto MobileCloud {@link RemoteWebDriver} object
     * based on the parameters passed
     *
     * @param manufacturer The manufacturer of the device to be used for the test
     *                     execution (Samsung, Apple, etc.)
     * @param model        The device model to be used for the test execution
     *                     (Galaxy S6, iPad Air, etc.)
     * @param browser      The {@link Browser} to be used for the test execution
     * @param remoteUrl    The Perfecto MobileCloud URL to be used for the test
     *                     execution
     * @return The corresponding {@link RemoteWebDriver} object
     */
    public static WebDriver getPerfectoRemoteWebDriverByDeviceModel(String manufacturer, String model, Browser browser,
                                                                    String remoteUrl) {
        DesiredCapabilities desiredCapabilities = getPerfectoExecutionCapabilities(browser);
        desiredCapabilities.setCapability("manufacturer", manufacturer);
        desiredCapabilities.setCapability("model", model);

        URL url = getUrl(remoteUrl);

        return new RemoteWebDriver(url, desiredCapabilities);
    }

    /**
     * Function to return the object for AppiumDriver {@link AppiumDriver} object
     *
     * @param testParameters
     * @return Instance of the {@link AppiumDriver} object
     */
    @SuppressWarnings("rawtypes")
    public static AppiumDriver getPerfectoAppiumDriver(SeleniumTestParameters testParameters) {

        AppiumDriver driver = null;
        mobileProperties = Settings.getInstance();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
        desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));
        try {
            switch (testParameters.getMobileExecutionPlatform()) {

                case ANDROID:

                    desiredCapabilities.setCapability("platformName", "Android");
                    desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
                    desiredCapabilities.setCapability("appPackage",
                            mobileProperties.getProperty("Application_Package_Name"));
                    desiredCapabilities.setCapability("appActivity",
                            mobileProperties.getProperty("Application_MainActivity_Name"));
                    // desiredCapabilities.setCapability("app",
                    // "PUBLIC:appium/apiDemos.apk");
                    try {
                        driver = new AndroidDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
                                desiredCapabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    break;

                case IOS:

                    desiredCapabilities.setCapability("platformName", "ios");
                    desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
                    desiredCapabilities.setCapability("newCommandTimeout", 120);
                    desiredCapabilities.setCapability("bundleId", mobileProperties.getProperty("PerfecttoIosBundleID"));
                    // desiredCapabilities.setCapability("app",
                    // "PUBLIC:appium/apiDemos.ipa");

                    try {
                        driver = new IOSDriver(new URL(mobileProperties.getProperty("PerfectoHost")), desiredCapabilities);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;

                case WEB_ANDROID:

                    desiredCapabilities.setCapability("platformName", "Android");
                    desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
                    desiredCapabilities.setCapability("browserName", "Chrome");

                    try {
                        driver = new AndroidDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
                                desiredCapabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;

                case WEB_IOS:

                    desiredCapabilities.setCapability("platformName", "ios");
                    desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
                    desiredCapabilities.setCapability("automationName", "Appium");
                    desiredCapabilities.setCapability("browserName", "Safari");

                    try {
                        driver = new IOSDriver(new URL(mobileProperties.getProperty("PerfectoHost")), desiredCapabilities);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;

                default:

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return driver;

    }

    /**
     * Function to return the object for RemoteWebDriver {@link RemoteWebDriver}
     * object
     *
     * @param testParameters
     * @return Instance of the {@link RemoteWebDriver} object
     */
    public static WebDriver getPerfectoRemoteWebDriverForDesktop(SeleniumTestParameters testParameters) {

        mobileProperties = Settings.getInstance();
        RemoteWebDriver driver = null;
        String browserName = getBrowerName(testParameters);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
        desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));
        desiredCapabilities.setCapability("platformName", testParameters.getPlatform());
        desiredCapabilities.setCapability("platformVersion", testParameters.getPlatformVersion());
        desiredCapabilities.setCapability("browserName", browserName);
        desiredCapabilities.setCapability("browserVersion", testParameters.getBrowserVersion());
        desiredCapabilities.setCapability("resolution", mobileProperties.getProperty("Resolution"));
        desiredCapabilities.setCapability("location", mobileProperties.getProperty("Location"));
        desiredCapabilities.setCapability("takesScreenshot", true);
        try {
            driver = new RemoteWebDriver(new URL(mobileProperties.getProperty("PerfectoHost")), desiredCapabilities);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static String getBrowerName(SeleniumTestParameters testParameters) {
        String browserName = null;
        if (testParameters.getBrowser().equals(Browser.CHROME)) {
            browserName = "Chrome";
        } else if (testParameters.getBrowser().equals(Browser.FIREFOX)) {
            browserName = "Firefox";
        } else if (testParameters.getBrowser().equals(Browser.INTERNET_EXPLORER)) {
            browserName = "Internet Explorer";
        } else if (testParameters.getBrowser().equals(Browser.EDGE)) {
            browserName = "Edge";
        }
        return browserName;
    }
}
