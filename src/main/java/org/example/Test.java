package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class Test {

    public Test() throws InterruptedException {

        // STEP 1: Setup
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mateo\\IdeaProjects\\untitled5\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\Mateo\\SeleniumProfile");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        // STEP 2: Stealth tweaks
        ((JavascriptExecutor) driver).executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        // STEP 3: Open product page
        driver.get("https://us.supreme.com/collections/tops-sweaters");

        WebElement productImage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//img[@alt='Small Box Tee - Black']")
        ));
        productImage.click();

        // STEP 4: Select size + add to cart
        WebElement sizeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("select[data-testid='size-dropdown']")
        ));
        new Select(sizeDropdown).selectByVisibleText("Large");

        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='add-to-cart-button']")
        ));
        addToCart.click();

        Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[data-testid='mini-cart']")
        ));


        // STEP 6: Wait until you reach the checkout page


        // STEP 7: Wait until form is ready
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));

        // STEP 8: Fill in contact/shipping
        actions.moveToElement(emailField).click().pause(Duration.ofMillis(300)).sendKeys("mateo.lauzardo@hotmail.com").perform();
        driver.findElement(By.name("lastName")).sendKeys("Lauzardo");
        driver.findElement(By.name("address1")).sendKeys("16274 sw 28ct");
        driver.findElement(By.name("city")).sendKeys("Miramar");
        driver.findElement(By.name("postalCode")).sendKeys("33027");
        driver.findElement(By.name("phone")).sendKeys("7542132327");
        new Select(driver.findElement(By.name("province"))).selectByVisibleText("Florida");




    }

}