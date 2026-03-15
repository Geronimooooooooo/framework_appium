package org.vuquylong.PageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.vuquylong.utils.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{
	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver; 
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vqlong");
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	// driver.findElement(By.xpath("com.widget.RadioButton[@text='Female']")).click();
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;

	// driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryOption;

	// driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]")).click();
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]")
	private WebElement btnLetShop;
	
	
	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}

	public void setGender(String gender) {
		if (gender.contains("female")) {
			femaleOption.click();
		} else {
			maleOption.click();
		}
	}

	public void setCountryAndPressButton(String country) {
		countryOption.click();
		scrollToTextAction(country);
	}
	
	public ProductCatalogue submitForm() {
		btnLetShop.click();
		return new ProductCatalogue(driver);
	}
	
}
