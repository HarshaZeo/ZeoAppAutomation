package POMClasses;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ConfigFiles.DriverMethods;
import ConfigFiles.ExcelUtils;
import ConfigFiles.ReUsibleMethod;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignUp {

AppiumDriver driver;
	
	public SignUp(AppiumDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this );
	}

	//Allow while using app permession
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
	public WebElement permission1;
	
	//Allow button.
	@AndroidFindBy(xpath="//android.widget.Button[@text='Allow']")public WebElement permission2;
	
	//Continue with google
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/card_normal")public WebElement btnContinueWithGoogle;
	
	//choose an account email
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.google.android.gms:id/account_display_name\"]")public WebElement googleAccounts;
	
	//email and password button
	@AndroidFindBy(xpath= "//android.widget.TextView[@text=\"Continue with Email + Password\"]")public WebElement btnEmailPassword;
	
	//Enter email text field
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.zeoauto.zeocircuit:id/edtEmail\"]")public WebElement txtEnterEmail;
	
	//Create password
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.zeoauto.zeocircuit:id/edtPassword\"]")public WebElement txtCreatePassword;
	
	//Show create password
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.zeoauto.zeocircuit:id/txt_show_hide\" and @text=\"SHOW\"]")public WebElement ShowCrPassword;
	
	//Confirm password
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.zeoauto.zeocircuit:id/edtConfirmPassword\"]")public WebElement txtConfirmPassword;
	
	//Show create password
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.zeoauto.zeocircuit:id/txt_show_hide_con\" and @text=\"SHOW\"]")public WebElement ShowCoPassword;
	
	//Create account button
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.zeoauto.zeocircuit:id/btnSignUp\"]")public WebElement btnCreateAccount;
	
	//error text
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.zeoauto.zeocircuit:id/snackbar_text\"]")
	public WebElement errorTxt;
	
	//My profile icon
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/img_profile")
	public WebElement iconMyProfile;
	
	//Account
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/lin_password")
	public WebElement lnkaccount;
	
	//Delete account
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/txt_delete_account")
	public WebElement btnDeleteAccount;
	
	//popup label
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/txtHeader")
	public WebElement lblDeleteAccount;
	
	//Select reason
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/txt_reason_8")
	public WebElement optReason;
	
	//text area 
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/edt_note")
	public WebElement txtDescription;
	
	//Button delete account
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/btn_delete_account")
	public WebElement btnDelete;
	
	//verify Please tell us reason to delete account img
    @AndroidFindBy(xpath="//android.widget.Toast[@text=\"Please tell us reason to delete account\"]")
    public WebElement imgReason;
    
    //willl do it later button
    @AndroidFindBy(id="com.zeoauto.zeocircuit:id/btn_later")
    public WebElement btnlater;
	
	//global varible for drivermethod class
	DriverMethods dm;
	
	//SignUp steps
	public void SignUpSteps(String path,String name,String appPackage,String appActivity,AndroidDriver d) throws Exception
	{
		//initilize drivermethod class 
	    dm=new DriverMethods(d);
	    
		dm.implicitWait(10);
		
		//verify installiation of the app
		dm.verifyApp(appPackage);
		
		//Verify app activity
		dm.currentActivity(appActivity);
		
		//read data from the sheet
		ExcelUtils eu=new ExcelUtils(path,name);
		
		//if email password appear
		if(ReUsibleMethod.isElementPresent(d,By.xpath("//android.widget.TextView[@text=\"Continue with Email + Password\"]")))
		{	
			
		    //click on Continue with email+password
		    btnEmailPassword.click();
		    
		    //loop to iterate excel data
		    for(int r =1;r<=eu.getRows();r++)
		    {
		        //clear email field
		    	txtEnterEmail.clear();	
		    
		        //send email in the text field
		        txtEnterEmail.sendKeys(eu.getValues(r,0));
		        
		        //Store email
		        String eMail=txtEnterEmail.getAttribute("text");
		        
		        //Clear password
		        txtCreatePassword.clear();
		    
		        //Create password
		        txtCreatePassword.sendKeys(eu.getValues(r,1));
		        
		        //if show password is disabled
				if(ReUsibleMethod.isElementPresent(d,By.xpath("//android.widget.TextView[@resource-id=\"com.zeoauto.zeocircuit:id/txt_show_hide\" and @text=\"SHOW\"]")))
				{
				  //click on show password 
				  ShowCrPassword.click();
				}
		        
		        //String password
		        String crPass=txtCreatePassword.getAttribute("text");
		    
		        //if confirm password text field is not visible
		        if(!(ReUsibleMethod.isElementPresent(d, By.xpath("//android.widget.EditText[@resource-id=\"com.zeoauto.zeocircuit:id/edtConfirmPassword\"]"))))
		        {
		           //close keypad
		           dm.keyboard();
		        }
		        //Clear password
		        txtConfirmPassword.clear();
		        
		        //Confirm password
		        txtConfirmPassword.sendKeys(eu.getValues(r,2));
		        
		        //if show password is disabled
				if(ReUsibleMethod.isElementPresent(d,By.xpath("//android.widget.TextView[@resource-id=\"com.zeoauto.zeocircuit:id/txt_show_hide_con\" and @text=\"SHOW\"]")))
				{
				  //click on show password 
					ShowCoPassword.click();
				}
		        
		       //String password
		        String coPass=txtConfirmPassword.getAttribute("text");
		    
		        //click on Create account
		        btnCreateAccount.click();
		    
		    //Empety signup mail
		    if(eMail.equals("Enter Email") && !(crPass.equals("")) && !(coPass.equals(""))) 
		    {
		    	//Verify error text
		    	assertEquals(errorTxt.getText(),"Please enter email");
		    }
		    
		    //All the negitave cases incloding positive sign up
		    else if(!(eMail.equals("")) && !(crPass.equals("")) && !(coPass.equals("")))
		    {
		    	//Duplicate email
		    	if(eMail.equals(eu.getValues(1,0)) && crPass.equals(eu.getValues(1,1)) && coPass.equals(eu.getValues(1,2)))
		    	{
		    		//Verify error text
			    	assertEquals(errorTxt.getText(),"Email has already been taken");
		    	}
		    	
		    	//Invalid email
		    	else if(eMail.equals(eu.getValues(3,0)) && crPass.equals(eu.getValues(3,1)) && coPass.equals(eu.getValues(3,2)))
		    	{

		    		//Verify error text
			    	assertEquals(errorTxt.getText(),"Please enter proper email address");
		    	}
		    	
		    	//Mismatch password
		    	else if(eMail.equals(eu.getValues(4,0)) && crPass.equals(eu.getValues(4,1)) && coPass.equals(eu.getValues(4,2)))
		    	{

		    		//Verify error text
			    	assertEquals(errorTxt.getText(),"Password mismatch");
		    	}
		    	
		    	//Valid mail and passwords
		    	else
		    	{
		    		
		    		//Wait till my profile appears
		    		WebDriverWait ww=new WebDriverWait(d,Duration.ofSeconds(15));
		    		ww.until(ExpectedConditions.visibilityOf(iconMyProfile));

		    		//verify my profile 
		    		assertTrue(iconMyProfile.isDisplayed());
		    	}
		    }
		    }
		}
	}
	
	//Signup with google
	public void signUpWithGoogle(String path,String name,String appPackage,String appActivity,AndroidDriver d) throws Exception
	{
		//initilize drivermethod class 
	    dm=new DriverMethods(d);
	    
		dm.implicitWait(10);
				
		dm.activateApp(appPackage);
				
		//verify installiation of the app
		dm.verifyApp(appPackage);
				
		//Verify app activity
		dm.currentActivity(appActivity);
		
	    //click on continue with google
		btnContinueWithGoogle.click();
		
		//read data from the sheet
		ExcelUtils eu=new ExcelUtils(path,name);
		
		//store common locator for google accounts in collections
		ArrayList<WebElement> al=new ArrayList<WebElement>(d.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.google.android.gms:id/account_display_name\"]")));
		
		//Iterate and select perticular mail
		for(WebElement m:al)
		{
			//select perticular mail
			if(m.getText().equals(eu.getValues(1,3)))
			{
				//cselect mail
				m.click();
				
				//if connection invite popup appears
				if(ReUsibleMethod.isElementPresent(d,By.xpath("//android.widget.FrameLayout[@resource-id=\"com.zeoauto.zeocircuit:id/design_bottom_sheet\"]/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout")))
				{
					//click on will do it later
					btnlater.click();
				}
			break;
			}
		}
		
	}

	//Delete account
	public void deleteAccount(AndroidDriver d) throws Exception
	{
		//implicitly wait
		DriverMethods dm=new DriverMethods(d);
		dm.implicitWait(10);
				
		//click on my profile icon
		iconMyProfile.click();
						
		//Click on prefrences
		lnkaccount.click();
		
		//Click on delete account
		btnDeleteAccount.click();
		
		//Verify popup
		assertEquals(lblDeleteAccount.getText(),"Delete Account");
		
		//click on reason
		optReason.click();
		
		//loop
		for(int r=0;r<=1;r++)
		{
			if(r==0)
			{
				txtDescription.sendKeys("");
				
				//click on delete account
				btnDelete.click();
				
//				Thread.sleep(1000);
//				//Verify img
//				assertTrue(imgReason.isDisplayed());
			}
			else
			{
				txtDescription.sendKeys("Automated Test");
				
				//click on delete account
				btnDelete.click();
			}

		}
		
	}
}
