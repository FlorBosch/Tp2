package ar.fiuba.tecnicas.framework.Pruebas.Casos2;

import ar.fiuba.tecnicas.framework.JTest.Test;
import ar.fiuba.tecnicas.framework.JTest.TestCase;
import ar.fiuba.tecnicas.framework.JTest.TestCreator;
import ar.fiuba.tecnicas.framework.JTest.TestSuite;

public class TestCreatorSuccessTest implements TestCreator{

    @Override
    public Test getTest() throws Exception {
	TestSuite suite = new TestSuite("TS1");
	TestCase test1 = new MyTestCase("T1");
	TestCase test2 = new MyTestCase("T2");
	TestCase test3 = new MyTestCase("T3");

	suite.addTest(test1);
	suite.addTest(test2);
	suite.addTest(test3);
	return suite;	
	
    }

}
