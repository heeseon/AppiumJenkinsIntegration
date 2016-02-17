package urqa.io.AppiumDemoWithJenkins;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Vector;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ProcessRunner {  
	
	public Vector vector = new Vector();
    public static void main(String[] args)
                throws IOException,    InterruptedException {
        String[] command = new String[] { "/Users/hwangheeseon/Library/Android/sdk/platform-tools/adb", "devices" };
        ProcessRunner runner = new ProcessRunner();
        runner.byRuntime(command);
        //for(int j = 0 ; j < 20 ; ++ j){
        for(int i = 0 ; i < runner.vector.size() ; ++ i){
        	try {
        		AndroidSetup.prepareAndroidForAppium("/Users/hwangheeseon/Documents/workspace3/AppiumDemo/apps", "com.facebook.katana_50.0.0.10.54-16053538_minAPI15.apk", (String) runner.vector.get(i));
        		JUnitExecutionTestListener.prepareJUnitExecutionListener();
        		Result result = JUnitCore.runClasses(JunitTestSuite.class);
      	      	for (Failure failure : result.getFailures()) {
      	         System.out.println(failure.toString());
      	      	}
      	      	System.out.println(result.wasSuccessful());
        	}catch(Exception e){
        		
        	}
        	
        	
        }
//        }
        
//        runner.byProcessBuilder(command);
//        runner.byProcessBuilderRedirect(command);
    }

    public void byRuntime(String[] command)
                throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        printStream(process);
    }

    public void byProcessBuilder(String[] command)
                throws IOException,InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();
        printStream(process);
    }

    private void printStream(Process process)
                throws IOException, InterruptedException {
        process.waitFor();
        InputStream psout = process.getInputStream();
        if (psout != null) {
        	
        	try{
        		BufferedReader reader = new BufferedReader(new InputStreamReader(psout));
            	String line;
    	        while ((line = reader.readLine()) != null) {
    	        	System.err.println(line);
    	        	if(line.endsWith("device")){
    	        		vector.add(line.substring(0, line.indexOf('\t')));
    	        		System.err.println(line.substring(0, line.indexOf('\t')));
    	        	}
    	        }
    	        
    	        reader.close();
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	
        	
        	
//            copy(psout, System.out);
        }
    }

    public void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int n = 0;
        while ((n = input.read(buffer)) != -1) {
            output.write(buffer, 0, n);
        }
    }
}
