package urqa.io.AppiumDemoWithJenkins;


import java.util.Vector;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class UrQARunner extends BlockJUnit4ClassRunner {

	static Vector vector = new Vector();
	
    public UrQARunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override public void run(RunNotifier notifier){
    	
    	System.err.println("notifier " + notifier);
    	
    	if( !vector.contains(notifier)){
    		JUnitExecutionTestListener listener = new JUnitExecutionTestListener();	
    		notifier.addListener(listener);
    		if(vector.size() == 0){
    			listener.prepareJUnitExecutionListener();
    		}
    		vector.add(notifier);
    	}
    	
    	System.out.println("end " + notifier);
    	
        super.run(notifier);
        
        
    	
    }
    
    
}