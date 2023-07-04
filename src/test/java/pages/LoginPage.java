package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    //Page Object Model

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, LoginPage.class);
        this.driver = driver;
    }

    // driver.findElement(By.id("j_idt88:name")).click();
    @FindBy(id = "j_idt88:name")
    public WebElement username;

    @FindBy(id = "j_idt88:name")
    public WebElement password;

    public void login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
    }

}
