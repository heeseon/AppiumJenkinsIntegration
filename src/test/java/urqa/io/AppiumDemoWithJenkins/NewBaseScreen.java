package urqa.io.AppiumDemoWithJenkins;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.heeseon.MobileDriver;
import io.appium.heeseon.TouchAction;
import io.appium.heeseon.TouchShortcuts;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by nishant on 13/09/14.
 */
public class NewBaseScreen {

	public static String snapshotDestPath = ""; 
    protected WebDriver driver;
    
    String app_package_name;

    public NewBaseScreen(WebDriver driver2, String packageName) {
        this.driver = driver2;
        app_package_name = packageName;
    }

    protected void waitForVisibilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected void waitForClickabilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    protected void waitForVisibilityOf(By element1, By element2) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        try{
        	wait.until(ExpectedConditions.visibilityOfElementLocated(element1));
        }catch(Exception e){
        	
        	wait.until(ExpectedConditions.visibilityOfElementLocated(element2));
        }
        
    }
    
    protected void click(By element){
    	driver.findElement(element).click();
    }
    
    protected void click(WebElement element){
    	element.click();
    }
    
    protected void tap(WebElement element){
    	new TouchAction((MobileDriver) driver).tap(element).perform();
    }
    
    protected void swipeUp(){
    	//new TouchAction(driver).flick(0,-10).perform();
    	Dimension size = driver.manage().window().getSize();
    	int startX = size.width/2;
    	int startY = (int) (size.height *0.90);
    	int endY = (int) (size.height *0.10);
    	
    	
    	
	    	((TouchShortcuts) driver).swipe(startX,startY,startX,endY,1000);
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	
    }
    
    protected void swipeDown(){
    	Dimension size = driver.manage().window().getSize();
    	int startX = size.width/2;
    	int startY = (int) (size.height *0.20);
    	int endY = (int) (size.height *0.90);
    		((TouchShortcuts) driver).swipe(startX,startY,startX,endY,1000);
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	//new TouchAction(driver).flick(0,-10).perform();
    	//((TouchShortcuts) driver).swipe(100,100,100,200,2000);
    	//new TouchAction((MobileDriver) driver).moveTo( 0 , -100).perform();
    }
    
    public void takeScreenShot() { 
		// Set folder name to store screenshots. 
		String destDir = snapshotDestPath + File.separator + "screenshots"; 
		// Capture screenshot. 
		File scrFile = FacebookTest.driver.getScreenshotAs(OutputType.FILE); 
		// Set date format to set It as screenshot file name. 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy__hh_mm_ss"); 
		// Create folder under project with name "screenshots" provided to destDir. 
		new File(destDir).mkdirs(); 
		// Set file name using current date time. 
		String destFile = dateFormat.format(new Date()) + ".png"; 
		try { 
			// Copy paste file at destination folder location 
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile)); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		 
	}

    public void scrollPageUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.50);
        swipeObject.put("startY", 0.95);
        swipeObject.put("endX", 0.50);
        swipeObject.put("endY", 0.01);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }


    public void swipeLeftToRight() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.01);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.9);
        swipeObject.put("endY", 0.6);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipeRightToLeft() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.5);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipeFirstCarouselFromRightToLeft() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.2);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.2);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void performTapAction(WebElement elementToTap) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("x", (double) 360); // in pixels from left
        tapObject.put("y", (double) 170); // in pixels from top
        tapObject.put("element", Double.valueOf(((RemoteWebElement) elementToTap).getId()));
        js.executeScript("mobile: tap", tapObject);
    }
}
