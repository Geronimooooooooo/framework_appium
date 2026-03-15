package org.vuquylong.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils{

	AndroidDriver driver;

	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
	}

	public void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile:longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
	}

	public void scrollToEndAction() {
		boolean scrollAble;
		do {
			scrollAble = (boolean) ((JavascriptExecutor) driver).executeScript("mobile:scrollGesture", ImmutableMap
					.of("left", 0, "top", 456, "width", 1080, "height", 1368, "direction", "down", "percent", 0.8));
		} while (scrollAble);
	}

	public void scrollToTextAction(String str) {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + str
						+ "\"))"))
				.click();
	}

	public void swipeAction(WebElement ele) {
		driver.executeScript("mobile:swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "direction", "left", "percent", 0.1));
	}

	public void dragAndDropAction(WebElement ele, int endX, int endY) {
		driver.executeScript("mobile:dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "endX", endX, "endY", endY));
	}
	
}
