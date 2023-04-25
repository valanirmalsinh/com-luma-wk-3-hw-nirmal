package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class GearTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {
        //* Mouse Hover on Gear Menu
        mouseHoverElement(By.xpath("//a[@id='ui-id-6']"));

        //* Click on Bags
        Thread.sleep(2000);
        mouseHoverElementAndClick(By.xpath("//a[@id='ui-id-25']"));
        //* Click on Product Name ‘Overnight Duffle’
        clickOnElement(By.xpath("//a[contains(text(),'Overnight Duffle')]"));
        //* Verify the text ‘Overnight Duffle’
        String actualName = getTextElement(By.xpath("//span[@class='base']"));
        Assert.assertEquals("Invalid name", "Overnight Duffle", actualName);
        //* Change Qty 3
        driver.findElement(By.xpath("//input[@id='qty']")).clear();
        sendTextToElement(By.xpath("//input[@id='qty']"), "3");
        //* Click on ‘Add to Cart’ Button.
        clickOnElement(By.id("product-addtocart-button"));
        //* Verify the text ‘You added Overnight Duffle to your shopping cart.’
        verifyText("Invalid text", "You added Overnight Duffle to your shopping cart.", getTextElement(By.xpath("//div[@class='message-success success message']")));
        //* Click on ‘shopping cart’ Link into message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        //* Verify the product name ‘Overnight duffle’
        Thread.sleep(2000);
        verifyText("Invalid product added", "Overnight Duffle", getTextElement(By.xpath("//td[@class='col item']/descendant::a[2]")));
        // * Verify the Qty is ‘3’
        String qty = driver.findElement(By.xpath("//td[@class='col qty']/child::div[1]/descendant::input")).getAttribute("value");
        Assert.assertEquals("Invalid quantity", "3", qty);
        //* Verify the product price ‘$135.00’
        String price = driver.findElement(By.xpath("//td[@class='col subtotal']//descendant::span[contains(text(),'$135.00')]")).getText();
        Assert.assertEquals("Invalid price", "$135.00", price);
        //* Change Qty to ‘5’
        driver.findElement(By.xpath("//td[@class='col qty']/child::div[1]/descendant::input")).clear();
        sendTextToElement(By.xpath("//td[@class='col qty']/child::div[1]/descendant::input"), "5");
        //* Click on ‘Update Shopping Cart’ button
        clickOnElement(By.xpath("//div[@class='cart main actions']/descendant::button[2]"));
        //* Verify the product price ‘$225.00’
        Thread.sleep(2000);
        String qty1 = driver.findElement(By.xpath("//td[@data-th='Subtotal']//span[@class='price']")).getText();
        Assert.assertEquals("Invalid text", "$225.00", qty1);

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}





