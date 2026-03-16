package org.vuquylong.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.vuquylong.utils.AppiumUtils;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BasesTest extends AppiumUtils {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void ConfigureAppium() throws URISyntaxException, IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\APPIUM\\AppiumFramwrokDesign\\src\\main\\java\\org\\vuquylong\\resources\\data.properties");
		prop.load(fis);
		String ipAdress = prop.getProperty("ipAdress");
		String port = prop.getProperty("port");
		service = startAppiumServer(ipAdress,Integer.parseInt(port));
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("VQLONG");
		options.setUdid("0A171FDD40053Z");
		options.setApp("D:\\APPIUM\\Appium\\src\\main\\java\\resources\\General-Store.apk");
		
		  options.setChromedriverExecutable(
		  "C:\\Users\\PhongHy\\chromedriver\\win64-145.0.7632.117\\chromedriver-win64\\chromedriver.exe"
		  );
		  
		  //options.setCapability("browserName", "Chrome");
		 


		driver = new AndroidDriver(service.getUrl(), port);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void endSession() {
		driver.quit();
		service.stop();
	}
}
