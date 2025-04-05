package POMClasses;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import ConfigFiles.DriverMethods;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LogOutMyProfile {
	
AppiumDriver driver;
	
	public LogOutMyProfile(AppiumDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this );
	}

	    //My profile icon
		@AndroidFindBy(id="com.zeoauto.zeocircuit:id/img_profile")
		public WebElement iconMyProfile;
		
		//Scrollable element
		@AndroidFindBy(id="com.zeoauto.zeocircuit:id/linearScroll")
		public WebElement scroll;
		
		//LogOut button
		@AndroidFindBy(id="com.zeoauto.zeocircuit:id/card_logout")
		public WebElement btnLogout;
		
		//Confirm logOut
		@AndroidFindBy(id="com.zeoauto.zeocircuit:id/btnLogout")
		public WebElement btnConfirm;

		//logout steps
		public void logOut(AndroidDriver d)
		{
			//Wait for my profile 
			DriverMethods dm=new DriverMethods(d);
			dm.implicitWait(10);
			
			//click on my profile
			iconMyProfile.click();
			
		    //Scrool to logout button and click
			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId", 
					((RemoteWebElement) scroll).getId(),
				"direction", "up","percent", 0.50
			));
			btnLogout.click();
		    
		    //Confirm logout
		    btnConfirm.click();
		}
}
