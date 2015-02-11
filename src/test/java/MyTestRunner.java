import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MyTestRunner{
	
	private static int totalFailedTests = 0;

	public static void main(String[] args){
		runClassTests(TestComponentNodes.class);
		runClassTests(TestSymbols.class);
		runClassTests(TestInterpretable.class);
		runClassTests(TestInterpreter.class);
		runClassTests(TestInterpreterInputErrors.class);
		runClassTests(TestIterators.class);
		runClassTests(TestVisitor.class);
		runClassTests(TestExpressionBuilder.class);
		runClassTests(TestCommand.class);
		runClassTests(TestCommandFactory.class);
		runClassTests(TestState.class);
		runClassTests(TestEvaluatable.class);
		runClassTests(TestEvaluator.class);
		
		if (totalFailedTests == 0){
			System.out.println("All test classes have passed!\n");
		} else if (totalFailedTests == 1){
			System.out.println( "All testing has completed with "
				+ " 1 test class having failed.\n");
		} else {
			System.out.println( "All testing has completed with "
				+ String.valueOf(totalFailedTests)
				+ " test classes having failed.\n");
		}
	}
	
	
	public static void runClassTests(Class testClass){
		System.out.println("Testing class: " + testClass.toString());		
		Result result = JUnitCore.runClasses(testClass);
		if( result.wasSuccessful()){
			System.out.println(result.getRunCount()
				+ " tests have completed succesfully.");
		} else if (result != null && System.out != null) {
			totalFailedTests++;
			System.out.println("Testing completed with " 
				+ result.getFailureCount() + " failures out of "
				+ result.getRunCount()	+ " total tests run.");
			for(Failure failure: result.getFailures()){
				System.out.println(failure.toString());	
			}
		} else {
			totalFailedTests++;
			System.out.println("Testing completed with no result or a "
				+ "null System.out PrintStream.");
		}
		System.out.println( "The runtime was " 
				+ String.valueOf(result.getRunTime())
				+ " milliseconds.");		
		System.out.println("Finished testing class: " + testClass.toString() + "\n");

	}
}
