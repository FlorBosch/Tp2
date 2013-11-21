package ar.fiuba.tecnicas.framework.Pruebas.Casos2;

import ar.fiuba.tecnicas.framework.JTest.Assert;
import ar.fiuba.tecnicas.framework.JTest.TestCase;

public class FailedTestCase extends TestCase {

	public FailedTestCase(String testname) {
		super(testname);
	}

	@Override
	public void runTest() {
		Assert.assertTrue(false);
	}

}
