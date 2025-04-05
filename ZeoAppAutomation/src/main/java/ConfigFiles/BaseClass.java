package ConfigFiles;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import POMClasses.LogOutMyProfile;
import POMClasses.LoginPage;
import POMClasses.SignUp;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {
		
		public AppiumDriverLocalService service;
		public FileUtility file = new FileUtility();
		public AndroidDriver driver;
		public static AndroidDriver sdriver;
		public DriverMethods dUtils;
		public GestureUtility gUtils;
		public static LoginPage lp;
		public static SignUp sp;
		
		//Excel local paths
		public String signUpSheet="E:\\Selenium web driver-utlities\\ZeoAppautomation\\src\\test\\resources\\ZeoAppSignUp.xlsx";
		
		public String signInSheet="E:\\Selenium web driver-utlities\\ZeoAppautomation\\src\\test\\resources\\ZeoAppSignIn.xlsx";
		
		public String changePasswordSheet="E:\\Selenium web driver-utlities\\ZeoAppAutomation\\src\\test\\resources\\ChangePassworddata.xlsx";
		
		public String AddStopsSheet="E:\\Selenium web driver-utlities\\ZeoAppAutomation\\src\\test\\resources\\Add Stops.xlsx";
		
		@BeforeSuite
		public void startServer() {
			File f = new File("C:\\Users\\User\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");

			service = new AppiumServiceBuilder().withAppiumJS(f).withIPAddress("127.0.0.1").usingPort(4723)
					.withTimeout(Duration.ofSeconds(300)).build();

			service.start();
		}

		@BeforeClass
		public void activateApp() throws Exception {

			String platform = file.dataFromProperties("PLATFORM_NAME");
			String automation = file.dataFromProperties("AUTOMATION_NAME");
			String device = file.dataFromProperties("DEVICE_NAME");
			String UDID = file.dataFromProperties("UDID");
			String noRest = file.dataFromProperties("NO_REST");
			String apiPolicy = file.dataFromProperties("API_POLICY_ERROR");
			String permission = file.dataFromProperties("GRANT_PERMISSION");
			String appPackage = file.dataFromProperties("APP_PACKAGE");
			String activity = file.dataFromProperties("APP_ACTIVITY");

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("platformName", platform);
			dc.setCapability("automationName", automation);
			dc.setCapability("UDID", UDID);
			dc.setCapability("deviceName", device);
			dc.setCapability("noReset", noRest);
			dc.setCapability("ignoreHiddenApiPolicyError", apiPolicy);
			dc.setCapability("autoGrantPermission", permission);
			dc.setCapability("appPackage", appPackage);
			dc.setCapability("appActivity", activity);

//			dc.setCapability("app", "C:\\Users\\User\\OneDrive\\Desktop\\Appium\\apk files\\General-Store.apk");
			URL u = new URL("http://localhost:4723");
			driver = new AndroidDriver(u,dc);
			
			sdriver=driver;
			dUtils = new DriverMethods(driver);
			gUtils = new GestureUtility(driver);
			
			//activate app
			dUtils.activateApp(appPackage);
			
		}

		@BeforeMethod
		public void Login() throws Exception {
			//dUtils.activateApp(file.dataFromProperties("APP_PACKAGE"));
			
			//login steps
		    lp=new LoginPage(driver);
		    lp.login(signInSheet,"ZeoSignIn",file.dataFromProperties("APP_PACKAGE"),file.dataFromProperties("APP_ACTIVITY2"),driver);
		}
		
		@AfterMethod
		public void Logout() throws IOException {
			
			//Logout steps
			LogOutMyProfile lop=new LogOutMyProfile(driver);
			lop.logOut(driver);
		}
		
		@AfterClass
		public void closeApp() throws Exception {
			
			//close app
			dUtils.closeApp(file.dataFromProperties("APP_PACKAGE"));
			//dUtils.unInstallApp(file.dataFromProperties("APP_PACKAGE"));
		}

		@AfterSuite
		public void stopServer() {
			service.stop();
		}
	}
