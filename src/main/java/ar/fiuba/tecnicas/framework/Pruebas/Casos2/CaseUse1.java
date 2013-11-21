package ar.fiuba.tecnicas.framework.Pruebas.Casos2;

import ar.fiuba.tecnicas.framework.JTest.*;

public class CaseUse1 implements TestCreator {

    @Override
    public Test getTest() throws Exception {
	TestSuite suite = new TestSuite("TS1");
	TestSuite suite2 = new TestSuite("TS2");

	TestCase test1 = new MyTestCase("T1");
	TestCase test2 = new MyTestCase("T2");
	TestCase test3 = new MyTestCase("T3");
	TestCase testTimeOut1 = new TestCaseTimeOut("TestTimeOut1");
	TestCase testTimeOut2 = new TestCaseTimeOut("TestTimeOut2");

	test1.addTag("SLOW");
	test3.addTag("SLOW");
	testTimeOut1.addTag("SLOW");
	testTimeOut2.addTag("SLOW");

	suite.addTest(test1);
	suite.addTest(test2);
	suite.addTest(test3);
	suite.addTest(testTimeOut1);
	suite2.addTest(testTimeOut2);
	suite.addTest(suite2);
	return suite;
    }

    // para correr el test se paso como parametros el tag: SLOW
    // mediante el parametro: -tctags SLOW
    // y sólo se ejecuta el test T1, T3, testTimeOut1 y testTimeOut2
    public static void main(String args[]) {
	TestCreator creatorTest = new CaseUse1();
	TestRunner runner = new TestRunner();
	runner.setCreatorTest(creatorTest);
	runner.run(args);
    }
}
