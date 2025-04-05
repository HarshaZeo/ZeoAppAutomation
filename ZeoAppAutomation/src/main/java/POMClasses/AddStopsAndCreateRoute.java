package POMClasses;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import ConfigFiles.DriverMethods;
import ConfigFiles.ExcelUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddStopsAndCreateRoute {
	
AppiumDriver driver;
	
	public AddStopsAndCreateRoute(AppiumDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this );
	}

	//History module
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/linear_history")
	public WebElement history;
	
	//Add new route
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/relative_add_route")
	public WebElement btnAddNewRoute;
	
	//Search by address
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Search by address\")")
	public WebElement txtSearchAddress;
	
	//address suggestions
	@AndroidFindBy(xpath="(//android.widget.LinearLayout[@resource-id=\"com.zeoauto.zeocircuit:id/linearMain2\"])/android.widget.TextView[2]")
	public WebElement addressSuggestions;
	
	//Search by address
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/lin_byAddress")
	public WebElement lnkByAddress;
	
	//on map
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/lin_bymap")
	public WebElement lnkOnMap;
	
	//Map view to scroll
	@AndroidFindBy(xpath="(//android.view.View[@content-desc=\"Google Map\"])[1]")
	public WebElement scrollMapView;
	
	//stop actions under on map view
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/txt_aws_detail")
	public WebElement stopActions;
	
	//delivery types
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/linearDelivery")
	public WebElement optDelivery;
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/linearPickup")
	public WebElement optPickUp;
	
	//priority types
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/linearNormal")
	public WebElement optNormal;
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/linearAsap")
	public WebElement optASAP;
	
	//notes
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/rel_note")
	public WebElement lnkNotes;
	
	//lable trip notes
	@AndroidFindBy(id="//android.widget.TextView[@text=\"Trip notes\"]")
	public WebElement lblTripNotes;
	
	//notes text area
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/edtNotes")
	public WebElement txtTripNotes;
	
	//on map
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/lin_by_latlong")
	public WebElement lnkByLatLong;
	
	//excel import
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/rel_excel_card")
	public WebElement ImportExcel;
	
	//import stops label
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Import Stops\"]")
	public WebElement lblImportStops;
	
	//Browise files on your phone
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Browse files on your phone to upload.\")")
	public WebElement BrowiseFile;
	
	//Click on hamburgur icon on top left
	@AndroidFindBy(accessibility="Show roots")
	public WebElement hamIcon;
	
	//select drive charsha760@gmail.com
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"android:id/summary\"]")
	public WebElement Selectdriver;
	
	//select my drive
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"My Drive\"]")
	public WebElement MyDrive;
	
	//common locator for all the files in the drive
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"android:id/title\"]")
	public WebElement selectFile;
	
	//Lable Help us by mapping your address..
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Help us by mapping your address..\")")
	public WebElement lblHelpByMapping;
	
	//proceed button
	@AndroidFindBy(xpath="(//android.widget.Button[@resource-id=\"com.zeoauto.zeocircuit:id/btn_proceed\"])[1]")
	public WebElement btnProceed;
	
	//Lable additional info
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Additional info...\")")
	public WebElement lblAdditionalin;
	
	//Finish mapping button
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/btn_mapping")
	public WebElement btnFinishMapping;
	
	
	/*Add stops through search*/
	public void addStopsSearch(AndroidDriver d,String path,String name) throws Exception
	{
		 //implicitly wait
		DriverMethods dm=new DriverMethods(d);
		dm.implicitWait(30);
		
		//click on history from header
		history.click();
		
		//click on add new route
		btnAddNewRoute.click();
		
		//read data from sheet
		ExcelUtils eu=new ExcelUtils(path,name);
		System.out.println(eu.getRows());
		//loop to iterate excel data
		for(int i=1; i<eu.getRows();i++)
		{
		    
			//enter address in the search field
		    Actions ac=new Actions(d);
		    ac.sendKeys(txtSearchAddress, eu.getValues(i, 0)).build().perform();
			//txtSearchAddress.sendKeys(eu.getValues(i, 0));
			
			//close keyboard
			dm.keyboard();
			
			Thread.sleep(2000);
			//store all suggestions in arraylist
			ArrayList<WebElement> al=new ArrayList<WebElement>(d.findElements(By.xpath("(//android.widget.LinearLayout[@resource-id=\"com.zeoauto.zeocircuit:id/linearMain2\"])/android.widget.TextView[2]")));
			
			//loop to iterate suggetsion address
			for(int ed=0;ed<=al.size();ed++)
			{
				//condution to check suggestion address and address mentioned in the sheet
				if(al.get(ed).getText().equals(eu.getValues(i, 1)))
				{
					//select matched address
					ac.click(al.get(ed)).build().perform();
					break;
				}
			}

		}
	}

	/*Add stops excel*/
	public void addStopsExcel(AndroidDriver d) throws Exception
	{
		 //implicitly wait
		DriverMethods dm=new DriverMethods(d);
		dm.implicitWait(30);
		
		//click on history from header
		history.click();
		
		//click on add new route
		btnAddNewRoute.click();
		
		//Click on import excel
		ImportExcel.click();
		
		//Verify import stops popup
		assertTrue(lblImportStops.isDisplayed());
		
		//click on browse file on phone
		BrowiseFile.click();
		
		//click on hamicon
		hamIcon.click();
		
		//verify and select drive from the list of drives
		ArrayList<WebElement> drives=new ArrayList<WebElement>(d.findElements(By.xpath("//android.widget.TextView[@resource-id=\"android:id/summary\"]")));
		
		//loop to iterate 
		for(int nd=0;nd<=drives.size();nd++)
		{
			//click if the condition is true
			if(drives.get(nd).getText().equals("charsha760@gmail.com"));
			{
				//click on drive
				drives.get(nd).click();
				break;
			}
		}
		
		//click on my drive
		MyDrive.click();
		
		//scroll and identify the element and click on it
		d.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"manual-and-excel-orders_15758007.xlsx\"));")).click();
		
		//click on proceed button
		
	}
	

	
}
