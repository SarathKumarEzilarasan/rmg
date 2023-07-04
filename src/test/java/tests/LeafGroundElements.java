package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Test;
import pages.TextBoxPage;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class LeafGroundElements extends BaseTest {

    @Test
    public void textBox() throws InterruptedException {
        driver.get("https://www.leafground.com/input.xhtml");
        TextBoxPage textBoxPage = new TextBoxPage(driver);
        textBoxPage.inputBox.sendKeys("test user");
        driver.findElement(By.xpath("//input[@value='Chennai']")).sendKeys(" India");
        String disabled = driver.findElement(By.id("j_idt88:j_idt93")).getAttribute("disabled");
        Assert.assertTrue(disabled.equalsIgnoreCase("true"));
        driver.findElement(By.id("j_idt88:j_idt95")).clear();
        driver.findElement(By.id("j_idt106:auto-complete_input")).sendKeys("test");
        Thread.sleep(2000); // java wait statement
        List<WebElement> list = driver.findElements(
                By.xpath("//span[@id='j_idt106:auto-complete_panel']//li"));
        list.get(2).click();
    }

    @Test(attributes = {})
    public void button() {
        driver.get("https://www.leafground.com/button.xhtml");
        driver.findElement(By.xpath("//span[@class=\"ui-button-text ui-c\"]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.leafground.com/dashboard.xhtml"));
        driver.get("https://www.leafground.com/button.xhtml");
        Point point = driver.findElement(By.id("j_idt88:j_idt94")).getLocation();
        Assert.assertEquals(point.getX(), 81);
        Assert.assertEquals(point.getY(), 400);
        Dimension dimension = driver.findElement(By.id("j_idt88:j_idt98")).getSize();
        Assert.assertEquals(dimension.getHeight(), 34);
        Assert.assertEquals(dimension.getWidth(), 87);
        List<WebElement> list = driver.findElements(
                By.xpath("//button[contains(@class,\"rounded-button\")]"));
        Assert.assertEquals(list.size(), 4);
    }

    @Test
    public void dropDown() throws InterruptedException {
        driver.get("https://www.leafground.com/select.xhtml");
        Select select = new Select(driver.findElement(
                By.xpath("//select[@class=\"ui-selectonemenu\"]")));
        select.selectByIndex(3);
        select.selectByVisibleText("Playwright");
        driver.findElement(By.id("j_idt87:country_label")).click();
        driver.findElement(By.id("j_idt87:country_3")).click();
        List<String> expectedCities = Arrays.asList("Bengaluru", "Chennai", "Delhi");
        Thread.sleep(2000);
        driver.findElement(By.id("j_idt87:city_label")).click();
        Thread.sleep(2000);
        List<WebElement> actualCities = driver.findElements(
                By.xpath("//ul[@id=\"j_idt87:city_items\"]//li"));
        for (int i = 0; i < expectedCities.size(); i++) {
            Assert.assertEquals(actualCities.get(i + 1).getText(), expectedCities.get(i));
        }
    }

    @Test
    public void checkbox() throws InterruptedException {
        driver.get("https://www.leafground.com/checkbox.xhtml");
        List<WebElement> checkboxes = driver.findElements(By.xpath
                ("//span[@class=\"ui-chkbox-icon ui-icon ui-icon-blank ui-c\"]//parent::div"));
        checkboxes.get(0).click();
        checkboxes.get(1).click();
        Thread.sleep(2000);
        String actual = driver.findElement(By.xpath("//span[@class=\"ui-growl-title\"]")).getText();
        Assert.assertEquals(actual, "Checked");
        checkboxes.get(4).click();
    }

    @Test
    public void radioButton() {
        driver.get("https://www.leafground.com/radio.xhtml");
        List<WebElement> radioButtons = driver.findElements(By.xpath(
                "//table[@id=\"j_idt87:console2\"]" +
                        "//div[starts-with(@class,\"ui-radiobutton-box ui-widget ui-corner-all\")]"));
        int index = -1;
        for (int i = 0; i < radioButtons.size(); i++) {
            WebElement element = radioButtons.get(i);
            if (element.isSelected()) {
                index = i;
                break;
            }
        }
        List<WebElement> browserNames = driver.findElements
                (By.xpath("//table[@id=\"j_idt87:console2\"]//label"));
        if (index >= 0)
            Assert.assertEquals(browserNames.get(index).getText(), "Safari");
        else
            System.out.println("No browser is selected by default");

        int age = 50;

        List<WebElement> ageGroup = driver.findElements(By.xpath("//div[@id=\"j_idt87:age\"]//div[starts-with" +
                "(@class,\"ui-radiobutton-box ui-widget ui-corner-all ui-state-default\")]"));

        List<WebElement> ageGroupValues = driver.findElements(By.xpath(
                "//div[@id=\"j_idt87:age\"]//label"));

        List<Integer> values = new ArrayList<>();

        for (WebElement element : ageGroupValues) {
            String val = element.getText().replace(" Years", "");
            String[] str = val.split("-");
            values.add(Integer.parseInt(str[0]));
            values.add(Integer.parseInt(str[1]));
        }

        if (age >= values.get(0) && age <= values.get(1)) {
            ageGroup.get(0).click();
        } else if (age >= values.get(2) && age <= values.get(3)) {

        } else if (age >= values.get(4) && age <= values.get(5)) {
            ageGroup.get(2).click();
        }
    }

    @Test
    public void waits() {
        driver.get("https://www.leafground.com/waits.xhtml");
        waitAndClick(driver, By.id("j_idt87:j_idt89"), 20);
        String text = driver.findElement(By.id("j_idt87:j_idt90")).getText();
        Assert.assertEquals(text, "I am here");

    }

    @Test
    public void table() throws InterruptedException {
        driver.get("https://www.leafground.com/table.xhtml");
        driver.findElement(By.id("form:j_idt89:globalFilter"))
                .sendKeys("India");
        Thread.sleep(2000);
        WebElement tableBody = driver.findElement(By.id("form:j_idt89_data"));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        Assert.assertTrue(rows.size() >= 1);

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            for (WebElement column : columns) {
                System.out.println(column.getText());
            }
        }
    }

    @Test
    public void grid() {
        //search for bamboo
        //assert 1 record is present
        //add bamboo clock
        //assert for Product Added message
        //assert for 2 records
        //select any product delete it
        //assert for 1 record
    }

    @Test
    public void calendar() {
        driver.get("https://www.leafground.com/calendar.xhtml");
        LocalDate currentDate = LocalDate.now();
        LocalDate eventDate = currentDate.plusDays(2);

        WebElement table = driver.findElement(
                By.xpath("//table[@class=\"fc-scrollgrid-sync-table\"]"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            for (WebElement column : columns) {
                if (column.getAttribute("data-date").equalsIgnoreCase(eventDate.toString())) {
                    column.click();

                }
            }
        }
    }

    @Test
    public void alert() {
        driver.get("https://www.leafground.com/alert.xhtml");
        driver.findElement(By.id("j_idt88:j_idt91")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.id("j_idt88:j_idt104")).click();
        driver.switchTo().alert().sendKeys("hello world");
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void frame() {
        driver.get("https://www.leafground.com/frame.xhtml");
        WebElement frame = driver.findElement(By.xpath("//iframe[@src=\"default.xhtml\"]"));
        driver.switchTo().frame(frame);
        driver.findElement(By.id("Click")).click();
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//div[@class=\"card\"]//h5")).getText();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src=\"page.xhtml\"]")));
        driver.switchTo().frame(driver.findElement(By.id("frame2")));
        driver.findElement(By.id("Click")).click();
        driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//div[@class=\"card\"]//h5")).getText();
    }

    @Test
    public void window() {
        driver.get("https://www.leafground.com/window.xhtml");
        driver.findElement(By.id("j_idt88:new")).click();
        Set<String> handles = driver.getWindowHandles();
        ArrayList<String> list = new ArrayList<>(handles);
        driver.switchTo().window(list.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.leafground.com/dashboard.xhtml");
        driver.close();
        driver.switchTo().window(list.get(0));
    }

    @Test
    public void drag() {
        driver.get("https://www.leafground.com/drag.xhtml");
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.id("form:conpnl_header"));
        actions.dragAndDropBy(element, 100, 200).perform();
        actions.moveToElement(element).keyDown(Keys.CONTROL).keyDown("C").click().release().perform();
    }

    @Test
    public void test() {
        //Javascript executor
        driver.get("https://www.leafground.com/button.xhtml");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.id("j_idt88:j_idt90")));//element.click();
    }


    public void waitAndClick(WebDriver driver, By by, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void waitAndSendKeys(WebDriver driver, By by, long timeOut, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).sendKeys(text);
    }

    @Test(attributes = {@CustomAttribute(name = "hello", values = "smoke")})
//    @CustomAttribute(name = "hello", values = "smoke")
    public void test1() {
        System.out.println();
    }

    public static void main(String[] args) {

    }
}
