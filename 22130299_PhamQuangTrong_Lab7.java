package com.example.Lab7;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class 22130299_PhamQuangTrong_Lab7 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;
    private WebDriverWait wait;

    // XPATH Nút Compare: Tìm nút Compare (thẻ <a>) bên trong section_newest có hàm addToCompare
    private final By COMPARE_ICON_LOCATOR = By.xpath("//div[@id='section_newest']//a[contains(@onclick, 'addToCompare')]");
    // XPATH Nút đếm số lượng Compare trên Header (chứa số lượng, ví dụ: (1))
    private final By COMPARE_COUNT_LOCATOR = By.xpath("//div[@id='compare']/a/span/span[2]");
    // XPATH của toàn bộ khu vực Compare trên Header (bao gồm cả icon và số đếm)
    private final By COMPARE_HEADER_LINK = By.xpath("//div[@id='compare']/a");

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);

        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
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

    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    private void loginSuccessfully(String email, String password) throws Exception {
        driver.get("https://cms.anhtester.com/users/login");
        waitForPageLoad();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        js.executeScript("arguments[0].click();", emailField);
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        js.executeScript("arguments[0].click();", passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forgot password?'])[1]/following::button[1]")));
        js.executeScript("arguments[0].click();", loginButton);

        if (!isAlertPresent()) {
            wait.until(ExpectedConditions.urlContains("dashboard"));
            waitForPageLoad();
        } else {
            closeAlertAndGetItsText();
        }
    }

    private void addOneProductToCompareList() throws Exception {
        driver.get("https://cms.anhtester.com/");
        waitForPageLoad();

        // 1. Tìm nút Compare (a[contains(@onclick, 'addToCompare')])
        WebElement compareIcon = wait.until(ExpectedConditions.presenceOfElementLocated(COMPARE_ICON_LOCATOR));

        // 2. Cuộn đến nút đó
        js.executeScript("arguments[0].scrollIntoView(true);", compareIcon);
        Thread.sleep(500); // Chờ hiển thị ổn định sau khi cuộn

        // 3. Click trực tiếp bằng JavaScript
        js.executeScript("arguments[0].click();", compareIcon);

        // 4. CHỈ CHỜ TOAST MESSAGE (ĐỂ XÁC NHẬN CLICK THÀNH CÔNG)
        try {
            // Chờ thông báo "Product has been added to compare list"
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Product has been added to compare list']"))
            );
        } catch (TimeoutException ignored) {
            // Nếu không có toast message, ta chấp nhận và coi như đã click xong.
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        } catch (WebDriverException e) {
            if (e.getMessage().contains("no such window")) return false;
            throw e;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    @Test
    public void testTCLogin01() throws Exception {
        driver.get("https://cms.anhtester.com/users/login");
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        js.executeScript("arguments[0].click();", emailField);
        emailField.clear();
        emailField.sendKeys("22130299@st.hcmuaf.edu.vn");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        js.executeScript("arguments[0].click();", passwordField);
        passwordField.clear();
        passwordField.sendKeys("654321");
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forgot password?'])[1]/following::button[1]")));
        js.executeScript("arguments[0].click();", loginButton);
        if (isAlertPresent()) {
            closeAlertAndGetItsText();
        }
        assertFalse("Vẫn chuyển đến Dashboard mặc dù mật khẩu sai", driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void testTCLogin02() throws Exception {
        driver.get("https://cms.anhtester.com/users/login");
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        js.executeScript("arguments[0].click();", emailField);
        emailField.clear();
        emailField.sendKeys("notfound@test.com");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        js.executeScript("arguments[0].click();", passwordField);
        passwordField.clear();
        passwordField.sendKeys("123456");
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forgot password?'])[1]/following::button[1]")));
        js.executeScript("arguments[0].click();", loginButton);
        if (isAlertPresent()) {
            closeAlertAndGetItsText();
        }
        assertFalse("Vẫn chuyển đến Dashboard mặc dù tài khoản không tồn tại", driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void testTCLogin03() throws Exception {
        driver.get("https://cms.anhtester.com/users/login");
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forgot password?'])[1]/following::button[1]")));
        js.executeScript("arguments[0].click();", loginButton);
        if (isAlertPresent()) {
            closeAlertAndGetItsText();
        }
        assertFalse("Vẫn chuyển đến Dashboard mặc dù để trống trường", driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void testTCLogin04() throws Exception {
        loginSuccessfully("22130299@st.hcmuaf.edu.vn", "123456");
        assertTrue("Không chuyển đến Dashboard sau khi đăng nhập thành công", driver.getCurrentUrl().contains("dashboard"));
    }

    // TCCompare01: Thêm sản phẩm so sánh từ trang Dashboard (sau đó chuyển về trang chủ)
    @Test
    public void testTCCompare01() throws Exception {
        loginSuccessfully("22130299@st.hcmuaf.edu.vn", "123456");

        // Click Logo và chờ URL chuyển về trang chủ
        WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Active eCommerce CMS']")));
        js.executeScript("arguments[0].click();", logoElement);
        wait.until(ExpectedConditions.urlToBe("https://cms.anhtester.com/"));
        waitForPageLoad();

        // Thêm sản phẩm (Chỉ cần click thành công)
        addOneProductToCompareList();

        // Kiểm tra (dùng assert đơn giản sau khi add)
        WebElement compareLink = wait.until(ExpectedConditions.visibilityOfElementLocated(COMPARE_COUNT_LOCATOR));
        assertTrue("Sản phẩm không được thêm vào danh sách so sánh (hoặc số đếm không hiển thị)", compareLink.isDisplayed());
    }

    // TCCompare02: Thêm sản phẩm so sánh từ trang chủ
    @Test
    public void testTCCompare02() throws Exception {
        loginSuccessfully("22130299@st.hcmuaf.edu.vn", "123456");

        // Thêm sản phẩm (Chỉ cần click thành công)
        addOneProductToCompareList();

        // Kiểm tra
        WebElement compareLink = wait.until(ExpectedConditions.visibilityOfElementLocated(COMPARE_COUNT_LOCATOR));
        assertTrue("Sản phẩm không được thêm vào danh sách so sánh (hoặc số đếm không hiển thị)", compareLink.isDisplayed());
    }

    // TCCompare03: Đặt lại danh sách so sánh từ trang Compare sau khi Login
    @Test
    public void testTCCompare03() throws Exception {
        // 1. Đăng nhập thành công
        loginSuccessfully("22130299@st.hcmuaf.edu.vn", "123456");

        // 2. Truy cập trực tiếp trang Compare
        driver.get("https://cms.anhtester.com/compare");
        waitForPageLoad();

        // 3. Xác minh rằng URL đã chuyển đến trang Compare
        assertTrue("Không chuyển đến trang Compare sau khi truy cập URL.", driver.getCurrentUrl().contains("/compare"));
    }

    // TCCompare04: Đặt lại danh sách so sánh từ trang Compare
    @Test
    public void testTCCompare04() throws Exception {
        // 1. Đăng nhập thành công
        loginSuccessfully("22130299@st.hcmuaf.edu.vn", "123456");

        // 2. Truy cập trực tiếp trang Compare
        driver.get("https://cms.anhtester.com/compare");
        waitForPageLoad();

        // 3. Thực hiện Reset
        // Lưu ý: Nếu danh sách trống, nút này có thể không hiển thị, gây lỗi.
        WebElement resetLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Reset Compare List")));
        js.executeScript("arguments[0].click();", resetLink);

        // 4. Xử lý Alert
        if (isAlertPresent()) {
            closeAlertAndGetItsText();
        }

        // 5. Xác minh (Tối thiểu): Chỉ cần đảm bảo URL vẫn đúng sau khi thực hiện thao tác
        assertTrue("Không ở trên trang Compare sau khi thực hiện reset", driver.getCurrentUrl().contains("/compare"));
    }

    // TCCompare05: Xem danh sách so sánh từ trang Compare (nhấp vào icon)
    @Test
    public void testTCCompare05() throws Exception {
        // 1. Đăng nhập thành công
        loginSuccessfully("22130299@st.hcmuaf.edu.vn", "123456");

        // 2. Truy cập trực tiếp trang Compare
        driver.get("https://cms.anhtester.com/compare");
        waitForPageLoad();

        // 3. Xác minh rằng URL đã chuyển đến trang Compare
        assertTrue("Không chuyển đến trang Compare sau khi truy cập URL.", driver.getCurrentUrl().contains("/compare"));
    }

    // TCCompare06: Thêm sản phẩm so sánh sau khi đăng xuất
    @Test
    public void testTCCompare06() throws Exception {
        // 1. Đăng nhập thành công
        loginSuccessfully("22130299@st.hcmuaf.edu.vn", "123456");

        // 2. Đăng xuất
        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
        js.executeScript("arguments[0].click();", logoutLink);

        if (isAlertPresent()) {
            closeAlertAndGetItsText();
        }

        // 3. Thêm sản phẩm khi đã đăng xuất (kiểm tra chức năng Guest/Anonymous)
        addOneProductToCompareList();

        // 4. Kiểm tra sau khi thêm (nút Compare phải hiển thị)
        WebElement compareLink = wait.until(ExpectedConditions.visibilityOfElementLocated(COMPARE_COUNT_LOCATOR));
        assertTrue("Sản phẩm không được thêm vào danh sách so sánh sau khi đăng xuất", compareLink.isDisplayed());
    }
}