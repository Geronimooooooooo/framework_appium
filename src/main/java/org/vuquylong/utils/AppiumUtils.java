package org.vuquylong.utils;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	public AppiumDriverLocalService service;
	
	public Float getFormattedAmount(String amount) {
		Float price = Float.parseFloat(amount.substring(1));
		return price;
	}
	
	public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(ele, "text", "Cart"));
	}
	
	public AppiumDriverLocalService startAppiumServer(String ipAdress, int port) {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\PhongHy\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAdress).usingPort(port).build();
		service.start();
		return service;
	}
}
