package ar.fiuba.tecnicas.framework.Pruebas.Casos2;

import ar.fiuba.tecnicas.framework.JTest.Test;
import ar.fiuba.tecnicas.framework.JTest.TestCase;
import ar.fiuba.tecnicas.framework.JTest.TestCreator;
import ar.fiuba.tecnicas.framework.JTest.TestRunner;
import ar.fiuba.tecnicas.framework.JTest.TestSuite;
import ar.fiuba.tecnicas.framework.JTest.Timer;
import ar.fiuba.tecnicas.framework.JTest.rerunner.RerunMode;
import ar.fiuba.tecnicas.framework.JTest.rerunner.XMLStorage;

public class XMLStorageTest implements TestCreator {

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

	public static void main(String args[]) {
		Timer.timeOut = 1000000;
		
		TestCreator creatorTest = new XMLStorageTest();
		TestRunner runner = new TestRunner();
		
		runner.setRerunStorage(new XMLStorage());
		// La prueba se hace a mano, primero hay que correrla con STORE y luego con RERUN (cambiar!)
		runner.setRerunMode(RerunMode.RERUN);
		
		runner.setCreatorTest(creatorTest);
		runner.run(args);
	}

}