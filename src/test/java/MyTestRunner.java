import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MyTestRunner{

	public static void main(String[] args){
		System.out.print("...Testing\n");
		
		Result result = JUnitCore.runClasses(TestStructure.class);
		if( result.wasSuccessful()){
			System.out.println("Testing completed and was succesful.\n");
		} else {		
			System.out.println("Testing completed with " 
				+ result.getFailureCount() + " failures out of "
				+ result.getRunCount()	+ " total tests run. \n");
			for(Failure failure: result.getFailures()){
				System.out.println(failure.toString() + "\n");	
			}
		}
		System.out.println( "The runtime was " 
				+ String.valueOf(result.getRunTime())
				+ " milliseconds.");		
	}
}
