package org.vuquylong;

import org.testng.annotations.Test;
import org.vuquylong.PageObjects.android.CartPage;
import org.vuquylong.PageObjects.android.FormPage;
import org.vuquylong.PageObjects.android.ProductCatalogue;
import org.vuquylong.TestUtils.BasesTest;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_tc_3_Hybrid extends BasesTest {
	float totalAmount = 0;
	float actualAmount = 0;

	@BeforeMethod
	public void preSetup() {
		driver.terminateApp("com.androidsample.generalstore");

		driver.activateApp("com.androidsample.generalstore");
		/*
		 * driver.executeScript("mobile: startActivity", ImmutableMap.of("intent",
		 * "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity")
		 * );
		 */

	}

	@AfterMethod
	public void resetToNative() {
		driver.context("NATIVE_APP");
	}

	@Test
	public void fillForm_ErrorValidation() throws InterruptedException {
		// driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vqlong");

		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();

		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Argentina\"))")).click();

		driver.findElement(By.xpath("//android.widget.RadioButton[@text=\"Female\"]")).click();

		driver.findElement(
				By.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]"))
				.click();
		String nameToast = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
		// String nameToast = driver.findElement(AppiumBy.androidUIAutomator("new
		// UiSelector().text(\"Please enter your name\")")).getAttribute("name");
		AssertJUnit.assertEquals(nameToast, "Please enter your name");
	}

	@Test(dataProvider = "getData")
	public void fillForm(String name, String gender, String country) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		FormPage formPage = new FormPage(driver);
		formPage.setNameField(name);
		formPage.setGender(gender);
		formPage.setCountryAndPressButton(country);
		ProductCatalogue productCatalogue = formPage.submitForm();
		List<String> listProducts = List.of("Jordan 6 Rings", "Nike SFB Jungle");
		for (String product : listProducts) {
			productCatalogue.scrolltoProduct(product);
		}
		CartPage cartpage = productCatalogue.goToCartPage();

		Thread.sleep(2000L);
		WebElement pageTitle = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
		wait.until(ExpectedConditions.attributeContains(pageTitle, "text", "Cart"));

		for (String product : listProducts) {
			AssertJUnit.assertTrue(cartpage.verifyProductCart(product));
		}

		totalAmount = cartpage.totalPrice();
		actualAmount = cartpage.actutalPrice();
		System.out.println(totalAmount + " " + actualAmount);
		AssertJUnit.assertEquals(totalAmount, actualAmount);
		cartpage.purchaseCart();

		Thread.sleep(6000);

		Set<String> contexts = driver.getContextHandles();
		for (String context : contexts) {
			System.out.println(context);
		}

		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("facebook");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(6000L);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
	}
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"Vqlong","male","Argentina"},{"Ly Long","male","Argentina"}};
	}
}
