package urqa.io.AppiumDemoWithJenkins;
import io.appium.heeseon.MobileElement;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FriendRequestScreen extends NewBaseScreen {

	
	By friendContainer = By.id(app_package_name + "friend_requestlist_container");
	By friend_view_group = By.id("android:id/list");
	By friend_name = By.id(app_package_name + "friend_request_title_view");
	By may_friend = By.id(app_package_name + "person_you_may_know_extra_text");
	By entirty_card = By.id(app_package_name + "entity_card_container");
	
	public FriendRequestScreen(WebDriver driver, String packageName) {
		super(driver, packageName);
		// TODO Auto-generated constructor stub
	}
	
	public void checkScreen(){
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(friendContainer));

	}
	
	public void visitFriends(){
		
		List views = null;
		By view_locator = By.className("android.view.ViewGroup");
		List group = (List) driver.findElements(friendContainer);
		
		if(group.size() > 0){
			MobileElement group_element = (MobileElement) group.get(0);
			List groups = group_element.findElements(view_locator);
	    	
	    	for(int i = 0 ; i < groups.size() ; ++ i){
	    		MobileElement each_group = (MobileElement) groups.get(i);
	    		
	    		MobileElement element = null;
	
	    		try{
	    			element = each_group.findElement(friend_name);
	    		}catch(Exception e){
	    			try{
	    				element = each_group.findElement(may_friend);
	    				
	    			}catch(Exception e1){
	    				
	    			}
	    		}
	    		
	    		if(element != null){
	    			element.click();
	        		
	        		try {
	    				Thread.sleep(5000);
	    			} catch (InterruptedException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	        		
	        		element = null;
	        		try{
	        			element = (MobileElement) driver.findElement(entirty_card);
	        		}catch(Exception e){
	        		}
	        		if(element != null)
	        			driver.navigate().back();
	        		

	        		try {
	    				Thread.sleep(5000);
	    			} catch (InterruptedException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}    
	        		
	    		}
			
		}

		}
		
	
	}
	

}
