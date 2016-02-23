package urqa.io.AppiumDemoWithJenkins;
import io.appium.heeseon.MobileElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BookmarkScreen extends NewBaseScreen {

	By bookmarklist = By.id(app_package_name + "bookmarks_list");
	By fb_logo = By.id(app_package_name + "fb_logo_up_button");
	
	public BookmarkScreen(WebDriver driver, String packageName) {
		super(driver, packageName);
		// TODO Auto-generated constructor stub
		
	}
	
	public void checkScreen(){
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(bookmarklist));
	        	        
		
	}
	
	public void visitFavorites(){
		
		By view_locator = By.className("android.view.ViewGroup");
		
    	List views = null;
    	views = (List) driver.findElements(view_locator);
    	
    	for(int i = 0 ; i < views.size() && i < 3; ++ i){
    		
    		MobileElement element = (MobileElement) views.get(i);
    		element.click();
    		
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		element = null;
    			
    			try {
    				element = (MobileElement) driver.findElement(fb_logo);
    				Thread.sleep(5000);
    				if(element != null)
        				element.click();
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				
    				while(true){
        				try{
        					checkScreen();
        					break;
        				}catch(Exception e1){
        					driver.navigate().back();
        				}
        				
        				try {
							Thread.sleep(500);
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
    					
    				}
    				
    			}
    			
    		
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	}	
		
	}
	
	

}
