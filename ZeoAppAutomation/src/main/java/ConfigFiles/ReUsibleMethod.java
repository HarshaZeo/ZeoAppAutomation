package ConfigFiles;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;

public class ReUsibleMethod {
	
	public static boolean isElementPresent(AndroidDriver driver, By locator)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		try
		{
			driver.findElement(locator);
			return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
	}
	
	public static boolean isElementsPresent(AndroidDriver driver, By locator)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		try
		{
			driver.findElements(locator);
			return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
	}
	
	public static boolean elementPresent(AndroidDriver driver, By locator)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		try
		{
			driver.findElement(locator);
			return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
	}
}