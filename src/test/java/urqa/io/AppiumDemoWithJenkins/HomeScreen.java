package urqa.io.AppiumDemoWithJenkins;


import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomeScreen extends BaseScreen{

	String app_package_name = "com.google.android.gms.example.apidemo:id/";

    By listview_button_locator = By.id(app_package_name + "listView");
    By apidemo_button_locator = By.id(app_package_name + "apiDemo");
	    
    public HomeScreen(WebDriver driver) {
    	super(driver);
    }

    
    public void checkHomeScreen() {
    	
    	waitForClickabilityOf(listview_button_locator);
    	waitForClickabilityOf(apidemo_button_locator);
    }
    
    
    public void goHomeScreen(){
    	
    	
    	WebElement webEllement = null ;
    	for(int i = 0 ; i < 5 ; ++ i){
    		try{
            	webEllement = driver.findElement(listview_button_locator);
    		}catch(Exception e){
    			
    		}
        	
        	if(webEllement == null){
        		driver.navigate().back();
        		try {
    				Thread.sleep(10);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}
        	else {
        		if(driver.findElement(listview_button_locator).isDisplayed())
        			break;
        	}
    		
    	}
    }
    
    public void clickAdsAPIDemoBtn(){
    	click(apidemo_button_locator);
    }
    
    public void clickAdsListViewBtn(){
    	click(listview_button_locator);
    }
    
    

}
