package urqa.io.AppiumDemoWithJenkins;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Vector;

public class CopyOfProcessRunner {  
	
    public static void main(String[] args)
                throws IOException,    InterruptedException {
        String[] command = new String[] { "/Users/hwangheeseon/Library/Android/sdk/platform-tools/adb", "devices" };
        CopyOfProcessRunner runner = new CopyOfProcessRunner();
        runner.byRuntime(command);
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
