package ConfigFiles;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
public class GestureUtility {

		AndroidDriver driver;
		
		public GestureUtility(AndroidDriver driver) {
			this.driver=driver;
		}

		public void longClickByElement(WebElement element, int time) {
			((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", time));
		}

		public void clickByElement(WebElement element) {
			((JavascriptExecutor) driver).executeScript("mobile: clickGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
		}

		public void doubleClickByElement(WebElement element) {
			((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
		}

		public void longClickByCordinates(int x, int y, int time) {
			((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
					ImmutableMap.of("x", x, "y", y, "duration", time));
		}

		public void clickByCordinates(int x, int y) {
			((JavascriptExecutor) driver).executeScript("mobile: clickGesture", ImmutableMap.of("x", x, "y", y));
		}

		public void doubleClickByCordinates(int x, int y) {
			((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of("x", x, "y", y));
		}

		public void dragAndDrop(WebElement element, int x, int y) {
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "endX", x, "endY", y));
		}

		public void zoomIn(WebElement element, double percent) {
			((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "percent", percent));
		}

		public void zoomOut(WebElement element, double percent) {
			((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "percent", percent));
		}

		public void swipeByCordinates(int left, int top, int width, int height, String direction, double percent) {
			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("left", left, "top", top,
					"width", width, "height", height, "direction", direction, "percent", percent));
		}

		public void swipeByElement(WebElement element, String direction, double percent) {
			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
					((RemoteWebElement) element).getId(), "direction", direction, "percent", percent));
		}

		public WebElement scrollByText(String text) {
			WebElement elementText = driver.findElement(AppiumBy
					.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
			return elementText;
		}

		public WebElement scrollByxPath(String xpath) {
			WebElement elementxPath = driver.findElement(AppiumBy
					.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(xpath(\"" + xpath + "\"));"));
			return elementxPath;
		}
	}
