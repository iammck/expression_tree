import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MyTestRunner{

	public static void main(String[] args){
		System.out.print("\n\n");
		runClassTests(TestStructure.class);
		runClassTests(TestCreation.class);
	}
	
	
	public static void runClassTests(Class testClass){
		System.out.println("Testing class: " + testClass.toString());		
		Result result = JUnitCore.runClasses(testClass);
		if( result.wasSuccessful()){
			System.out.println(result.getRunCount()
				+ " tests have completed succesfully.");
		} else {		
			System.out.println("Testing completed with " 
				+ result.getFailureCount() + " failures out of "
				+ result.getRunCount()	+ " total tests run.");
			for(Failure failure: result.getFailures()){
				System.out.println(failure.toString());	
			}
		}
		System.out.println( "The runtime was " 
				+ String.valueOf(result.getRunTime())
				+ " milliseconds.");		
		System.out.println("Finished testing class: " + testClass.toString() + "\n");

	}
}
