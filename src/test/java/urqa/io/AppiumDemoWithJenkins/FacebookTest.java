package urqa.io.AppiumDemoWithJenkins;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.TouchShortcuts;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.remote.RemoteWebDriver;

//import scenarios.AndroidSetup;


public class FacebookTest {
	public static AndroidDriver driver;

	
	String facebook_package_name = "com.facebook.katana:id/";
	By root = By.id("android:id/content");
	By current = root;
	
	
	
    public void getTestItem(){
    	
    	
    	
    }
	
	public FacebookTest(){
		

		driver = AndroidSetup.driver;
	    
		
		/*
		home = new HomeScreen(driver);
		adsAPIDemo = new AdsAPIDemoScreen(driver);
		adsListView = new AdsListViewScreen(driver);
		
		*/
		System.err.println("------------------------------constructor--------------------------------");
	}
	
    //테스트 전처리 구문 
    @Before
    public void setUp() throws Exception {
    	System.err.println("------------------------------AppiumDemo.setUp--------------------------------");
    }
    @After
    public void afterTest() throws Exception {
    	System.err.println("------------------------------AppiumDemo.afterTest--------------------------------");
    	//driver.quit(); //세션 생성을 위해 사용했던 리소스 해제 
    }

    //테스트 전처리 구문 
    @BeforeClass
    public static void setUpInitialize() throws Exception {
    	try {
			AndroidSetup.prepareAndroidForAppium("/Users/hwangheeseon/Documents/workspace3/AppiumDemo/apps", "com.facebook.katana_50.0.0.10.54-16053538_minAPI15.apk", "");
			//JUnitExecutionTestListener.prepareJUnitExecutionListener();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.err.println("------------------------------AppiumDemo.setUpInitialize--------------------------------");
 //   	driver = AndroidSetup.prepareAndroidForAppium("/Users/hwangheeseon/Documents/workspace3/AppiumDemo/apps", "app-debug.apk");
    	
    	
    }

  //테스트 후처리 구문 
    @AfterClass
    public static void tearDown() throws Exception {
    	System.err.println("------------------------------AppiumDemo.tearDown--------------------------------");
    	AndroidSetup.destroyAndroidForAppium();
    }
    
    
    
/*
    //테스트 
    @Test
    public void check1Test()
    {
    	
    	home.checkHomeScreen();
    	
    	
    	
    }
    
    @Test
    public void check2Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
   */


    @Test
    public void check3Test(){
        // String app_package_name = "com.example.swipetest:id/";
    	LoginScreen login = new LoginScreen(driver, facebook_package_name);
	    MainScreen main = new MainScreen(driver, facebook_package_name);
        login.checkScreen();
        login.login();
        main.checkScreen();
    }
   
    @Test
    public void check4Test(){
        // String app_package_name = "com.example.swipetest:id/";
    	LoginScreen login = new LoginScreen(driver, facebook_package_name);
	    MainScreen main = new MainScreen(driver, facebook_package_name);
        
    	for(int i=0; i < 10 ; ++i){

        	Dimension size = driver.manage().window().getSize();
        	int startX = size.width/2;
        	int startY = (int) (size.height *0.90);
        	int endY = (int) (size.height *0.10);
        	
        	((TouchShortcuts) driver).swipe(startX,startY,startX,endY,1000);
    	    
    	    try {
    			Thread.sleep(5000);
    		} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
    	}

        
        
    }    
    
    @Test
    public void check5Test(){
        // String app_package_name = "com.example.swipetest:id/";
    	LoginScreen login = new LoginScreen(driver, facebook_package_name);
	    MainScreen main = new MainScreen(driver, facebook_package_name);
        
    	for(int i=0; i < 10 ; ++i){

        	Dimension size = driver.manage().window().getSize();
        	int startX = size.width/2;
        	int startY = (int) (size.height *0.30);
        	int endY = (int) (size.height *0.90);
        	((TouchShortcuts) driver).swipe(startX,startY,startX,endY,1000);
    	    try {
    			Thread.sleep(5000);
    		} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
    	}
    	
    	        
    }     
    
    
    
    
    
    
    
    
    
    
    
    
/*
    @Ignore
    @Test
    public void check4Test(){

    }

    @Test
    public void check5Test(){
        throw new RuntimeException();
    }
        
    
    /*
    @Test
    public void check3Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check4Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check5Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check6Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
   
    @Test
    public void check7Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check8Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check9Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
   
    //테스트 
    @Test
    public void check10Test()
    {
    	
    	home.checkHomeScreen();
    	
    	
   	
    	
    }
    
    
    
    @Test
    public void check11Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check12Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check13Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check14Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check15Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check16Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check17Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check18Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    //테스트 
    @Test
    public void check19Test()
    {
    	
    	home.checkHomeScreen();
    	
    	
    	
    	
    }
    
   
    
    
    @Test
    public void check21Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check22Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check23Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check24Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check25Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check26Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check27Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    //테스트 
    @Test
    public void check28Test()
    {
    	
    	home.checkHomeScreen();
    	
    	
    }
    
    
    
    @Test
    public void check29Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check30Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check31Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check32Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check33Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check34Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check35Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check36Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    //테스트 
    @Test
    public void check37Test()
    {
    	
    	home.checkHomeScreen();
    	
    	
    	
    	
    }
    
    
    
    @Test
    public void check38Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check39Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check40Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check41Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check42Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check43Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check44Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check45Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    //테스트 
    @Test
    public void check46Test()
    {
    	
    	home.checkHomeScreen();
    	
    	
    	
    	
    }
    
    
    
    @Test
    public void check47Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check48Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check49Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check50Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check51Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check52Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check53Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check54Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    //테스트 
    @Test
    public void check55Test()
    {
    	
    	home.checkHomeScreen();
    	
    
    
    	
    }
    
    
    
    @Test
    public void check56Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check57Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check58Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check59Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check60Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check61Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check62Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check63Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    //테스트 
    @Test
    public void check64Test()
    {
    	
    	home.checkHomeScreen();
    	
    	
    	
    
    }
    
    
    
    @Test
    public void check65Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check66Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check67Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check68Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check69Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check70Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check71Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check72Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    //테스트 
    @Test
    public void check73Test()
    {
    	
    	home.checkHomeScreen();
    	
    
    }
    
    
    
    @Test
    public void check74Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check75Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check76Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check77Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check78Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check79Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check80Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check81Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    //테스트 
    @Test
    public void check82Test()
    {
    	
    	home.checkHomeScreen();
    	
    	
    	
    	
    }
    
    
    
    @Test
    public void check83Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check84Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check85Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check86Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check87Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check88Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check89Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check90Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    //테스트 
    @Test
    public void check91Test()
    {
    	
    	home.checkHomeScreen();
    	
    	
    	
    	
    }
    
    
    
    @Test
    public void check92Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check93Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check94Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check95Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check96Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check97Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check98Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check99Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    @Test
    public void check100Test()
    {
    	
    	home.checkHomeScreen();
    	
    	
    	
    }
    
    */
    /*
    //테스트 
    @Test
    public void check101Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    /*	
    	
    }
    
    
    
    @Test
    public void check102Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check103Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check104Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check105Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check106Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check107Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check108Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check109Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    
    //테스트 
    @Test
    public void check110Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    	/*
    	
    }
    
    
    
    @Test
    public void check111Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check112Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check113Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check114Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check115Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check116Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check117Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check118Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    //테스트 
    @Test
    public void check119Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    	
    	/*
    }
    
    
    
    @Test
    public void check120Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check121Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check122Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check123Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check124Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check125Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check126Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check127Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    //테스트 
    @Test
    public void check128Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    /*	
    	
    }
    
    
    
    @Test
    public void check129Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check130Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check131Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check132Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check133Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check134Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check135Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check136Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    //테스트 
    @Test
    public void check137Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    /*	
    	
    }
    
    
    
    @Test
    public void check138Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check139Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check140Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check141Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check142Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check143Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check144Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check145Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    //테스트 
    @Test
    public void check146Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    /*	
    	
    }
    
    
    
    @Test
    public void check147Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check148Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check149Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check150Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check151Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check152Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check153Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check154Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    //테스트 
    @Test
    public void check155Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    /*	
    	
    }
    
    
    
    @Test
    public void check156Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check157Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check158Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check159Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check160Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check161Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check162Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check163Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    //테스트 
    @Test
    public void check164Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    /*	
    	
    }
    
    
    
    @Test
    public void check165Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check166Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check167Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check168Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check169Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check170Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check171Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check172Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    //테스트 
    @Test
    public void check173Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    /*	
    	
    }
    
    
    
    @Test
    public void check174Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check175Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check176Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check177Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check178Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check179Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check180Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check181Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    //테스트 
    @Test
    public void check182Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    /*	
    	
    }
    
    
    
    @Test
    public void check183Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check184Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check185Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check186Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check187Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check188Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check189Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check190Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    //테스트 
    @Test
    public void check191Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    /*	
    	
    }
    
    
    
    @Test
    public void check192Test()
    {
    	home.goHomeScreen();
    	home.clickAdsAPIDemoBtn();
    	adsAPIDemo.checkAPIDemoScreen();
    	
    }
    
    
    
    @Test
    public void check193Test()
    {
    	
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkEachAPI();
    	
    }
    
    @Test
    public void check194Test()
    {
    	adsAPIDemo.goAPIListWindow();
    	adsAPIDemo.checkTextView();
    }
    
    
    @Test
    public void check195Test()
    {
    	home.goHomeScreen();
    	home.clickAdsListViewBtn();
    	adsListView.checkListViewScreen();
    	
    }
    
    @Test
    public void check196Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.clickListImageViewItem();
    }  
    
    @Test
    public void check197Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.tapListLinearLayoutItem();    	
    }  
    
    @Test
    public void check198Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeUp();
    	
    	
    }
    
    @Test
    public void check199Test()
    {
    	adsListView.checkListViewScreen();
    	adsListView.screenSwipeDown();
    	
    }
    
    @Test
    public void check200Test()
    {
    	
    	home.checkHomeScreen();
    	
    	/*
    	System.err.println("AppiumDemo.loginTest");
    	

    	Runtime.getRuntime();
        String app_package_name = "com.example.hwangheeseon.myapplication:id/";
        By userId = By.id(app_package_name + "idET");
        By password = By.id(app_package_name + "pwdET");
        By login_Button = By.id(app_package_name + "login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich");
        driver.findElement(password).sendKeys("1234");
        driver.findElement(login_Button).click(); 
      */
    	
    	
    
    
   // }
    
/*
  
*/   
    
    
   

}
