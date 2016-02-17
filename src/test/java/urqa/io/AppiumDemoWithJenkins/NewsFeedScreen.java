package urqa.io.AppiumDemoWithJenkins;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NewsFeedScreen extends NewBaseScreen {

	By view = By.id("android:id/list"); //android.support.v7.widget.RecyclerView

	By translation = By.id(app_package_name + "see_translation");
	By story_message = By.id(app_package_name + "feed_story_message");

	//Continue Reading 갖는 android.view.View 클래스 가 아래 있는지 보고 있으면 클릭 

	//android.support.v7.widget.RecyclerView 로 스크롤 범위 지정
	
	
	
	public NewsFeedScreen(WebDriver driver, String packageName) {
		super(driver, packageName);
		// TODO Auto-generated constructor stub
	}
	
	public void checkScreen(){
		
	}
	
	public boolean checkTranlation(){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 3000);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(translation));
	        driver.findElement(translation);
		}catch(Exception e){
			return false;
		}
		
		return true;
	}
	
	
	public boolean checkContinuousReading(){
		
		WebElement element;
		
		try {
			element = driver.findElement(story_message);
	       
		}catch(Exception e){
			return false;
		}
		
		//WebElement 
		
		
		
		return true;
	}
	
	public void clickTranslation(){
		click(translation);
	}
	

}
