package urqa.io.AppiumDemoWithJenkins;



import io.appium.heeseon.android.AndroidDriver;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

public class AndroidSetup extends BaseScreen{
	
	public static AndroidDriver driver;
	//public static String testResultPath;
	
    public AndroidSetup(WebDriver _driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		driver = (AndroidDriver) _driver;
	}


    protected static AndroidDriver prepareAndroidForAppium(String appPath, String appName) throws MalformedURLException {
        File appDir = new File(appPath);
        File app = new File(appDir, appName);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device","Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName",System.getProperty("platformName"));
        capabilities.setCapability("platformName", System.getProperty("platformVersion"));
        capabilities.setCapability("platformVersion","5.0");
        //capabilities.setCapability("udid","192.168.56.101:5555");
        

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;
    }
    public static AndroidDriver prepareAndroidForAppium(String appPath, String appName, String id) throws MalformedURLException {
        File appDir = new File(appPath);
        File app = new File(appDir, appName);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device","Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName","Android");
        capabilities.setCapability("platformName","Android");
        //capabilities.setCapability("udid",id);
        

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;
    }
    
    protected static void destroyAndroidForAppium(){
    	
    	String profile = driver.endProfile();
    	
//    	System.err.println("========endProfile = " + profile);
//    	String result = driver.customProperty("end", "");
    	
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
//    	System.err.println("result = " + result);
    	//File outputfile = new File(destPath + "/" + "outputfile.json");
    	
    	JSONArray info = minesota(profile);
    	
    	
    	
    	
    	sendresult(info);
    	 
    	driver.quit();
    }

    
    public static void sendresult(JSONArray info){
    	
    	try {
    		String app = ",";
        	String device = "{";
    		
    		for(int k = 0 ; k < info.size() ; ++ k){
            	net.sf.json.JSONObject jsonData = (net.sf.json.JSONObject)info.get(k);
            	
            	Iterator keyData = jsonData.keys();
            	String keyObj = null;
            	String dataObj;
            	
            	
            	
            	while(keyData.hasNext()){
            		keyObj = (String) keyData.next();
            		dataObj = (String) jsonData.get(keyObj);
            		
            		if(keyObj.endsWith("packageName"))
            		{
            			app += "\"packageName\" : \"" + dataObj + "\",";
            			
            		}
            		else if(keyObj.endsWith("sdk")){
            			app += "\"sdk\" : \"" + dataObj + "\",";
            		}
            		else if(keyObj.endsWith("version")){
            			app += "\"version\" : \"" + dataObj + "\",";
            		}
            		else if(keyObj.endsWith("period")){
            			app += "\"period\" : \"" + dataObj + "\"";
            		}
            		else if(keyObj.endsWith("manufacturer"))
            		{
            			device += "\"manufacturer\" : \"" + dataObj + "\",";
            			
            		}
            		else if(keyObj.endsWith("release"))
            		{
            			device += "\"release\" : \"" + dataObj + "\",";
            			
            		}
            		else if(keyObj.endsWith("model"))
            		{
            			device += "\"model\" : \"" + dataObj + "\",";
            			
            		}
            		else if(keyObj.endsWith("country"))
            		{
            			device += "\"country\" : \"" + dataObj + "\"}";
            			
            		}
            		
            		
            		
                	
                	
            	}
            	
            	
            	//System.err.println("app = " + app +", device = " + device);
            	JUnitExecutionTestListener.app = app;
            	JUnitExecutionTestListener.device = device;
            	
            	
    		}




        } catch (Exception e) {
            e.printStackTrace();
        }
    

       


    	
    	
    }
    

    
    public static JSONArray minesota(String result){
    	byte res[]=driver.pullServerFile(result);
    	
    	//System.err.println("data = " + new String(res));
    	JSONArray infoValue = null ;

    	
    	String jsonTxt = null;
		try {
			jsonTxt = IOUtils.toString( new InputStreamReader(new ByteArrayInputStream(res) ));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		net.sf.json.JSONObject json = (net.sf.json.JSONObject) net.sf.json.JSONSerializer.toJSON( jsonTxt ); 

		Iterator it = json.keys();
		
		
		BufferedWriter battery = null;
		try {
			battery = new BufferedWriter(new FileWriter(destPath + File.separator + "battery.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter cpu_user = null;
		try {
			cpu_user = new BufferedWriter(new FileWriter(destPath + File.separator + "cpu_user.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter cpu_system = null;
		try {
			cpu_system = new BufferedWriter(new FileWriter(destPath + File.separator + "cpu_system.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter nativePrivateDirty = null;
		try {
			nativePrivateDirty = new BufferedWriter(new FileWriter(destPath + File.separator + "nativePrivateDirty.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BufferedWriter dalvikPrivateDirty = null;
		try {
			dalvikPrivateDirty = new BufferedWriter(new FileWriter(destPath + File.separator + "dalvikPrivateDirty.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter totalPrivateDirty = null;
		try {
			totalPrivateDirty = new BufferedWriter(new FileWriter(destPath + File.separator + "totalPrivateDirty.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter nativePss = null ;
		try {
			nativePss = new BufferedWriter(new FileWriter(destPath + File.separator + "nativePss.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter dalvikPss = null;
		try {
			dalvikPss = new BufferedWriter(new FileWriter(destPath + File.separator + "dalvikPss.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter totalPss = null;
		try {
			totalPss = new BufferedWriter(new FileWriter(destPath + File.separator + "totalPss.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter nativeHeapAllocatedSize = null;
		try {
			nativeHeapAllocatedSize = new BufferedWriter(new FileWriter(destPath + File.separator + "nativeHeapAllocatedSize.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter nativeHeapSize = null;
		try {
			nativeHeapSize = new BufferedWriter(new FileWriter(destPath + File.separator + "NativeHeapSize.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BufferedWriter eglPrivateDirty = null;
		try {
			eglPrivateDirty = new BufferedWriter(new FileWriter(destPath + File.separator + "eglPrivateDirty.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BufferedWriter eglPss = null;
		try {
			eglPss = new BufferedWriter(new FileWriter(destPath + File.separator + "eglPss.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BufferedWriter glPss = null;
		try {
			glPss = new BufferedWriter(new FileWriter(destPath + File.separator + "glPss.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BufferedWriter glPrivateDirty = null;
		try {
			glPrivateDirty = new BufferedWriter(new FileWriter(destPath + File.separator + "glPrivateDirty.json", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		BufferedWriter totalresult = null;
		try {
			totalresult = new BufferedWriter(new FileWriter(destPath + File.separator + resultDirectory + "_profile.js", true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			totalresult.write("var indexData = [");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean first = true;
		
    	if(it != null){
    		String key = "";
    		int i = 0;
    		
    		if( it.hasNext() ) key = (String) it.next(); //info
    		
    		JSONArray info = (JSONArray) json.get(key);
    		
    		infoValue = info;
    		
    		
    		if( it.hasNext() ) key = (String) it.next(); //before
    		
    		
            while(it.hasNext()){
            	
            	key = (String) it.next();
            	
                String fileName = destPath + File.separator + (i++) + ".json";
                System.out.println( "fileName = " + fileName );
                
                JSONArray profile = (JSONArray) json.get(key);
                
                for(int k = 0 ; k < profile.size() ; ++ k){
                	net.sf.json.JSONObject jsonData = (net.sf.json.JSONObject)profile.get(k);
                	Iterator keyData = jsonData.keys();
                	String keyObj = null;
                	String dataObj;
                	if( keyData.hasNext() ) keyObj = (String) keyData.next();
                	dataObj = (String) jsonData.get(keyObj);
                	
                	try {
                		if(first){
                			totalresult.write("\"" + key + "_" + keyObj + "\"");
                			//first = false;
                		}
                		else 
                			totalresult.write(", \"" + key + "_" + keyObj + "\"");
            		} catch (IOException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                	
                	String ret[] = dataObj.split(":");
                	
                	if(battery != null){
                		if(ret[0] != "undefined"){
                	
		            		try {
		            			if(first){
		            				battery.write(ret[0]);
		                		}
		                		else 
		                			battery.write("," + ret[0]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
                		}else {
                			try {
    							battery.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                    		battery = null;
                		}
                	}
                	
                	if(cpu_user != null){
                		if(ret[1] != "undefined"){
                	
		            		try {
		            			if(first){
		            				cpu_user.write(ret[1]);
		                		}
		                		else 
		                			cpu_user.write("," + ret[1]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
                		}
                		else {
                			try {
    							cpu_user.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                    		cpu_user = null;
                		}
                	}

                	if(cpu_system != null){
                		if(ret[2] != "undefined"){
                	
		            		try {
		            			if(first){
		            				cpu_system.write(ret[2]);
		                		}
		                		else 
		                			cpu_system.write("," + ret[2]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
                		}
                		else {
                			try {
								cpu_system.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                			cpu_system = null;
                		}
                	}
                	
                	if(nativePrivateDirty != null){
                		if(ret[3] != "undefined"){
                	
		            		try {
		            			if(first){
		            				nativePrivateDirty.write(ret[3]);
		                		}
		                		else 
		                			nativePrivateDirty.write("," + ret[3]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
                		}else {
                			try {
    							nativePrivateDirty.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                    		nativePrivateDirty = null;
                		}
                	}
                	
            		if(dalvikPrivateDirty != null){
            			if(ret[4] != "undefined"){
            		
		            		try {
		            			if(first){
		            				dalvikPrivateDirty.write(ret[4]);
		                		}
		                		else 
		                			dalvikPrivateDirty.write("," + ret[4]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
            			}
            			else {
            				try {
    							dalvikPrivateDirty.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                			dalvikPrivateDirty = null;
            			}
            		}
            		
            		if(totalPrivateDirty != null){
            			if(ret[5] != "undefined"){
            		
		            		try {
		            			if(first){
		            				totalPrivateDirty.write(ret[5]);
		                		}
		                		else 
		                			totalPrivateDirty.write("," + ret[5]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
            			} else {
            				try {
    							totalPrivateDirty.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                			totalPrivateDirty = null;
            			}
            		
            		}
            		if(nativePss != null){
            			if( ret[6] != "undefined"){
            		
		            		try {
		            			if(first){
		            				nativePss.write(ret[6]);
		                		}
		                		else 
		                			nativePss.write("," + ret[6]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
            			}
            			else {
            				try {
    							nativePss.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                			nativePss = null;
            			}
            		}
            		
            		if(dalvikPss != null){
            			if( ret[7] != "undefined"){
            		
		            		try {
		            			if(first){
		            				dalvikPss.write(ret[7]);
		                		}
		                		else 
		                			dalvikPss.write("," + ret[7]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
            			}
            			else {
            				try {
    							dalvikPss.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                			dalvikPss = null;
            			}
            		}
            		
            		if(totalPss != null){
            			if( ret[8] != "undefined"){
            		
		            		try {
		            			if(first){
		            				totalPss.write(ret[8]);
		                		}
		                		else 
		                			totalPss.write("," + ret[8]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
            			}
            			else {
            				try {
    							totalPss.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                			totalPss = null;
            			}
            		
            		}
            		if(nativeHeapAllocatedSize != null){
            			if( ret[9] != "undefined"){
            		
		            		try {
		            			if(first){
		            				nativeHeapAllocatedSize.write(ret[9]);
		                		}
		                		else 
		                			nativeHeapAllocatedSize.write("," + ret[9]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
            			}
            			else {
            				try {
    							nativeHeapAllocatedSize.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                			nativeHeapAllocatedSize = null;
            			}
            		}
            		
            		if(nativeHeapSize != null){
            			if( ret[10] != "undefined"){
            		
		            		try {
		            			if(first){
		            				nativeHeapSize.write(ret[10]);
		                		}
		                		else 
		                			nativeHeapSize.write("," + ret[10]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
            			}else {
            				try {
    							nativeHeapSize.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                			nativeHeapSize = null;
            			}
            		}
            		
            		
            		if(eglPrivateDirty != null ){
            			if(!ret[11].trim().endsWith("undefined")){
            		
            				//System.err.println("eglPrivateDirty = " +  ret[11]);
            			
		            		try {
		            			if(first){
		            				eglPrivateDirty.write(ret[11]);
		                		}
		                		else 
		                			eglPrivateDirty.write("," + ret[11]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
            			}
            			else {
            				try {
    							eglPrivateDirty.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                			eglPrivateDirty = null;
            			}
            			
            		}
            		
            		if(eglPss != null){
            			if(!ret[12].trim().endsWith( "undefined")){
            		
            				//System.err.println("eglPss = " +  ret[12]);
		            		try {
		            			if(first){
		            				eglPss.write(ret[12]);
		                		}
		                		else 
		                			eglPss.write("," + ret[12]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
            			}
            			else {
            				try {
    							eglPss.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                			eglPss = null;
            			}
            		
            		}
            		
            		if(glPrivateDirty != null ){
            			if( ! ret[13].trim().endsWith("undefined")){
            		
	            			//System.err.println("glPrivateDirty = " +  ret[13]);
		            		try {
		            			if(first){
		            				glPrivateDirty.write(ret[13]);
		                		}
		                		else 
		                			glPrivateDirty.write("," + ret[13]);
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
            			}else {
            				try {
    							glPrivateDirty.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                			glPrivateDirty = null;
            			}
            		
            		}
            		
            		if(glPss != null ){
            			if( ! ret[14].trim().endsWith("undefined")){
            		
	            			//System.err.println("glPss = " +  ret[14]);
		            		try {
		            			if(first){
		            				glPss.write(ret[14]);
		                		}
		                		else 
		                			glPss.write("," + ret[14]);
		            		
		            		} catch (IOException e1) {
		            			// TODO Auto-generated catch block
		            			e1.printStackTrace();
		            		}
            			}
            			else {
            				try {
    							glPss.close();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                			glPss = null;
            			}
            		}
          		
            		if(first){
            			first = false;
            		}
            		
                	
                	//System.out.println("key = " + keyObj + ", value = " + dataObj);
                }
            }
            try {
        		totalresult.write("];var minnesota = [");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
            if(battery != null){
	    		try {
	    			battery.flush();
	    			battery.close();
	    			
	    			totalresult.write("{ name : \"Battery\", values: [ ");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "battery.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			
	    			totalresult.write("]},");
	    			
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
            }
            if(cpu_user != null){
	    		try {
	    			cpu_user.flush();
	    			cpu_user.close();
	    			
	    			totalresult.write("{ name : \"User CPU\", values: [ ");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "cpu_user.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    			
	    			
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
            }
    		if(cpu_system != null){
    		
    		
	    		try {
	    			cpu_system.flush();
	    			cpu_system.close();
	    			totalresult.write("{ name : \"System CPU\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "cpu_system.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    			
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}
    		if(totalPss != null){
    		
	    		try {
	    			totalPss.flush();
	    			totalPss.close();
	    			totalresult.write("{ name : \"totalPss\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "totalPss.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}
    		if(nativePss != null){
	    		try {
	    			nativePss.flush();
	    			nativePss.close();
	    			totalresult.write("{ name : \"nativePss\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "nativePss.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}
    		if(dalvikPss != null){
	    		try {
	    			dalvikPss.flush();
	    			dalvikPss.close();
	    			totalresult.write("{ name : \"dalvikPss\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "dalvikPss.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}

    		
    		if(eglPss != null){
    		
	    		try {
	    			eglPss.flush();
	    			eglPss.close();
	    			totalresult.write("{ name : \"eglPss\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "eglPss.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}
    		
    		if(glPss != null){
	    		try {
	    			glPss.flush();
	    			glPss.close();
	    			totalresult.write("{ name : \"glPss\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "glPss.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}
    		if(totalPrivateDirty != null){

	    		try {
	    			totalPrivateDirty.flush();
	    			totalPrivateDirty.close();
	    			totalresult.write("{ name : \"totalPrivateDirty\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "totalPrivateDirty.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}
    		if(nativePrivateDirty != null){       		
	    		try {
	    			nativePrivateDirty.flush();
	    			nativePrivateDirty.close();
	    			totalresult.write("{ name : \"nativePrivateDirty\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "nativePrivateDirty.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}
    		if(dalvikPrivateDirty != null){  
	    		try {
	    			dalvikPrivateDirty.flush();
	    			dalvikPrivateDirty.close();
	    			totalresult.write("{ name : \"dalvikPrivateDirty\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "dalvikPrivateDirty.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}
    		if(eglPrivateDirty != null){ 
    		
	    		try {
	    			eglPrivateDirty.flush();
	    			eglPrivateDirty.close();
	    			totalresult.write("{ name : \"eglPrivateDirty\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "eglPrivateDirty.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}
    		if(glPrivateDirty != null){ 
	    		try {
	    			glPrivateDirty.flush();
	    			glPrivateDirty.close();
	    			totalresult.write("{ name : \"glPrivateDirty\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "glPrivateDirty.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}
    		if(nativeHeapAllocatedSize != null){  		
	    		try {
	    			nativeHeapAllocatedSize.flush();
	    			nativeHeapAllocatedSize.close();
	    			totalresult.write("{ name : \"NativeHeapAllocatedSize\", values: [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "nativeHeapAllocatedSize.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
	    			totalresult.write("]},");
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
    		}
    		
    		try {
    			if(nativeHeapSize != null){
	    			nativeHeapSize.flush();
	    			nativeHeapSize.close();
	    			
	    			totalresult.write("{ name : \"NativeHeapSize\", values : [");
	    			try {
	    				   FileInputStream fis = new FileInputStream(destPath + File.separator + "NativeHeapSize.json");
	    				   
	    				   
	    				   int data = 0;
	    				   while((data=fis.read())!=-1) {
	    					   totalresult.write(data);
	    				   }
	    				   fis.close();
	    				  
	    				   
	    			} catch (IOException e) {
	    				   // TODO Auto-generated catch block
	    				   e.printStackTrace();
	    			}
    			}
    			totalresult.write("]}];");
    			totalresult.flush();
    			totalresult.close();
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		
    	}
        
    	return infoValue;
    }
    
            	
}    		
