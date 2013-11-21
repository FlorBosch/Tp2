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
	Timer.setTimeOut(2000);

	TestCreator creatorTest = new XMLStorageTest();
	TestRunner runner = new TestRunner();

	/* Se setea el storage que se va a usar para persistir los datos */
	runner.setRerunStorage(new XMLStorage());

	/* Se setea el modo para recordar la corrida */
	runner.setRerunMode(RerunMode.RECORD);
	runner.setCreatorTest(creatorTest);
	runner.run(args);

	/* Se vuelve a correr seteando el modo de leer de corridas anteriores */
	runner.setRerunMode(RerunMode.RERUN);
	runner.run(args);
    }

}
