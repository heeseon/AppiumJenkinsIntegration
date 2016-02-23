package urqa.io.AppiumDemoWithJenkins;
import io.appium.heeseon.AppiumDriver;
import io.appium.heeseon.MobileDriver;
import io.appium.heeseon.MobileElement;
import io.appium.heeseon.TouchShortcuts;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainScreen extends NewBaseScreen {

	By news_feed_tab = By.id(app_package_name + "news_feed_tab");
	By friend_request_tab = By.id(app_package_name + "friend_requests_tab");
	By notification_tab = By.id(app_package_name + "notifications_tab");
	By bookmark_tab = By.id(app_package_name + "bookmarks_tab");
	By search = By.id(app_package_name + "search_edit_text");
	
	public MainScreen(MobileDriver driver, String packageName) {
		super(driver, packageName);
		// TODO Auto-generated constructor stub
	}
	
	public void checkScreen(){
		WebDriverWait wait = new WebDriverWait(driver, 60000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(news_feed_tab));
	}
	
	public void clickFeedTab(){
		click(news_feed_tab);
	}
	
	public void clickFriendRequstTab(){
		click(friend_request_tab);
	}
	
	public void clickNotificationTab(){
		click(notification_tab);
	}
	
	public void clickBookmarkTab(){
		click(bookmark_tab);
	}
	
	public void inputWordinSearch(String word){
		
		MobileElement element = (MobileElement) driver.findElement(search);
		
		if(element != null)
			element.click();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(element != null)
			element.sendKeys(word);
		
	}
	
	public void inputEnterKeyinSearch(){
		String text = driver.findElement(search).getText() + "\n";
		driver.findElement(search).sendKeys(text);
	}
	

	public void click(WebElement element){
		element.click();
	}
	

}
