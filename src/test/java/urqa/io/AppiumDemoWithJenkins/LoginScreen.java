package urqa.io.AppiumDemoWithJenkins;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginScreen extends NewBaseScreen {

    By userId = By.id(app_package_name + "login_username");
    By userPW = By.id(app_package_name + "login_password");
    By login = By.id(app_package_name + "login_login");

	public LoginScreen(WebDriver driver, String packageName) {
		super(driver, packageName);
		// TODO Auto-generated constructor stub
		//checkScreen();
	}
	
	public void checkScreen(){
		WebDriverWait wait = new WebDriverWait(driver, 100000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        
        driver.findElement(userId).sendKeys("");
        driver.findElement(userId).click();
        driver.findElement(userPW).sendKeys("");
        driver.findElement(userPW).click();
        driver.findElement(login).click(); 
        
	}
	
	public void login(){
		
        
        
	}

}
