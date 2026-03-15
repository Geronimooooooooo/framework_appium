package org.vuquylong.PageObjects.android;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.vuquylong.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{
	AndroidDriver driver;
	WebDriverWait wait;
	boolean isFound = false;
	float totalPrice = 0;
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement pageTitle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> listProductName; 
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> listProductPrice;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/totalAmountLbl\"]")
	private WebElement actualPrice;
	
	@AndroidFindBy(xpath = "//android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnProceed\"]")
	private WebElement purchaseButton;
	
	public boolean verifyProductCart(String productName) {

		for (WebElement product : listProductName) {
			if (product.getText().equalsIgnoreCase(productName)) {
				isFound = true;
				break;
			}
		}
		return isFound;	
	}
	
	public float totalPrice() {
		for (WebElement price: listProductPrice) {
			totalPrice += Float.parseFloat(price.getText().replace("$", ""));
		}
		return totalPrice;
	}
	
	public float actutalPrice() {
		float actutalprice = Float.parseFloat(actualPrice.getText().replace("$", "")); 
		return actutalprice;
	}
	
	public void purchaseCart() {
		checkBox.click();
		purchaseButton.click();
	}
	
}
