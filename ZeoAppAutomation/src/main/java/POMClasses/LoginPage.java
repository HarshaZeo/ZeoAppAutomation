package POMClasses;

import static org.testng.Assert.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import ConfigFiles.DriverMethods;
import ConfigFiles.ExcelUtils;
import ConfigFiles.ReUsibleMethod;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {
	
	
	AppiumDriver driver;
	
	public LoginPage(AppiumDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this );
	}

	//Allow while using app permession
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
	public WebElement permission1;
	
	//Allow button.
	@AndroidFindBy(xpath="//android.widget.Button[@text='Allow']")public WebElement permission2;
	
	//email and password button
	@AndroidFindBy(xpath= "//android.widget.TextView[@text=\"Continue with Email + Password\"]")public WebElement btnEmailPassword;
	
	//login link
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Login']")public WebElement lnkLogin;
	
	//Email text field
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.zeoauto.zeocircuit:id/edtEmail\" and @hint=\"you@mail.com\"]")
	public WebElement txtEmail;
	
	//Password text field
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.zeoauto.zeocircuit:id/edtPassword\" and @hint=\"Password\"]")
	public WebElement txtPassword;
	
	//show password icon
	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id=\"com.zeoauto.zeocircuit:id/txt_show_hide\" and @text=\"SHOW\"])[2]")
	public WebElement iconShowPassword;
	
	//link forgot password
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.zeoauto.zeocircuit:id/txtForgetPassword\"]")
	public WebElement lnkForgotPassword;
	
	//verify forgot password label
	@AndroidFindBy(xpath="(//android.widget.TextView[@text=\"Forgot Password ?\"])[2]")
	public WebElement lblForgotPassword;
	
	//email text box 
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id=\"com.zeoauto.zeocircuit:id/edt_email\" and @hint=\"you@mail.com\"]")
	public WebElement txtFpEmail;
	
	//Button reset password
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"com.zeoauto.zeocircuit:id/btn_recover\"]")
	public WebElement btnResetPassword;
	
	//Login button
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/btnLogin")
	public WebElement btnLogin;
	
	//error text
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.zeoauto.zeocircuit:id/snackbar_text\"]")
	public WebElement errorTxt;
	
	//Forgot password text
	@AndroidFindBy(xpath="//android.widget.Toast[@text=\"A temporary password has been sent to your email id\"]")
	public WebElement forgotPassword;
	
	//Scrill gmail down to view new message
	@AndroidFindBy(id="com.google.android.gm:id/thread_list_view")
	public WebElement scroll;
	
	//Gmail sent
	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id=\"com.google.android.gm:id/subject\" and @text=\"Forgot Password\"])[1]")
	public WebElement tMail;
	
	//Forgot password mail short
	@AndroidFindBy(xpath="//android.widget.TextView[starts-with(@text,\"Password:\")]")
	public WebElement mailTxtS;
	
	//Delete forgot password mail
	@AndroidFindBy(id="com.google.android.gm:id/delete")
	public WebElement deleteMail;
	
	 //My profile icon
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/img_profile")
	public WebElement iconMyProfile;
	
	//Account
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/lin_password")
	public WebElement lnkaccount;
	
	//Change password
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/txt_password")
	public WebElement changePass;
	
	//Label change password
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/txtHeader")
	public WebElement lblchangePass;
	
	//enter password
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/edtPassword")
	public WebElement txtEnterPass;
	
	//Show enter password
	@AndroidFindBy(id="com.zeoauto.zeocircuit:id/imgPassword")
	public WebElement showEnterPass;
	
	//confirm password
    @AndroidFindBy(id="com.zeoauto.zeocircuit:id/edtConfirmPassword")
	public WebElement txtConfirmPass;
    
    //confirm password
    @AndroidFindBy(id="com.zeoauto.zeocircuit:id/imgConfirmPassword")
	public WebElement showConfirmPass;
    
    //Password reset succefully
    @AndroidFindBy(xpath="//android.widget.Toast[@text=\"Password reset successfully\"]")
    public WebElement imgPasswordReset;
    
    //Change password button
    @AndroidFindBy(id="com.zeoauto.zeocircuit:id/btnChangePassword")
	public WebElement btnChangePass;
	
	//Login positive & negitave steps
	public void loginSteps(String path,String name,String appPackage,String appActivity,AndroidDriver d) throws Exception
	{
		        //implicitly wait
				DriverMethods dm=new DriverMethods(d);
				dm.implicitWait(10);
				
				//verify installiation of the app
				dm.verifyApp(appPackage);
				
				ExcelUtils eu=new ExcelUtils(path,name);
					
				//click on Continue with email+password
				btnEmailPassword.click();
				
				//click on login
				lnkLogin.click();
				    
				//Current activaty of the app
				assertEquals(d.currentActivity(),appActivity);
				
				//iterate excel data
				for(int r =1;r<=eu.getRows();r++)
				{
				
				//clear email
				txtEmail.clear();	
					
				//Click on email text field
				txtEmail.sendKeys(eu.getValues(r,0));
				
				//store text from the email field
				String emailTxt=txtEmail.getAttribute("text");
				
				//Clear password
				txtPassword.clear();
				    
				//Click on password field
				txtPassword.sendKeys(eu.getValues(r,1));
				
				//if show password is disabled
				if(ReUsibleMethod.isElementPresent(d,By.xpath("(//android.widget.TextView[@resource-id=\"com.zeoauto.zeocircuit:id/txt_show_hide\" and @text=\"SHOW\"])[2]")))
				{
				  //click on show password 
				  iconShowPassword.click();
				}
				//store text from password field
				String passwordTxt=txtPassword.getAttribute("text");
				
					//Valid email and blank password
					if(!(emailTxt.equals("")) && passwordTxt.equals("Password"))
					{
						//Verify login bibbon should not be enabled
						assertFalse(btnLogin.isEnabled());
					}
					
					//all the cases incloding valid email & password
					else if(!(emailTxt.equals("")) && !(passwordTxt.equals("")))
					{
						if(emailTxt.equals(eu.getValues(5,0)) && passwordTxt.equals(eu.getValues(5,1)))
						{   
							//click on login
							btnLogin.click();
							
							//verify error texts
							if(errorTxt.getText().equals("Please try after 1 minute as you have crossed the maximum limit.") || errorTxt.getText().equals("Please try after less than a minute as you have crossed the maximum limit."))
							{
								//wait till error text disappiers
								WebDriverWait ww=new WebDriverWait(d,Duration.ofSeconds(10));
								ww.until(ExpectedConditions.invisibilityOf(errorTxt));

							    //wait for 25 sec and click on login button
							    Thread.sleep(25000);
							    //click on login
							    btnLogin.click();
							}
							
							   //if error text appear again click on login buttton again
							   if(ReUsibleMethod.isElementPresent(d, By.xpath("//android.widget.TextView[@resource-id=\"com.zeoauto.zeocircuit:id/snackbar_text\"]")))
							  {
								//explicitly wait until the error message disappears
								WebDriverWait w=new WebDriverWait(d,Duration.ofSeconds(15));
								w.until(ExpectedConditions.invisibilityOf(errorTxt));
								
								//click on login
								btnLogin.click();
							   }
						}
						      else
						     {
							   //click on login
							   btnLogin.click();
							
							   //Explicitly wait for the error message
							   WebDriverWait w=new WebDriverWait(d,Duration.ofSeconds(15));
							   w.until(ExpectedConditions.visibilityOf(errorTxt));
							
						       //Error notifactio for all the cases
						       assertEquals(errorTxt.getText(),("Invalid email password"));
						       
						       //wait till the error text disappears
						       w.until(ExpectedConditions.invisibilityOf(errorTxt));
						    }
					}
			  }
			}

	//Forgot password 
	public void forgotPassword(String path,String name,AndroidDriver d) throws Exception
	{
		//implicitly wait
		DriverMethods dm=new DriverMethods(d);
		dm.implicitWait(10);
		
		//click on Continue with email+password
		btnEmailPassword.click();
		
		//click on login
		lnkLogin.click();
		
		//click on forgot password
		lnkForgotPassword.click();
		
		//Navigate and verify label
		assertEquals(lblForgotPassword.getText(),"Forgot Password ?");
		
		//read data from the sheet
		ExcelUtils eu=new ExcelUtils(path,name);
		
		//Type email in the text field for the temporary password
		txtFpEmail.sendKeys(eu.getValues(5,0));
		
		//click on reset password button
		btnResetPassword.click();
		
//		Thread.sleep(1000);
//		//verify notifaction text
//		assertTrue(forgotPassword.isDisplayed());
				
		//open Gmail
		dm.activateApp("com.google.android.gm");
		
		//if mail does not appier
		if(!(ReUsibleMethod.elementPresent(d,By.xpath("(//android.widget.TextView[@resource-id=\"com.google.android.gm:id/subject\" and @text=\"Forgot Password\"])[1]"))))
		{
			//Scroll down 	
			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId", 
					((RemoteWebElement) scroll).getId(),
				"direction", "down","percent", 0.75
			));
		}	
	    
	    //click on mail sent
		tMail.click();
		
		//get temporary password
		String tpass=mailTxtS.getText();
			
		//delete mail
		deleteMail.click();	
		
		Thread.sleep(2000);	
		//Close Gmail
		dm.closeApp("com.google.android.gm");
		
		//Click on email text field
		txtEmail.sendKeys(eu.getValues(1,0));

		//Click on password field
		txtPassword.sendKeys(tpass.substring(10,20));
		
		//click on login
		btnLogin.click();
	}
	
	//Change password
	public void changePassword(String path,String name,AndroidDriver d) throws Exception
	{
		//implicitly wait
		DriverMethods dm=new DriverMethods(d);
		dm.implicitWait(10);
		
		//click on my profile icon
		iconMyProfile.click();
				
		//Click on prefrences
		lnkaccount.click();
				
		//Change password
		changePass.click();
		
		//Verify change password label
		assertEquals(lblchangePass.getText(),"Change Password");
		
		//click on show enter password
		showEnterPass.click();
		
		//Click on confirm enter password
		showConfirmPass.click();
		
		//Read data from the sheet
		ExcelUtils eu=new ExcelUtils(path,name);
		
		//iterate excel data
		for(int r =1;r<=eu.getRows();r++)
		{
		
		//clear email
		txtEnterPass.clear();	
			
		//Click on email text field
		txtEnterPass.sendKeys(eu.getValues(r,0));
		
		//store text from the email field
		String ePassword=txtEnterPass.getAttribute("text");
		
		//Clear password
		txtConfirmPass.clear();
		    
		//Click on password field
		txtConfirmPass.sendKeys(eu.getValues(r,1));
		
		//store text from password field
		String cPassword=txtConfirmPass.getAttribute("text");
		
		//click on chnage password
		btnChangePass.click();
		
		//blank enter password and blank confirm password
		if(ePassword.equals("Enter Password") && cPassword.equals("Confirm Password"))
		{
			//Verify text
			assertEquals(errorTxt.getText(),"Please enter password?");
		}
		
		//Valid enter password and blank confirm password
		else if(ePassword.equals(eu.getValues(2, 0)) && cPassword.equals("Confirm Password"))
		{
			//Verify text
			assertEquals(errorTxt.getText(),"Please confirm password");
		}
		
		//valid enter password and invalid enter password
		else if(ePassword.equals(eu.getValues(3, 0)) && cPassword.equals(eu.getValues(3, 1)))
		{
			//Verify text
			assertEquals(errorTxt.getText(),"Password does not match");
		}
		
		//Both are valid
		else
		{
			//Verify text
//			assertTrue(imgPasswordReset.isDisplayed());
//				
//			//Wait till image gets disappired
//			WebDriverWait ww=new WebDriverWait(d,Duration.ofSeconds(15));
//			ww.until(ExpectedConditions.invisibilityOf(imgPasswordReset));
		}
			
		}
	}

	//Login 
	public void login(String path,String name,String appPackage,String appActivity,AndroidDriver d) throws Exception
	{
		//implicitly wait
		DriverMethods dm=new DriverMethods(d);
		dm.implicitWait(10);
		
		//verify installiation of the app
		dm.verifyApp(appPackage);
		
		ExcelUtils eu=new ExcelUtils(path,name);
			
		//click on Continue with email+password
		btnEmailPassword.click();
		
		//click on login
		lnkLogin.click();
		    
		//Current activaty of the app
		assertEquals(d.currentActivity(),appActivity);
		
		//Click on email text field
		txtEmail.sendKeys(eu.getValues(5,0));
		    
		//Click on password field
		txtPassword.sendKeys(eu.getValues(5,1));
		
		//click on login
		 btnLogin.click();
	}
}
