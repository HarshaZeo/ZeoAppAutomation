package ConfigFiles;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;

public class DriverMethods {
	
		AndroidDriver driver;
		
		public DriverMethods(AndroidDriver driver) {
			this.driver=driver;
		}
		
		public void installApp(String path) {
			driver.installApp(path);
		}
		
		public void verifyApp(String packageName) {
			boolean verify = driver.isAppInstalled(packageName);
			System.out.println(verify);
		}
		
		public void unInstallApp(String packageName) {
			driver.removeApp(packageName);
		}
		
		public void activateApp(String packageName) {
			driver.activateApp(packageName);
		}
		
		public void runInBackground(int time) {
			driver.runAppInBackground(Duration.ofSeconds(time));
		}
		
		public void appStatus(String packageName) {
			ApplicationState status = driver.queryAppState(packageName);
			System.out.println(status);
		}
		
		public void currentActivity(String currentActivity) {
			boolean verify = driver.currentActivity().equals(currentActivity);
			System.out.println(verify);
		}
		
		public void closeApp(String packageName) {
			driver.terminateApp(packageName);
		}
		
		public void notification() {
			driver.openNotifications();
		}
		
		public void keyboard() {
			driver.hideKeyboard();
		}
		
		public void data() {
			driver.toggleData();
		}
		
		public void wifi() {
			driver.toggleWifi();
		}
		
		public void airplaneMode() {
			driver.toggleAirplaneMode();
		}
		
		public void potraitOrientation() {
			ScreenOrientation screen = driver.getOrientation();
			driver.rotate(screen.PORTRAIT);
		}
		
		public void landscapeOrientation() {
			ScreenOrientation screen = driver.getOrientation();
			driver.rotate(screen.LANDSCAPE);
		}
		
		public void setClipboard(String text) {
			driver.setClipboardText(text);
		}
		
		public void getClipboard() {
			String txt = driver.getClipboardText();
			System.out.println(txt);
		}
		
		public void implicitWait(int time) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		}
		
		public void toast() {
			String msg = driver.findElement(AppiumBy.xpath("//android.widget.Toast[1]")).getText();
			System.out.println(msg);
		}
		
		public void getContexts() {
			Set<String> contexts = driver.getContextHandles();
			System.out.println(contexts.size());
			for (String ch : contexts) {
				System.out.println(ch);
				if(ch.contains("WEBVIEW")) {
					driver.context(ch);
					break;
				}
			}
		}
	}
