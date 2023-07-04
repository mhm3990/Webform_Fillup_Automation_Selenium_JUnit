import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class webFormFillUp {
    WebDriver driver;
    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @DisplayName("Fill-up and Submit the web-form by visiting the website and assert the expected message")
    @Test
    public void webFormFillUp() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        driver.findElement(By.id("onetrust-reject-all-handler")).click();

        driver.findElement(By.id("edit-name")).sendKeys("Mahmudul Mahadi");
        driver.findElement(By.id("edit-number")).sendKeys("01937028787");
        driver.findElement(By.xpath("//label[contains(text(),'20-30')]")).click();
        driver.findElement(By.id("edit-date")).click();
        DateFormat todayDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date currentDate = new Date();
        String todayDate = todayDateFormat.format(currentDate);
        driver.findElement(By.id("edit-date")).sendKeys(todayDate, Keys.ENTER);
        driver.findElement(By.id("edit-email")).sendKeys("mahmudulmahadi7@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("Hello! " +
                "I have completed my B.Sc. in CSE from Jahangirnagar University and currently doing M.Sc. in CSE from Jahangirnagar University. " +
                "My aim is to become a " + "Fullstack SQA Engineer.");

        WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
        //uploadElement.sendKeys("F:\\SDET\\Class 11 (Java Class 11)\\Java-Class-1-Basic.pptx");
        File document = new File(".\\src\\test\\resources\\Java-Class-1-Basic.pptx");
        uploadElement.sendKeys(document.getAbsolutePath());

        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();

        driver.switchTo().alert().accept();

        String actual_message = driver.findElement(By.className("page-title")).getText();
        String expected_message = "Thank you for your submission!";
        Assertions.assertEquals(actual_message,expected_message);

    }
    @AfterAll
    public void closeDriver() {
        driver.quit();

    }
}