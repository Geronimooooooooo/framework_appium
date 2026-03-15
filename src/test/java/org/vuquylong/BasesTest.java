package org.vuquylong;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BasesTest {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException, URISyntaxException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\PhongHy\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("VQLONG");
		options.setUdid("0A171FDD40053Z");
		options.setApp("D:\\APPIUM\\Appium\\src\\main\\java\\resources\\General-Store.apk");
		
		  options.setChromedriverExecutable(
		  "C:\\Users\\PhongHy\\chromedriver\\win64-145.0.7632.117\\chromedriver-win64\\chromedriver.exe"
		  );
		  
		  //options.setCapability("browserName", "Chrome");
		 


		driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void endSession() {
		driver.quit();
		service.stop();
	}
}
