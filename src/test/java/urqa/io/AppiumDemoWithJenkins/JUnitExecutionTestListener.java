package urqa.io.AppiumDemoWithJenkins;


import org.apache.commons.io.FileUtils;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.openqa.selenium.OutputType;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;



public class JUnitExecutionTestListener extends RunListener {
	
	static FileWriter file = null;
	JSONObject logObj = null;//new JSONObject();
//	JSONArray list = null; //new JSONArray();
	
	static public boolean firstcase = false;
	
	public String profilefullpath = "";
	public static String testResultDir ="";

	
	public static long testStartedTime = 0;
	public static long testEndedTime = 0;
	
	public static String app = "";
	public static String device = "";
	
	public static String testResultPullPath ="";
	public static String profileResultPullPath = "";
	public static String resultDirectory = "";
	
	
	public static void prepareJUnitExecutionListener(){
		System.out.println("===========prepareJUnitExecutionListener=============");
		try {
		
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss"); 
			String str = dateFormat.format(new Date()); 

			String destDir = "testResultDir"+ File.separator + str; 
			File dir = new File(destDir);
			dir.mkdirs();
			System.out.println("full = " + dir.getAbsolutePath());
			
			BaseScreen.destPath = testResultDir = dir.getAbsolutePath();
			BaseScreen.resultDirectory = resultDirectory = str;
			file = new FileWriter(destDir + File.separator + str + "_result.json");
			
			file.write("[");
			testResultPullPath = destDir + File.separator + str + "_result.json";
			profileResultPullPath = destDir + File.separator + str + "_profile.js";
			
			firstcase = false;

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void takeScreenShot(String name) { 
		// Set folder name to store screenshots. 
		String destDir = testResultDir + File.separator + "screenshots"; 
		// Capture screenshot. 
		File scrFile = FacebookTest.driver.getScreenshotAs(OutputType.FILE); 
		// Set date format to set It as screenshot file name. 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy__hh_mm_ss"); 
		// Create folder under project with name "screenshots" provided to destDir. 
		new File(destDir).mkdirs(); 
		// Set file name using current date time. 
		String destFile = name + "-" + dateFormat.format(new Date()) + ".png"; 
		try { 
			// Copy paste file at destination folder location 
			FileUtils.copyFile(scrFile, new File( destDir + File.separator + destFile)); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		 
	}
	
	public void sendLog(String type, String value){
    	//String result = FacebookTest.driver.customProperty(type, value);
    	//System.out.println("Started: result: " + result);

	}
	

    public void testRunStarted(Description description) throws Exception {
        System.out.println("testRunStarted");
        //takeScreenShot();
        
        //prepareJUnitExecutionListener();
    }

    public void testRunFinished(Result result) throws Exception {
    	
    	try{
	        System.out.println("testRunFinished Number of tests executed: " + result.getRunCount());
	        
	        
	        String t_result = "{ \"ResultDirectory\" : \"" + resultDirectory + "\", ";
	        
	        if(file != null){
//	        	JSONObject obj = new JSONObject();
//	        	list = new JSONArray();
//	        	obj.put("Number of tests executed", result.getRunCount());
	        	//list.add("Number of tests executed: " + result.getRunCount());
	        	int totalCount = result.getRunCount();
	        	int failureCount = result.getFailureCount();
	        	int ignoreCount = result.getIgnoreCount();
	        	int passCount = totalCount - failureCount - ignoreCount;
	        	
	        	t_result += "\"total\" : " + totalCount + ", \"shortresult\" : \"" + passCount +"/" + failureCount + "/" + ignoreCount +"\", \"resultfile\" :\""+ resultDirectory + "_result.json\", \"profilefile\" : \"" +resultDirectory + "_profile.js\"" ;
	        	
	        	System.out.println("data for testdb = " +  t_result);
	        	
//	        	obj.put("PASS", passCount);
//	        	obj.put("FAIL", failureCount);
//	        	obj.put("Ignored", ignoreCount);
//	        	list.add(obj);
//	        	
	        	file.write( "]");
	        	file.flush();
	        	file.close();
	        	
	        	
	        	this.senddata(t_result);
	        	
	        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        
  
     }

    public void testStarted(Description description) throws Exception {

    	try{
	    	try{
	    		Thread.sleep(1000);
	    	}catch(Exception e){
	    		
	    	}

	    	if(!firstcase){
	    		//String path = FacebookTest.driver.setProfile(resultDirectory, 2000, "com.facebook.katana");
	    		System.out.println("====================path = " );
	    		
	    	}
	    	
	    	
	    	
	    	sendLog("testcasename", description.getClassName() +"."+ description.getMethodName());
	    	
	    	if(!firstcase){
	    		sendLog("profile", "");
	    		sendLog("profileResultDir",testResultDir);
	    		
	    		
	        	
	        	//System.out.println("Started: result: " + result);
	    	//	sendLog("ip", InetAddress.getLocalHost().getHostAddress());
	    		//firstcase = true;
	    		
	    	}
	    	
	    	if(file != null){
	    		logObj = new JSONObject();
	    	}
	        
	    	System.out.println("Started: " + description.getMethodName());
	    	takeScreenShot("before-" +description.getMethodName());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	testStartedTime = System.currentTimeMillis(); 
    	
    	
    }

    public void testFinished(Description description) throws Exception {
    	
    	testEndedTime = System.currentTimeMillis(); 
    	try{
    		
	        System.out.println("Finished: " + description.getMethodName());
	        
        	
	        if(file != null){
	        	if(logObj.size() == 0 ){
	        		logObj.put("Log","");
	        		logObj.put("Result", "PASS");
	        	}
	        	else
	        		logObj.put("Result", "FAIL");
	        	
	        	logObj.put("Duration", ((testEndedTime - testStartedTime)/1000));
	        	//list.add(obj);
	        	
	        	
	        	System.out.println("druation = " + (testEndedTime - testStartedTime/1000));
	        	
	        	if(!firstcase){
	        		firstcase = true;
	        		file.write("{\"" + description.getMethodName() + "\""+ " : " + logObj.toString() + "}");
	        	}
	        	else
	        		file.write(",{\"" + description.getMethodName() + "\""+ " : " + logObj.toString() + "}");
	        	
	        	
	    		file.flush();
	        }
	        
	        try{
	    		Thread.sleep(1000);
	    	}catch(Exception e){
	    		
	    	}
	        
	        takeScreenShot("after-" + description.getMethodName());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    

    public void testFailure(Failure failure) throws Exception {
    	
    		
        System.out.println("Failed: Description : " + failure.getDescription().getMethodName());
        if(file != null){
        	//JSONObject obj = new JSONObject();
        	String value = "";
        	if(logObj.size() > 0 ){
        		value += ("\n" + logObj.getString("Log"));
        		
        	}
        	logObj.put("Log", value + failure.getDescription().getMethodName() +"\n" 
        			+"Failed(Exception) : " +  failure.getException() +"\n" 
        			+"Failed(Message) : " +  failure.getMessage() +"\n"
        			+"Failed(Trace) : " +failure.getTrace());
        	//list.add(obj);
        	
        }
    }

    public void testAssumptionFailure(Failure failure) {
    	
        System.out.println("Failed: " + failure.getDescription().getMethodName());
        if(file != null){
        	//JSONObject obj = new JSONObject();
        	String value = "";
        	if(logObj.size() > 0 ){
        		value += ("\n" + logObj.getString("Log"));
        		
        	}
        	logObj.put("Log", value + failure.getDescription().getMethodName() +"\n" 
        			+"Failed(Exception) : " +  failure.getException() +"\n" 
        			+"Failed(Message) : " +  failure.getMessage() +"\n"
        			+"Failed(Trace) : " +failure.getTrace());
        	//list.add(obj);
        	
        }
    }

    public void testIgnored(Description description) throws Exception {
    	
        System.out.println("Ignored: " + description.getMethodName());
        
        if(file != null){
        	
        	JSONObject obj = new JSONObject();
        	//obj.put("TestCase", description.getMethodName());
        	obj.put("Log", "");
        	obj.put("Result", "Ignored");
        	obj.put("Duration", 0);
        	
        	if(!firstcase){
        		firstcase = true;
        		file.write("{\"" + description.getMethodName() + "\""+ " : " + obj.toString()+ "}");
        	}
        	else
        		file.write(",{\"" + description.getMethodName() + "\""+ " : " + obj.toString()+ "}");
        	
        	file.flush();

        }
        
    }
    
    
    public void senddata(String testdb){
        OkHttpClient client = new OkHttpClient();

        File file = new File(testResultPullPath);
        File file2 = new File(profileResultPullPath);
        
        String contentType = null;
		try {
			contentType = file.toURL().openConnection().getContentType();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        //String contentType = file.toURL().openConnection().getContentType();
        RequestBody fileBody = RequestBody.create(MediaType.parse(contentType), file);
        RequestBody fileBody2 = RequestBody.create(MediaType.parse(contentType), file2);
        
        
        testdb+= app;
        testdb += "}";
        		
        System.out.println("test db = " + testdb);

        String count = "2";
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.FORM)
                .addFormDataPart("device", device)
                .addFormDataPart("result", testdb)
                .addFormDataPart("results", testResultPullPath, fileBody)
                .addFormDataPart("results", profileResultPullPath, fileBody2);
        
        
        
        
        
        

        //        .addFormDataPart("sendProfileName", sendProfileName);
        //Log.i("hhs", "---------------------" + files.size());
//        for(int k = 0 ; k < files.size(); ++ k){
//            Log.i("hhs", "---------------------" +files.elementAt(k).toString());
//            File sendfile = new File(files.elementAt(k).toString());
//
//            RequestBody sendfileBody = RequestBody.create(MediaType.parse(contentType), sendfile);
//            multipartBuilder.addFormDataPart("thumbnail", files.elementAt(k).toString(), sendfileBody);
//        }


        RequestBody requestBody = multipartBuilder.build();


        Request request = new Request.Builder()
                .url("http://localhost:3000/addTestResult")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Request request, IOException e) {
                System.out.println("onFailure........................" + request.toString() + ", " + e.getMessage());
            }

            

			public void onResponse(Response arg0) throws IOException {
				// TODO Auto-generated method stub
				System.err.println("onResponse........................" + arg0.toString() );
			}
        });

    }
    
    
    
    
}
