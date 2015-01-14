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
			System.out.println("Testing complete with a failure count of " 
				+ result.getFailureCount() + ".");
			for(Failure failure: result.getFailures()){
				System.out.println(failure.toString());	
			}
		}
		System.out.println( "The runtime was " 
				+ String.valueOf(result.getRunTime())
				+ " milliseconds.");
		
	}
	
	public static boolean testExpressionTreeStructure(){
		/*
		 * TODO
		 * Make one of each concrete structure class.
		 * Check compareToNode() for each to each.
		 */
		return false;
	}
	
	public static boolean testExpressionTreeCreation(){
		return false;
	}
}
