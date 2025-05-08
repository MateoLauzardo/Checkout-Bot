package org.example;

import com.microsoft.playwright.*;
import java.io.IOException;


public class SupremePlaywrightBot {
    public static void main(String[] args) throws IOException {

            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Step 1: Go to product page
            page.navigate("https://us.supreme.com/collections/tops-sweaters");

            // Step 2: Click product
            page.click("img[alt='Small Box Tee - Black']");

            // Step 3: Select size
            page.selectOption("select[data-testid='size-dropdown']", "Large");

            // Step 4: Add to cart
            page.click("button[data-testid='add-to-cart-button']");

            // Step 5: Wait for cart and prompt user
            page.waitForSelector("div[data-testid='mini-cart']");

            //STEP 6: Wait for the "checkout now" button and click its parent link
            page.waitForSelector("a:has(span:text('checkout now'))").click();

            // Step 7: Fill checkout info
            page.fill("input[name='email']", "mateo.lauzardo@hotmail.com");
            page.fill("input[name='firstName']", "Mateo");
            page.fill("input[name='lastName']", "Lauzardo");
            page.fill("input[name='address1']", "16274 sw 28ct");
            page.fill("input[name='city']", "Miramar");
            page.fill("input[name='postalCode']", "33027");
            page.fill("input[name='phone']", "7542132327");
            page.selectOption("select[name='province']", "FL");


         // Wait a bit for Stripe iframes to appear
         page.waitForTimeout(6000);


        /// add step 8 which adds all the text



        /// add step 9 which clicks process payment button




        System.out.println("Press ENTER to exit");
        System.in.read();
        browser.close();
        playwright.close();

    }
}