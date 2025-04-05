package POMClasses;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ConfigFiles.DriverMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PrefrencesPOD {
	
AppiumDriver driver;
	
	public PrefrencesPOD(AppiumDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this );
	}

	    //My profile icon
		@AndroidFindBy(id="com.zeoauto.zeocircuit:id/linear_profile")
		public WebElement iconMyProfile;
		
		//prefrences 
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Preferences\")")
		public WebElement lnkPrefrences;
		
		//Route prefrences
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.RelativeLayout\").instance(3)")
		public WebElement lnkRoutePrefrences;
		
		//Proof of delivery
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").instance(8)")
		public WebElement btnPod;
		
		//Pod enable
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Enable\")")
		public WebElement btnEnable;
		
		//pod disable
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Disable\")")
		public WebElement btnDisable;
		
		//Save changes
		@AndroidFindBy(id="com.zeoauto.zeocircuit:id/btn_save")
		public WebElement btnSaveChanges;
		
		//navigate back to my profile screen
		@AndroidFindBy(id="com.zeoauto.zeocircuit:id/rel_back")
		public WebElement btnBack;

		//prefrences pod enable or disable
		public void enableOrDisablePod(AndroidDriver d) throws Exception
		{
			//Wait for my profile 
			DriverMethods dm=new DriverMethods(d);
			dm.implicitWait(10);
			
			//click on my profile icon
			iconMyProfile.click();
			
			//Click on prefrences
//			lnkPrefrences.click();
//			
//			//Click on route prefrences
//			lnkRoutePrefrences.click();
//			
//			//click on proof of delivery
//			getBtnPod().click();
//			
//			//if condition to click on either enable or disable
//			if(getBtnEnable().isEnabled())
//			{
//				//click on disabled button
//				getBtnDisable().click();
//				
//				//print selected text of the button
//				System.out.println("Button ->"+getBtnDisable().getText());
//			}
//			
//			//else click on disable
//			else
//			{
//				//click on enable
//				getBtnEnable().click();
//				
//				//print selected text of the button
//				System.out.println("Button ->"+getBtnEnable().getText());
//			}
//			
//			//click on save changes
//			btnSaveChanges.click();
//			
//			//click on back button
//			btnBack.click();
		}
}
