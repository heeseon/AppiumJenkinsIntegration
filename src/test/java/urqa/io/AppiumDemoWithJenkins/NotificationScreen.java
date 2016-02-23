package urqa.io.AppiumDemoWithJenkins;
import io.appium.heeseon.MobileElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NotificationScreen extends NewBaseScreen {

	By notificationContainer = By.id(app_package_name + "notificationlist_container");
	By notification_view = By.id(app_package_name + "notifications_title_view");
	
	By fb_logo = By.id(app_package_name + "fb_logo_up_button");
	
	By title_bar = By.id(app_package_name + "titlebar");
	
	By title_wrapper = By.id(app_package_name + "logo_title_wrapper");
	
	public NotificationScreen(WebDriver driver, String packageName) {
		super(driver, packageName);
		// TODO Auto-generated constructor stub
	}
	
	public void checkScreen(){
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(notificationContainer));
	   
	}
	
	public void visitFavorites(){
		
		
    	List views = null;
    	views = (List) driver.findElements(notification_view);
    	
    	for(int i = 0 ; i < views.size() && i < 3; ++ i){
    		MobileElement element = (MobileElement) views.get(i);
    		element.click();
    		
    		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		element = null;
    		
    		try{
    			
    			try {
    				element = (MobileElement) driver.findElement(fb_logo);
    				Thread.sleep(3000);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    			if(element != null)
    				element.click();
    			
    			try {
    				Thread.sleep(5000);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    		}catch(Exception e){
    			
    		}
    		
    	}

	}
	
}
