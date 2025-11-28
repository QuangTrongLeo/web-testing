package com.example;

import java.time.Duration;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class _22130081_CaoMinhHieu_Lab7 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private JavascriptExecutor js;
    private WebDriverWait wait;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        // cho cửa sổ lớn 1 chút để hạn chế overlay đè
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ==================== TCSEARCH01 ====================
    // Search với từ khóa "La" và chọn gợi ý "Laptop"
    @Test
    public void testTCSEARCH01() throws Exception {
        driver.get("https://cms.anhtester.com/");

        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("La");

        // Chọn gợi ý Laptop
        safeClick(By.linkText("Laptop"));
    }

    // ==================== TCSEARCH02 ====================
    // Search với từ khóa "la" (viết thường) và chọn "Laptop"
    @Test
    public void testTCSEARCH02() throws Exception {
        driver.get("https://cms.anhtester.com/");

        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("la");

        // Chọn gợi ý Laptop
        safeClick(By.linkText("Laptop"));
    }

    // ==================== TCSEARCH03 ====================
    // Search với từ khóa "lap" và chọn "Laptop"
    @Test
    public void testTCSEARCH03() throws Exception {
        driver.get("https://cms.anhtester.com/");

        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("lap");

        // Chọn gợi ý Laptop
        safeClick(By.linkText("Laptop"));
    }

    // ==================== TCSEARCH04 ====================
    // Search trực tiếp từ khóa "Laptop" rồi ENTER
    @Test
    public void testTCSEARCH04() throws Exception {
        driver.get("https://cms.anhtester.com/");

        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("Laptop");
        searchBox.sendKeys(Keys.ENTER);
    }

    // ==================== TCFILTER01 ====================
    @Test
    public void testTCFILTER01() throws Exception {
        driver.get("https://cms.anhtester.com/");
        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("La");

        safeClick(By.linkText("Laptop"));

        safeClick(By.linkText("Acer 01"));
    }

    // ==================== TCFILTER02 ====================
    @Test
    public void testTCFILTER02() throws Exception {
        driver.get("https://cms.anhtester.com/");
        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("la");

        safeClick(By.linkText("Laptop"));

        // Click thanh trượt giá
        safeClick(By.xpath("//div[@id='input-slider-range']/div/div[3]/div"));
    }

    // ==================== TCFILTER03 ====================
    @Test
    public void testTCFILTER03() throws Exception {
        driver.get("https://cms.anhtester.com/");
        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("la");

        safeClick(By.linkText("Laptop"));

        // Mở dropdown Brand
        safeClick(By.xpath("//form[@id='search-form']/div/div[2]/div/div/div[3]/div/button/div/div/div"));

        // Chọn 1 option trong list
        safeClick(By.id("bs-select-1-7"));

        // Chọn brand bằng Select
        Select brandSelect = new Select(driver.findElement(By.name("brand")));
        brandSelect.selectByVisibleText("df");
    }

    // ==================== TCFILTER04 ====================
    @Test
    public void testTCFILTER04() throws Exception {
        driver.get("https://cms.anhtester.com/");
        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("la");

        safeClick(By.linkText("Laptop"));
        safeClick(By.linkText("Acer 01"));

        // Mở dropdown Brand ở trang chi tiết
        safeClick(By.xpath("//form[@id='search-form']/div/div[2]/div/div/div[3]/div/button/div/div/div"));

        // Chọn option trong list
        safeClick(By.id("bs-select-1-7"));

        // Chọn brand bằng Select
        Select brandSelect = new Select(driver.findElement(By.name("brand")));
        brandSelect.selectByVisibleText("df");
    }

    // ==================== TCFILTER05 ====================
    @Test
    public void testTCFILTER05() throws Exception {
        driver.get("https://cms.anhtester.com/");
        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("la");

        safeClick(By.linkText("Laptop"));

        // Mở filter Size
        safeClick(By.linkText("Filter by Size"));

        // Chọn size đầu tiên
        safeClick(By.xpath("//div[@id='collapse_Size']/div/label/span[2]"));
    }

    // ==================== TCFILTER06 ====================
    @Test
    public void testTCFILTER06() throws Exception {
        driver.get("https://cms.anhtester.com/");
        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("lap");

        safeClick(By.linkText("Laptop"));

        // Chọn Brand
        safeClick(By.xpath("//form[@id='search-form']/div/div[2]/div/div/div[3]/div/button/div/div/div"));
        safeClick(By.id("bs-select-1-7"));
        Select brandSelect = new Select(driver.findElement(By.name("brand")));
        brandSelect.selectByVisibleText("df");

        // Chọn Sort: Price low to high
        safeClick(By.xpath("//form[@id='search-form']/div/div[2]/div/div/div[4]/div/button/div/div/div"));
        safeClick(By.xpath("//a[@id='bs-select-2-2']/span"));
        Select sortSelect = new Select(driver.findElement(By.name("sort_by")));
        sortSelect.selectByVisibleText("Price low to high");
    }

    // ==================== TCFILTER07 ====================
    @Test
    public void testTCFILTER07() throws Exception {
        driver.get("https://cms.anhtester.com/");
        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("la");

        safeClick(By.linkText("Laptop"));

        // Mở dropdown Brand
        safeClick(By.xpath("//form[@id='search-form']/div/div[2]/div/div/div[3]/div/button/div/div/div"));

        // Tìm kiếm brand "Nike" trong ô search của dropdown
        WebElement brandSearchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']"))
        );
        brandSearchInput.clear();
        brandSearchInput.sendKeys("Ni");

        // Chọn option Nike trong list
        safeClick(By.id("bs-select-1-97"));

        // Chọn brand bằng Select
        Select brandSelect = new Select(driver.findElement(By.name("brand")));
        brandSelect.selectByVisibleText("Nike");
    }

    // ==================== TCFILTER08 ====================
    @Test
    public void testTCFILTER08() throws Exception {
        driver.get("https://cms.anhtester.com/");
        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("la");

        safeClick(By.linkText("Laptop"));
        safeClick(By.linkText("Acer 01"));

        // Chọn Brand
        safeClick(By.xpath("//form[@id='search-form']/div/div[2]/div/div/div[3]/div/button/div/div/div"));
        safeClick(By.id("bs-select-1-7"));
        Select brandSelect = new Select(driver.findElement(By.name("brand")));
        brandSelect.selectByVisibleText("df");

        // Chọn Sort: Price high to low
        safeClick(By.xpath("//form[@id='search-form']/div/div[2]/div/div/div[4]/div/button/div"));
        safeClick(By.xpath("//a[@id='bs-select-2-3']/span"));
        Select sortSelect = new Select(driver.findElement(By.name("sort_by")));
        sortSelect.selectByVisibleText("Price high to low");

        // Quay lại danh sách Laptop
        safeClick(By.linkText("Laptop"));
    }

    // ==================== TCFILTER09 ====================
    @Test
    public void testTCFILTER09() throws Exception {
        driver.get("https://cms.anhtester.com/");

        // Search "lap" rồi ENTER, không dùng auto-suggest để tránh lỗi locator
        safeClick(By.id("search"));
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        searchBox.sendKeys("lap");
        searchBox.sendKeys(Keys.ENTER);

        // Vào danh mục Laptop
        safeClick(By.linkText("Laptop"));

        // Chọn sản phẩm Acer 01
        safeClick(By.linkText("Acer 01"));

        // Chọn Brand
        safeClick(By.xpath("//form[@id='search-form']/div/div[2]/div/div/div[3]/div/button/div"));
        safeClick(By.id("bs-select-1-7"));
        Select brandSelect = new Select(driver.findElement(By.name("brand")));
        brandSelect.selectByVisibleText("df");

        // Chọn Sort: Price low to high
        safeClick(By.xpath("//form[@id='search-form']/div/div[2]/div/div/div[4]/div/button/div/div/div"));
        safeClick(By.xpath("//a[@id='bs-select-2-2']/span"));
        Select sortSelect = new Select(driver.findElement(By.name("sort_by")));
        sortSelect.selectByVisibleText("Price low to high");
    }

    // ==================== Helper & teardown ====================
    private void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            js.executeScript("arguments[0].click();", element);
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
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
}
