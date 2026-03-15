package org.vuquylong.PageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.vuquylong.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogue extends AndroidActions {
	AndroidDriver driver;

	public ProductCatalogue(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement btnGoToCart;
	
	public void scrolltoProduct(String product) {
		scrollToTextAction(product);
		driver.findElement(By.xpath(
				"//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\""
						+ product
						+ "\"]/following-sibling::android.widget.LinearLayout[2]/android.widget.TextView[@text='ADD TO CART']"))
				.click();	
	}
	
	public CartPage goToCartPage() {
		btnGoToCart.click();
		return new CartPage(driver);
	}
}
