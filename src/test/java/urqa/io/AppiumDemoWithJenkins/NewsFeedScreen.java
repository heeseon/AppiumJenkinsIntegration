package urqa.io.AppiumDemoWithJenkins;
import io.appium.heeseon.MobileElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NewsFeedScreen extends NewBaseScreen {

	By view = By.id("android:id/list"); //android.support.v7.widget.RecyclerView

	By translation = By.id(app_package_name + "see_translation");
	By story_message = By.id(app_package_name + "feed_story_message");
	By newsContainer = By.id(app_package_name + "newsfeed_container");
	By new_stories = By.id(app_package_name + "new_stories_button");
	
	
	public NewsFeedScreen(WebDriver driver, String packageName) {
		super(driver, packageName);
		// TODO Auto-generated constructor stub
	}
	
	public void checkScreen(){
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(newsContainer));
	    
	}
	
	public WebElement checkTranlation(){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(translation));
	        WebElement element = (WebElement) driver.findElement(translation);
	        
	        return element;
	        
		}catch(Exception e){
			return null;
		}

	}
	
	
	public void clickTranslation(){
		
		 
		for(int i = 0 ; i < 3; ){
			WebElement element = checkTranlation();
			 if( element == null ){
			 	swipeUp();
			 }
			 else {
				 ++i;
			 	element.click();
			 	
			 }
			 
			 try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	public WebElement checkContinuousReading(){
		
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(story_message));
	        	        
		}catch(Exception e){
			return null;
		}
				
		
		By view_locator = By.className("android.view.View");
		
		
    	WebElement parentElement = null;
    	List views = null;
    	
    	try{
    		parentElement = (WebElement) driver.findElement(story_message);
    		views = (List) parentElement.findElements(view_locator);
        	
        	for(int i = 0 ; i < views.size() ; ++ i){
        		MobileElement element = (MobileElement) views.get(i);
        		
        		if ( element.getText() != null &&  element.getText().endsWith("Continue Reading") )
        			return element;
        		
        	}
    	
    	}catch(Exception e){
    		return null;
    	}
    	
		return null;

	}
	
	public void clickContinuousReading(){
		
		for(int i = 0 ; i < 3; ){
			WebElement element = checkContinuousReading();
		    
			 if( element == null ){
				 swipeUp();
			 }
			 else {
				 ++i;
			 	element.click();
			 	try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	
			 	driver.navigate().back();
			 	
			 	try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	
			 	swipeUp();
			 	
			 }
			 
			 try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}

	}
	
	public void displayNewStories(){
		
		WebElement element = (WebElement) driver.findElement(new_stories);
		element.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
