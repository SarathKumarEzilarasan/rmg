package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TextBoxPage {
    // POM -> Page Object Model --> Page Factory Model

    // driver.findElement(By.id("j_idt88:name")).sendKeys("test user");

    public TextBoxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }





    @FindBy(id = "j_idt88:name")
    public WebElement inputBox;

    @FindBy(xpath = "//input[@value='Chennai']")
    public WebElement inputBox1;


}
