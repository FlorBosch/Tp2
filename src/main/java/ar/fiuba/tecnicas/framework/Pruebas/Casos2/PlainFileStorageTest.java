package ar.fiuba.tecnicas.framework.Pruebas.Casos2;

import ar.fiuba.tecnicas.framework.JTest.Test;
import ar.fiuba.tecnicas.framework.JTest.TestCase;
import ar.fiuba.tecnicas.framework.JTest.TestCreator;
import ar.fiuba.tecnicas.framework.JTest.TestRunner;
import ar.fiuba.tecnicas.framework.JTest.TestSuite;
import ar.fiuba.tecnicas.framework.JTest.Timer;
import ar.fiuba.tecnicas.framework.JTest.rerunner.PlainFileStorage;
import ar.fiuba.tecnicas.framework.JTest.rerunner.RerunMode;
import ar.fiuba.tecnicas.framework.JTest.rerunner.RerunStorage;

public class PlainFileStorageTest implements TestCreator {

	@Override
	public Test getTest() throws Exception {
		TestSuite suite = new TestSuite("Suite");
		TestCase test1 = new MyTestCase("Test that doesn't fail (1)");
		TestCase test2 = new FailedTestCase("Test that fails (2)");
		TestCase test3 = new MyTestCase("Test that doesn't fail (3)");

		suite.addTest(test1);
		suite.addTest(test2);
		suite.addTest(test3);

		return suite;
	}
	
	private static void firstRun(RerunStorage storage) {
		TestCreator creatorTest = new PlainFileStorageTest();
		TestRunner runner = new TestRunner();
		String args[] = {};
		
		runner.setRerunStorage(storage);
		runner.setRerunMode(RerunMode.RECORD);
		
		runner.setCreatorTest(creatorTest);
		runner.run(args);
	}
	
	private static void secondRun(RerunStorage storage) {
		TestCreator creatorTest = new PlainFileStorageTest();
		TestRunner runner = new TestRunner();
		String args[] = {};
		
		runner.setRerunStorage(storage);
		runner.setRerunMode(RerunMode.RERUN);
		
		runner.setCreatorTest(creatorTest);
		runner.run(args);
	}

	public static void main(String args[]) {
		Timer.setTimeOut(2000);
		
		RerunStorage storage = new PlainFileStorage();

		firstRun(storage);
		secondRun(storage);
	}

}
