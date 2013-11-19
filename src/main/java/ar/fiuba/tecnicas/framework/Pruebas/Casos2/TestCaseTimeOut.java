package ar.fiuba.tecnicas.framework.Pruebas.Casos2;

import org.junit.Assert;

import ar.fiuba.tecnicas.framework.JTest.TestCase;

public class TestCaseTimeOut extends TestCase {

    public TestCaseTimeOut(String testname) {
	super(testname);
    }

    @Override
    public void runTest() {
	try {
	    Thread.sleep(50);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	Assert.assertTrue(true);
    }

}
