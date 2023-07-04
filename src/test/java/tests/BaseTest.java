package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import utilities.DriverManager;
import utilities.QaEnvProp;
import utilities.TestDataReader;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        DriverManager.init();
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//0 - 10
        QaEnvProp.init();
        TestDataReader.init();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                {"testuser", "test@123"},
                {"testadmin", "test@123"}
        };
    }
}

// Listeners