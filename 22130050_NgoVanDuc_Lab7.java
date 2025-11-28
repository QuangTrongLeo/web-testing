package com.example.TS07UpdateProfile;

import java.time.Duration;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class 22130050_NgoVanDuc_Lab7 {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void runAllTests() throws Exception {
        // ======================
        // TC01-TC04: Update Profile
        // ======================
        driver.get("https://cms.anhtester.com/");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.absolute-full.bg-black.opacity-60")
        ));

        driver.findElement(By.linkText("Login")).click();
        pause(1);

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("duc2k42603@gmail.com");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("ducvan263");
        pause(1);

        driver.findElement(By.cssSelector(".form-default")).submit();
        pause(2);

        System.out.println("Login complete");

        // TC02: Update Name
        driver.get("https://cms.anhtester.com/profile");
        pause(2);

        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("name")));
        js.executeScript("arguments[0].scrollIntoView(true);", nameField);
        nameField.clear();
        pause(1);
        nameField.sendKeys("Nguyen Van Duc Updated");
        System.out.println("TC02 complete");

        // TC03: Update Name + Phone
        nameField.clear();
        pause(1);
        nameField.sendKeys("vduc");

        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("phone")));
        js.executeScript("arguments[0].scrollIntoView(true);", phoneField);
        phoneField.clear();
        pause(1);
        phoneField.sendKeys("0293848586");

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Confirm Password'])[1]/following::button[1]")
        ));
        saveButton.click();
        pause(2);
        System.out.println("TC03 complete");

        // TC04: Update Email
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        js.executeScript("arguments[0].scrollIntoView(true);", emailField);
        emailField.clear();
        pause(1);
        emailField.sendKeys("@@gmail.com");

        WebElement verifyButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Verify'])[1]/following::button[1]")
        ));
        verifyButton.click();
        pause(2);

        System.out.println("TC04 complete");

        // ======================
        // TC01 Support Ticket
        // ======================
        driver.get("https://cms.anhtester.com/"); // reload để vào My Panel
        pause(2);

        driver.findElement(By.linkText("My Panel")).click();
        pause(1);

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Compare'])[2]/following::span[1]")).click();
        pause(1);

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Support Ticket'])[2]/following::div[3]")).click();
        pause(1);

        driver.findElement(By.xpath("//div[@id='ticket_modal']/div/div/div[2]/form/div[4]/button")).click();
        System.out.println("TC01 Support Ticket complete");

        // ======================
        // TC02 Support Ticket
        // ======================
        driver.get("https://cms.anhtester.com/support_ticket");
        pause(1);

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Support Ticket'])[2]/following::div[3]")).click();
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ticket_modal")));
        WebElement subjectField = wait.until(ExpectedConditions.elementToBeClickable(By.name("subject")));
        js.executeScript("arguments[0].scrollIntoView(true);", subjectField);
        pause(1);
        subjectField.clear();
        subjectField.sendKeys("Vấn đề đơn hàng x");

        driver.findElement(By.name("details")).clear();
        driver.findElement(By.name("details")).sendKeys("khách hàng không nhận được hàng");
        driver.findElement(By.xpath("//div[@id='ticket_modal']/div/div/div[2]/form/div[4]/button[2]")).click();
        System.out.println("TC02 Support Ticket complete");

        // ======================
        // TC03 Support Ticket
        // ======================
        driver.get("https://cms.anhtester.com/support_ticket");
        pause(1);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Support Ticket'])[2]/following::div[3]")).click();
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='ticket_modal']//div[2]/form/div[4]/button[2]")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();
        System.out.println("TC03 Support Ticket complete");

        // ======================
        // TC04 Support Ticket
        // ======================
        driver.get("https://cms.anhtester.com/support_ticket");
        pause(1);
        driver.findElement(By.linkText("View Details")).click();
        System.out.println("TC04 Support Ticket complete");

        // Logout at the end
        driver.findElement(By.linkText("Logout")).click();
        System.out.println("All tests complete, logged out");
    }

    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

}
