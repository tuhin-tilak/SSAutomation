package com.cucumbercraft.framework;

import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Class to encapsulate utility functions of the framework
 *
 * @author Cognizant
 */
public class Util extends MasterStepDefs {


    private Util() {
        // To prevent external instantiation of this class
    }

    /**
     * Function to get the separator string to be used for directories and files
     * based on the current OS
     *
     * @return The file separator string
     */
    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    /**
     * Function to get the Absolute Path
     *
     * @return The AbsolutePath in String
     */
    public static String getAbsolutePath() {
        String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
        return relativePath;
    }

    /**
     * Function to get the Result Path
     *
     * @return The ResultPath in String
     */
    public static String getResultsPath() {
        File path = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "Results");
        if (!path.isDirectory()) {
            path.mkdirs();
        }

        return path.toString();
    }

    /**
     * Function to get the Target Path
     *
     * @return The TargetPath in String
     */
    public static String getTargetPath() {

        File targetPath = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "target" + Util.getFileSeparator()
                + "cucumber-report");

        return targetPath.toString();
    }

    /**
     * Function to get the Extent Report Path
     *
     * @return The Extent Report Path in String
     */
    public static String getTargetExtentReportPath() {

        File targetPath = new File(
                Util.getAbsolutePath() + Util.getFileSeparator() + "target" + Util.getFileSeparator() + "ExtentReport");

        return targetPath.toString();
    }

    /**
     * Function to get the Allure Report Path
     *
     * @return The Allure Report Path in String
     */
    public static String getTargetAllureReportPath() {

        File targetPath = new File(
                Util.getAbsolutePath() + Util.getFileSeparator() + "target" + Util.getFileSeparator() + "site");

        return targetPath.toString();
    }

    /**
     * Function to take screenShot for WebDriver {@link WebDriver}
     *
     * @return The Byte[]
     */
    public static byte[] takeScreenshot(WebDriver driver) {
        if (driver == null) {
            throw new RuntimeException("Report.driver is not initialized!");
        }

        if (driver.getClass().getSimpleName().equals("HtmlUnitDriver") || driver.getClass().getGenericSuperclass()
                .toString().equals("class org.openqa.selenium.htmlunit.HtmlUnitDriver")) {
            return null; // Screenshots not supported in headless mode
        }

        if (driver.getClass().getSimpleName().equals("RemoteWebDriver")) {
            Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
            if (capabilities.getBrowserName().equals("htmlunit")) {
                return null; // Screenshots not supported in headless mode
            }
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
        } else {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
    }

    /**
     * Function to take screenShot for WebDriver {@link WebDriver}
     *
     * @return The screenshotFile Path as String
     */
    public static String takeScreenshotFile(WebDriver driver) {

        String screenShotPath = null;
        try {
            if (driver == null) {
                throw new RuntimeException("Report.driver is not initialized!");
            }

            WebDriver augmentedDriver = new Augmenter().augment(driver);
            File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

            screenShotPath = copyFile(scrFile).toString();
        } catch (Exception e) {

        }
        return screenShotPath;
    }

    private static File copyFile(File scrFile) {

        File newPath = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "Screenshots");
        if (!newPath.isDirectory()) {
            newPath.mkdir();
        }

        File screenShotPath = new File(
                newPath + Util.getFileSeparator() + currentScenario.getName() + ".png");
        try {
            FileUtils.copyFile(scrFile, new File(screenShotPath.toString()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenShotPath;
    }

    /**
     * Function to return the current time
     *
     * @return The current time
     * @see #getCurrentFormattedTime(String)
     */
    public static Date getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * Function to return the current time, formatted as per the
     * DateFormatString setting
     *
     * @param dateFormatString The date format string to be applied
     * @return The current time, formatted as per the date format string
     * specified
     * @see #getCurrentTime()
     * @see #getFormattedTime(Date, String)
     */
    public static String getCurrentFormattedTime(String dateFormatString) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Function to format the given time variable as specified by the
     * DateFormatString setting
     *
     * @param time             The date/time variable to be formatted
     * @param dateFormatString The date format string to be applied
     * @return The specified date/time, formatted as per the date format string
     * specified
     * @see #getCurrentFormattedTime(String)
     */
    public static String getFormattedTime(Date time, String dateFormatString) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        return dateFormat.format(time);
    }

    /**
     * Function to get the time difference between 2 {@link Date} variables in
     * minutes/seconds format
     *
     * @param startTime The start time
     * @param endTime   The end time
     * @return The time difference in terms of hours, minutes and seconds
     */
    public static String getTimeDifference(Date startTime, Date endTime) {
        long timeDifferenceSeconds = (endTime.getTime() - startTime.getTime()) / 1000; // to
        // convert
        // from
        // milliseconds
        // to
        // seconds
        long timeDifferenceMinutes = timeDifferenceSeconds / 60;

        String timeDifferenceDetailed;
        if (timeDifferenceMinutes >= 60) {
            long timeDifferenceHours = timeDifferenceMinutes / 60;

            timeDifferenceDetailed = timeDifferenceHours + " hour(s), "
                    + timeDifferenceMinutes % 60 + " minute(s), "
                    + timeDifferenceSeconds % 60 + " second(s)";
        } else {
            timeDifferenceDetailed = timeDifferenceMinutes + " minute(s), "
                    + timeDifferenceSeconds % 60 + " second(s)";
        }

        return timeDifferenceDetailed;
    }

    public static String takeScreenshotasFile(WebDriver driver) throws IOException {
        if (driver == null) {
            throw new RuntimeException("Report.driver is not initialized!");
        }

        if (driver.getClass().getSimpleName().equals("HtmlUnitDriver") || driver.getClass().getGenericSuperclass()
                .toString().equals("class org.openqa.selenium.htmlunit.HtmlUnitDriver")) {
            return null; // Screenshots not supported in headless mode
        }

        if (driver.getClass().getSimpleName().equals("RemoteWebDriver")) {
            Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
            if (capabilities.getBrowserName().equals("htmlunit")) {
                return null; // Screenshots not supported in headless mode
            }
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

            File newPath = new File(Util.getTargetExtentReportPath() + Util.getFileSeparator() + "Screenshots");
            if (!newPath.isDirectory()) {
                newPath.mkdir();
            }

            File screenShotPath = new File(
                    newPath + Util.getFileSeparator() + RandomStringUtils.randomAlphanumeric(16) + ".png");
            try {
                FileUtils.copyFile(scrFile, new File(screenShotPath.toString()), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Screenshot taken successfully");
            return String.valueOf(screenShotPath);
        } else {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String encodedBase64 = null;
            FileInputStream fileInputStreamReader = null;
            fileInputStreamReader = new FileInputStream(scrFile);
            byte[] bytes = new byte[(int) scrFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
            File newPath = new File(Util.getTargetExtentReportPath() + Util.getFileSeparator() + "Screenshots");
            if (!newPath.isDirectory()) {
                newPath.mkdir();
            }


            File screenShotPath = new File(

                    newPath + Util.getFileSeparator() + currentScenario.getId() + RandomStringUtils.randomAlphanumeric(2) + ".png");

//
//			File screenShotPath = new File(
//					"C:\\" +  RandomStringUtils.randomAlphanumeric(16) + ".png");

            try {
                FileUtils.copyFile(scrFile, new File(screenShotPath.toString()), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("**********Screenshot taken successfully*************");

            return "data:image/png;base64," + encodedBase64;

        }

    }


}