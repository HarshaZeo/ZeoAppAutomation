package ZeoOnboardingScripts;

import org.testng.annotations.*;

import ConfigFiles.BaseClass;
import POMClasses.LogOutMyProfile;
import POMClasses.LoginPage;
import POMClasses.SignUp;

public class OnboardingTests extends BaseClass{
	
	@Test(priority=1)
	public void googleSignUp() throws Exception
	{
		//Signup
		SignUp sp=new SignUp(driver);
		sp.signUpWithGoogle(signUpSheet,"ZeoSignUp",file.dataFromProperties("APP_PACKAGE"),file.dataFromProperties("APP_ACTIVITY2"),driver);
	
		//Logout steps
		LogOutMyProfile lop=new LogOutMyProfile(driver);
		lop.logOut(driver);
	}
	
	@Test(priority=2)
	public void signUpEmailPassword() throws Exception
	{
		//Signup
		SignUp sp=new SignUp(driver);
		sp.SignUpSteps(signUpSheet,"ZeoSignUp",file.dataFromProperties("APP_PACKAGE"),file.dataFromProperties("APP_ACTIVITY2"),driver);
	    sp.deleteAccount(driver);
	}
	
	@Test(priority=4)
	public void forgotPasswordAndChange() throws Exception
	{
		//forgot password and change password
		LoginPage lp=new LoginPage(driver);
		lp.forgotPassword(signInSheet,"ZeoSignIn", driver);
		lp.changePassword(changePasswordSheet, "Change Password", driver);
		
		//Logout steps
		LogOutMyProfile lop=new LogOutMyProfile(driver);
		lop.logOut(driver);
	}
	
	@Test(priority=3)
	public void login() throws Exception
	{
		//login steps
		LoginPage lp=new LoginPage(driver);
	    lp.loginSteps(signInSheet,"ZeoSignIn",file.dataFromProperties("APP_PACKAGE"),file.dataFromProperties("APP_ACTIVITY2"),driver);
	    
	  //Logout steps
	  LogOutMyProfile lop=new LogOutMyProfile(driver);
	  lop.logOut(driver);
	}

}
