package tests;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TextBoxPage;
import utilities.QaEnvProp;
import utilities.TestDataReader;

import java.awt.*;

public class AmazonTest extends BaseTest {

//    @Test
//    public void textBox() {
//        String url = QaEnvProp.getValue("url");
//        JSONObject testData = TestDataReader.getTestData("textBox");
//        driver.get(url + "/input.xhtml");
//        TextBoxPage textBoxPage = new TextBoxPage(driver);
//        textBoxPage.inputBox.sendKeys((String) testData.get("input_user"));
//        textBoxPage.inputBox1.sendKeys((String) testData.get("country"));
//    }

    @Test
    public void textBox1() {
        String url = QaEnvProp.getValue("url");
        JSONObject testData = TestDataReader.getTestData("textBox");
        driver.get(url + "/input.xhtml");
//        driver.findElement(By.id("j_idt88:name")).click();
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.element.click();
    }
}
