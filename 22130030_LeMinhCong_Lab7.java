package com.example.ModuleSignUp;

import java.time.Duration;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CombinedAllTests {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        // driver.manage().window().maximize(); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
    }

    // ==========================================
    // MODULE: SIGN UP (ĐĂNG KÝ)
    // ==========================================

    @Test
    public void testSIGNUPCREATEUSERSUCCESS01() throws Exception {
        driver.get("https://cms.anhtester.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        closeOverlay(wait);
        
        clickRegistration(wait);
        driver.findElement(By.cssSelector("[name=\"name\"]")).sendKeys("cong");
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys("lmc@gmail.com");
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys("123456");
        driver.findElement(By.cssSelector("[name=\"password_confirmation\"]")).sendKeys("123456");
        clickTermsAndSubmit(driver, wait);
    }

    @Test
    public void testSIGNUPCREATEFAILMISSINGNAME02() throws Exception {
        driver.get("https://cms.anhtester.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        closeOverlay(wait);
        clickRegistration(wait);
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys("ct@gmail.com");
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys("123456");
        driver.findElement(By.cssSelector("[name=\"password_confirmation\"]")).sendKeys("123456");
        clickTermsAndSubmit(driver, wait);
    }

    @Test
    public void testSIGNUPCREATEFAILEMAILEXISTS03() throws Exception {
        driver.get("https://cms.anhtester.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        closeOverlay(wait);
        clickRegistration(wait);
        driver.findElement(By.cssSelector("[name=\"name\"]")).sendKeys("cong");
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys("tc@gmail.com");
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys("123456");
        driver.findElement(By.cssSelector("[name=\"password_confirmation\"]")).sendKeys("123456");
        clickTermsAndSubmit(driver, wait);
    }

    @Test
    public void testSIGNUPCREATEFAILPASSWORDNOTMATCH04() throws Exception {
        driver.get("https://cms.anhtester.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        closeOverlay(wait);
        clickRegistration(wait);
        driver.findElement(By.cssSelector("[name=\"name\"]")).sendKeys("cong");
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys("ct@gmail.com");
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys("123456");
        driver.findElement(By.cssSelector("[name=\"password_confirmation\"]")).sendKeys("4567890");
        clickTermsAndSubmit(driver, wait);
    }

    @Test
    public void testSIGNUPCREATEFAILWEAKPASSWORD05() throws Exception {
        driver.get("https://cms.anhtester.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        closeOverlay(wait);
        clickRegistration(wait);
        driver.findElement(By.cssSelector("[name=\"name\"]")).sendKeys("cong");
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys("ct@gmail.com");
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys("123");
        driver.findElement(By.cssSelector("[name=\"password_confirmation\"]")).sendKeys("123");
        clickTermsAndSubmit(driver, wait);
    }

    @Test
    public void testSIGNUPCREATEFAILUNCHECKTERMS06() throws Exception {
        driver.get("https://cms.anhtester.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        closeOverlay(wait);
        clickRegistration(wait);
        driver.findElement(By.cssSelector("[name=\"name\"]")).sendKeys("cong");
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys("ct@gmail.com");
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys("123456");
        driver.findElement(By.cssSelector("[name=\"password_confirmation\"]")).sendKeys("123456");
        
        WebElement submitBtn = driver.findElement(By.xpath("//form[@id='reg-form']/div[6]/button"));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", submitBtn);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
            submitBtn.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", submitBtn);
        }
    }

    // ==========================================
    // MODULE: WISHLIST (YÊU THÍCH)
    // ==========================================

    @Test
    public void testADDPRODUCTSUCCESSWISHLIST01() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://cms.anhtester.com/");
        closeOverlay(wait);
        performLogin(wait, "t@gmail.com", "123456");
        driver.get("https://cms.anhtester.com/product/dell");

        WebElement addToWishlistBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Add to wishlist') or contains(.,'Add to Wishlist')]|//a[contains(.,'Add to wishlist') or contains(.,'Add to Wishlist')]")
        ));
        try {
            addToWishlistBtn.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", addToWishlistBtn);
        }
    }

    @Test
    public void testVIEWLISTWISHLIST02() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://cms.anhtester.com/");
        closeOverlay(wait);
        performLogin(wait, "t@gmail.com", "123456");

        WebElement wishlistIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#wishlist a")
        ));
        try {
            wishlistIcon.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", wishlistIcon);
        }
    }

    @Test
    public void testMOVEPRODUCTTOCARTWISHLIST03() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        driver.get("https://cms.anhtester.com/");
        closeOverlay(wait);
        performLogin(wait, "t@gmail.com", "123456");

        WebElement wishlistIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#wishlist a")));
        try {
            wishlistIcon.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", wishlistIcon);
        }

        WebElement moveToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(@onclick,'addToCart') or contains(.,'Add to cart')])[1]")
        ));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", moveToCartBtn);
        try {
            moveToCartBtn.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", moveToCartBtn);
        }
        handleModalIfPresent(wait);
    }

    @Test
    public void testREMOVEPRODUCTWISHLIST04() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        driver.get("https://cms.anhtester.com/");
        closeOverlay(wait);
        performLogin(wait, "t@gmail.com", "123456");

        WebElement wishlistIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#wishlist a")));
        try {
            wishlistIcon.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", wishlistIcon);
        }

        WebElement removeIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[starts-with(@id,'wishlist_')]/div/div[2]/a/i)[1]")
        ));
        try {
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", removeIcon);
            removeIcon.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", removeIcon);
        }
    }

    // --- ĐÃ FIX TEST CASE NÀY ---
    @Test
    public void testSAVEAFTERLOGOUTWISHLIST05() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://cms.anhtester.com/");
        closeOverlay(wait);

        performLogin(wait, "c@gmail.com", "123456");

        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Active eCommerce CMS']")));
        logo.click();

        WebElement newestSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("section_newest")));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", newestSection);

        List<WebElement> wishlistIcons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("#section_newest a[onclick*='addToWishList'] i")
        ));
        if (wishlistIcons.size() < 2) {
            throw new RuntimeException("Không tìm thấy đủ icon addToWishList. Số lượng: " + wishlistIcons.size());
        }

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", wishlistIcons.get(0));
        js.executeScript("arguments[0].click();", wishlistIcons.get(0));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", wishlistIcons.get(1));
        js.executeScript("arguments[0].click();", wishlistIcons.get(1));

        WebElement wishlistIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#wishlist a")));
        js.executeScript("arguments[0].click();", wishlistIcon);

        performLogout(wait); // Gọi hàm logout đã fix

        performLogin(wait, "c@gmail.com", "123456");

        WebElement wishlistIcon2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#wishlist a")));
        js.executeScript("arguments[0].click();", wishlistIcon2);
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    // ==========================================
    // HELPER METHODS
    // ==========================================

    private void closeOverlay(WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.absolute-full.bg-black.opacity-60")));
        } catch (TimeoutException e) {}
    }

    private void clickRegistration(WebDriverWait wait) {
        WebElement registrationLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Registration']")));
        registrationLink.click();
    }

    private void clickTermsAndSubmit(WebDriver driver, WebDriverWait wait) {
        WebElement termsLabel = driver.findElement(By.xpath("//form[@id='reg-form']/div[5]/label"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", termsLabel);
        wait.until(ExpectedConditions.elementToBeClickable(termsLabel));
        try {
            termsLabel.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsLabel);
        }
        driver.findElement(By.xpath("//form[@id='reg-form']/div[6]/button")).click();
    }

    private void performLogin(WebDriverWait wait, String emailText, String passwordText) {
        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Login']")));
        loginLink.click();
        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        email.clear();
        email.sendKeys(emailText);
        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys(passwordText);
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'wishlists')]")));
    }

    // --- HÀM NÀY ĐÃ ĐƯỢC CẬP NHẬT ĐỂ SỬA LỖI ---
    private void performLogout(WebDriverWait wait) {
        // Tìm avatar theo class 'avatar' hoặc vùng 'aiz-user-panel' thay vì tên file ảnh cụ thể
        WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(@class,'avatar')]//img | //div[contains(@class,'aiz-user-panel')]//img")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", avatar);
        
        WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Logout']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutLink);
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Login']")));
    }

    private void handleModalIfPresent(WebDriverWait wait) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement addToCartModalBtn = shortWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='addToCart-modal-body']//button")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartModalBtn);
            WebElement closeModalBtn = shortWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#modal-size button")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeModalBtn);
        } catch (Exception ignore) {}
    }
}
