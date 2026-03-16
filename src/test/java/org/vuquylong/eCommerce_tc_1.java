package org.vuquylong;

import org.testng.annotations.Test;
import org.vuquylong.TestUtils.BasesTest;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;

public class eCommerce_tc_1 extends BasesTest {
	@Test
	public void fillForm() throws InterruptedException {
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vqlong");

		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();

		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Argentina\"))")).click();

		driver.findElement(By.xpath("//android.widget.RadioButton[@text=\"Female\"]")).click();

		driver.findElement(
				By.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]"))
				.click();
		String nameToast = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
		//String nameToast = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Please enter your name\")")).getAttribute("name");
		AssertJUnit.assertEquals(nameToast,"Please enter your name");
	}
}
